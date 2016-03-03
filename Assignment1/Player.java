package Assignment1;

import java.util.ArrayList;
import java.util.Stack;



public class Player {
	
	public Tree gameTree = null;
	
	public final int MAXMOVES = 40;
	
	public Player(Tree tree){
		
		gameTree = tree;
		
	}
	
	
	public boolean play(ArrayList<Boolean> move, boolean maxNode){

<<<<<<< HEAD
		return looks(10, move);
=======
		return looks(7, move);
>>>>>>> c82ffa51638434e52aaae404f9ac32ec5c4c52cf
	}
	
	
	public boolean looks(int d, ArrayList<Boolean> moves){
		
		int depth = d;
		ArrayList<Boolean> move = new ArrayList<Boolean>(moves);
		if(move.size() + depth > MAXMOVES){
			depth = 40 - move.size() - 1;
		}
		//System.out.println("Depth: " + depth);
		
		Stack<Integer> pStack = new Stack<Integer>();
		//Root
		pStack.push(0);
		for(int i = 0; i < depth; i++){
			pStack.push(0);
			move.add(true);
		}
		
		while(!(pStack.isEmpty())){
			
			int level = pStack.size();
			
			int n = pStack.pop();
			
			//"Bottom Level"
			if(level == depth + 1){
				
				float value = gameTree.value(move);
				//System.out.println(value);
				int parent = pStack.pop();
				if(parent == 0){
					pStack.push( value < 0? -1:1);
				} else if( (level - 1) % 2 == 0){ //Minimizer's turn
					if(parent > 0){
						pStack.push(value < 0? -1: parent);
					}
					
				} else if( (level - 1) % 2 != 0){ //Maximizer's turn
					if(parent < 0){
						pStack.push(value > 0? 1: parent);
					}
				}
				
			} else { 
				//If satisfy the maximizer/minimizer
				int parent;
				if(!pStack.empty()){ //not yet at root
					 parent = pStack.pop();
				} else if(n > 0){
					return true;
				} else {
					return false;
				}
				
<<<<<<< HEAD
				if( (level - 1) % 2 != 0){ //Minimizer's node
					if (n < 0){ //Minimizer got what it wanted
						move.remove(move.size() - 1);
						if(parent == 0){
							pStack.push(-1);
						}
						else {
							pStack.push(parent);
						}
//
						continue;
					} else if(!(move.get(move.size() - 1))){
						move.remove(move.size() -1);
						if(parent < n){ //Max is parent, push positive if n is positive
							pStack.push(n);
						} else {
							pStack.push(parent);
						} 
						continue;
					}
				
				} else if( (level - 1) % 2 == 0){ //Maximizer's node
					if(n > 0){ //Maximizer got what it wanted
						move.remove(move.size() - 1);
						if(parent == 0){
							pStack.push(1);
						} 
						else {
							pStack.push(parent);
						}
						continue;
					} else if(!(move.get(move.size() - 1))){ //if left is already check
						move.remove(move.size() - 1);
						if(parent > n){ //Min is parent, push negative if n is negative
							pStack.push(n);
						} else {
							pStack.push(parent);
						}
						continue;
					} 
				}
				pStack.push(parent);
				
				if((move.size() == 0 || move.get(move.size() - 1))){
					
=======
				//check left if not yet and not at the bottom
				if((move.size() == 0 || move.get(move.size() - 1)) && (p.beta > p.alpha)){
>>>>>>> c82ffa51638434e52aaae404f9ac32ec5c4c52cf
					if(move.size() == 0){
						move.add(true);
					}
					
					move.set(move.size() - 1, false);
					pStack.add(n); //push the current node back to the stack
					pStack.add(0);
					for(int i = level; i < depth; i++){
						pStack.add(0);
						move.add(true);
					}
					
				}
				
				
			
				
			}
		}
		
		return true;
	}
	
	class Pruning{
		public float beta,alpha, value;
		
		public Pruning(float a, float b, float v){
			beta = b;
			alpha = a;
			value = v;
		}
	}
}

