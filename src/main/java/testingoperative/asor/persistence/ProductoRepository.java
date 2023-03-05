package testingoperative.asor.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testingoperative.asor.domain.Product;
import testingoperative.asor.domain.repository.ProductRepository;
import testingoperative.asor.persistence.crud.ProductoCrudRepository;
import testingoperative.asor.persistence.entity.Producto;
import testingoperative.asor.persistence.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

//anotacion que le indica a spring que esta clase es quien interactua con la BD
@Repository
public class ProductoRepository implements ProductRepository {
    //con la anotacion autowired le autorizamos a spring crear la instanciacion de estos objetos que aun no estan inicializados y evitamos un null pointer exception
    @Autowired
    private ProductoCrudRepository ProductoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) ProductoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = ProductoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = ProductoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return ProductoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(ProductoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        ProductoCrudRepository.deleteById(productId);
    }

}
