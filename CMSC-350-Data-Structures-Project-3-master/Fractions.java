
/**
 * Anthony Borza
 * Project 3: Fractions class has a constructor that accepts a string
 * representation of a fraction and a toString method. It also implements the
 * comparable interface, which include a compareTo method.
 *
 */

public class Fractions implements Comparable<Fractions>
{
	private int numerator;		// numerator declared as private and an integer
	private int denominator;	// denominator declared as private and an integer

	/**
	 * Empty constructor for Fractions
	 */

	public Fractions()
	{
		// empty
	}

	/**
	 * Constructor takes two parameters numerator and denominator
	 *
	 * @param numerator
	 * @param denominator
	 */

	public Fractions(int numerator, int denominator)
	{
		this.numerator = numerator;				// reference to numerator
		this.denominator = denominator;			// reference to denominator
	}

	/**
	 * comapareTo method: compares an object with the specified object for order.
	 * It then returns a zero, negative number, or a positive integer as this object,
	 * which is less than, equal to, or greater than the specified object.
	 *
	 * @param tmp
	 * @return
	 */

	public int compareTo(Fractions tmp)
	{
		return this.numerator * tmp.denominator - tmp.numerator * this.denominator;
	}

	/**
	 * toString method: is used to get a string object representing the value of the
	 * values object. The purpose is to take a primitive data type as its argument,
	 * and then return its value as a string.
	 **/

	public String toString()
	{
		return numerator + "/" + denominator;	// returns value in fraction format and converts it to a string
	}
}



