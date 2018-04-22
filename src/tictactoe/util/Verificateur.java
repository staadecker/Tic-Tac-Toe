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
public class Verificateur implements ChangeListener<Boite.Status> {
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

    private int boiteVide;

    @SuppressWarnings("ConstantConditions")
    public Verificateur(@NotNull GagnantListener listener, @NotNull StructurePlateau<ReadOnlyObjectProperty<Boite.Status>> statusBoite) {
        this.listener = listener;
        this.boiteVide = statusBoite.size();

        //Pour chaque rangée créé un vérificateur de ligne
        Iterator<List<ReadOnlyObjectProperty<Boite.Status>>> iteratorRangee = statusBoite.iteratorRangee();

        while (iteratorRangee.hasNext()) {
            new VerificateurLigne(listener, iteratorRangee.next());
        }

        //Créé un vérificateur de ligne pour chaque colonne
        Iterator<List<ReadOnlyObjectProperty<Boite.Status>>> iteratorColonne = statusBoite.iteratorColonne();

        while (iteratorColonne.hasNext()) {
            new VerificateurLigne(listener, iteratorColonne.next());
        }

        //Crée un vérificateur de ligne pour les deux diagonales
        new VerificateurLigne(listener, statusBoite.getDiagonaleGaucheDroit());
        new VerificateurLigne(listener, statusBoite.getDiagonaleDroiteGauche());

        for (Position position : statusBoite) {
            //Necessaire pour que le compter boiteVide commence avec le bon chiffre
            if (statusBoite.get(position).get() != Boite.Status.VIDE) {
                boiteVide--;
            }

            //Ajouter un listener pour être notifié quand les boites changent pour pouvoir détecter les égalités
            statusBoite.get(position).addListener(this);
        }
    }

    /**
     * Appelé quand une boite change d'état
     */
    @Override
    public void changed(ObservableValue<? extends Boite.Status> observable, Boite.Status oldValue, Boite.Status newValue) {
        //Si la boite était vide (et ne l'est plus) soustraire 1 au compter
        if (oldValue == Boite.Status.VIDE) {
            boiteVide--;
        }

        //Si la boite devient vide ajouter 1 au compter
        if (newValue == Boite.Status.VIDE) {
            boiteVide++;
        }

        //Si aucunes boites est vide, c'est une égalité
        if (boiteVide == 0) {
            listener.notifierEgalite();
        }
    }

    /**
     * Object qui vérifie un ligne pour des gagnants dans une ligne (par example des colonnes ou des rangée)
     */
    class VerificateurLigne implements ChangeListener<Boite.Status> {
        //La liste de boites formant la ligne
        @NotNull
        private final List<ReadOnlyObjectProperty<Boite.Status>> ligne;

        //Le listener à notifier si il y a un gagnant
        @NotNull
        private final GagnantListener listener;

        VerificateurLigne(@NotNull GagnantListener gagnantListener, List<ReadOnlyObjectProperty<Boite.Status>> ligne) {
            this.listener = gagnantListener;
            this.ligne = ligne;

            //Se faire notifier si une des boites dans la ligne change
            for (ReadOnlyObjectProperty<Boite.Status> etatBoite : ligne) {
                etatBoite.addListener(this);
            }
        }

        /**
         * Appelé quand une des boites dans la ligne change
         */
        @Override
        public void changed(ObservableValue<? extends Boite.Status> observable, Boite.Status oldValue, Boite.Status newValue) {
            if (newValue == Boite.Status.VIDE) return; //Si la boite est devenu vide aucun gagnant

            //Vérifier quand chaque boite dans la ligne est identique (tous des X ou tous de O)
            for (ReadOnlyObjectProperty<Boite.Status> caseAVerifier : ligne) {
                if (caseAVerifier.get() != newValue) return; //Si non identique pas de gagnants

            }

            //Si identique notifier le listener du gagnant
            if (newValue == Boite.Status.CROIX) {
                listener.notifierGagnantX();
            } else {
                listener.notifierGagnantO();
            }
        }

    }
}
