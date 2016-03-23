package Assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Player {
	
	
	private double[][] myMatrix, hisMatrix;
	public Player(double[][] mine, double[][] his){
		myMatrix = mine;
		hisMatrix = his;
	}
	
	public double makePlay(){
		int precision = 0;
		for(int i = 0; i < myMatrix.length; i++){
			for(int ii=0; ii < myMatrix[i].length; ii++){
				int len = Double.toString(myMatrix[i][ii]).length();
				if(precision < len){
					precision = len;
				}
				
				len = Double.toString(hisMatrix[i][ii]).length();
				if(precision < len){
					precision = len;
				}
			}
		}
		BigDecimal ONE_ONE = new BigDecimal(Double.toString(hisMatrix[1][1]));
		BigDecimal ONE_ZERO = new BigDecimal(Double.toString(hisMatrix[1][0]));
		BigDecimal ZERO_ZERO = new BigDecimal(Double.toString(hisMatrix[0][0]));
		BigDecimal ZERO_ONE = new BigDecimal(Double.toString(hisMatrix[0][1]));
		//double prob = (hisMatrix[1][1] - hisMatrix[1][0])/(hisMatrix[0][0] - hisMatrix[0][1] + hisMatrix[1][1] - hisMatrix[1][0]);
//		System.out.println(hisMatrix[1][1]);
//		System.out.println(hisMatrix[1][0]);
		//System.out.println(hisMatrix[1][0]);
		BigDecimal p = ONE_ONE.subtract(ONE_ZERO);
		//System.out.println(p);
		BigDecimal deci = ZERO_ZERO.subtract(ZERO_ONE).add(ONE_ONE).subtract(ONE_ZERO);
		//System.out.println(deci);
		p = p.divide(deci,20,RoundingMode.HALF_UP);
		//System.out.println(p);
		System.out.println(p.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)));
		//System.out.println(BigDecimal.valueOf(Double.MIN_VALUE));
		double prob = (hisMatrix[1][1] - hisMatrix[1][0])/(hisMatrix[0][0] - hisMatrix[0][1] + hisMatrix[1][1] - hisMatrix[1][0]);
		if ((precision - 2) > 16){
			prob = p.doubleValue();
		}
		
		//System.out.println(prob);
		if( prob < 0 || prob > 1){ //Out of range , pure  
//			for( int i = 0; i < myMatrix.length; i++){
//				for(int ii = 0; ii < myMatrix[i].length; ii++){
//				}
//			}
			
			
			
			if (myMatrix[0][0] > myMatrix[1][0] &&  hisMatrix[0][0] > hisMatrix[0][1]){
				return 1.0;
			} else if (myMatrix[0][1] > myMatrix[1][1] && hisMatrix[0][1] > hisMatrix[0][0]){
				return 1.0;
			} else {
				return 0;
			}
			
			
		}
		return prob;
	}
	
//	public double dominating(){
//		
//		boolean P_A = 
//		return 0.0;
//	}
}
