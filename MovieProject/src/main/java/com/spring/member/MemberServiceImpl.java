package com.spring.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	/**
	 * �α��� �� ȸ�� üũ
	 * @param email - �Է��� email
	 * @param pw - �Է��� password
	 * @return ȸ�� ���� ���
	 */
	@Override
	public int userCheck(String email, String pw) {
		int x = -1;
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberVO vo = memberDAO.userCheck(email);
		
		// ȸ���� ���
		if(vo != null) {
			if(pw.equals(vo.getM_password()))
				x = 1; // ���̵�/��й�ȣ �Ѵ� �´°��
			else
				x = -1; // ���̵�� ������ ��й�ȣ�� �ٸ����
		}else 
			x = 0; // ȸ���� �ƴ� ���
		return x;
	}

	@Override
	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteMember(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberVO findEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
