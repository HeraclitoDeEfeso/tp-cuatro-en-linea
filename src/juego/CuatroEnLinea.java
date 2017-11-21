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
		if (validaciones(filas, columnas, jugadorAmarillo, jugadorVerde)) {
			this.filas = filas;
			this.columnas = columnas;
			this.jugadorAmarillo = jugadorAmarillo;
			this.jugadorVerde = jugadorVerde;
			this.jugadorActual = jugadorAmarillo;
			casilleros = new Casillero[columnas][filas];
		}
		vaciarCasilleros();

	}

	/*
	 * POST : chequea que los parametros ingresados al iniciar el juego sean
	 * correctos
	 */
	private boolean validaciones(int columnas, int filas,
			String jugadorAmarillo, String jugadorVerde) {
		if (columnas > 15 || filas > 15) {
			throw new Error(
					"Las filas y las columnas no deben ser mayores a 15;");
		}
		if (columnas < 4 || filas < 4) {
			throw new Error(
					"Las filas y las columnas deben ser mayores o iguales a 4 ");
		}

		if (jugadorVerde == null || jugadorAmarillo == null
				|| jugadorAmarillo.length() < 3 || jugadorVerde.length() < 3) {
			throw new Error(
					"Los nombres de los jugadores deben tener almenos 3 letras");
		}
		if (jugadorAmarillo.length() > 15 || jugadorVerde.length() > 15) {
			throw new Error(
					"Los nombres de los jugadores deben tener menos de 15 caracteres");
		}
		if (jugadorAmarillo.equalsIgnoreCase(jugadorVerde)) {
			throw new Error(
					"Los nombres de los jugadores no pueden ser iguales");
		}
		boolean LosParametrosEstanBien = true;
		return LosParametrosEstanBien;
	}

	/**
	 * post: pone todos los casilleros en blanco(vacio)
	 */
	public void vaciarCasilleros() {

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
		columna--;
		int filaDestino = filas - 1;
		if (!termino() && laColumnaExisteYTieneLugar(columna)) {
			for (int i = 0; i < casilleros[columna].length; i++) {
				if (casilleros[columna][i] != Casillero.VACIO) {
					filaDestino--;
				}
			}
			alternarTurnosYPintar(columna, filaDestino);
		}
	}

	/*
	 * post: pinta el casillero indicado del color del jugador actual y le pasa
	 * el turno al siguiente jugador
	 */
	private void alternarTurnosYPintar(int columna, int fila) {
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

	/*
	 * post: cheque que la columna indicada existe y tiene almenos un casillero
	 * vacio
	 */
	private boolean laColumnaExisteYTieneLugar(int columna) {
		return (columna >= 0 && columna < contarColumnas() && hayCasillerosVaciosEnLaColumna(columna));
	}

	/** Post: indica si quedan casilleros vacios en la columna */
	private boolean hayCasillerosVaciosEnLaColumna(int columna) {

		return (casilleros[columna][0] == Casillero.VACIO);
	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {

		return (hayGanador() || !hayCasillerosVaciosEnElTablero());
	}

	/**
	 * POST; indica si existe algun casillero vacio en el tablero
	 */
	private boolean hayCasillerosVaciosEnElTablero() {
		boolean hayUnCasilleroVacio = false;
		for (int i = 0; i < contarColumnas() && !hayUnCasilleroVacio; i++) {
			hayUnCasilleroVacio = hayCasillerosVaciosEnLaColumna(i);
		}

		return hayUnCasilleroVacio;
	}

	/** post: indica si el juego terminó y tiene un ganador. */
	
	public boolean hayGanador() {

		boolean alguienGano = false;
		for (int i = 0; i < contarColumnas(); i++) {
			for (int j = 0; j < contarFilas(); j++) {
				alguienGano = alguienGano || hayGanadorVertical(i, j)
						|| hayGanadorHorizontal(i, j)
						|| hayGanadorDiagonalDecreciente(i, j)
						|| hayGanadorDiagonalCreciente(i, j);
			}
		}

		return alguienGano;
	}

	private boolean hayGanadorVertical(int columna, int fila) {
		boolean alguienGano = false;

		if (fila + 3 < contarColumnas()
				&& casilleros[columna][fila] != Casillero.VACIO
				&& casilleros[columna][fila] == casilleros[columna][fila + 1]
				&& casilleros[columna][fila] == casilleros[columna][fila + 2]
				&& casilleros[columna][fila] == casilleros[columna][fila + 3]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	private boolean hayGanadorHorizontal(int columna, int fila) {
		boolean alguienGano = false;
		if (columna + 3 < contarFilas()
				&& casilleros[columna][fila] != Casillero.VACIO
				&& casilleros[columna][fila] == casilleros[columna + 1][fila]
				&& casilleros[columna][fila] == casilleros[columna + 2][fila]
				&& casilleros[columna][fila] == casilleros[columna + 3][fila]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	private boolean hayGanadorDiagonalDecreciente(int columna, int fila) {
		boolean alguienGano = false;
		if (columna + 3 < contarFilas()
				&& fila + 3 < contarColumnas()
				&& casilleros[columna][fila] != Casillero.VACIO
				&& casilleros[columna][fila] == casilleros[columna + 1][fila + 1]
				&& casilleros[columna][fila] == casilleros[columna + 2][fila + 2]
				&& casilleros[columna][fila] == casilleros[columna + 3][fila + 3]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	private boolean hayGanadorDiagonalCreciente(int columna, int fila) {
		boolean alguienGano = false;
		if (columna + 3 < contarFilas()
				&& fila + 3 < contarColumnas()
				&& casilleros[columna][fila + 3] != Casillero.VACIO
				&& casilleros[columna][fila + 3] == casilleros[columna + 1][fila + 2]
				&& casilleros[columna][fila + 3] == casilleros[columna + 2][fila + 1]
				&& casilleros[columna][fila + 3] == casilleros[columna + 3][fila]) {

			alguienGano = true;
		}
		return alguienGano;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerResultado() {
		String ganador = null;

		if (hayGanador()) {

			ganador = jugadorActual;
		}
		return ganador;
	}
}

