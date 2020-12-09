//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8


import java.util.Random;

/* Board class
 * Περιέχονται οι μεταβλητές int n,m που συμβολίζουν τις διαστάσεις του board
 * ένας int πίνακας 2 διαστάσεων που περιέχει όλα τα tiles
 * 3 πίνακες, οι snakes, ladders και apples τύπου Snake, Ladder και Apple
 * οι οποίοι περιέχουν τα φίδια,τις σκάλες και τα μήλα αντίστοιχα
 * Περιέχονται 3 constructors, ένας χωρίς όρισματα, ένας με όρισματα και ένας
 * με όρισμα αντικείμενο.
 * Περιέχονται 6 getters και 6 setters, από δυο για κάθε μεταβλητή
 * Περιέχονται οι συναρτήσεις createBoard() και createElementBoard(),
 * των οποίων η λειτουργία περιγράφεται παρακάτω.
 */ 
public class Board {
	private int n;			
	private int m;
	private int[][] tiles;
	private Snake[] snakes;
	public Ladder[] ladders;
	private Apple[] apples;

	//Κενός Constructor
	public Board(){
		n=2;                       //Αποφύγαμε να βάλουμε 0 ή 1, γιατί δεν γνωρίζουμε
		m=2;                        //τι αποτελέσματα μπορεί να έχει αυτό στο αντικείμενο
		tiles = new int [n][m];
		snakes = new Snake[1];      //Ομοίως αποφεύγουμε να βάλουμε και εδώ το 0
		ladders = new Ladder[1];
		apples = new Apple[1];

	}

	//Constructor με ορίσματα
	public Board(int n, int m, int snakes, int ladders, int apples){
		this.n=n;
		this.m=m;
		this.tiles = new int [n][m];
		this.snakes = new Snake[snakes];
		for(int i=0; i<snakes; i++)
			this.snakes[i] = new Snake();
		this.ladders = new Ladder[ladders];
		for(int i=0; i<ladders; i++)
			this.ladders[i] = new Ladder();
		this.apples = new Apple[apples];
		for(int i=0; i<apples; i++)
			this.apples[i] = new Apple();

	}

