package Assignment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args){



		Utility u = new Utility();
//		System.out.println(u.getNumMoves());
//		System.out.println(u.getNumPlayers());



//		try {
//			u.exportUtility();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try {
			u.importUtility();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Integer> moves = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> cycle = new ArrayList<ArrayList<Integer>>();
		Random rand = new Random();


		for(int i = 0; i < 20; i++){
			moves.add(rand.nextInt(100));
//			System.out.println(moves.get(i));
		}

		cycle.add(moves);




//		ArrayList<Integer> payOut = u.evaluate(moves);
//		for(int i = 0 ; i < payOut.size(); i++){
//			payOut = u.evaluate(moves);
//			moves = new ArrayList<Integer>(moves);
//
//			/**
//			 * Find the better payout,
//			 */
//
//			int move = payOut.get(i);
//
//
//			/**
//			 * Create the new possible move and check payout
//			 * 	if it's better payout, that is the next cycle
//			 * 	else
//			 * 	check the opposite direction.
//			 */
//			ArrayList<Integer> nextMove = new ArrayList<Integer>(moves);
//			nextMove.set(i, nextMove.get(i));
//		}




	}
}
