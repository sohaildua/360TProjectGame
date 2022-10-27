package com.game.gameproject360t.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.game.gameproject360t.player.InitiatorPlayer;
import com.game.gameproject360t.player.Player;

/**
 * This class is used to start a game Between 2 players
 * 
 * @author Sohail Dua
 *
 */

public class App {

	/**
	 * firstPlayer sends a message to secondPlayer , secondPlayer responds to the firstPlayer with a message
	 * Also Printing out the messages received from both players
	 * 
	 * @param args
	 *
	 */

	private static final int MAX_MESSAGES_IN_QUEUE = 1;

	public static void main(String[] args) throws InterruptedException {

		/*
		 * Blocking queue looks superfluous for single message. But such a queue saves
		 * us from cumbersome synchronization of the threads.
		 */
		BlockingQueue<String> firstToSecond = new ArrayBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);
		BlockingQueue<String> secondToFirst = new ArrayBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);

		// Both players use the same queues symmetrically.
		InitiatorPlayer firstPlayer = new InitiatorPlayer(firstToSecond, secondToFirst);
		Player secondPlayer = new Player(secondToFirst, firstToSecond);

		
		
		
		new Thread(secondPlayer).start();
		new Thread(firstPlayer).start();
	

	}
}
