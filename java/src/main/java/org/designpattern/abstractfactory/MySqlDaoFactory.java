package org.designpattern.abstractfactory;

public class MySqlDaoFactory implements DaoFactory{
	@Override
	public UserInfoDao createUserInfoDao() {
		return new UserInfoMySqlDao();
	}

	@Override
	public ProductDao createProductDao() {
		return new ProductMysqlDao();
	}
}
