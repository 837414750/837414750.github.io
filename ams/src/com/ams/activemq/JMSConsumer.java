package com.ams.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//默认的用户名
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//默认的密码
	private static final String BROKEURL="failover://tcp://192.168.171.136:61616";//ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
	
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;//连接工厂
		Connection connection = null;//连接
		Session session;//会话  接收或者发送消息线程
		Destination destination;//消息的目的地
		MessageConsumer messageConsumer;//消息消费者
		
		
		//实例化工厂
		connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
		
		try {
			connection = connectionFactory.createConnection();//工厂连接
			connection.start();
			session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createQueue("FirestQueue1");//创建连接的消息队列
			messageConsumer=session.createConsumer(destination); // 创建消息消费者
			while(true){
				TextMessage testMessage = (TextMessage) messageConsumer.receive(100000);
				if(testMessage != null){
					System.out.println("收到消息："+testMessage.getText());
				}else{
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null){
				try {
					connection.stop();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
