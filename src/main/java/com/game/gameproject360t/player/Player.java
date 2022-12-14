package com.game.gameproject360t.player;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * This class is used to run a player class 
 * 
 * @author Sohail Dua
 *
 */

public class Player implements Runnable {

	protected final BlockingQueue<String> msgSent;
	protected final BlockingQueue<String> msgRecieved;

	
	/**
	 * Constructor of Player class
	 * @param msgSent
	 * @param msgRecieved
	 */
	public Player(BlockingQueue<String> msgSent, BlockingQueue<String> msgRecieved) {

		this.msgSent = msgSent;
		this.msgRecieved = msgRecieved;
	}

	private BigInteger numberOfMsgsSent = new BigInteger("0");

	
	
	/**
	 * Running the thread through this function
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			String recievedMessage = recieve();
			reply(recievedMessage);
		}

	}

	/**
	 * send a message to another player
	 * @param recievedMessage message send to another player
	 * @throws InterruptedException if the thread is interrupted while sending a message
	 * @throws IllegalStateException if thread is already started or stop
	 */
	public void reply(String recievedMessage) {

		String reply = recievedMessage + " " + numberOfMsgsSent;
		try {
			msgSent.put(reply);

			System.out.println("Player " + this + "sent message [" + reply + "]");
			numberOfMsgsSent = numberOfMsgsSent.add(BigInteger.ONE);
		} catch (InterruptedException exception) {

			String error = String.format("Player [%s] failed to send a message [%d].", this, numberOfMsgsSent);
			throw new IllegalStateException(error, exception);

		}

	}

	
	/**
	 * receives the message in BlockingQueue
	 * @throws InterruptedException if the thread is interrupted while receive a message
	 * @throws IllegalStateException if thread is already started or stop
	 * @return recievedMessage
	 */
	public String recieve() {
		String recievedMessage = "";
		try {

			recievedMessage = msgRecieved.take();
		} catch (InterruptedException exception) {

			String error = String.format("Player [%s] failed to receive a message [%d].", this, numberOfMsgsSent);
			throw new IllegalStateException(error, exception);

		}
		return recievedMessage;
	}

}
