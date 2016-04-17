package Assignment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {


	public static void main(String[] args){

		Utility u;
		Random rand;

		/***** Set up Utility *******/
		u = new Utility();
		rand = new Random();

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
		ArrayList<Integer> payOut;
		int NUMPLAYERS = u.getNumPlayers();
		int NUMMOVES = u.getNumMoves();
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
					payOut = u.evaluate(moves);
//					printArrayList(payOut);
					int currentPayOut = payOut.get(player);
					int maxMove = moves.get(player);

					//Find better payout for this player
					ArrayList<Integer> tempPayOut;

					for (int i = 0; i < 100; i++){
						moves.set(player, i);
//						printArrayList(moves);
						tempPayOut = u.evaluate(moves);
						if(currentPayOut < tempPayOut.get(player)){
							currentPayOut = tempPayOut.get(player);
							maxMove = i;
						}
					}
					moves.set(player, maxMove);
//					printArrayList(moves);


					//If current is already maxed, move on to the next player
					int check = checkListExist(cycle, moves);
					if(check == -1){
						cycle.add(moves);
						System.out.println(cycle.size());
					} else {
						System.out.println("Check");
						printCycle(cycle);
						printArrayList(moves);
						System.out.println(check);
						System.out.println(player);
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

//		printCycle(cycle);
		System.out.println(resultCycle.size());


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

}
