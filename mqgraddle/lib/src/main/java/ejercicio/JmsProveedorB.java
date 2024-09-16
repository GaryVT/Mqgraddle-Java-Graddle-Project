package ejercicio;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

public class JmsProveedorB {

    public static void main(String[] args) throws Exception {
        String queueName = "proveedorBQueue";
        String url = "tcp://localhost:61616";
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);

        System.out.println("Proveedor B esperando mensajes...");

        while (true) {
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String json = textMessage.getText();
                System.out.println("Proveedor B recibió: " + json);
            }
        }
    }
}