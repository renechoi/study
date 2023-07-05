package org.designpattern.abstractfactory;

public interface DaoFactory {
	UserInfoDao createUserInfoDao();
	ProductDao createProductDao();
}
