package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.CodeLandRequireDAO;
import cn.jxufe.entity.CodeLandRequire;
import cn.jxufe.service.CodeLandRequireService;

@Service
public class CodeLandRequireImp implements CodeLandRequireService {
	@Autowired
	CodeLandRequireDAO codeLandRequireDAO;

	@Override
	public Iterable<CodeLandRequire> findALl() {
		return codeLandRequireDAO.findAll();
	}

	@Override
	public boolean isLandTypeSame(long landId, int landRequirementCode) {
		CodeLandRequire codeLandRequire = codeLandRequireDAO.findByCode(landRequirementCode);
		if (codeLandRequire == null)
			return false;
		String codeLandCaption = codeLandRequire.getCaption();

		int ldCode = (int) (landId - 1) / 6;
		String landRQ = "";
		switch (ldCode) {
		case 0:
			landRQ = "金土地";
			break;
		case 1:
			landRQ = "黄土地";
			break;
		case 2:
			landRQ = "红土地";
			break;
		case 3:
			landRQ = "黑土地";
			break;
		default:
			break;
		}
		return landRQ.equals(codeLandRequire.getCaption()) ? true : false;
	}
}
