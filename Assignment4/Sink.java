package Assignment4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Sink {


	Utility u;
	int NUMPLAYER;
	int NUMMOVES;
	int numFile = 0;

	public Sink(){
		u = new Utility();
		NUMPLAYER = u.getNumPlayers();
		NUMMOVES = u.getNumMoves();
		try{
			u.importUtility();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void findCircle(){
		Random  rand = new Random();
		HashSet<String> hashMove = new HashSet<String>();
		ArrayList<Integer> move = new ArrayList<Integer>();
		Queue<Node> queue = new ArrayDeque<Node>();

		for(int i = 0; i < NUMPLAYER; i++){
			move.add(rand.nextInt(NUMMOVES));
		}

		Node root = new Node(move, null);
		hashMove.add(root.hash);
		queue.add(root);
		int count = 1;
		while(!queue.isEmpty()){
			Node node = queue.remove();
			node.findNextMoves(u);
			for(Node n: node.child){

				if(hashMove.contains(n.hash)){ //Check for Cycle
//					System.out.println(n.hash);
					Node cycleHead = n;
					Node parent = node.parent;
					Stack<Node> stack = new Stack<Node>();
					stack.add(node);
					stack.add(parent);
					while(  parent != null && !parent.hash.equals(cycleHead.hash)){
						parent = parent.parent;
						stack.add(parent);

					}

					if (parent == null){
//						System.out.println("Hit Root " + count);
						continue;
					}

					if( parent.hash.equals(cycleHead.hash)){
						ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
						while(!stack.isEmpty()){
							result.add(stack.pop().move);
						}
						printFile((++numFile)+"result.txt", result);

						System.out.println("Found Cycle");
					}





				} else {
					hashMove.add(n.hash);
					queue.add(n);
					count++;


					int size = hashMove.size();
					if(size % 5000 == 0){
						System.out.println(size);
					}
//					System.out.println(++count);
				}

			}


		}
	}

	public void printFile(String fileName, ArrayList<ArrayList<Integer>> result){
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

	public void printArrayList(ArrayList<Integer> list){
		for(Integer i: list){
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	class Node{

		ArrayList<Node> child;
		HashSet<String> hashChild;
		ArrayList<Integer> move;
		String hash;
		Node parent;
		public Node(ArrayList<Integer> m, Node p){

			parent = p;
			move = m;
			child = new ArrayList<Node>();
			hashChild = new HashSet<String>();
			hash = getHash(move);

		}

		public void findNextMoves(Utility u){
			int count = 0;
			int NUMPLAYER = u.getNumPlayers();


			for(int player = 0; player < NUMPLAYER; player++){
				ArrayList<Integer> nextMove = getNextMove(player, move, u);
//				printArrayList(nextMove);
				String hash = getHash(nextMove);
				if(!hashChild.contains(hash)){
					child.add(new Node(nextMove,this));
					hashChild.add(hash);
				}

			}
		}

		public ArrayList<Integer> getNextMove(int player, ArrayList<Integer> moves, Utility u){
			ArrayList<Integer> payOut = u.evaluate(moves);
			ArrayList<Integer> cMoves = new ArrayList<Integer>(moves);
			int currentPayOut = payOut.get(player);
			int maxMove = move.get(player);

			for(int i = 0; i < NUMMOVES; i++){
				cMoves.set(player, i);
				payOut = u.evaluate(cMoves);
				if(currentPayOut < payOut.get(player)){
					currentPayOut = payOut.get(player);
					maxMove = i;
				}
			}
			cMoves.set(player, maxMove);
			return cMoves;
		}

		public String getHash(ArrayList<Integer> moves){
			String hash = "";
			for(Integer m: moves){
				hash += m  +"-";
			}
			return hash;
		}



	}


	public static void main(String[] args){

		System.out.println("Looking for Cycle...");
		Sink sink = new Sink();
		sink.findCircle();

	}
}
