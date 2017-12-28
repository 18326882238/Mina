package com.lfhao.xbox.factory;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import com.lfhao.xbox.entity.HDecoder;
import com.lfhao.xbox.entity.HEncoder;

/**
 * 编解码工厂
 * @author Daryl
 *
 */
public class HCoderFactory implements ProtocolCodecFactory {
	
	private final HEncoder encoder;
	private final HDecoder decoder;
	
	public HCoderFactory() {
		//this(Charset.defaultCharset());
		this(Charset.forName("UTF-8"));
	}
	
	public HCoderFactory(Charset charSet) {
		// TODO Auto-generated constructor stub
		this.encoder = new HEncoder(charSet);
		this.decoder = new HDecoder(charSet);
	}	
	
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}
	
}
