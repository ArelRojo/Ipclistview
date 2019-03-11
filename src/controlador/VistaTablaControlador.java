/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class VistaTablaControlador implements Initializable {

    @FXML
    private TableView<Persona> TablaPersonas;
    @FXML
    private TableColumn<Persona, String> nomCol;
    @FXML
    private TableColumn<Persona, String> apeCol;
    @FXML
    private Button BAddfxID;
    @FXML
    private Button BBorrarfxID;
    private ObservableList<Persona> datos = null;
    @FXML
    private Button Modificar;
    @FXML
    private Button BSalir;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Persona> misdatos;
        misdatos = new ArrayList<>();
        misdatos.add(new Persona("Pepe", "García"));
        misdatos.add(new Persona("María", "Pérez"));
        datos = FXCollections.observableArrayList(misdatos);
        TablaPersonas.setItems(datos); // vinculaci�n entre la vista y el modelo
        nomCol.setCellValueFactory(cellData -> cellData.getValue().NombreProperty());
        apeCol.setCellValueFactory(cellData -> cellData.getValue().ApellidosProperty());
        Modificar.disableProperty().bind(Bindings.equal(-1, TablaPersonas.getSelectionModel().selectedIndexProperty()));
        BBorrarfxID.disableProperty().bind(Bindings.equal(-1, TablaPersonas.getSelectionModel().selectedIndexProperty()));
    }

    @FXML
    private void modificar(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        Parent root = miCargador.load();
        
        Persona p = TablaPersonas.getSelectionModel().getSelectedItem(); 
        VistaPersonaControlador vpc = (VistaPersonaControlador) miCargador.getController();
        vpc.myInitialize(datos, false);
        vpc.initPersona(p);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modificar pesonas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        
    }

    @FXML
    private void addMethod(ActionEvent event) throws IOException {
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        Parent root = miCargador.load();
        Persona p = new Persona("", "");
        VistaPersonaControlador vpc = (VistaPersonaControlador) miCargador.getController();
        vpc.myInitialize(datos, true);
        vpc.initPersona(p);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir pesonas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        
    }

    @FXML
    private void borrarPersona(ActionEvent event) {
        datos.remove(TablaPersonas.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node minodo1 = (Node) event.getSource();
        minodo1.getScene().getWindow().hide();
    }

}


