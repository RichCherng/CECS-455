//package Assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class readLog {

	public static void main(String[] args){
		
		readLog RL = new readLog();
		Scanner reader = new Scanner(System.in);
		int count = 0;
		while(reader.hasNextLine()){
			//System.out.println(reader.nextLine());
			String first = reader.nextLine();
			String second = reader.nextLine();
			String third = reader.nextLine();
			String fourth = reader.nextLine();
			
			readLog.Player player = RL.
					new Player(parseDoubleArray(first), parseDoubleArray(second));
			if(player.makePlay() == Double.parseDouble(third)){
			//if( Math.abs(player.makePlay() - Double.parseDouble(fourth))/ (Double.parseDouble(fourth)+0.00000001) < 0.001){
//				System.out.println(Math.abs(player.makePlay() - Double.parseDouble(third))/ Double.parseDouble(third));
//				if(Double.parseDouble(fourth) == 1.0 || Double.parseDouble(fourth) == 0){
//					count++;
//					System.out.println(player.makePlay());
//					System.out.println(Double.parseDouble(fourth));
//					System.out.printf("%s%n%s%n%s%n%s%n",first, second, third, fourth);
//					System.out.println(player.makePlay());
//				}
//				System.out.printf("%s%n%s%n%s%n%s%n",first, second, third, fourth);
//				System.out.println(player.makePlay());
				count++;
			} 
			
//			if(Double.parseDouble(fourth) == 0 || Double.parseDouble(fourth) == 1){
//				if(Double.parseDouble(fourth) != Double.parseDouble(third)){
//					System.out.printf("%s%n%s%n%s%n%s%n",first, second, third, fourth);
//				}
//			}
			
			//if(fourth.equals("0.0")){
//			if(Double.parseDouble(third) == 1.0){
//				System.out.printf("%s%n%s%n%s%n%s%n",first, second, third, fourth);
//			}
		}
		System.out.println(count);
	}
	
	public static double[][] parseDoubleArray(String input){
//		Scanner read = new Scanner(input);
		double[][] reArray = new double[2][2];
		
		String in = input;
		in = in.replaceAll("[\\[\\]]", " ");
		in = in.replaceAll("\\s+", "");
		String[] num = in.split(",");
//		in = in.replaceAll("]", "");
//		System.out.println(in);
		
		
		reArray[0][0] = Double.parseDouble(num[0]);
		reArray[0][1] = Double.parseDouble(num[1]);
		reArray[1][0] = Double.parseDouble(num[2]);
		reArray[1][1] = Double.parseDouble(num[3]);
		
		
//		while(!read.hasNextDouble()){String a = read.next();}
//		reArray[0][0] = read.nextDouble();
//		while(!read.hasNextDouble()){read.next();}
//		reArray[0][1] = read.nextDouble();
//		while(!read.hasNextDouble()){read.next();}
//		reArray[1][0] = read.nextDouble();
//		while(!read.hasNextDouble()){read.next();}
//		reArray[1][1] = read.nextDouble();
		
		return reArray;
	}
	
	class Player {
		
		
		private double[][] myMatrix, hisMatrix;
		public Player(double[][] mine, double[][] his){
//			myMatrix = mine;
//			hisMatrix = his;
			myMatrix = mine;
			hisMatrix = his;
		}
		
		
//		public double makePlay(){
//			
//			double prob = (hisMatrix[1][1] - hisMatrix[1][0])
//					/(hisMatrix[0][0] - hisMatrix[0][1] + hisMatrix[1][1] - hisMatrix[1][0]);
//			if( prob < 0 || prob > 1){ //Out of range , pure  
////				for( int i = 0; i < myMatrix.length; i++){
////					for(int ii = 0; ii < myMatrix[i].length; ii++){
////					}
////				}
//				
//				
//				
//				if (myMatrix[0][0] > myMatrix[1][0] &&  hisMatrix[0][0] > hisMatrix[0][1]){
//					return 1.0;
//				} else if (myMatrix[0][1] > myMatrix[1][1] && hisMatrix[0][1] > hisMatrix[0][0]){
//					return 1.0;
//				} else {
//					return 0;
//				}
//				
//				
//			}
//			return prob;
//		}
		
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
//			System.out.println(hisMatrix[1][1]);
//			System.out.println(hisMatrix[1][0]);
			//System.out.println(hisMatrix[1][0]);
			BigDecimal p = ONE_ONE.subtract(ONE_ZERO);
			//System.out.println(p);
			BigDecimal deci = ZERO_ZERO.subtract(ZERO_ONE).add(ONE_ONE).subtract(ONE_ZERO);
			//System.out.println(deci);
			p = p.divide(deci,20,RoundingMode.HALF_UP);
			//System.out.println(p);
			//System.out.println(p.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)));
			//System.out.println(BigDecimal.valueOf(Double.MIN_VALUE));
			double prob = (hisMatrix[1][1] - hisMatrix[1][0])/(hisMatrix[0][0] - hisMatrix[0][1] + hisMatrix[1][1] - hisMatrix[1][0]);
			if ((precision - 2) > 16){
				prob = p.doubleValue();
			}
			
			//System.out.println(prob);
			if( prob < 0 || prob > 1){ //Out of range , pure  
//				for( int i = 0; i < myMatrix.length; i++){
//					for(int ii = 0; ii < myMatrix[i].length; ii++){
//					}
//				}
				
				
				
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		public double makePlay(){
//			
//			double prob = (hisMatrix[1][1] - hisMatrix[0][1])
//					/(hisMatrix[0][0] - hisMatrix[0][1] + hisMatrix[1][1] - hisMatrix[1][0]);
//			if( prob < 0 || prob > 1){ //Out of range , pure  
////				for( int i = 0; i < myMatrix.length; i++){
////					for(int ii = 0; ii < myMatrix[i].length; ii++){
////					}
////				}
////				System.out.println(prob);
//				if (myMatrix[0][0] > myMatrix[0][1] && hisMatrix[0][0] > hisMatrix[1][0]){
//					return 1.0;
//				} else if (myMatrix[1][0] > myMatrix[1][1] && hisMatrix[1][0] > hisMatrix[0][0]){
//					return 1.0;
//				} else {
//					return 0;
//				}
//				
//				
//			}
//			
//			return prob;
//		}
		
//		public double dominating(){
//			
//			boolean P_A = 
//			return 0.0;
//		}
	}

}
