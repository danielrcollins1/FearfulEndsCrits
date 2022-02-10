import java.util.*;

/******************************************************************************
*  Fearful Ends Crits test math.
*
*  @author   Daniel R. Collins (dcollins@superdan.net)
*  @since    2022-02-09
******************************************************************************/

public class FearfulEndsCrits {

	/** Constant for basic success threshold. */
	static final int SUCCESS_VALUE = 4;

	/** Constant for number of trails per case. */
	static final int NUM_TRIALS = 10000;

	/**
	*  Enumeration for result possibilities.
	*/
	enum Result {Failure, Success, Critical};

	/**
	*  Roll one die.
	*/
	int rollDie (int sides) {
		return (int) (Math.random() * sides) + 1;
	}
	
	/**
	*  Make one skill roll.
	*/
	Result oneSkillRoll (int die1, int die2) {

		// Make rolls
		int roll1 = rollDie(die1);
		int roll2 = rollDie(die2);

		// Check critical
		if (roll1 >= SUCCESS_VALUE
			&& roll2 >= SUCCESS_VALUE
			&& roll1 == roll2)
		{
			return Result.Critical;
		}
		
		// Check basic success
		if (roll1 >= SUCCESS_VALUE
			|| roll2 >= SUCCESS_VALUE)
		{
			return Result.Success;
		}		
			
		// Otherwise we failed
		return Result.Failure;	
	}	
	
	/**
	*  Make many skill rolls.
	*/
	void printOneSkillChances (int die1, int die2) {
		int numSuccess = 0;
		int numCrits = 0;

		// Make a bunch of rolls
		for (int i = 0; i < NUM_TRIALS; i++) {
			switch (oneSkillRoll(die1, die2)) {
				
				// Note fall-through used here
				case Critical: numCrits++;
				case Success: numSuccess++;			
			}
		}	
	
		// Report results
		double pctSuccess = (double) numSuccess / NUM_TRIALS;
		double pctCrits = (double) numCrits / NUM_TRIALS;
		System.out.println("Dice " + die1 + ", " + die2 
			+ "\t" + pctSuccess + "\t" + pctCrits);
	}

	/**
	*  Check skill rolls for all available dice combinations.
	*/
	void printAllSkillChances () {
		for (int die1 = 4; die1 <= 12; die1 += 2) {
			for (int die2 = 4; die2 <= 12; die2 += 2) {
				printOneSkillChances(die1, die2);		
			}
		}
		System.out.println();	
	}

	/**
	*  Main application method.
	*/
	public static void main (String[] args) {
		FearfulEndsCrits fec = new FearfulEndsCrits();
		fec.printAllSkillChances();
	}
}
