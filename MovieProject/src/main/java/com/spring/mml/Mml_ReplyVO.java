package com.spring.mml;

import java.util.Date;

import lombok.Data;

@Data
public class Mml_ReplyVO {

<<<<<<< HEAD
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
=======
	private int mml_reply_code;				//리뷰코드
	private int id;							//작성자 아이디코드
	private int mml_num;					//나영리코드
	private String mml_reply_content;		//나영리 댓글내용
	private Date mml_reply_write_date;		//나영리 댓글작성 시간
	private Date mml_reply_update_date;		//나영리 댓글갱신 시간
	private int mml_reply_like;				//댓글 츄촌 수
	private int mml_reply_dislike;			//댓글 비추천 수
	private int mml_rep_warn_count;			//댓글 경고 수
	
}

>>>>>>> a94a8023fa5a5843929e830508f93402d459c4a8
