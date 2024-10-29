package init;

import java.time.LocalDateTime;
import components.Productor;

public class Lanzador {

	public static void main(String[] args) {
		Productor productor=new Productor();
		
		for(int i=1; i<=10;i++) {
			productor.send("topicTest","Mensaje generado a las "+LocalDateTime.now());
		}
		
		productor.cerrar();

	}
	

}
