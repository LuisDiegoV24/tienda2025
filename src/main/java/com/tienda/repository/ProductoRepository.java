package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author Luis Diego
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    public List<Producto> findByActivoTrue();
}
