package service;

import model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementoService {

	Flux<Elemento> buscarProducto(double precioMax);
}
