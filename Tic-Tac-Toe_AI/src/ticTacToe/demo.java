package ticTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class demo extends JFrame implements ActionListener {

	public class JLabelBox extends JLabel {

		public int Field = 0;
	}

	private int XWins = 0;
	private int OWins = 0;
	private int Draws = 0;

	private JPanel contentPane;
	private JButton btnNewGame, btnReset;
	private JLabelBox P1, P2, P3, P4, P5, P6, P7, P8, P9;
	private JLabel lblXWins, lblOWins, lblDraws;
	private TicTacToeAI TTT;
	private JLabel lblXWins_1;
	private JLabel lblOWins_1;
	private JLabel lblDraws_1;

	public static void main(String[] args) { // launch app
		demo frame = new demo();
		frame.setVisible(true);
	}

	public demo() {			//create the window labels and buttons
		
		TTT = new TicTacToeAI();
		setResizable(false);
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		setContentPane(contentPane);
	    contentPane.setLayout(null);

		P1 = new JLabelBox();	//create box#1
		P1.Field = 1;
		P1.addMouseListener(new LabelAdapter(P1));
		P1.setForeground(Color.GREEN);
		P1.setBackground(new Color(0, 0, 0));
		P1.setHorizontalAlignment(SwingConstants.CENTER);
		P1.setBounds(10, 10, 100, 100);
		P1.setOpaque(true);
		contentPane.add(P1);

		P2 = new JLabelBox();	//create box#2
		P2.Field = 2;
		P2.addMouseListener(new LabelAdapter(P2));
		P2.setOpaque(true);
		P2.setHorizontalAlignment(SwingConstants.CENTER);
		P2.setForeground(Color.GREEN);
		P2.setBackground(Color.BLACK);
		P2.setBounds(120, 10, 100, 100);
		contentPane.add(P2);

		P3 = new JLabelBox();	//create box#3
		P3.Field = 3;
		P3.addMouseListener(new LabelAdapter(P3));
		P3.setOpaque(true);
		P3.setHorizontalAlignment(SwingConstants.CENTER);
		P3.setForeground(Color.GREEN);
		P3.setBackground(Color.BLACK);
		P3.setBounds(230, 10, 100, 100);
		contentPane.add(P3);

		P4 = new JLabelBox();	//create box#4
		P4.Field = 4;
		P4.addMouseListener(new LabelAdapter(P4));
		P4.setOpaque(true);
		P4.setHorizontalAlignment(SwingConstants.CENTER);
		P4.setForeground(Color.GREEN);
		P4.setBackground(Color.BLACK);
		P4.setBounds(10, 120, 100, 100);
		contentPane.add(P4);

		P5 = new JLabelBox();	//create box#5
		P5.Field = 5;
		P5.addMouseListener(new LabelAdapter(P5));
		P5.setOpaque(true);
		P5.setHorizontalAlignment(SwingConstants.CENTER);
		P5.setForeground(Color.GREEN);
		P5.setBackground(Color.BLACK);
		P5.setBounds(120, 120, 100, 100);
		contentPane.add(P5);

		P6 = new JLabelBox();	//create box#6
		P6.Field = 6;
		P6.addMouseListener(new LabelAdapter(P6));
		P6.setOpaque(true);
		P6.setHorizontalAlignment(SwingConstants.CENTER);
		P6.setForeground(Color.GREEN);
		P6.setBackground(Color.BLACK);
		P6.setBounds(230, 120, 100, 100);
		contentPane.add(P6);

		P7 = new JLabelBox();	//create box#7
		P7.Field = 7;
		P7.addMouseListener(new LabelAdapter(P7));
		P7.setOpaque(true);
		P7.setHorizontalAlignment(SwingConstants.CENTER);
		P7.setForeground(Color.GREEN);
		P7.setBackground(Color.BLACK);
		P7.setBounds(10, 230, 100, 100);
		contentPane.add(P7);

		P8 = new JLabelBox();	//create box#8
		P8.Field = 8;
		P8.addMouseListener(new LabelAdapter(P8));
		P8.setOpaque(true);
		P8.setHorizontalAlignment(SwingConstants.CENTER);
		P8.setForeground(Color.GREEN);
		P8.setBackground(Color.BLACK);
		P8.setBounds(120, 230, 100, 100);
		contentPane.add(P8);

		P9 = new JLabelBox();	//create box#9
		P9.Field = 9;
		P9.addMouseListener(new LabelAdapter(P9));
		P9.setOpaque(true);
		P9.setHorizontalAlignment(SwingConstants.CENTER);
		P9.setForeground(Color.GREEN);
		P9.setBackground(Color.BLACK);
		P9.setBounds(230, 230, 100, 100);
		contentPane.add(P9);

		btnNewGame = new JButton("New game");
		btnNewGame.addActionListener(this);
		btnNewGame.setBounds(10, 350, 320, 25);
		contentPane.add(btnNewGame);

		lblXWins = new JLabel("0");
		lblXWins.setBounds(425, 50, 50, 20);
		contentPane.add(lblXWins);

		lblOWins = new JLabel("0");
		lblOWins.setBounds(425, 100, 50, 20);
		contentPane.add(lblOWins);

		lblDraws = new JLabel("0");
		lblDraws.setBounds(425, 150, 50, 20);
		contentPane.add(lblDraws);

		btnReset = new JButton("Reset Score");
		btnReset.setBounds(350, 200, 125, 75);
		btnReset.addActionListener(this);

		contentPane.add(btnReset);

		lblXWins_1 = new JLabel("X wins:");
		lblXWins_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXWins_1.setBounds(350, 50, 50, 20);
		contentPane.add(lblXWins_1);

		lblOWins_1 = new JLabel("O wins:");
		lblOWins_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOWins_1.setBounds(350, 100, 50, 20);
		contentPane.add(lblOWins_1);

		lblDraws_1 = new JLabel("Draws:");
		lblDraws_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDraws_1.setBounds(350, 150, 50, 20);
		contentPane.add(lblDraws_1);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnNewGame) {
			P1.setText("");
			P2.setText("");
			P3.setText("");
			P4.setText("");
			P5.setText("");
			P6.setText("");
			P7.setText("");
			P8.setText("");
			P9.setText("");
			TTT.NewGame();
		}

		if (e.getSource() == btnReset) {
			scoreChange(0);
		}
	}

	public void scoreChange(int Num) { //changes scores based on who has won
		switch (Num) {
			case 1:		XWins++; break;
			case -1:	OWins++; break;
			case 2:		Draws++; break;
			case 0:		XWins = 0; OWins = 0; Draws = 0; break;
		}
		lblXWins.setText(XWins + "");
		lblOWins.setText(OWins + "");
		lblDraws.setText(Draws + "");
	}

	class LabelAdapter extends MouseAdapter {
		JLabelBox sender;

		public LabelAdapter(JLabelBox sender) {
			this.sender = sender;
		}

		public void mouseReleased(MouseEvent me) {
			int winner = TTT.isGameOver();
			if (TTT.Move(sender.Field, 1) && winner == 0) {
				sender.setForeground(Color.GREEN);
				sender.setText("X");
				ComputerMove(-1, Color.RED,0);
				if ((winner = TTT.isGameOver()) != 0)
					scoreChange(winner);
			}
		}
	}

	private void NewGame() {
		P1.setText("");
		P2.setText("");
		P3.setText("");
		P4.setText("");
		P5.setText("");
		P6.setText("");
		P7.setText("");
		P8.setText("");
		P9.setText("");
		TTT.NewGame();
	}

	private void ComputerMove(int player, Color pcolor,int move) {
		if (move==0) 
			move = TTT.isGameOver() == 0 ? TTT.GenerateMove(player) : 0;
		String PText = (player == 1) ? "X" : "O";

		switch (move) {
		case 1:
			P1.setForeground(pcolor);
			P1.setText(PText);
			TTT.Move(move, player);
			break;
		case 2:
			P2.setForeground(pcolor);
			P2.setText(PText);
			TTT.Move(move, player);
			break;
		case 3:
			P3.setForeground(pcolor);
			P3.setText(PText);
			TTT.Move(move, player);
			break;
		case 4:
			P4.setForeground(pcolor);
			P4.setText(PText);
			TTT.Move(move, player);
			break;
		case 5:
			P5.setForeground(pcolor);
			P5.setText(PText);
			TTT.Move(move, player);
			break;
		case 6:
			P6.setForeground(pcolor);
			P6.setText(PText);
			TTT.Move(move, player);
			break;
		case 7:
			P7.setForeground(pcolor);
			P7.setText(PText);
			TTT.Move(move, player);
			break;
		case 8:
			P8.setForeground(pcolor);
			P8.setText(PText);
			TTT.Move(move, player);
			break;
		case 9:
			P9.setForeground(pcolor);
			P9.setText(PText);
			TTT.Move(move, player);
			break;
		}

	}
}
