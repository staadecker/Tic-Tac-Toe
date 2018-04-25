# Tic Tac Toe

Jeu de tic tac toe à deux joueurs utilisant JavaFX (avec FXML).

## Architecture

Utilise le [MVP (Model-View-Presenter) Pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter).

### Le Model 
Le model est définit par la class `Jeu` et est indépendant de l'interface graphique.
Le model définit le status de cases, à qui le tour et le status du jeu (Egalité, Gagnant, etc.)

### Le View

Le view est définit par les fichiers FXML. C'est l'interface graphique.

### Le Controlleur (Le Presenteur)

Le presenter ou controlleur contrôle le View (l'interface graphique) en fonction du model.
Il s'assure que l'interface graphique représente toujours le modèle du Jeu.
Il transmet aussi au modèle les actions de l'utilisateur (button appuyé, click sur case)
