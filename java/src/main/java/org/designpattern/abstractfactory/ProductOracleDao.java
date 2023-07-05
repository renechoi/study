package org.designpattern.abstractfactory;

public class ProductOracleDao implements ProductDao{
	@Override
	public void insertProduct(Product product) {
		System.out.println("Oracle Dao Insert Operation");
	}

	@Override
	public void updateProduct(Product product) {
		System.out.println("Oracle Dao update Operation");
	}

	@Override
	public void deleteProduct(Product product) {
		System.out.println("Oracle Dao delete Operation");
	}
}
