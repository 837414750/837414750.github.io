package com.ams.activemq;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//默认的用户名
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//默认的密码
	private static final String BROKEURL="failover://tcp://192.168.171.136:61616";//ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;//连接工厂
		Connection connection;//连接
		Session session;//会话  接收或者发送消息线程
		Destination destination;//消息的目的地
		MessageProducer messageProducer;//消息生产者
		
		//实例化工厂
		connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, JMSProducer.BROKEURL);
		
		try {
			connection=connectionFactory.createConnection();//通过连接工厂连接
			connection.start();//启动连接
			session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//创建Session
			destination=session.createQueue("FirestQueue1");//创建队列
			messageProducer =session.createProducer(destination);//创建消息生产者
			JMSProducer.sendMessage(session, messageProducer);// 发送消息
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送消息
	 * @param session
	 * @param messageProducer
	 * @throws Exception
	 */
	public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
		for (int i = 0; i < 10; i++) {
			TextMessage message = session.createTextMessage("ActiveMq 发送消息"+i);
			System.out.println("发送消息："+"ActiveMq 发送的消息"+i);
			messageProducer.send(message);
		}
	}
}
