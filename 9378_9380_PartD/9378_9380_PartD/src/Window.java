//Σκαπέτης Χρήστος ΑΕΜ 9378 6944316621 ikofokots@ece.auth.gr
//Ηλίας Κωφοκώτσιος ΑΕΜ 9380 6933251534 skapetis@ece.auth.gr


//Εισαγωγή βιβλιοθηκών
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
public class Window {
	//Δήλωση των components του window
	Game game;
	JFrame window;
	Board board;
	JLabel roundLabel; 
	JLabel playerALabel; 
	JLabel playerBLabel;
	JLabel pAMoveLabel; 
	JLabel pBMoveLabel; 
	JLabel pAScoreLabel; 
	JLabel pBScoreLabel;
    JPanel buttonPanel;
    JPanel arrayPanel;
    JTable jBoard;
    JComboBox<String> playerA;
	JComboBox<String> playerB;
	JButton boardGen;
    JButton play;
    JButton quit;
    JLabel pawnA;
    JLabel pawnB;
    
    JLabel[] snakes;
    JLabel[] ladders;
    JLabel[] apples;

	//constructor του window
	Window(){
		//αρχικοποίηση μεταβλητών
		window = new JFrame("Snakes and Ladders");
		game = new Game(0);
		board = new Board(10,20,3,3,6);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setName("Snakes and Ladders");
		window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		roundLabel = new JLabel("Round: 0");
		playerALabel = new JLabel("Player A");
		playerBLabel = new JLabel("Player B");
		pAMoveLabel = new JLabel("Move Score: 0");
		pBMoveLabel = new JLabel("Move Score: 0");
		pAScoreLabel = new JLabel("Total Score: 0");
		pBScoreLabel = new JLabel("Total Score: 0");
        buttonPanel=new JPanel(); 
        arrayPanel = new JPanel();
        pawnA = new JLabel(new ImageIcon("./green-pawn.jpeg"));
        pawnB = new JLabel(new ImageIcon("./red-pawn.jpeg"));
        apples = new JLabel[board.getApples().length];
        snakes = new JLabel[board.getSnakes().length];
        snakes[0] = new JLabel(new ImageIcon("./snake1.png"));
        snakes[1] = new JLabel(new ImageIcon("./snake2.png"));
        snakes[2] = new JLabel(new ImageIcon("./snake1.png"));
		ladders = new JLabel[board.getLadders().length];
		for(int i=0; i<3; i++) {
			ladders[i] = new JLabel(new ImageIcon(".ladder1"));
			apples[i] = new JLabel();
			apples[i+3] = new JLabel();
		}
		
		playerALabel.setForeground(Color.GREEN);
		pAMoveLabel.setForeground(Color.GREEN);
		pAScoreLabel.setForeground(Color.GREEN);

		playerBLabel.setForeground(Color.RED);
		pBMoveLabel.setForeground(Color.RED);
		pBScoreLabel.setForeground(Color.RED);


        int n=board.getN(), m=board.getM();
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
        
        jBoard = new JTable(tiles, tiles[0]);

		String[] options = {"Simple Player", "Heuristic Player", "MinMax Player"};
		playerA = new JComboBox<String>(options);
		playerB = new JComboBox<String>(options);
        boardGen = new JButton("Generate Board");
        play = new JButton("Play");
        quit = new JButton("Quit");
        
        //Προσθήκη των components στο window και στα panels που θέλουμε
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
        arrayPanel.add(pawnA);
        arrayPanel.add(pawnB);
        arrayPanel.add(jBoard);
        for(int i=0; i<3;i++) {
        	arrayPanel.add(snakes[i]);
        	arrayPanel.add(ladders[i]);
        	arrayPanel.add(apples[i]);
        	arrayPanel.add(apples[3+i]);
        }
        
        //Τοποθέτηση των components στο παράθυρο
        roundLabel.setBounds(20, 20, 100, 30);
        playerALabel.setBounds(20, 600, 100, 30);
        pAMoveLabel.setBounds(20, 630, 100, 30);
        pAScoreLabel.setBounds(20, 660, 150, 30);
        playerBLabel.setBounds(window.getWidth()-20-170, 600, 100, 30);
        pBMoveLabel.setBounds(window.getWidth()-20-170, 630, 100, 30);
        pBScoreLabel.setBounds(window.getWidth()-20-170, 660, 150, 30);
        playerA.setBounds(20, 700, 150, 50);
        playerB.setBounds(window.getWidth()-20-170, 700, 150, 50);
        buttonPanel.setBounds(window.getWidth()/2 - 150,750,400,50); 
        arrayPanel.setBounds(100, 50, window.getWidth()-200, 500);
        
        jBoard.setRowHeight(50);  
        jBoard.setBounds(5, 5 , arrayPanel.getWidth(), arrayPanel.getHeight());
        jBoard.setShowHorizontalLines(true);
        jBoard.setShowVerticalLines(true);
        jBoard.setCellSelectionEnabled(false);
        jBoard.setGridColor(new Color(0,0,0));
        jBoard.setGridColor(new Color(150,150, 200));


        
        pawnA.setBounds(arrayPanel.getWidth()/board.getM()*3/10, arrayPanel.getHeight()-45, 40, 40);
        pawnB.setBounds(arrayPanel.getWidth()/board.getM()*6/10, arrayPanel.getHeight()-45, 40, 40);
        arrayPanel.setComponentZOrder(jBoard, 2);
        arrayPanel.setComponentZOrder(pawnA, 0);
        arrayPanel.setComponentZOrder(pawnB, 0);
          
        quit.addActionListener(new QuitAction());
        boardGen.addActionListener(new GenerateBoardAction());
        play.addActionListener(new PlayAction());
        
        //visibility
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
        pawnA.setVisible(true);
        pawnB.setVisible(true);
        
        arrayPanel.setLayout(null);
        jBoard.setVisible(true);
        window.setLayout(null);  
        window.setVisible(true);  

	}
	
