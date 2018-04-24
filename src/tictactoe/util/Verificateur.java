package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;
import tictactoe.StatusJeu;
import tictactoe.gui.BoiteController;

import java.util.Iterator;
import java.util.List;

/**
 * Class qui vérifie pour des gagnants ou en status d'égalité puis si c'est le cas notifie le listener (dans notre cas notifie le jeu)
 * <p>
 * Au lieu de revérifier l'état du plateau après chaque tour, le listener vérifie que la rangée, colonne au diagonale qui a été modifié
 * Pour détecter des égalité, le verificateur compte le nombre de boites remplies (non-vide).
 */
public class Verificateur implements ChangeListener<StatusJeu> {

    private final ReadOnlyObjectWrapper<StatusJeu> status = new ReadOnlyObjectWrapper<>(StatusJeu.EN_PARTIE);

    private final int nombreDeLignes;
    private int nombreDeEgalite = 0;

    @SuppressWarnings("ConstantConditions")
    public Verificateur(@NotNull StructurePlateau<ReadOnlyObjectProperty<BoiteController.Status>> statusBoite) {
        int nombreDeLignes = 0;

        //Pour chaque rangée créé un vérificateur de ligne
        Iterator<List<ReadOnlyObjectProperty<BoiteController.Status>>> iteratorRangee = statusBoite.iteratorRangee();

        while (iteratorRangee.hasNext()) {
            new VerificateurLigne(iteratorRangee.next()).statusProperty().addListener(this);
            nombreDeLignes++;
        }

        //Créé un vérificateur de ligne pour chaque colonne
        Iterator<List<ReadOnlyObjectProperty<BoiteController.Status>>> iteratorColonne = statusBoite.iteratorColonne();

        while (iteratorColonne.hasNext()) {
            new VerificateurLigne(iteratorColonne.next()).statusProperty().addListener(this);
            nombreDeLignes++;
        }

        //Crée un vérificateur de ligne pour les deux diagonales
        new VerificateurLigne(statusBoite.getDiagonaleGaucheDroit()).statusProperty().addListener(this);
        new VerificateurLigne(statusBoite.getDiagonaleDroiteGauche()).statusProperty().addListener(this);
        nombreDeLignes += 2;

        this.nombreDeLignes = nombreDeLignes;

//        for (Position position : statusBoite) {
//            //Necessaire pour que le compter boiteVide commence avec le bon chiffre
//            if (statusBoite.get(position).get() != BoiteController.Status.VIDE) {
//                boiteVide--;
//            }
//
//            //Ajouter un listener pour être notifié quand les boites changent pour pouvoir détecter les égalités
//            statusBoite.get(position).addListener(this);
//        }
    }

    @Override
    public void changed(ObservableValue<? extends StatusJeu> observable, StatusJeu oldValue, StatusJeu newValue) {
        if (oldValue == StatusJeu.EGALITE) {
            nombreDeEgalite--;
        }

        if (newValue == StatusJeu.EGALITE) {
            nombreDeEgalite++;
        }

        switch (newValue) {
            case EN_PARTIE:
            case O_GAGNE:
            case X_GAGNE:
                status.set(newValue);
                return;
        }

        if (nombreDeEgalite == nombreDeLignes){
            status.set(StatusJeu.EGALITE);
        }
    }

    public ReadOnlyObjectProperty<StatusJeu> statusProperty() {
        return status.getReadOnlyProperty();
    }
}
