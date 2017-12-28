package com.lfhao.xbox.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.lfhao.xbox.factory.HCoderFactory;

public class MainClient {
	
	public static void main(String[] args) {
		//创建一个tcp/ip链接
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter()); 
		
		/*---------接收字符串---------*/
		// //创建接收数据的过滤器
		// DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		// // 设定这个过滤器将一行一行(/r/n)的读取数据
		// chain.addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory()));
		
		//创建接收对象数据过滤器
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		//设定这个过滤器将以对象为单位读取数据(自定义编码器在过滤器中对数据进行处理)
		//ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
		ProtocolCodecFilter filter = new ProtocolCodecFilter(new HCoderFactory( Charset.forName("UTF-8")));
		
		// 设定服务器端的消息处理器:一个SamplMinaServerHandler对象,
		chain.addLast("objectFilter", filter);
		connector.setHandler(new ClientMinaServerHanlder());
		
		//设置链接超时
		connector.setConnectTimeoutCheckInterval(30);
		
		//链接到服务器
		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9001));
		
		//设置等待链接尝试时间
		cf.awaitUninterruptibly();
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	
	}
}
