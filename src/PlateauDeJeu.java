import javafx.scene.layout.*;

public class PlateauDeJeu extends GridPane {

    private final Case[][] cases = new Case[3][3];

    public PlateauDeJeu() {
        super();

        for (int rangee = 0; rangee < 3; rangee++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                cases[rangee][colonne] = new Case();
                this.add(cases[rangee][colonne], colonne, rangee);
            }
        }

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setPercentWidth(33);

        this.getColumnConstraints().addAll(columnConstraints,columnConstraints,columnConstraints);


        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setPrefHeight(33);

        this.getRowConstraints().addAll(rowConstraints,rowConstraints,rowConstraints);

        this.setBorder(new Border(new BorderStroke(null, null, null, BorderWidths.FULL)));
    }

    public void mettreO(int rangee, int colonne){
        cases[rangee][colonne].mettreO();
    }

    public void mettreX(int rangee, int colonne){
        cases[rangee][colonne].mettreX();
    }

    public void mettreVide(int rangee, int colonne){
        cases[rangee][colonne].mettreVide();
    }
}
