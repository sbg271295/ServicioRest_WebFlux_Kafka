package service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import model.Elemento;
import reactor.core.publisher.Flux;
@Service
public class ElementoServiceImpl implements ElementoService {
	String url1="http://servicios-productos1";
	String url2="http://servicios-productos2";
	
	@Autowired
	WebClient.Builder builder;
	@Override
	public Flux<Elemento> buscarProducto(double precioMax) {
		Flux<Elemento> flux1=catalogo(url1,"tienda_1");
		Flux<Elemento> flux2=catalogo(url2,"tienda_2");
		
		return Flux.merge(flux2,flux1)
				.filter(c->c.getPrecioUnitario()<=precioMax)
				.delayElements(Duration.ofSeconds(1))
				;
	}

	private Flux<Elemento> catalogo (String url, String tienda){
	WebClient webClient=builder.build();
	
	return webClient.get()
	         .uri(url+"/productos")
	         .accept(MediaType.APPLICATION_JSON)
	         .retrieve()
	         //Estamos pasandole el catalogo de una tienda, y el propio java reconocera que 
	         //cada campo debe ir a donde se le indica ¿Pero que pasa con Tienda?
	         .bodyToFlux(Elemento.class)
	         .map(e->{
	        	 
	        	 e.setTienda(tienda);
	        	 return e;
	        	 
	                     });
	
	
	}

}
