package runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TestRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
	
		WebClient client=WebClient.create("http://localhost:8000");
	/*	Flux<Producto> flx=client.get()
			                     .uri("/productos")
		                         .accept(MediaType.APPLICATION_JSON)
		                         .retrieve()
		                         .bodyToFlux(Producto.class)
		                         ;

		flx.subscribe(p->System.out.println(p));*/  
		
	/*	client.post()
		      .uri("/alta")
		      .body(Mono.just(new Producto(200,"Frita","cat test",5,5)),Producto.class)
		      .retrieve()
		      .bodyToMono(Void.class)
		      .doOnTerminate(()->System.out.println("Se ha dado de alta el producto"))
		      .block()
		      ;*/
/*		
	Mono<Producto> monofind= client.get()
		      .uri("/producto?cod=190")
		      .accept(MediaType.APPLICATION_JSON)
		      .retrieve()
		      .bodyToMono(Producto.class);
		
		monofind.subscribe(p->System.out.println(p));
		monofind.switchIfEmpty(Mono.just(new Producto()).map(p->{
			
			System.out.println("No se ha encontrado producto");
			return p;
		                                                          }
		)).block();
              
	}*/

	    client.delete()
		      .uri("/eliminar?cod=120")
		      .accept(MediaType.APPLICATION_JSON)
		      .retrieve()
		      .onStatus(h->h.is4xxClientError(),t->{
		    	  System.out.println("No se encontro el producto");
		    	  return Mono.empty();
		      })
		      .bodyToMono(Producto.class)
		      .subscribe(c->System.out.println(c))
		       ;
	    
		
}
}
