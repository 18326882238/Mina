package com.lfhao.xbox.entity;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 创建一个编码器
 * @author Admin
 *
 */
public class HEncoder extends ProtocolEncoderAdapter {
	
	private final static Log log = LogFactory.getLog(HEncoder.class);  
    
    private final Charset charset;  
  
    public HEncoder(Charset charset) {  
        this.charset = charset;  
    }  
    
    public void encode(IoSession session, Object message,  
            ProtocolEncoderOutput out) throws Exception {  
          
        CharsetEncoder ce = charset.newEncoder();  
        String mes = (String) message;  
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);  
        buffer.putString(mes,ce);  
        buffer.flip();  
        out.write(buffer);  
          
        /*System.out.println("---------encode-------------"); 
        String mes = (String) message; 
        byte[] data = mes.getBytes("UTF-8"); 
        IoBuffer buffer = IoBuffer.allocate(data.length + 4); 
        buffer.putInt(data.length); 
        buffer.put(data); 
        buffer.flip(); 
        out.write(buffer); 
        out.flush();*/  
    }  
  
    @Override  
    public void dispose(IoSession session) throws Exception {  
        log.info("Dispose called,session is " + session);  
    }  
	
	//编码工具encode
//	public void encode(IoSession arg0, Object arg1, ProtocolEncoderOutput arg2) throws Exception {
//		// TODO Auto-generated method stub
//		CharsetEncoder ce = charset.newEncoder();
//		PlayerAccount_Entity paEntity = (PlayerAccount_Entity) arg1;
//		String name = paEntity.getName();
//		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
//		buffer.putString(name, ce);
//		buffer.flip();
//		arg2.write(buffer);
//	}
		
}
