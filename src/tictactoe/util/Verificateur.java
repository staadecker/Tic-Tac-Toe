package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;
import tictactoe.gui.Boite;

import java.util.Iterator;
import java.util.List;

/**
 * Class qui vérifie pour des gagnants ou en status d'égalité puis si c'est le cas notifie le listener (dans notre cas notifie le jeu)
 * <p>
 * Au lieu de revérifier l'état du plateau après chaque tour, le listener vérifie que la rangée, colonne au diagonale qui a été modifié
 * Pour détecter des égalité, le verificateur compte le nombre de boites remplies (non-vide).
 */
public class Verificateur implements ChangeListener<VerificateurLigne.Status> {
    /**
     * Interface qui définit le listener qui sera notifié en cas d'égalité ou de gagnant
     */
    public interface GagnantListener {
        void notifierGagnantX();

        void notifierGagnantO();

        void notifierEgalite();
    }

    @NotNull
    private final GagnantListener listener;

    private final int nombreDeLignes;
    private int nombreDeEgalite = 0;

    @SuppressWarnings("ConstantConditions")
    public Verificateur(@NotNull GagnantListener listener, @NotNull StructurePlateau<ReadOnlyObjectProperty<Boite.Status>> statusBoite) {
        this.listener = listener;
        int nombreDeLignes = 0;

        //Pour chaque rangée créé un vérificateur de ligne
        Iterator<List<ReadOnlyObjectProperty<Boite.Status>>> iteratorRangee = statusBoite.iteratorRangee();

        while (iteratorRangee.hasNext()) {
            new VerificateurLigne(iteratorRangee.next()).statusProperty().addListener(this);
            nombreDeLignes++;
        }

        //Créé un vérificateur de ligne pour chaque colonne
        Iterator<List<ReadOnlyObjectProperty<Boite.Status>>> iteratorColonne = statusBoite.iteratorColonne();

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
//            if (statusBoite.get(position).get() != Boite.Status.VIDE) {
//                boiteVide--;
//            }
//
//            //Ajouter un listener pour être notifié quand les boites changent pour pouvoir détecter les égalités
//            statusBoite.get(position).addListener(this);
//        }
    }

    @Override
    public void changed(ObservableValue<? extends VerificateurLigne.Status> observable, VerificateurLigne.Status oldValue, VerificateurLigne.Status newValue) {
        if (oldValue == VerificateurLigne.Status.EGALITE){
            nombreDeEgalite--;
        }

        if (newValue == VerificateurLigne.Status.EGALITE){
            nombreDeEgalite++;
        }

        if (newValue == VerificateurLigne.Status.X_GAGNE){
            listener.notifierGagnantX();
        }

        if (newValue == VerificateurLigne.Status.O_GAGNE){
            listener.notifierGagnantO();
        }

        if (nombreDeEgalite == nombreDeLignes){
            listener.notifierEgalite();
        }
    }
}
