import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Created by wangjingbo on 6/21/17.
 */
public class GameGrid extends GridPane{

    public GameGrid(GameBoard gBoard) {
        super();
        for (int i = 0; i < gBoard.getRows(); i++) {
            for (int j = 0; j < gBoard.getCols(); j++) {
                ImageView view = gBoard.getBlock(i,j).getImage();
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                this.add(view, j, i);
            }
        }
    }

}
