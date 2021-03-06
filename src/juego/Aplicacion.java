package juego;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Aplicación del juego Cuatro en Línea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en Línea";

	private GridPane grilla;

	private Label jugadorAmarillo = new Label("Jugador Amarillo: ");
	private Label jugadorVerde = new Label("Jugador verde: ");
	private Label filas = new Label("filas: ");
	private Label columnas = new Label("columnas: ");

	private TextField campoNombreJugadorVerde;
	private TextField campoNombreJugadorAmarillo;

	private TextField campoColumnas;
	private TextField campoFilas;

	private Button botonIniciar;
	private Scene escena;
	private Stage escenarioPrincipal;

	@Override
	public void start(Stage escenarioPrincipal) {

		this.escenarioPrincipal=escenarioPrincipal;

		crearGrilla();

		escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
	}

	private void crearGrilla() {

		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(20);
		grilla.setVgap(20);
		grilla.setStyle("-fx-background-color:black");

		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFill(Color.WHITE);
		textoTitulo.setFont(Font.font("tahoma", FontWeight.BOLD,
				FontPosture.REGULAR, 30));

		crearControles();

		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(jugadorVerde, 0, 2);
		grilla.add(campoNombreJugadorVerde, 1, 2);
		grilla.add(jugadorAmarillo, 0, 1);
		grilla.add(campoNombreJugadorAmarillo, 1, 1);
		grilla.add(filas, 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(columnas, 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(botonIniciar, 0, 5, 2, 1);

		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);
	}

	private void crearControles() {

		jugadorAmarillo.setTextFill(Color.YELLOW);
		jugadorAmarillo.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));

		jugadorVerde.setTextFill(Color.LIGHTGREEN);
		jugadorVerde.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));
		
		filas.setTextFill(Color.WHITE);
		filas.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));

		
		columnas.setTextFill(Color.WHITE);
		columnas.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));
		

		campoNombreJugadorVerde = new TextField("Verde");
		campoNombreJugadorVerde.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));
		campoNombreJugadorVerde.setStyle("-fx-background-color:lightgreen");

		
		campoNombreJugadorAmarillo = new TextField("amarillo");
		campoNombreJugadorAmarillo.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));
		campoNombreJugadorAmarillo.setStyle("-fx-background-color:yellow");

		campoColumnas = new TextField("7");
		campoFilas = new TextField("7");

		botonIniciar = new Button("Iniciar");
		botonIniciar.setOnAction(new IniciarJuego(this));

	}

	/**
	 * post: crea un juego CuatroEnLinea, lo asocia a una Tablero y comienza su
	 * ejecución.
	 * 
	 */
	public void iniciar() {

		String nombreJugadorVerde = campoNombreJugadorVerde.getText();
		String nombreJugadorAmarillo = campoNombreJugadorAmarillo.getText();
		int filas = Integer.parseInt(campoFilas.getText());
		int columnas = Integer.parseInt(campoColumnas.getText());
		CuatroEnLinea juego = null;
		try {
			juego = new CuatroEnLinea(filas, columnas, nombreJugadorAmarillo,
					nombreJugadorVerde);
		} catch (Error e) {

			Stage dialogo = new Stage();

			BorderPane panelError = new BorderPane();
			panelError.setPadding(new Insets(10.0));
			Text textoError = new Text(e.getMessage());
			textoError.setFont(Font.font("verdana", FontWeight.BOLD,
					FontPosture.ITALIC, 22.0));
			textoError.setFill(Color.WHITE);

			panelError.setTop(textoError);
			panelError.setStyle("-fx-background-color:black");

			Scene escenaError = new Scene(panelError);

			dialogo.setScene(escenaError);
			dialogo.initOwner(escena.getWindow());
			dialogo.initModality(Modality.WINDOW_MODAL);
			dialogo.setResizable(false);
			dialogo.showAndWait();

		}

		Tablero tablero = new Tablero(juego);
		tablero.mostrar();
		escenarioPrincipal.close();
		
	}


	public static void main(String[] args) {

		launch(args);
	}
}

