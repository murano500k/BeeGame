package com.murano500k.test.beegame;

import android.os.Bundle;
import android.util.Log;

import com.murano500k.test.beegame.model.Bee;
import com.murano500k.test.beegame.model.QueenBee;
import com.murano500k.test.beegame.model.WorkerBee;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.murano500k.test.beegame.GameLogic.KEY_BEES_LIST;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void negativeTestKillQueen() throws Exception {
		Bundle bundle=new Bundle();
		ArrayList <Bee> bees=new ArrayList<>();
		QueenBee queenBee = new QueenBee(0);
		while(queenBee.getHp()>0){
			queenBee.wasHit();
		}
		Log.d(TAG, "addition_isCorrect: ");
		bees.add(queenBee);
		bees.add(new WorkerBee(1));
		bees.add(new WorkerBee(2));
		bundle.putParcelableArrayList(KEY_BEES_LIST, bees);
		GameInterface gameInterface=new GameInterface() {
			@Override
			public void spawnBees(ArrayList<Bee> bees) {
				Log.d(TAG, "spawnBees: ");
			}

			@Override
			public void beeIsHit(Bee bee) {
				Log.d(TAG, "beeIsHit: ");
			}

			@Override
			public void gameOver() {
				Log.d(TAG, "gameOver: ");
			}
		};
		GameLogic gameLogic=new GameLogic(gameInterface, bees);
		Assert.assertTrue(gameLogic.isGameOver());
	}
	@Test
	public void negativeTestKillAll() throws Exception {
		Bundle bundle=new Bundle();
		ArrayList <Bee> bees=new ArrayList<>();

		WorkerBee workerBee = new WorkerBee(1);
		while(workerBee.getHp()>0){
			workerBee.wasHit();
		}
		assertTrue(workerBee.getHp()==0);
		bees.add(workerBee);
		bees.add(workerBee);
		bundle.putParcelableArrayList(KEY_BEES_LIST, bees);
		GameInterface gameInterface=new GameInterface() {
			@Override
			public void spawnBees(ArrayList<Bee> bees) {
				Log.d(TAG, "spawnBees: ");
			}

			@Override
			public void beeIsHit(Bee bee) {
				Log.d(TAG, "beeIsHit: ");
			}

			@Override
			public void gameOver() {
				Log.d(TAG, "gameOver: ");
			}
		};
		GameLogic gameLogic=new GameLogic(gameInterface, bees);
		Assert.assertTrue(gameLogic.isGameOver());
	}

	@Test
	public void testHit() throws Exception {
		int maxHp=200;
		int damagePerHit=5;
		Bee bee =new Bee("TestBee", maxHp,3,damagePerHit,0);
		int hpBefore=bee.getHp();
		bee.wasHit();
		assertTrue(hpBefore>bee.getHp());
		assertEquals( bee.getHp(),hpBefore-damagePerHit);
	}

	@Test
	public void testHitKill() throws Exception {
		int maxHp=10;
		int damagePerHit=25;
		Bee bee =new Bee("TestBee", maxHp,3,damagePerHit,0);
		bee.wasHit();
		assertEquals( bee.getHp(),0);
	}

		@Test
	public void positiveTest() throws Exception {
		Log.d(TAG, "addition_isCorrect: ");
		GameInterface gameInterface=new GameInterface() {
			@Override
			public void spawnBees(ArrayList<Bee> bees) {
				Log.d(TAG, "spawnBees: ");
			}

			@Override
			public void beeIsHit(Bee bee) {
				Log.d(TAG, "beeIsHit: ");
			}

			@Override
			public void gameOver() {
				Log.d(TAG, "gameOver: ");
			}
		};
		GameLogic gameLogic=new GameLogic(gameInterface, null);
		Assert.assertTrue(!gameLogic.isGameOver());
	}

}