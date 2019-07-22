package com.spring.mypage;
/**
 * @Class Name : MyPageController.java
 * @Description : MyPage Controller
 * @Modification Information
 * @
 * @  ������     	  ������                 ��������
 * @ ---------   ---------   -------------------------------
 * @ 2019.07.0?     Ȳ����      ���ʻ���
 * @ 2019.07.17     ������      ��������, �� �޼��� �� �ּ��߰�(��ɼ���)
 * @ 2019.07.17     ������      Ŭ������ ����(LoginController -> MyPageController), ��ɱ���
 * @author bit 2��
 * @since 2019. 07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Bit All right reserved.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyPageController {
	
	@Autowired
	private MyPageService myPageService;
	
	
	//���������� ùȭ��
	@RequestMapping(value="/mypage.do", method=RequestMethod.GET)
	public String mypage(Model model, HttpSession session) {
		
		
			session.setAttribute("id", 1);
				
			
			MemberVO member = (MemberVO)myPageService.getMember(1);
			System.out.println(member);
			model.addAttribute("member",member);
			return "mypage/mypage";
	}
	
	//���������� - ��й�ȣ ��Ȯ��
	@RequestMapping(value="/pw_confirm.do", method=RequestMethod.GET)
	public String pwConfirm(int id, Model model) {
		MemberVO member = myPageService.getMember(id);
		
		model.addAttribute("member",member);
		
		return "mypage/pw_confirm";
	}
	
	//���������� - ��й�ȣ ��Ȯ�� - ȸ������ ����
	@RequestMapping(value="/member_info.do")
	public String memberInfo(MemberVO member, Model model, int id) {
		MemberVO member1 = myPageService.getMember(id);
		System.out.println("member1="+member1);
		// Ŭ���̾�Ʈ���� �Է��� ��й�ȣ
		String input_password = member.getM_password();
		System.out.println("input_pwd="+input_password);
		// id�� �˻��� member�� ��й�ȣ
		String member_password = member1.getM_password();
		System.out.println("member_pwd="+member_password);
		
		int check = 3;
		
		if(input_password.equals(member_password)) {		
			model.addAttribute("member",member);
			model.addAttribute("member1",member1);
			model.addAttribute("check",check);
			System.out.println("�ѱ��"+member1);
			return "mypage/member_info";
		}
		else {
			return "mypage/mypage";
		}
	}
	//���������� - ��й�ȣ����
	@RequestMapping(value="/update_pw.do")
	public String updatePw(Model model, MemberVO memberVO, int id) {
	
		MemberVO member1 = myPageService.getMember(id);
		
		myPageService.updatePw(memberVO);
		
		
		 member1 = myPageService.getMember(id);
		 
		model.addAttribute("member1",member1);
		return "mypage/member_info";
	}
	
//	//���������� - ������ �г��� �ߺ� Ȯ��
//	@RequestMapping(value="/update_checknick.do")
//	public String updateCheckNick(Model model, String m_nickname, MemberVO memberVO,int id) {
//		int check = myPageService.checkNick(m_nickname);
//		MemberVO member = myPageService.getMember(id);
//		
//		model.addAttribute("check", check);
//		model.addAttribute("member1",member);
//		System.out.println("�ߺ�üũ " +check);
//		return "mypage/member_info";
//	}
	
	
	//���������� - �г��Ӽ���
	@RequestMapping(value="/update_nick.do")
	public String updateNick(Model model, int id, MemberVO memberVO) {
		MemberVO member1 = myPageService.getMember(id);
		myPageService.updateNick(memberVO);
		
		 member1 = myPageService.getMember(id);
	
		model.addAttribute("member1",member1);
		
		return "mypage/member_info";
	}

	//���������� - ȸ����������
	@RequestMapping(value="/member_update.do")
	public String updateMember(Model model, HttpServletResponse response, MemberVO memberVO, int id) {
		MemberVO member1 = myPageService.getMember(id);
		myPageService.updateMember(memberVO);
		
		 member1 = myPageService.getMember(id);
		 
		 model.addAttribute("member1",member1);
		
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('ȸ�����������Ϸ�');");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "mypage/member_info";
	}
	
	//���������� - ȸ��Ż��
	@RequestMapping(value="/member_out.do", method=RequestMethod.GET)
	public String memberOut() {
		return "mypage/member_out";
	}
	
	//���������� - 1:1 ���ǳ��� ����Ʈ
	@RequestMapping(value="/one_list.do", method=RequestMethod.GET)
	public String oneList(HttpServletRequest request, HttpSession session) {
	
		//���� �޴� ����� ����� ������������ ���� session�� ������ email���� ����
		session.setAttribute("m_email", "bit0hyj@gmail.com");
		
		//����� ����
		String m_email = (String)session.getAttribute("m_email");
		String m_name = myPageService.getMemberName(m_email);	//System.out.println("=============MyPageController.java=====================  m_name : " + m_name);
		request.setAttribute("m_name", m_name);
		
		//1:1 ���ǳ���
		List<OneVO> qnaList = null;
		int id = myPageService.getMemberId(m_email);	//System.out.println("=============MyPageController.java=====================  id : " + id);
		qnaList = myPageService.getQnaList(id);
		request.setAttribute("qnaList", qnaList);
		return "mypage/one_list";
	}
	
	//���������� - 1:1 ���ǳ��� - 1:1 ���ǳ��� �󼼺���
	@RequestMapping(value="/one_get.do", method=RequestMethod.GET)
	public String Loggin() {
		return "mypage/one_get";
	}
	
	//���������� - 1:1 ���ǳ��� ����Ʈ - 1:1���ǳ��� ���
	@RequestMapping(value="/one_register.do", method=RequestMethod.GET)
	public String oneRegister() {
		return "mypage/one_register";
	}
	
	//���������� - 1:1 ���ǳ��� ����
	@RequestMapping(value="/one_update.do", method=RequestMethod.GET)
	public String oneUpdate() {
		return "mypage/one_update";
	}
	
	//���������� - FAQ
	@RequestMapping(value="/faq.do", method=RequestMethod.GET)
	public String faqList() {
		return "mypage/faq";
	}
	
	

	
}
