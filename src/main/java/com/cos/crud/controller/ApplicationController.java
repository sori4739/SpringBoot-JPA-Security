package com.cos.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.MyUser;
import com.cos.crud.repository.MyUserRepository;
import com.cos.crud.security.MyUserDetails;

@Controller //IOC 제어의역전
public class ApplicationController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserRepository mRepo;
	
	@GetMapping("/home")
	public @ResponseBody String home() {
		return "<h1>home</h1>";
	}
	
	@GetMapping("/admin/test")
	public @ResponseBody String admitTest(@AuthenticationPrincipal MyUserDetails userDetails) {
		StringBuffer sb = new StringBuffer();
		sb.append("id : "+userDetails.getUser().getId()+"<br/>");
		sb.append("username : "+userDetails.getUsername()+"<br/>");
		sb.append("password : "+userDetails.getPassword()+"<br/>");
		sb.append("email : "+userDetails.getUser().getEmail()+"<br/>");
	
		
		return sb.toString();
	}
	
	public @ResponseBody String boardTest() {
		return "<h1>board</h1>";
	}
		
	
	
	
	@GetMapping("/user/joinForm")
	public String join() {
		return "joinForm";
	}
	
	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	// 403 에러 해결방안
	// 첫번째 : csrf 설정
	// 두번째 : password 인코더
	@PostMapping("/user/create")
	public String create(MyUser user) {//jackson bind 라이브러리 작동
		
		// 패스워드 암호화는 필수 (시큐리티를 적용한다면)
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		mRepo.save(user);
		return "redirect:/home";
	}
}
