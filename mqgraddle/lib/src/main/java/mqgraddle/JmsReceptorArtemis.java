package mqgraddle;


import javax.jms.Connection;  
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

public class JmsReceptorArtemis {

	public static void main(String[] args) throws Exception {
		String queueName = "mymq"; 
		String url = "tcp://localhost:61616";
		ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
		Connection connection = cf.createConnection("admin", "admin");
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(queueName);
		
		TextMessage msg = null;
		MessageConsumer consumer = session.createConsumer(queue);
		msg = (TextMessage) consumer.receive();
		String json = msg.getText();
		try {
			session.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		System.out.println(json);
	}
}
