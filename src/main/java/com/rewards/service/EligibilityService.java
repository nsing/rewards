package com.rewards.service;

import com.rewards.exception.InvalidAccountNumberException;
import com.rewards.exception.TechnicalFailureException;

/**
 * Eligibility service interface.
 * 
 */
public interface EligibilityService {

	/**
	 * Returns eligibility for an account number.
	 * 
	 * @param acctNo
	 *            customer account number
	 * @return eligibility
	 * @throws InvalidAccountNumberException
	 * @throws TechnicalFailureException
	 */
	String checkEligibility(String acctNo)
			throws InvalidAccountNumberException, TechnicalFailureException;
}