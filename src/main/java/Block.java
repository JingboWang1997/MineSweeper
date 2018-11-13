import java.awt.*;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Block {

	private boolean revealed;
	private boolean isBomb;
	private boolean flagged;
	private int row;
	private int col;
	private int numBomb;
	private ArrayList<Block> adjacencyList; //initialize AFTER the gameboard is finished
	private ImageView view;

	public Block(int row, int col) { //initialize a block
		this.row = row;
		this.col = col;
		revealed = false;
		isBomb = false;
		flagged = false;
		adjacencyList = new ArrayList<>();
	}

//	public ArrayList getAdjacencyList() {
//		return adjacencyList;
//	}

	public void addAdjacent(Block block) {
		adjacencyList.add(block);
	}

	public int getNumBomb() {
		return numBomb;
	}

	public int getRow() {
	    return row;
    }

    public int getCol() {
	    return col;
    }

	public void setNumBomb(int n) {
		numBomb = n;
	}

	public void putBomb() {
		isBomb = true;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public boolean isRevealed() {
	    return revealed;
    }

    public boolean isFlagged() {
	    return flagged;
    }

    public void setRevealed(boolean r) {
	    revealed = r;
    }


	public ImageView getImage() { //call when clicked
		if (revealed || flagged) {
			generateImage();
		} else {
			view = new ImageView(new Image("File:./image/plain.png"));
		}

		view.setOnMouseClicked(event -> {
		    clickAction(event);
		});
		return view;
	}

	private void clickAction(MouseEvent event) {
	    if (GameRunner.getGameStatus()) {
            if(event.getButton() == MouseButton.SECONDARY && !revealed) {
                flagged = !flagged;
                GameRunner.updateBlock(row, col);
            } else if (!flagged) {
                if (isBomb) {
                    GameRunner.gameOver();
                } else if (!revealed) {
                    update();
                } else if (revealed) {
                    int numFlagged = 0;
                    for (Block b: adjacencyList) {
                        if (b.isFlagged()) {
                            numFlagged++;
                        }
                    }
                    if (numFlagged == numBomb) {
                        for (Block b: adjacencyList) {
                            if (!b.isRevealed() && !b.isFlagged()) {
                                if (b.isBomb()) {
                                    GameRunner.gameOver();
                                }
                                b.update();
                            }
                        }
                    }
                }
            }
        }
    }


    private void update() {
	    revealed = true;
        GameRunner.updateBlock(row, col);
	    if (numBomb == 0) {
	        for (Block b: adjacencyList) {
	            if (!b.isRevealed() && !b.isFlagged()) {
                    b.update();
                }
            }
        }
    }

    public void generateImage() {
        if (flagged) {
            view = new ImageView(new Image("File:./image/flag.jpeg"));
		} else if (isBomb) {
            view = new ImageView(new Image("File:./image/bomb.jpeg"));
        } else {
			if (numBomb == 0) {
				view = new ImageView(new Image("File:./image/white.png"));
			} else if (numBomb == 1) {
				view = new ImageView(new Image("File:./image/one.png"));
			} else if (numBomb == 2) {
				view = new ImageView(new Image("File:./image/two.png"));
			} else if (numBomb == 3) {
				view = new ImageView(new Image("File:./image/three.png"));
			} else if (numBomb == 4) {
				view = new ImageView(new Image("File:./image/four.png"));
			} else if (numBomb == 5) {
				view = new ImageView(new Image("File:./image/five.png"));
			} else if (numBomb == 6) {
				view = new ImageView(new Image("File:./image/six.png"));
			} else if (numBomb == 7) {
				view = new ImageView(new Image("File:./image/seven.png"));
			} else if (numBomb == 8) {
				view = new ImageView(new Image("File:./image/eight.png"));
			}
		}
	}
}