package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class CuatroEnLineaTest {

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaSinNombreDeJugadorRojo() {
		new CuatroEnLinea(4, 4, null, "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaSinNombreDeJugadorVerde() {
		new CuatroEnLinea(4, 4, "jugadorRojo", "");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaSinNombresDeJugadores() {
		new CuatroEnLinea(4, 4, "", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimo() {
		new CuatroEnLinea(4, 3, "jugadorRojo", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimoSinNombreDeJugadorRojo() {
		new CuatroEnLinea(4, 3, "", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimoSinNombreDeJugadorVerde() {
		new CuatroEnLinea(4, 3, "jugadorRojo", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimoSinNombreDeJugadores() {
		new CuatroEnLinea(4, 3, "", "");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimo() {
		new CuatroEnLinea(3, 4, "jugadorRojo", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimoSinNombreDeJugadorRojo() {
		new CuatroEnLinea(3, 4, "", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimoSinNombreDeJugadorVerde() {
		new CuatroEnLinea(3, 4, "jugadorRojo", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimoSinNombreDeJugadores() {
		new CuatroEnLinea(3, 4, "", "");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferioresAlMinimo() {
		new CuatroEnLinea(3, 3, "jugadorRojo", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferiorAlMinimoSinNombreDeJugadorRojo() {
		new CuatroEnLinea(3, 3, "", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferiorAlMinimoSinNombreDeJugadorVerde() {
		new CuatroEnLinea(3, 3, "jugadorRojo", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferiorAlMinimoSinNombreDeJugadores() {
		new CuatroEnLinea(3, 3, "", "");
	}

	@Test
	public void verificarQueLasFilasDeUnJuegoDeCincoPorOnceSeanCinco() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		assertEquals(5, miJuego.contarFilas());
	}

	@Test
	public void verificarQueLasColumnasDeUnJuegoDeCincoPorOnceSeanOnce() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		assertEquals(11, miJuego.contarColumnas());
	}

	@Test (expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeColumnaMenorAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		miJuego.obtenerCasillero(1, 0);
	}

	@Test (expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeColumnaMayorAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		miJuego.obtenerCasillero(1, 12);
	}
	
	@Test (expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaMenorAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		miJuego.obtenerCasillero(0, 1);
	}

	@Test (expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaMayorAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		miJuego.obtenerCasillero(6, 1);
	}
	
	@Test (expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaYColumnaMayoresAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		miJuego.obtenerCasillero(6, 12);
	}
	
	@Test (expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaYColumnaMenoresAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorRojo",
				"jugadorVerde");
		miJuego.obtenerCasillero(0, 0);
	}
	
	@Test
	public void verificarQueUnJuegoDeCuatroPorCuatroComienzaVacio() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorRojo",
				"jugadorVerde");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertTrue(
						"Verificar que al iniciar un Juego de 4 x 4 la casilla de fila " + (i + 1) + " y columna " + (j + 1) + " esté vacía.",
						miJuego.obtenerCasillero(i + 1, j + 1) == Casillero.VACIO);
			}
		}
	}

//	private Casillero[][] obtenerTablero(CuatroEnLinea miJuego) {
//		Casillero[][] tablero = new Casillero[miJuego.contarFilas()][miJuego
//				.contarColumnas()];
//		for (int i = 0; i < miJuego.contarFilas(); i++) {
//			for (int j = 0; j < miJuego.contarColumnas(); j++) {
//				tablero[i][j] = miJuego.obtenerCasillero(i + 1, j + 1);
//			}
//		}
//		return tablero;
//	}
//
//	@Test
//	public void testSoltarFicha() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTermino() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testHayGanador() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testObtenerGanador() {
//		fail("Not yet implemented");
//	}

}
