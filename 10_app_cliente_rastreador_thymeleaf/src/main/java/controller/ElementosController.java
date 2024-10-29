package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import service.ElementoService;

@Controller
public class ElementosController {

	@Autowired
	ElementoService Eleservice;


	@GetMapping(value="buscar")
	public String buscar(@RequestParam("precio") double precioMax, Model model) {
		//Encapsula objetos reactivos
		//El número es un buffer que utiliza para ir almacenando datos antes de entregarlos directamente,
		//al indicarle 1 es para que a me dida que los vaya obteniendo los vaya generando.
		IReactiveDataDriverContextVariable reactive= new ReactiveDataDriverContextVariable(Eleservice.elementosPorPrecio(precioMax),1);
		model.addAttribute("resultado",reactive);
		
		return "listado";
	}
	
	@GetMapping(value="/")
	public String inicio() {
		return "inicio";
	}
}
