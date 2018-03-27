package tictactoe.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;
import tictactoe.CaseClickListener;

public class PlateauDeJeu extends GridPane implements CaseClickListener{

    private final Case[] cases = new Case[9];

    public PlateauDeJeu() {
        super();

        for (int i = 0; i < 9; i++) {
            cases[i] = new Case(this);
            this.add(cases[i], getColonne(i), getRangee(i));
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

    @Override
    public void notifierCaseClicked(Case caseClicked) {
        for (Case caseATester : cases){
            if (caseATester == caseClicked){
                //TODO
                break;
            }
        }
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
