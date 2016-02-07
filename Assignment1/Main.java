package Assignment1;
import java.util.ArrayList;

public class Main {


	public static void main(String[] args){
		final long randomSeed = 2806598097L;
		
		long before=System.currentTimeMillis();
		Tree t = new Tree(randomSeed);
		
		Player player = new Player(t);
		
		
		
		ArrayList<Boolean> move = new ArrayList<Boolean>();
		System.out.println(player.looks(5, move));
		long after=System.currentTimeMillis();
		System.out.println("The time: "+(after-before));
		
	}
	
	public static void main2(String[] args) {
		final long randomSeed=0L;
		
		Tree t=new Tree(randomSeed);
		ArrayList<Boolean> moves=new ArrayList<Boolean>();
		
		Player player=new Player(t);
		Player other=new Player(t);
		int turn=0;
		for (int i=0; i<t.height; i++) {
			long before=System.currentTimeMillis();
			boolean maxNode=(turn==0);
			Boolean newMove=((maxNode)?player.play(moves, maxNode):other.play(moves, maxNode));
			if (newMove==null) 
				throw new RuntimeException("No decision made.");
			moves.add(newMove);
			long after=System.currentTimeMillis();
			if ((after-before)>30000) {
				if (maxNode) System.out.println("The maximizer took too long: "+(after-before));
				else System.out.println("The minimizer took too long: "+(after-before));
				System.exit(0); }
			System.out.println("Time: "+(after-before));
			turn=1-turn;
		}
		
		System.out.println("Final score: "+t.value(moves));
		
	}

}
