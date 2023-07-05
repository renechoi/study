package org.designpattern.abstractfactory;

public class AbstractFactoryMain {

	public static void test() {

		String dbType = readeFile();

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("12345");
		userInfo.setPassword("!@#$%");
		userInfo.setUserName("�̼���");

		Product product = new Product();
		product.setProductId("0011AA");
		product.setProductName("TV");

		DaoFactory daoFactory = null;
		UserInfoDao userInfoDao = null;
		ProductDao productDao = null;

		if(dbType.equals("ORACLE")){
			daoFactory = new OracleDaoFactory();
		}
		else if(dbType.endsWith("MYSQL")){
			daoFactory = new MySqlDaoFactory();
		}
		else{
			System.out.println("db support error");
			return;
		}

		userInfoDao = daoFactory.createUserInfoDao();
		productDao = daoFactory.createProductDao();

		System.out.println("==USERINFO TRANSACTION==");
		userInfoDao.insertUserInfo(userInfo);
		userInfoDao.updateUserInfo(userInfo);
		userInfoDao.deleteUserInfo(userInfo);

		System.out.println("==PRODUCT TRANSACTION==");
		productDao.insertProduct(product);
		productDao.updateProduct(product);
		productDao.deleteProduct(product);


	}

	public static void main(String[] args) {
		test();
	}


	private static String readeFile(){
		// property file에 해당하는 설정이 등록되어 있다고 가정하자.
		return "MYSQL";
	}
}
