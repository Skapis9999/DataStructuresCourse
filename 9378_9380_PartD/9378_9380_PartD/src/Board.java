//Σκαπέτης Χρήστος ΑΕΜ 9378 6944316621 ikofokots@ece.auth.gr
//Ηλίας Κωφοκώτσιος ΑΕΜ 9380 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8


import java.util.Random;

/* Board class
 * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ ΞΏΞΉ ΞΌΞµΟ„Ξ±Ξ²Ξ»Ξ·Ο„Ξ­Ο‚ int n,m Ο€ΞΏΟ… ΟƒΟ…ΞΌΞ²ΞΏΞ»Ξ―Ξ¶ΞΏΟ…Ξ½ Ο„ΞΉΟ‚ Ξ΄ΞΉΞ±ΟƒΟ„Ξ¬ΟƒΞµΞΉΟ‚ Ο„ΞΏΟ… board
 * Ξ­Ξ½Ξ±Ο‚ int Ο€Ξ―Ξ½Ξ±ΞΊΞ±Ο‚ 2 Ξ΄ΞΉΞ±ΟƒΟ„Ξ¬ΟƒΞµΟ‰Ξ½ Ο€ΞΏΟ… Ο€ΞµΟ�ΞΉΞ­Ο‡ΞµΞΉ Ο�Ξ»Ξ± Ο„Ξ± tiles
 * 3 Ο€Ξ―Ξ½Ξ±ΞΊΞµΟ‚, ΞΏΞΉ snakes, ladders ΞΊΞ±ΞΉ apples Ο„Ο�Ο€ΞΏΟ… Snake, Ladder ΞΊΞ±ΞΉ Apple
 * ΞΏΞΉ ΞΏΟ€ΞΏΞ―ΞΏΞΉ Ο€ΞµΟ�ΞΉΞ­Ο‡ΞΏΟ…Ξ½ Ο„Ξ± Ο†Ξ―Ξ΄ΞΉΞ±,Ο„ΞΉΟ‚ ΟƒΞΊΞ¬Ξ»ΞµΟ‚ ΞΊΞ±ΞΉ Ο„Ξ± ΞΌΞ®Ξ»Ξ± Ξ±Ξ½Ο„Ξ―ΟƒΟ„ΞΏΞΉΟ‡Ξ±
 * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ 3 constructors, Ξ­Ξ½Ξ±Ο‚ Ο‡Ο‰Ο�Ξ―Ο‚ Ο�Ο�ΞΉΟƒΞΌΞ±Ο„Ξ±, Ξ­Ξ½Ξ±Ο‚ ΞΌΞµ Ο�Ο�ΞΉΟƒΞΌΞ±Ο„Ξ± ΞΊΞ±ΞΉ Ξ­Ξ½Ξ±Ο‚
 * ΞΌΞµ Ο�Ο�ΞΉΟƒΞΌΞ± Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½ΞΏ.
 * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ 6 getters ΞΊΞ±ΞΉ 6 setters, Ξ±Ο€Ο� Ξ΄Ο…ΞΏ Ξ³ΞΉΞ± ΞΊΞ¬ΞΈΞµ ΞΌΞµΟ„Ξ±Ξ²Ξ»Ξ·Ο„Ξ®
 * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ ΞΏΞΉ ΟƒΟ…Ξ½Ξ±Ο�Ο„Ξ®ΟƒΞµΞΉΟ‚ createBoard() ΞΊΞ±ΞΉ createElementBoard(),
 * Ο„Ο‰Ξ½ ΞΏΟ€ΞΏΞ―Ο‰Ξ½ Ξ· Ξ»ΞµΞΉΟ„ΞΏΟ…Ο�Ξ³Ξ―Ξ± Ο€ΞµΟ�ΞΉΞ³Ο�Ξ¬Ο†ΞµΟ„Ξ±ΞΉ Ο€Ξ±Ο�Ξ±ΞΊΞ¬Ο„Ο‰.
 */ 
public class Board {
	private int n;			
	private int m;
	private int[][] tiles;
	private Snake[] snakes;
	public Ladder[] ladders;
	private Apple[] apples;

