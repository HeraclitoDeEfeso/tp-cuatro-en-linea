package juego;

import java.io.File;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Aplicación del juego Cuatro en Lí­nea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en Lí­nea";
	
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
	private Stage miEscenario;

	@Override
	public void start(Stage escenarioPrincipal) {
		
		//String path = "C:/Users/Public/Music/Sample Music/Kalimba.mp3";
		//Media media = new Media(new File (path).toURI().toString());
		//MediaPlayer mediaPlayer= new MediaPlayer(media);
		//mediaPlayer.setAutoPlay(true);
		//MediaView mediaView = new MediaView(mediaPlayer);


		crearGrilla();

		Scene escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
		miEscenario = escenarioPrincipal;
	}

	private void crearGrilla() {

		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(20);
		grilla.setVgap(20);
		grilla.setStyle("-fx-background-color:black");

		
		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFill(Color.WHITE);
		textoTitulo.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 30));

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

		campoNombreJugadorVerde = new TextField("Verde");
		campoNombreJugadorVerde.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));
		campoNombreJugadorAmarillo = new TextField("amarillo");
		campoNombreJugadorAmarillo.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.REGULAR, 15));
		
		campoNombreJugadorVerde.setStyle("-fx-background-color:lightgreen");
		campoNombreJugadorAmarillo.setStyle("-fx-background-color:yellow");

		campoColumnas = new TextField("7");
		campoFilas = new TextField("7");

		botonIniciar = new Button("Iniciar");
		botonIniciar.setOnAction(new IniciarJuego(this));

	}
	
	/**
	 * post: crea un juego CuatroEnLinea, lo asocia a una Tablero 
	 * 		 y comienza su ejecución.
	 * 
	 */
	public void iniciar() {
		
		String nombreJugadorVerde = campoNombreJugadorVerde.getText();
		String nombreJugadorAmarillo = campoNombreJugadorAmarillo.getText();
		int filas = Integer.parseInt(campoFilas.getText());
		int columnas = Integer.parseInt(campoColumnas.getText());

		CuatroEnLinea juego = new CuatroEnLinea(filas, columnas, nombreJugadorVerde, nombreJugadorAmarillo);

		Tablero tablero = new Tablero(juego);
		tablero.mostrar();
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
