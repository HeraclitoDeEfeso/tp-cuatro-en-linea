package juego;

/**
 * Juego Cuatro en Línea Reglas: EL juego esta diseñado unicamente para ser
 * jugado por dos personas, termina cuando uno de los jugadores logra colocar 4
 * fichas en linea recta consecutiva en cualquier direccion, o cuando ya no hay
 * casilleros vacios. El Tablero se construye al inicio del juego, pero el
 * numero de filas y columnas debe ser mayor a 4.
 */

public class CuatroEnLinea {

	private int filas = 0;
	private int columnas = 0;
	private String jugadorAmarillo;
	private String jugadorVerde;
	private String jugadorActual;
	private Casillero[][] casilleros;
	public Error campoNombreVacio = new Error(
			"El campo de un jugador se encuentra vacio");
	public Error filasYColumnasDebenSerMayorACuatro = new Error(
			"Las filas y las columnas deben ser mayores o iguales a 4 ");
	public Error filasYColumnasNoDebenSuperarLos15 = new Error(
			"Las filas y las columnas no deben ser mayores a 15;");
	public Error losNombresNoPuedenSerIguales = new Error(
			"Los nombres de los jugadores deben ser diferentes");

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4 y menores a 16, los
	 * nombres de los jugadores no pueden estar vacios. Post: empieza el juego
	 * entre el jugador que tiene fichas rojas, identificado como 'jugadorRojo'
	 * y el jugador que tiene fichas verdes, identificado como 'jugadorVerde'.
	 * Todo el tablero está vacío.
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 * @param jugadorAmarillo
	 *            : nombre del jugador con fichas rojas.
	 * @param jugadorVerde
	 *            : nombre del jugador con fichas verde.
	 */
	public CuatroEnLinea(int filas, int columnas, String jugadorAmarillo,
			String jugadorVerde) {
		if (columnas > 15 || filas > 15) {
			throw filasYColumnasNoDebenSuperarLos15;
		}
		if (columnas < 4 || filas < 4) {
			throw filasYColumnasDebenSerMayorACuatro;
		}
		if ((jugadorVerde == null) || (jugadorVerde.equals(""))
				|| (jugadorAmarillo == null) || (jugadorAmarillo.equals(""))) {
			throw campoNombreVacio;
		}
		if (jugadorVerde == jugadorAmarillo) {
			throw losNombresNoPuedenSerIguales;
		}
		this.filas = filas;
		this.columnas = columnas;
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorVerde = jugadorVerde;
		this.jugadorActual = jugadorAmarillo;
		casilleros = new Casillero[columnas][filas];

		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				casilleros[i][j] = Casillero.VACIO;
			}
		}
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el
	 * tablero.
	 */
	public int contarFilas() {

		return this.filas;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el
	 * tablero.
	 */
	public int contarColumnas() {

		return this.columnas;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]. post: indica qué ocupa el casillero en
	 * la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		if (fila > contarFilas() || columna > contarColumnas()) {
			throw new Error(
					"La  fila o Columna ingresada es superior a las que posee el tablero actual");
		}
		if (fila < 1 || columna < 1) {
			throw new Error(
					"La numeracion de los casilleros arranca desde el uno");
		}
		return casilleros[columna - 1][fila - 1];
	}

	/**
	 * pre : el juego no terminó, columna está en el intervalo [1,
	 * contarColumnas()] y aún queda un Casillero.VACIO en la columna indicada.
	 * post: deja caer una ficha en la columna indicada.@param columna
	 */
	public void soltarFicha(int columna) {
		if (!termino()) {
			columna--;
			if (!(columna < 0 || columna >= contarColumnas() || noQuedanCasillerosVaciosEnLaColumna(columna))) {
				int fila = filas - 1;
				for (int i = 0; i < casilleros[columna].length; i++) {
					if (casilleros[columna][i] != Casillero.VACIO) {
						fila--;
					}
				}
				if (jugadorActual == jugadorAmarillo) {
					casilleros[columna][fila] = Casillero.AMARILLO;
					if (!termino()) {
						jugadorActual = jugadorVerde;
					}
				} else {
					casilleros[columna][fila] = Casillero.VERDE;
					if (!termino()) {
						jugadorActual = jugadorAmarillo;
					}
				}
			}
		}
	}

	/** Post: indica si quedan casilleros vacios en la columna */
	private boolean noQuedanCasillerosVaciosEnLaColumna(int columna) {
		boolean noHayCasilleroVacio = true;
		for (int j = 0; j < contarFilas(); j++) {
			if (casilleros[columna][j] == Casillero.VACIO) {
				noHayCasilleroVacio = false;
			}
		}
		return noHayCasilleroVacio;
	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {
		boolean partidoTerminado = false;
		if (hayGanador() || noExistenCasillerosVaciosEnElTablero()) {
			partidoTerminado = true;
		}

		return partidoTerminado;
	}

	/** POST; indica si existe algun casillero vacio en el tablero */
	private boolean noExistenCasillerosVaciosEnElTablero() {
		boolean estanTodosLosCasillerosOcupados = true;
		for (int i = 0; i < contarColumnas(); i++) {
			for (int j = 0; j < contarFilas(); j++) {
				if (casilleros[i][j] == Casillero.VACIO) {
					estanTodosLosCasillerosOcupados = false;
				}
			}
		}
		return estanTodosLosCasillerosOcupados;
	}

	// private int buscarCasillerosIgualesEnDireccion(int posicionFila, int
	// posicionColumna, int incrementoFila, int incrementoColumna){
	// int casilleroIguales = 0;
	// for(int i = posicionFila, j = posicionColumna; i <= contarFilas() && j <=
	// contarColumnas(); i += incrementoFila, j += incrementoFila ){
	// if(obtenerCasillero(posicionFila, posicionColumna) == obtenerCasillero(i,
	// j)){
	// casilleroIguales++;
	// }
	// }
	// return casilleroIguales;
	// }

	/** post: indica si el juego terminó y tiene un ganador. */
	public boolean hayGanador() {

		boolean alguienGano = false;
		for (int i = 0; i < contarColumnas(); i++) {
			for (int j = 0; j < contarFilas(); j++) {

				if (hayGanadorVertical(i, j)) {
					alguienGano = true;
				} else if (hayGanadorHorizontal(i, j)) {
					alguienGano = true;
				} else if (hayGanadorDiagonalDecreciente(i, j)) {
					alguienGano = true;
				} else if (hayGanadorDiagonalCreciente(i, j)) {
					alguienGano = true;
				}

			}
		}

		return alguienGano;
	}

	private boolean hayGanadorVertical(int fila, int columna) {
		boolean alguienGano = false;

		if (columna + 3 < contarColumnas()
				&& casilleros[fila][columna] != Casillero.VACIO
				&& casilleros[fila][columna] == casilleros[fila][columna + 1]
				&& casilleros[fila][columna] == casilleros[fila][columna + 2]
				&& casilleros[fila][columna] == casilleros[fila][columna + 3]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	private boolean hayGanadorHorizontal(int fila, int columna) {
		boolean alguienGano = false;
		if (fila + 3 < contarFilas()
				&& casilleros[fila][columna] != Casillero.VACIO
				&& casilleros[fila][columna] == casilleros[fila + 1][columna]
				&& casilleros[fila][columna] == casilleros[fila + 2][columna]
				&& casilleros[fila][columna] == casilleros[fila + 3][columna]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	private boolean hayGanadorDiagonalDecreciente(int fila, int columna) {
		boolean alguienGano = false;
		if (fila + 3 < contarFilas()
				&& columna + 3 < contarColumnas()
				&& casilleros[fila][columna] != Casillero.VACIO
				&& casilleros[fila][columna] == casilleros[fila + 1][columna + 1]
				&& casilleros[fila][columna] == casilleros[fila + 2][columna + 2]
				&& casilleros[fila][columna] == casilleros[fila + 3][columna + 3]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	private boolean hayGanadorDiagonalCreciente(int fila, int columna) {
		boolean alguienGano = false;
		if (fila + 3 < contarFilas()
				&& columna + 3 < contarColumnas()
				&& casilleros[fila][columna + 3] != Casillero.VACIO
				&& casilleros[fila][columna + 3] == casilleros[fila + 1][columna + 2]
				&& casilleros[fila][columna + 3] == casilleros[fila + 2][columna + 1]
				&& casilleros[fila][columna + 3] == casilleros[fila + 3][columna]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego. O en su defecto si empataron
	 */
	public String obtenerResultado() {
		String ganador = null;

		if (noExistenCasillerosVaciosEnElTablero()) {
			ganador = "Empataron";
		}
		if (hayGanador()) {
			if (jugadorActual == jugadorAmarillo) {
				ganador = jugadorAmarillo;
			} else if (jugadorActual == jugadorVerde) {
				ganador = jugadorVerde;
			}
		}

		return ganador;
	}
}
