package com.lfhao.xbox.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

/**
 * 单例，保存所有客服端链接session
 * @author Daryl
 *
 */
public class SessionMap {
	
	private final static Log log = LogFactory.getLog(SessionMap.class); 
	
	private static SessionMap sessionMap = null;
	
	private Map<String, IoSession>map = new HashMap<String, IoSession>(); 
	
	//私有化单例
	private SessionMap(){}
	
	/**
	 * 获取唯一实例
	 * @return
	 */
	public static SessionMap newInstance(){  
        log.debug("SessionMap单例获取");  
        if(sessionMap == null){  
            sessionMap = new SessionMap();  
        }  
        return sessionMap;  
    }  
	
	/**
	 * 保存链接session会话
	 * @param deviceId
	 * @param session
	 */
	public void addSession (String deviceId, IoSession session){
		log.debug("保存会话到SessionMap单例---key=" + deviceId);
		this.map.put(deviceId, session);
	}
	
	/**
	 * 通过key查找缓存session
	 * @param key
	 * @return
	 */
    public IoSession getSession(String deviceId){  
        log.debug("获取会话从SessionMap单例---key=" + deviceId);  
        return this.map.get(deviceId);  
    }
	
	/**
	 * 根据session发送消息到客户端
	 * @param keys
	 * @param message
	 */
    public void sendMessage(String[] deviceIds, Object message){  
        for(String deviceId : deviceIds){  
            IoSession session = getSession(deviceId);  
            log.debug("反向发送消息到客户端Session---key=" + deviceId + "----------消息=" + message);  
            if(session == null){  
                return;  
            }  
            session.write(message);
        }  
    } 
    /**
     * 单个发
     * @param key
     * @param message
     */
    public void sendSingleMessage(String deviceId, Object message){
            IoSession session = getSession(deviceId);
            log.debug("反向发送消息到客户端Session---key=" + deviceId + "----------消息=" + message);
            if(session == null){
                return;
            }
            session.write(message);
    }
    
}
