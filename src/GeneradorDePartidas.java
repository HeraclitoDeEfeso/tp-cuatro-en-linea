import juego.*;

import java.util.Arrays;

public class GeneradorDePartidas {

	private int succes = 0;

	public void generar(CuatroEnLinea juego, int[] partida) {
		int[] jugadasPosibles = obtenerJugadasPosibles(juego);
		for (int jugadaNueva : jugadasPosibles) {
			if (succes < 1000) {
				int[] partidaNueva = agregarJugada(partida, jugadaNueva);
				CuatroEnLinea juegoNuevo = new CuatroEnLinea(
						juego.contarFilas(), juego.contarColumnas(),
						"jugadorRojo", "jugadorVerde");
				jugarPartida(juegoNuevo, partidaNueva);
				if (juegoNuevo.termino()) {
					succes++;
					System.out.println("La partida que termino es: "
							+ Arrays.toString(partidaNueva));
				} else {
					generar(juegoNuevo, partidaNueva);
				}
			}
		}
	}

	public void jugarPartida(CuatroEnLinea juego, int[] partida) {
		for (int jugada : partida) {
			juego.soltarFicha(jugada);
		}
	}

	public int[] agregarJugada(int[] partida, int jugada) {
		int[] nuevaPartida = new int[partida.length + 1];
		System.arraycopy(partida, 0, nuevaPartida, 0, partida.length);
		nuevaPartida[nuevaPartida.length - 1] = jugada;
		return nuevaPartida;
	}

	public int[] obtenerJugadasPosibles(CuatroEnLinea juego) {
		int columnasNoLlenas = 0;
		int[] jugadasPosibles = new int[juego.contarColumnas()];
		for (int i = 1; i <= juego.contarColumnas(); i++) {
			if (juego.obtenerCasillero(1, i) == Casillero.VACIO) {
				jugadasPosibles[columnasNoLlenas] = i;
				columnasNoLlenas++;
			}
		}
		return Arrays.copyOf(jugadasPosibles, columnasNoLlenas);
	}

}