	//Ξ�ΞµΞ½Ο�Ο‚ Constructor
	public Board(){
		n=2;                       //Ξ‘Ο€ΞΏΟ†Ο�Ξ³Ξ±ΞΌΞµ Ξ½Ξ± Ξ²Ξ¬Ξ»ΞΏΟ…ΞΌΞµ 0 Ξ® 1, Ξ³ΞΉΞ±Ο„Ξ― Ξ΄ΞµΞ½ Ξ³Ξ½Ο‰Ο�Ξ―Ξ¶ΞΏΟ…ΞΌΞµ
		m=2;                        //Ο„ΞΉ Ξ±Ο€ΞΏΟ„ΞµΞ»Ξ­ΟƒΞΌΞ±Ο„Ξ± ΞΌΟ€ΞΏΟ�ΞµΞ― Ξ½Ξ± Ξ­Ο‡ΞµΞΉ Ξ±Ο…Ο„Ο� ΟƒΟ„ΞΏ Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½ΞΏ
		tiles = new int [n][m];
		snakes = new Snake[1];      //Ξ�ΞΌΞΏΞ―Ο‰Ο‚ Ξ±Ο€ΞΏΟ†ΞµΟ�Ξ³ΞΏΟ…ΞΌΞµ Ξ½Ξ± Ξ²Ξ¬Ξ»ΞΏΟ…ΞΌΞµ ΞΊΞ±ΞΉ ΞµΞ΄Ο� Ο„ΞΏ 0
		ladders = new Ladder[1];
		apples = new Apple[1];

	}

	//Constructor ΞΌΞµ ΞΏΟ�Ξ―ΟƒΞΌΞ±Ο„Ξ±
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

