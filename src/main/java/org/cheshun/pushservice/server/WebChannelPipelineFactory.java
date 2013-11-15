package org.cheshun.pushservice.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;

public class WebChannelPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
//		pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(80, Delimiters.lineDelimiter()));
    	pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
    	pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
    	pipeline.addLast("handler", new WebClientHandler());
		return pipeline;
	}

}
