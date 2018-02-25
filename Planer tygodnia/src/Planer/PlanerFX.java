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

/**
 * Created by Michał Nowak
 */

public class PlanerFX {

    ObservableList<String> dniTygodnia = FXCollections.observableArrayList("Poniedziałek", "Wtorek", "Środa",
            "Czwartek", "Piątek");                 // lista dni tygodnia typu Observable list
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Label label;

    /**
     * Metoda która zosyaje automatycznie wywołana przy starcie aplikacji
     * Ustawia ona dni tygodnia jako elementy ComboBoxa
     */
    @FXML
    private void initialize() {
        comboBox.setItems(dniTygodnia);
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku zapisz
     * @throws IOException
     */
    @FXML
    private void Zapisz() throws IOException {
        dzialanienaplikach("Zapisz");
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku usuń
     * @throws IOException
     */
    @FXML
    private void usun() throws IOException {
        dzialanienaplikach("Usun");
    }

    /**
     * Metoda wywoływana po wybraniu dnia tygodnia z ComboBoxa
     * @throws IOException
     */
    @FXML
    private void czyszczenie() throws IOException {
        textArea.setText("");
        dzialanienaplikach("Podglad");
    }


    /**
     * Metoda w której odbywa się otworzenie pliku, wyczyszczenie tekstu, zapisanie tekstu do pliku
     * @param akcja - pole typu String, wykorzystane w instukcjach warunkowych
     * @throws IOException - obsłużenie wyjatku związanego z otworzeniem, zapisem do pliku
     */
    private void dzialanienaplikach(String akcja) throws IOException {
        String dzien = String.valueOf(comboBox.getValue());   // pobieranie nazwy wybranego dnia tygodnia
        if (dzien != "null") {               //sprawdzenie czy wybrano któryś dzień tygodnia
            File file;
            file = new File("src/Planer/Poniedzialek.txt");
            switch (dzien) {   // wybranie pliku z odpowiadającym mu dniem tygodnia
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
            if (akcja == "Zapisz") {                    //zapis do pilku
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
            } else if (akcja == "Podglad")              // odczyt pliku
            {
                Scanner scanner = new Scanner(file);    // utworzenie obiektu typu Scanner i przkazanie w konstruktorze
                                                        // scieżki do pliku
                String s="";                            // wstępna inicjalizacja pola typu String
                while(scanner.hasNextLine()){           // pętla w której łańcuch znaków jest powiększany do
                                                        // ilości znaków w pliku tekstowym
                    s = s +scanner.nextLine()+"\n";
                }
                textArea.setText(s);
            }
            else if (akcja == "Usun") {     // wyczyszczenie pola TextArea
                textArea.setText("");
                label.setTextFill(Color.RED);
                label.setText("Usunięto");
            }
        } else {
            label.setText("Nie wybrales dnia");
        }
    }


}
