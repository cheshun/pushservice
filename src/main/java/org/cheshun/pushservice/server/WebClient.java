package org.cheshun.pushservice.server;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public class WebClient {
	private String host;
	private int port;
	private Channel channel;
	public WebClient(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	public void init() {
		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);
		bootstrap.setPipelineFactory(new WebChannelPipelineFactory());

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);

		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
		channel = future.awaitUninterruptibly().getChannel();
	}
	public boolean send(String message) {
		ChannelFuture future = channel.write(message);
		return future.isSuccess();
	}
	
	public static void main(String[] args) {
		
		new WebClient("localhost", 8888).send("hah");
	}
}
