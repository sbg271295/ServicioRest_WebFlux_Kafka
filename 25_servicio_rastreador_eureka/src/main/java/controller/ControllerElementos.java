package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import model.Elemento;
import reactor.core.publisher.Flux;
import service.ElementoService;

@RestController
public class ControllerElementos {

	@Autowired
	ElementoService service;
	
	@GetMapping (value="elementos/{precio}")
	public ResponseEntity<Flux<Elemento>> buscarProductos(@PathVariable("precio") double precioMax){	
		return new ResponseEntity<>(service.buscarProducto(precioMax),HttpStatus.OK);
	}
	
	
}
