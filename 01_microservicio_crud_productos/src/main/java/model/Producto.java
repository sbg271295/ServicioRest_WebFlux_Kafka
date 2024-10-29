package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor

/*Anotación que incluye ya los Getter y los Setter*/
@Data

public class Producto {
	
	private int codProducto;
	private String nombre;
	private String categoria;
	private double precioUnitario;
	private int stock;
	
	
	
}