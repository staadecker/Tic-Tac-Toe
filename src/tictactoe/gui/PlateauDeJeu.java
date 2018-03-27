package tictactoe.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;
import tictactoe.CaseClickListener;

public class PlateauDeJeu extends GridPane {

    private final Case[] cases = new Case[9];

    public PlateauDeJeu(CaseClickListener listener) {
        super();

        for (int id = 0; id < 9; id++) {
            cases[id] = new Case(id, listener);
            this.add(cases[id], getColonne(id), getRangee(id));
        }

        appliquerConstraints();

        this.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, BorderWidths.FULL)));
    }

    private void appliquerConstraints() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);


        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        rowConstraints.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
    }

    public void setStatus(int id, Case.Status status){
        cases[id].setStatus(status);
    }

    @Contract(pure = true)
    private static int getRangee(int id){
        return (int) Math.floor(id / 3.0);
    }

    @Contract(pure = true)
    private static int getColonne(int id){
        return id % 3;
    }
}
