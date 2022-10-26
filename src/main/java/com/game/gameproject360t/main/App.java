package com.game.gameproject360t.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.game.gameproject360t.player.InitiatorPlayer;
import com.game.gameproject360t.player.Player;


public class App 
{
	 // Blocking queue looks superfluous for single message. But such a queue saves us from cumbersome
    // synchronization of the threads.
    private static final int MAX_MESSAGES_IN_QUEUE = 1;
     

    public static void main(String[] args) throws InterruptedException
    {
        BlockingQueue<String> firstToSecond = new ArrayBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);
        BlockingQueue<String> secondToFirst = new ArrayBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);

        // Both players use the same queues symmetrically.
        InitiatorPlayer firstPlayer = new InitiatorPlayer(firstToSecond, secondToFirst);
        Player secondPlayer = new Player(secondToFirst, firstToSecond);

        // Please note that we can start threads in reverse order. But thankfully to
        // blocking queues the second player will wait for initialization message from
        // the first player.
        
        new Thread(secondPlayer).start();
        Thread.sleep(5000);
        new Thread(firstPlayer).start();
      
        }
    }

