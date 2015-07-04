package com.rewards.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Rewards file DAO implementation.
 * 
 */
public class RewardsFileDaoImpl implements RewardsDao {

	/** Channel reward file name **/
	private static final String CHANNEL_REWARD_FILE = "channel_reward.txt";

	/** Channel reward map **/
	private Map<String, String> channelRewards;

	/** Constructor **/
	public RewardsFileDaoImpl() {
		// load data
		loadChannelRewards();
	}

	/**
	 * Loads channel rewards from a file.
	 * 
	 */
	private void loadChannelRewards() {
		channelRewards = new HashMap<String, String>();
		ClassLoader classLoader = RewardsFileDaoImpl.class.getClassLoader();
		File file = new File(classLoader.getResource(CHANNEL_REWARD_FILE)
				.getFile());
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				channelRewards.put(sc.next(), sc.next());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns reward string.
	 * 
	 * @param channel
	 *            channel value
	 * @return reward string
	 * 
	 */
	public String getReward(String channel) {
		return channelRewards.get(channel);
	}

}
