package com.spring.mml;

import java.util.Date;

import lombok.Data;

@Data
public class Mml_ReplyVO {

	private int mml_reply_code;				//�����ڵ�
	private int id;							//�ۼ��� ���̵��ڵ�
	private int mml_num;					//�������ڵ�
	private String mml_reply_content;		//������ ��۳���
	private Date mml_reply_write_date;		//������ ����ۼ� �ð�
	private Date mml_reply_update_date;		//������ ��۰��� �ð�
	private int mml_reply_like;				//��� ���� ��
	private int mml_reply_dislike;			//��� ����õ ��
	private int mml_rep_warn_count;			//��� ��� ��
	
}
