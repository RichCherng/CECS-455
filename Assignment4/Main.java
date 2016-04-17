package Assignment4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main {


	public static void main(String[] args){

		Utility u;


		/***** Set up Utility *******/
		u = new Utility();


		/***** Read in File ****/
		try {
			u.importUtility();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		ArrayList<Integer> moves;
		ArrayList<ArrayList<Integer>> cycle;
		ArrayList<ArrayList<Integer>> resultCycle;
		int NUMPLAYERS = u.getNumPlayers();
		/******* Start Cycle  Setup******/

		moves = new ArrayList<Integer>(); //Instantiate first moves
		cycle = new ArrayList<ArrayList<Integer>>();

		//Random move
		for(int i = 0; i < 20; i++){
			moves.add(rand.nextInt(100));
		}

		// Find max of one player first, that will be the first move in the cycle

		whileLoop:
			while(true){
				for( int player = 0; player < NUMPLAYERS; player++){
					moves = new ArrayList<Integer>(moves);

					moves = getNextMove(player, moves, u);

					//If current is already maxed, move on to the next player
					int check = checkListExist(cycle, moves);
					if(check == -1){
						cycle.add(moves);

					} else {
//						System.out.println(checkUnique(cycle));
						/** Check if any other place want to move **/
//						printCycle(cycle);
						System.out.println(cycle.size());
//						System.out.println(check + " : " + cycle.size());
						if (check == cycle.size() - 1){ //This player doesn't want to move
							continue;
						} else {
							/**
							 * Get Index of that moves, cut anything else before that moves
							 */
							resultCycle = new ArrayList<ArrayList<Integer>>();
							for(int index = check; index < cycle.size(); index++){
								resultCycle.add(cycle.get(index));
							}

							break whileLoop; //Cycle created
						}

					}

				}
			}

//		printCycle(cycle);
		System.out.println(resultCycle.size());
		printFile("result.txt", resultCycle);


	}

	public static boolean findCycle(Utility u){

		Random rand = new Random();
		ArrayList<Integer> moves;
		ArrayList<ArrayList<Integer>> cycle;
		ArrayList<ArrayList<Integer>> resultCycle;
		int NUMPLAYERS = u.getNumPlayers();
		/******* Start Cycle  Setup******/

		moves = new ArrayList<Integer>(); //Instantiate first moves
		cycle = new ArrayList<ArrayList<Integer>>();

		//Random move
		for(int i = 0; i < 20; i++){
			moves.add(rand.nextInt(100));
		}

		// Find max of one player first, that will be the first move in the cycle

		whileLoop:
			while(true){
				for( int player = 0; player < NUMPLAYERS; player++){
					moves = new ArrayList<Integer>(moves);

					moves = getNextMove(player, moves, u);

					//If current is already maxed, move on to the next player
					int check = checkListExist(cycle, moves);
					if(check == -1){
						cycle.add(moves);

					} else {
//						System.out.println(checkUnique(cycle));
						/** Check if any other place want to move **/
//						printCycle(cycle);
						System.out.println(cycle.size());
//						System.out.println(check + " : " + cycle.size());
						if (check == cycle.size() - 1){ //This player doesn't want to move
							continue;
						} else {
							/**
							 * Get Index of that moves, cut anything else before that moves
							 */
							resultCycle = new ArrayList<ArrayList<Integer>>();
							for(int index = check; index < cycle.size(); index++){
								resultCycle.add(cycle.get(index));
							}

							printFile("result.txt", resultCycle);
							return true; //Cycle created
						}

					}

				}
				if(cycle.size() > 5000){
					break;
				}
			}

		return false;
	}

	public static ArrayList<Integer> getNextMove(int player, ArrayList<Integer> moves, Utility u){
		ArrayList<Integer> payOut = u.evaluate(moves);
		int currentPayOut = payOut.get(player);
		int maxMove = moves.get(player);

		for (int i = 0; i < 100; i++){
			moves.set(player, i);
//			printArrayList(moves);
			payOut = u.evaluate(moves);
			if(currentPayOut < payOut.get(player)){
				currentPayOut = payOut.get(player);
				maxMove = i;
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>(moves);
		result.set(player, maxMove);

		return result;


	}

	public static void printCycle(ArrayList<ArrayList<Integer>> Cycle){
		for(ArrayList<Integer> i: Cycle){
			for(Integer a: i){
				System.out.print(a + ", ");
			}
			System.out.println();
		}
	}

	public static void printArrayList(ArrayList<Integer> list){
		for(Integer i: list){
			System.out.print(i + ", ");
		}
		System.out.println();
	}


	public static int checkListExist(ArrayList<ArrayList<Integer>> cycle, ArrayList<Integer> moves){

		for(int i = 0; i < cycle.size(); i++){
			if(moves.equals(cycle.get(i))){
				return i;
			}
		}
		return -1;
	}

	public static boolean checkUnique(ArrayList<ArrayList<Integer>> cycle){
		for(int i = 0; i < cycle.size(); i++){
			for(int ii = i+1; ii <cycle.size(); ii++){
				if(cycle.get(i).equals(cycle.get(ii))){
					return false;
				}
			}
		}
		return true;
	}

	public static void printFile(String fileName, ArrayList<ArrayList<Integer>> result){
		try{
			PrintWriter out = new PrintWriter(fileName);
			for(ArrayList<Integer> arr: result){
				out.printf("[%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]%n", arr.get(0), arr.get(1), arr.get(2),
						arr.get(3), arr.get(4), arr.get(5),arr.get(6),arr.get(7),arr.get(8),arr.get(9));
			}

			out.close();
		} catch (FileNotFoundException e1){
			e1.printStackTrace();
		}
	}

}
