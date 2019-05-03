package ticTacToeAi.copy;

import java.util.*;
public class TicTacToe {

    public static void main(String[] args) {
        Board b = new Board();
        Scanner s = new Scanner(System.in);
		
		System.out.println("TicTextToe!!");			//Get it, cause you use text to play? hahahahahaha
		System.out.print("Please enter your name: ");
		String player = s.nextLine();
		System.out.println("You will be playing against your computer!");
		b.displayBoard(); //displays the board
        
        while (!b.isGameOver()) {	//loops that plays until there is a gameOver
        	
            System.out.println(player + "'s move: ");	//players turn
            int x, y;
            while(!b.checkMove(x = s.nextInt(),y = s.nextInt())); //Gets the users input until it is a valid input
            Point userMove = new Point(x,y);
            b.Move(userMove, 2); //Make the users move
            b.displayBoard();
            
            if (b.isGameOver())break;	//check to see if game is over
            
            System.out.println("Computer's move: ");	//computers turn
            b.callMinimax(0, 1);		//find best move
            for (PointsAndScores pas : b.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            }
            b.Move(b.bestMove(), 1);	//make that move
            b.displayBoard();
        }
        if (b.PlayerWin()) {
            System.out.println("You win! This is not going to get printed.");
        } else if (b.ComputerWin()) {
            System.out.println("Unfortunately, you lost!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}