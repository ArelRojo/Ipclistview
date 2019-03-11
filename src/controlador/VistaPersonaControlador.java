/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class VistaPersonaControlador implements Initializable {

    
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
//    private boolean guardado = false;
    @FXML
    private TextField Apellido;
    @FXML
    private TextField Nombre;
    private Persona p;
    private ObservableList<Persona> datos;
    @FXML
    private Text text;
    
    private boolean modo;
    
    public VistaPersonaControlador() {
       
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Me acaban de llamar");
    }
    public void myInitialize(ObservableList<Persona> datos,boolean modo){
        
        //IF modo == 0 Actualizar; modo == 1 Nuevo
        this.datos = datos;
         guardar.disableProperty().bind(Apellido.textProperty().isEmpty().or(Nombre.textProperty().isEmpty()));
        
    }
    
   
    public void initPersona(Persona p){
        this.p = p;
      
        Nombre.setText(p.getNombre());
        Apellido.setText(p.getApellidos());
  
    }
//    
//    public boolean Haguardado() {
//    p.setNombre(Nombre.getText());
//    p.setApellidos(Apellido.getText());
//    return guardado ;
//    }
   
    @FXML
    private void buttonSave(ActionEvent event) {
         String s = Nombre.getText();
        s = s.trim();
        if(modo == false){
            System.out.println("Estas modificando");
            this.p.setApellidos(Apellido.getText());
            this.p.setNombre(Nombre.getText());
        } 
        else if(modo == true){
            System.out.println("Estas añadiendo");
            this.datos.add(new Persona(Nombre.getText(),Apellido.getText()));
        }
        
        Node minodo = (Node) event.getSource();
        minodo.getScene().getWindow().hide();
       
        
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node minodo1 = (Node) event.getSource();
        minodo1.getScene().getWindow().hide();
    }

    void myInitialize(ObservableList<Persona> datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
