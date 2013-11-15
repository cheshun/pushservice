package org.cheshun.pushservice.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;

public class PushServer {
	public static void main(String[] args) {
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
            	ChannelPipeline pipeline = Channels.pipeline();
//            	pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(80, Delimiters.lineDelimiter()));
            	pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
            	pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
            	pipeline.addLast("handler", new PushServerHandler());
            	return pipeline;
            }
		});

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
 
        bootstrap.bind(new InetSocketAddress(8888));
	}
}
