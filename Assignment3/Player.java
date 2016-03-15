package Assignment3;

public class Player {
	
	
	private double[][] myMatrix, hisMatrix;
	public Player(double[][] mine, double[][] his){
		myMatrix = mine;
		hisMatrix = his;
	}
	
	public double makePlay(){
		return (hisMatrix[1][1] - hisMatrix[1][0])
				/(hisMatrix[0][0] - hisMatrix[0][1] + hisMatrix[1][1] - hisMatrix[1][0]);
	}
}
