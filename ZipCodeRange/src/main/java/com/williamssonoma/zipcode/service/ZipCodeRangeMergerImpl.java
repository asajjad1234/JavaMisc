/**
 * 
 */
package com.williamssonoma.zipcode.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.williamssonoma.zipcode.model.ZipCodeRange;
import com.williamssonoma.zipcode.utils.ZipCodeRangeConstants;
import com.williamssonoma.zipcode.validator.ZipCodeRangeValidator;

/**
 * @author ahmedsajjad 
 * ZipCodeRangeMerger takes the inputs of Zip Codes ranges and merges them
 *
 */
public class ZipCodeRangeMergerImpl implements ZipCodeRangeMerger {
	
	private final static Logger logger = Logger.getLogger(ZipCodeRangeMergerImpl.class.getName());
	private ZipCodeRangeValidator zipCodeRangeValidator = new ZipCodeRangeValidator();
	
	/**
	 * This method compares two strings and returns the string that's greater of the two
	 * @param s1
	 * @param s2
	 * @return max
	 */
	private String getGreaterString(String s1, String s2) {
		String max = s2;
		if (s1.compareTo(s2) >= 1) {
			max = s1;
		}
		return (max);
	}

	// Step 1: Accept the input and construct ZipCodeRange objects
	
	/**
	 * This method takes the input string and returns a list of Zip Code Range objects
	 * @param inputZipCodes
	 * @return zipCodeList
	 */
	public List<ZipCodeRange> getInputZipCodes(String inputZipCodes) {

		List<ZipCodeRange> zipCodeList = new ArrayList<ZipCodeRange>();
		inputZipCodes = inputZipCodes.replaceAll(" ", "");
		StringTokenizer st = new StringTokenizer(inputZipCodes, "][");

		while (st.hasMoreTokens()) {
			String zipStr = st.nextToken();
			String[] zipCodes = zipStr.split(",");
			ZipCodeRange zipObject = new ZipCodeRange(zipCodes[0], zipCodes[1]);
			zipCodeList.add(zipObject);
		}
		return (zipCodeList);
	}

	// Step 2: Validate the input. If there's anything wrong, throw the error
	/**
	 * This method calls the validator to check if the Zip Codes are valid
	 */
	public boolean validateZipCodes(List<ZipCodeRange> zipCodeRangeList) {
		return (zipCodeRangeValidator.validateZipCodes(zipCodeRangeList));
	}

	// Step 3: Merge Zip Code Ranges
	/**
	 * Fetches the valid zip ranges from a given list.
	 * 
	 * @param inputZipRanges
	 * @return resultZipRanges
	 * @throws IllegalArgumentException
	 */
	public List<ZipCodeRange> mergeZipCodeRange(List<ZipCodeRange> zipCodeRangeList) throws IllegalArgumentException {
		
		if (zipCodeRangeList == null || zipCodeRangeList.isEmpty() || !validateZipCodes(zipCodeRangeList)) {
			
			if (logger.isDebugEnabled()) {
				logger.debug(ZipCodeRangeConstants.INVALID_ZIPCODES_FOUND);
			}
			throw new IllegalArgumentException(ZipCodeRangeConstants.INVALID_ZIPCODES_FOUND);
		}

		// The size of the list is 1. So, there is nothing to do.
		if (zipCodeRangeList.size() <= 1)
			return zipCodeRangeList;

		// Sort the ZipCodes in ascending order
		Collections.sort(zipCodeRangeList);

		// Accumulate processed ZipCodes in a Linked List
		List<ZipCodeRange> resultZipRanges = new LinkedList<ZipCodeRange>();
		String index1 = zipCodeRangeList.get(0).getLowerBound();
		String index2 = zipCodeRangeList.get(0).getUpperBound();

		for (ZipCodeRange interval : zipCodeRangeList) {
			if (interval.getLowerBound().compareTo(index2) <= 0) {
				// Overlapping ZipCode ranges, move the end if needed
				index2 = getGreaterString(index2, interval.getUpperBound());
			} else {
				// Disjoint ZipCode ranges, add the previous one and reset bounds
				resultZipRanges.add(new ZipCodeRange(index1, index2));
				index1 = interval.getLowerBound();
				index2 = interval.getUpperBound();
			}
		}
		// Add the last interval
		resultZipRanges.add(new ZipCodeRange(index1, index2));
		return resultZipRanges;
	}

	// Step 4: DisplayResults
	/**
	 * This method displays the contents of the list
	 * @param zipCodeList
	 */
	public void displayResults(List<ZipCodeRange> zipCodeList) {
		for (ZipCodeRange item : zipCodeList) {
			System.out.print(item.toString());
		}
	}
}
