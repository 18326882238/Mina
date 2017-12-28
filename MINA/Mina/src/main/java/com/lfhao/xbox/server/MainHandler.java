package com.lfhao.xbox.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.lfhao.xbox.entity.SessionMap;

public class MainHandler extends IoHandlerAdapter{
	
	private final static Log log = LogFactory.getLog(MainHandler.class);//logging
	private static String final_deviceId;
	
	private int count = 0;
	// 当一个新客户端连接后触发此方法.
	/*
	 * 这个方法当一个 Session 对象被创建的时候被调用。对于 TCP 连接来说,连接被接受的时候 调用,但要注意此时 TCP
	 * 连接并未建立,此方法仅代表字面含义,也就是连接的对象 IoSession 被创建完毕的时候,回调这个方法。 对于 UDP
	 * 来说,当有数据包收到的时候回调这个方法,因为 UDP 是无连接的。
	 */
	public void sessionCreated(IoSession session) {
		 log.info("新客户端链接");
	}
	
	// 当一个客端端连结进入时 @Override
	/*
	 * 这个方法在连接被打开时调用,它总是在 sessionCreated()方法之后被调用。对于 TCP 来
	 * 说,它是在连接被建立之后调用,你可以在这里执行一些认证操作、发送数据等。 对于 UDP 来说,这个方法与
	 * sessionCreated()没什么区别,但是紧跟其后执行。如果你每 隔一段时间,发送一些数据,那么
	 * sessionCreated()方法只会在第一次调用,但是 sessionOpened()方法每次都会调用。
	 */
	
	public void sessionOpened(IoSession session) throws Exception {
		 count++;
		 log.info("第 " + count + " 个 client 登陆！address： : " + session.getRemoteAddress());
	}
	
	// 当客户端发送的消息到达时:
	 /*
	 * 对于 TCP 来说,连接被关闭时,调用这个方法。 对于 UDP 来说,IoSession 的 close()方法被调用时才会毁掉这个方法。
	 */
	@Override
	public void messageReceived(IoSession session, Object message)throws Exception {
		
//		if( message.toString().trim().equalsIgnoreCase("exit") ) {
//			session.close();//结束会话 
//			return;
//		}
		log.debug("服务端收到信息-------------");
		 //获取客户端发过来的key
         String key = message.toString();
         System.out.println("message :"+message.toString());
         String deviceId = key.substring(key.indexOf("=") + 1);
         System.out.println("deviceId :"+deviceId);
         final_deviceId = deviceId;
         //保存客户端的会话session
         SessionMap sessionMap = SessionMap.newInstance();
         sessionMap.addSession(deviceId, session);
		 
		
//		接收到客户端消息返回修改信息
//		PlayerAccount_Entity po = (PlayerAccount_Entity) message;
//		po.setName("Daryl--LFH");
//		System.out.println("Client Say:" + po.getName());

//		log.debug("服务端收到信息:" + po.getName()); 
//		
//		po.setName("Daryl--LFH");
//		session.write(po);
	}
	
	// 当信息已经传送给客户端后触发此方法.
	/*
	 * 当发送消息成功时调用这个方法,注意这里的措辞,发送成功之后,也就是说发送消息是不 能用这个方法的。
	 */
	@Override
	public void messageSent(IoSession session, Object message) {
		log.debug("---服务端发消息到客户端---");
	}
	
	// 当一个客户端关闭时

	/*
	* 对于 TCP 来说,连接被关闭时,调用这个方法。 对于 UDP 来说,IoSession 的 close()方法被调用时才会毁掉这个方法。
	*/
	@Override
	public void sessionClosed(IoSession session) {
		log.debug("远程session关闭了一个..." + session.getRemoteAddress().toString()); 
	}
	
	// 当连接空闲时触发此方法.
	/*
	* 这个方法在 IoSession 的通道进入空闲状态时调用,对于 UDP 协议来说,这个方法始终不会 被调用。
	*/
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
//		log.info("-客户端与服务端连接[空闲] - " + status.toString());
		log.info("-客户端与服务端连接[空闲] - deviceId:" + final_deviceId + session.getServiceAddress() +"IDS"); 
	}
	
	// 当接口中其他方法抛出异常未被捕获时触发此方法
	/*
	 * 这个方法在你的程序、Mina 自身出现异常时回调,一般这里是关闭 IoSession。
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		System.out.println("其他方法抛出异常");
	}
	
	
	
}
