package com.ams.activemq2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * @author TeamLu
 *
 */
public class JMSConsumer {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//默认的用户名
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//默认的密码
	private static final String BROKEURL="failover://tcp://192.168.171.136:61616";//ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
	
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;//连接工厂
		Connection connection;//连接
		Session session;//会话  接收或者发送消息线程
		Destination destination;//消息的目的地
		MessageConsumer messageConsumer;//消息消费者
		
		//实例化工厂
		connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
		
		try {
			connection = connectionFactory.createConnection();//工厂连接
			connection.start();
			session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createTopic("FirstTopic1");//创建连接Topic
			messageConsumer=session.createConsumer(destination); // 创建消息消费者
			messageConsumer.setMessageListener(new Listener());//注册消息监听
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
