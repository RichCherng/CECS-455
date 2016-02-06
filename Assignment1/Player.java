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
		
		
		Stack<Pruning> pStack = new Stack<Pruning>();
		
		//Can't look pass level 40, dept = 41 is max!
		if(move.size() + depth > MAXMOVES){
			System.out.println("Trying to look past level 40th.");
			return true;
		}
		
		//Traverse down the left-most branches
		for(int i = 0; i < depth; i++){
			move.add(true);
			pStack.push(new Pruning(-10000f,10000f));
		}	
		
		int level = depth;
		float beta = 100000f, alpha= -100000f;
		float maxAlpha = -100000f;
		float leftAlpha = -100000f, rightAlpha = -100000f;
		boolean direction  = false;
		while(pStack.size() > 0){
			Pruning p = pStack.pop();
			
//			if(beta == 100000f){
//				beta = p.beta;
//			}
//			if(alpha == -100000f){
//				alpha = p.alpha;
//			}
			
			if(((level % 2 == 0) && level != 0) || beta > p.beta){
				//alpha = beta;
				if(alpha < beta && beta < 1000f)
					alpha = beta;
				beta = p.beta;
			}
			
			if((level % 2 != 0 && level != 0)  || alpha < p.alpha){
				//beta = alpha;
				if(beta > alpha && alpha > 1000f)
					beta = alpha;
				alpha = p.alpha;
			}
			if(maxAlpha < p.alpha){
				maxAlpha = p.alpha;
			}
			
			if(pStack.empty() && direction == false){
				direction = true;
				leftAlpha = maxAlpha;
				maxAlpha = -100000f;
			} else if (pStack.empty() && direction == true){
				rightAlpha = maxAlpha;
			}
			
			
			
			/***** check if at the "bottom"  *****/
			if(level == depth){
				float value = gameTree.value(move);
				System.out.println(value);
				if(level % 2 == 0){ //Minimizer's turn
					if(beta > value){
						beta = value;
					}
				} else if (level % 2 != 0){ //Maximizer's turn
					if(alpha < value){ 
						alpha = value;
					}
				}
				
			} 
				
			//if not yet, check left
			if(move.get(move.size() - 1)){
				//Push current beta & alpha
				move.set(move.size() - 1, false);
				pStack.add(new Pruning(alpha, beta));
				for(int i = level; i < depth; i ++){
					pStack.add(new Pruning(alpha, beta));
					level++;
					move.add(true);
				}
				
			}  
			else {
				level--;
				move.remove(move.size() - 1);
			}
			
			
			
			
		}


		System.out.println("LeftAlpha = " + leftAlpha);
		System.out.println("RightAlpha = " + rightAlpha);
		return leftAlpha > rightAlpha? true:false;
	}
	
	class Pruning{
		public float beta,alpha;
		
		public Pruning(float a, float b){
			beta = b;
			alpha = a;
		}
	}
}
