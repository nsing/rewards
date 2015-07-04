package com.rewards.service;

import java.util.List;

import com.rewards.model.RewardsResponse;

/**
 * Rewards service interface.
 *
 */
public interface RewardsService {

	/**
	 * Returns a list containing rewards.
	 * 
	 * @param acctNo
	 *            customer account number
	 * @param channels
	 *            list of channels subscribed by the customer
	 * @return rewards response containing list of rewards
	 * 
	 */
	RewardsResponse getRewards(String acctNo, List<String> channels);

}
