package com.lfhao.xbox.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.lfhao.xbox.client.ClientMinaServerHanlder;
import com.lfhao.xbox.factory.HCoderFactory;

public class MinaServer {
	
	public static void main(String[] args) {
		
		//创建一个非阻塞的server端Socket ，用NIO
		//可设置IoProcessor线程池中线程个数，如：new NioSocketAcceptor(5)，即可同时处理5个读写操作
		//默认IoProcessor池中线程个数为CPU的核数+1
		SocketAcceptor acceptor = new NioSocketAcceptor();
		
		LoggingFilter lf = new LoggingFilter();
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,5);
		lf.setSessionOpenedLogLevel(LogLevel.DEBUG);
		acceptor.getFilterChain().addLast("logger", lf);
		
		/*
		 * 接收字符串
		 */
		//创建一个接收过滤器
//		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
//		chain.addLast("mychin", new ProtocolCodecFilter(new TextLineCodecFactory()   ));
		
		//创建接收数据的过滤器
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		
		//设定这个过滤器将以对象为单位读取数据
		//ProtocolCodecFilter filter= new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
		ProtocolCodecFilter filter = new ProtocolCodecFilter(new HCoderFactory( Charset.forName("UTF-8")));
		chain.addLast("objectFilter",filter);
		
		//设定服务器消息处理器
		acceptor.setHandler(new MainHandler());
		
		//服务器绑定的端口
		final int bindPort = 9001;
		
		//绑定端口，启动服务器
		try {
			acceptor.bind(new InetSocketAddress(bindPort));			
		} catch (Exception e) {
			System.out.println("Mina Server start for error!"+bindPort);
			e.printStackTrace();
		}
		System.out.println("Mina Server run done! on port:"+bindPort);
	}	
}
