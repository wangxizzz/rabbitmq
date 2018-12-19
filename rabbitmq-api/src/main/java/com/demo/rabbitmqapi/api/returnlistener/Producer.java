package com.demo.rabbitmqapi.api.returnlistener;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Producer {

	
	public static void main(String[] args) throws Exception {
		
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.11.76");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		String exchange = "test_return_exchange";
		String routingKey = "return.save";
		String routingKeyError = "abc.save";
		
		String msg = "Hello RabbitMQ Return Message";
		
		// 把不可达的消息返回producer端。比如找不到对应的队列。(routingKey设置错误)
		channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int replyCode, String replyText, String exchange,
					String routingKey, BasicProperties properties, byte[] body) throws IOException {
				
				System.err.println("---------handle  return----------");
				System.err.println("replyCode: " + replyCode); // 响应码
				System.err.println("replyText: " + replyText); // 响应文本
				System.err.println("exchange: " + exchange);
				System.err.println("routingKey: " + routingKey);
				System.err.println("properties: " + properties);
				System.err.println("body: " + new String(body));
			}
		});
		
		// 发送一个错误的消息 参数mandatory设置为true表示消息不可达也不会被删除，以便后续处理。反之就会被删除。
		channel.basicPublish(exchange, routingKeyError, true, null, msg.getBytes());
		
		//channel.basicPublish(exchange, routingKeyError, true, null, msg.getBytes());
		
		
		
		
	}
}
