package com.tienda.controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ConsultaController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        return "/consultas/listado";
    }

    // CONSULTAS SEMANA #8

    @PostMapping("/consultaDerivada")
    public String consultaDerivida(@RequestParam BigDecimal precioInf,
            @RequestParam BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaDerivada(precioInf, precioSup);
        model.addAttribute("resultadoProductos", productos);
        model.addAttribute("tipoConsultaProducto", "Derivada simple");
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    @PostMapping("/consultaJPQL")
    public String consultaJPQL(@RequestParam BigDecimal precioInf,
            @RequestParam BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("resultadoProductos", productos);
        model.addAttribute("tipoConsultaProducto", "JPQL simple");
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    @PostMapping("/consultaSQL")
    public String consultaSQL(@RequestParam BigDecimal precioInf,
            @RequestParam BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("resultadoProductos", productos);
        model.addAttribute("tipoConsultaProducto", "SQL simple");
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    // CONSULTA AVANZADA DE PRODUCTO

    @PostMapping("/producto/derivada")
    public String consultaProductoDerivada(@RequestParam BigDecimal precioMin,
            @RequestParam BigDecimal precioMax,
            @RequestParam Integer existenciasMin,
            @RequestParam String descripcionCategoria,
            Model model) {

        var productos = productoService.consultaProductoDerivada(
                precioMin, precioMax, existenciasMin, descripcionCategoria);

        model.addAttribute("resultadoProductos", productos);
        model.addAttribute("tipoConsultaProducto", "Derivada");
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);
        model.addAttribute("existenciasMin", existenciasMin);
        model.addAttribute("descripcionCategoria", descripcionCategoria);

        return "/consultas/listado";
    }

    @PostMapping("/producto/jpql")
    public String consultaProductoJPQL(@RequestParam BigDecimal precioMin,
            @RequestParam BigDecimal precioMax,
            @RequestParam Integer existenciasMin,
            @RequestParam String descripcionCategoria,
            Model model) {

        var productos = productoService.consultaProductoJPQL(
                precioMin, precioMax, existenciasMin, descripcionCategoria);

        model.addAttribute("resultadoProductos", productos);
        model.addAttribute("tipoConsultaProducto", "JPQL");
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);
        model.addAttribute("existenciasMin", existenciasMin);
        model.addAttribute("descripcionCategoria", descripcionCategoria);

        return "/consultas/listado";
    }

    @PostMapping("/producto/sql")
    public String consultaProductoSQL(@RequestParam BigDecimal precioMin,
            @RequestParam BigDecimal precioMax,
            @RequestParam Integer existenciasMin,
            @RequestParam String descripcionCategoria,
            Model model) {

        var productos = productoService.consultaProductoSQL(
                precioMin, precioMax, existenciasMin, descripcionCategoria);

        model.addAttribute("resultadoProductos", productos);
        model.addAttribute("tipoConsultaProducto", "SQL");
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);
        model.addAttribute("existenciasMin", existenciasMin);
        model.addAttribute("descripcionCategoria", descripcionCategoria);

        return "/consultas/listado";
    }

    // CONSULTA AVANZADA DE CATEGORIA
 
    @PostMapping("/categoria/derivada")
    public String consultaCategoriaDerivada(@RequestParam Integer cantidadMinProductos,
            @RequestParam String textoDescripcion,
            Model model) {

        var categorias = categoriaService.consultaCategoriaDerivada(
                cantidadMinProductos, textoDescripcion);

        model.addAttribute("resultadoCategorias", categorias);
        model.addAttribute("tipoConsultaCategoria", "Derivada");
        model.addAttribute("cantidadMinProductos", cantidadMinProductos);
        model.addAttribute("textoDescripcion", textoDescripcion);

        return "/consultas/listado";
    }

    @PostMapping("/categoria/jpql")
    public String consultaCategoriaJPQL(@RequestParam Long cantidadMinProductos,
            @RequestParam String textoDescripcion,
            Model model) {

        var categorias = categoriaService.consultaCategoriaJPQL(
                cantidadMinProductos, textoDescripcion);

        model.addAttribute("resultadoCategorias", categorias);
        model.addAttribute("tipoConsultaCategoria", "JPQL");
        model.addAttribute("cantidadMinProductos", cantidadMinProductos);
        model.addAttribute("textoDescripcion", textoDescripcion);

        return "/consultas/listado";
    }

    @PostMapping("/categoria/sql")
    public String consultaCategoriaSQL(@RequestParam Long cantidadMinProductos,
            @RequestParam String textoDescripcion,
            Model model) {

        var categorias = categoriaService.consultaCategoriaSQL(
                cantidadMinProductos, textoDescripcion);

        model.addAttribute("resultadoCategorias", categorias);
        model.addAttribute("tipoConsultaCategoria", "SQL");
        model.addAttribute("cantidadMinProductos", cantidadMinProductos);
        model.addAttribute("textoDescripcion", textoDescripcion);

        return "/consultas/listado";
    }
}