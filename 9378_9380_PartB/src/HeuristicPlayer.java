//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

import java.util.ArrayList;

//Κλάση HeuristicPlayer κληρονομεί τα χαρακτηριστικά της Player και αντιπροσωπεύει τον παίκτη που "κλέβει". Ως επιπλέον μεταβλητές έχει μια ArrayList που ονομάζεται path που βοηθάει στο κλέψιμο και περιέχει
//όλες τις πληροφορίες για την κίνηση του HeuristicPlayer
public class HeuristicPlayer extends Player {

	private ArrayList <int[]> path;
	
	//constructor χωρίς όρισμα
	HeuristicPlayer()
	{ 
		super();
		path = new ArrayList<int[]>();
	}
	
	//constructor με ορίσματα τα playerId, name, score, board που αντιστοιχούν στις αντίστοιχες μεταβλητές της Player
	HeuristicPlayer(int playerId, String name, int score, Board board)
	{
		super( playerId,  name,  score,  board);
		path = new ArrayList<int[]>();
	}
	//constructor με όρισμα αντικείμο
	HeuristicPlayer(HeuristicPlayer H)
	{
		super((Player)H);
		path =H.path;
	}
	
	//Συνάρτηση evaluate με όρισμα έναν int currentPos που αντιστοιχεί στην παρούσα θέση του παίκτη και ένα int dice που είναι η ζαριά. Σκοπός της είναι να κάνει μια ανάλυση της κάθε ζαριάς που της δίνεται. 
	//Επιστρέφει την τιμή αξιολόγησης της κίνησης, χωρίς να κάνει μεταβολές στον πίνακα.
	double evaluate(int currentPos, int dice)
	{
		double factor = 0;
		int points = 0;
		int id = currentPos + dice;
		int start = -1;
		do {
			start = id;
			Apple apple = appleExists(id);
			if(apple.getAppleId() != 0) 
				points += apple.getPoints();
				
			Snake snake = snakeExists(id);
			if(snake.getSnakeId() != 0) {
				id = snake.getTailId();
			}
			else {
				Ladder ladder = ladderExists(id);
				if(ladder.getLadderId() != 0) {
					id = ladder.getDownStepId();
				}
			}
		}while(start != id);
		int steps = id - currentPos;
		factor = steps*0.75 + points*0.25;
		return factor;
		
	}
	
	//Συνάρτηση getNextMove. Επιστρέφει την νέα θέση του παίκτη. Δέχεται ως όρισμα την παρούσα θέση σε int.
	int getNextMove(int currentPos) {
		double [] moves = new double[6];
		double max = Double.NEGATIVE_INFINITY;
		int maxDie = 0;
		for(int i=0; i<6; i++)  //εύρεση μεγίστου
		{
			moves[i] = evaluate(currentPos, i+1);
			if(max<moves[i]) {
				max=moves[i];
				maxDie=i+1;
			}
		}
		int array[] = new int[5];
		array = move(currentPos,maxDie);
		int newPath[] = new int[]{maxDie, (int)max, array[0], array[1], array[2], array[3], array[4]};
		path.add(newPath);
		return array[0];
		
	}
	
	//συνάρτηση statistics. απλώς τυπώνει στατιστικά σχετικά με τον heuristicPlayer τα οποία περιέχονται στην μεταβλητή path
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