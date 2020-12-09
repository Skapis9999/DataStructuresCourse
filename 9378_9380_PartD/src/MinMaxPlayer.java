//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8

import java.util.ArrayList;


/* MinMaxPlayer Class
 * Κληρονομεί τα χαρακτηριστικά της Player
 * Περιέχεται η  ArraList τύπου int[] path
 * που περιγράφει την κίνηση
 * Περιέχονται δύο constructors, ο ένας με ορίσματα μεταβλητές και ο άλλος χωρίς
 * Περιέχεται 1 getter για την μεταβλητή της κλάσης αυτής.
 * Περιέχονται οι συναρτήσεις evaluate, getNextMove, createMySubtree, createOpponentSubtree
 * chooseMinMaxMove, minMax και statistics των οποίων η λειτουργία περιγράφεται παρακάτω.
 */
public class MinMaxPlayer extends Player {
	private ArrayList <int[]> path;
 
	
	//Κενός constructor
	public MinMaxPlayer() {
		super();
	}

	// Constructor με ορίσματα
	public MinMaxPlayer(int playerId, String name, int score, Board board) {
		super(playerId, name, score, board);
		this.path = new ArrayList<int[]>();
	}
	
	ArrayList<int[]> getPath(){
		return path;
	}
	
	//Συνάρτηση evaluate με όρισμα έναν int currentPos που αντιστοιχεί στην παρούσα θέση του παίκτη, ένα int dice που είναι η ζαριά και ένα board.
	//Σκοπός της είναι να κάνει μια ανάλυση της κάθε ζαριάς που της δίνεται. 
			//Επιστρέφει την τιμή αξιολόγησης της κίνησης, χωρίς να κάνει μεταβολές στον πίνακα.
		double evaluate(int currentPos, int dice, Board board)
		{
			double factor = 0;
			int points = 0;
			int id = currentPos + dice;
			int start = -1;
			do {
				start = id;
				Apple apple = appleExists(id, board);
				if(apple.getAppleId() != 0) 
					points += apple.getPoints();
					
				Snake snake = snakeExists(id, board);
				if(snake.getSnakeId() != 0) {
					id = snake.getTailId();
				}
				else {
					Ladder ladder = ladderExists(id, board);
					if(ladder.getLadderId() != 0) {
						id = ladder.getDownStepId();
					}
				}
			}while(start != id);
			int steps = id - currentPos;
			factor = steps*0.75 + points*0.25;
			return factor;
			
		}
		/* Συνάρτηση appleExists
		 * Η συνάρτηση αυτή αναζητά στο πλακίδιο με id το όρισμα της, εάν υπάρχει μήλο.
		 * Εάν βρει μήλο στο πλακίδιο, τότε το επιστρέφει. Αλλιώς, επιστρέφει ένα κενό μήλο.
		 */
			protected Apple appleExists(int id, Board board) {
				for(int i=0; i<board.getApples().length; i++)
					if (board.getApples()[i].getAppleTileId() == id)
						return board.getApples()[i];
				return new Apple();
			}

			/* Συνάρτηση snakeExists
			* Η συνάρτηση αυτή αναζητά στο πλακίδιο με id το όρισμα της, εάν υπάρχει φίδι.
			* Εάν βρει φίδι στο πλακίδιο, τότε το επιστρέφει. Αλλιώς, επιστρέφει ένα κενό φίδι.
			*/
			protected Snake snakeExists(int id, Board board) {
				for(int i=0; i< board.getSnakes().length; i++)
					if (board.getSnakes()[i].getHeadId() == id)
						return board.getSnakes()[i];
				return new Snake();
			}

			/* Συνάρτηση ladderExists
			* Η συνάρτηση αυτή αναζητά στο πλακίδιο με id το όρισμα της, εάν υπάρχει σκάλα.
			* Εάν βρει σκάλα στο πλακίδιο, τότε την επιστρέφει. Αλλιώς, επιστρέφει μια κενή σκάλα.
			*/
			protected Ladder ladderExists(int id, Board board) {
				for(int i=0; i< board.getLadders().length; i++)
					if (board.getLadders()[i].getUpStepId() == id && board.getLadders()[i].getBroken() == false)
						return board.getLadders()[i];
				return new Ladder();
			}
			
			/* Συνάρτηση getNextMove
			 * Έχει ως ορίσματα το board τύπου Board, και 2 ints currentPos και opponentCurrentPos
			 * Τα οποία αντιστοιχούν στην θέση του παίκτη και του αντιπάλου 
			 * Υλοποιεί την επόμενη κίνηση του παίκτη			 * 
			 */
			
