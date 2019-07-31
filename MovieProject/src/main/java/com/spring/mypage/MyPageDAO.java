package com.spring.mypage;

import java.util.List;

import com.spring.member.MemberVO;

/**
* @Class Name : MyPageDAO.java
* @Description : 
* @Modification Information
* @
* @  ������     	  ������                 ��������
* @ ---------   ---------   -------------------------------
* @ 2019.07.17     ������      ���ʻ���
* @ 2019.07.24		������		one_get  �κ� �߰�
* @author bit 2��
* @since 2019. 07.01
* @version 1.0
* @see
*
*  Copyright (C) by Bit All right reserved.
*/

public interface MyPageDAO {

	public MemberVO getMember(int id);
	void updateMember(MemberVO memberVO);
	void updatePw(MemberVO memberVO);
	void updateNick(MemberVO memberVO);
	int checkNick(String m_nickname);
	//��������� ��������, �����ϱ�, Ż���û�ϱ�
	public String getMemberName(String m_email);	//��� �̸� ��������
	public int getMemberId(String m_email);		//��� ���̵� ��������
	public String getMemberNickname(String m_email);	//��� �г��� ��������
	
	//1:1 ���ǳ��� ����Ʈ ��������
	public List<OneVO> getQnaList(int id);	//1:1���� ����Ʈ ��������
	
	//1:1 ���� ����ϱ�, ��������, �����ϱ�, �����ϱ�
	public int insertQna(OneVO oneVO);	//1:1 ���� ���
	public OneVO getQnaDetail(int qna_no);	//1:1 ���� ������ ��������
	public int updateQna(OneVO oneVO);	//1:1 ���� ������ �����ϱ�
	
	
	//hm | 1:1 ���� �亯 ��������
	public OneAdVO getQnaAdDetail(int qna_no); // 1:1 ���� ������ ��������
	
}