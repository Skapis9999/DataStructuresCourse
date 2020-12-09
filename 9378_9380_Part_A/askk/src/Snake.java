//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8

/* Snake Class.
 * Περιέχονται οι μεταβλητές snakeId, headId, tailId, όλες τύπου int
 * οι οποίες αντιστοιχούν στο id του φιδιού,
 * το id του πλακιδίου του ταμπλό όπου βρίσκεται το κεφάλι του φιδιού και
 * το id του πλακιδίου του ταμπλό όπου βρίσκεται η ουρά του φιδιού αντίστοιχα.
 * Περιέχονται 3 constructors, ένας χωρίς όρισματα, ένας με όρισματα και με
 * ένας με όρισμα αντικείμενο.
 * Περιέχονται 3 getters και 3 setters, από ένα ζεύγος για κάθε μεταβλητή της
 * κλάσης αυτής.
 */ 
public class Snake {
	private int snakeId;
	private int headId;
	private int tailId;

	//Κενός constructor
	public Snake()
	{
		snakeId = 0;
		headId = 0;
		tailId = 0;
	}

	// Constructor με ορίσματα
	public Snake(int snakeId, int headId, int tailId)
	{
		this.snakeId = snakeId;
		this.headId = headId;
		this.tailId = tailId;
	}

	//Constructor με όρισμα ένα αντικείμενο
	public Snake(Snake S)
	{
		this.snakeId = S.snakeId;
		this.headId = S.headId;
		this.tailId = S.tailId;
	}

	//Getters
	public int getSnakeId()
	{
		return snakeId;
	}

	public int getHeadId()
	{
		return headId;
	}

	public int getTailId()
	{
		return tailId;
	}

	//Setters
	public void setSnakeId(int a)
	{
		snakeId = a;
	}

	public void setHeadId(int a)
	{
		headId = a;
	}

	public void setTailId(int a)
	{
		tailId = a;
	}


}
