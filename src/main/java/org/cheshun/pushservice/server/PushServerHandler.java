package org.cheshun.pushservice.server;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public class PushServerHandler extends SimpleChannelHandler {
	ChannelGroup group = new DefaultChannelGroup();
	Map<String, Integer> users = new HashMap<String, Integer>();
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
		Channel ch = e.getChannel();
		String msg = (String) e.getMessage();
		Map<String, Object> message = Json.fromJsonAsMap(Object.class, msg);
		int key = (Integer) message.get(KeyType.TYPE);
		System.out.println(key);
		switch (key) {
		case MsgType.LOGIN:
			ChannelFuture future = ch.write("success");
			if (future.isSuccess()) {
				group.add(ch);
				LoginInfo loginInfo = Json.fromJson(LoginInfo.class, 
						Json.toJson(message.get(KeyType.LOGIN_INFO), JsonFormat.compact()));
				users.put(loginInfo.getUsername(), ch.getId());
			}
			break;
		case MsgType.ORDINARY:
			break;
		case MsgType.MANAGER:
			break;
		default:
			break;
		}
		
		ch.write(Json.toJson(message, JsonFormat.compact()));
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		Channel ch = e.getChannel();
		System.out.println(ch.getAttachment());
		ch.close();
	}
}
