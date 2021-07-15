package enoca.challenge.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import enoca.challenge.entities.concretes.Product;

public interface ProductDao extends CrudRepository<Product, Integer>{

	Product getById(int productId);
	List<Product> getByProductNameContains(String productName);
	List<Product> getByProductNameStartsWith(String productName);
	
}
