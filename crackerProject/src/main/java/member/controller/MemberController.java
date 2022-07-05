package member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value = "/member",produces="application/json;charset=utf-8" )

public class MemberController {
	@Autowired
	private MemberService memberService;
	@GetMapping("/memberLoginForm")
	public ModelAndView memberLoginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberLoginForm");
		return mav;
	}
	
	   @PostMapping("/memberLoginCheck")
	   @ResponseBody
	   public MemberDTO memberLoginCheck(@RequestParam Map<String,String>map, HttpSession session) {
	      MemberDTO memberDTO = memberService.memberLoginCheck(map);
	      if(memberDTO != null) {
	      session.setAttribute("memId", memberDTO.getMemberid());
	      session.setAttribute("memName", memberDTO.getMembername());
	      String email = memberDTO.getMemberemail1()+"@"+memberDTO.getMemberemail2();
	      session.setAttribute("memEmail", email);
	      }
	      return memberDTO;
	   }

	
	@GetMapping(value = "memberWriteForm")
	public ModelAndView memberWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberWriteForm");
		return mav;
	}
	
	@PostMapping("/memberWrite")
	@ResponseBody
	public String memberWrite(@RequestParam Map<String,String>map) {
		MemberDTO memberDTO = memberService.memberWrite(map);
		System.out.println("memberDTO" + memberDTO);
		return memberDTO.getMembername();
	}
	@PostMapping("/memberIdCheck")
	@ResponseBody
	public String memberIdCheck(@RequestParam String memberid) {
		String check = memberService.memberIdCheck(memberid);
		return check;
	}
	@GetMapping("/emailcheck")
	@ResponseBody
	public String emailcheck(@RequestParam String email) {
		return memberService.emailcheck(email);
	}
	@GetMapping("/memberFindIdForm")
	public ModelAndView memberFindIdForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberFindIdForm");
		return mav;
	}
	
	@GetMapping("/phoneCheck")
	@ResponseBody
	public String phoneCheck(@RequestParam String findtel) {
		return memberService.phoneCheck(findtel);
	}
	
	@PostMapping("/memberfindIdcheck")
	@ResponseBody
	public String memberfindIdcheck(@RequestParam Map<String,String>map) {
		 MemberDTO memberDTO  = memberService.memberfindIdcheck(map);
		 return memberDTO.getMemberid();
	}
	@GetMapping("/memberFindId")
	public String memberFindId() {
		
		return "/member/memberFindId";
	}
}

