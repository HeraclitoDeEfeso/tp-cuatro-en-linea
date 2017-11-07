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
	private String jugadorRojo;
	private String jugadorVerde;
	private String jugadorActual;
	private Casillero[][] casilleros;

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4, los nombres de los
	 * jugadores no pueden estar vacios. Post: empieza el juego entre el jugador
	 * que tiene fichas rojas, identificado como 'jugadorRojo' y el jugador que
	 * tiene fichas verdes, identificado como 'jugadorVerde'. Todo el tablero
	 * está vacío.
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo
	 *            : nombre del jugador con fichas rojas.
	 * @param jugadorVerde
	 *            : nombre del jugador con fichas verde.
	 */
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo,
			String jugadorVerde) {
//		if (filas > 15) {
//			throw new Error("El número de filas no debe superar el 15");
//		}
//		if (columnas > 15) {
//			throw new Error("El número de columnas no debe superar el 15");
//		}
		if (filas < 4) {
			throw new Error("El número de filas deben ser por lo menos 4");
		}
		if (columnas < 4) {
			throw new Error("El número de columnas debe ser por lo menos 4");
		}
		if ((jugadorRojo == null) || (jugadorRojo.equals(""))) {
			throw new Error("El campo Jugador Rojo no puede estar vacío");
		}
		if ((jugadorVerde == null) || (jugadorVerde.equals(""))) {
			throw new Error("El campo Jugador Amarillo no puede estar vacío");
		}
		this.filas = filas;
		this.columnas = columnas;
		this.jugadorRojo = jugadorRojo;
		this.jugadorVerde = jugadorVerde;
		this.jugadorActual = jugadorRojo;
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

		if ((fila < 1 || fila > contarFilas())
				&& (columna < 1 || columna > contarColumnas())) {
			throw new Error(
					"El número de fila o columna corresponde al tablero actual");
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
			if (columna < 0 || columna > contarColumnas()
					|| noQuedanCasillerosVaciosEnLaColumna(columna)) {
				throw new Error(
						"La columna seleccionada no puede ser seleccionada");
			}

			int fila = filas - 1;
			for (int i = 0; i < casilleros[columna].length; i++) {
				if (casilleros[columna][i] != Casillero.VACIO) {
					fila--;
				}
			}
			if (jugadorActual == jugadorRojo) {
				casilleros[columna][fila] = Casillero.ROJO;
				jugadorActual = jugadorVerde;
			} else {
				casilleros[columna][fila] = Casillero.VERDE;
				jugadorActual = jugadorRojo;
			}
		}
	}

	/**Post: indica si quedan casilleros vacios en la columna */
	private boolean noQuedanCasillerosVaciosEnLaColumna(int columna) {
		boolean estanTodosLosCasillerosOcupados = false;
		for (int j = 0; j < contarFilas(); j++) {
			if (casilleros[columna][j] == Casillero.VACIO) {
				estanTodosLosCasillerosOcupados = false;
			}
		}
		return estanTodosLosCasillerosOcupados;
	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {
		boolean partidoTerminado = false;
		if (hayGanador() || noExistenCasillerosVacios()) {
			partidoTerminado = true;
		}

		return partidoTerminado;
	}

	/** POST; indica si existe algun casillero vacio */
	private boolean noExistenCasillerosVacios() {
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

	/** post: indica si el juego terminó y tiene un ganador. */
	public boolean hayGanador() {

		boolean alguienGano = false;
		for (int i = 0; i < contarColumnas(); i++) {
			for (int j = 0; j < contarFilas(); j++) {
				// vertical
				if (j + 3 < contarFilas()
						&& casilleros[i][j] != Casillero.VACIO
						&& casilleros[i][j] == casilleros[i][j + 1]
						&& casilleros[i][j] == casilleros[i][j + 2]
						&& casilleros[i][j] == casilleros[i][j + 3]) {

					alguienGano = true;
				}
				// horizontal
				if (i + 3 < contarColumnas()
						&& casilleros[i][j] != Casillero.VACIO
						&& casilleros[i][j] == casilleros[i + 1][j]
						&& casilleros[i][j] == casilleros[i + 2][j]
						&& casilleros[i][j] == casilleros[i + 3][j]) {

					alguienGano = true;
				}
				if (i + 3 < contarColumnas() && j + 3 < contarFilas()) {
					//diagonal decreciente "\"
					if (casilleros[i][j] != Casillero.VACIO
							&& casilleros[i][j] == casilleros[i + 1][j + 1]
							&& casilleros[i][j] == casilleros[i + 2][j + 2]
							&& casilleros[i][j] == casilleros[i + 3][j + 3]) {

						alguienGano = true;
					}
					//diagonal creciente "/" 
					if (casilleros[i][j + 3] != Casillero.VACIO
							&& casilleros[i][j + 3] == casilleros[i + 1][j + 2]
							&& casilleros[i][j + 3] == casilleros[i + 2][j + 1]
							&& casilleros[i][j + 3] == casilleros[i + 3][j]) {

						alguienGano = true;
					}
				}
			}
		}

		return alguienGano;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {
		String ganador = "Empataron";
		if (hayGanador()) {
			if (jugadorActual == jugadorRojo) {
				ganador = jugadorVerde;
			} else {
				ganador = jugadorRojo;
			}
		}

		return ganador;
	}
}
