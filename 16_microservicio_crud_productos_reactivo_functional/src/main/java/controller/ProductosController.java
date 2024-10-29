package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.ProductosService;

@Configuration
public class ProductosController {
    @Autowired
    ProductosService productosService;
    
    /* 
    @GetMapping(value="productos")
    public ResponseEntity<Flux<Producto>> productos(){
        return new ResponseEntity<>(productosService.catalogo(), HttpStatus.OK);
    }

    @GetMapping(value="buscar/{categoria}")
    public ResponseEntity<Flux<Producto>> productosCategoria(@PathVariable("categoria") String categoria){
        return new ResponseEntity<>(productosService.productosCategoria(categoria), HttpStatus.OK);
    }

    @GetMapping(value="producto")
    public ResponseEntity<Mono<Producto>> productoCodigo(@RequestParam("cod") int cod) {
        return new ResponseEntity<>(productosService.productoCodigo(cod), HttpStatus.OK);
    }

    @PostMapping(value="alta", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Void>> altaProducto(@RequestBody Producto producto) {
        producto.setNuevo(true);
        return new ResponseEntity<>(productosService.altaProducto(producto), HttpStatus.OK);
    }

    @DeleteMapping(value="eliminar")
    public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("cod") int cod) {
        return productosService.eliminarProducto(cod)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PutMapping(value="actualizar")
    public Mono<ResponseEntity<Producto>> actualizarProducto(@RequestParam("cod") int cod, @RequestParam("precio") double precio) {
        return productosService.actualizarPrecio(cod, precio)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    } 
    */

    @Bean
    public RouterFunction<ServerResponse> respuestas() {
        return RouterFunctions.route(RequestPredicates.GET("/productos"),
                req -> ServerResponse.ok()
                    .body(productosService.catalogo(), Producto.class)
            )
            .andRoute(RequestPredicates.GET("/productos/{categoria}"),
                req -> ServerResponse.ok()
                    .body(productosService.productosCategoria(req.pathVariable("categoria")), Producto.class)
            )
            .andRoute(RequestPredicates.GET("/producto"), 
                req -> ServerResponse.ok()
                    .body(productosService.productoCodigo(req.queryParam("cod")
                        .map(s -> Integer.parseInt(s)).get()), Producto.class)
            )
            .andRoute(RequestPredicates.POST("/alta"),
                req -> req.bodyToMono(Producto.class)
                    .flatMap(p -> {
                        p.setNuevo(true);
                        return productosService.altaProducto(p);
                    })
                    .flatMap(v -> ServerResponse.ok().build())
            )
            .andRoute(RequestPredicates.DELETE("eliminar"), 
            		  req-> productosService.eliminarProducto(req.queryParam("cod")
            				                .map(s->Integer.parseInt(s)).get())
            		                        .flatMap(p->ServerResponse.ok()
            		                        		    .bodyValue(p)
            		                        		    )
            		                        		    .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
            		                        		    	                            .build()
            		                        		    ))
            .andRoute(RequestPredicates.PUT("actualizar"), 
					req->productosService.actualizarPrecio(req.queryParam("cod").map(s->Integer.parseInt(s)).get(), 
														   req.queryParam("precio").map(s->Double.parseDouble(s)).get())//Mono<Producto>
						.flatMap(p->ServerResponse.ok()//BodyBuilder
								.bodyValue(p)//Mono<ServerResponse>
								)//Mono<ServerResponse>
						.switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)//BodyBuilder
								.build()//Mono<ServerResponse>
								)//Mono<ServerResponse>
					);
}
    @Bean
	CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(source);
	}


}

