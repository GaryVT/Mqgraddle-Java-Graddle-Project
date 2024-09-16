package mqgraddle;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

public class JmsListenerArtemis {

	public static void main(String[] args) throws Exception {
		String queueName = "mymq"; 
		String url = "tcp://localhost:61616";
		ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
		Connection connection = cf.createConnection("admin", "admin");
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(queueName);
		
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message;
				String json;
				try {
					json = msg.getText();
					System.out.println(json);
				} catch (JMSException e) {
					e.printStackTrace();
				}
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