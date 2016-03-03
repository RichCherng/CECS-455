package Assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Player {
	
	// Count lives of each pace
	HashMap<Integer, Integer> survives = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> losts = new HashMap<Integer, Integer>();
	ArrayList<Integer> distance = new ArrayList<Integer>();
	int lives = 0, selection = 0;;
	int curTrial = 0,curDistIndex = 0, lastIndex;
	
	
	public Player(){
		
		//Initialize variable
		for(int i = 1; i <= 500; i++){
			survives.put(i, 0);
			losts.put(i, 0);
			distance.add(i);
		}
	}
	
	
	
	public int shoot(){
		
		int dist = distance.get(curDistIndex);
		lastIndex = curDistIndex; 
		curTrial++;
		
		if(lives < 1000000){ //Check all 500 paces
			
			if(curTrial >= 2000){  //1000 each pace
				curTrial = 0;
				curDistIndex++;

			} 
			
			
		} else if (lives < 2000000){ //check top 50
			
			
			if(curTrial >= 20000){  //10000 each pace
				curTrial = 0;
				curDistIndex++;

			} 
			
			
		} else if (lives < 3000000){ //Check top 5 
			
			
			if(curTrial >= 200000){  //200000 each pace
				curTrial = 0;
				curDistIndex++;

			} 
			
		} else if (lives < 5000000){ //Check top 2
			
			if(curTrial > 1000000){  //1,000,000 each pace
				curTrial = 0;
				curDistIndex++;

			} 
			
		}
		
		
		lives++;
		
		return dist;
	}
	
	public void result(boolean survived){
		if(survived){
			survives.put(distance.get(lastIndex), survives.get(distance.get(lastIndex))+1);
		} else {
			losts.put(distance.get(lastIndex), survives.get(distance.get(lastIndex))+1);
		}
		
		if(curDistIndex > distance.size() - 1){ //First test over, sort hashmap.
			curTrial = 1;
			curDistIndex = 0;

			/** Sort HashMap Pick **/
			switch(selection){
			case 0:
				survives = sortDist(50, survives);
//				System.out.println(lives);
				selection++;
				break;
				
			case 1:
				survives = sortDist(5, survives);
//				System.out.println(lives);
				selection++;
				break;
			case 2:
				survives = sortDist(2, survives);
//				System.out.println(lives);
				selection++;
				break;
//			default:
//				System.out.println(lives);
			}
			distance = new ArrayList<Integer>();
			for(Map.Entry<Integer, Integer> entry: survives.entrySet()){
				distance.add(entry.getKey());
			}

			
//			System.out.println("Survives: " + survives.size());
//			System.out.println("Distance: " + distance.size());
//			System.out.println(selection);

		}
	}
	
	public int guess(){
		float maxWinChance = 0;
		int bestDist = 0;
		for(Map.Entry<Integer, Integer> entry: survives.entrySet()){
			int wins = entry.getValue();
			float winChance = ((float) wins) / (float)( wins + losts.get(entry.getKey())) ;
//			System.out.println(winChance);
			if( winChance > maxWinChance){
				maxWinChance = winChance;
				bestDist = entry.getKey();
//				System.out.println("Max : " + maxWinChance);
			}
		}
		return bestDist;
	}
	
	public HashMap<Integer, Integer> sortDist(int top, HashMap<Integer, Integer> sur){
		ArrayList<Integer> dist = new ArrayList<Integer>();
		
		for(Map.Entry<Integer, Integer> entry: sur.entrySet()){
			dist.add(entry.getValue());
		}
		Collections.sort(dist);
		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
		
		int maxChance = dist.remove(dist.size() - 1);
		while(temp.size() < top){
			 for(Map.Entry<Integer, Integer> entry: sur.entrySet()){
				 if(entry.getValue() == maxChance){
					 temp.put(entry.getKey(), 0);
					 if(temp.size() >= top){
						 break;
					 }
				 }
			 }
			 maxChance = dist.remove(dist.size() - 1);
		}
		
		return temp;
	}
}
