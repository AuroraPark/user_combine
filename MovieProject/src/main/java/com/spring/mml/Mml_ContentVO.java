package com.spring.mml;

import java.util.Date;

import lombok.Data;

@Data
public class Mml_ContentVO {

<<<<<<< HEAD
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
=======
	private int mml_num;			//나영리코드
	private int mi_code;			//영화코드
	private int id;					//ID
	private int mml_view_count;		//조회수
	private Date mml_write_date;	//등록일
	private Date mml_update_date;	//수정일
	private int mml_like;			//좋아요
	private String mml_title;		//제목
	private String mml_content;		//내용
	private String mml_poster;		//나영리 포스터
	private int mml_warn_count;		//나영리 신고수
>>>>>>> a94a8023fa5a5843929e830508f93402d459c4a8
}
