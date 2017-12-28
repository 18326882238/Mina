package com.lfhao.xbox.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.lfhao.xbox.entity.MessageObject;
import com.lfhao.xbox.entity.PlayerAccount_Entity;

public class ClientMinaServerHanlder extends IoHandlerAdapter {
	
	private int count = 0;
	
//	@Override
//	public void sessionCreated(IoSession session) {
//		 System.out.println("新客户端连接");
//	}
	
	//当一个客端端连结到服务端
	@Override
	public void sessionOpened(IoSession session)throws Exception{
		count++;
		 System.out.println("第 " + count + " 个 client 登陆！address： : " + session.getRemoteAddress());
//		 发送消息对象
//		 PlayerAccount_Entity ho = new PlayerAccount_Entity();
//		 ho.setId(1);
//		 ho.setName("Daryl");
//		 ho.setEmailAdress("利富豪");
//		 System.out.println(ho.getName() + ho.getEmailAdress());
		 String str = "key="+10011;
		 session.write(str); 
	}
	
	// 当服务端发送的消息到达时:
	@Override
	public void messageReceived(IoSession session, Object message)throws Exception{
		// 测试将消息回送给客户端 session.write(s+count); count++;
		
//		PlayerAccount_Entity mo = (PlayerAccount_Entity) message;
//		System.out.println("Server Say:name:" + mo.getName());
		String s = message.toString();
        System.out.println("message :" + s);
        System.out.println("message length:" + s.length());
	}
	
	// 当信息已经传送给客户端后触发此方法.
//	@Override
//	public void messageSent(IoSession session, Object message){
//		System.out.println("信息已经传送给客户端");
//	}
	
	// 当一个客户端关闭时
//	@Override
//	public void sessionClosed(IoSession session) {
//		 session.close(false);//等socket发送完毕再关闭socket连接  
//		 System.out.println("one Clinet Disconnect !");
//	}
	
	// 当连接空闲时触发此方法.
//	@Override
//	public void sessionIdle(IoSession session, IdleStatus status){
//		System.out.println("连接空闲");
//	}
	
	// 当接口中其他方法抛出异常未被捕获时触发此方法
//	@Override
//	public void exceptionCaught(IoSession session, Throwable cause){
//		System.out.println("其他方法抛出异常");
//	}	
	
}
