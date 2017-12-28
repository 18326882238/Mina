package com.lfhao.xbox.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lfhao.xbox.entity.SessionMap;

@Controller
public class SentController {
	
	private final static Log log = LogFactory.getLog(SentController.class);//logging
	
	@RequestMapping("/sentMessage.do")
	@ResponseBody
	public String sentMessage(HttpServletRequest req, String key){
		try {
			SessionMap sessionMap = SessionMap.newInstance();
			sessionMap.sendSingleMessage(key, "服务器主动发信息成功！");
			log.debug("deviceId: " + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
}
