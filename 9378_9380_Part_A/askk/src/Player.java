//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8

/* Player class
 * Περιέχονται οι μεταβλητές playerId, name, score και board, τύπου int, string,
 * int και Board αντίστοιχα, οι οποίες αντιστοιχούν στο id του παίκτη,
 * το όνομα του παίκτη,
 * την βαθμολογία του παίκτη και
 * το ταμπλό στο οποίο παίζει ο παίκτης.
 * Περιέχονται δυο constructors, ένας κενός και ένας με ορίσματα.
 * Περιέχονται 4 ζεύγη getters και setters, ένα για κάθε μεταβλητή.
 * Επίσης περιέχεται η συνάρτηση move του παίκτη, που υλοποιεί την κίνηση του παίκτη
 * στο ταμπλό καθώς και τρεις βοηθητικές συναρτήσεις, οι appleExists, snakeExists και
 * ladderExists, οι οποίες χρησιμοποιούνται εντός της move.
 */ 
public class Player {
	private int playerId;
	private String name;
	private int score;
	private Board board;

	// Κενός constructor
	public Player() {
		playerId = 0;
		name = "";
		score = 0;
		board = new Board();
	}

	// Constructor με ορίσματα
	public Player(int playerId, String name, int score, Board board) {
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
	}

	// Setters
	public void setPlayerId(int playerId) {
		if(playerId > 0)                    // Έλεγχος της τιμής του ορίσματος
			this.playerId = playerId;
		else
			System.out.println("Setter Error: A player's id cannot be less than 1. No alteration has occured.");
	}

	public void setPlayerName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	// Getters
	public int getPlayerId() {
		return playerId;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public Board getBoard() {
		return board;
	}

/* H συνάρτηση move
 * Μετακινεί τον παίκτη στο ταμπλό από την θέση στην οποία βρισκόταν (που δίνεται
 * από το όρισμα id της συνάρτησης) μπροστά τόσες θέσεις όσες υποδεικνύει το ζάρι
 * (μεταβλητή die της συνάρτησης). Στο πλακίδιο που φτάνει, ελέγχεται εάν υπάρχει μήλο,
 * σκάλα ή φίδι και πραγματοποιείται η αντίστοιχη ενέργεια. Εάν ο παίκτης ανέβει κάποια
 * σκάλα ή κατέβει κάποιο φίδι και αλλάξει το id του πλακιδίου του, τότε ξαναγίνεται
 * ο έλεγχος. Τέλος, η συνάρτηση επιστρέφει έναν πίνακα int, 5 θέσεων,
 * που περιέχει το id του τελικού πλακιδίου στο οποίο φτάνει ο παίκτης, τον αριθμό από
 * φίδια που τον τσίμπησαν, σκάλες που ανέβηκε και μήλα (κόκκινα ή μαύρα) που έφαγε.
 * Εάν ο παίκτης, με την ζαριά του φτάσει ή ξεπεράσει το τελευταίο πλακίδιο του ταμπλό,
 * τότε η συνάρτηση επιστρέφει κατευθείαν πριν γίνει ο έλεγχος, αφού λόγω περιορισμού
 * στην συνάρτηση createBoard, δεν μπορεί να υπάρχει μήλο, σκάλα ή φίδι στο τελευταίο πλακίδιο.
 */
	int[] move(int id, int die) {
		int[] array = new int[5];
		id += die;

		if(id > board.getN()*board.getM()) {
			id = board.getN()*board.getM();
			return new int[]{board.getN()*board.getM(), 0, 0, 0, 0};
		}

		int start;
		do {
			start = id;
			Apple apple = appleExists(id);
			if(apple.getAppleId() != 0) {
				score += apple.getPoints();
				apple.setAppleTileId(0);
				if(apple.getColor() == "Red")
					array[3]++;
				else if(apple.getColor() == "Black")
					array[4]++;
			}
			Snake snake = snakeExists(id);
			if(snake.getSnakeId() != 0) {
				id = snake.getTailId();
				array[1]++;
			}
			else {
				Ladder ladder = ladderExists(id);
				if(ladder.getLadderId() != 0) {
					id = ladder.getDownStepId();
					array[2]++;
					ladder.setBroken(true);
				}
			}
		}while(start != id);
		array[0] = id;
		return array;
	}

/* Συνάρτηση appleExists
 * Η συνάρτηση αυτή αναζητά στο πλακίδιο με id το όρισμα της, εάν υπάρχει μήλο.
 * Εάν βρει μήλο στο πλακίδιο, τότε το επιστρέφει. Αλλιώς, επιστρέφει ένα κενό μήλο.
 */
	private Apple appleExists(int id) {
		for(int i=0; i<board.getApples().length; i++)
			if (board.getApples()[i].getAppleTileId() == id)
				return board.getApples()[i];
		return new Apple();
	}

	/* Συνάρτηση snakeExists
	 * Η συνάρτηση αυτή αναζητά στο πλακίδιο με id το όρισμα της, εάν υπάρχει φίδι.
	 * Εάν βρει φίδι στο πλακίδιο, τότε το επιστρέφει. Αλλιώς, επιστρέφει ένα κενό φίδι.
	 */
	private Snake snakeExists(int id) {
		for(int i=0; i< board.getSnakes().length; i++)
			if (board.getSnakes()[i].getHeadId() == id)
				return board.getSnakes()[i];
		return new Snake();
	}

	/* Συνάρτηση ladderExists
	 * Η συνάρτηση αυτή αναζητά στο πλακίδιο με id το όρισμα της, εάν υπάρχει σκάλα.
	 * Εάν βρει σκάλα στο πλακίδιο, τότε την επιστρέφει. Αλλιώς, επιστρέφει μια κενή σκάλα.
	 */
	private Ladder ladderExists(int id) {
		for(int i=0; i< board.getLadders().length; i++)
			if (board.getLadders()[i].getUpStepId() == id && board.getLadders()[i].getBroken() == false)
				return board.getLadders()[i];
		return new Ladder();
	}

}
