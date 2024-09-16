package evidencia;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class AppVentas {

    public static void main(String[] args) throws Exception {
        Producto producto1 = new Producto("3", "poloGary", 2, "C");
        Producto producto2 = new Producto("3", "poleraGary", 1, "A");
        Producto producto3 = new Producto("3", "shortGary", 3, "C");
        List<Producto> productos = Arrays.asList(producto1, producto2, producto3);

        Venta venta = new Venta(11, productos);
        Gson gson = new Gson();
        String json = gson.toJson(venta);

        String url = "tcp://localhost:61616";
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("mqGVTventa");//VENTASTOPIC
        MessageProducer producer = session.createProducer(topic);
        TextMessage message = session.createTextMessage(json);

        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        System.out.println("Enviando mensaje: " + json);
        producer.send(message);

        session.close();
        connection.close();
    }
}
