package com.spring.warningCombine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarningServiceImpl implements WarningService {
	
	@Autowired
	WarningDTO warningDTO;
	
	@Override
	public String warningCombine(WarningCombineVO vo) {
		
		String result;
		
		
		
		switch (vo.getBoardCode()) {
		case 1 :
			vo.setTableName("free_warning");
			result = "�����Խ��� �Խñ�  �Ű� �Ϸ�";
			break;
		case 2 :
			vo.setTableName("bfr_warning");
			result = "�����Խ��� ��� �Ű� �Ϸ�";
			break;
		case 3 :
			vo.setTableName("share_warning");
			result = "�����Խ��� �Խñ� �Ű� �Ϸ�";
			break;
		case 4 :
			vo.setTableName("sr_warning");
			result = "�����Խ��� ��� �Ű� �Ϸ�";
			break;
		case 5 :
			vo.setTableName("mml_warning");
			result = "������ �Խ��� �Խñ� �Ű� �Ϸ�";
			break;
		case 6 :
			vo.setTableName("mmlr_warning");
			result = "������ �Խ��� ��� �Ű� �Ϸ�";
			break;
		case 7 :
			vo.setTableName("mr_warning");
			result = "��ȭ���� �Ű� �Ϸ�";
			break;
		case 8 :	
			vo.setTableName("cr_warning");
			result = "���帮�� �Ű� �Ϸ�";
			break;
		default :
			result = "��ġ�ϴ� �Խ��� �ڵ尡 �����ϴ�.";
			return result;
		}
		
		warningDTO.warningCombine(vo);
		return result;
	}

}
