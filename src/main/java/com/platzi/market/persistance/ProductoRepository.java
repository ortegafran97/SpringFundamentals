package com.platzi.market.persistance;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistance.crud.ProductoCrudRepository;
import com.platzi.market.persistance.entity.Producto;
import com.platzi.market.persistance.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll(){
        List<Producto> p = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(p);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> list = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(list));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos  = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> productMapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
