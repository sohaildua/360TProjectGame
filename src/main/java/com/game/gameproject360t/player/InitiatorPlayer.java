package com.game.gameproject360t.player;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * 
 * This class is used to represent the initatorPlayer
 * 
 * @author Sohail Dua
 * 
 * 
 *
 */
public class InitiatorPlayer extends Player {

	
	/**
	 * Constructor of InitiatorPlayer class
	 * 
	 * @param msgSent
	 * @param msgRecieved
	 */
	public InitiatorPlayer(BlockingQueue<String> msgSent, BlockingQueue<String> msgRecieved) {
		super(msgSent, msgRecieved);
	}

	/**
	 * Running the thread through this function for init message
	 */
	@Override
	public void run() {
		sendInitMessage("message");
		int counter = 0;
		while (true) {
			String receivedMessage = recieve();
			reply(receivedMessage);
			counter++;
			if (counter == 10) {
	            System.exit(0);
	        }
		}
	}

	/**
	 * Used to send initMessage which will start the process
	 * 
	 */
	public void sendInitMessage(String initMessage ) {
		try {
			msgSent.put(initMessage);

			System.out.println("Player " + this + " sent message [" + initMessage + "]");
		} catch (InterruptedException interrupted) {
			String error = String.format("Player " + this + " failed to sent message " + initMessage);
			throw new IllegalStateException(error, interrupted);
		}
	}

}
