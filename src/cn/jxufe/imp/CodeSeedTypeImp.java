package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;

import cn.jxufe.dao.CodeSeedTypeDAO;
import cn.jxufe.entity.CodeSeedType;
import cn.jxufe.service.CodeSeedTypeService;
public class CodeSeedTypeImp implements CodeSeedTypeService{
	@Autowired
	CodeSeedTypeDAO codeGenderDAO ;
    @Override
    public Iterable<CodeSeedType> findALl() {
        return codeGenderDAO.findAll();
    }
}
