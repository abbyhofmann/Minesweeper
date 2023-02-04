# Simple Minesweeper Game 

**Description**<br/> 
- This is a command-line-interface implementation of the classic minesweeper logic game. 
- The rectangular board is represented as a 2d array of cells, where each cell has a cell state 
and a number of adjacent mines. A cell can be classified as a Mine, Expanded, or Unexpanded. The 
Mine cells are to be avoided. A cell is Expanded if it has been selected by the user and has 
adjacent mines or is adjacent to a selected cell and itself has no adjacent mines. A cell is 
unexpanded if it falls outside those two categories. 
- I have implemented the Model-View-Controller design patter to separate the various functionalities 
and logic of the game, as well as to allow for more versatility in enhancing the game in future 
iterations. For example, having a separate View interface would allow for a seamless integration of 
an upgraded interface, such as a graphical-user interface. 
- One challenge of designing this game was to figure out how to best represent the Cells and their 
changing states as the game progresses. To keep things relatively simple, I restricted the game 
states to Mine, Expanded, and Unexpanded, where the cells initialized as Mines will stay Mines for 
the duration of the game and only ever have 0 adjacent mines, whereas those initialized as
Unexpanded will only ever have the possibility to switch to occupy the Expanded state. 
- Another difficulty in designing this game was implementing a user-friendly interface. When this 
game is played on Google, the user can physically click on cells and mark others with flags. 
Supporting this functionality would require a more complex interface, but I wanted to focus on the 
logic of the back-end of the game. So, to allow for a command-line interface, I designed the game 
around user input with rows and column entries representing the cell a user would "click" on. The 
board itself is printed in the command-line as a display of numbers and underscores. The underscores
represent unknown cells (either Unexpanded or Mines), and the numbers represent cells that have been 
Expanded, with the number corresponding to the number of adjacent mines so the user can use logic to 
figure out where the mines must be located. Future iterations of this game would include an upgraded 
user interface to make for more engaging and intuitive play. <br/><br/>

**Installation**<br/> 
This program doe snot require installation. <br/><br/>
**How to Use**<br/> 
1. Open the project in a Java IDE, such as Intellij. 
2. Create a new run configuration.
   1. Edit Configurations --> Add New Configuration: select "Application" 
   2. Add a configuration name of your choosing 
   3. Select "MineSweeperMain" for the main class
   4. Enter program arguments 
      1. Default game: default 
      2. Custom game: custom -rows <# of rows> -columns <# of columns> -mines <# of mines> 
   5. Apply 
3. Run the main method in the MineSweeperMain file. 
4. In the command line, enter an integer for row and an integer for column (separated by a space) 
to make a move. 
5. To quit at any point, enter "Q" or "q" in the command line. 