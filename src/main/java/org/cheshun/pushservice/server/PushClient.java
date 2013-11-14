package org.cheshun.pushservice.server;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import com.google.gson.Gson;

public class PushClient {
	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = Integer.parseInt("8888");
		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(80, Delimiters.lineDelimiter()));
            	pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
            	pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
            	pipeline.addLast("handler", new PushClientHandler());
				return pipeline;
			}
		});

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);

		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
		Channel channel = future.awaitUninterruptibly().getChannel();
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUsername("2010081501");
		loginInfo.setPassword("123456");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put(KeyType.TYPE, MsgType.LOGIN);
		message.put(KeyType.LOGIN_INFO, loginInfo);
		ChannelFuture future_w = channel.write(Json.toJson(message, JsonFormat.compact()));
		if(future_w.isSuccess()) {
			System.out.println("Login success");
		}
	}
}
