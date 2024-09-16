package evidencia;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import com.google.gson.Gson;

public class AppDistribuidor {

    public static void main(String[] args) throws Exception {
        String url = "tcp://localhost:61616";
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("mqGVTventa");
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                String json = textMessage.getText();
                Gson gson = new Gson();
                Venta venta = gson.fromJson(json, Venta.class);
                for (Producto producto : venta.getProductos()) {
                    if (producto.getProveedorId().equals("A")) {
                        enviarACola(session, "mqGVTpedidoa", producto);
                    } else if (producto.getProveedorId().equals("C")) {
                        enviarACola(session, "mqGVTpedidoc", producto);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		try {
			while(true) {
				
			}
		} finally {
			session.close();
			if (connection != null) {
				connection.close();
			}
		}
    }

    private static void enviarACola(Session session, String colaNombre, Producto producto) throws Exception {
        Queue queue = session.createQueue(colaNombre);
        MessageProducer producer = session.createProducer(queue);
        String pedidoJson = new Gson().toJson(new Pedido(producto.getProductoId(), producto.getProductoNombre(), producto.getProductoCantidad()));
        TextMessage pedidoMessage = session.createTextMessage(pedidoJson);
        producer.send(pedidoMessage);
    }
}

