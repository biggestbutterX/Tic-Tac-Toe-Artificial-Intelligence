package ticTacToeAi;

import java.util.*;

class Board {
	 
    List<Point> availablePoints;
    List<PointsAndScores> pointScores;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[3][3];
    
    //Creates a board object
    public Board() {
    }
    
    //Displays the board to the console
    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
            	if (board[i][j] == 0) System.out.print("[ ]");
            	else if (board[i][j] == 1) System.out.print("[O]");
            	else if (board[i][j] == 2) System.out.print("[X]");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Checks to see if the user has won
    public boolean computerWin() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || 	
        	(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {	//check X Diagonally
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1) || 
            	(board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1)) {  //check X vertically/horizontally
                return true;
            }
        }
        return false;
    }

    //Checks to see if the computer has won
    public boolean playerWin() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || 
        	(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {	//check O Diagonally
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2) || 
            	(board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {  //check O vertically/horizontally
                return true;
            }
        }
        return false;
    }
    
    //returns true if X wins, O wins, or if there are no spaces left (draw)
    public boolean isGameOver() {		
        return (playerWin() || computerWin() || getAvailablePoints().isEmpty());
    }
    
    //Checks to make the sure the move the user is trying to make it legal
    public boolean checkMove(int row, int col){
    	if (row < 0 || row > 2){				//keeps the moves within range of 0 and 2
			System.out.println("Invalid move");
			return false;
		}
		if (col < 0 || col > 2){
			System.out.println("Invalid move");
			return false;
		}
		if (board[row][col] == 1 || board[row][col] == 2){
			System.out.println("Invalid move.");
			return false;
		}
		return true;
    }
    
    // creates a list of availablePoints by checking to see if there is an X or O present. if there is not, it adds that point to the list.
    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<Point>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {		// if point is available
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    //Places a point on the board (1 = O, 2 = X)
    public void Move(Point point, int player) {	
        board[point.x][point.y] = player;
    }
    
    
//////////////////////MINMAX SEARCH//////////////////////////////////////////////
    
    
    //Calculates the best move the computer has
    public Point bestMove() {
        int MAX = -100000;	//max starts as the best possible move
        int best = -1;

        for (int i = 0; i < pointScores.size(); ++i) {  // go through all possible points
            if (MAX < pointScores.get(i).score) {		//if point is better than previous
                MAX = pointScores.get(i).score;			//make that the best
                best = i;
            }
        }
        return pointScores.get(best).point; // returns point with highest heuristic value
    }
    
    // find minimum value of points
    public int getMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;	//sets min = max value
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {	//go through list
            if (list.get(i) < min) {			//if point is < min
                min = list.get(i);				//it is the new min
                index = i;
            }
        }
        return list.get(index);	//return the  
    }

    //find maximum value of points
    public int getMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;	//sets max = max value
        int index = -1;
        for (int i = 0; i < list.size(); ++i) { //go through list
            if (list.get(i) > max) {			//if point > max
                max = list.get(i);				//it is the new max
                index = i;
            }
        }
        return list.get(index);
    }
    
    // get initial call from main class set depth and turn
    public void callMinimax(int depth, int turn){
        pointScores = new ArrayList();		//pointScores = array list containing the heuristic values of the points
        minimax(depth, turn);				//call minimax algorithm
    }
    
    
    public int minimax(int depth, int turn) {

        if (computerWin()) return +1;	//check if computer has won
        if (playerWin()) return -1;		//check if player has won

        List<Point> pointsAvailable = getAvailablePoints();	//gets available points
        if (pointsAvailable.isEmpty()) return 0; 			//if no available points: draw

        List<Integer> scores = new ArrayList<>(); 		//make a list to store scores for each point

        for (int i = 0; i < pointsAvailable.size(); ++i) {	//for each available point
            Point point = pointsAvailable.get(i);  			//get the next point
            Move(point, 1); 								//put computers point in the table
            int currentScore = minimax(depth + 1, 2);		//check current score of board
            scores.add(currentScore);						//add to scores
            if (depth == 0){
            	pointScores.add(new PointsAndScores(currentScore, point));
            }
            Move(point, 2); 								//put users point in the table
            scores.add(minimax(depth + 1, 1));				//add scores 
            board[point.x][point.y] = 0; 					//Reset this point
        }
        return turn == 1 ? getMax(scores) : getMin(scores);	//if computer turn get max, if users, get min
    }
}