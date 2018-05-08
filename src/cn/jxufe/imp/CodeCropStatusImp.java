package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;

import cn.jxufe.dao.CodeCropStatusDAO;
import cn.jxufe.entity.CodeCropStatus;
import cn.jxufe.service.CodeCropStatusService;
public class CodeCropStatusImp implements CodeCropStatusService{
	@Autowired
	CodeCropStatusDAO codeCropStatusDAO ;
    @Override
    public Iterable<CodeCropStatus> findALl() {
        return codeCropStatusDAO.findAll();
    }
}
