package com.tienda.repository;

import com.tienda.domain.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public List<Producto> findByActivoTrue();

    // CONSULTAS SEMANA 8

    // Consulta derivada que recupera los productos de un rango de precio
    // y los ordena por precio ascendentemente
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(BigDecimal precioInf, BigDecimal precioSup);

    // Consulta JPQL que recupera los productos de un rango de precio
    // y los ordena por precio ascendentemente
    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(@Param("precioInf") BigDecimal precioInf,
                                       @Param("precioSup") BigDecimal precioSup);

    // Consulta SQL que recupera los productos de un rango de precio
    // y los ordena por precio ascendentemente
    @Query(nativeQuery = true,
           value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(@Param("precioInf") BigDecimal precioInf,
                                      @Param("precioSup") BigDecimal precioSup);

    // CONSULTAS AVANZADAS - PRODUCTO

    public List<Producto> findByActivoTrueAndPrecioBetweenAndExistenciasGreaterThanAndCategoriaActivoTrueAndCategoriaDescripcionContainingIgnoreCaseOrderByPrecioAsc(
            BigDecimal precioMin,
            BigDecimal precioMax,
            Integer existenciasMin,
            String descripcionCategoria
    );

    // Consulta JPQL con JOIN
    @Query("""
           SELECT p
           FROM Producto p
           JOIN p.categoria c
           WHERE p.activo = true
             AND p.precio BETWEEN :precioMin AND :precioMax
             AND p.existencias > :existenciasMin
             AND c.activo = true
             AND LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :descripcionCategoria, '%'))
           ORDER BY p.precio ASC
           """)
    public List<Producto> consultaProductoJPQL(@Param("precioMin") BigDecimal precioMin,
                                               @Param("precioMax") BigDecimal precioMax,
                                               @Param("existenciasMin") Integer existenciasMin,
                                               @Param("descripcionCategoria") String descripcionCategoria);

    // Consulta SQL nativa con JOIN
    @Query(value = """
           SELECT p.*
           FROM producto p
           INNER JOIN categoria c ON p.id_categoria = c.id_categoria
           WHERE p.activo = true
             AND p.precio BETWEEN :precioMin AND :precioMax
             AND p.existencias > :existenciasMin
             AND c.activo = true
             AND LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :descripcionCategoria, '%'))
           ORDER BY p.precio ASC
           """, nativeQuery = true)
    public List<Producto> consultaProductoSQL(@Param("precioMin") BigDecimal precioMin,
                                              @Param("precioMax") BigDecimal precioMax,
                                              @Param("existenciasMin") Integer existenciasMin,
                                              @Param("descripcionCategoria") String descripcionCategoria);

    // FILTRO POR CATEGORÍA
    public List<Producto> findByActivoTrueAndCategoriaIdCategoriaAndCategoriaActivoTrueOrderByPrecioAsc(Integer idCategoria);
}