import java.util.Scanner;


public class TicTacToe { 
	
	static String[][][] blank = new String[3][3][1];  //the main string that hold the x's and o's
	static String player1;	//holds the names of the players
	static String player2;	
	static int draw = 0;	// counts the number of draws
	static int won = 0;		//tells the game when to stop
	static int p1won = 0;	//keeps track of who won each game
	static int p2won = 0;
	static int p1 = 1;		// used to tell whose turn it is
	static int p2 = 0;
	static int points1 = 0;	// keeps track of the points each player has
	static int points2 = 0;
	static int count = 0;	//number of games
	static int badMove = 0; // tells if a move was valid
	
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("TicTextToe");			//intro
		System.out.print("Name of player 1: ");
		player1 = s.nextLine();
		System.out.println("You will be playing against your computer!");
		//Computer cortana = new Computer();
		
		do{							//this do loop restarts the game when a player wants to play again
			clearTable();
			DisplayTable();
			while(won == 0){		//This loop keeps the game going
				
				do{				//this do loop is player 1's turn
					p1 = 1;
					p2 = 0;
					badMove = 0;
					System.out.print("Player " + player1 + ": ");
					move(s.nextInt(),s.nextInt());
					DisplayTable();
				
				}while(badMove == 1);
					checkWinner(); 	// checks to see if player 1 won
					
				if (won == 0){ 
					p2 = 1;
					p1 = 0;
					System.out.print("Computers move: ");
					//calculateMove();
					move(s.nextInt(),s.nextInt());
					DisplayTable();
					checkWinner();
				}	
			}
			if (p1won == 1){									//this part adds the points together and keeps track of the score
				System.out.println(player1 + " is the winner!");
				points1++;
				count++;
				p1won=0;
				System.out.print("Another game y/n?");
			}
			else if (p2won == 1){
				System.out.println(player2 + " is the winner!");
				points2++;
				count++;
				p2won=0;
				System.out.print("Another game y/n?");
			}
			else{
				System.out.println("its a tie!");
				count++;
				draw = 0;
				System.out.print("Another game y/n?");
			}
		
			String again = s.next();  			// asks the players if they want to play again
			if (again.equalsIgnoreCase("y")){
				won = 0;
			}
		}while (won == 0);	
		
		System.out.println("Statistics: \n" + player1 +"\t"+ points1 + "/" + count + "\n" + player2 +"\t"+ points2 + "/" + count);
		System.out.println("Goodbye!");
	}
	
	static void DisplayTable(){			//displays the table
		System.out.println("  0  1  2");
		System.out.println("0[" + blank[0][0][0] + "][" + blank[0][1][0] + "][" + blank[0][2][0] + "]");
		System.out.println("1[" + blank[1][0][0] + "][" + blank[1][1][0] + "][" + blank[1][2][0] + "]");
		System.out.println("2[" + blank[2][0][0] + "][" + blank[2][1][0] + "][" + blank[2][2][0] + "]");
	}
	
	static void clearTable(){ // sets all slots in the table to blanks
		blank[0][0][0]= " ";
		blank[1][0][0]= " ";
		blank[2][0][0]= " ";
		blank[0][1][0]= " ";
		blank[0][2][0]= " ";
		blank[1][1][0]= " ";
		blank[2][2][0]= " ";
		blank[1][2][0]= " ";
		blank[2][1][0]= " ";
	}

	static void move(int row, int col){			//Calculates valid and invalid moves and places X's and O's if the moves are valid
		if (row < 0 || row > 2){				//keeps the moves within range of 0 and 2
			System.out.println("Invalid move");
			badMove = 1;
		}
		if (col < 0 || col > 2){
			System.out.println("Invalid move");
			badMove = 1;
		}
		if (badMove != 1){							// makes sure the space is not occupied
			if (!blank[row][col][0].equals(" ")){
				System.out.println("Invalid move.");
				badMove = 1;
			}
			if (badMove == 0){
				if (p1 == 1){						//places X's and O's
					blank[row][col][0] = "X";}
				else if (p2 == 1){
					blank[row][col][0] = "O";}
			}	
		}	
	}
	static void checkRow(){				//checks rows for a win
		for(int i = 0 ; i<3 ; i++){
		if(blank[i][0][0] .equals("X")){if(blank[i][1][0] .equals("X")){if(blank[i][2][0] .equals("X")){
			p1won = 1;}}}
		if(blank[i][0][0] .equals("O")){if(blank[i][1][0] .equals("O")){if(blank[i][2][0] .equals("O")){
			p2won = 1;}}}
		}
	}
	
	static void checkCol(){				// checks columbs for a win
		for(int i = 0 ; i<3 ; i++){
		if(blank[0][i][0] .equals("X") && blank[1][i][0] .equals("X") && blank[2][i][0] .equals("X")){
			p1won = 1;}
		if(blank[0][i][0] .equals("O")){if(blank[1][i][0] .equals("O")){if(blank[2][i][0] .equals("O")){
			p2won = 1;}}}
		}
		
	}
	
	static void checkDiagonal(){			//checks diagonals for a win
		if(blank[0][0][0] .equals("X")){if(blank[1][1][0] .equals("X")){if(blank[2][2][0] .equals("X")){
			p1won = 1;}}}
		if(blank[0][2][0] .equals("X")){if(blank[1][1][0] .equals("X")){if(blank[2][0][0] .equals("X")){
			p1won = 1;}}}
		if(blank[0][0][0] .equals("O")){if(blank[1][1][0] .equals("O")){if(blank[2][2][0] .equals("O")){
			p2won = 1;}}}
		if(blank[0][2][0] .equals("O")){if(blank[1][1][0] .equals("O")){if(blank[2][0][0] .equals("O")){
			p2won = 1;}}}
		
		
	}
	
	static void checkWinner(){ //checks who won and deals with ties
		checkDiagonal();
		checkCol();
		checkRow();
		if (!blank[0][0][0].equals (" ") && !blank[0][1][0].equals (" ") && !blank[1][0][0].equals (" ") && !blank[1][1][0].equals (" ") && !blank[1][2][0].equals (" ") && !blank[2][0][0].equals (" ") && !blank[2][1][0].equals (" ") && !blank[2][2][0].equals (" ") && !blank[0][2][0].equals (" ")){
			draw = 1;
		}
		if (draw == 1) won =1;
		if (p1won == 1)won = 1;
		if (p2won == 1)won = 1;
	}
	
	
	
}


