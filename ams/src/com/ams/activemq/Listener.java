package com.ams.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener{

	/**
	 * 消息监听
	 */
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("收到的消息为+"+((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
