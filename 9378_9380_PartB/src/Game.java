//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8


import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

//κλάση game που αντιπροσωπεύει την παρτίδα. Έχει μια μεταβλητή τύπου int, την round, η οποία δηλώνει τον γύρο στον οποίο βρισκόμαστε.
public class Game {
	private int round;

	//  constructor
	public Game() {
		round = 0;
	}

	// Constructor 
	public Game(int r) {
		round = r;
	}

	// Setter
	public void setRound(int round) {
		if(round > 0)
			this.round = round;
		else
			System.out.println("Setter Error: The game round cannot be less than 1. No alterations have been made.");
	}

	//Getter
	public int getRound() {
		return round;
	}

	
	//Συνάρτηση setTurns δέχεται σαν όρισμα ένα ArrayList τύπου Object στο οποίο περιέχονται όλοι οι παίκτες.
	public Map<Integer, Integer> setTurns(ArrayList<Object> players)
	{
		//if clause στην οποία ελέγχεται αν όντως οι παίκτες είναι 6 ή λιγότεροι
		if(players.size() > 6) {
			System.out.println("Error! Number of Players is too great. (" + players.size() + "). Operation Aborted. Game will terminate.");
			return null;
		}
		
		Map<Integer, Integer> dice = new HashMap<Integer, Integer>();
		for(int i=0; i<players.size(); i++)	//τυχαίες ζαριές για όλους
			dice.put(((Player) players.get(i)).getPlayerId(), (int) (Math.random() * 6 + 1));
		for(int i=1; i<=players.size(); i++)	//αν δύο παίκτες ρίχνουν το ίδιο ζάρι ξαναρίχνει ο δεύτερος μέχρι να φέρει διαφορετικό.
		{
			boolean exists = false;
			do {
				exists = false;
				dice.put(((Player) players.get(i-1)).getPlayerId(), (int) (Math.random() * 6 + 1));
				
				for(int j=1; j<=players.size(); j++) {
					if (i != j) {
						exists = dice.get(i) == dice.get(j) ? true : false;
					}
				}
			} while (exists);
		}
		
		Map<Integer, Integer> diceSorted = new LinkedHashMap<Integer, Integer>();
		int size = dice.size();

		for(int i=1; i<=size; i++) { //Εύρεση μικρότερης ζαριάς και τοποθέτηση του παίκτη στο LinkedHash Map κατά αύξουσα σειρά. 
			AbstractMap.SimpleEntry<Integer, Integer> min = new SimpleEntry<Integer, Integer>(dice.size(), 7);
			for(int j=1; j<=size; j++) {
				if(dice.get(j)==null)
					continue;
				if(dice.get(j) < min.getValue()) {
					min = new SimpleEntry<Integer, Integer>(j, dice.get(j));
				}
			}
			dice.remove(min.getKey());
			diceSorted.put(min.getKey(), min.getValue());
		}
		return diceSorted;
	}
	
	
	//συνάρτηση main
	public static void main(String[] args) {
		Game game = new Game(0);
		Board board = new Board(10, 20, 3, 3, 6);
		board.createBoard();
		board.createElementBoard();
		ArrayList<Object> players = new ArrayList<Object>();
		players.add(new Player(1, "Christos", 0, board));					//ένας φυσιολογικός παίκτης
		players.add(new HeuristicPlayer(2, "Ilias", 0, board));				//παίκτης τύπου heuristicPlayer
		System.out.println("Game begins. Players are: " + ((Player)players.get(0)).getName() + " (Player) and "+ ((HeuristicPlayer)players.get(1)).getName() + " (Heuristic Player).");
		Map<Integer, Integer> dice = game.setTurns(players);
		if(dice == null) {
			return;
		}
		boolean finished = false;
		int [] array = new int[5];
		int id = 0;
		
		do { //Ζαριές μέχρι τουλάχιστον ένας να τερματίσει
			game.setRound(game.getRound() + 1);
			for (Map.Entry<Integer, Integer> entry : dice.entrySet()) {
				if(players.get(entry.getKey()-1) instanceof HeuristicPlayer) {  //Αν είναι heuristic ο παίκτης παίρνεις την getNextMove. Αλλιώς πας στην else
					id = ((HeuristicPlayer) players.get(entry.getKey()-1)).getNextMove(id);
				}
				else {
					array = ((Player)players.get(entry.getKey()-1)).move(array[0], (int) (Math.random()*6+1));
				}
				if(id >= board.getM()* board.getN() || array[0] >= board.getM()* board.getN()) //συνθήκη τερματισμού
					finished = true;
			} 
		} while (!finished);
		
		double[] winScore = new double[2];
		winScore[0] = ((Player)players.get(0)).getScore() * 0.25 + array[0] * 0.75;				//υπολογισμός σκορ Player
		winScore[1] = ((HeuristicPlayer)players.get(1)).getScore() * 0.25 + id * 0.75;			//υπολογισμός σκορ HeuristicPlayer
		
		((HeuristicPlayer)players.get(1)).statistics();											//statistics heuristicPlayer
		
		System.out.println("The game has ended after " + game.getRound() + " rounds.");
		if(winScore[0] < winScore[1])	//ανάδειξη νικητή με την επιλεγμένη φόρμουλα 0,25*πόντοι+0,75*θέση
			System.out.println("The winner is: " + ((HeuristicPlayer)players.get(1)).getName() + " with a joint win factor "
					            + "of " + winScore[1] + ". " + ((Player)players.get(0)).getName() + " has a joint win factor of " + winScore[0] + ".");
		else if(winScore[0] > winScore[1])
			System.out.println("The winner is: " + ((Player)players.get(0)).getName()  + " with a joint win factor "
		            + "of " + winScore[0] + ". " + ((HeuristicPlayer)players.get(1)).getName() + " has a joint win factor of " + winScore[1] + ".");
		else if(array[0] < id)
			System.out.println("The winner is: " + ((HeuristicPlayer)players.get(1)).getName() + " with a joint win factor "
		            + "of " + winScore[1] + ". " + ((Player)players.get(0)).getName() + " also has a joint win factor of " + winScore[0] + ". "
		            + "\nBecause the players had the same joint win factor, the winner was the one who finished first.");
		else if(array[0] > id)
			System.out.println("The winner is: " + ((Player)players.get(0)).getName()  + " with a joint win factor "
		            + "of " + winScore[0] + ". " + ((HeuristicPlayer)players.get(1)).getName() + " has a joint win factor of " + winScore[1] + "."
		            + "\nBecause the players had the same joint win factor, the winner was the one who finished first.");
		else 
			System.out.println("The game has ended in a tie.");
	}

}
