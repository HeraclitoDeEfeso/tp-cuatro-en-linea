package juego;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class CuatroEnLineaTest {

	private static final Casillero[][] TABLERO_VACIO = {
			{ Casillero.VACIO,    Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO },
			{ Casillero.VACIO,    Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO },
			{ Casillero.VACIO,    Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO },
			{ Casillero.VACIO,    Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO }};
	
	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaSinNombreDeJugadorAmarillo() {
		new CuatroEnLinea(4, 4, null, "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaSinNombreDeJugadorVerde() {
		new CuatroEnLinea(4, 4, "jugadorAmarillo", "");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaSinNombresDeJugadores() {
		new CuatroEnLinea(4, 4, "", null);
	}
	
	@Test(expected = Error.class)
	public void losNombresDeLosJugadoresNoPuedenSerExactamenteIguales() {
		new CuatroEnLinea(4, 4, "Amarillo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void losNombresDeLosJugadoresNoPuedenSerIgualesAPesarDeLasMayusculas() {
		new CuatroEnLinea(4, 4, "amarillo", "Amarillo");
	}
	
	@Test(expected = Error.class)
	public void elNombreDelPrimerJugadorNoPuedeSerMenoresA3Letras() {
		new CuatroEnLinea(4, 4, "a", "Amarillo");
	}

	@Test(expected = Error.class)
	public void elNombreDelSegundoJugadorNoPuedeSerMenoresA3Letras() {
		new CuatroEnLinea(4, 4, "Verde", "v");
	}
	
	@Test(expected = Error.class)
	public void losNombresDeLosJugadoresNoPuedenSerMenoresA3Letras() {
		new CuatroEnLinea(4, 4, "a", "v");
	}
	
	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimo() {
		new CuatroEnLinea(4, 3, "jugadorAmarillo", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimoSinNombreDeJugadorAmarillo() {
		new CuatroEnLinea(4, 3, "", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimoSinNombreDeJugadorVerde() {
		new CuatroEnLinea(4, 3, "jugadorAmarillo", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeColumnasInferiorAlMinimoSinNombreDeJugadores() {
		new CuatroEnLinea(4, 3, "", "");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimo() {
		new CuatroEnLinea(3, 4, "jugadorAmarillo", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimoSinNombreDeJugadorAmarillo() {
		new CuatroEnLinea(3, 4, "", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimoSinNombreDeJugadorVerde() {
		new CuatroEnLinea(3, 4, "jugadorAmarillo", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasInferiorAlMinimoSinNombreDeJugadores() {
		new CuatroEnLinea(3, 4, "", "");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferioresAlMinimo() {
		new CuatroEnLinea(3, 3, "jugadorAmarillo", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferiorAlMinimoSinNombreDeJugadorAmarillo() {
		new CuatroEnLinea(3, 3, "", "jugadorVerde");
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferiorAlMinimoSinNombreDeJugadorVerde() {
		new CuatroEnLinea(3, 3, "jugadorAmarillo", null);
	}

	@Test(expected = Error.class)
	public void crearUnCruatroEnLineaConCantidadDeFilasYDeColumnasInferiorAlMinimoSinNombreDeJugadores() {
		new CuatroEnLinea(3, 3, "", "");
	}

	@Test
	public void verificarQueLasFilasDeUnJuegoDeCincoPorOnceSeanCinco() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		assertEquals(5, miJuego.contarFilas());
	}

	@Test
	public void verificarQueLasColumnasDeUnJuegoDeCincoPorOnceSeanOnce() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		assertEquals(11, miJuego.contarColumnas());
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeColumnaMenorAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(1, 0);
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeColumnaMayorAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(1, 12);
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaMenorAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(0, 1);
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaMayorAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(6, 1);
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaYColumnaMayoresAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(6, 12);
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaYColumnaMenoresAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(0, 0);
	}

	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaMenorAUnoYColumnaMayorAlLimite() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(0, 12);
	}
	
	@Test(expected = Error.class)
	public void verificarQueNoPermitaAccederCasilleroDeLineaMayorAlLimiteYColumnaMenorAUno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(5, 11, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.obtenerCasillero(6, 0);
	}
	
	@Test
	public void verificarQueUnJuegoDeCuatroPorCuatroComienzaVacio() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		assertArrayEquals(TABLERO_VACIO, obtenerTablero(miJuego));
	}

	@Test
	public void noSePuedeSoltarFichaEnColumnaNegativa() {

		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		Casillero[][] tableroActual = obtenerTablero(miJuego);

		miJuego.soltarFicha(-1);

		assertArrayEquals(tableroActual, obtenerTablero(miJuego));
	}

	@Test
	public void noSePuedeSoltarFichaEnColumnaCero() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		Casillero[][] tableroActual = obtenerTablero(miJuego);

		miJuego.soltarFicha(0);

		assertArrayEquals(tableroActual, obtenerTablero(miJuego));
	}

	@Test
	public void noSePuedeSoltarFichaEnUnaColumnaMayorALasDefinidasAlCrearElJuego() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		Casillero[][] tableroAntes = obtenerTablero(miJuego);
		
		miJuego.soltarFicha(5);
		Casillero[][] tableroDespues = obtenerTablero(miJuego);
		
		assertArrayEquals(tableroAntes, tableroDespues);
		
	}

	@Test
	public void verificarQueElPrimeroEnJugarSeaElPrimerJugadorDeFichasAmarillas() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.soltarFicha(1);
		assertEquals(Casillero.AMARILLO, miJuego.obtenerCasillero(4, 1));
	}

	@Test
	public void verificarQueElSegundoEnJugarSeaElJugadorDeFichasVerdes() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.soltarFicha(1);
		miJuego.soltarFicha(2);
		assertEquals(Casillero.VERDE, miJuego.obtenerCasillero(4, 2));
	}

	@Test
	public void verificarQueAlJugarEnLaMismaColumnaLasFichasSeApilen() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.soltarFicha(1);
		miJuego.soltarFicha(1);
		assertTrue(miJuego.obtenerCasillero(4, 1) == Casillero.AMARILLO
				&& miJuego.obtenerCasillero(3, 1) == Casillero.VERDE);
	}

	@Test 
	public void noSePuedenSoltarMasFichasEnUnaColumnaLlena() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 1, 1 }, miJuego);
		Casillero[][] tableroPrevio = obtenerTablero(miJuego);
		miJuego.soltarFicha(1);
		assertTrue(Arrays.deepEquals(tableroPrevio, obtenerTablero(miJuego)));
	}

	@Test 
	public void alSoltarMalUnaFichaNoPierdeElTurno() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 1, 1 }, miJuego);
		miJuego.soltarFicha(1);
		miJuego.soltarFicha(2);
		Casillero[][] tableroEsperado = {
				{ Casillero.VERDE,    Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO },
				{ Casillero.AMARILLO, Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO },
				{ Casillero.VERDE,    Casillero.VACIO,    Casillero.VACIO, Casillero.VACIO },
				{ Casillero.AMARILLO, Casillero.AMARILLO, Casillero.VACIO, Casillero.VACIO } };
		assertArrayEquals(tableroEsperado, obtenerTablero(miJuego));
	}

	@Test
	public void alComenzarElJuegoNoTermino() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		assertFalse(miJuego.termino());
	}

	@Test
	public void despuesDeUnaPartidaConElPrimerJugadorGanadorElJuegoTermino() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 2, 2, 3, 3, 4 }, miJuego);
		assertTrue(miJuego.termino());
	}

	@Test
	public void despuesDeUnaPartidaConElSegundoJugadorGanadorElJuegoTermino() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 1, 2, 1, 2, 3, 2 }, miJuego);
		assertTrue(miJuego.termino());
	}

	@Test
	public void despuesDeUnaPartidaEmpatadaElJuegoTermino() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(
				new int[] { 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 3, 4, 2, 1, 4, 3 },
				miJuego);
		assertTrue(miJuego.termino());
	}

	@Test
	public void alComenzarElJuegoNoHayGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		assertFalse(miJuego.hayGanador());
	}

	@Test
	public void despuesDeUnaPartidaConElPrimerJugadorGanadorHayGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 2, 2, 3, 3, 4 }, miJuego);
		assertTrue(miJuego.hayGanador());
	}

	@Test
	public void despuesDeUnaPartidaConElSegundoJugadorGanadorHayGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 1, 2, 1, 2, 3, 2 }, miJuego);
		assertTrue(miJuego.hayGanador());
	}

	@Test
	public void despuesDeUnaPartidaEmpatadaNoHayGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(
				new int[] { 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 3, 4, 2, 1, 4, 3 },
				miJuego);
		assertFalse(miJuego.hayGanador());
	}

	@Test
	public void alComenzarElJuegoNoSeObtieneElNombreDeUnGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		assertNull(miJuego.obtenerResultado());
	}

	@Test
	public void despuesDeUnaPartidaConElPrimerJugadorGanadorSeObtieneSuNombreComoGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 2, 2, 3, 3, 4 }, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test 
	public void despuesDeUnaPartidaConElSegundoJugadorGanadorSeObtieneSuNombreComoGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 1, 2, 1, 2, 3, 2 }, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorVerde"));
	}

	@Test  
	public void despuesDeUnaPartidaEmpatadaNoSeObtieneElNombreDeUnGanador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(
				new int[] { 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 3, 4, 2, 1, 4, 3 },
				miJuego);
		assertNull(miJuego.obtenerResultado());
	}
	
	@Test
	public void alReiniciarElTableroEstaVacio() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.soltarFicha(1);
		miJuego.vaciarCasillerosRecomenzarTurnos();
		assertArrayEquals(TABLERO_VACIO, obtenerTablero(miJuego));
	}
	
	@Test
	public void alReiniciarComienzaElPrimerJugador() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		miJuego.soltarFicha(1);
		Casillero fichaPrimerJugador = miJuego.obtenerCasillero(4, 1);
		miJuego.vaciarCasillerosRecomenzarTurnos();
		miJuego.soltarFicha(2);		
		assertEquals(fichaPrimerJugador, miJuego.obtenerCasillero(4, 2));
	}
	
	@Test  
	public void ganaElPrimerJugadoPrimeraFilaHorizonta() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 2, 2, 3, 3, 4 }, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test  
	public void ganaElPrimerJugadoSegundaFilaHorizonta() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 2, 3, 3, 4, 4, 4, 1}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}


	@Test  
	public void ganaElPrimerJugadoTerceraFilaHorizonta() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 3, 4}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test  
	public void ganaElSegundoJugadorCuartaFilaHorizontal() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 3, 4, 1, 3, 4, 4, 1, 1, 2, 4, 2, 2, 3, 3}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorVerde"));
	}
	@Test  
	public void ganaElPrimerJugadoPrimerColumnaVertical() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 1, 2, 1, 2, 1}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test  
	public void ganaElPrimerJugadoSegundaColumnaVertical() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 2, 1, 2, 1, 2, 1, 2}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test  
	public void ganaElPrimerJugadoTercerColumnaVertical() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 3, 2, 3, 2, 3, 2, 3}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test  
	public void ganaElPrimerJugadoCuartaColumnaVertical() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 4, 1, 4, 1, 4, 1, 4}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}
	
	@Test  
	public void ganaElPrimerJugadoDiagonalDecrecienteHaciaDerecha() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 4, 3, 3, 2, 2, 1, 2, 1, 1, 4, 1}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	@Test  
	public void ganaElPrimerJugadoDiagonalCrecienteHaciaDerecha() {
		CuatroEnLinea miJuego = new CuatroEnLinea(4, 4, "jugadorAmarillo",
				"jugadorVerde");
		jugarPartida(new int[] { 1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4}, miJuego);
		assertTrue(miJuego.obtenerResultado().equals("jugadorAmarillo"));
	}

	private Casillero[][] obtenerTablero(CuatroEnLinea miJuego) {
		Casillero[][] tablero = new Casillero[miJuego.contarFilas()][miJuego
				.contarColumnas()];
		for (int i = 0; i < miJuego.contarFilas(); i++) {
			for (int j = 0; j < miJuego.contarColumnas(); j++) {
				tablero[i][j] = miJuego.obtenerCasillero(i + 1, j + 1);
			}
		}
		return tablero;
	}

	private void jugarPartida(int[] columnasJugadas, CuatroEnLinea miJuego) {
		for (int i = 0; i < columnasJugadas.length; i++) {
			miJuego.soltarFicha(columnasJugadas[i]);
		}
	}
}
