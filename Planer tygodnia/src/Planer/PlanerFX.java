package Planer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.*;
import java.util.Observable;
import java.util.Scanner;


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
    private void Zapisz() throws IOException {
        dzialanienaplikach("Zapisz");
    }

    @FXML
    private void PodgladIEdycja() throws IOException {
        dzialanienaplikach("Podglad");

    }

    @FXML
    private void usun() throws IOException {
        dzialanienaplikach("Usun");
    }
    @FXML
    private void czyszczenie() throws IOException {
        textArea.setText("");
        dzialanienaplikach("Podglad");
    }




    private void dzialanienaplikach(String a) throws IOException {
        String dzien = String.valueOf(comboBox.getValue());
        if (dzien != "null") {
            File file;
            file = new File("src/Planer/Poniedzialek.txt");
            switch (dzien) {
                case "Poniedziałek":
                    file = new File("src/Planer/Poniedzialek.txt");
                    break;
                case "Wtorek":
                    file = new File("src/Planer/Wtorek.txt");
                    break;
                case "Środa":
                    file = new File("src/Planer/Sroda.txt");
                    break;
                case "Czwartek":
                    file = new File("src/Planer/Czwartek.txt");
                    break;
                case "Piątek":
                    file = new File("src/Planer/Piatek.txt");
                    break;
            }
            label.setText("");
            String text = textArea.getText();
            if (a == "Zapisz") {                                                                //zapis do pilku
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                        try {
                            bufferedWriter.write(text+"\n");
                            bufferedWriter.close();
                            label.setTextFill(Color.GREEN);
                            label.setText("Zapisano");
                        } catch (IOException ex) {
                            System.out.print("Wystąpil blad zapisu");
                        }


            } else if (a == "Podglad")                                                         // odczyt pliku
            {
                /*
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {

                    textArea.setText(bufferedReader.readLine());
                    bufferedReader.close();

                } catch (IOException ex) {
                    System.out.print("Problem z otworzeniem pliku");
                }

                System.out.print(comboBox.getValue());*/

                Scanner scanner = new Scanner(file);
                String s="";
                while(scanner.hasNextLine()){
                    s = s +scanner.nextLine()+"\n";
                }
                textArea.setText(s);

            }
            else if (a == "Usun") {
                textArea.setText("");
                label.setTextFill(Color.RED);
                label.setText("Usunięto");
            }
        } else {
            label.setText("Nie wybrales dnia");
        }
    }


}