	//Constructor με όρισμα ένα αντικείμενο
	public Board(Board board){
	    n=board.n;
		m=board.m;
		tiles = new int [n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				tiles[i][j] = board.tiles[i][j];
		snakes = new Snake[board.snakes.length];
		for(int i=0; i<board.snakes.length; i++)
			snakes[i] = new Snake(board.snakes[i]); 	
		apples = new Apple[board.apples.length];
		for(int i=0; i<board.apples.length; i++)
			apples[i] = new Apple(board.apples[i]); 	
		ladders = new Ladder[board.ladders.length];
		for(int i=0; i<board.ladders.length; i++)
			ladders[i] = new Ladder(board.ladders[i]); 	
	}

	//Getters
	public int getN(){
		return n;
	}

	public int getM(){
		return m;
	}

	public int[][] getTiles(){
		return tiles;
	}

	public Snake[] getSnakes(){
		return snakes;
	}

	public Ladder[] getLadders(){
		return ladders;
	}

	public Apple[] getApples(){
		return apples;
	}

	//Setters
	public void setN(int n){
		this.n = n;
	}

	public void setM(int m){
		this.m = m;
	}

	public void setTiles(int t, int i, int j){
		tiles[i][j] = t;
	}

	public void setSnakes(Snake S, int i ){
		snakes[i] = S;
	}

	public void setLadders(Ladder L, int i){
		ladders[i] = L;
	}

	public void setApples(Apple A, int i){
		apples[i] = A;
	}

	/*
	*  Συνάρτηση createBoard()
	*  Τοποθετεί στο ταμπλό τυχαία τα φίδια, τις σκάλες και τα μήλα χωρίς να
	*  υπάρχουν διενέξεις (δυο αντικείμενα στο ίδιο πλακίδιο) ή να δημιουργούνται
	*  περίεργες καταστάσεις (πχ φίδι με ουρά πιο πάνω από το κεφάλι)
	*  Επίσης, αντιστοιχεί σε κάθε στοιχείο του πίνακα tiles το πραγματικό
	*  tileId που θα έχει σε ένα κανονικό παιχνίδι.
	*/
	public void createBoard(){
		Random rand = new Random();
		int numTiles = n*m;
		int[] taken;
		taken = new int [snakes.length + ladders.length];
		// Ο πίνακας taken περιέχει τους αριθμούς των πλακιδίων όπου βρίσκονται
		// τα κεφάλια των φιδιών και οι βάσεις των σκαλών
		int[] headTaken = new int [snakes.length];
		// Ο πίνακας headTaken περιέχει τους αριθμούς των πλακιδίων όπου βρίσκονται
		// τα κεφάλια των φιδιών
		int[] upStepTaken = new int [ladders.length];
		// Ο πίνακας upStepTaken περιέχει τους αρθμούς των πλακιδίων όπου βρίσκονται
		// οι βάσεις των σκαλών

		// Βρόχος for που τοποθετεί τα φίδια στο ταμπλό
		for(int i=0; i<snakes.length; i++){
			//Τμήμα βρόχου για το snakeId
			snakes[i].setSnakeId(i+1);

			///Τμήμα Βρόχου για το headId
			boolean check = true;
			int a=0;
			do{
				a = rand.nextInt(numTiles-2) + 2;   //Παραγωγή τυχαίου ακεραίου στο διάστημα [2,numTiles)
				check = true;
				for(int j=0; j<i; j++){
					if(taken[j] == a)			    //Έλεγχος εάν υπάρχει άλλο κεφάλι φιδιού στο πλακίδιο
						check = false;
				}
			}while(check == false);

			snakes[i].setHeadId(a);
			taken[i] = a;
			headTaken[i] = a;

			// Τμήμα Βρόχου για το tailId
			check = true;
			do{
				a = rand.nextInt(numTiles-2) + 1;    //Παραγωγή τυχαίου ακεραίου στο διάστημα [1,numTiles-1)
				check = true;
				if(taken[i] <= a)					   //Έλεγχος για τον εάν το κεφάλι είναι πιο ψηλά από την ουρά
					check = false;
			}while(check == false);
			snakes[i].setTailId(a);
		}

		//Βρόχος που τοποθετεί τις σκάλες στο ταμπλό
		for(int i=0; i<ladders.length; i++){
			// Τμήμα βρόχου για το ladderId
			ladders[i].setLadderId(i+1);

			//Τμήμα βρόχου για το upStepId
			boolean check = true;
			int a=0;
			do{
				a = rand.nextInt(numTiles-1) + 1;    //Παραγωγή τυχαίου ακεραίου στο διάστημα [1,numTiles)
				check = true;
				for(int j=0; j<snakes.length + i; j++){
					if(taken[j]==a)					//Έλεγχος εάν υπάρχει στο πλακίδιο ήδη βάση σκάλας ή κεφάλι φιδιού
						check = false;
				}
			}while(check == false);

			ladders[i].setUpStepId(a);
			taken[snakes.length + i] = a;
			upStepTaken[i] = a;

			//Τμήμα βρόχου για το downStepId
			check = true;
			do{
				a = rand.nextInt(numTiles-1) +2 ;    //Παραγωγή τυχαίου ακεραίου στο διάστημα [2,numTiles+1)
				check = true;
				if(upStepTaken[i] >= a)		   //Έλεγχος εάν η βάση της σκάλας είναι πιο ψηλά από την κορυφή
					check = false;
			}while(check == false);
			ladders[i].setDownStepId(a);
		}

		// Βρόχος for για την τοποθέτηση των μήλων
		for(int i=0; i<apples.length; i++)
		{
			//Τμήμα βρόχου για το appleTileId
			boolean check = true;
			int a = 0;
			do{
				a = rand.nextInt(numTiles-1) + 1;     //Παραγωγή τυχαίου ακεραίου στο διάστημα [1,numTiles)
				check = true;
				for(int j=0; j<snakes.length; j++) {
					if(headTaken[j] == a)			       //Έλεγχος εάν υπάρχει κεφάλι φιδιού στο πλακίδιο
						check = false;
				}
				for(int j=0; j<i; j++) {
					if(apples[j].getAppleTileId() == a)
						check = false;
				}
			}while(check == false);
			apples[i].setAppleTileId(a);

			//Τμήμα βρόχου για το appleId
			apples[i].setAppleId(i+1);

			//Τμημα βρόχου για το colour και το points
			boolean b = true;
			b = rand.nextBoolean() ;           //Παραγωγή τυχαίου δυαδικού ψηφίου
			if(b){
				apples[i].setColor("Red");
				apples[i].setPoints(rand.nextInt(10)+1);
			}
			else{
				apples[i].setColor("Black");
				apples[i].setPoints(- rand.nextInt(10)- 1);
			}
		}


		/* Ανάθεση της πραγματικής τιμής του πλακιδίου στο ταμπλό στα στοιχεία του
		* πίνακα tiles. Ξεκινώντας από κάτω δεξιά, απαριθμούμε τα στοιχεία κινούμενοι
		* εναλλάξ σε κάθε σειρά δεξιά ή αριστερά και ανεβαίνοντας πάντα προς τα πάνω
		*/
		int realTileNumber = 1;
		boolean normalDirection = true;
		for(int i = n-1; i>=0; i--) {
			if(normalDirection){
				for(int j=0; j<m; j++) {
					tiles[i][j] = realTileNumber;
					realTileNumber++;
					normalDirection = false;
				}
			}
			else{
				for(int j=m-1; j>=0; j--) {
					tiles[i][j] = realTileNumber;
					realTileNumber++;
					normalDirection = true;
				}
			}
		}
	}

	/* Συνάρτηση createElementBoard()
	* Εκτυπώνονται οι τρεις elementBoard που περιέχουν τις θέσεις των φιδιών, σκαλών
	* και μ΄ήλων στο ταμπλό. Αρχικά, σε κάθε θέση εισάγεται το αλφαριθμητικό "___"
	* και στην συνέχεια, το κάθε αντικείμενο τοποθετείται με την χρήση ενός μετασχηματισμού
	* συντεταγμένων στην σωστή θέση στους πίνακες. Τέλος, οι τρεις πίνακες εκτυπώνονται.
	*/
	public void createElementBoard()
	{
		String[][] elementBoardSnakes = new String[n][m];
		String[][] elementBoardLadders = new String[n][m];
		String[][] elementBoardApples = new String[n][m];

		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++) {
			elementBoardSnakes[i][j] = "___";
			elementBoardLadders[i][j] = "___";
			elementBoardApples[i][j] = "___";
			}

		/* Ακολουθεί το τμήμα που αφορά τα φίδια.
		* Επειδή το headId και το tailId περιέχουν αριθμούς που αντιστοιχούν σε
		* πραγματικά πλακίδια, πρέπει να μετασχηματιστούν σε i και j.
		*/
		for(int i=0; i<snakes.length; i++) {
			int id = snakes[i].getSnakeId();
			int headId = snakes[i].getHeadId();
			int tailId = snakes[i].getTailId();
			int headI = (n-1)-(headId-1)/m;
			int headJ = (n-1-headI)%2 == 0 ? (headId-1)%m : (m-1)-(headId-1)%m;
			int tailI = (n-1)-(tailId-1)/m;
			int tailJ = (n-1-tailI)%2 == 0 ? (tailId-1)%m : (m-1)-(tailId-1)%m;
			elementBoardSnakes[headI][headJ] = elementBoardSnakes[headI][headJ] == "___" ? "SH" + Integer.toString(id) : elementBoardSnakes[headI][headJ]+ "SH" + Integer.toString(id);
			elementBoardSnakes[tailI][tailJ] = elementBoardSnakes[tailI][tailJ] == "___" ? "ST" + Integer.toString(id) : elementBoardSnakes[tailI][tailJ]+ "ST" + Integer.toString(id);
		}

		// Ακολουθεί το τμήμα που αφορά τις σκάλες
		for(int i=0; i<ladders.length; i++) {
			int id = ladders[i].getLadderId();
			int upStepId = ladders[i].getUpStepId();
			int downStepId = ladders[i].getDownStepId();
			int upStepI = (n-1)-(upStepId-1)/m;
			int upStepJ = ((n-1-upStepI)%2 == 0) ? (upStepId-1)%m : (m-1)-(upStepId-1)%m;
			int downStepI = (n-1)-(downStepId-1)/m;
			int downStepJ = ((n-1-downStepI)%2 == 0) ? (downStepId-1)%m : (m-1)-(downStepId-1)%m;
			elementBoardLadders[upStepI][upStepJ] = elementBoardLadders[upStepI][upStepJ] == "___" ? "LU" + Integer.toString(id) : elementBoardLadders[upStepI][upStepJ] + "LU" + Integer.toString(id);
			elementBoardLadders[downStepI][downStepJ] = elementBoardLadders[downStepI][downStepJ] == "___" ? "LD" + Integer.toString(id) : elementBoardLadders[downStepI][downStepJ] + "LD" + Integer.toString(id);
		}

		//Ακολουθεί το τμήμα που αφορά τα μήλα
		for(int i=0; i<apples.length; i++) {
			int id = apples[i].getAppleId();
			int tileId = apples[i].getAppleTileId();
			String colour = apples[i].getColor();
			int tileI = (n-1)-(tileId-1)/m;
			int tileJ = (n-1-tileI)%2 == 0 ? (tileId-1)%m : (m-1)-(tileId-1)%m;
			elementBoardApples[tileI][tileJ] = ("A" + (colour == "Red" ? "R": "B") + Integer.toString(id));
		}
		// Εκτύπωση των τριών πινάκων, σε διάταξη ο ένας δίπλα στον άλλο.
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				System.out.printf("%4d%s%s%s ", tiles[i][j], elementBoardApples[i][j], elementBoardLadders[i][j], elementBoardSnakes[i][j]);
			} 
			System.out.println();
		}

	}
	
	public void magic(int i)
	{
		ladders[i].setUpStepId(-1);
		
	}
}