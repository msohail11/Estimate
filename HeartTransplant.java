/*************************************************************************
 * Compilation: javac HeartTransplant.java Execution: java HeartTransplant <
 * data.txt
 *
 * @author: Mohammad Sohail mms458@scarletmail.rutgers.edu mms458
 *
 *************************************************************************/

public class HeartTransplant {

	/* ------ Instance variables -------- */

	// Person array, each Person is read from the data file
	private Person[] listOfPatients;

	// SurvivabilityByAge array, each rate is read from data file
	private SurvivabilityByAge[] survivabilityByAge;

	// SurvivabilityByCause array, each rate is read from data file
	private SurvivabilityByCause[] survivabilityByCause;

	/* ------ Constructor -------- */

	/*
	 * Initializes all instance variables to null.
	 */
	public HeartTransplant() {
		listOfPatients = null;
		survivabilityByAge = null;
		survivabilityByCause = null;
	}

	/* ------ Methods -------- */

	/*
	 * Inserts Person p into listOfPatients
	 * 
	 * Returns: 0 if successfully inserts p into array, -1 if there is not enough
	 * space to insert p into array
	 */
	public int addPerson(final Person p, final int arrayIndex) {
		if (listOfPatients[arrayIndex] == null) {
			listOfPatients[arrayIndex] = p;
			return 0;
		}
		return -1;
	}

	/*
	 * 1) Creates the listOfPatients array with numberOfLines length.
	 * 
	 * 2) Reads from the command line data file. File Format: ID, Ethinicity,
	 * Gender, Age, Cause, Urgency, State of health Each line refers to one Person.
	 * 
	 * 3) Inserts each person from file into listOfPatients Hint: uses addPerson()
	 * method
	 * 
	 * Returns the number of patients read from file
	 */
	public int readPersonsFromFile(final int numberOfLines) {
		listOfPatients = new Person[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			final int[] values = new int[7];
			for (int j = 0; j < 7; j++) {
				values[j] = StdIn.readInt();
			}
			final Person x = new Person(values[0], values[1], values[2], values[3], values[4], values[5],
					values[6]);
			addPerson(x, i);
		}
		return numberOfLines;
		// WRITE YOUR CODE HERE
	}

	/*
	 * 1) Creates the survivabilityByAge array with numberOfLines length.
	 * 
	 * 2) Reads from the command line file. File Format: Age YearsPostTransplant
	 * Rate Each line refers to one survivability rate by age.
	 * 
	 * 3) Inserts each rate from file into the survivabilityByAge array
	 * 
	 * Returns the number of survivabilities rates read from file
	 */
	public int readSurvivabilityRateByAgeFromFile(final int numberOfLines) {
		survivabilityByAge = new SurvivabilityByAge[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			final double[] values = new double[3];
			for (int j = 0; j < 3; j++) {
				values[j] = StdIn.readDouble();
			}
			final SurvivabilityByAge x = new SurvivabilityByAge((int) (values[0]), (int) (values[1]),
					values[2]);
			survivabilityByAge[i] = x;
		}
		return numberOfLines;
		// WRITE YOUR CODE HERE
	}

	/*
	 * 1) Creates the survivabilityByCause array with numberOfLines length.
	 * 
	 * 2) Reads from the command line file. File Format: Cause YearsPostTransplant
	 * Rate Each line refers to one survivability rate by cause.
	 * 
	 * 3) Inserts each rate from file into the survivabilityByCause array
	 * 
	 * Returns the number of survivabilities rates read from file
	 */
	public int readSurvivabilityRateByCauseFromFile(final int numberOfLines) {
		survivabilityByCause = new SurvivabilityByCause[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			final double[] values = new double[3];
			for (int j = 0; j < 3; j++) {
				values[j] = StdIn.readDouble();
			}

			final SurvivabilityByCause x = new SurvivabilityByCause((int) values[0], (int) values[1],
					values[2]);
			survivabilityByCause[i] = x;
		}
		return numberOfLines;
		// WRITE YOUR CODE HERE
	}

	/*
	 * Returns listOfPatients
	 */
	public Person[] getListOfPatients() {
		return listOfPatients;
	}

	/*
	 * Returns survivabilityByAge
	 */
	public SurvivabilityByAge[] getSurvivabilityByAge() {
		return survivabilityByAge;
	}

	/*
	 * Returns survivabilityByCause
	 */
	public SurvivabilityByCause[] getSurvivabilityByCause() {
		return survivabilityByCause;
	}

	/*
	 * Returns a Person array in which with every Person that has age above the
	 * parameter age from the listOfPatients array.
	 * 
	 * The return array has to be completely full with no empty spots, that is the
	 * array size should be equal to the number of persons with age above the
	 * parameter age.
	 * 
	 * Return null if there is no Person with age above the parameter age.
	 */
	public Person[] getPatientsWithAgeAbove(final int age) {
		int count = 0;
		for (int i = 0; i < listOfPatients.length; i++) {
			if (listOfPatients[i].getAge() >= age)
				count++;
		}
		if (count == 0)
			return null;

		final Person[] nums = new Person[count];
		count = 0;
		for (int i = 0; i < listOfPatients.length; i++) {
			if (listOfPatients[i].getAge() > age) {
				nums[i] = listOfPatients[i];
				count++;
			}

		}
		return nums;

	}

