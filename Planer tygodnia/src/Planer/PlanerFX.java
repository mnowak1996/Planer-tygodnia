package Planer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.Observable;


public class PlanerFX {

    ObservableList<String> dniTygodnia = FXCollections.observableArrayList("Poniedziałek", "Wtorek", "Środa",
            "Czwartek", "Piątek");
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Label label;


    @FXML
    private void initialize() {
        comboBox.setItems(dniTygodnia);
    }

    @FXML
    private void Zapisz() throws IOException{
        dzialanienaplikach("Zapisz");
    }
    @FXML
    private void Podglad() throws IOException{
        dzialanienaplikach("Podglad");

    }
    @FXML
    private void usun() throws IOException{
        dzialanienaplikach("Usun");
    }
    @FXML
    private void edytuj() throws IOException {
        dzialanienaplikach("Edytuj");
    }

    private void dzialanienaplikach(String a) throws IOException{
        label.setText("");
        String text = textArea.getText();
        File file = new File("src/Planer/Poniedzialek.txt");
        if(a=="Zapisz") {                                                                //zapis do pilku
            if(text!="") {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                try {
                    bufferedWriter.write(text);
                    bufferedWriter.close();
                    label.setText("Zapisano");
                } catch (IOException ex) {
                    System.out.print("Wystąpil blad zapisu");
                }
            }

        }
        else if(a=="Podglad")                                                         // odczyt pliku
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try{

                textArea.setText(bufferedReader.readLine());
                textArea.setEditable(false);
                bufferedReader.close();

            }catch (IOException ex){
                System.out.print("Problem z otworzeniem pliku");
            }
        }
        else if(a=="Edytuj"){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try{
                textArea.setEditable(true);
                textArea.setText(bufferedReader.readLine());
                bufferedReader.close();

            }catch (IOException ex){
                System.out.print("Problem z otworzeniem pliku");
            }

        }
        else if(a=="Usun")
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            try{
                bufferedWriter.write("");
                bufferedWriter.close();
                textArea.setText("");
                label.setText("Usunięto");
            }catch(IOException ex){
                System.out.print("Problem z zapisem pliku");
            }

        }
    }

}
