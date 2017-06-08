package kr.co.jaemin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping("index.htm")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="chat.htm", method=RequestMethod.GET)
	public String chat(){
		return "chat/chat";
	}
}