	/*
	 * Returns a Person array with every Person that has the state of health equal
	 * to the parameter state from the listOfPatients array.
	 * 
	 * The return array has to be completely full with no empty spots, that is the
	 * array size should be equal to the number of persons with the state of health
	 * equal to the parameter state.
	 * 
	 * Return null if there is no Person with the state of health equal to the
	 * parameter state.
	 */
	public Person[] getPatientsByStateOfHealth(final int state) {
		int count = 0;
		for (int i = 0; i < listOfPatients.length; i++) {
			if (listOfPatients[i].getStateOfHealth() == state) {
				count++;
			}
		}

		if (count == 0)
			return null;

		final Person[] nums = new Person[count];
		count = 0;
		for (int i = 0; i < listOfPatients.length; i++) {
			if (listOfPatients[i].getStateOfHealth() == state) {
				nums[i] = listOfPatients[i];
				count++;
			}

		}

		return nums;

	}

	/*
	 * Returns a Person array with every person that has the heart condition cause
	 * equal to the parameter cause from the listOfPatients array.
	 * 
	 * The return array has to be completely full with no empty spots, that is the
	 * array size should be equal to the number of persons with the heart condition
	 * cause equal to the parameter cause.
	 * 
	 * Return null if there is no Person with the heart condition cause equal to the
	 * parameter cause.
	 */
	public Person[] getPatientsByHeartConditionCause(final int cause) {
		int count = 0;
		for (int i = 0; i < listOfPatients.length; i++) {
			if (listOfPatients[i].getCause() == cause) {
				count++;
			}
		}

		if (count == 0)
			return null;

		final Person[] nums = new Person[count];
		count = 0;
		for (int i = 0; i < listOfPatients.length; i++) {
			if (listOfPatients[i].getCause() == cause) {
				nums[i] = listOfPatients[i];
				count++;
			}

		}
		return nums;

	}

	/*
	 * Assume there are numberOfHearts available for transplantation surgery. Also
	 * assume that the hearts are of the same blood type as the persons on the
	 * listOfPatients. This method finds a set of persons to be the recepients of
	 * these hearts.
	 * 
	 * The method returns a Person array from the listOfPatients array that have the
	 * highest potential for survivability after the transplant. The array size is
	 * numberOfHearts.
	 * 
	 * If numberOfHeartsAvailable is greater than listOfPatients array size all
	 * Persons will receive a transplant.
	 * 
	 * If numberOfHeartsAvailable is smaller than listOfPatients array size find the
	 * set of people with the highest potential for survivability.
	 * 
	 * There is no correct solution, you may come up with any set of persons from
	 * the listOfPatients array.
	 */
	public Person[] match(final int numberOfHearts) {
		final Person[] nums = new Person[numberOfHearts];
		if (numberOfHearts >= listOfPatients.length)
			return listOfPatients;

		double[] rates = new double[listOfPatients.length];
		for (int i = 0; i < rates.length; i++) {
			final Person p = listOfPatients[i];
			int age = p.getAge();
			final int cause = p.getCause();
			double sba = 0;
			double sbc = 0;
			int j = 0;

			while (age > survivabilityByAge[j].getAge()) {
				sba = survivabilityByAge[j].getRate();
				j++;
				if (j == survivabilityByAge.length - 1) {
					sba = survivabilityByAge[j].getRate();
					age = Integer.MAX_VALUE;
				}
			}

			j = 0;
			while (cause != survivabilityByCause[j].getCause()) {
				sbc = survivabilityByCause[j].getRate();
				j++;
			}

			final double num = sba * sbc;
			final double div = p.getAge() * (10 - p.getStateOfHealth());

			rates[i] = num / div;
		}

		for (int p = 0; p < nums.length; p++) {
			double high = rates[0];
			int index = 0;
			for (int i = 1; i < rates.length; i++) {
				if ((high < rates[i])) {
					high = rates[i];
					index = i;
				}
			}

			nums[p] = listOfPatients[index];

			final double[] rates2 = new double[rates.length - 1];
			int count = 0;
			for (int i = 0; i < rates.length; i++) {
				if (i != index) {
					rates2[count] = rates[i];
					count++;
				}
				rates = rates2;
			}

		}
		return nums;

		// WRITE YOUR CODE HERE
	}

	/*
	 * Client to test the methods you write
	 */
	public static void main(final String[] args) {

		final HeartTransplant ht = new HeartTransplant();

		// read persons from file
		int numberOfLines = StdIn.readInt();
		// System.out.println(numberOfLines);
		int numberOfReadings = ht.readPersonsFromFile(numberOfLines);
		StdOut.println(numberOfReadings + " patients read from file.");

		// read survivability by age from file
		numberOfLines = StdIn.readInt();
		numberOfReadings = ht.readSurvivabilityRateByAgeFromFile(numberOfLines);
		StdOut.println(numberOfReadings + " survivability rates by age lines read from file.");

		// read survivability by heart condition cause from file
		numberOfLines = StdIn.readInt();
		numberOfReadings = ht.readSurvivabilityRateByCauseFromFile(numberOfLines);
		StdOut.println(numberOfReadings + " survivability rates by cause lines read from file.");

		// list all patients
		for (final Person p : ht.getListOfPatients()) {
			StdOut.println(p);
		}

		// list survivability by age rates
		for (final SurvivabilityByAge rate : ht.getSurvivabilityByAge()) {
			StdOut.println(rate);
		}

		// list survivability by cause rates
		for (final SurvivabilityByCause rate : ht.getSurvivabilityByCause()) {
			StdOut.println(rate);
		}

	}
}
