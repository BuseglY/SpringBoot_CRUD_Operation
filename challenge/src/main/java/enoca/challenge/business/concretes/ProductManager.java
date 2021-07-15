package enoca.challenge.business.concretes;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import enoca.challenge.business.abstracts.ProductService;
import enoca.challenge.core.utilities.results.DataResult;
import enoca.challenge.core.utilities.results.ErrorDataResult;
import enoca.challenge.core.utilities.results.ErrorResult;
import enoca.challenge.core.utilities.results.Result;
import enoca.challenge.core.utilities.results.SuccessDataResult;
import enoca.challenge.core.utilities.results.SuccessResult;
import enoca.challenge.dataAccess.abstracts.ProductDao;
import enoca.challenge.entities.concretes.Product;

@Service
public class ProductManager implements ProductService{

	private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	@Override
	public DataResult<List<Product>> getAll() {
		List<Product> products = new ArrayList<Product>();  
		productDao.findAll().forEach(products1 -> products.add(products1));   
		return new SuccessDataResult<List<Product>>
		(products,"Data listelendi");
	}
	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Ürün eklendi");
	}
	@Override
	public Result deleteById(int id) {
		if(productDao.findById(id).isPresent()) {
			productDao.deleteById(id);
			return new SuccessResult("Ürün silindi");
		}
		else {
			return new ErrorResult("Bu id ye sahip bir ürün bulunamadı. Silme işlemi başarısız.");
		}
	}
	@Override
	public DataResult<List<Product>> updateProduct(int productId,String productName,int unitPrice) {
		
		if(productDao.findById(productId).isPresent()) {
			Product updateProduct=this.productDao.findById(productId).get();
			updateProduct.setProductId(productId);
			updateProduct.setProductName(productName);
			updateProduct.setUnitPrice(unitPrice);

			this.productDao.save(updateProduct);
			return new SuccessDataResult<List<Product>>(null,"Güncelleme işleminiz başarılı");
		}
		
		else {
			return new ErrorDataResult<List<Product>>(null,"Bu id'ye sahip ürün bulunamadı. Güncelleme işleminiz başarısız.");
		}
		
	}
	@Override
	public DataResult<Product> getById(int productId) {
		if(productDao.findById(productId).isPresent()) {
			Product data=this.productDao.findById(productId).get();
			return new SuccessDataResult<Product>(data,"Ürün getirildi.");
		}
		else {
			return new ErrorDataResult<Product>(null,"Bu id'ye sahip ürün bulunamadı. İşlem başarısız.");
		}
	}
	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		if(!productDao.getByProductNameContains(productName).isEmpty()) {
			return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName),"İşlem başarılı");
		}
		else {
			return new ErrorDataResult<List<Product>>(null,"Bu harfi içeren bir ürün ismi bulunmuyor.");
		}
	}
	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		if(!this.productDao.getByProductNameStartsWith(productName).isEmpty()) {
			return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName),"İşlem başarılı");
		}
		else
			return new ErrorDataResult<List<Product>>(null,"Bu harf ile başlayan ürün ismi bulunmamaktadır.");
	}
	
	

}
