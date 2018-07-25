/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trying;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import Kelas.*;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class FXMLDocumentController implements Initializable{
    @FXML
    List <Pengguna> pg = new ArrayList();
    
    @FXML
    private TextField tfCari;
    
    @FXML
    private Label dasar, keyword;
    
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
