package com.williamssonoma.zipcode.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.williamssonoma.zipcode.model.ZipCodeRange;
import com.williamssonoma.zipcode.utils.ZipCodeRangeConstants;

/**
 * This Validator class is used to check if the ZipCodes are valid
 * @author ahmedsajjad
 *
 */
public class ZipCodeRangeValidator {

	/**
	 * This method checks if a ZipCode is a valid and returns true/false
	 * @param zipCode
	 * @return true/false
	 */
	private boolean isValid(String zipCode) {
		Pattern pattern = Pattern.compile(ZipCodeRangeConstants.ZIPCODE_REGEX);
		Matcher matcher = pattern.matcher(zipCode);
		return matcher.matches();
	}
	
	// Step 2: Validate the input. If there's anything wrong, throw the error
	/**
	 * This method is called from all other classes to check the validity of the Zip Code
	 */
	public boolean validateZipCodes(List<ZipCodeRange> zipCodeRangeList) {
		boolean valid = true;
		
		for (ZipCodeRange zipCode : zipCodeRangeList) {
			if (!isValid(zipCode.getLowerBound()) || !isValid(zipCode.getUpperBound())) {
				valid = false;
				break;
			}
		}
		return (valid);
	}
}
