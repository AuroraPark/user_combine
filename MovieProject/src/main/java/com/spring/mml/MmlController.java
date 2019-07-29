package com.spring.mml;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.member.MemberVO;

import org.springframework.web.bind.annotation.*;

@Controller
public class MmlController {
	
	@Autowired
	MmlService mmlService;
	
	@RequestMapping(value="/mmlList.do", method=RequestMethod.GET)
	public String mmlList() {
		return "mml/mmlList";
	}
	
	@RequestMapping(value="/mmlGet.do", method=RequestMethod.GET)
	public String mmlGet(@RequestParam("mml_num") int mml_num, Model model) {
		System.out.println("������ �Խñ� " +mml_num + " �Ѿ��" );
		mmlService.upCounter(mml_num);//��ȸ�� 1 ����
		Mml_ContentVO content = mmlService.getPage(mml_num); //���������̹Ƿ� null���� ������ �����ȴ�
		
		model.addAttribute("mml_content", content ); //��ȯ���� null�̸�, null���� �״�� ��ҿ� ��� ������
		model.addAttribute("member", mmlService.getMemberInfo(content.getId()));
	
		return "mml/mmlGet";
	}
	@RequestMapping(value="/mmlWrite.do", method=RequestMethod.GET)
	public String mmlWrite() {
		return "mml/mmlWrite";
	}
	@RequestMapping(value="/mmlFollowList.do", method=RequestMethod.GET)
	public String mmlFollow(@RequestParam("id") int id, Model model) {
		model.addAttribute("followee",mmlService.getMemberInfo(id));
		System.out.println("followee ���� ���� �Ϸ�");
		model.addAttribute("followers",mmlService.getFollowList(id));
		System.out.println("followers ���� ���� �Ϸ�");
		return "mml/mmlFollowList";
	}
	
	@RequestMapping(value="/mmlMemberList.do", method=RequestMethod.GET)
	public String mmlMember() {
		return "mml/mmlMemberList";
	}
	
	@GetMapping("/mmlDelete.do")
	public String mmlDelete(@RequestParam("mml_num")int mml_num, HttpServletResponse response) {
		mmlService.mmlDelete(mml_num);
		System.out.println(mml_num+" �� ������ �Խù� ����. ����Ʈ �������� Redirect");
		//�˸�â���� �����Ǿ����� �����ұ�??
		response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.println("<script>");
        out.println("alert('�Խñ��� �����Ǿ����ϴ�. ����...');");
        out.println("location.replace('/movie/mmlList.do')");
        out.println("</script>");
        out.close();
		return "redirect:/mmlList.do";
	}
	
	//�׽�Ʈ�� �޼���. ���ս� ����.
	@GetMapping("/mmlUpdate.do")
	public String mmlUpdate(Mml_ContentVO vo, Model model) {
		
		System.out.println("�ش� VO�� Update�� �Ѿ�ϴ� : "+vo);
		
        return "mml/mmlUpdate";
	}
	
	
}
