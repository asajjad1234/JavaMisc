/**
 * 
 */
package com.williamssonoma.zipcode.service;

import java.util.List;

import com.williamssonoma.zipcode.model.ZipCodeRange;

/**
 * @author ahmedsajjad
 * ZipRangeMerger Interface for Zip Codes ranges
 *
 */
public interface ZipCodeRangeMerger {

	/**
	 * Reads the input string and constructs ZipCodeRange objects
	 * @param inputZipCodes
	 * @return
	 */
	public List<ZipCodeRange> getInputZipCodes(String inputZipCodes);
	
	/**
	 * 
	 * @param zipCodeList
	 * @return
	 */
	public boolean validateZipCodes(List<ZipCodeRange> zipCodeList);
	
	/**
	 * Fetches the valid zip ranges from a given list.
	 * @param inputZipRanges
	 * @return finalZipRanges
	 * @throws IllegalArgumentException
	 */
	public List<ZipCodeRange> mergeZipCodeRange(List<ZipCodeRange> inputZipRanges) throws IllegalArgumentException;
}
