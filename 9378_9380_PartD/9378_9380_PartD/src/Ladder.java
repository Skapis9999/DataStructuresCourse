//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8

/* Ladder Class
 * Περιέχονται οι μεταβλητές ladderId, upStepId, downStepId, broken. ο
 * Οι 3 πρώτες είναι τύπου int και η τελευταία boolean και
 * αντιστοιχούν στο id της σκάλας,
 * το id του πλακιδίου του ταμπλό όπου βρίσκεται η βάση της σκάλας και
 * το id του πλακιδίου του ταμπλό όπου βρίσκεται η κορυφή της σκάλας αντίστοιχα,
 * ενώ το broken δείχνει αν η σκάλα είναι σπασμένη ή όχι.
 * Περιέχονται 3 constructors, ένας χωρίς όρισματα, ένας με όρισματα και ένας
 * με όρισμα αντικείμενο.
 * Περιέχονται 4 getters και 4 setters, από ένα ζεύγος για κάθε μεταβλητή
 * της κλάσης αυτής.
 */ 
public class Ladder {
	private int ladderId;
	private int upStepId;
	private int downStepId;
	private boolean broken;

	//Κενός constructor
	public Ladder()
	{
		ladderId = 0;
		upStepId = 0;
		downStepId = 0;
		broken = false;
	}

	// Constructor με ορίσματα
	public Ladder(int ladderId, int upStepId, int downStepId, boolean broken)
	{
		this.ladderId = ladderId;
		this.upStepId = upStepId;
		this.downStepId = downStepId;
		this.broken = broken;
	}

	//Constructor με όρισμα ένα αντικείμενο
	public Ladder(Ladder L)
	{
		this.ladderId = L.ladderId;
		this.upStepId = L.upStepId;
		this.downStepId = L.downStepId;
		this.broken = L.broken;
	}

	//Getters
	public int getLadderId()
	{
		return ladderId;
	}

	public int getUpStepId()
	{
		return upStepId;
	}

	public int getDownStepId()
	{
		return downStepId;
	}

	public boolean getBroken()
	{
		return broken;
	}

	//Setters
	public void setLadderId(int a)
	{
		ladderId = a;
	}

	public void setUpStepId(int a)
	{
		upStepId = a;
	}

	public void setDownStepId(int a)
	{
		downStepId = a;
	}

	public void setBroken(boolean a)
	{
		broken = a;
	}
}