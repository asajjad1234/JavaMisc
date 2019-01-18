package com.williamssonoma.zipcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.williamssonoma.zipcode.model.ZipCodeRange;
import com.williamssonoma.zipcode.service.ZipCodeRangeMerger;
import com.williamssonoma.zipcode.service.ZipCodeRangeMergerImpl;

/**
 * ZipCodeRangeMergerTest validates all test cases for different types of Zip Codes
 * @author ahmedsajjad
 *
 */
public class ZipCodeRangeMergerTest {
	
	private static ZipCodeRangeMerger zipCodeRangeMerger = new ZipCodeRangeMergerImpl();
	
	@Before
	public void beforeEachTest() {
	}

	@After
	public void afterEachTest() {
	}

	// Test cases from 01 - 08 are for invalid cases
	@Test
	public void testAlphaCharsZipCode() {
		String inputZipCodes = "[ABCDE,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testNonAlphaNumericZipCode() {
		String inputZipCodes = "[!(@#*,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testAlphaNumericZipCode() {
		String inputZipCodes = "[AB123,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testAlphaNonAlphaNumericZipCode() {
		String inputZipCodes = "[394$$,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testNegativeZipCode() {
		String inputZipCodes = "[-1029,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testBlankStringZipCode() {
		String inputZipCodes = "[,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testEmptyStringZipCode() {
		String inputZipCodes = "[         ,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testLessThan5DigitZipCode() {
		String inputZipCodes = "[1234,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	
	// Test cases from 09 - 16 are for valid cases									 
	@Test
	public void testAllZeroDigitsZipCode() {
		String inputZipCodes = "[00000,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(1, zipCodeListOut.size());
	}
	
	@Test
	public void testDisjointIntervalZipCode() {
		String inputZipCodes = "[94133,94149] [94200,94299] [94600,94699]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testAdjacentIntervalZipCode() {
		System.out.println ("testZipCodeRangeMerger6");
		String inputZipCodes = "[95742,95743] [94226,94669] [95744,95760] ";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testOverlappedIntervalZipCode1() {
		String inputZipCodes = "[94133,94149] [94200,94299] [94226,94399]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(2, zipCodeListOut.size());
	}
	
	@Test
	public void testOverlappedIntervalZipCode2() {
		String inputZipCodes = "[95630,95742] [94226,94669] [95730, 99999] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testEnclosedIntervalZipCode1() {
		String inputZipCodes = "[95630,95742] [94226,94669] [95700, 95740] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testFullyEnclosedIntervalZipCode1() {
		String inputZipCodes = "[95630,95742] [94226,94669] [00000, 99999] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(1, zipCodeListOut.size());
	}
	
	@Test
	public void testFullyEnclosedIntervalZipCode2() {
		String inputZipCodes = "[95630,95742] [95742,95743] [95744,95744] [94226,94669] [94133,94133] [00000, 99999] [94200,94229] [01028,01034] [01007,01037] [01060,01065]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(1, zipCodeListOut.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullZipRangesIllegalArgException() throws Exception {
		System.out.println("Output of testNullZipRangesIllegalArgException");
		assertEquals(1, zipCodeRangeMerger.mergeZipCodeRange(null));
	}
}
