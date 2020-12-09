
public class test {
	public static void main(String[] args) {
		Board board = new Board(10, 20, 3, 3, 6);
		board.createBoard();
		int[][] tiles = new int[10][20];
		int realTileNumber = 1;
		boolean normalDirection = true;
		int n = 10;
		int m = 20;
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
//		for (int i = 0; i < tiles.length; i++) {
//			for (int j = 0; j < tiles[0].length; j++) {
//				System.out.printf("%3d %2d %2d %2d %2d  ", tiles[i][j], i , j ,( (n - 1) - (tiles[i][j] - 1) / m) ,(n-1-i)%2 == 0 ? (tiles[i][j]-1)%m : (m-1)-(tiles[i][j]-1)%m );
//			}
//			System.out.println();
//		}
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
					for(int i=0; i<board.getSnakes().length; i++) {
						int id = board.getSnakes()[i].getSnakeId();
						int headId = board.getSnakes()[i].getHeadId();
						int tailId = board.getSnakes()[i].getTailId();
						int headI = (n-1)-(headId-1)/m;
						int headJ = (n-1-headI)%2 == 0 ? (headId-1)%m : (m-1)-(headId-1)%m;
						int tailI = (n-1)-(tailId-1)/m;
						int tailJ = (n-1-tailI)%2 == 0 ? (tailId-1)%m : (m-1)-(tailId-1)%m;
						elementBoardSnakes[headI][headJ] = "SH" + Integer.toString(id);
						elementBoardSnakes[tailI][tailJ] = "ST" + Integer.toString(id);
					}
			
					//Ακολουθεί το τμήμα που αφορά τις σκάλες
					for(int i=0; i<board.getLadders().length; i++) {
						int id = board.getLadders()[i].getLadderId();
						int upStepId = board.getLadders()[i].getUpStepId();
						int downStepId = board.getLadders()[i].getDownStepId();
						int upStepI = (n-1)-(upStepId-1)/m;
						int upStepJ = ((n-1-upStepI)%2 == 0) ? (upStepId-1)%m : (m-1)-(upStepId-1)%m;
						int downStepI = (n-1)-(downStepId-1)/m;
						int downStepJ = ((n-1-downStepI)%2 == 0) ? (downStepId-1)%m : (m-1)-(downStepId-1)%m;
						elementBoardLadders[upStepI][upStepJ] = "LU" + Integer.toString(id);
						elementBoardLadders[downStepI][downStepJ] = "LD" + Integer.toString(id);
					}
			
			//		Ακολουθεί το τμήμα που αφορά τα μήλα
					for(int i=0; i<board.getApples().length; i++) {
						int id = board.getApples()[i].getAppleId();
						int tileId = board.getApples()[i].getAppleTileId();
						String colour = board.getApples()[i].getColor();
						int tileI = (n-1)-(tileId-1)/m;
						int tileJ = (n-1-tileI)%2 == 0 ? (tileId-1)%m : (m-1)-(tileId-1)%m;
						elementBoardApples[tileI][tileJ] = ("A" + (colour == "Red" ? "R": "B") + Integer.toString(id));
					}
					for (int i = 0; i < tiles.length; i++) {
						for (int j = 0; j < tiles[0].length; j++) {
							System.out.printf("%4d%s%s%s ", tiles[i][j], elementBoardApples[i][j], elementBoardLadders[i][j], elementBoardSnakes[i][j]);
						} 
						System.out.println();
					}
		}
	}

