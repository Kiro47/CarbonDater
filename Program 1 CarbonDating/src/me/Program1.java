package me;
/**
 * @author James (JC) Helm
 * Program to take data and use it to determine the age, date, and the carbon - 14 percent of an artifact or organism.
 */

import java.util.Calendar;
import java.util.Scanner;

public class Program1 {

	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	int timeHalfLife = 5730; // Half life for Carbon 14
	double rateOfDecay = -0.693;
	double decayConstant = (Math.log(2) / timeHalfLife);

	/**
	 * Main portion of the java program, collects the input data and directs it
	 * to the proper method.
	 * 
	 * Offers a choice option to choose wether calculating percents or age.
	 * @param args
	 *            Main argument collection for the program.
	 */
	public static void main(String[] args) {
		Program1 program1 = new Program1();
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Would you like to calculate a percent (Enter 1) or an age (Enter 2) of an artifact or organism?");
		int choice = input.nextInt();
		if (choice == 1) {
			System.out.println("What is the age of the artifact or organism?");
			int i = input.nextInt();
			System.out.println("The percent of carbon 14 left in the object is "
					+ Math.rint(program1.radiocarbonPercent(i) * 100) + "%.");
			input.close();
			return;
		}
		if (choice == 2) {
			System.out.println("What percent of carbon - 14 of the object? (Ex: 0.20 = 20%");
			double d = input.nextDouble();
			System.out.println("Age: " + Math.abs(program1.radiocarbonAge(d)));
			System.out.println(
					"The year this artifact was created or the organism died was " + program1.radiocarbonDate(d));
			input.close();
		} else {
			input.close();
			System.out.println(choice + " is not a valid option, terminating program.");
		}

	}

	// Time = (ln(0.92)/rateOfDecay) x timeHalfLife
	/**
	 * Finds the date an organism died or artifact was created. Uses
	 * radiocarbonAge() to reduce redundant code.
	 * 
	 * @param p
	 *            The percentage of Carbon 14 in it's original state and it's
	 *            current state. Must be between 0.0 and 1.00
	 * @return Returns the year that an organism died or artifact was created
	 *         truncated down. (Integer)
	 */
	public int radiocarbonDate(double p) {
		// p = percent of radio carbon - 14 remaining.
		// Used radiocarbonAge as it already exists, no need to duplicate code.
		return currentYear - radiocarbonAge(p);

	}

	/**
	 * radioCarbonAge
	 * @param p
	 *            The percentage of Carbon 14 in it's original state and it's
	 *            current state. Must be between 0.0 and 1.00
	 * @return Returns how the old object as an integer truncated down.
	 */
	public int radiocarbonAge(double p) {
		// p = percent of radio carbon - 14 remaining.
		int age = (int) ((float) (Math.log(p) / rateOfDecay) * timeHalfLife);
		return age;

	}

	/**
	 * radioCarbonPercent
	 * @param age The age of the specified artifact or organism.
	 * @return Returns the percentage of radio carbon 14 left within an object.
	 */
	public double radiocarbonPercent(int age) {
		// Carbon14 remaining = N / OrginalNucleiAmount
		double carbonPercent = Math.pow(Math.E, (age / ((timeHalfLife / rateOfDecay))));
		return carbonPercent;

	}

}
