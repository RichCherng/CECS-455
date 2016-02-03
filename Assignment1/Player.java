package Assignment1;
import java.util.ArrayList;



public class Player {
	
	public Tree gameTree = null;

	public Player(Tree tree){
		gameTree = tree;
		
		
		ArrayList<Boolean> m = new ArrayList<Boolean>();
		
		for(int i = 0; i < 40; i++){
			System.out.println(tree.value(m));
			m.add(true);
		}
	}
	
	
	public boolean play(ArrayList<Boolean> move, boolean maxNode){
		long before=System.currentTimeMillis();
		
		long after=System.currentTimeMillis();
		
		System.out.println("The time: "+(after-before));
		return true;
	}
}
