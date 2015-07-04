package com.rewards.model;

import java.util.List;

/**
 * Rewards response.
 *
 */
public class RewardsResponse {
	
	/** Rewards list **/
	private List<String> rewards;
	
	/** Response message **/
	private String message;

	/**
	 * Returns rewards.
	 * 
	 * @return list of rewards
	 */
	public List<String> getRewards() {
		return rewards;
	}

	/**
	 * Sets rewards.
	 * 
	 * @param rewards list of rewards
	 */
	public void setRewards(List<String> rewards) {
		this.rewards = rewards;
	}

	/**
	 * Returns response message.
	 * 
	 * @return response message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets response message.
	 * 
	 * @param response message error message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	

}
