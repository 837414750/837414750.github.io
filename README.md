# 837414750.github.io

ams项目
ActiveMq实现(点对点[queue]，发布订阅[topic]) 

点对点消息实现；

1、直接Receive方式
Session.AUTO_ACKNOWLEDGE。当客户成功的从receive 方法返回的时候，或者从MessageListener.onMessage
方法成功返回的时候，会话自动确认客户收到的消息。
Session.CLIENT_ACKNOWLEDGE。客户通过消息的acknowledge 方法确认消息。需要注意的是，在这种模
式中，确认是在会话层上进行：确认一个被消费的消息将自动确认所有已被会话消费的消息。例如，如果一
个消息消费者消费了10 个消息，然后确认第5 个消息，那么所有10 个消息都被确认。
Session.DUPS_ACKNOWLEDGE。该选择只是会话迟钝第确认消息的提交。如果JMS provider 失败，那么可
能会导致一些重复的消息。如果是重复的消息，那么JMS provider 必须把消息头的JMSRedelivered 字段设置
为true。

2、使用监听的方式实现会话
****
详见代码

JMSProducer.java 生产者类，在类中有详细的说明；
activemq包下的，JMSConsumer.java 使用的是receive(),方法接收消息。

activemq包下的，JMSConsumer2.java 使用是的监听的方式获取消息,监听器是Listener类，该类实现了MessageListener接口,并重写了，onMessage方法.
