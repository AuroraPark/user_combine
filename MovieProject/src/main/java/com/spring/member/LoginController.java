package com.spring.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
* @Class Name : LoginController.java
* @Description : login���� ��Ʈ�ѷ� 
* @Modification Information
* @
* @  	������               	 ������                  	��������
* @ -----------   ---------   -------------------------------
* @ 2019. 07. 15         Ȳ����            		���ʻ���
* @ 2019. 07. 16 	Ȳ����				�α���/�α׾ƿ�, �̸��� ã�� ��Ʈ�ѷ� �߰�
* @ 2019. 07. 17 	Ȳ����				
* @ 2019. 07. 22	�̿���			ȸ������ + ���Խ� ����&�г��� �ߺ�Ȯ�� ����
* @ 2019. 07. 26	�̿���			login ������ id �ڵ带 ���ǰ��� �߰��ϵ��� ����
* @author bit 2��
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
   
   @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
   
   /**
    * �α���
    * @param vo - �α��ν� �Է��� ������ ��� MemberVO
    * @param request
    * @param response
    * @param model
    * @return "index"
    * @throws Exception 
    */
   @RequestMapping(value="/Login")
   public String MemberLogin(MemberVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
      String email= vo.getM_email();
      String pw = vo.getM_password();
      HttpSession session = request.getSession();
      
      int check = memberService.userCheck(email, pw);
      if(check == 1){
         session.setAttribute("m_email", email);
         session.setAttribute("id", memberService.getId(email, pw));
         return "index";
      }else if( check == -1) {
         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('��й�ȣ�� �ٸ��ϴ�. Ȯ�����ּ���!');");
         out.println("history.go(-1);");
         out.println("</script>");
         out.close();
         return "index";
      }else {
         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('���̵� Ȥ�� ��й�ȣ�� �ٸ��ϴ�. Ȯ�����ּ���!');");
         out.println("history.go(-1);");
         out.println("</script>");
         out.close();
         return "index";
      }
   }
   
   /**
    * �α׾ƿ�
    * @param request
    * @param response
    * @param model
    * @return "index"
    */
   @RequestMapping(value="/Logout")
   public String MemberLogOut(HttpServletRequest request, HttpServletResponse response, Model model)  {
      HttpSession session = request.getSession();
      session.invalidate();
      return "index";
   }
   
   @RequestMapping(value="/index")
   public String index(HttpServletRequest request, HttpServletResponse response, Model model)  {
      return "index";
   }
   
   /**
    * �̸��� ã��
    * @param vo - ���̵� ã�� �� �Է��� ������ ��� MemberVO
    * @param request
    * @param response
    * @param model
    * @return String
    */
   @RequestMapping(value="/id_find", method=RequestMethod.GET, produces="application/json")
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
    * ��й�ȣ ã��
    * @param vo - ��й�ȣ ã�� �� �Է��� ������ ��� MemberVO
    * @param request
    * @param response
    * @param model
    * @return String
    */
   @RequestMapping(value="/pw_find", method=RequestMethod.GET)
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
   /**
    * ȸ������
    * @param vo - �α��ν� �Է��� ������ ��� MemberVO
    * @param request
    * @param response
    * @param model
    * @return "index"
    * @throws Exception 
    */
   @PostMapping("/memberJoin") 
   String memberJoin(MemberVO vo, HttpServletRequest request, HttpServletResponse response) {
	   String phone = request.getParameter("m_phone1") + request.getParameter("m_phone2") + request.getParameter("m_phone3");
	   vo.setM_phone(phone);
	   memberService.memberJoin(vo);
	   	   
	   return "index";
   }
   /**
    * �α���
    * @param vo - �α��ν� �Է��� ������ ��� MemberVO
    * @param request
    * @param response
    * @param model
    * @return "application/text"
    * @throws Exception 
    */
   @RequestMapping(value="/email_overlap_chk", method=RequestMethod.GET, produces="application/json")
   public @ResponseBody String emailOverlapChk (@RequestParam(value="m_email") String m_email, HttpServletRequest request, HttpServletResponse response, Model model) {
	  MemberVO vo = new MemberVO();
	  vo.setM_email(m_email);
	  System.out.println("�Էµ� ���� =" + vo.getM_email());
	  if( memberService.emailOverlapChk(vo)) {
		   return "success";
	   }else {
		   return "fail";
	   }
   }
   /**
    * �α���
    * @param vo - �α��ν� �Է��� ������ ��� MemberVO
    * @param request
    * @param response
    * @param model
    * @return "application/text"
    * @throws Exception 
    */
	  @RequestMapping(value="/nick_overlap_chk", method=RequestMethod.GET, produces="application/json")
	   public @ResponseBody String nickOverlapChk (@RequestParam(value="m_nickname") String m_nickname, HttpServletRequest request, HttpServletResponse response, Model model) {
		  MemberVO vo = new MemberVO();
		  vo.setM_nickname(m_nickname);
		  System.out.println("�Էµ� ���� =" + vo.getM_nickname());
		  if( memberService.nickOverlapChk(vo)) {
			   return "success";
		   }else {
			   return "fail";
		   }
	  }

   @RequestMapping(value="/pw_new", method=RequestMethod.GET)
   public @ResponseBody String pw_new(MemberVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {
	   int count = memberService.updatePw(vo);
	   
	   if(count == 1) {
		   return "success";
	   }else {
		   return "fail";
	   }
}   
}