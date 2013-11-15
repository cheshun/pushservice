package org.cheshun.pushservice.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class DiscardServerHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//		ChannelBuffer buf = (ChannelBuffer) e.getMessage();
//		while(buf.readable()) {
//			System.out.println((char) buf.readByte());
//			System.out.flush();
//		}
		Channel ch = e.getChannel();
		ch.setAttachment("hehe");
		ch.write(e.getMessage());
	}

	
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("Channel connected " + e);  
        Channel ch = e.getChannel();  
        ChannelBuffer cb = ChannelBuffers.wrappedBuffer("success".getBytes()) ;  
        ch.write(cb); 
	}



	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		System.out.println("");
		Channel ch = e.getChannel();
		System.out.println(ch.getAttachment());
		ch.close();
	}
}
