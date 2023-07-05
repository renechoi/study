package org.designpattern.abstractfactory;

public class OracleDaoFactory implements DaoFactory{
	@Override
	public UserInfoDao createUserInfoDao() {
		return new UserInfoOracleDao();
	}

	@Override
	public ProductDao createProductDao() {
		return new ProductOracleDao();
	}
}
