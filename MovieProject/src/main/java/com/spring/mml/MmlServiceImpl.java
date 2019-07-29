package com.spring.mml;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.MemberVO;

@Service("mmlService")
public class MmlServiceImpl implements MmlService {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private MmlDAO mmlDAO;
	
	@Override
	public Mml_ContentVO getPage(int mml_num) {
		System.out.println(mml_num); //ok
		Mml_ContentVO content = mmlDAO.getMmlContent(mml_num); //null�� �߻�
		System.out.println("�Խñ� ��ȸ ��ȯ��� = "+content);	
		
		return content;
	}
	
	public MemberVO getMemberInfo(int member) {
		//System.out.println(content.getId());
		MemberVO memberInfo = mmlDAO.getMemberInfo(member);
		//System.out.println(content);
		System.out.println(memberInfo);
	return memberInfo;
	}

	@Override
	public int getContFollower(int id) {return mmlDAO.getCountFollower(id);}

	@Override
	public int getCountLike(int mml_num) {return mmlDAO.getCountLike(mml_num);}

	@Override
	@Transactional
	public String registerFR(int followee, int follower) {
		if(followee == follower) { //��Ͱ˻�� ���� ���ִ°� ������ �ϴ�. �ƿ� ��ư�� �����Ű�� �ʵ��� ȭ�鿡�� ó���ϴ°� �����״�
			return "followItSelf";
		}else {
			//�ߺ�Ȯ��
			if(mmlDAO.FROverlapedChk(followee, follower) !=0) {
				return "overlaped";
			}else {
			//�ڱ��ȷο�� �ߺ��ȷο찡 �ƴ϶�� ���� �߰�
			//�̾ ���̺� ������Ʈ. �׻� 1�� �߰��Ѵ�.
				mmlDAO.registerFR(followee, follower);
				mmlDAO.updateMml_follower(followee);
				System.out.println("���� : �ȷο� �α�");
				return "success";
			}
		}
	}

	@Override
	@Transactional
	public String giveLike(int giver, int mml_num) {
		if(mmlDAO.giveLikeOverlapCheck(giver, mml_num) != 0) {
			return "overlaped";
		}else {	//�ߺ��� �ƴ� ����
			mmlDAO.giveLike(giver, mml_num);
			mmlDAO.updateMml_like(mml_num);
			System.out.println("���� : ��õ�ֱ�");
			return "success";
		}
	}

	@Override
	@Transactional
	public String giveWarning(int warner, int mml_num) {
		if(mmlDAO.giveWarningOverlapCheck(warner, mml_num) != 0) {
			return "overlaped";
		}else {	//�ߺ��� �ƴ� ����
			mmlDAO.giveWarning(warner, mml_num);
			mmlDAO.updateMml_warn_count(mml_num);
			System.out.println("���� : ��õ�ֱ�");
			return "success";
		}
	}

	@Override
	public void upCounter(int mml_num) {mmlDAO.upCounter(mml_num);}

	@Override
	public void mmlDelete(int mml_num) {mmlDAO.mmlDelete(mml_num);}

	@Override
	@Transactional
	public List<MemberVO> getFollowList(int id) {
		//1. �Խ��� id�� �������� �ȷο� ����� �̴´�.
		List<Integer> list = mmlDAO.getFollowListFromMF_table(id);
		System.out.println(id+"ȸ���� ������ �����̵���"+list);
		System.out.println("������!!!! �ϴ� List���·� ������ in");
		//2. �迭���� �ڷᱸ���� �־ ���� ���� ȣ��... ���??
		List<MemberVO> result = mmlDAO.getFollowers(list);
		System.out.println("��ȯ�� �����ʹ� ������ ����. "+result);
		return result;
	}
}//e_MmlServiceImpl
