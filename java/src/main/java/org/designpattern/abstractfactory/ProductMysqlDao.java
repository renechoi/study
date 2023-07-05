package org.designpattern.abstractfactory;

public class ProductMysqlDao implements ProductDao{
	@Override
	public void insertProduct(Product product) {
		System.out.println("Mysql Dao Insert Operation");
	}

	@Override
	public void updateProduct(Product product) {
		System.out.println("Mysql Dao update Operation");
	}

	@Override
	public void deleteProduct(Product product) {
		System.out.println("Mysql Dao delete Operation");
	}
}
