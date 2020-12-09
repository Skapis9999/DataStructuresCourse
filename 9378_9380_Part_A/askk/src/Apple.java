//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8


 /* Apple Class
  * Περιέχονται οι μεταβλητές appleId, appleTileId, color, points
  * οι οποίες αντιστοιχούν στο id του μήλου, τύπου int,
  * το id του πλακιδίου του ταμπλό όπου βρίσκεται το μήλο, τύπου int,
  * το χρώμα του μήλου (red ή black), τύπου string,
  * οι πόντοι που θα κερδίζει ή θα χάνει ένας παίκτης (ανάλογα με το χρώμα του μήλου)
  * όταν θα τρώει το μήλο, αντίστοιχα, τύπου int.
  * Περιέχονται 3 constructors, ένας χωρίς όρισματα, ένας με όρισματα και ένας με όρισμα αντικείμενο.
  * Περιέχονται 4 getters και 4 setters, ένα ζεύγος για κάθε μεταβλητή της κλάσης αυτής.
  */ 
public class Apple {
	private int appleId;
	private int appleTileId;
	private String color;
	private int points;

	//Κενός constructor
	public Apple()
	{
		appleId = 0;
		appleTileId = 0;
		color = "";
		points = 0;
	}

	//Constructor με ορίσματα
	public Apple(int appleId, int appleTileid,String color, int points)
	{
		this.appleId = appleId;
		this.appleTileId = appleTileid;
		this.color = color;
		this.points = points;
	}

	//Constructor με όρισμα ένας αντικείμενο
	public Apple(Apple A)
	{
		this.appleId = A.appleId;
		this.appleTileId = A.appleTileId;
		this.color=A.color;
		this.points = A.points;
	}

	//Getters
	public int getAppleId()
	{
		return appleId;
	}

	public int getAppleTileId()
	{
		return appleTileId;
	}

	public String getColor()
	{
		return color;
	}

	public int getPoints()
	{
		return points;
	}

	//Setters
	public void setAppleId(int a)
	{
		appleId = a;
	}

	public void setAppleTileId(int a)
	{
		appleTileId = a;
	}

	public void setColor(String a)
	{
		color = a;
	}

	public void setPoints(int a)
	{
		points = a;
	}

}
