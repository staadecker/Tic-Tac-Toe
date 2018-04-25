# Tic Tac Toe

Jeu de tic tac toe à deux joueurs utilisant JavaFX (avec FXML).

## Architecture

Utilise le MVP (Model-View-Presenter) Pattern.<

### Le Model 
Le model est définit par la class Jeu et est indépendant de l'interface graphique.
Le model définit le status de cases, à qui le tour et le status du jeu (Egalité, gagnant, etc.)

### Le Presenter (Le controlleur)

Le presenter ou controlleur contrôle l'interface graphique en fonction du jeu.
Il s'assure que l'interface graphique représentent toujours le modèle du Jeu.

