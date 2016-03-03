package Assignment1;
import java.util.ArrayList;
import java.util.Random;

/**
 * Randomly pick the first 35 moves then on the i = 34th moves, you have the entire 36 nodes at the bottom in your hand,
 * do the beta-pruning then pick the one that guarantee the positive number (wins).
 * 
 * @author LeafCherngchaosil
 *
 */
public class PlayerOne {

	public Tree gameTree = null;
	boolean choiceBool;
	int currentArrSize;
	boolean lastElementInArr;
	int roundCount;
	Random aRand = new Random();

	public PlayerOne(Tree tree) {
		gameTree = tree;
		roundCount = 0;
	}

	public float helpMethod(ArrayList<Boolean> pMoves) {
		if (pMoves.size() == (currentArrSize + 6) || pMoves.size() == 40) {
			float nodeValue = gameTree.value(pMoves);
			pMoves.remove(pMoves.size() - 1);
			if (nodeValue > 0)
				return (float) 0.5;
			else {
				return 0;
			}
		}
		pMoves.add(true);
		float leftVal = helpMethod(pMoves);
		pMoves.add(false);
		float rightVal = helpMethod(pMoves);
		if (pMoves.size() > 0) {
			pMoves.remove(pMoves.size() - 1);
		}
		if (leftVal > rightVal) {
			choiceBool = true;
		} else {
			choiceBool = false;
		}
		return ((leftVal + rightVal) < 1) ? 0 : (leftVal + rightVal);
	}

	public Boolean play(ArrayList<Boolean> pMoves, boolean pMaxNode) {
		if (roundCount == 0) {
			roundCount++;
			return aRand.nextBoolean();
		}
		currentArrSize = pMoves.size();
		lastElementInArr = pMoves.get(pMoves.size() - 1);
		helpMethod(pMoves);
		pMoves.add(lastElementInArr);
		//		System.out.println("Picking " + choiceBool);
		return choiceBool;
	}

	//	public void calculateLevelDeep(int pNumOfLevels) throws IOException {
	//		testBoolArr = new ArrayList<Boolean>();
	//		for (int i = 0; i < 25; i++) {
	//			testBoolArr.add(true);
	//		}
	//		globalBefore = System.currentTimeMillis();
	//		helpMethod(pNumOfLevels);
	//	}
	//
	//	public float helpMethod(int pNumOfLevel) throws IOException {
	//		globalAfter = System.currentTimeMillis();
	//		if (globalAfter - globalBefore > 25000) {
	//			throw new IOException();
	//		}
	//
	//		System.out.println("Size of testBoolArr: " + testBoolArr.size());
	//		System.out.println("Is it equal to: " + (25 + pNumOfLevel));
	//		if (testBoolArr.size() == (25 + pNumOfLevel)) {
	//			testBoolArr.remove(testBoolArr.size() - 1);
	//			return 0;
	//		}
	//		testBoolArr.add(true);
	//		helpMethod(pNumOfLevel);
	//		testBoolArr.add(false);
	//		helpMethod(pNumOfLevel);
	//		if (testBoolArr.size() > 0) {
	//			testBoolArr.remove(testBoolArr.size() - 1);
	//		}
	//		return 0;
	//	}
	//
	//	// Total number of round you get to pick is 20
	//	public Boolean play(ArrayList<Boolean> pMoves, boolean pMaxNode) {
	//		// roundCount starts at 0
	//		roundCount++;
	//		if (roundCount <= 5 && stopTestLevelBool) {
	//			// levelDeep starts at 4
	//			if (levelDeep <= 8 && stopTestLevelBool) {
	//				try {
	//					System.out.println("***TRYING LEVEL*** " + levelDeep);
	//					calculateLevelDeep(levelDeep);
	//				} catch (IOException ioe) {
	//					System.out.printf("***This level %d took too long.**");
	//					stopTestLevelBool = false;
	//					return aRand.nextBoolean();
	//				}
	//				levelDeep++;
	//			}
	//		}
	//		return aRand.nextBoolean();
	//	}
}
