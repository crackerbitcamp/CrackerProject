package member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.maven.model.Model;
import org.apache.velocity.exception.ParseErrorException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value = "/member",produces="application/json;charset=utf-8" )

public class MemberController{
	@Autowired
	private MemberService memberService;
	//로그인
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
	      String email = memberDTO.getMemberemail1()+"@"+memberDTO.getMemberemail2();
	      session.setAttribute("memId", memberDTO.getMemberid());
	      session.setAttribute("memName", memberDTO.getMembername());
	      session.setAttribute("memEmail", email);
	      session.setAttribute("memNickname", memberDTO.getMembernickname());
      }
      return memberDTO;
   }
   
   @GetMapping("/memberLogout")
   public ModelAndView memberlogout(HttpSession session) {
	   ModelAndView mav = new ModelAndView();
	   session.invalidate();
	   mav.addObject("indexSection1","/WEB-INF/main/indexSection1.jsp");
		mav.addObject("indexSection2","/WEB-INF/main/indexSection2.jsp");
		mav.addObject("indexSection3","/WEB-INF/main/indexSection3.jsp");
		mav.addObject("indexSection4","/WEB-INF/main/indexSection4.jsp");
		mav.addObject("menu","/WEB-INF/main/menu.jsp");
		mav.addObject("nav","/WEB-INF/main/nav.jsp");
		mav.setViewName("index");
	   return mav;
   }
   
   //회원정보 수정
   @GetMapping("/memberUpdatePasswordCheckForm")
   public ModelAndView memberUpdatePasswordCheckForm(HttpSession session) {
	   String memberid = (String)session.getAttribute("memId");
	   MemberDTO memberDTO = memberService.getMember(memberid);
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("memberDTO",memberDTO);
	   mav.addObject("menu","/WEB-INF/main/menu.jsp");
	   mav.addObject("nav","/WEB-INF/main/nav.jsp");
	   mav.addObject("display","/WEB-INF/member/memberUpdatePasswordCheckForm.jsp");
	   mav.setViewName("/index");
	   return mav;
   }
   
   @PostMapping("/memberUpdatePasswordCheck")
   @ResponseBody
   public MemberDTO memberUpdatePasswordCheck(@RequestParam Map<String,String>map, HttpSession session) {
	   String memberid = (String)session.getAttribute("memId");
	   map.put("memberid", memberid);
	   MemberDTO memberDTO = memberService.memberLoginCheck(map);
	   return memberDTO;
   }
   
   @GetMapping("/memberUpdateForm")
   public ModelAndView memberUpdateForm(HttpSession session) {
	   String memberid = (String)session.getAttribute("memId");
	   MemberDTO memberDTO = memberService.getMember(memberid);
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("memberDTO",memberDTO);
	   mav.addObject("menu","/WEB-INF/main/menu.jsp");
	   mav.addObject("nav","/WEB-INF/main/nav.jsp");
	   mav.addObject("display","/WEB-INF/member/memberUpdateForm.jsp");
	   mav.setViewName("/index");
	   return mav;
   }
   
   //회원가입
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
		return memberDTO.getMembernickname();
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
		System.out.println("email 확인 : " + email);
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
	public String phoneCheck(@RequestParam String findphone) {
		return memberService.phoneCheck(findphone);
	}
	
	@PostMapping("/memberfindIdcheck")
	@ResponseBody
	public String memberfindIdcheck(@RequestParam Map<String,String>map) {
		 MemberDTO memberDTO = memberService.memberfindIdcheck(map);
		 System.out.println(memberDTO);
		 String check;
		 if(memberDTO == null) {
			 check = "fail";
		 }else {
			 check = memberDTO.getMemberid();
		 }
		 return check;
	}
	@GetMapping("/memberFindId")
	public String memberFindId() {
		
		return "/member/memberFindId";
	}
	
	@GetMapping("/emailUpdateForm")
	public ModelAndView emailUpdateForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/emailUpdateForm");
		return mav;
	}
	
	//update Email
	@GetMapping("/updateEmailCheck")
	@ResponseBody
	public String updateEmailCheck(@RequestParam String email) {
		return memberService.updateEmailCheck(email);
	}
	@GetMapping("/telUpdateForm")
	public ModelAndView telUpdateForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/telUpdateForm");
		return mav;
	}
	@PostMapping("/memberUpdate")
	@ResponseBody
	public void memberUpdate(@RequestParam Map<String,String>map, HttpSession session) {
		memberService.memberUpdate(map);
		session.invalidate();
	}
	@PostMapping("/memberFindPwd")
	@ResponseBody
	public String memberFindPwd(@RequestParam Map<String,String>map) {
		return memberService.memberFindPwd(map);
	}
	
	@GetMapping("/naverlogincallback")
	public String naverlogincallback() {
		return "/member/naverlogincallback";
	}
	@PostMapping("/naverdatacall")
	@ResponseBody
	public void naverdatacall(@RequestParam Map<String,String>map, HttpSession session) {
		System.out.println("map!"+map);
		session.setAttribute("naverName", map.get("naverName"));
		session.setAttribute("naverTel", map.get("naverTel"));
		session.setAttribute("naverEmail", map.get("naverEmail"));
		session.setAttribute("naverNickName", map.get("naverNickName"));
		
	}
}

