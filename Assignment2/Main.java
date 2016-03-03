package Assignment2;

public class Main {

	public static void main(String[] args){
		
		Player2 p = new Player2();
		for(int i = 0; i < 5000000; i++){
			double shot = p.shoot();
			shot = Math.random();// * shot/10.00;
//			System.out.println(shot);
			if(shot > .2){
				p.result(false);
			} else {
				p.result(true);
			}
			//System.out.println(shot);
		}
		System.out.println("The guess is " + p.guess());
	}
}
