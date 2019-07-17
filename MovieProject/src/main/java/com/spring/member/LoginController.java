package com.spring.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
* @Class Name : LoginController.java
* @Description : login관련 컨트롤러 
* @Modification Information
* @
* @  	수정일               	 수정자                  	수정내용
* @ -----------   ---------   -------------------------------
* @ 2019. 07. 15         황진석            		최초생성
* @ 2019. 07. 16 	황진석				로그인/로그아웃, 이메일 찾기 컨트롤러 추가
* @ 2019. 07. 17 	황진석				
* @author bit 2조
* @since 2019. 07.01
* @version 1.0
* @see
*
*  Copyright (C) by Bit All right reserved.
*/

@Controller
public class LoginController {
   
   @Autowired
   private MemberService memberService;
   
   /**
    * 로그인
    * @param vo - 로그인시 입력한 정보가 담긴 MemberVO
    * @param request
    * @param response
    * @param model
    * @return "index"
    * @throws Exception 
    */
   @RequestMapping(value="/Login.do")
   public String MemberLogin(MemberVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
      String email= vo.getM_email();
      String pw = vo.getM_password();
      HttpSession session = request.getSession();
      
      int check = memberService.userCheck(email, pw);
      if(check == 1){
         session.setAttribute("email", email);
         return "index";
      }else if( check == -1) {
         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('비밀번호가 다릅니다. 확인해주세요!');");
         out.println("history.go(-1);");
         out.println("</script>");
         out.close();
         return "index";
      }else {
         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('아이디 혹은 비밀번호가 다릅니다. 확인해주세요!');");
         out.println("history.go(-1);");
         out.println("</script>");
         out.close();
         return "index";
      }
   }
   
   /**
    * 로그아웃
    * @param request
    * @param response
    * @param model
    * @return "index"
    */
   @RequestMapping(value="/Logout.do")
   public String MemberLogOut(HttpServletRequest request, HttpServletResponse response, Model model)  {
      HttpSession session = request.getSession();
      session.invalidate();
      return "index";
   }
   
   /**
    * 이메일 찾기
    * @param vo - 아이디 찾기 시 입력한 정보가 담긴 MemberVO
    * @param request
    * @param response
    * @param model
    * @return String
    */
   @RequestMapping(value="/id_find.do", method=RequestMethod.GET, produces="application/json")
   public @ResponseBody String id_find(MemberVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {  
	   String phone = request.getParameter("m_phone1") + request.getParameter("m_phone2") + request.getParameter("m_phone3");
	   vo.setM_phone(phone);
	   String email = memberService.findEmail(vo);
	   if(email == "fail")
		  return "fail";
	   else
		  return email;
   }
   
   /**
    * 비밀번호 찾기
    * @param vo - 비밀번호 찾기 시 입력한 정보가 담긴 MemberVO
    * @param request
    * @param response
    * @param model
    * @return String
    */
   @RequestMapping(value="/pw_find.do", method=RequestMethod.GET, produces="application/json")
   public @ResponseBody String pw_find(MemberVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {  
	   String phone = request.getParameter("m_phone1") + request.getParameter("m_phone2") + request.getParameter("m_phone3");
	   vo.setM_phone(phone);
	   MemberVO memberVO = memberService.findPw(vo);
	   
	   if(memberVO != null) {
		   return "success";
	   }else {
		   return "fail";
	   }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}