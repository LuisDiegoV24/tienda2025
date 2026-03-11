package com.tienda.controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public IndexController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        var productos = productoService.getProductos(true);
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSeleccionada", 0);

        return "/index";
    }

    @GetMapping("/categoria/{idCategoria}")
    public String productosPorCategoria(@PathVariable("idCategoria") Integer idCategoria, Model model) {
        var productos = productoService.getProductosPorCategoria(idCategoria);
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSeleccionada", idCategoria);

        return "/index";
    }
}