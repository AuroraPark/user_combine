package com.spring.mypage;

import java.sql.Date;

import org.springframework.stereotype.Component;
/**
* @Class Name : AdminVO.java
* @Description : 1:1���� �亯 ������ - 1:1���� ���̺��� admin �亯 �÷���� �����ϰ� �ۼ�
* @Modification Information
* @
* @  ������     	  ������                 ��������
* @ ---------   ---------   -------------------------------
* @ 2019.07.22     ������      ���ʻ���
* @author bit 2��
* @since 2019. 07.01
* @version 1.0
* @see
*
*  Copyright (C) by Bit All right reserved.
*/

@Component
public class AdminVO {
	private int aqna_no; // 1:1 ���� �亯 ��ȣ
	private int qna_no;  // 1:1 ���� ���� ��ȣ
	private String aqna_content; // 1:1 ���� �亯����
	private Date aqna_date;  // 1:1 ���� �亯 ��¥
	private Date aqna_update_date; // 1:1 ���� �亯 ���� ��¥
	private int admin_num; // 1:1 ���� �亯 ������ ��ȣ
	
	public int getAqna_no() {
		return aqna_no;
	}
	public void setAqna_no(int aqna_no) {
		this.aqna_no = aqna_no;
	}
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public String getAqna_content() {
		return aqna_content;
	}
	public void setAqna_content(String aqna_content) {
		this.aqna_content = aqna_content;
	}
	public Date getAqna_date() {
		return aqna_date;
	}
	public void setAqna_date(Date aqna_date) {
		this.aqna_date = aqna_date;
	}
	public Date getAqna_update_date() {
		return aqna_update_date;
	}
	public void setAqna_update_date(Date aqna_update_date) {
		this.aqna_update_date = aqna_update_date;
	}
	public int getAdmin_num() {
		return admin_num;
	}
	public void setAdmin_num(int admin_num) {
		this.admin_num = admin_num;
	}

}
