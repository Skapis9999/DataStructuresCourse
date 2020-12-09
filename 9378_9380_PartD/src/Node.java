//Κωφοκώτσιος Ηλίας 9380 6944316621 ikofokots@ece.auth.gr
//Σκαπέτης Χρήστος 9378 6933251534 skapetis@ece.auth.gr

// IF THE COMMENTS CANNOT BE SEEN CHANGE THE ENCODING TO UTF-8

import java.util.ArrayList;

/* Node Class
 * Περιέχονται οι μεταβλητές parent, children, nodeDepth, nodeMove, nodeBoard, nodeEvaluation
 * Ειναι τύπου Node, ArrayList (τύπου Node) , int, πίνακας (τύπου int), Board και double αντίστοιχα
 * αντιστοιχούν στον πατρικό κόμβο,
 * στα παιδιά του κόμβου,
 * στο βάθος του κόμβου,
 * στην κίνηση,
 * στο board του κόμβου,
 * και στην αξιολόγηση της κίνησης αντίστοιχα.
 * Περιέχονται δύο constructors, ο ένας με ορίσματα μεταβλητές και ο άλλος με όρισμα αντικείμενο
 * Περιέχονται 6 getters και 6 setters, από ένα ζεύγος για κάθε μεταβλητή της κλάσης αυτής.
 */ 
public class Node {

	private Node parent;
	private ArrayList<Node>children;
	private int nodeDepth;
	private int[] nodeMove;
	private Board nodeBoard;
	private double nodeEvaluation;
	
	// Constructor με ορίσματα
	public Node(Node parent, int nodeDepth, int[] nodeMove, Board nodeBoard, double nodeEvaluation) {
		this.parent = parent;
		this.nodeDepth = nodeDepth;
		this.nodeMove = nodeMove;
		this.nodeBoard = new Board(nodeBoard);
		this.nodeEvaluation = nodeEvaluation;
		this.children = new ArrayList<Node>();
	}
	
	//Constructor με όρισμα ένα αντικείμενο
	public Node(Node n)
	{
		this.parent = n.parent;
		this.children = n.children;
		this.nodeDepth = n.nodeDepth;
		this.nodeMove = n.nodeMove;
		this.nodeBoard = n.nodeBoard;
		this.nodeEvaluation = n.nodeEvaluation;
	}
	
	//Getters and Setters
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	public int getNodeDepth() {
		return nodeDepth;
	}
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	public int[] getNodeMove() {
		return nodeMove;
	}
	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
	public Board getNodeBoard() {
		return nodeBoard;
	}
	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
	
}
