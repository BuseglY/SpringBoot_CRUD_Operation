package enoca.challenge.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import enoca.challenge.business.abstracts.ProductService;
import enoca.challenge.core.utilities.results.DataResult;
import enoca.challenge.core.utilities.results.Result;
import enoca.challenge.entities.concretes.Product;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	private ProductService productService;
	
	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}
	@GetMapping("/getAll")
	public DataResult<List<Product>> getAll(){
		return this.productService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Product product) {
		return this.productService.add(product);
	}
	
	@GetMapping("/deleteById")
	public Result delete(@RequestParam int id){
		return this.productService.deleteById(id);
	}
	
	@PutMapping("/updateProductWithId")
	public DataResult<List<Product>> updateProduct(@RequestParam int productId,@RequestParam String productName,int unitPrice) {
		return this.productService.updateProduct(productId,productName,unitPrice);
	}
	
	@GetMapping("/getById")
	public DataResult<Product> getById(@RequestParam int productId){
		return this.productService.getById(productId);
	}
	
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("/getByProductNameStartsWith")
	public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
		return this.productService.getByProductNameStartsWith(productName);
	}
	
	
	
	
	
	
	
	
	
	
	
}
