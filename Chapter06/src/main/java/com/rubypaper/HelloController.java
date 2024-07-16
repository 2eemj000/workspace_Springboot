package com.rubypaper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

//	@GetMapping("/hello")
//	public void hello() {
//		//void면 원래 url로 호출한 /hello가 호출됨
//		//" /WEB-INF/board/hello.jsp "
//	}
	
//	//localhost:8080/hello?name=홍길동
//	@GetMapping("/hello")
//	public String hello(String name, Model model) {
//		//void면 원래 url로 호출한 /hello가 호출됨
//		//" /WEB-INF/board/hello.jsp "
//		model.addAttribute("name", name);
//		return "hello";
//	}
	
	//localhost:8080/hello?name=홍길동
	@GetMapping("/hello")
	public ModelAndView hello(String name) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name",name);
		mv.setViewName("hello");
		return mv;
	}
}
