package com.platzi.market.persistance;

import com.platzi.market.persistance.crud.ProductoCrudRepository;
import com.platzi.market.persistance.entity.Producto;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto p){
        return productoCrudRepository.save(p);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
