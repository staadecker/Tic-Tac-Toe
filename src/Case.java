import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Case extends StackPane {
    public void mettreX(){
        mettreImage("/x.png");
    }

    public void mettreO(){
        mettreImage("/o.png");
    }

    public void mettreVide(){
        this.getChildren().clear();
    }

    private void mettreImage(String nomDuFichier){
        ImageView imageView = new ImageView(this.getClass().getResource(nomDuFichier).toString());
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(imageView);
    }
}
