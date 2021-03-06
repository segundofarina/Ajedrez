package grafica;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;

public class GuardarPartida {
	public static void display(Juego juego, int cantJugadores, ColorPieza color){
		
		Stage stage= new Stage();
		
		TextField file;
		
		Label label= new Label("Ingrese nombre de archivo");
		file= new TextField();
		file.setMaxWidth(300);

		Button guardar= new Button("Guardar");
		guardar.getStyleClass().add("squareButton");
		guardar.setOnAction(e->{
			String fileName = file.getText();
			if(guardar(juego, cantJugadores, color,fileName)){
				stage.close();
			}
			
		});

		Button cancelar= new Button("Cancelar");
		cancelar.getStyleClass().add("squareButton");
		cancelar.setOnAction((e->{
			stage.close();
		}));

		VBox vBox= new VBox(10);
		HBox hBox= new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);

		hBox.getChildren().addAll(guardar,cancelar);
		vBox.getChildren().addAll(label,file,hBox);
		stage.setHeight(150);
		stage.setWidth(400);
		Scene scene= new Scene(vBox);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(true);
		stage.showAndWait();
	}
	
	private static boolean guardar(Juego juego, int cantJugadores, ColorPieza color, String nombreFile){
		nombreFile = nombreFile+".chess";
		
		try{
			FileOutputStream file = new FileOutputStream(nombreFile,false);
			ObjectOutputStream juegoGuardado = new ObjectOutputStream(file);
			juegoGuardado.writeObject(juego);
			juegoGuardado.writeInt(cantJugadores);
			juegoGuardado.writeObject(color);
			juegoGuardado.close();
		}catch(FileNotFoundException e){
			Alerta.display("Oh oh! Algo salio mal...", "El archivo cargado no se puede leer.");
			return false;
		}catch(IOException e){
			Alerta.display("Oh oh! Algo salio mal...", "Se ha producido un error al intentar cargar el archivo.");
			return false;
		}
		return true;
	}
}
