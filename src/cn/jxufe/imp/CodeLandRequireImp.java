package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.CodeLandRequireDAO;
import cn.jxufe.entity.CodeLandRequire;
import cn.jxufe.service.CodeLandRequireService;
@Service
public class CodeLandRequireImp implements CodeLandRequireService{
	@Autowired
	CodeLandRequireDAO codeLandRequireDAO ;
    @Override
    public Iterable<CodeLandRequire> findALl() {
        return codeLandRequireDAO.findAll();
    }
}
