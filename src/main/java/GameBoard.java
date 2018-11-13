import java.util.HashSet;
import java.util.Random;

public class GameBoard {
	
	private int rows;
	private int cols;
	private int bombNum;
	private Block[][] grid;
	private HashSet<Block> bombPos;

	private Random rand;

	public GameBoard(int rows, int cols, int bombNum) {
		this.rows = rows;
		this.cols = cols;
		this.bombNum = bombNum;
		grid = new Block[rows][cols]; //data structure representing the grid
		bombPos = new HashSet<>();
		rand = new Random();
		initGameBoard();

	}

	public void initGameBoard() {

		// initialize every cell/Block
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new Block(i,j);
			}
		}

		// put bombNum bombs in the grid with distinct position
		while (bombPos.size() < bombNum) {
			int row = rand.nextInt(rows);
			int col = rand.nextInt(cols);
			if (bombPos.add(grid[row][col])) {
				grid[row][col].putBomb();
			}
		}

		// give every block their adjacent list
		// don't really need this, just count the number of bombs around a non-bomb block 
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				initAdjacencyList(grid[i][j], i, j);
			}
		}
	}

	public void initAdjacencyList(Block current, int row, int col) {
		//init the adjacent list
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = col - 1; j < col + 2; j++) {
				if ((i >= 0 && i < rows)
					&& (j >= 0 && j < cols)
					&& !(i == row && j == col)) {
					current.addAdjacent(grid[i][j]);
					if (grid[i][j].isBomb()) {
						current.setNumBomb(current.getNumBomb() + 1);
					}
				}
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getBombNum() {
		return bombNum;
	}

	public Block getBlock(int row, int col) {
		return grid[row][col];
	}

    public void clearBombPos() {
	    bombPos.clear();
    }

    public HashSet<Block> getBombPos() {
        return bombPos;
    }
}