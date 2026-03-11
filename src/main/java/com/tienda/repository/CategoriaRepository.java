package com.tienda.repository;

import com.tienda.domain.Categoria;
import com.tienda.dto.CategoriaResumenDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    public List<Categoria> findByActivoTrue();

    // CONSULTA DERIVADA LIMITADA

    public List<Categoria> findByActivoTrueAndDescripcionContainingIgnoreCase(String textoDescripcion);

    // CONSULTA JPQL
    
    @Query("""
           SELECT new com.tienda.dto.CategoriaResumenDTO(
               c.idCategoria,
               c.descripcion,
               COUNT(p)
           )
           FROM Categoria c
           JOIN c.productos p
           WHERE c.activo = true
             AND p.activo = true
             AND LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :textoDescripcion, '%'))
           GROUP BY c.idCategoria, c.descripcion
           HAVING COUNT(p) >= :cantidadMinProductos
           ORDER BY COUNT(p) DESC
           """)
    public List<CategoriaResumenDTO> consultaCategoriaJPQL(
            @Param("cantidadMinProductos") Long cantidadMinProductos,
            @Param("textoDescripcion") String textoDescripcion);

    // CONSULTA SQL NATIVA

    @Query(value = """
           SELECT c.id_categoria, c.descripcion, COUNT(p.id_producto) AS cantidad_productos
           FROM categoria c
           INNER JOIN producto p ON p.id_categoria = c.id_categoria
           WHERE c.activo = true
             AND p.activo = true
             AND LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :textoDescripcion, '%'))
           GROUP BY c.id_categoria, c.descripcion
           HAVING COUNT(p.id_producto) >= :cantidadMinProductos
           ORDER BY COUNT(p.id_producto) DESC
           """, nativeQuery = true)
    public List<Object[]> consultaCategoriaSQL(
            @Param("cantidadMinProductos") Long cantidadMinProductos,
            @Param("textoDescripcion") String textoDescripcion);
}