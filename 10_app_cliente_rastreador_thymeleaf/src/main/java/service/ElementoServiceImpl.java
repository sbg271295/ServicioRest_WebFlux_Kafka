package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import model.Elemento;
import reactor.core.publisher.Flux;
@Service
public class ElementoServiceImpl implements ElementoService {
	
	String url="http://localhost:8080";
	
	@Autowired
	WebClient web;
	@Override
	public Flux<Elemento> elementosPorPrecio(double precio) {
//Y esto no lo vamos a usar de esta forma		WebClient web=WebClient.create(url);
		
		return  web.get()
				   .uri(url+"/elementos/"+precio)
				   .accept(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToFlux(Elemento.class);
	}

}
