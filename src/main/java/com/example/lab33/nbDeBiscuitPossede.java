package com.example.lab33;

import javafx.scene.control.Label;

public class nbDeBiscuitPossede extends Label {

    public nbDeBiscuitPossede(int nbDeBiscuit) {
        //Label pour montrer le nombre de biscuits
        super("Nombre de bicuits : " + nbDeBiscuit);
        this.setScaleX(1.5);
        this.setScaleY(1.5);
        this.setTranslateX(35);

    }

}
