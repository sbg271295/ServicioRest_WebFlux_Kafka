package service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProductosServiceImpl implements ProductosService {

	private static List<Producto> productos=new ArrayList<>(List.of(new Producto(100,"Azucar","Alimentación",1.10,20),
			new Producto(101,"Leche","Alimentación",1.20,15),
			new Producto(102,"Jabón","Limpieza",0.89,30),
			new Producto(103,"Mesa","Hogar",125,4),
			new Producto(104,"Televisión","Hogar",650,10),
			new Producto(105,"Huevos","Alimentación",2.20,30),
			new Producto(106,"Fregona","Limpieza",3.40,6),
			new Producto(107,"Detergente","Limpieza",8.7,12)));

	@Override
	public Flux<Producto> catalogo() {
		
		return Flux.fromIterable(productos)
				   .delayElements(Duration.ofSeconds(2));
	}

	@Override
	public Flux<Producto> productosCategoria(String categoria) {
		return catalogo()
				        .filter(c->c.getCategoria().equals(categoria));
	}

	@Override
	public Mono<Producto> productoCodigo(int cod) {
		
		return catalogo()
				 .filter(c->c.getCodProducto()==cod)
				 .next()
				 .switchIfEmpty(Mono.just(new Producto() ));
	}

	
	//Este metodo no funciona, revisar creo que es por el ArrayList que da problemas,
	//Posibilidad hacer copia y sustituir con el producto añadido pero debería haber otra forma.
	@Override
	public Mono<Void> altaProducto(Producto producto) {
		return productoCodigo(producto.getCodProducto())//Mono<Producto>
				.switchIfEmpty(Mono.just(producto).map(p->{
					productos.add(producto);
					return p;
				}))//Mono<Producto>
				.then();//Mono<Void>
	}

	@Override
	public Mono<Producto> eliminarProducto(int cod) {
		 return productoCodigo(cod).map(p->
		                                 {
		                                	productos.removeIf(r->r.getCodProducto()==cod);
		                                	return p;
		                                 });
		    
	}

	@Override
	public Mono<Producto> actualizarPrecio(int cod, double precioNew) {
		
		return productoCodigo(cod).map(c->{
			                              
			                              c.setPrecioUnitario(precioNew);
			                              return c;
			
		                                  });
	}
	
}