package com.tienda.dto;

public class CategoriaResumenDTO {

    private Integer idCategoria;
    private String descripcion;
    private Long cantidadProductos;

    public CategoriaResumenDTO(Integer idCategoria, String descripcion, Long cantidadProductos) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.cantidadProductos = cantidadProductos;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Long cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
}