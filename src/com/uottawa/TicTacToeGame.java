package com.uottawa;

public class TicTacToeGame {
    /**
     * The board of the game, stored as a single array.
     */
    private CellValue[] board;


    /**
     * level records the number of rounds that have been
     * played so far. Starts at 0.
     */
    private int level;

    /**
     * gameState records the current state of the game.
     */
    private GameState gameState;


    /**
     * lines is the number of lines in the grid
     */
    private final int lines;

    /**
     * columns is the number of columns in the grid
     */
    private final int columns;


    /**
     * sizeWin is the number of cell of the same type
     * that must be aligned to win the game.
     * For simplicity, it will be always 3 in this assignment.
     */
    private final int sizeWin;


    /**
     * default constructor, for a game of 3x3, which must
     * align 3 cells
     */
    public TicTacToeGame(){
        // your code here
        board=new CellValue[9];
        for (int i = 0; i < 9; i++) {
            board[i]=CellValue.EMPTY;
            System.out.println(board[i]);
        }
        columns = 3;
        lines = 3;
        sizeWin = 3;
        level = 0;
        gameState = GameState.PLAYING;


    }

    /**
     * constructor allowing to specify the number of lines
     * and the number of columns for the game. 3 cells must
     * be aligned.
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     */
    public TicTacToeGame(int lines, int columns){
        // your code here
        board=new CellValue[lines*columns];
        for (int i = 0; i < lines * columns; i++) {
            board[i]=CellValue.EMPTY;
            System.out.println(board[i]);
        }

        this.columns = columns;
        this.lines = lines;
        sizeWin = 3;
        level = 0;
        gameState = GameState.PLAYING;
    }

    /**
     * constructor allowing to specify the number of lines
     * and the number of columns for the game, as well as
     * the number of cells that must be aligned to win.
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     * @param sizeWin
     *  the number of cells that must be aligned to win.
     */
    public TicTacToeGame(int lines, int columns, int sizeWin){
        // your code here
        board=new CellValue[lines*columns];
        for (int i = 0; i < lines * columns; i++) {
            board[i]=CellValue.EMPTY;
        }

        this.columns = columns;
        this.lines = lines;
        this.sizeWin = sizeWin;
        level = 0;
        gameState = GameState.PLAYING;

    }



    /**
     * getter for the variable lines
     * @return
     * 	the value of lines
     */
    public int getLines(){
        // your code here
        return lines;
    }

    /**
     * getter for the variable columns
     * @return
     * 	the value of columns
     */
    public int getColumns(){
        // your code here
        return columns;
    }

    /**
     * getter for the variable level
     * @return
     * 	the value of level
     */
    public int getLevel(){
        // your code here
        return level;
    }


    /**
     * getter for the variable gameState
     * @return
     * 	the value of gameState
     */
    public GameState getGameState(){
        // your code here
        return gameState;
    }

    /**
     * getter for the variable sizeWin
     * @return
     * 	the value of sizeWin
     */
    public int getSizeWin(){
        // your code here

        return sizeWin;
    }

    /**
     * returns the cellValue that is expected next,
     * in other word, which played (X or O) should
     * play next.
     * This method does not modify the state of the
     * game.
     * @return
     *  the value of the enum CellValue corresponding
     * to the next expected value.
     */
    public CellValue nextCellValue(){
        // your code here
        if (level % 2 == 0) {
            return CellValue.X;
        }

        else {
            return CellValue.O;
        }
    }

    /**
     * returns the value  of the cell at
     * index i.
     * If the index is invalid, an error message is
     * printed out. The behaviour is then unspecified
     * @param i
     *  the index of the cell in the array board
     * @return
     *  the value at index i in the variable board.
     */
    public CellValue valueAt(int i) {
        // your code here

        return board[i];
    }

    /**
     * This method is called by the next player to play
     * at the cell  at index i.
     * If the index is invalid, an error message is
     * printed out. The behaviour is then unspecified
     * If the chosen cell is not empty, an error message is
     * printed out. The behaviour is then unspecified
     * If the move is valide, the board is updated, as well
     * as the state of the game.
     * To faciliate testing, it is acceptable to keep playing
     * after a game is already won. If that is the case, the
     * a message should be printed out and the move recorded.
     * the  winner of the game is the player who won first
     * @param i
     *  the index of the cell in the array board that has been
     * selected by the next player
     */
    public void play(int i) {

        //Index out of bound (I = 10 in 3x3 Game)
        if (i > board.length) {
            System.out.println("The value should be between 1 and "+ board.length);

        }
        //Index has already been played .
        else if (board[i-1] == CellValue.X || board[i-1] == CellValue.O) {
            System.out.println("This cell has already been played");
        }

        // your code here
        //valid input
        else {
            if (nextCellValue() == CellValue.X) {
                board[i-1] = CellValue.X;
            }

            else {
                board[i-1] = CellValue.O;
            }
            level++;
            setGameState(i-1);

        }

    }


    /**
     * A helper method which updates the gameState variable
     * correctly after the cell at index i was just set.
     * The method assumes that prior to setting the cell
     * at index i, the gameState variable was correctly set.
     * it also assumes that it is only called if the game was
     * not already finished when the cell at index i was played
     * (the the game was playing). Therefore, it only needs to
     * check if playing at index i has concluded the game
     * So check if 3 cells are formed to win.
     //   	* @param i
     *  the index of the cell in the array board that has just
     * been set
     */

    private void setGameState(int index){

        // your code here
        //logic to check winnig by forming 3 set in a row.
        for (int i=0;i<lines;i++)
        {
            //int t;
            //t=i*columns;
            //System.out.println(i);
            for(int j=i*columns;j+sizeWin<=(i+1)*columns;j++)
            {
                if(board[j]==board[j+1] &&  board[j]==board[j+2] && board[j]!=CellValue.EMPTY)
                {
                    if(board[index]==CellValue.X)
                    {
                        gameState = GameState.XWIN;
                    }
                    else
                    {
                        gameState = GameState.OWIN;
                    }

                    break;
                }

            }
            if(gameState==GameState.XWIN || gameState==GameState.OWIN)
            {
                break;
            }
        }

    }





    final String NEW_LINE = System.getProperty("line.separator");
    // returns the OS dependent line separator

    /**
     * Returns a String representation of the game matching
     * the example provided in the assignment's description
     *
     * @return
     *  String representation of the game
     */

    public String toString(){
        // your code here
        // use NEW_LINE defined above rather than \n



        int k=0;
        String str="";
        int lines=getLines();
        //System.out.println(lines);
        int columns=getColumns();
        //System.out.println(columns);
        for (int i = 1; i <= lines; i++)
        {
            for (int j = 1; j <= columns; j++)
            {
                //System.out.print(" " + board[i+j+1] +" ");
                if(j<columns)

                {
                    //System.out.println(board[k]);
                    //System.out.println(k);
                    if(board[k]==CellValue.EMPTY)
                    {
                        str=str+" "+" "+" "+'|';
                    }
                    else
                    {

                        str=str+" "+ board[k]+" "+'|';
                    }
                }
                else
                {	//System.out.println(board[k]);
                    //System.out.println(k);
                    if(board[k]==CellValue.EMPTY)
                    {
                        str=str+" ";
                    }
                    else
                    {
                        str=str+board[k];
                    }
                }
                k=k+1;



            }
            str=str+NEW_LINE;
            //System.out.println(i);
            if(i!=lines);
            {
                for(int l=0;l<columns*4-1;l++)
                {
                    str=str+"_";
                }
                str=str+NEW_LINE;
            }

        }
//System.out.println(str);
        return(str);

    }
}
