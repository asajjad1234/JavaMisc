/**
 * 
 */
package com.williamssonoma.zipcode.model;

/**
 * @author ahmedsajjad
 * A class to hold the lower and upper bound Zip Code ranges.
 */
public final class ZipCodeRange implements Comparable <ZipCodeRange> {

	private String lowerBound;
	private String upperBound;
	
	/**
	 * Constructor to create ZipCodeRange
	 * @param lowerBound
	 * @param upperBound
	 */
	public ZipCodeRange(String lowerBound, String upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	/**
	 * Compare method to compare 2 different ZipCodeRanges
	 */
	public int compareTo(ZipCodeRange zipRange) {
		return (this.lowerBound.compareTo(zipRange.lowerBound));
	}
	
	/**
	 * @return the lowerBound
	 */
	public String getLowerBound() {
		return lowerBound;
	}

	/**
	 * @return the upperBound
	 */
	public String getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound the upperBound to set
	 */
	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}
	
	@Override
	/**
	 * toString method for ZipCodeRange
	 */
	public String toString() {
		return "["+this.lowerBound + "," + this.upperBound+"] ";
	}
}