	//Constructor ΞΌΞµ Ο�Ο�ΞΉΟƒΞΌΞ± Ξ­Ξ½Ξ± Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½ΞΏ
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
	*  Ξ£Ο…Ξ½Ξ¬Ο�Ο„Ξ·ΟƒΞ· createBoard()
	*  Ξ¤ΞΏΟ€ΞΏΞΈΞµΟ„ΞµΞ― ΟƒΟ„ΞΏ Ο„Ξ±ΞΌΟ€Ξ»Ο� Ο„Ο…Ο‡Ξ±Ξ―Ξ± Ο„Ξ± Ο†Ξ―Ξ΄ΞΉΞ±, Ο„ΞΉΟ‚ ΟƒΞΊΞ¬Ξ»ΞµΟ‚ ΞΊΞ±ΞΉ Ο„Ξ± ΞΌΞ®Ξ»Ξ± Ο‡Ο‰Ο�Ξ―Ο‚ Ξ½Ξ±
	*  Ο…Ο€Ξ¬Ο�Ο‡ΞΏΟ…Ξ½ Ξ΄ΞΉΞµΞ½Ξ­ΞΎΞµΞΉΟ‚ (Ξ΄Ο…ΞΏ Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½Ξ± ΟƒΟ„ΞΏ Ξ―Ξ΄ΞΉΞΏ Ο€Ξ»Ξ±ΞΊΞ―Ξ΄ΞΉΞΏ) Ξ® Ξ½Ξ± Ξ΄Ξ·ΞΌΞΉΞΏΟ…Ο�Ξ³ΞΏΟ�Ξ½Ο„Ξ±ΞΉ
	*  Ο€ΞµΟ�Ξ―ΞµΟ�Ξ³ΞµΟ‚ ΞΊΞ±Ο„Ξ±ΟƒΟ„Ξ¬ΟƒΞµΞΉΟ‚ (Ο€Ο‡ Ο†Ξ―Ξ΄ΞΉ ΞΌΞµ ΞΏΟ…Ο�Ξ¬ Ο€ΞΉΞΏ Ο€Ξ¬Ξ½Ο‰ Ξ±Ο€Ο� Ο„ΞΏ ΞΊΞµΟ†Ξ¬Ξ»ΞΉ)
	*  Ξ•Ο€Ξ―ΟƒΞ·Ο‚, Ξ±Ξ½Ο„ΞΉΟƒΟ„ΞΏΞΉΟ‡ΞµΞ― ΟƒΞµ ΞΊΞ¬ΞΈΞµ ΟƒΟ„ΞΏΞΉΟ‡ΞµΞ―ΞΏ Ο„ΞΏΟ… Ο€Ξ―Ξ½Ξ±ΞΊΞ± tiles Ο„ΞΏ Ο€Ο�Ξ±Ξ³ΞΌΞ±Ο„ΞΉΞΊΟ�
	*  tileId Ο€ΞΏΟ… ΞΈΞ± Ξ­Ο‡ΞµΞΉ ΟƒΞµ Ξ­Ξ½Ξ± ΞΊΞ±Ξ½ΞΏΞ½ΞΉΞΊΟ� Ο€Ξ±ΞΉΟ‡Ξ½Ξ―Ξ΄ΞΉ.
	*/
	public void createBoard(){
		Random rand = new Random();
		int numTiles = n*m;
		int[] taken;
		taken = new int [snakes.length + ladders.length];
		// Ξ� Ο€Ξ―Ξ½Ξ±ΞΊΞ±Ο‚ taken Ο€ΞµΟ�ΞΉΞ­Ο‡ΞµΞΉ Ο„ΞΏΟ…Ο‚ Ξ±Ο�ΞΉΞΈΞΌΞΏΟ�Ο‚ Ο„Ο‰Ξ½ Ο€Ξ»Ξ±ΞΊΞΉΞ΄Ξ―Ο‰Ξ½ Ο�Ο€ΞΏΟ… Ξ²Ο�Ξ―ΟƒΞΊΞΏΞ½Ο„Ξ±ΞΉ
		// Ο„Ξ± ΞΊΞµΟ†Ξ¬Ξ»ΞΉΞ± Ο„Ο‰Ξ½ Ο†ΞΉΞ΄ΞΉΟ�Ξ½ ΞΊΞ±ΞΉ ΞΏΞΉ Ξ²Ξ¬ΟƒΞµΞΉΟ‚ Ο„Ο‰Ξ½ ΟƒΞΊΞ±Ξ»Ο�Ξ½
		int[] headTaken = new int [snakes.length];
		// Ξ� Ο€Ξ―Ξ½Ξ±ΞΊΞ±Ο‚ headTaken Ο€ΞµΟ�ΞΉΞ­Ο‡ΞµΞΉ Ο„ΞΏΟ…Ο‚ Ξ±Ο�ΞΉΞΈΞΌΞΏΟ�Ο‚ Ο„Ο‰Ξ½ Ο€Ξ»Ξ±ΞΊΞΉΞ΄Ξ―Ο‰Ξ½ Ο�Ο€ΞΏΟ… Ξ²Ο�Ξ―ΟƒΞΊΞΏΞ½Ο„Ξ±ΞΉ
		// Ο„Ξ± ΞΊΞµΟ†Ξ¬Ξ»ΞΉΞ± Ο„Ο‰Ξ½ Ο†ΞΉΞ΄ΞΉΟ�Ξ½
		int[] upStepTaken = new int [ladders.length];
		// Ξ� Ο€Ξ―Ξ½Ξ±ΞΊΞ±Ο‚ upStepTaken Ο€ΞµΟ�ΞΉΞ­Ο‡ΞµΞΉ Ο„ΞΏΟ…Ο‚ Ξ±Ο�ΞΈΞΌΞΏΟ�Ο‚ Ο„Ο‰Ξ½ Ο€Ξ»Ξ±ΞΊΞΉΞ΄Ξ―Ο‰Ξ½ Ο�Ο€ΞΏΟ… Ξ²Ο�Ξ―ΟƒΞΊΞΏΞ½Ο„Ξ±ΞΉ
		// ΞΏΞΉ Ξ²Ξ¬ΟƒΞµΞΉΟ‚ Ο„Ο‰Ξ½ ΟƒΞΊΞ±Ξ»Ο�Ξ½

		// Ξ’Ο�Ο�Ο‡ΞΏΟ‚ for Ο€ΞΏΟ… Ο„ΞΏΟ€ΞΏΞΈΞµΟ„ΞµΞ― Ο„Ξ± Ο†Ξ―Ξ΄ΞΉΞ± ΟƒΟ„ΞΏ Ο„Ξ±ΞΌΟ€Ξ»Ο�
		for(int i=0; i<snakes.length; i++){
			//Ξ¤ΞΌΞ®ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ snakeId
			snakes[i].setSnakeId(i+1);

			///Ξ¤ΞΌΞ®ΞΌΞ± Ξ’Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ headId
			boolean check = true;
			int a=0;
			do{
				a = rand.nextInt(numTiles-2) + 2;   //Ξ Ξ±Ο�Ξ±Ξ³Ο‰Ξ³Ξ® Ο„Ο…Ο‡Ξ±Ξ―ΞΏΟ… Ξ±ΞΊΞµΟ�Ξ±Ξ―ΞΏΟ… ΟƒΟ„ΞΏ Ξ΄ΞΉΞ¬ΟƒΟ„Ξ·ΞΌΞ± [2,numTiles)
				check = true;
				for(int j=0; j<i; j++){
					if(taken[j] == a)			    //Ξ�Ξ»ΞµΞ³Ο‡ΞΏΟ‚ ΞµΞ¬Ξ½ Ο…Ο€Ξ¬Ο�Ο‡ΞµΞΉ Ξ¬Ξ»Ξ»ΞΏ ΞΊΞµΟ†Ξ¬Ξ»ΞΉ Ο†ΞΉΞ΄ΞΉΞΏΟ� ΟƒΟ„ΞΏ Ο€Ξ»Ξ±ΞΊΞ―Ξ΄ΞΉΞΏ
						check = false;
				}
			}while(check == false);

			snakes[i].setHeadId(a);
			taken[i] = a;
			headTaken[i] = a;

			// Ξ¤ΞΌΞ®ΞΌΞ± Ξ’Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ tailId
			check = true;
			do{
				a = rand.nextInt(numTiles-2) + 1;    //Ξ Ξ±Ο�Ξ±Ξ³Ο‰Ξ³Ξ® Ο„Ο…Ο‡Ξ±Ξ―ΞΏΟ… Ξ±ΞΊΞµΟ�Ξ±Ξ―ΞΏΟ… ΟƒΟ„ΞΏ Ξ΄ΞΉΞ¬ΟƒΟ„Ξ·ΞΌΞ± [1,numTiles-1)
				check = true;
				if(taken[i] <= a)					   //Ξ�Ξ»ΞµΞ³Ο‡ΞΏΟ‚ Ξ³ΞΉΞ± Ο„ΞΏΞ½ ΞµΞ¬Ξ½ Ο„ΞΏ ΞΊΞµΟ†Ξ¬Ξ»ΞΉ ΞµΞ―Ξ½Ξ±ΞΉ Ο€ΞΉΞΏ Ο�Ξ·Ξ»Ξ¬ Ξ±Ο€Ο� Ο„Ξ·Ξ½ ΞΏΟ…Ο�Ξ¬
					check = false;
			}while(check == false);
			snakes[i].setTailId(a);
		}

		//Ξ’Ο�Ο�Ο‡ΞΏΟ‚ Ο€ΞΏΟ… Ο„ΞΏΟ€ΞΏΞΈΞµΟ„ΞµΞ― Ο„ΞΉΟ‚ ΟƒΞΊΞ¬Ξ»ΞµΟ‚ ΟƒΟ„ΞΏ Ο„Ξ±ΞΌΟ€Ξ»Ο�
		for(int i=0; i<ladders.length; i++){
			// Ξ¤ΞΌΞ®ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ ladderId
			ladders[i].setLadderId(i+1);
			ladders[i].setBroken(false);
			//Ξ¤ΞΌΞ®ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ upStepId
			boolean check = true;
			int a=0;
			do{
				a = rand.nextInt(numTiles-1) + 1;    //Ξ Ξ±Ο�Ξ±Ξ³Ο‰Ξ³Ξ® Ο„Ο…Ο‡Ξ±Ξ―ΞΏΟ… Ξ±ΞΊΞµΟ�Ξ±Ξ―ΞΏΟ… ΟƒΟ„ΞΏ Ξ΄ΞΉΞ¬ΟƒΟ„Ξ·ΞΌΞ± [1,numTiles)
				check = true;
				for(int j=0; j<snakes.length + i; j++){
					if(taken[j]==a)					//Ξ�Ξ»ΞµΞ³Ο‡ΞΏΟ‚ ΞµΞ¬Ξ½ Ο…Ο€Ξ¬Ο�Ο‡ΞµΞΉ ΟƒΟ„ΞΏ Ο€Ξ»Ξ±ΞΊΞ―Ξ΄ΞΉΞΏ Ξ®Ξ΄Ξ· Ξ²Ξ¬ΟƒΞ· ΟƒΞΊΞ¬Ξ»Ξ±Ο‚ Ξ® ΞΊΞµΟ†Ξ¬Ξ»ΞΉ Ο†ΞΉΞ΄ΞΉΞΏΟ�
						check = false;
				}
			}while(check == false);

			ladders[i].setUpStepId(a);
			taken[snakes.length + i] = a;
			upStepTaken[i] = a;

			//Ξ¤ΞΌΞ®ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ downStepId
			check = true;
			do{
				a = rand.nextInt(numTiles-1) +2 ;    //Ξ Ξ±Ο�Ξ±Ξ³Ο‰Ξ³Ξ® Ο„Ο…Ο‡Ξ±Ξ―ΞΏΟ… Ξ±ΞΊΞµΟ�Ξ±Ξ―ΞΏΟ… ΟƒΟ„ΞΏ Ξ΄ΞΉΞ¬ΟƒΟ„Ξ·ΞΌΞ± [2,numTiles+1)
				check = true;
				if(upStepTaken[i] >= a)		   //Ξ�Ξ»ΞµΞ³Ο‡ΞΏΟ‚ ΞµΞ¬Ξ½ Ξ· Ξ²Ξ¬ΟƒΞ· Ο„Ξ·Ο‚ ΟƒΞΊΞ¬Ξ»Ξ±Ο‚ ΞµΞ―Ξ½Ξ±ΞΉ Ο€ΞΉΞΏ Ο�Ξ·Ξ»Ξ¬ Ξ±Ο€Ο� Ο„Ξ·Ξ½ ΞΊΞΏΟ�Ο…Ο†Ξ®
					check = false;
			}while(check == false);
			ladders[i].setDownStepId(a);
		}

		// Ξ’Ο�Ο�Ο‡ΞΏΟ‚ for Ξ³ΞΉΞ± Ο„Ξ·Ξ½ Ο„ΞΏΟ€ΞΏΞΈΞ­Ο„Ξ·ΟƒΞ· Ο„Ο‰Ξ½ ΞΌΞ®Ξ»Ο‰Ξ½
		for(int i=0; i<apples.length; i++)
		{
			//Ξ¤ΞΌΞ®ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ appleTileId
			boolean check = true;
			int a = 0;
			do{
				a = rand.nextInt(numTiles-1) + 1;     //Ξ Ξ±Ο�Ξ±Ξ³Ο‰Ξ³Ξ® Ο„Ο…Ο‡Ξ±Ξ―ΞΏΟ… Ξ±ΞΊΞµΟ�Ξ±Ξ―ΞΏΟ… ΟƒΟ„ΞΏ Ξ΄ΞΉΞ¬ΟƒΟ„Ξ·ΞΌΞ± [1,numTiles)
				check = true;
				for(int j=0; j<snakes.length; j++) {
					if(headTaken[j] == a)			       //Ξ�Ξ»ΞµΞ³Ο‡ΞΏΟ‚ ΞµΞ¬Ξ½ Ο…Ο€Ξ¬Ο�Ο‡ΞµΞΉ ΞΊΞµΟ†Ξ¬Ξ»ΞΉ Ο†ΞΉΞ΄ΞΉΞΏΟ� ΟƒΟ„ΞΏ Ο€Ξ»Ξ±ΞΊΞ―Ξ΄ΞΉΞΏ
						check = false;
				}
				for(int j=0; j<i; j++) {
					if(apples[j].getAppleTileId() == a)
						check = false;
				}
			}while(check == false);
			apples[i].setAppleTileId(a);

			//Ξ¤ΞΌΞ®ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ appleId
			apples[i].setAppleId(i+1);

			//Ξ¤ΞΌΞ·ΞΌΞ± Ξ²Ο�Ο�Ο‡ΞΏΟ… Ξ³ΞΉΞ± Ο„ΞΏ colour ΞΊΞ±ΞΉ Ο„ΞΏ points
			boolean b = true;
			b = rand.nextBoolean() ;           //Ξ Ξ±Ο�Ξ±Ξ³Ο‰Ξ³Ξ® Ο„Ο…Ο‡Ξ±Ξ―ΞΏΟ… Ξ΄Ο…Ξ±Ξ΄ΞΉΞΊΞΏΟ� Ο�Ξ·Ο†Ξ―ΞΏΟ…
			if(b){
				apples[i].setColor("Red");
				apples[i].setPoints(rand.nextInt(10)+1);
			}
			else{
				apples[i].setColor("Black");
				apples[i].setPoints(- rand.nextInt(10)- 1);
			}
		}


		/* Ξ‘Ξ½Ξ¬ΞΈΞµΟƒΞ· Ο„Ξ·Ο‚ Ο€Ο�Ξ±Ξ³ΞΌΞ±Ο„ΞΉΞΊΞ®Ο‚ Ο„ΞΉΞΌΞ®Ο‚ Ο„ΞΏΟ… Ο€Ξ»Ξ±ΞΊΞΉΞ΄Ξ―ΞΏΟ… ΟƒΟ„ΞΏ Ο„Ξ±ΞΌΟ€Ξ»Ο� ΟƒΟ„Ξ± ΟƒΟ„ΞΏΞΉΟ‡ΞµΞ―Ξ± Ο„ΞΏΟ…
		* Ο€Ξ―Ξ½Ξ±ΞΊΞ± tiles. Ξ�ΞµΞΊΞΉΞ½Ο�Ξ½Ο„Ξ±Ο‚ Ξ±Ο€Ο� ΞΊΞ¬Ο„Ο‰ Ξ΄ΞµΞΎΞΉΞ¬, Ξ±Ο€Ξ±Ο�ΞΉΞΈΞΌΞΏΟ�ΞΌΞµ Ο„Ξ± ΟƒΟ„ΞΏΞΉΟ‡ΞµΞ―Ξ± ΞΊΞΉΞ½ΞΏΟ�ΞΌΞµΞ½ΞΏΞΉ
		* ΞµΞ½Ξ±Ξ»Ξ»Ξ¬ΞΎ ΟƒΞµ ΞΊΞ¬ΞΈΞµ ΟƒΞµΞΉΟ�Ξ¬ Ξ΄ΞµΞΎΞΉΞ¬ Ξ® Ξ±Ο�ΞΉΟƒΟ„ΞµΟ�Ξ¬ ΞΊΞ±ΞΉ Ξ±Ξ½ΞµΞ²Ξ±Ξ―Ξ½ΞΏΞ½Ο„Ξ±Ο‚ Ο€Ξ¬Ξ½Ο„Ξ± Ο€Ο�ΞΏΟ‚ Ο„Ξ± Ο€Ξ¬Ξ½Ο‰
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

	/* Ξ£Ο…Ξ½Ξ¬Ο�Ο„Ξ·ΟƒΞ· createElementBoard()
	* Ξ•ΞΊΟ„Ο…Ο€Ο�Ξ½ΞΏΞ½Ο„Ξ±ΞΉ ΞΏΞΉ Ο„Ο�ΞµΞΉΟ‚ elementBoard Ο€ΞΏΟ… Ο€ΞµΟ�ΞΉΞ­Ο‡ΞΏΟ…Ξ½ Ο„ΞΉΟ‚ ΞΈΞ­ΟƒΞµΞΉΟ‚ Ο„Ο‰Ξ½ Ο†ΞΉΞ΄ΞΉΟ�Ξ½, ΟƒΞΊΞ±Ξ»Ο�Ξ½
	* ΞΊΞ±ΞΉ ΞΌΞ„Ξ®Ξ»Ο‰Ξ½ ΟƒΟ„ΞΏ Ο„Ξ±ΞΌΟ€Ξ»Ο�. Ξ‘Ο�Ο‡ΞΉΞΊΞ¬, ΟƒΞµ ΞΊΞ¬ΞΈΞµ ΞΈΞ­ΟƒΞ· ΞµΞΉΟƒΞ¬Ξ³ΞµΟ„Ξ±ΞΉ Ο„ΞΏ Ξ±Ξ»Ο†Ξ±Ο�ΞΉΞΈΞΌΞ·Ο„ΞΉΞΊΟ� "___"
	* ΞΊΞ±ΞΉ ΟƒΟ„Ξ·Ξ½ ΟƒΟ…Ξ½Ξ­Ο‡ΞµΞΉΞ±, Ο„ΞΏ ΞΊΞ¬ΞΈΞµ Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½ΞΏ Ο„ΞΏΟ€ΞΏΞΈΞµΟ„ΞµΞ―Ο„Ξ±ΞΉ ΞΌΞµ Ο„Ξ·Ξ½ Ο‡Ο�Ξ®ΟƒΞ· ΞµΞ½Ο�Ο‚ ΞΌΞµΟ„Ξ±ΟƒΟ‡Ξ·ΞΌΞ±Ο„ΞΉΟƒΞΌΞΏΟ�
	* ΟƒΟ…Ξ½Ο„ΞµΟ„Ξ±Ξ³ΞΌΞ­Ξ½Ο‰Ξ½ ΟƒΟ„Ξ·Ξ½ ΟƒΟ‰ΟƒΟ„Ξ® ΞΈΞ­ΟƒΞ· ΟƒΟ„ΞΏΟ…Ο‚ Ο€Ξ―Ξ½Ξ±ΞΊΞµΟ‚. Ξ¤Ξ­Ξ»ΞΏΟ‚, ΞΏΞΉ Ο„Ο�ΞµΞΉΟ‚ Ο€Ξ―Ξ½Ξ±ΞΊΞµΟ‚ ΞµΞΊΟ„Ο…Ο€Ο�Ξ½ΞΏΞ½Ο„Ξ±ΞΉ.
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

		/* Ξ‘ΞΊΞΏΞ»ΞΏΟ…ΞΈΞµΞ― Ο„ΞΏ Ο„ΞΌΞ®ΞΌΞ± Ο€ΞΏΟ… Ξ±Ο†ΞΏΟ�Ξ¬ Ο„Ξ± Ο†Ξ―Ξ΄ΞΉΞ±.
		* Ξ•Ο€ΞµΞΉΞ΄Ξ® Ο„ΞΏ headId ΞΊΞ±ΞΉ Ο„ΞΏ tailId Ο€ΞµΟ�ΞΉΞ­Ο‡ΞΏΟ…Ξ½ Ξ±Ο�ΞΉΞΈΞΌΞΏΟ�Ο‚ Ο€ΞΏΟ… Ξ±Ξ½Ο„ΞΉΟƒΟ„ΞΏΞΉΟ‡ΞΏΟ�Ξ½ ΟƒΞµ
		* Ο€Ο�Ξ±Ξ³ΞΌΞ±Ο„ΞΉΞΊΞ¬ Ο€Ξ»Ξ±ΞΊΞ―Ξ΄ΞΉΞ±, Ο€Ο�Ξ­Ο€ΞµΞΉ Ξ½Ξ± ΞΌΞµΟ„Ξ±ΟƒΟ‡Ξ·ΞΌΞ±Ο„ΞΉΟƒΟ„ΞΏΟ�Ξ½ ΟƒΞµ i ΞΊΞ±ΞΉ j.
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

		// Ξ‘ΞΊΞΏΞ»ΞΏΟ…ΞΈΞµΞ― Ο„ΞΏ Ο„ΞΌΞ®ΞΌΞ± Ο€ΞΏΟ… Ξ±Ο†ΞΏΟ�Ξ¬ Ο„ΞΉΟ‚ ΟƒΞΊΞ¬Ξ»ΞµΟ‚
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

		//Ξ‘ΞΊΞΏΞ»ΞΏΟ…ΞΈΞµΞ― Ο„ΞΏ Ο„ΞΌΞ®ΞΌΞ± Ο€ΞΏΟ… Ξ±Ο†ΞΏΟ�Ξ¬ Ο„Ξ± ΞΌΞ®Ξ»Ξ±
		for(int i=0; i<apples.length; i++) {
			int id = apples[i].getAppleId();
			int tileId = apples[i].getAppleTileId();
			String colour = apples[i].getColor();
			int tileI = (n-1)-(tileId-1)/m;
			int tileJ = (n-1-tileI)%2 == 0 ? (tileId-1)%m : (m-1)-(tileId-1)%m;
			elementBoardApples[tileI][tileJ] = ("A" + (colour == "Red" ? "R": "B") + Integer.toString(id));
		}
		// Ξ•ΞΊΟ„Ο�Ο€Ο‰ΟƒΞ· Ο„Ο‰Ξ½ Ο„Ο�ΞΉΟ�Ξ½ Ο€ΞΉΞ½Ξ¬ΞΊΟ‰Ξ½, ΟƒΞµ Ξ΄ΞΉΞ¬Ο„Ξ±ΞΎΞ· ΞΏ Ξ­Ξ½Ξ±Ο‚ Ξ΄Ξ―Ο€Ξ»Ξ± ΟƒΟ„ΞΏΞ½ Ξ¬Ξ»Ξ»ΞΏ.
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