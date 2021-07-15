package enoca.challenge.business.abstracts;

import java.util.List;

import enoca.challenge.core.utilities.results.DataResult;
import enoca.challenge.core.utilities.results.Result;
import enoca.challenge.entities.concretes.Product;

public interface ProductService {

	DataResult<List<Product>> getAll();
	Result add(Product product);
	Result deleteById(int id);
	DataResult<List<Product>> updateProduct(int productId,String productName,int unitPrice);
	DataResult<Product> getById(int productId);
	DataResult<List<Product>> getByProductNameContains(String productName);
	DataResult<List<Product>> getByProductNameStartsWith(String productName);
}
