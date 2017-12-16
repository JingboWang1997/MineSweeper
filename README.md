# MINE SWEEPER

## a basic mine sweeper game

——STRUCTURE——
- /src
	- Block.java
		- block object class for each cell in the grid
	- GameBoard.java
		- 2-d array
	- GameGrid.java
		- generate a grid on the game screen
	- GameRunner.java
		- handle the flow of the game
- /out/production/mine_sweeper
	- java classes
- /image


——FUNCTIONALITY——
- classic mine sweeper functionality
- left click to unopened cell to open
- right click to put flag
- left click number to reveal all unopened cell aroumnd it if all the adjacent bombs 	are covered by flags
- left click flag to unflag
- restart button to restart
- if open a cell with bomb, game over


——RUN——
- compile
  - javac -d out/production/mine_sweeper src/*.java
- run
  - java -cp out/production/mine_sweeper GameRunner


——TO FIX——
- left click on a number with wrong flags around should end the game
- user pick game grid
- add timer
- add a record of best score
- add a picture to display on game over (win and lose)
