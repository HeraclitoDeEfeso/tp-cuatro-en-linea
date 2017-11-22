package juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ReiniciarJuego implements EventHandler<ActionEvent>{

	private Tablero tablero;
	 	
	  	public ReiniciarJuego(Tablero tablero) {
	  
 		this.tablero = tablero;
	  	}
	  
	  	@Override
	 	public void handle(ActionEvent event) {
	  		tablero.llamarReinicioJuego();
	  		tablero.dibujar();
	  		tablero.CerrarPanelGanador();
	  		
	  	}
}
