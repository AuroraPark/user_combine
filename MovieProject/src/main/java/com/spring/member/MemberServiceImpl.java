package com.spring.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Service("memberService")
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	@Autowired
	private SqlSession sqlSession;
	
	@Setter(onMethod_ = {@Autowired})
	private MemberDAO memberDAOglobal;
	
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
		
		if(vo != null) {
			if(pw.equals(vo.getM_password()))
				x = 1; // ���̵�/��й�ȣ �Ѵ� �´°��
			else
				x = -1;// ���̵�� ������ ��й�ȣ�� �ٸ����
		}else 
			x = 0;  // ȸ���� �ƴ� ���
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
	public String findEmail(MemberVO vo) {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		String email = memberDAO.findEmail(vo);
		
		if(email != null) { 
			return email;
		}
		else
			return  "fail";
					
	}

	@Override
	public MemberVO findPw(MemberVO vo) {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberVO memberVO = memberDAO.findPw(vo);
		if(memberVO == null) {
			return null;
		}else {
			return memberVO;
		}
	}


	/**
	 * ȸ������
	 * @param memberVO vo
	 */
	@Override
	public void memberJoin(MemberVO vo) {
		vo.setM_image("");
		vo.setM_image("defaultprofile.PNG");
		vo.setM_cert("N");
		vo.setM_deleteyn("N");
		vo.setM_following(0);
		vo.setM_follower(0);
		vo.setM_level("BRONZE");
		vo.setM_blacklist("N");
		
		memberDAOglobal.memberJoin(vo);
		}

	/**
	 * ���� �ߺ�Ȯ��
	 * @param memberVO vo
	 */
	@Override
	public boolean emailOverlapChk(MemberVO vo) {
		if (memberDAOglobal.emailOverlapChk(vo.getM_email()) == 0) {
			System.out.println("��ġ�ϴ� ���� ����");
			return true;
		} else {
			System.out.println("��ġ�ϴ� ���� ����. 1 on n");
			return false;
			
		}
	}

	/**
	 * �г��� �ߺ�Ȯ��
	 * @param memberVO vo
	 */
	@Override
	public boolean nickOverlapChk(MemberVO vo) {
		if (memberDAOglobal.nickOverlapChk(vo.getM_nickname()) == 0) {
			System.out.println("��ġ�ϴ� �г��� = ��� ����");
			return true;
		} else {
			System.out.println("��ġ�ϴ� �г��� ����. 1 on n");
			return false;
			
		}
	}
	
	@Override
	public int updatePw(MemberVO vo) {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int num = memberDAO.updatePw(vo);
		return num;
	}

	@Override
	public int getId(String m_email, String m_password) {
		
		return memberDAOglobal.getId(m_email, m_password);
	}

}