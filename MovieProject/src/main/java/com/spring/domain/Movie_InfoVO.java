package com.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Movie_InfoVO {

	private int mi_code;		//��ȭ �ڵ�
	private String mi_ktitle;	//��ȭ�̸�
	private String mi_etitle;	//��ȭ�̸�eng
	private String mi_director;	//�����̸�
	private String mi_poster;	//������
	private Date mi_releaseday;	//������
	private String mi_ccode;	//������
	private String mi_actor;	//���
	private String mi_story;	//�ٰŸ�
	private String mi_teaser;	//Ƽ��
	private String grade_code;	//���ǵ��
	private String mi_gcode;	//�帣
}
