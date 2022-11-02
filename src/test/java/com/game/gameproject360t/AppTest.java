package com.game.gameproject360t;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.game.gameproject360t.player.InitiatorPlayer;
import com.game.gameproject360t.player.Player;

/**
 * 
 * Unit tests
 * 
 * @author Sohail Dua
 */

public class AppTest {

	BlockingQueue<String> firstToSecondMessage;
	BlockingQueue<String> secondToFirstMessage;
	InitiatorPlayer firstPlayer;
	Player secondPlayer;

	/**
	 * Create two players, initiator and player
	 */
	@BeforeEach
	public void initialization() {
		firstToSecondMessage = new ArrayBlockingQueue<String>(1);
		secondToFirstMessage = new ArrayBlockingQueue<String>(1);
		firstPlayer = new InitiatorPlayer(firstToSecondMessage, secondToFirstMessage);
		secondPlayer = new Player(secondToFirstMessage, firstToSecondMessage);

	}

	/**
	 * Initiator sends a message to player
	 */
	@Test
	public void sendMessageToSecondPlayer() {
		firstPlayer.sendInitMessage("message");

		Assertions.assertEquals(firstToSecondMessage.size(), 1);

	}

	/**
	 * When initiator sends a message to player, check if player received the same
	 * message
	 */

	@Test
	public void whenInitiatorSendsMessageToPlayer_thenPlayer2SendsBackMessageWithCounter() {
		firstPlayer.sendInitMessage("message");
		secondPlayer.reply("message");
		Assertions.assertEquals(secondToFirstMessage.poll(), "message 0");

	}

}
