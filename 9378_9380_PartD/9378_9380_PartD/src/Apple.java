//Σκαπέτης Χρήστος ΑΕΜ 9378 6944316621 ikofokots@ece.auth.gr
//Ηλίας Κωφοκώτσιος ΑΕΜ 9380 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8


 /* Apple Class
  * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ ΞΏΞΉ ΞΌΞµΟ„Ξ±Ξ²Ξ»Ξ·Ο„Ξ­Ο‚ appleId, appleTileId, color, points
  * ΞΏΞΉ ΞΏΟ€ΞΏΞ―ΞµΟ‚ Ξ±Ξ½Ο„ΞΉΟƒΟ„ΞΏΞΉΟ‡ΞΏΟ�Ξ½ ΟƒΟ„ΞΏ id Ο„ΞΏΟ… ΞΌΞ®Ξ»ΞΏΟ…, Ο„Ο�Ο€ΞΏΟ… int,
  * Ο„ΞΏ id Ο„ΞΏΟ… Ο€Ξ»Ξ±ΞΊΞΉΞ΄Ξ―ΞΏΟ… Ο„ΞΏΟ… Ο„Ξ±ΞΌΟ€Ξ»Ο� Ο�Ο€ΞΏΟ… Ξ²Ο�Ξ―ΟƒΞΊΞµΟ„Ξ±ΞΉ Ο„ΞΏ ΞΌΞ®Ξ»ΞΏ, Ο„Ο�Ο€ΞΏΟ… int,
  * Ο„ΞΏ Ο‡Ο�Ο�ΞΌΞ± Ο„ΞΏΟ… ΞΌΞ®Ξ»ΞΏΟ… (red Ξ® black), Ο„Ο�Ο€ΞΏΟ… string,
  * ΞΏΞΉ Ο€Ο�Ξ½Ο„ΞΏΞΉ Ο€ΞΏΟ… ΞΈΞ± ΞΊΞµΟ�Ξ΄Ξ―Ξ¶ΞµΞΉ Ξ® ΞΈΞ± Ο‡Ξ¬Ξ½ΞµΞΉ Ξ­Ξ½Ξ±Ο‚ Ο€Ξ±Ξ―ΞΊΟ„Ξ·Ο‚ (Ξ±Ξ½Ξ¬Ξ»ΞΏΞ³Ξ± ΞΌΞµ Ο„ΞΏ Ο‡Ο�Ο�ΞΌΞ± Ο„ΞΏΟ… ΞΌΞ®Ξ»ΞΏΟ…)
  * Ο�Ο„Ξ±Ξ½ ΞΈΞ± Ο„Ο�Ο�ΞµΞΉ Ο„ΞΏ ΞΌΞ®Ξ»ΞΏ, Ξ±Ξ½Ο„Ξ―ΟƒΟ„ΞΏΞΉΟ‡Ξ±, Ο„Ο�Ο€ΞΏΟ… int.
  * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ 3 constructors, Ξ­Ξ½Ξ±Ο‚ Ο‡Ο‰Ο�Ξ―Ο‚ Ο�Ο�ΞΉΟƒΞΌΞ±Ο„Ξ±, Ξ­Ξ½Ξ±Ο‚ ΞΌΞµ Ο�Ο�ΞΉΟƒΞΌΞ±Ο„Ξ± ΞΊΞ±ΞΉ Ξ­Ξ½Ξ±Ο‚ ΞΌΞµ Ο�Ο�ΞΉΟƒΞΌΞ± Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½ΞΏ.
  * Ξ ΞµΟ�ΞΉΞ­Ο‡ΞΏΞ½Ο„Ξ±ΞΉ 4 getters ΞΊΞ±ΞΉ 4 setters, Ξ­Ξ½Ξ± Ξ¶ΞµΟ�Ξ³ΞΏΟ‚ Ξ³ΞΉΞ± ΞΊΞ¬ΞΈΞµ ΞΌΞµΟ„Ξ±Ξ²Ξ»Ξ·Ο„Ξ® Ο„Ξ·Ο‚ ΞΊΞ»Ξ¬ΟƒΞ·Ο‚ Ξ±Ο…Ο„Ξ®Ο‚.
  */ 
public class Apple {
	private int appleId;
	private int appleTileId;
	private String color;
	private int points;

	//Ξ�ΞµΞ½Ο�Ο‚ constructor		
	public Apple()
	{
		appleId = 0;
		appleTileId = -1;
		color = "";
		points = 0;
	}

	//Constructor ΞΌΞµ ΞΏΟ�Ξ―ΟƒΞΌΞ±Ο„Ξ±
	public Apple(int appleId, int appleTileid,String color, int points)
	{
		this.appleId = appleId;
		this.appleTileId = appleTileid;
		this.color = color;
		this.points = points;
	}

	//Constructor ΞΌΞµ Ο�Ο�ΞΉΟƒΞΌΞ± Ξ­Ξ½Ξ±Ο‚ Ξ±Ξ½Ο„ΞΉΞΊΞµΞ―ΞΌΞµΞ½ΞΏ
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