			int[] getNextMove(Board board, int currentPos, int opponentCurrentPos)
			{
				int dice=0;   
				//Creating the first node which does not signify any move
				Node  root=new Node(null,0,null,board, 0);
				createMySubtree(root, 1,currentPos, opponentCurrentPos, 0); //δημιουργία δέντρου
				dice = chooseMinMaxMove(root);								//εύρεση βέλτιστης ζαριάς με την συνάρτηση MinMaxMove 
				int array[] = new int[5];
				double eval = evaluate(currentPos, dice, board);
				array = move(currentPos, dice);								//array της move
				int newPath[] = new int[]{dice, (int)eval, array[0], array[1], array[2], array[3], array[4]};
				path.add(newPath);
				return newPath;												//επιστροφή των στοιχείων της κίνησης 
			}
			

			/*Συνάρτηση createMySubtree
			 * Έχει ως ορίσματα Node parent,  3 int depth, currentPos, opponentCurrentPosκαι έναν double parentEval
			 * Τα οποία αντιστοιχούν στον πατέρα-κόμβο, στο βάθος στην θέση του παίκτη, στην θέση του αντιπάλου και στο eval του πατέρα.
			 * Δημιουργεί τα depth στο δέντρο που ανήκουν στην κίνηση του παίκτη
			 */
			void createMySubtree(Node parent, int depth, int currentPos, int opponentCurrentPos, double parentEval) 
			{
				if(depth > 6)  /// Depth (Εδώ πέρα το έχουμε βάλει 6)		//Συνθήκη που επιστρέφει όταν φτάσουμε στο τέλος του δέντρου
					return;
				for(int i=1; i<=6; i++)
				{
					Board board = new Board(parent.getNodeBoard());			//αντίγραφο του board
					double eval = evaluate(currentPos, i, board);			//αξιολόγηση παίκτη
					Player p = new Player(0, "Test", 0, board);				//δοκιμαστικός παίκτης που υλοποιεί την υποθετική κίνηση 
					int[] array = p.move(currentPos, i);					//move 
					if(array[0]==board.getN()*board.getM())
						eval = Double.POSITIVE_INFINITY;					//+άπειρο αν έχει νικήσει ο παίκτης 
					if(parentEval == Double.NEGATIVE_INFINITY) 
						eval = Double.NEGATIVE_INFINITY;					//-άπειρο αν έχει νικήσει ο αντίπαλος 
					Node newNode = new Node(parent, depth, new int[]{currentPos, i}, board, eval);	//νέος κόμβος παιδί 
					parent.getChildren().add(i-1, newNode);
					createOpponentSubtree(newNode, depth+1, array[0], opponentCurrentPos, eval);	//καλεί την opponentSubtree
				}				
			}
				/*Συνάρτηση createOpponentSubtree
				 * Έχει ως ορίσματα Node parent,  3 int depth, currentPos, opponentCurrentPosκαι έναν double parentEval
				 * Τα οποία αντιστοιχούν στον πατέρα-κόμβο, στο βάθος στην θέση του αντιπάλου, στην θέση του παίκτη και στο eval του πατέρα.
				 * Δημιουργεί τα depth στο δέντρο που ανήκουν στην κίνηση του αντιπάλου
				 */			
			private void createOpponentSubtree(Node parent, int depth, int currentPos, int opponentCurrentPos, double parentEval) 
			{
				for(int i=1; i<=6; i++)
				{
					Board board = new Board(parent.getNodeBoard());					//αντίγραφο του board
					double eval = -evaluate(opponentCurrentPos, i, board);			//αξιολόγηση παίκτη
					Player p = new Player(0, "Test", 0, board);						//δοκιμαστικός παίκτης που υλοποιεί την υποθετική κίνηση 
					int[] array = p.move(opponentCurrentPos, i);					//move
					if(array[0]==board.getN()*board.getM())
						eval = Double.NEGATIVE_INFINITY;							//-άπειρο αν έχει νικήσει ο αντίπαλος 					
					if(parentEval == Double.POSITIVE_INFINITY)
						eval = Double.POSITIVE_INFINITY;							//+άπειρο αν έχει νικήσει ο παίκτης 
					//System.out.println("op"+eval);
					Node newNode = new Node(parent, depth, new int[]{opponentCurrentPos, i}, board, eval);//νέος κόμβος παιδί 
					parent.getChildren().add(newNode);
					createMySubtree(newNode, depth+1, currentPos, array[0], eval);	//καλεί την MySubtree
				}			
			}

