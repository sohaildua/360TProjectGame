package com.game.gameproject360t.player;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sohail Dua
 *
 */
public class Player implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(Player.class);
	protected final BlockingQueue<String> msgSent;
	protected final BlockingQueue<String> msgRecieved;

	public Player(BlockingQueue<String> msgSent, BlockingQueue<String> msgRecieved) {

		this.msgSent = msgSent;
		this.msgRecieved = msgRecieved;
	}

	private BigInteger numberOfMsgsSent = new BigInteger("0");

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			String recievedMessage = recieve();
			reply(recievedMessage);
		}

	}

	protected void reply(String recievedMessage) {

		// TODO Auto-generated method stub
		String reply = recievedMessage + " " + numberOfMsgsSent;
		try {
			msgSent.put(reply);

			
			System.out.println("Player " + this + "sent message [" + reply +"]");
			numberOfMsgsSent = numberOfMsgsSent.add(BigInteger.ONE);
		} catch (InterruptedException exception) {

			String error = String.format("Player [%s] failed to send a message [%d].", this, numberOfMsgsSent);
			throw new IllegalStateException(error, exception);

		}

	}

	protected String recieve() {
		// TODO Auto-generated method stub
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
