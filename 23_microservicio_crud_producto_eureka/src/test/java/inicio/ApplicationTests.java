package inicio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import model.Producto;
import reactor.test.StepVerifier;
import service.ProductosService;

@SpringBootTest
class ApplicationTests {
@Autowired

ProductosService service;

	@Test
	void testProductosCategoria() {
		
		 StepVerifier.create(service.productosCategoria("Alimentación"))
		              .expectNextMatches(p->p.getNombre().equals("Azucar"))
		              .expectNextMatches(p->p.getNombre().equals("Leche"))
		              .expectNextMatches(p->p.getNombre().equals("Huevos"))
		              .verifyComplete();
	}
	@Test
	void testEliminarProducto() {
		 StepVerifier.create(service.eliminarProducto(103))
		             .expectNextMatches(c->c.getNombre().equals("Mesa"))
                     .verifyComplete();
		 
	}
	@Test
	void testAltaProducto() {
		Producto pr=new Producto(250,"ptest","cate",10,20);
		
		 StepVerifier.create(service.altaProducto(pr))
         .expectComplete()
         .verify();
		 
	}
	
	@Test
	void testCatalogo() {

		 StepVerifier.create(service.catalogo())
         .expectNextCount(9)
         .verifyComplete();
		 
	}

}