			/*Συνάρτηση chooseMinMaxMove
			 * Έχει ως ορίσμα ένα Node root
			 * Το οποίο αντιστοιχεί στον 1ο κόμβο τον root
			 * Υπολογίζει την βέλτιστη ζαριά
			 */					
			int chooseMinMaxMove(Node root)
			{
				double eval = minMax(root);						//κληση της MinMax για το root που επιστρέφει το maxEvaluation
				//System.out.println(eval + "++++++");
				int index = 0;
				for(int i=0; i<root.getChildren().size(); i++) {						//εύρεση πιο ζάρι έχει το maxEvaluation
					if(root.getChildren().get(i).getNodeEvaluation() == eval) {
						index = i;
					}
				}
				//System.out.println(root.getChildren().get(index).getNodeMove()[1]+"-----");
				return root.getChildren().get(index).getNodeMove()[1];						//επιστροφή του ζαριού
				//return index+1;
			}
			/*Συνάρτηση minMax
			 * Έχει ως ορίσμα ένα Node root
			 * Το οποίο αντιστοιχεί στον 1ο κόμβο τον root
			 * Υπολογίζει την βέλτιστη αξιολόγηση
			 */	
			double minMax(Node root) {
				if(root.getNodeDepth() == 6) { //depth 6             //στον τελευταίο κόμβο επιστρέφεται η αξιολόγηση της κίνησης
					return root.getNodeEvaluation();
				}
				double min = 10000;
				double max = -10000;
				if(root.getNodeDepth() % 2 == 1) { // We play
					for(int i=0; i<root.getChildren().size(); i++)
					{
						double temp = minMax(root.getChildren().get(i));					//εύρεση βέλτιστης κίνησης αντιπάλου (min)
						if(min >  temp)
							min = temp;
					}
					//System.out.println("prin "+min+ " " +root.getNodeEvaluation());
					root.setNodeEvaluation(min+root.getNodeEvaluation());					//Συνολική αξιολόγηση= αξιολόγηση κίνησης+συνολική αξιολόγηση πατέρα
					//System.out.println("meta "+min+ " " +root.getNodeEvaluation());
					return root.getNodeEvaluation();
				}
				else { // Opponent plays
					for(int i=0; i<root.getChildren().size(); i++) {
						double temp=minMax(root.getChildren().get(i));						//εύρεση βέλτιστης κίνησης παίκτη (max)
						if(max < temp)
							max = temp;
					}
					//System.out.println("prin max "+max+ " " +root.getNodeEvaluation());
					root.setNodeEvaluation(max+root.getNodeEvaluation());					//Συνολική αξιολόγηση= αξιολόγηση κίνησης+συνολική αξιολόγηση πατέρα
					//System.out.println("meta max"+max+ " " +root.getNodeEvaluation());
					return root.getNodeEvaluation();
					
				}
			}
			
			//συνάρτηση statistics απλώς τυπώνει στατιστικά σχετικά με τον MinMaxPlayer τα οποία περιέχονται στην μεταβλητή path
			void statistics() {
				int []roundStats = new int[7];
				int snakes = 0, ladders = 0, redApples = 0, blackApples = 0,  round = 1;
				for(int i=0; i<path.size(); i++) {
					roundStats = path.get(i);
					System.out.println("Round: " + round);
					round++;
					System.out.print("Die chosen: " + roundStats[0]  + " ");
					System.out.println("The chosen move gives " + roundStats[1] + " and takes the player to tile " + roundStats[2]);
					System.out.print(roundStats[3] == 0 ? "" : ("The player was bitten by " + roundStats[3] + " snake(s)\n"));
					snakes = roundStats[3] == 0 ? snakes : snakes + 1;
					System.out.print(roundStats[4] == 0 ? "" : ("The player used " + roundStats[4] + " ladder(s)\n"));
					ladders = roundStats[4] == 0 ? ladders : ladders + 1;
					System.out.print(roundStats[5] == 0 ? "" : ("The player ate " + roundStats[5] + " red apple(s)\n"));
					redApples = roundStats[5] == 0 ? redApples : redApples + 1;
					System.out.print(roundStats[6] == 0 ? "" : ("The player ate " + roundStats[6] + " black apple(s)\n"));
					blackApples = roundStats[6] == 0 ? blackApples : blackApples + 1;
					}
				System.out.println("Total Snakes Bitten: " + snakes);
				System.out.println("Total Ladders Used: " + ladders);
				System.out.println("Total Red Apples Eaten: " + redApples);
				System.out.println("Total Black Apples Eaten: " + blackApples);
			}
}
