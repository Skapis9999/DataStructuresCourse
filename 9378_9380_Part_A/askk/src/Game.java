//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8

/* Game Class
 * Περιέχεται η μεταβλητή round, τύπου int, η οποία αντιτοιχεί στους γύρους του παιχνιδιού.
 * Περιέχονται δυο constructors, ένας με ορίσματα και ένας χωρίς.
 * Επίσης, περιέχεται η συνάρτηση main του project.
 * Ακόμη, περιέχονται ένας getter και ένας setter για την μεταβλητή της κλάσης.
 */
public class Game {
	private int round;

	// Κενός constructor
	public Game() {
		round = 0;
	}

	// Constructor με ορίσματα
	public Game(int r) {
		round = r;
	}

	// Setter
	public void setRound(int round) {
		if(round > 0) // Έλεγχος για την ορθότητα της τιμής του ορίσματος
			this.round = round;
		else
			System.out.println("Setter Error: The game round cannot be less than 1. No alterations have been made.");
	}

	//Getter
	public int getRound() {
		return round;
	}

	/* Ακολουθεί η συνάρτηση main.
	 * Δημιουργούνται ένα αντικείμενο τύπου Game, ένα τύπου Board και ένας πίνακας
	 * με δυο αντικείμενα τύπου Player, που αντιστοιχούν στο παιχνίδι, το ταμπλό
	 * και τους παίκτες. Ακόμη, ορίζονται μια μεταβλητή τύπου int, η winnerId, που
	 * εκφράζει τον νικητή του παιχνιδιού και μια μεταβλητή τύπου boolean που
	 * εκφράζει εάν κάποιος παίκτης έχει φτάσει στον τερματισμό.
	 * Ακόμα, δημιουργείται ένας δισδιάστατος πίνακας int για αποθήκευση των επιστρεφόμενων
	 * τιμών της συνάρτησης move του κάθε παίκτη.
	 * Στην συνέχεια, οι παίκτες παίζουν εναλλάξ, με κλήση της move, μέχρι κάποιος να
	 * φτάσει στον τερματισμό. Τότε εκτυπώνονται οι βαθμοί του κάθε παίκτη και αυτός
	 * που ε΄φτασε πρώτος ανακυρήσσεται νικητής. Αν φτάσανε μαζί, τότε νικητής είναι
	 * ο παίκτης με τους περισσότερους πόντους.
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.setRound(1);

		Board board = new Board(20, 10, 3, 3, 6);
		board.createBoard();        // Δημιουργία του ταμπλό
		board.createElementBoard(); // Εκτύπωση του ταμπλό

		// Δημιουργία των παικτών
		Player[] players = new Player[] {new Player(1, "Player 1", 0, board), new Player(2,"Player 2", 0, board)};

		int winnerId = 0;
		boolean finish = false;

		int[][] pArray = new int[][] {{1, 0, 0, 0, 0},{1, 0, 0, 0, 0}};

		// Επανάληψη όπου παίζουν οι δυο παίκτες
		do {
			System.out.println("We are in round " + game.getRound() + ".\n");
			for(int i=0; i< players.length; i++) {
				int die = (int) (Math.random()*6+1);
				pArray[i] = players[i].move(pArray[i][0], die);
				System.out.println("Player " + (i+1) + " rolls the die. \nHe rolls a " + die + ".");
				System.out.print("He goes through " + pArray[i][1] + " snakes, " + pArray[i][2] + " ladders and eats ");
				System.out.println(pArray[i][3] + " red apples and " + pArray[i][4] + " black apples.");
				System.out.println("His score is now " + players[i].getScore() + " and he is now on tile " + pArray[i][0] + ".\n");
			}
			game.setRound(game.getRound()+1);

			if (pArray[0][0]==board.getN()*board.getM() && pArray[1][0]==board.getN()*board.getM()) {
				finish = true;
				winnerId = 0;
			}
			else if(pArray[0][0]== board.getN()*board.getM()) {
				finish = true;
				winnerId = 1;
			}
			else if(pArray[1][0]== board.getN()*board.getM()){
				finish = true;
				winnerId = 2;
			}
		}while(finish == false);

		// Εκτύπωση του νικητή
		if (winnerId == 0) {
			System.out.println("Both players have reached tile " + board.getN()*board.getM() + ". The scores are as follows:");
			System.out.println("Player 1:" + players[0].getScore());
			System.out.println("Player 2:" + players[1].getScore());
			if(players[0].getScore() > players[1].getScore())
				System.out.println("Player 1 has won the game! Congratulations!");
			else if(players[0].getScore() < players[1].getScore())
				System.out.println("Player 2 has won the game! Congratulations!");
			else
				System.out.println("The game has ended in a draw.");
		}
		else {
		System.out.println("Player " + winnerId + " has reached tile " + board.getN()*board.getM() + " first, with " + players[winnerId-1].getScore() + " points.");
		System.out.println("Player " + (winnerId%2 == 1? "2 " : "1 ") + "has reached tile " + pArray[winnerId%2][0] + " with " + players[winnerId%2].getScore() + " points.");
		System.out.println("Player " + winnerId + " has won the game! Congratulations!");
		}
	}

}
