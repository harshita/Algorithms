public class LCS
{
	private static int cost[][];
	private static char direction[][];
	public static void main(String args[]) {
		LCS.computeLCS("amputation", "planning");
	}
	public static void computeLCS(String s1, String s2) {
		char[] tempS1 = s1.toCharArray();
		char[] tempS2 = s2.toCharArray();
		
		cost = new int[tempS1.length][tempS2.length]; 
		direction = new char[tempS1.length][tempS2.length];
		for (int i = 0; i < tempS1.length; i++) {
			for (int j = 0; j < tempS2.length; j++) {
				if (tempS1[i]  == tempS2[j]) {
					cost[i][j] = getCost(i - 1, j - 1) + 1;
					direction[i][j] = '\\';
				}	
				else if (getCost(i - 1, j) >= getCost(i, j - 1)) {
					cost[i][j] = getCost(i - 1, j);
					direction[i][j] = '|';
				}
				else {
					cost[i][j] = getCost(i, j - 1);
					direction[i][j] = '-';
				}
				
			}
		}
		printLCS(s2, tempS1.length - 1, tempS2.length - 1);
    }
	
	public static void printLCS(String s2, int i, int j) {
		//System.out.println(String.format("testing %d %d", i,j));
		if (i == -1 || j == -1)
			return;
		if (direction[i][j] == '\\') {
			printLCS(s2, i - 1, j - 1);
			System.out.println(s2.charAt(j));
		}
		else if (direction[i][j] == '|') {
			printLCS(s2, i - 1, j);
		}
		else	
			printLCS(s2, i, j-1);
			
	}
	
	
	public static int getCost(int i, int j) {
		if (i == -1 || j == -1)
			return 0;
		return cost[i][j];
	}
	
}

