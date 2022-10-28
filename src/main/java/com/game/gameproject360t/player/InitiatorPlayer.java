package com.game.gameproject360t.player;

import java.util.concurrent.BlockingQueue;

/**
 * @author Sohail Dua
 *
 */
public class InitiatorPlayer extends Player {

	
	private static final String INIT_MESSAGE = "message";

	public InitiatorPlayer(BlockingQueue<String> msgSent, BlockingQueue<String> msgRecieved) {
		super(msgSent, msgRecieved);
	}

	@Override
	public void run() {
		sendInitMessage();
		int i = 0;
		while (i < 4) {
			String receivedMessage = recieve();
			reply(receivedMessage);
			i++;
		}
	}

	private void sendInitMessage() {
		try {
			msgSent.put(INIT_MESSAGE);
		
			System.out.println("Player " + this + " sent message [" + INIT_MESSAGE +"]");
		} catch (InterruptedException interrupted) {
			String error = String.format("Player " + this + " failed to sent message " + INIT_MESSAGE);
			throw new IllegalStateException(error, interrupted);
		}
	}

}
