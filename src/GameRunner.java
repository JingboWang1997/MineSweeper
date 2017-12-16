import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashSet;

public class GameRunner extends Application {

	private static GameGrid gBoardUI;
	private static GameBoard gBoard;
	private static BorderPane window;
	private static VBox options;
	private static Button restart;
	private static Stage stage;
	private static boolean gameStatus;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gameStatus = true; //?
        stage = primaryStage; //take the stage variable
        setUp(19, 30, 99); // set up game board 19x30 with 99 bombs and button
        gBoardUI = new GameGrid(gBoard); // init grid UI
        window.setCenter(gBoardUI);
        stage.setScene(new Scene(window));
        stage.show();
    }

    private void setUp(int rows, int cols, int bombNum) {
        gBoard = new GameBoard(rows,cols,bombNum); //init a 2-d array
        restart = new Button("RESTART");
        restart.setOnMouseClicked(e -> {
            stage.setScene(new Scene(new StackPane(new ImageView(new Image("File:./image/loading.png")))));
            stage.show();

            renewBoard(rows, cols, bombNum);

            window.setCenter(gBoardUI);
            stage.setScene(window.getScene());
            stage.show();

            gameStatus = true;
        });
        window = new BorderPane();
        options = new VBox(10, restart);
        window.setRight(options);
    }

    private void renewBoard(int rows, int cols, int bombNum) {
        gBoard = new GameBoard(rows, cols, bombNum);
        gBoardUI = new GameGrid(gBoard);
        window.setCenter(gBoardUI);
        stage.setScene(window.getScene());
        stage.show();
    }

	public static void updateBlock(int row, int col) {
        ImageView view = gBoard.getBlock(row,col).getImage();
        view.setFitHeight(30);
        view.setPreserveRatio(true);
        gBoardUI.add(view, col, row);
	}

    public static void gameOver() {
        HashSet<Block> bombs = gBoard.getBombPos();
        for (Block b: bombs) {
            b.setRevealed(true);
            updateBlock(b.getRow(), b.getCol());
        }
        gameStatus = false;
    }

    public static boolean getGameStatus() {
        return gameStatus;
    }
}
