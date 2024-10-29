package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Producto;
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
	public List<Producto> catalogo() {
		
		return productos;
	}

	@Override
	public List<Producto> productosCategoria(String categoria) {
		
		return productos.stream()
				        .filter(c->c.getCategoria().equals(categoria))
				        .toList();
	}

	@Override
	public Producto productoCodigo(int cod) {
		
		return productos.stream()
				        .filter(c->c.getCodProducto()==cod)
				        .findFirst()
				        .orElse(null)
				        ;
	}

	@Override
	public void altaProducto(Producto producto) {
	if(productoCodigo(producto.getCodProducto())==null) {
		productos.add(producto);
	}

	}

	@Override
	public Producto eliminarProducto(int cod) {
		Producto producto=productoCodigo(cod);
		
		if(producto!=null) {
			productos.removeIf(c->c.getCodProducto()==cod);
		}
		return producto;
	}

	@Override
	public Producto actualizarPrecio(int cod, double precioNew) {
		Producto producto=productoCodigo(cod);
		if(producto!=null) {
			producto.setPrecioUnitario(precioNew);
		}
		return producto;
	}

}
