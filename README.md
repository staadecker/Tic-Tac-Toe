# Tic Tac Toe

Two-player tic tac toe game using JavaFX (with FXML).

## Architecture

Uses the [MVP (Model-View-Presenter) Pattern] (https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter).

### The model
The model is defined by the `Game` class and is independent of the GUI.
The model defines the status of boxes, to which the tour and the status of the game (Equality, Winner, etc.)

### The View

The view is defined by FXML files. It's the graphical interface.

### The Controller (The Presentator)

The presenter or controller controls the View (the GUI) according to the model.
It makes sure that the graphical interface always represents the model of the Game.
It also transmits the actions of the user to the model (ex. button pressed, click on box)
