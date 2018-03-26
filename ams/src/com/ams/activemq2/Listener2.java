package com.ams.activemq2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * 消息监听：订阅者一
 * @author TeamLu
 *
 */
public class Listener2 implements MessageListener{

	/**
	 * 消息监听
	 */
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("订阅者二收到的消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
