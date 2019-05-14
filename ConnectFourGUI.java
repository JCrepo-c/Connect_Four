package connect_four;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectFourGUI extends JFrame{
	
	private JPanel jpMain;
	Player player1;
	Player player2;
	Player currPlayer;
	ConnectFourBoard tttBoard;
	ScoreBoard scoreBoard;//score board
	TextFileHandler tfh = new TextFileHandler();

	
	
	public ConnectFourGUI(){
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());

		player1 = new Player("Batman", "X");
		player2 = new Player("Superman", "O");
		currPlayer = player1;
		
		scoreBoard = new ScoreBoard();
		tttBoard = new ConnectFourBoard();//game gets played in here
		
		jpMain.add(scoreBoard, BorderLayout.NORTH);
		jpMain.add(tttBoard, BorderLayout.CENTER);

		add(jpMain);
		setSize(500,500);
//		pack();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private class ScoreBoard extends JPanel{
		private JLabel lblChamp, lblLatestWinner,
		lblPlaceHolder, lblPlayerNames, lblPlayerNumWins,
		lblPlayer1Name,lblPlayer2Name,
		lblPlayer1NumWins,lblPlayer2NumWins, lblCLickHere,
		lblCurrentPlayer, lblDsplyLatestWinner, lblDisplayChampion;
		JPanel jpGenScoreInfo, jpPlayerScoreInfo, jpBoard,jpInputName;
		 
		
		public ScoreBoard(){
			setLayout(new BorderLayout());
			jpGenScoreInfo = new JPanel();
			jpGenScoreInfo.setLayout(new GridLayout(2,2));
			jpGenScoreInfo.setBackground(Color.WHITE);
			lblDsplyLatestWinner = new JLabel("Latest Winner");
			lblChamp = new JLabel("Champion");
			lblLatestWinner = new JLabel("--------");
			lblDisplayChampion = new JLabel("--------");
			jpGenScoreInfo.add(lblChamp);
			jpGenScoreInfo.add(lblLatestWinner);
			jpGenScoreInfo.add(lblDsplyLatestWinner);
			jpGenScoreInfo.add(lblDisplayChampion);

			
			jpPlayerScoreInfo = new JPanel();
			jpPlayerScoreInfo.setLayout(new GridLayout(3,3));
			jpPlayerScoreInfo.setBackground(Color.GRAY);
			lblPlaceHolder = new JLabel(" ");
			lblPlayerNames  = new JLabel("NAME");
			lblPlayerNumWins = new JLabel("NUM WINS");
			lblPlayer1Name = new JLabel(player1.getName());
			lblPlayer2Name = new JLabel(player2.getName());
			lblPlayer1NumWins = new JLabel(""+player1.getNumWins());
			lblPlayer2NumWins = new JLabel(""+player2.getNumWins());
			
			jpBoard = new JPanel();
			jpBoard.setLayout(new GridLayout(1,2));
			jpBoard.setBackground(Color.RED);
			lblCLickHere = new JLabel("Current Player ");
			lblCurrentPlayer = new JLabel(currPlayer.getName());
			jpBoard.add(lblCurrentPlayer); 
			
			
			jpPlayerScoreInfo.add(lblPlaceHolder);//00
			jpPlayerScoreInfo.add(new JLabel("Player 1"));//01
			jpPlayerScoreInfo.add(new JLabel("Player 2"));//02
			jpPlayerScoreInfo.add(lblPlayerNames);//10
			jpPlayerScoreInfo.add(lblPlayer1Name);//11
			jpPlayerScoreInfo.add(lblPlayer2Name);//12
			jpPlayerScoreInfo.add(lblPlayerNumWins);//20
			jpPlayerScoreInfo.add(lblPlayer1NumWins);
			jpPlayerScoreInfo.add(lblPlayer2NumWins);
			jpBoard.add(lblCLickHere);
			
			
			add(jpGenScoreInfo, BorderLayout.NORTH);
			add(jpPlayerScoreInfo, BorderLayout.CENTER);
			add(jpBoard, BorderLayout.SOUTH);

		}
	}//end of 

	



	
	private class ConnectFourBoard extends JPanel implements GameBoardInterface, ActionListener {
//		private JButton [] drpboard;
		private JButton [][] board;
		private static final int NUM_ROWS = 6;
		private static final int NUM_COLS = 7;
		
		public ConnectFourBoard(){
			setLayout(new GridLayout(NUM_ROWS,NUM_COLS));
			displayBoard();
			
		}

		@Override
		public void clearBoard() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col].setText("");
					board[row][col].setEnabled(true);
				}
			}
		}
		
		
		

		@Override
		public void displayBoard() {
/*			drpboard = new JButton[NUM_COLS];
			for(int row=0; row<drpboard.length; row++){
				drpboard[row] = new JButton();
				Font bigF = new Font(Font.SANS_SERIF, Font.BOLD, 30);
				drpboard[row].setFont(bigF);
				drpboard[row].setText("Click");
				add(drpboard[row]);
			
			}
*/			
			
			board = new JButton[NUM_ROWS][NUM_COLS];//initialize 2D array to 3x3 for ttt
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					
					board[row][col] = new JButton();
					Font bigF = new Font(Font.SANS_SERIF, Font.BOLD, 30);
					board[row][col].setFont(bigF);
					board[row][col].addActionListener(this);//listen for clicks
					
					add(board[row][col]);
				}
			}
			
			

		}

		@Override
		public void populateBoard() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void takeTurn() {
			if(currPlayer.equals(player1)){
				currPlayer = player2;
			}
			else{
				currPlayer = player1;
			}
			if (currPlayer.equals(player1)) {
				scoreBoard.lblCurrentPlayer.setText("" + player1.getName());
				} else {
				scoreBoard.lblCurrentPlayer.setText("" + player2.getName());
			}
		}
		
		
		public void promptPlayAgain() {
		 	int yesno = JOptionPane.showConfirmDialog(null, "Play Again?", "Yes or No", JOptionPane.YES_NO_OPTION);
		 	if (yesno == JOptionPane.YES_OPTION) {
		 		clearBoard();
		 	} else {
		 		System.exit(EXIT_ON_CLOSE);
		 	}
		}


		@Override
		public void displayWinner() {
			JOptionPane.showMessageDialog(null, "WINNER! " + currPlayer.getName());
			/*Object[] options = { "YES", "NO" };
			JOptionPane.showOptionDialog(null, "Would you like to play again?, "
					, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[1]);
			*/
		
			
			currPlayer.addWin();
			scoreBoard.lblLatestWinner.setText(currPlayer.getName());
			if(currPlayer.equals(player1)){
				scoreBoard.lblPlayer1NumWins.setText(""+player1.getNumWins());
			}
			else{
				scoreBoard.lblPlayer2NumWins.setText(""+player2.getNumWins());
			}
			if(player1.getNumWins() > player2.getNumWins()){
				scoreBoard.lblDisplayChampion.setText("" + player1.getName());
			}
			else {
				scoreBoard.lblDisplayChampion.setText("" + player2.getName());
			}
			//will also update labels eventually
		}
		
		@Override		
		public void displayChampion() {
		int a = player1.getNumWins();
		int b = player2.getNumWins();
		
		if(a > b){
			scoreBoard.lblDisplayChampion.setText("" + player1.getName());
		}
		else {
			scoreBoard.lblDisplayChampion.setText("" + player2.getName());
		}
		if(a == b) {
			scoreBoard.lblDisplayChampion.setText("uytre");

		}
		}
		
		
		

		@Override
		public boolean isFull() {
			//iterate through all cells
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					if(board[row][col].getText().equals("")){
						return false; //if any are empty return false
					}
				}
			}
			return true;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isWinner(String currPlayerSymbol) {
			if(isWinnerInRow() || isWinnerInCol() || isWinnerInSecDiag() || isWinnerInMainDiagonal()){
				tfh.appendToFile("Connect4-Results.txt", currPlayer.getName() + " wins");
				return true;
			}
			//if isWinnerInAnyRow ||
			//if isWinnerInAnyCol ||
			//if isWinnerInMainDiag ||
			//if isWinnerInSecondDiag
			return false;
		}

		@Override
		public boolean isDraw() {
			// TODO Auto-generated method stub
			if(isFull()==true){
			tfh.appendToFile("Connect4-Results.txt", "Game is a draw");
			return true;
			}
			else {
			return false;
			}
		}

		public boolean isWinnerInRow(){
			for(int row=0; row<board.length; row++){
				int numMatchesInRow = 0;
				for(int col=0; col<board[row].length; col++){
					if(board[row][col].getText().equals(currPlayer.getSymbol())){
						numMatchesInRow++;
						if(!board[row][col].getText().equals(currPlayer.getSymbol())){
							numMatchesInRow = 0;
							if(numMatchesInRow == 4){
								return true;
									
								}
						}				

					}
				}
			}
			return false;
		}
		public boolean isWinnerInCol(){
			for(int col=0; col<NUM_COLS; col++){
			int numMatchesInCol = 0;
			for(int row=0; row<board.length; row++){
					if(board[row][col].getText().equals(currPlayer.getSymbol())){
						numMatchesInCol++;
						if(!board[row][col].getText().equals(currPlayer.getSymbol())){
							numMatchesInCol = 0;
							if(numMatchesInCol == 4){
								return true;
								}

						}

					}
				}
			}
			return false;
		}
		
		public boolean isWinnerInMainDiagonal() {
		    String symbol = currPlayer.getSymbol();
		    int matchesInMainDiag = 0;
		    int row = board.length-1;
		    int col = 0;
		    for (int i = board.length-1; i >=0; i--) {
		        row = i;
		        col = 0;
		        matchesInMainDiag = 0;
		        while(row<board.length && col <board[row].length) {
		            if( board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
		                matchesInMainDiag++;
		            }
		            else {
		                matchesInMainDiag = 0;
		            }
		            if(matchesInMainDiag == 4) {
		                return true;
		            }
		            col++;
		            row--;
		            if (row<0) {
		                row = 0;
		                break;
		            }
		        }
		    }

		    for (int j = 0; j<board[row].length; j++) {
		        col = j;
		        row = board.length-1;
		        matchesInMainDiag = 0;
		        while( row<board.length && col <board[row].length) {
		            if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
		                matchesInMainDiag++;
		            }
		            else {
		                matchesInMainDiag = 0;
		            }
		            if (matchesInMainDiag == 4) {
		                return true;
		            }
		            row--;
		            col++;
		            if(row<0) {
		                row =0;
		                break;
		            }
		        }
		    }
		    return false;
		} //this is where the matches in the main Diag ends


		public boolean isWinnerInSecDiag() {
		    String symbol = currPlayer.getSymbol();
		    int matchesInSecDiag = 0;
		    int row = 0;
		    int col =0;
		    for(int showRow = 0; showRow < board.length; showRow++) {
		        row = showRow;
		        col = 0;
		        matchesInSecDiag = 0;
		        while(row<board.length && col < board[row].length) {
		            if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
		                matchesInSecDiag++;
		            }else {
		                matchesInSecDiag=0;
		            }
		            if(matchesInSecDiag == 4) {
		                return true;
		            }
		            row++;
		            col++;
		        }

		    }
		    row = 0;
		    for(int showCol = 0; showCol < board[row].length; showCol++) {
		        row = 0;
		        col = showCol;
		        matchesInSecDiag = 0;
		        while(row< board.length && col < board[row].length) {
		            if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
		                matchesInSecDiag++;
		            }else {
		                matchesInSecDiag =0;
		            }
		            if(matchesInSecDiag == 4) {
		                return true;
		            }
		            row++;
		            col++;
		            if(row>5) {
		                row =5;
		                break;
		            }
		        }
		    }
		    return false;
		}    //This is where winner in Second Diag will win at


		
	/*	public boolean isWinnerInDiagNeg(){
		for(int col=0; col<NUM_COLS; col++){
		for(int row = 0; row<board.length; row++){
				int numMatchesDiag = 0;
				for(int diag = 0; diag <= 1; diag++) {
					if(board[row][col].getText()
							.equals(currPlayer.getSymbol())&&board[row+1][col-1].getText()
							.equals(currPlayer.getSymbol())&&board[row+2][col-2].getText()
							.equals(currPlayer.getSymbol())&&board[row+3][col-3].getText()
							.equals(currPlayer.getSymbol())){
						return true;
						}	
					}	
				}
			}
			return false;
 		}
	*/	 	
		
		
		
	/*public boolean isWinnerInDiagPos(){
			for(int col=0; col<NUM_COLS; col++){
			for(int row = 0; row<board.length; row++){
					int numMatchesDiag = 0;
					for(int diag = 0; diag <= 1; diag++) {
						if(board[row][col].getText()
								.equals(currPlayer.getSymbol())&&board[row+1][col+1].getText()
								.equals(currPlayer.getSymbol())&&board[row+2][col+2].getText()
								.equals(currPlayer.getSymbol())&&board[row+3][col+3].getText()
								.equals(currPlayer.getSymbol())){
							return true;
						}	
					}	
				}
			}
				return false;
	 	}
		*/

		@Override
		public void actionPerformed(ActionEvent e) {
			//e.getActionCommand()   e.getSource()
			JButton btnClicked = (JButton)e.getSource();
			btnClicked.setText(currPlayer.getSymbol());//set the symbol on the button
			btnClicked.setEnabled(false);//disable

			
			//isWinner
			if (isWinner(currPlayer.getSymbol())) {
								displayWinner();
				promptPlayAgain();
			}

			if (isFull() && !isWinner(currPlayer.getSymbol())) {
				JOptionPane.showMessageDialog(null, "Game Is A Draw");
				promptPlayAgain();
			}

			
			takeTurn();
		}//end of actionPerformed

		@Override
		public void displayCurrentPlayer() {
			// TODO Auto-generated method stub
			
		}


	}
		
		
		
	}
	
	
	
	
