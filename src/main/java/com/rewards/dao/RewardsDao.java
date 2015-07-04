package com.rewards.dao;

/**
 * DAO interface for rewards.
 *
 */
public interface RewardsDao {

	/**
	 * Returns reward string.
	 * 
	 * @param channel
	 *            channel value
	 * @return reward string
	 * 
	 */
	String getReward(String channel);
}
