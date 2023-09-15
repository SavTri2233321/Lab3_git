package com.example.lab33;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


public class HelloApplication extends Application {

    int NbDeBiscuit = 0;
    double nivDeLamelioration = 1;
    int valeurAjoute = 1;
    double durer = 5;
    int valeurAjouteTimeLine=1;
    @Override
    public void start(Stage stage) throws IOException {

        //Creation bouton amelioration
        int[] valeurAmelioration = {10,10,10,0,0};
        Button bouttonAmelioration1 = new Button("      Augmenter le nombre de biscuit de +1       ");
        Button bouttonAmelioration2 = new Button("Augmenter le nombre de biscuit du clic auto de +1");
        Button bouttonAmelioration3 = new Button("           Ajouter un clic auto 1/60sec          ");
        Button bouttonAmelioration4 = new Button("                 Coup  Critique                  ");
        Button bouttonAmelioration5 = new Button("                  Amelioration                   ");

        //Creation essentiel
        Button bouttonBiscuit = new Button("Biscuit");
        Button bouttonAmelioration = new Button("Amelioration");
        Button bouttonPrincipale = new Button("Principale");
        Label nbDeBiscuitPossedePrincipale = new nbDeBiscuitPossede(NbDeBiscuit);
        Label nbDeBiscuitPossedeAmelioration = new nbDeBiscuitPossede(NbDeBiscuit);

        //Creation clicAuto
        Timeline clicAuto = new Timeline();
        clicAuto.setCycleCount(Animation.INDEFINITE);
        AtomicBoolean activer = new AtomicBoolean(false);

        //Construction de la page principale
        Group menuPrincipale = new Group(bouttonBiscuit,nbDeBiscuitPossedePrincipale,bouttonAmelioration);
        Scene scenePrincipale = new Scene(menuPrincipale);
        Group menuAmelioration = new Group(bouttonPrincipale,nbDeBiscuitPossedeAmelioration,bouttonAmelioration1,bouttonAmelioration2,bouttonAmelioration3,bouttonAmelioration4,bouttonAmelioration5);
        Scene sceneAmelioration = new Scene(menuAmelioration);

        //Grandeur de la page
        stage.setWidth(700);
        stage.setHeight(700);

        //Bouton pour augmenter le nombre de biscuits
        bouttonBiscuit.setScaleX(4);
        bouttonBiscuit.setScaleY(4);
        bouttonBiscuit.setTranslateX(300);
        bouttonBiscuit.setTranslateY(300);
        bouttonBiscuit.setOnAction(actionEvent -> {
            NbDeBiscuit += valeurAjoute;
            nbDeBiscuitPossedePrincipale.setText("Nombre de bicuits : " + NbDeBiscuit);
            stage.setScene(scenePrincipale);

        });
        //Bouton pour changer à la scene amélioration
        bouttonAmelioration.setScaleX(2);
        bouttonAmelioration.setScaleY(2);
        bouttonAmelioration.setTranslateX(535);
        bouttonAmelioration.setTranslateY(610);
        bouttonAmelioration.setOnAction(actionEvent -> {
            nbDeBiscuitPossedeAmelioration.setText("Nombre de bicuits : " + NbDeBiscuit);
            stage.setScene(sceneAmelioration);

        });
        //Bouton pour changer à la scene principale
        bouttonPrincipale.setScaleX(2);
        bouttonPrincipale.setScaleY(2);
        bouttonPrincipale.setTranslateX(535);
        bouttonPrincipale.setTranslateY(610);
        bouttonPrincipale.setOnAction(actionEvent -> {
            nbDeBiscuitPossedePrincipale.setText("Nombre de bicuits : " + NbDeBiscuit);
            stage.setScene(scenePrincipale);
        });
        //Bouton pour amelioration 1
        bouttonAmelioration1.setScaleX(2);
        bouttonAmelioration1.setScaleY(2);
        bouttonAmelioration1.setTranslateX(150);
        bouttonAmelioration1.setTranslateY(100);
        Label prixAmelioration1 = new Label("Prix : " + valeurAmelioration[0]);
        menuAmelioration.getChildren().add(prixAmelioration1);
        prixAmelioration1.setScaleX(2);
        prixAmelioration1.setScaleY(2);
        prixAmelioration1.setTranslateX(40);
        prixAmelioration1.setTranslateY(145);
        bouttonAmelioration1.setOnAction(actionEvent -> {
            if (valeurAmelioration[0] <= NbDeBiscuit) {
                NbDeBiscuit -= valeurAmelioration[0];
                valeurAjoute += 1;
                valeurAmelioration[0] *= 1.6;
                prixAmelioration1.setText("Prix : " + valeurAmelioration[0]);
                nbDeBiscuitPossedeAmelioration.setText("Nombre de bicuits : " + NbDeBiscuit);
                stage.setScene(sceneAmelioration);
            }
        });

        //Bouton pour amelioration 2
        bouttonAmelioration2.setScaleX(2);
        bouttonAmelioration2.setScaleY(2);
        bouttonAmelioration2.setTranslateX(160);
        bouttonAmelioration2.setTranslateY(190);
        Label prixAmelioration2 = new Label("Prix : " + valeurAmelioration[1]);
        menuAmelioration.getChildren().add(prixAmelioration2);
        prixAmelioration2.setScaleX(2);
        prixAmelioration2.setScaleY(2);
        prixAmelioration2.setTranslateX(40);
        prixAmelioration2.setTranslateY(235);
        bouttonAmelioration2.setOnAction(actionEvent -> {
            if (valeurAmelioration[1] <= NbDeBiscuit && activer.get()) {
                NbDeBiscuit -= valeurAmelioration[1];
                valeurAjouteTimeLine +=1;
                valeurAmelioration[1] *= 1.6;
                clicAuto.getKeyFrames().removeAll();
                clicAuto.getKeyFrames().add(this.creerKeyFrame(nbDeBiscuitPossedeAmelioration));
                prixAmelioration2.setText("Prix : " + valeurAmelioration[1]);
                nbDeBiscuitPossedeAmelioration.setText("Nombre de bicuits : " + NbDeBiscuit);
                stage.setScene(sceneAmelioration);
                clicAuto.play();
            }

        });


        //Bouton pour amelioration 3
        bouttonAmelioration3.setScaleX(2);
        bouttonAmelioration3.setScaleY(2);
        bouttonAmelioration3.setTranslateX(134);
        bouttonAmelioration3.setTranslateY(280);
        Label prixAmelioration3 = new Label("Prix : " + valeurAmelioration[2]);
        menuAmelioration.getChildren().add(prixAmelioration3);
        prixAmelioration3.setScaleX(2);
        prixAmelioration3.setScaleY(2);
        prixAmelioration3.setTranslateX(45);
        prixAmelioration3.setTranslateY(325);
        bouttonAmelioration3.setOnAction(actionEvent -> {
            if (valeurAmelioration[2] <= NbDeBiscuit) {
                NbDeBiscuit -= valeurAmelioration[2];
                activer.set(true);
                valeurAmelioration[2] *= 1.6;
                nivDeLamelioration += 1;
                clicAuto.getKeyFrames().removeAll();
                clicAuto.getKeyFrames().add(this.creerKeyFrame(nbDeBiscuitPossedeAmelioration));
                clicAuto.play();
                prixAmelioration3.setText("Prix : " + valeurAmelioration[2]);
                nbDeBiscuitPossedeAmelioration.setText("Nombre de bicuits : " + NbDeBiscuit);
                stage.setScene(sceneAmelioration);
            }

        });

        //Bouton pour amelioration 4
        bouttonAmelioration4.setScaleX(2);
        bouttonAmelioration4.setScaleY(2);
        bouttonAmelioration4.setTranslateX(119);
        bouttonAmelioration4.setTranslateY(370);
        Label prixAmelioration4 = new Label("Prix : " + valeurAmelioration[3]);
        menuAmelioration.getChildren().add(prixAmelioration4);
        prixAmelioration4.setScaleX(2);
        prixAmelioration4.setScaleY(2);
        prixAmelioration4.setTranslateX(35);
        prixAmelioration4.setTranslateY(415);

        //Bouton pour amelioration 5
        bouttonAmelioration5.setScaleX(2);
        bouttonAmelioration5.setScaleY(2);
        bouttonAmelioration5.setTranslateX(119);
        bouttonAmelioration5.setTranslateY(460);
        Label prixAmelioration5 = new Label("Prix : " + valeurAmelioration[4]);
        menuAmelioration.getChildren().add(prixAmelioration5);
        prixAmelioration5.setScaleX(2);
        prixAmelioration5.setScaleY(2);
        prixAmelioration5.setTranslateX(35);
        prixAmelioration5.setTranslateY(505);


        //Set la scene
        stage.setTitle("CookieClicker");
        stage.setScene(scenePrincipale);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public KeyFrame creerKeyFrame(Label label) {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durer/nivDeLamelioration),
                actionEvent -> {
                    NbDeBiscuit += valeurAjouteTimeLine;
                    label.setText("Nombre de bicuits : " + NbDeBiscuit);
                });
        return keyFrame;
    }

}