package Assignment1;
import java.util.ArrayList;
import java.util.Stack;



public class Player {
	
	public Tree gameTree = null;
	
	public final int MAXMOVES = 41;
	
	public Player(Tree tree){
		
		gameTree = tree;
		
	}
	
	
	public boolean play(ArrayList<Boolean> move, boolean maxNode){
		long before=System.currentTimeMillis();
		
		long after=System.currentTimeMillis();
		
		System.out.println("The time: "+(after-before));
		return true;
	}
	
	public boolean looks(int depth, ArrayList<Boolean> move){
		
		if(move.size() + depth > MAXMOVES){
			depth = 40 - move.size();
		}
		
		Stack<Pruning> pStack = new Stack<Pruning>();
		
		//Each stack level will represent the node, that why there are depth+1 on the stacks
		pStack.push(new Pruning(-1000f, 10000f, 10000f));
		for(int i = 0; i <depth; i++){
			move.add(true);
			pStack.push(new Pruning(-1000f, 1000f, 10000f));
		}
		
		float leftAlpha = -10000f, rightAlpha = -10000f;
		boolean left = true;
		while(!pStack.isEmpty()){
			
			int level = pStack.size();
			
			Pruning p = pStack.pop();
			System.out.println("Node Alpha: " +p.alpha);
			System.out.println("Node Beta: " + p.beta);
			System.out.println("Node Value: " + p.value);
			System.out.println();
			
			if(pStack.empty()){
//				System.out.println("Root Alpha: " +p.alpha);
//				System.out.println("Root Beta: " + p.beta);
				if(left){
					left = false;
					leftAlpha = p.alpha;
				} else {
					if(p.alpha == leftAlpha){
						rightAlpha = 0;
					} else {
						rightAlpha = p.alpha;
					}
				}
			}
			//beta = p.beta;
			//alpha = p.alpha;
			
			//At the bottom of the tree
			if(level == depth + 1){
				p.value = gameTree.value(move);
				System.out.println(p.value);
				Pruning node = pStack.peek();
				if( (level-1) % 2 == 0){ //Minimizer's turn
					if(p.beta > p.value){
						//beta = value;
						node.beta = p.value;
						//node.value = p.value;
					}
					if(node.value == 10000f){
						node.value = p.value;
					} else if(node.value > p.value && node.value != 10000f){
						node.value = p.value;
					}
					
				} else if ((level-1) % 2 != 0){ //Maximizer's turn
					if(p.alpha < p.value){
						//alpha = value;
						node.alpha = p.value;
						//node.value = p.value;
					}
					if(node.value == 10000f){
						node.value = p.value;
					} else if(node.value < p.value && node.value != 10000f){
						node.value = p.value;
					}
					
				}

			} else {
				
				
				//check left if not yet and not at the bottom
				if((move.size() == 0 || move.get(move.size() - 1)) && (p.beta > p.alpha)){
					if(move.size() == 0){
						move.add(true);
					}
					move.set(move.size() - 1, false);
					pStack.add(p); //push the current node back to the stack
					pStack.add(new Pruning(p.alpha, p.beta, 10000f));
					for(int i = level; i < depth; i++){
						pStack.add(new Pruning(p.alpha, p.beta, 10000f));
						move.add(true);
					}
				} else { //Moving up
					if(pStack.empty()) // At the root
						continue;
					move.remove(move.size() - 1);
					Pruning node = pStack.peek();
					if( (level-1) % 2 == 0){ //Minimizer's turn
						//Check if it's "empty"
						if(node.beta != 1000f){
							if(node.beta > p.value){
								node.beta = p.value;
								node.value = p.value;
							}
						} else {
							node.beta = p.value;
							node.value = node.beta;
						}
						
					} else if ((level-1) % 2 != 0){ //Maximizer's turn
						if( node.alpha != 1000f){
							if(node.alpha < p.value){
								node.alpha = p.value;
								node.value = p.value;
							}
						} else {
							node.alpha = p.value;
							node.value = node.alpha;
						}
					}
				}
			}
			
			
			
		}
		
		
		System.out.println("LeftAlpha = " + leftAlpha);
		System.out.println("RightAlpha = " + rightAlpha);
		return leftAlpha > rightAlpha;
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
