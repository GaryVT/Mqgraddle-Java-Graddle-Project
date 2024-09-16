package ejercicioDoble;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

public class JmsReceptorVestir {

    public static void main(String[] args) throws Exception {
        String queueName = "colaVestir"; 
        String url = "tcp://localhost:61616";
        
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        
        while (true) {
            TextMessage msg = (TextMessage) consumer.receive();
            System.out.println("Recibido en colaVestir: " + msg.getText());
        }
    }
}