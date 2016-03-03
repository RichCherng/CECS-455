//package Assignment2;

import java.io.PrintWriter;
import java.util.Scanner;

public class analyzeLog {
	
	public static void main(String[] args){
		
		Scanner reader = new Scanner(System.in);
		int[] death = new int[501];
		int[] survive = new int[501];
		while(reader.hasNextLine()){
			String[] line = reader.nextLine().split(",");
			//lazy code
			if(line.length <= 1){
				break;
			}
			int dist = Integer.parseInt(line[0].split("\\s+")[3]);
			String[] result = line[1].split("\\s+");
			if(result[2].equals("Death")){
				death[dist]++;
			} else {
				survive[dist]++;
			}
			//line[1].equals("Death")? death[dist-1]++: survive[dist-1]++;
//			for(String i: line){
//				String[] result = i.split("\\s+");
//				dist[Integer.parseInt(result[4]) - 1] += result[1].equals("SurviveDistance")? 1:0;
//			}
			
		}
//		PrintWriter out = new PrintWriter("LogOutput.txt");
//		for(int i = 0 ; i < death.length; i++){
//			out.println("Distance " + (i + 1) + ": Death = " + death[i] + " && Survive = " + survive[i]);	
//		}
		PrintWriter out = null;
		try {
			out = new PrintWriter("LogOutput.txt");
			for(int i = 0 ; i < death.length; i++){
			out.println("Distance " + (i) + ": Death = " + death[i] + " && Survive = " + survive[i] + ",      " + ((float)survive[i]*100 / (survive[i]+death[i])));	
			}
		} catch (Exception e){
			
		}
		out.close();
	}
}
