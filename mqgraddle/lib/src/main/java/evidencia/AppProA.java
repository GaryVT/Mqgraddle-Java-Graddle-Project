package evidencia;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

public class AppProA {

    public static void main(String[] args) throws Exception {
        String url = "tcp://localhost:61616";
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("mqGVTpedidoa");//colaProveedorA
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                String json = textMessage.getText();
                System.out.println("Proveedor A procesando: " + json);
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
}
