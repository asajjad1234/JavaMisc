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

	@Test
	public void testValidateZipCodesAlphaChars() {
		String inputZipCodes = "[ABCDE,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesNonAlphaNumeric() {
		String inputZipCodes = "[!(@#*,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesAlphaNumeric() {
		String inputZipCodes = "[AB123,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesAlphaNonAlphaNumeric() {
		String inputZipCodes = "[394$$,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesNegative() {
		String inputZipCodes = "[-1029,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesBlankString1() {
		String inputZipCodes = "[,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesBlankString2() {
		String inputZipCodes = "[\"\",95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesEmptyString1() {
		String inputZipCodes = "[         ,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesEmptyString2() {
		String inputZipCodes = "[\"      \",95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test
	public void testValidateZipCodesLessThan5Digit() {
		String inputZipCodes = "[1234,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
									 
	@Test
	public void testValidateZipCodesMoreThan5Digit() {
		String inputZipCodes = "[123456,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		boolean isValid = zipCodeRangeMerger.validateZipCodes(zipCodeList);
		assertEquals(false, isValid);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeNullZipRanges() {
		List<ZipCodeRange> zipCodeRangeList = null;
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeEmptyZipRanges() {
		List<ZipCodeRange> zipCodeRangeList = new ArrayList<ZipCodeRange>();
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeAlphaChars() {
		String inputZipCodes = "[ABCDE,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeNonAlphaNumeric() {
		String inputZipCodes = "[!(@#*,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeAlphaNumeric() {
		String inputZipCodes = "[AB123,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeAlphaNonAlphaNumeric() {
		String inputZipCodes = "[394$$,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeNegative() {
		String inputZipCodes = "[-1029,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeBlankString1() {
		String inputZipCodes = "[,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeBlankString2() {
		String inputZipCodes = "[\"\",95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeEmptyString1() {
		String inputZipCodes = "[         ,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeEmptyString2() {
		String inputZipCodes = "[\"      \",95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeLessThan5Digit() {
		String inputZipCodes = "[1234,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMergeZipCodeRangeMoreThan5Digit() {
		String inputZipCodes = "[123456,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
	}
	
	@Test
	public void testMergeZipCodeRangeAllZeroDigits() {
		String inputZipCodes = "[00000,95742] [94226,94669] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(1, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeDisjointInterval() {
		String inputZipCodes = "[94133,94149] [94200,94299] [94600,94699]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeAdjacentInterval() {
		String inputZipCodes = "[95742,95743] [94226,94669] [95744,95760] ";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeOverlappedInterval1() {
		String inputZipCodes = "[94133,94149] [94200,94299] [94226,94399]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(2, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeOverlappedInterval2() {
		String inputZipCodes = "[95630,95742] [94226,94669] [95730, 99999] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeEnclosedInterval() {
		String inputZipCodes = "[95630,95742] [94226,94669] [95700, 95740] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(3, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeFullyEnclosedInterval1() {
		String inputZipCodes = "[95630,95742] [94226,94669] [00000, 99999] [94133,94133] [94200,94229]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(1, zipCodeListOut.size());
	}
	
	@Test
	public void testMergeZipCodeRangeFullyEnclosedInterval2() {
		String inputZipCodes = "[95630,95742] [95742,95743] [95744,95744] [94226,94669] [94133,94133] [00000, 99999] [94200,94229] [01028,01034] [01007,01037] [01060,01065]";
		List<ZipCodeRange> zipCodeRangeList = zipCodeRangeMerger.getInputZipCodes(inputZipCodes);
		List<ZipCodeRange> zipCodeListOut = zipCodeRangeMerger.mergeZipCodeRange(zipCodeRangeList);
		assertEquals(1, zipCodeListOut.size());
	}	
}
