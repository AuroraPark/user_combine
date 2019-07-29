package com.spring.mml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.member.MemberVO;

public interface MmlDAO {

	public void upCounter(@Param("mml_num")int mml_num);

	public Mml_ContentVO getMmlContent(int mml_num);

	public MemberVO getMemberInfo(int id);

	public int getCountFollower(int id);

	public int getCountLike(int mml_num);
//�ȷο� ��Ʈ�ѷ����� ���̴� ���� 3��
	public void registerFR(@Param("mf_ing")int mf_ing, @Param("mf_wer")int mf_wer);

	public int FROverlapedChk(@Param("mf_ing")int mf_ing,@Param("mf_wer") int mf_wer);

	public void updateMml_follower(@Param("id")int id);
//��õ ��Ʈ�ѷ����� ���̴� ���� 3��
	public int giveLikeOverlapCheck(@Param("giver")int giver,@Param("mml_num")int mml_num);

	public void giveLike(@Param("giver")int giver, @Param("mml_num")int mml_num);
	
	public void updateMml_like(@Param("mml_num")int mml_num);
//�Ű� ��Ʈ�ѷ����� ���̴� ���� 3��
	public int giveWarningOverlapCheck(@Param("giver")int warner,@Param("mml_num") int mml_num);

	public void giveWarning(@Param("giver")int warner,@Param("mml_num") int mml_num);

	public void updateMml_warn_count(@Param("mml_num")int mml_num);
//��κ��� ������ ������, ������ ���۸� �������� ������ �� ������? �� Ž���غ���	

	public void mmlDelete(int mml_num);

	public List<Integer> getFollowListFromMF_table(int id);

	public List<MemberVO> getFollowers(@Param("list")List<Integer> list);


}
