package com.lfhao.xbox.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 
 * @author Daryl
 *
 */
public class ClientHandlerEX extends IoHandlerAdapter {
	
	 private final static Log log = LogFactory.getLog(ClientHandlerEX.class);
	
	 /** 
	     * 写处理服务端推送的信息的逻辑 
	     */  
	    @Override  
	    public void messageReceived(IoSession session, Object message)  
	            throws Exception {
	        System.out.println("-----服务器返回的json数据-----");  
	        String s = message.toString();
	        System.out.println("message :" + s);
	        System.out.println("message length:" + s.length());
	        
	    }
	    
	    @Override    
	    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {    
	        log.info("-客户端与服务端连接[空闲] - " + status.toString());    
	        System.out.println("-客户端与服务端连接[空闲] - " + status.toString());  
	          
	        if(session != null){    
	            session.close(true);    
	        }    
	    } 
	
	
}
