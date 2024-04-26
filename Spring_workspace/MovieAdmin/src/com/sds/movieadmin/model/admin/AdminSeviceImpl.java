package com.sds.movieadmin.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieadmin.common.EncryptionManager;
import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.AdminException;

@Service
public class AdminSeviceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private EncryptionManager encryptionManager;
	
	@Override
	public void regist(Admin admin) throws AdminException {
		// TODO Auto-generated method stub
		admin.setAdmin_pw(encryptionManager.getConvertedData(admin.getAdmin_pw()));
		adminDAO.insert(admin);
	}

	@Override
	public Admin loginCheck(Admin admin) throws AdminException {
		// TODO Auto-generated method stub
		admin.setAdmin_pw(encryptionManager.getConvertedData(admin.getAdmin_pw()));
		Admin dto = adminDAO.loginCheck(admin);
		return dto;
	}
	
}
