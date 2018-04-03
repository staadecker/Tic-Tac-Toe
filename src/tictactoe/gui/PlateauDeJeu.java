package tictactoe.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;
import tictactoe.CaseClickListener;

public class PlateauDeJeu extends GridPane {

    private final Boite[] boites = new Boite[9];

    public PlateauDeJeu(CaseClickListener listener) {
        super();

        for (int id = 0; id < 9; id++) {
            boites[id] = new Boite(id, listener);
            this.add(boites[id], getColonne(id), getRangee(id));
        }

        GridPane.setFillHeight(this, true);
        GridPane.setFillWidth(this,true);

        appliquerConstraintes();

        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.FULL)));
    }

    private void appliquerConstraintes() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);


        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        rowConstraints.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
    }

    public void setStatus(int id, Boite.Status status){
        boites[id].setStatus(status);
    }

    public Boite.Status[] getStatus() {
        Boite.Status[] status = new Boite.Status[9];

        for (int i = 0 ; i < 9 ; i ++){
            status[i] = boites[i].getStatus();
        }

        return status;
    }

    @Contract(pure = true)
    static int getRangee(int id){
        return (int) Math.floor(id / 3.0);
    }

    @Contract(pure = true)
    static int getColonne(int id){
        return id % 3;
    }
}