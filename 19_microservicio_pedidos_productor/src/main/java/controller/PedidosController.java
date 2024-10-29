package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Pedido;
import service.PedidosService;

@RestController
public class PedidosController {
@Autowired
PedidosService pedidosService;
public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido){
	try {
		pedidosService.registrarPedido(pedido);
		return new ResponseEntity<>(HttpStatus.OK);
	}catch(Exception ex) {
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
}
