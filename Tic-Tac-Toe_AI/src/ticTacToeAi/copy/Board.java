package ticTacToeAi.copy;

import java.util.*;

class Board {
	 
    List<Point> availablePoints;
    List<PointsAndScores> rootsChildrenScores;
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
            	else if (board[i][j] == 1) System.out.print("[X]");
            	else if (board[i][j] == 2) System.out.print("[O]");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Checks to see if the user has won
    public boolean PlayerWin() {
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
    public boolean ComputerWin() {
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
        return (PlayerWin() || ComputerWin() || getAvailableStates().isEmpty());
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
    public List<Point> getAvailableStates() {
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

    //Places a point on the board (1 = X, 2 = O)
    public void Move(Point point, int player) {	
        board[point.x][point.y] = player;
    }
    
    
//////////////////////MINMAX SEARCH//////////////////////////////////////////////
    
    
    //Calculates the best move the computer has using recursion
    public Point bestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScores.size(); ++i) { 
            if (MAX < rootsChildrenScores.get(i).score) {
                MAX = rootsChildrenScores.get(i).score;
                best = i;
            }
        }
 
        return rootsChildrenScores.get(best).point;
    }
    
    public int getMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int getMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }
 
    public void callMinimax(int depth, int turn){
        rootsChildrenScores = new ArrayList<>();
        minimax(depth, turn);
    }
    
    public int minimax(int depth, int turn) {

        if (PlayerWin()) return +1;
        if (ComputerWin()) return -1;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0; 

        List<Integer> scores = new ArrayList<>(); 

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);  

            if (turn == 1) { //X's turn select the highest from below minimax() call
                Move(point, 1); 
                int currentScore = minimax(depth + 1, 2);
                scores.add(currentScore);

                if (depth == 0) 
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));
                
            } else if (turn == 2) {//O's turn select the lowest from below minimax() call
                Move(point, 2); 
                scores.add(minimax(depth + 1, 1));
            }
            board[point.x][point.y] = 0; //Reset this point
        }
        return turn == 1 ? getMax(scores) : getMin(scores);
    }
}