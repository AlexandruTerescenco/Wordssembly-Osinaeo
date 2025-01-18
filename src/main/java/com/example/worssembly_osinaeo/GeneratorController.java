package com.example.worssembly_osinaeo;

import com.example.worssembly_osinaeo.creators.wordCreator;
import com.example.worssembly_osinaeo.word.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Random;


public class GeneratorController {
    public TextArea text;

    public Button button_generate;

    public Pane chck_Pane;

    public CheckBox chck_Box;


    @FXML
    public void onGenerateButtonClick(ActionEvent actionEvent) {

        if(chck_Box == null) throw new IllegalStateException("Select one of the options above!");

        wordCreator wordGenerator = new wordCreator();
        Word word = wordGenerator.createObject();
        boolean wasRandom = false;

        if(Objects.equals(chck_Box.getId(), "chck_Random") || wasRandom) {
            chck_Box.setId(random_CheckBox());
            wasRandom = true;
        }

        switch(chck_Box.getId()) {
            case "chck_Noun" :
                word.setType("Noun"); break;
            case "chck_Adjective" :
                word.setType("Adjective"); break;
            case "chck_Verb" :
                word.setType("Verb"); break;
            case "chck_Prep" :
                word.setType("Preposition");
        }

        word.word = word.assemble();
        word.word += Objects.equals(word.type, "Verb") ? "ō" : "o";

        text.appendText(word.word + " - " + word.type + "\n");

        chck_Box.setId(wasRandom ? "chck_Random" : chck_Box.getId());
        wasRandom = false;
    }

    @FXML
    public void set_Selected(ActionEvent event) {
        if(chck_Box != null) chck_Box.setSelected(false);
        chck_Box = (CheckBox) event.getSource();
    }

    private String random_CheckBox() {
        Random rnd = new Random();
        int nr = rnd.nextInt(1, 5);

        return switch (nr) {
            case 1 -> "chck_Noun";
            case 2 -> "chck_Adjective";
            case 3 -> "chck_Verb";
            case 4 -> "chck_Prep";
            default -> throw new IllegalStateException("Unexpected value: " + nr);
        };
    }

}