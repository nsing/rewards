package com.rewards.service;

import java.util.ArrayList;
import java.util.List;

import com.rewards.dao.RewardsDao;
import com.rewards.exception.InvalidAccountNumberException;
import com.rewards.exception.TechnicalFailureException;
import com.rewards.model.EligibilityStatus;
import com.rewards.model.ResponseMessage;
import com.rewards.model.RewardsResponse;

/**
 * Rewards service implementation.
 * 
 */
public class RewardsServiceImpl implements RewardsService {

	/** Eligibility service **/
	private EligibilityService eligibilityService;

	/** Rewards DAO **/
	private RewardsDao rewardsDao;

	/**
	 * Eligibility service setter.
	 * 
	 * @param eligibilityService
	 *            eligibility service
	 */
	public void setEligibilityService(EligibilityService eligibilityService) {
		this.eligibilityService = eligibilityService;
	}

	/**
	 * Rewards DAO setter.
	 * 
	 * @param rewardsDao
	 *            rewards DAO
	 */
	public void setRewardsDao(RewardsDao rewardsDao) {
		this.rewardsDao = rewardsDao;
	}

	/**
	 * Returns rewards response for the given customer account number and
	 * channels after checking eligibility.
	 * 
	 * @param acctNo
	 *            customer account number
	 * @param channels
	 *            list of channels subscribed by the customer
	 * @return rewards response
	 */
	public RewardsResponse getRewards(String acctNo, List<String> channels) {
		List<String> rewards = new ArrayList<>();
		RewardsResponse rewardsResponse = new RewardsResponse();
		rewardsResponse.setRewards(rewards);

		// Get eligibility status
		try {
			String status = eligibilityService.checkEligibility(acctNo);
	
			if (status.equals(EligibilityStatus.CUSTOMER_ELIGIBLE.name())) {
				// Customer is eligible for rewards
				for (String channel : channels) {
					// Get reward corresponding to each channel subscribed by the
					// customer
					String reward = rewardsDao.getReward(channel);
					if (null != reward) {
						// Add to reward to reward list if not null
						rewards.add(reward);
					}
				}
				rewardsResponse.setMessage(ResponseMessage.CUSTOMER_ELIGBILE_MSG.getMessage());
			}
			else {
				rewardsResponse.setMessage(ResponseMessage.CUSTOMER_NOT_ELIGBILE_MSG.getMessage());
			}
		}	
		catch(InvalidAccountNumberException ianExc) {
			rewardsResponse.setMessage(ResponseMessage.ACCOUNT_NUMBER_INVALID_MSG.getMessage());
		}
		catch(TechnicalFailureException tfExc) {
			rewardsResponse.setMessage(ResponseMessage.SERVICE_TECHNICAL_FAILURE_MSG.getMessage());
		}
		return rewardsResponse;
	}

}
