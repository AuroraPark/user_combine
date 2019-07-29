package com.spring.mml;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class MmlRestController {
	
	@Autowired
	MmlService mmlService;
	
	@GetMapping(value="/getCountFollower/{id}", produces= {	MediaType.APPLICATION_XML_VALUE,
															MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<String> getCountFollower (@PathVariable("id") int id) {
		//��  ResponseEntity�� Integer�� ���������� ���ѱ���? ��ü�� ��Ƽ� ������ �Ҿ��ѵ�...
		String result = mmlService.getContFollower(id)+"";
		return new ResponseEntity<>(result,	HttpStatus.OK);
	}
	
	@GetMapping(value="/getCountLike/{mml_num}", produces= {MediaType.APPLICATION_XML_VALUE,
															MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<String> getCountLike (@PathVariable("mml_num") int mml_num) {
		System.out.println(mml_num+" �Ѿ��");
		//��  ResponseEntity�� Integer�� ���������� ���ѱ���? ��ü�� ��Ƽ� ������ �Ҿ��ѵ�...
		String result = mmlService.getCountLike(mml_num)+"";
		System.out.println(result+" �������"); 
	return new ResponseEntity<>(result,	HttpStatus.OK);
	}

	@GetMapping(value="/registerFollowRelationship/{id}", produces= {MediaType.APPLICATION_XML_VALUE,
															MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<String> registerFollowRelationship (@PathVariable("id") int id, HttpSession session) {
		System.out.println(id+" �Ѿ��");
				int follower =(int) session.getAttribute("id");	// ����id = ������
				System.out.println("���ǿ��� ���� ��ȸ�� id�� "+id);
				int followee = id;								// id = �Խ��� = �������
				
				String result = mmlService.registerFR(followee, follower);
				System.out.println(result+" �������"); 
	return new ResponseEntity<>(result,	HttpStatus.OK);
}

	@GetMapping(value="/giveLike/{mml_num}", produces= {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
		public ResponseEntity<String> giveLike (@PathVariable("mml_num") int mml_num, HttpSession session) {
		int giver = (int) session.getAttribute("id");// id = ��õ��
		
		//giver�� ��õ��, mml_num�� ��� �Խù� ��ȣ
		String result = mmlService.giveLike(giver, mml_num);
		return new ResponseEntity<>(result,	HttpStatus.OK);
	}
	
	@GetMapping(value="/giveWarning/{mml_num}", produces= {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
		public ResponseEntity<String> giveWarning (@PathVariable("mml_num") int mml_num, HttpSession session) {
		int warner = (int) session.getAttribute("id");// id = �Ű���
		
		//giver�� ��õ��, mml_num�� ��� �Խù� ��ȣ
		String result = mmlService.giveWarning(warner, mml_num);
		return new ResponseEntity<>(result,	HttpStatus.OK);
	}
	
}//e_controller