	//κατοπτρισμός εικόνας 
	Icon flipHorizontally(Icon image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getIconWidth(), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage src = new BufferedImage(image.getIconWidth(),image.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = src.createGraphics();
		image.paintIcon(null, g, 0,0);
		g.dispose();
		BufferedImage dest = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		op.filter(src, dest);
		image = new ImageIcon(dest);
		return image;
	}

	//υλοποίηση κουμπί quit
	class QuitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			window.dispose();
		}
	}
	
	//υλοποίηση κουμπί generateBoard
	class GenerateBoardAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
    		board.createBoard();
    		board.createElementBoard();
            play.setEnabled(true);
            game = new Game(0);
    		roundLabel.setText("Round: 0");
    		playerALabel.setText("Player A");
    		playerBLabel.setText("Player B");
    		pAMoveLabel.setText("Move Score: 0");
    		pBMoveLabel.setText("Move Score: 0");
    		pAScoreLabel.setText("Total Score: 0");
    		pBScoreLabel.setText("Total Score: 0");
    		int[][] snakeCoord = new int[3][4];
    		int[][] laddCoord = new int[3][4];
    		int[][] appleCoord = new int[6][2];
    		int n = board.getN(), m = board.getM();
    		for(int i=0; i<3;i++) {
    			snakeCoord[i][0] = (n-1)-(board.getSnakes()[i].getHeadId()-1)/m;
    			snakeCoord[i][1] = (n-1-snakeCoord[i][0])%2 == 0 ? 
    				(board.getSnakes()[i].getHeadId()-1)%m : (m-1)-(board.getSnakes()[i].getHeadId()-1)%m;
    			snakeCoord[i][2] = (n-1)-(board.getSnakes()[i].getTailId()-1)/m;
    			snakeCoord[i][3] = (n-1-snakeCoord[i][2])%2 == 0 ? 
    					(board.getSnakes()[i].getTailId()-1)%m : (m-1)-(board.getSnakes()[i].getTailId()-1)%m;
    			// Για τα φίδια δουλεύει
    					
    			laddCoord[i][0] = (n-1)-(board.getLadders()[i].getDownStepId()-1)/m;
    			laddCoord[i][1] = (n-1-laddCoord[i][0])%2 == 0 ? 
    				(board.getLadders()[i].getDownStepId()-1)%m : (m-1)-(board.getLadders()[i].getDownStepId()-1)%m;
    			laddCoord[i][2] = (n-1)-(board.getLadders()[i].getUpStepId()-1)/m;
    	    	laddCoord[i][3] = (n-1-laddCoord[i][2])%2 == 0 ? 
    	    		(board.getLadders()[i].getUpStepId()-1)%m : (m-1)-(board.getLadders()[i].getUpStepId()-1)%m;
    	    	
    	    	appleCoord[i][0] = (n-1)-(board.getApples()[i].getAppleTileId()-1)/m;
        	    appleCoord[i][1] = (n-1-appleCoord[i][0])%2 == 0 ? 
        			(board.getApples()[i].getAppleTileId()-1)%m : (m-1)-(board.getApples()[i].getAppleTileId()-1)%m;
        			System.out.println(appleCoord[i][0] + " " + appleCoord[i][1] + " " + board.getApples()[i].getAppleTileId());
        		appleCoord[i+3][0] = (n-1)-(board.getApples()[i+3].getAppleTileId()-1)/m;
        	   	appleCoord[i+3][1] = (n-1-appleCoord[i+3][0])%2 == 0 ? 
        	    	(board.getApples()[i+3].getAppleTileId()-1)%m : (m-1)-(board.getApples()[i+3].getAppleTileId()-1)%m;
        	    	System.out.println(appleCoord[i+3][0] + " " + appleCoord[i+3][1] + " " + board.getApples()[i+3].getAppleTileId());
        	    
        	    // Φίδια
        	    int height = -(snakeCoord[i][0] - snakeCoord[i][2])*50 + 30;
        	    height = height <= 50 ? 40 : height;
        	    int width = -(snakeCoord[i][1] - snakeCoord[i][3])*arrayPanel.getWidth()/m;
        	    width = (width<=arrayPanel.getWidth()/m && width >= 0) ? arrayPanel.getWidth()/m: width;
        	    
        	    Icon image = new ImageIcon(new ImageIcon("./snake"+ Integer.toString(i+1)+".png").getImage().getScaledInstance(Math.abs(width) + arrayPanel.getWidth()/(2*m), height, Image.SCALE_SMOOTH));
        	    if(width < 0) {
        	    	image = flipHorizontally(image);
        	    	snakes[i].setIcon(image);
            	    snakes[i].setBounds((snakeCoord[i][1]+1)*arrayPanel.getWidth()/m - image.getIconWidth(), snakeCoord[i][0]*50 + 10, image.getIconWidth(), image.getIconHeight());
        	    }
        	    else {
        	    	snakes[i].setIcon(image);
            	    snakes[i].setBounds(snakeCoord[i][1]*arrayPanel.getWidth()/m, snakeCoord[i][0]*50 + 10, image.getIconWidth(), image.getIconHeight());
        	    }
        	    snakes[i].setVisible(true);
        	    arrayPanel.setComponentZOrder(snakes[i], 0);
        	    
        	    //Σκάλες
        	    height = -(laddCoord[i][0] - laddCoord[i][2])*50 + 30;
        	    height = height <= 50 ? 40 : height;
        	    width = -(laddCoord[i][1] - laddCoord[i][3])*arrayPanel.getWidth()/m;
        	    width = (width<=arrayPanel.getWidth()/m && width >= 0) ? arrayPanel.getWidth()/m: width;
        	    
        	    
        	    image = new ImageIcon(new ImageIcon("./ladder1.png").getImage().getScaledInstance((int)(Math.abs(width)*(width<0?1.5f:6/5f)) + arrayPanel.getWidth()/(2*m), height*7/6, Image.SCALE_SMOOTH));
        	    if(width < 0) {
        	    	image = flipHorizontally(image);
        	    	ladders[i].setIcon(image);
            	    ladders[i].setBounds((laddCoord[i][1]+1)*arrayPanel.getWidth()/m - image.getIconWidth()+(int)(-width*0.2f), laddCoord[i][0]*50 + 10, image.getIconWidth(), image.getIconHeight());
        	    }
        	    else {
        	    	ladders[i].setIcon(image);
            	    ladders[i].setBounds(laddCoord[i][1]*arrayPanel.getWidth()/m,laddCoord[i][0]*50, image.getIconWidth(), image.getIconHeight());
        	    }
        	    ladders[i].setVisible(true);
        	    arrayPanel.setComponentZOrder(ladders[i], 0);
        	    
        	    //Μήλα
        	    if(board.getApples()[i].getColor()=="Red")
        	    	apples[i].setIcon(new ImageIcon("./red-apple.png"));
        	    else 
        	    	apples[i].setIcon(new ImageIcon("./black-apple.png"));
        	   	apples[i].setBounds(appleCoord[i][1]*arrayPanel.getWidth()/m+30,appleCoord[i][0]*50+5 , 70, 50);
        	   	
        	   	if(board.getApples()[i+3].getColor()=="Red")
        	    	apples[i+3].setIcon(new ImageIcon("./red-apple.png"));
        	   	else
        	    	apples[i+3].setIcon(new ImageIcon("./black-apple.png"));
        	   	apples[i+3].setBounds(appleCoord[i+3][1]*arrayPanel.getWidth()/m+30,appleCoord[i+3][0]*50+5 , 70, 50);

        	   	apples[i].setText(Integer.toString(board.getApples()[i].getPoints()));
        	   	apples[i+3].setText(Integer.toString(board.getApples()[i+3].getPoints()));
        	   	apples[i].setIconTextGap(-25);
        	   	apples[i].setForeground(Color.WHITE);
        	   	apples[i+3].setIconTextGap(-25);
        	   	apples[i+3].setForeground(Color.WHITE);

        	   	apples[i].setVisible(true);
        	   	apples[i+3].setVisible(true);
        	   	arrayPanel.setComponentZOrder(apples[i], 0);
        	   	arrayPanel.setComponentZOrder(apples[i+3], 0);

    		}
    	}
	}

	//Υλοποίηση κουμπιού play
	class PlayAction implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		PlayWorker play = new PlayWorker();
    		play.execute();
    	}
	}
	
	class PlayWorker extends SwingWorker<Boolean, double[][]>{

		@Override
		//υλοποίηση παιχνιδιού όπως στην προήγουμενη εργασία η αντίστοιχη main 
		protected Boolean doInBackground() throws Exception {
			String AChoice = playerA.getItemAt(playerA.getSelectedIndex());
    		String BChoice = playerB.getItemAt(playerB.getSelectedIndex());
    		Object playerA;
    		Object playerB;
    		//Επιλογή τύπου παίκτη
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
    		players.add(playerA);					
    		players.add(playerB);				
    		Map<Integer, Integer> dice = game.setTurns(players);
    		if(dice == null) {
    			return false;
    		}
    		boolean finished = false;
    		int [] pathA = new int[7];
    		int [] pathB = new int[7];
    		double[] winScore = new double[3];
    		do {
    			game.setRound(game.getRound() + 1);
    			for (Map.Entry<Integer, Integer> entry : dice.entrySet()) {

    				if(((Player)players.get(entry.getKey()-1)).getPlayerId()==1)
    				{
    					if(players.get(entry.getKey()-1) instanceof MinMaxPlayer) { 
    						pathA = (((MinMaxPlayer) players.get(entry.getKey()-1)).getNextMove(board, (int)(pathA[2]), (int)(pathB[2])));
    					}
    					else if(players.get(entry.getKey()-1) instanceof HeuristicPlayer) {
    						pathA = ((HeuristicPlayer)players.get(entry.getKey()-1)).getNextMove((int)pathA[2]);
    					}
    					else
    					{
    						int [] array = new int[5];
    						int die = (int)(Math.random()*6+1);
    						double eval = (new HeuristicPlayer(99, "", 0, board).evaluate((int)pathA[2],die));
    						array = ((Player)players.get(entry.getKey()-1)).move((int)pathA[2], die);
    						pathA[0]=die;
    						pathA[1]=(int)(eval);
    					    for(int i=0; i<5; i++)
    					    	pathA[i+2]=array[i];
    					}
    					
    					winScore[0] = ((Player)players.get(0)).getScore() * 0.25 + pathA[2] * 0.75;
    					double[][] toPublish = new double[2][8];
    					for(int i=0; i<7;i++) {
    						toPublish[0][i] = pathA[i];
    						toPublish[1][i] = pathB[i];
    					}
    					toPublish[0][7]=winScore[0];
    					toPublish[1][7]=winScore[1];
    					publish(toPublish);
    					
    					Thread.sleep(300);
    					
    					if(pathA[2] >= board.getM()* board.getN() || pathB[2] >= board.getM()* board.getN())
    						finished = true;
    			} 
    				if(((Player)players.get(entry.getKey()-1)).getPlayerId()==2)
    				{
    					if(players.get(entry.getKey()-1) instanceof MinMaxPlayer) { 
    						pathB = (((MinMaxPlayer) players.get(entry.getKey()-1)).getNextMove(board, (int)(pathB[2]), (int)(pathA[2])));
    					}
    					else if(players.get(entry.getKey()-1) instanceof HeuristicPlayer) {
    						pathB = ((HeuristicPlayer)players.get(entry.getKey()-1)).getNextMove((int)pathB[2]);
    					}
    					else
    					{
    						int [] array = new int[5];
    						int die = (int)(Math.random()*6+1);
    						double eval = (new HeuristicPlayer(99, "", 0, board).evaluate((int)pathB[2],die));
    						array = ((Player)players.get(entry.getKey()-1)).move((int)pathB[2], die);
    						pathB[0]=die;
    						pathB[1]=(int)(eval);
    					    for(int i=0; i<5; i++)
    					    	pathB[i+2]=array[i];
    					}
    		    		winScore[1] = ((Player)players.get(1)).getScore() * 0.25 + pathB[2] * 0.75;
    		    		double[][] toPublish = new double[2][8];
    					for(int i=0; i<7;i++) {
    						toPublish[0][i] = pathA[i];
    						toPublish[1][i] = pathB[i];
    					}
    					toPublish[0][7]=winScore[0];
    					toPublish[1][7]=winScore[1];
    					publish(toPublish);
    					
    					Thread.sleep(300);

    					if(pathA[2] >= board.getM()* board.getN() || pathB[2] >= board.getM()* board.getN())
    						finished = true;
    				}
    			}
    		} while (!finished);
    		winScore[0] = ((Player)players.get(0)).getScore() * 0.25 + pathA[2] * 0.75;						
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

			return true;
		}
		
		//Συνάρτηση η οποία φροντίζει να ανανεώνει τα στοιχεία μετά από κάθε κίνηση. Αλλάζει τους πόντους, τα rounds καιθώς σβήνει και τις σκάλες και τα μύλα όποτε 
		//απαιτείται απο την εκφώνηση
		@Override
		protected void	process(List<double[][]> data) {
			boardGen.setEnabled(false);
			play.setEnabled(false);
			double[] pathA = data.get(0)[0];
			double[] pathB = data.get(0)[1];
			double[] winScore = {pathA[7], pathB[7]};
			pAMoveLabel.setText("Move Score: " + Integer.toString((int)pathA[1]));
			pAScoreLabel.setText("Total Score: " + Double.toString(winScore[0]));
			pBMoveLabel.setText("Move Score: " + Integer.toString((int)pathB[1]));
			pBScoreLabel.setText("Total Score: " + Double.toString(winScore[1]));
			roundLabel.setText("Round: "+ game.getRound());
			movePawns((int)pathA[2],(int)pathB[2]);
			if(pathA[4] == 1 || pathB[4] == 1) {
				for(int i=0; i<6; i++) 
					if(board.getApples()[i].getAppleTileId() == -1) 
						apples[i].setVisible(false);
				for(int i=0; i<3; i++)
					if(board.getLadders()[i].getBroken() == true)
						ladders[i].setVisible(false);
			}
		}
		
		//Συνάρτηση που υλοποιεί την κίνηση των πιονών με χρήση συντεταγμένων
		void movePawns(int positionA, int positionB) {
			Point a = new Point(pawnA.getLocation());
			Point b = new Point(pawnB.getLocation());
			int heightA = (board.getN()-1)-((int)positionA-1)/board.getM();
			int lengthA = (board.getN()-1-heightA)%2 == 0 ? ((int)positionA-1)%board.getM() : (board.getM()-1)-((int)positionA-1)%board.getM();
			int heightB = (board.getN()-1)-((int)positionB-1)/board.getM();
			int lengthB = (board.getN()-1-heightB)%2 == 0 ? ((int)positionB-1)%board.getM() : (board.getM()-1)-((int)positionB-1)%board.getM();
			Point aNew = new Point(lengthA*arrayPanel.getWidth()/board.getM()+30, heightA*50 + 10);
			Point bNew = new Point(lengthB*arrayPanel.getWidth()/board.getM()+60, heightB*50 + 10);
			if(!(a.equals(aNew))) {
				pawnA.setLocation(aNew);
			}
			if(!(b.equals(bNew))) {
				pawnB.setLocation(bNew);
			}
			
		}
		
		//Συνάρτηση που επαναφέρει το πίονια στην αρχή του board.
		@Override
		protected void done() {
			boardGen.setEnabled(true);
			pawnA.setLocation(new Point(30, arrayPanel.getHeight()-45));
			pawnB.setLocation(new Point(60, arrayPanel.getHeight()-45));
		}
		
	}
	
	//main
	public static void main(String[] args) {
		new Window();
	}
}
