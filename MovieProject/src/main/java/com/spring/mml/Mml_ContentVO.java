package com.spring.mml;

import java.util.Date;

import lombok.Data;

@Data
public class Mml_ContentVO {

	private int mml_num;			//�������ڵ�
	private int mi_code;			//��ȭ�ڵ�
	private int id;					//ID
	private int mml_view_count;		//��ȸ��
	private Date mml_write_date;	//�����
	private Date mml_update_date;	//������
	private int mml_like;			//���ƿ�
	private String mml_title;		//����
	private String mml_content;		//����
	private String mml_poster;		//������ ������
	private int mml_warn_count;		//������ �Ű��
}