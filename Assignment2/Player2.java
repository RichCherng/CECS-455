package Assignment2;

import java.util.ArrayList;

public class Player2 {
	
	int[] lives = new int[501];
	int[] deaths = new int[501];
	ArrayList<Integer> distances = new ArrayList<Integer>();
	// respawn < 5,000,000
	int respawn = 1, indexDistance = 0, trial = 1;

	public Player2(){
		
		for(int i = 0; i <= 500; i++){
			distances.add(i);
		}
	}
	
	public int shoot(){
		int dist = distances.get(indexDistance);
		return dist;
	}
	
	public void result(boolean survived){
		
		if(survived){
			lives[distances.get(indexDistance)]++;
		} else {
			deaths[distances.get(indexDistance)]++;
		}
		
		if(respawn == 2000000){ //while less, check 500
			distances = sort(100,distances);
			
//			System.out.println(distances.size());
//			for( int i = 0; i < lives.length; i++){
//				System.out.println(i + " = " + lives[i]+ " : " + deaths[i]);
//			}
			//reset(lives, deaths);
			
		} else if(respawn == 3000000){ // while less, check top 100
			distances = sort(20, distances);
			
//			System.out.println(distances.size());
//			for( int i = 0; i < lives.length; i++){
//				System.out.println(i + " = " + lives[i]+ " : " + deaths[i]);
//			}
			//reset(lives, deaths);
		} else if(respawn == 4000000){ //while less, check top 10
			distances = sort(4,distances);
			
//			System.out.println(distances.size());
//			for( int i = 0; i < lives.length; i++){
//				System.out.println(i + " = " + lives[i]+ " : " + deaths[i]);
//			}
			//reset(lives, deaths);
		} else if(respawn == 5000000){ //while less, check top 5
			distances = sort(1, distances);
			
//			System.out.println(distances.size());
//			for( int i = 0; i < lives.length; i++){
//				System.out.println(i + " = " + lives[i]+ " : " + deaths[i] + "   " + ((float)lives[i])/((float)lives[i] + (float)deaths[i]));
//			}
			//reset(lives, deaths);
		}
		
		indexDistance++;
		respawn++;
		if(indexDistance >= distances.size()){
			indexDistance = 0;
		}
	}
	
	public int guess(){
		//System.out.println(respawn);
		return distances.get(0);
	}
	
	public  ArrayList<Integer> sort(int top, ArrayList<Integer> dist){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int t = 0; t < top; t++){
			//find top lives
			float maxChance = 0, chance;
			int maxDistance = 0, maxIndex = 0;
			for(int i = 0; i < dist.size(); i++){
				//chance = (float)lives[i] / ((float)lives[i] + (float)deaths[i]); //By overall chances
				chance = (float)lives[dist.get(i)] / ((float)lives[dist.get(i)] + (float)deaths[dist.get(i)]);
				if(chance > maxChance){
					maxChance = chance;
//					System.out.println(maxChance);
					maxDistance = dist.get(i);
					maxIndex = i;
				}
			}
			dist.remove(maxIndex);
			temp.add(maxDistance);
		}
		return temp;
	}
	
	public void reset(int[] l, int[] d){
		for(int i =0; i < l.length; i++){
//			if(d[i] > 0)
//				System.out.println((float)l[i] / ((float)d[i] + (float)l[i]));
			l[i] = 0;
			d[i] = 0;
		}
	}
	
}
