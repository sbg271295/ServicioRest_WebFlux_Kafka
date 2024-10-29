package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(value="productos")
//Se incluye la clase persistable para decirle que no tiene que guardar tiene que salvar el dato cuando lo damos de alta.
public class Producto implements Persistable<Integer>{
	@Id
	//Aquí pasa que java reconoce las mayusculas como "_" por ello, se le incorpora un column name para que
	//pueda identificarlo,
	@Column(value="codProducto")
	private int codProducto;
	private String nombre;
	private String categoria;
	@Column(value="precioUnitario")
	private double precioUnitario;
	private int stock;
	
	
	//Con transient lo que le deciimos es que no esta asociado a ninguna BBDD y que por ello, lo vamos autilizar nosotros para otra cosa;
	@Transient
	private boolean nuevo;
	@Override
	public Integer getId() {
		return codProducto;
	}
	@Override
	public boolean isNew() {
		return nuevo;
	}
}
