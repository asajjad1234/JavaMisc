package com.williamssonoma.zipcode.service;

import java.util.List;

import com.williamssonoma.zipcode.model.ZipCodeRange;
import com.williamssonoma.zipcode.utils.ZipCodeRangeConstants;

/**
 * This class a test client for Zip Code Range 
 * @author ahmedsajjad
 *
 */
public class ZipCodeRangeTestClient {

	public static void main(String[] args) {

		// Step 1: Accept the input and construct ZipCodeRange objects
		// Step 2: Validate the input. If there's anything wrong, throw the error
		// Step 3: Merge Zip Code Ranges
		// Step 4: Display Results
		
		String[] data = { 
				"[94133,94149] [94200,94299] [94600,94699]", "[95742,95743] [94226,94669] [95744,95760]",
				"[94133,94149] [94200,94299] [94226,94399]",
				"[95630,95742] [94226,94669] [95730,99999] [94133,94133] [94200,94229]",
				"[95630,95742] [94226,94669] [95700,95740] [94133,94133] [94200,94229]",
				"[95630,95742] [94226,94669] [00000,99999] [94133,94133] [94200,94229]",
				"[95630,95742] [95742,95743] [95744,95744] [94226,94669] [94133,94133] [00000, 99999] [94200,94229] [01028,01034] [01007,01037] [01060,01065]"
				};

		ZipCodeRangeMergerImpl zroi = new ZipCodeRangeMergerImpl();
		
		try {
			for (int i = 0; i < data.length; i++) {
				// Step 1: Accept the input and construct ZipCodeRange objects
				List<ZipCodeRange> zipCodeList = zroi.getInputZipCodes(data[i]);

				System.out.print(ZipCodeRangeConstants.INPUT_STRING);
				zroi.displayResults(zipCodeList);
				System.out.println("");

				// Step 2: Validate the input. If there's anything wrong, throw the error
				boolean isValid = zroi.validateZipCodes(zipCodeList);
				System.out.println(ZipCodeRangeConstants.VALID_ZIPCODES_FOUND + isValid);

				// Step 3: Merge Zip Code Ranges
				List<ZipCodeRange> zipCodeListOut = zroi.mergeZipCodeRange(zipCodeList);

				// Step 4: Display Results
				System.out.print(ZipCodeRangeConstants.OUTPUT_STRING);
				zroi.displayResults(zipCodeListOut);
				System.out.println("\n");
			}
		} catch (IllegalArgumentException e1) {
			System.out.println(ZipCodeRangeConstants.INVALID_ZIPCODES_FOUND);
		} catch (Exception e2) {
			System.out.println(ZipCodeRangeConstants.OTHER_ERRORS_FOUND);
		}
	}
}
