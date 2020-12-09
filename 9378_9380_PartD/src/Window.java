import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
public class Window {
	Game game;
	JFrame window;
	Board board;
	
	Window(){
		window = new JFrame("Snakes and Ladders");
		game = new Game(0);
		board = new Board(10,20,3,3,6);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setName("Snakes and Ladders");
		window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		JLabel roundLabel = new JLabel("Round: 0");
		JLabel playerALabel = new JLabel("Player A");
		JLabel playerBLabel = new JLabel("Player B");
		JLabel pAMoveLabel = new JLabel("Move Score: 0");
		JLabel pBMoveLabel = new JLabel("Move Score: 0");
		JLabel pAScoreLabel = new JLabel("Total Score: 0");
		JLabel pBScoreLabel = new JLabel("Total Score: 0");
        JPanel buttonPanel=new JPanel(); 
        JPanel arrayPanel = new JPanel();
        
        int n=10, m=20;
        String tiles[][] = new String[n][m];
        int realTileNumber = 1;
		boolean normalDirection = true;
		for(int i = n-1; i>=0; i--) {
			if(normalDirection){
				for(int j=0; j<m; j++) {
					tiles[i][j] = String.valueOf(realTileNumber);
					realTileNumber++;
					normalDirection = false;
				}
			}
			else{
				for(int j=m-1; j>=0; j--) {
					tiles[i][j] = String.valueOf(realTileNumber);
					realTileNumber++;
					normalDirection = true;
				}
			}
		}
        JTable jBoard = new JTable(tiles, tiles[0]);

		String[] options = {"Simple Player", "Heuristic Player", "MinMax Player"};
		JComboBox<String> playerA = new JComboBox<String>(options);
		JComboBox<String> playerB = new JComboBox<String>(options);
        JButton boardGen = new JButton("Generate Board");
        JButton play = new JButton("Play");
        JButton quit = new JButton("Quit");
        
        window.add(roundLabel);
        window.add(playerALabel);
        window.add(playerBLabel);
        window.add(pAMoveLabel);
        window.add(pBMoveLabel);
        window.add(pAScoreLabel);
        window.add(pBScoreLabel);
        window.add(buttonPanel);
        buttonPanel.add(boardGen);
        buttonPanel.add(play);
        buttonPanel.add(quit);
        window.add(playerA);
        window.add(playerB);
        window.add(arrayPanel);
        arrayPanel.add(jBoard);
        
        roundLabel.setBounds(20, 20, 100, 30);
        playerALabel.setBounds(20, 600, 100, 30);
        pAMoveLabel.setBounds(20, 630, 100, 30);
        pAScoreLabel.setBounds(20, 660, 150, 30);
        playerBLabel.setBounds(window.getWidth()-20-170, 600, 100, 30);
        pBMoveLabel.setBounds(window.getWidth()-20-170, 630, 100, 30);
        pBScoreLabel.setBounds(window.getWidth()-20-170, 660, 150, 30);
        playerA.setBounds(20, 700, 150, 50);
        playerB.setBounds(window.getWidth()-20-170, 700, 150, 50);
        buttonPanel.setBounds(window.getWidth()/2 - 150,750,300,50); 
        arrayPanel.setBounds(100, 50, Toolkit.getDefaultToolkit().getScreenSize().width-200, 1000);
        
        jBoard.setRowHeight(50);  
        
        quit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		window.dispose();
        	}
        });
        boardGen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		board.createBoard();
        		board.createElementBoard();
                play.setEnabled(true);
        		//TODO Position images
        	}
        });
        
        play.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String AChoice = playerA.getItemAt(playerA.getSelectedIndex());
        		String BChoice = playerB.getItemAt(playerB.getSelectedIndex());
        		//TODO Play Game
        		Object playerA;
        		Object playerB;
        		if(AChoice == "Simple Player")
        			playerA = new Player(1, "Player A", 0, board);
        		else if (AChoice == "Heuristic Player")
        			playerA = new HeuristicPlayer(1, "Player A", 0, board);
        		else
        			playerA = new MinMaxPlayer(1, "Player A", 0, board);
        		
        		if(BChoice == "Simple Player")
        			playerB = new Player(2, "Player B", 0, board);
        		else if (BChoice == "Heuristic Player")
        			playerB = new HeuristicPlayer(2, "Player B", 0, board);
        		else
        			playerB = new MinMaxPlayer(2, "Player B", 0, board);
        		
        		ArrayList<Object> players = new ArrayList<Object>();
        		players.add(playerA);					//Γ�Γ­Γ΅Γ² Γ¶ΓµΓ³Γ©Γ―Γ«Γ―Γ£Γ©Γ�ΓΌΓ² Γ°Γ΅Γ�Γ�Γ΄Γ§Γ²
        		players.add(playerB);				//Γ°Γ΅Γ�Γ�Γ΄Γ§Γ² Γ΄Γ½Γ°Γ―Γµ heuristicPlayer
        		Map<Integer, Integer> dice = game.setTurns(players);
        		if(dice == null) {
        			return;
        		}
        		boolean finished = false;
        		//int [] array = new int[5];
        		int [] pathA = new int[7];
        		int [] pathB = new int[7];
        		//int id = 0;
        		double[] winScore = new double[3];
        		do { // Ξ–Ξ±Ο�ΞΉΞ­Ο‚ ΞΌΞ­Ο‡Ο�ΞΉ Ο„ΞΏΟ…Ξ»Ξ¬Ο‡ΞΉΟƒΟ„ΞΏΞ½ Ξ­Ξ½Ξ±Ο‚ Ξ½Ξ± Ο„ΞµΟ�ΞΌΞ±Ο„Ξ―ΟƒΞµΞΉ
        			game.setRound(game.getRound() + 1);
        			for (Map.Entry<Integer, Integer> entry : dice.entrySet()) {

        				if(((Player)players.get(entry.getKey()-1)).getPlayerId()==1)
        				{
        					if(players.get(entry.getKey()-1) instanceof MinMaxPlayer) {  //Γ�Γ­ Γ¥Γ�Γ­Γ΅Γ© minMax Γ― Γ°Γ΅Γ�Γ�Γ΄Γ§Γ² Γ°Γ΅Γ�Γ±Γ­Γ¥Γ©Γ² Γ΄Γ§Γ­ getNextMove. Γ�Γ«Γ«Γ©ΓΎΓ² Γ°Γ΅Γ² Γ³Γ΄Γ§Γ­ else 
        						pathA = (((MinMaxPlayer) players.get(entry.getKey()-1)).getNextMove(board, (int)(pathA[2]), (int)(pathB[2])));
        					}
        					else if(players.get(entry.getKey()-1) instanceof HeuristicPlayer) {
        						pathA = ((HeuristicPlayer)players.get(entry.getKey()-1)).getNextMove((int)pathA[2]);
        					}
        					else
        					{
        						int [] array = new int[5];
        						int die = (int)(Math.random()*6+1);
        						double eval = (new HeuristicPlayer(99, "keno", 0, board).evaluate((int)pathA[2],die));
        						array = ((Player)players.get(entry.getKey()-1)).move((int)pathA[2], die);
        						pathA[0]=die;
        						pathA[1]=(int)(eval);
        					    for(int i=0; i<5; i++)
        					    	pathA[i+2]=array[i];
        					}
            				pAMoveLabel.setText("Move Score: " + Integer.toString(pathA[1]));
            				winScore[0] = ((Player)players.get(0)).getScore() * 0.25 + pathA[2] * 0.75;
            				pAScoreLabel.setText("Total Score: " + Double.toString(winScore[0]));

        					if(pathA[2] >= board.getM()* board.getN() || pathB[2] >= board.getM()* board.getN()) //Γ³ΓµΓ­Γ¨Γ�Γ�Γ§ Γ΄Γ¥Γ±Γ¬Γ΅Γ΄Γ©Γ³Γ¬Γ―Γ½
        						finished = true;
        			} 
        				if(((Player)players.get(entry.getKey()-1)).getPlayerId()==2)
        				{
        					if(players.get(entry.getKey()-1) instanceof MinMaxPlayer) {  //Γ�Γ­ Γ¥Γ�Γ­Γ΅Γ© minMax Γ― Γ°Γ΅Γ�Γ�Γ΄Γ§Γ² Γ°Γ΅Γ�Γ±Γ­Γ¥Γ©Γ² Γ΄Γ§Γ­ getNextMove. Γ�Γ«Γ«Γ©ΓΎΓ² Γ°Γ΅Γ² Γ³Γ΄Γ§Γ­ else 
        						pathB = (((MinMaxPlayer) players.get(entry.getKey()-1)).getNextMove(board, (int)(pathB[2]), (int)(pathA[2])));
        					}
        					else if(players.get(entry.getKey()-1) instanceof HeuristicPlayer) {
        						pathB = ((HeuristicPlayer)players.get(entry.getKey()-1)).getNextMove((int)pathB[2]);
        					}
        					else
        					{
        						int [] array = new int[5];
        						int die = (int)(Math.random()*6+1);
        						double eval = (new HeuristicPlayer(99, "keno", 0, board).evaluate((int)pathB[2],die));
        						array = ((Player)players.get(entry.getKey()-1)).move((int)pathB[2], die);
        						pathB[0]=die;
        						pathB[1]=(int)(eval);
        					    for(int i=0; i<5; i++)
        					    	pathB[i+2]=array[i];
        					}
            				pBMoveLabel.setText("Move Score: " + Integer.toString(pathB[1]));
                    		winScore[1] = ((Player)players.get(1)).getScore() * 0.25 + pathB[2] * 0.75;
            				pBScoreLabel.setText("Total Score: " + Double.toString(winScore[1]));

        					if(pathA[2] >= board.getM()* board.getN() || pathB[2] >= board.getM()* board.getN()) //Γ³ΓµΓ­Γ¨Γ�Γ�Γ§ Γ΄Γ¥Γ±Γ¬Γ΅Γ΄Γ©Γ³Γ¬Γ―Γ½
        						finished = true;
        			}
        				roundLabel.setText("Round: "+ game.getRound());
        			}
        			try {
        			Thread.sleep(250);
        			}catch(InterruptedException ex) {
        				Thread.currentThread().interrupt();
        			}
        		} while (!finished);	
        		
        		winScore[0] = ((Player)players.get(0)).getScore() * 0.25 + pathA[2] * 0.75;						//ΓµΓ°Γ―Γ«Γ―Γ£Γ©Γ³Γ¬ΓΌΓ² Γ³Γ�Γ―Γ± Player
        		winScore[1] = ((Player)players.get(1)).getScore() * 0.25 + pathB[2] * 0.75;
        		int winner;
        		String endGame = "The game is over.\nThe winner is: ";
        		if(winScore[0] > winScore[1])
        			winner = 0;
        		else
        			winner = 1;
        		endGame += ((Player)players.get(winner)).getName();
        		endGame += "\nwith a joint win score of ";
        		endGame += Double.toString(winScore[((Player)players.get(winner)).getPlayerId()==1 ? 0 :1]);
        		endGame += "\nafter " + game.getRound() + " rounds.";
        		JOptionPane.showMessageDialog(window, endGame);
        				
        		play.setEnabled(false);
        	}
        });
        
        roundLabel.setVisible(true);
        playerALabel.setVisible(true);
        pAMoveLabel.setVisible(true);
        pAScoreLabel.setVisible(true);
        playerBLabel.setVisible(true);
        pBMoveLabel.setVisible(true);
        pBScoreLabel.setVisible(true);
        playerA.setVisible(true);
        playerB.setVisible(true);
        boardGen.setVisible(true);
        
        play.setVisible(true);
        play.setEnabled(false);
        
        quit.setVisible(true);
        arrayPanel.setVisible(true);
        jBoard.setVisible(true);
        window.setLayout(null);  
        window.setVisible(true);  

	}
	
	
	public static void main(String[] args) {
		Window w = new Window();
		//this is a huge main. 
	}

}
