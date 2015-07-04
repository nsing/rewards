package com.rewards;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rewards.dao.RewardsFileDaoImpl;
import com.rewards.exception.InvalidAccountNumberException;
import com.rewards.exception.TechnicalFailureException;
import com.rewards.model.EligibilityStatus;
import com.rewards.model.ResponseMessage;
import com.rewards.model.RewardsResponse;
import com.rewards.service.EligibilityService;
import com.rewards.service.RewardsService;
import com.rewards.service.RewardsServiceImpl;

/**
 * Rewards service test.
 *
 */
public class RewardsServiceTest {

	/** Rewards service **/
	private static RewardsService rewardsService;

	/** Channels **/
	private static List<String> channels;

	/**
	 * Initialises the data use
	 */
	@BeforeClass
	public static void setUp() {
		// Create and set channels
		channels = new ArrayList<>();
		channels.add("SPORTS");
		channels.add("KIDS");
		channels.add("MOVIES");
		channels.add("MUSIC");
		channels.add("NEWS");

		// Create rewards service implementation
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl();

		// Set rewards DAO
		rewardsServiceImpl.setRewardsDao(new RewardsFileDaoImpl());

		// Set rewards service
		rewardsService = rewardsServiceImpl;
	}

	/**
	 * Test for eligible customer.
	 */
	@Test
	public void testCheckEligibilityEligible() {
		// Set stub for eligibility service returning eligible
		((RewardsServiceImpl) rewardsService)
				.setEligibilityService(new EligibilityService() {
					public String checkEligibility(String acctNo)
							throws InvalidAccountNumberException,
							TechnicalFailureException {
						return EligibilityStatus.CUSTOMER_ELIGIBLE.name();
					}

				});
		// Get rewards response
		RewardsResponse rewardsResponse = rewardsService.getRewards("AccNumb1", channels);
		// Check response 
		assertTrue("Response is null", null != rewardsResponse);
		// Check size
		assertTrue("Size is not as expected", 3 == rewardsResponse.getRewards().size());
		// Check message
		assertTrue("Message is not as expected", ResponseMessage.CUSTOMER_ELIGBILE_MSG.getMessage().equals(rewardsResponse.getMessage()));
	}

	/**
	 * Test for ineligible customer.
	 */
	@Test
	public void testCheckEligibilityIneligible() {
		// Set stub for eligibility service returning ineligible
		((RewardsServiceImpl) rewardsService)
				.setEligibilityService(new EligibilityService() {
					public String checkEligibility(String acctNo)
							throws InvalidAccountNumberException,
							TechnicalFailureException {
						return EligibilityStatus.CUSTOMER_INELIGIBLE.name();
					}

				});
		// Get rewards response
		RewardsResponse rewardsResponse = rewardsService.getRewards("AccNumb1", channels);
		// Check response 
		assertTrue("Response is null", null != rewardsResponse);
		// Check size
		assertTrue("Size is not as expected", 0 == rewardsResponse.getRewards().size());
		// Check message
		assertTrue("Message is not as expected", ResponseMessage.CUSTOMER_NOT_ELIGBILE_MSG.getMessage().equals(rewardsResponse.getMessage()));
	}

	/**
	 * Test for invalid account number exception.
	 */
	@Test
	public void testCheckEligibilityInvalidAccountNumberException() {
		// Set stub for eligibility service throwing invalid account number
		// exception
		((RewardsServiceImpl) rewardsService)
				.setEligibilityService(new EligibilityService() {
					public String checkEligibility(String acctNo)
							throws InvalidAccountNumberException,
							TechnicalFailureException {
						throw new InvalidAccountNumberException();
					}

				});
		// Get rewards response
		RewardsResponse rewardsResponse = rewardsService.getRewards("AccNumb1", channels);
		// Check response 
		assertTrue("Response is null", null != rewardsResponse);
		// Check size
		assertTrue("Size is not as expected", 0 == rewardsResponse.getRewards().size());
		// Check message
		assertTrue("Message is not as expected", ResponseMessage.ACCOUNT_NUMBER_INVALID_MSG.getMessage().equals(rewardsResponse.getMessage()));
	}

	/**
	 * Test for technical failure exception.
	 */
	@Test
	public void testCheckEligibilityTechnicalFailureException() {
		// Set stub for eligibility service throwing technical failure exception
		((RewardsServiceImpl) rewardsService)
				.setEligibilityService(new EligibilityService() {
					public String checkEligibility(String acctNo)
							throws InvalidAccountNumberException,
							TechnicalFailureException {
						throw new TechnicalFailureException();
					}

				});
		// Get rewards response
		RewardsResponse rewardsResponse = rewardsService.getRewards("AccNumb1", channels);
		// Check response 
		assertTrue("Response is null", null != rewardsResponse);
		// Check size
		assertTrue("Size is not as expected", 0 == rewardsResponse.getRewards().size());
		// Check message
		assertTrue("Message is not as expected", ResponseMessage.SERVICE_TECHNICAL_FAILURE_MSG.getMessage().equals(rewardsResponse.getMessage()));
	}

}
