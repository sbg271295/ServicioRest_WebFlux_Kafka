package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import model.Producto;


import java.util.*;
import service.ProductosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductosController {
	
	@Autowired
	ProductosService productosService;
	
	@GetMapping(value="productos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> productos(){
		return productosService.catalogo();
	}
	
	@GetMapping(value="productos/{categoria}")
	public List<Producto> productosCategoria(@PathVariable("categoria") String categoria){
		return productosService.productosCategoria(categoria);
	}
	
	@GetMapping(value="producto")
	public Producto productosCod(@RequestParam("cod") int cod){
		return productosService.productoCodigo(cod);
	}
	

	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void altaProducto(@RequestBody Producto producto){
		 productosService.altaProducto(producto);
	}
	
	@DeleteMapping(value="eliminar")
	public void eliminarProducto(@RequestParam("cod") int cod){
		 productosService.eliminarProducto(cod);
	}
	
	@PutMapping(value="actualizar")
	public Producto actualizarProducto(@RequestParam("cod")int cod,@RequestParam("precioNew")double precio){
		return productosService.actualizarPrecio(cod,precio);
	}
}
