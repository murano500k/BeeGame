package com.murano500k.test.beegame;

import android.util.Log;

import com.murano500k.test.beegame.model.Bee;
import com.murano500k.test.beegame.model.DroneBee;
import com.murano500k.test.beegame.model.QueenBee;
import com.murano500k.test.beegame.model.WorkerBee;

import java.util.ArrayList;
import java.util.Random;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by artem on 1/16/17.
 */

public class GameLogic{
	public static final String KEY_BEES_LIST = "list";
	private static final String TAG = "GameLogic";
	public static final int MAX_COUNT_WORKER=8;
	public static final int MAX_COUNT_DRONE=5;
	public static final int MAX_COUNT_QUEEN=1;

	public static final int COLUMN_COUNT=6;
	public static final int ROW_COUNT=6;
	public static final int CELL_COUNT=COLUMN_COUNT*ROW_COUNT;
	public static final int MAX_HP_WORKER=50;

	public static final int MAX_HP_DRONE=75;
	public static final int MAX_HP_QUEEN=100;
	public static final int DAMAGE_PER_HIT_WORKER=12;

	public static final int DAMAGE_PER_HIT_DRONE=10;
	public static final int DAMAGE_PER_HIT_QUEEN=8;
	public static final String NAME_WORKER="Worker";

	public static final String NAME_DRONE="Drone";
	public static final String NAME_QUEEN="Queen";
	private GameInterface gameInterface;

	ArrayList<Bee> bees;

	public GameLogic(GameInterface gameInterface) {
		Log.d(TAG, "GameLogic: new constructor");
		this.gameInterface = gameInterface;
		loadBees();
	}

	public GameLogic(GameInterface gameInterface, ArrayList<Bee> list) {
		Log.d(TAG, "GameLogic:resume constructor");
		this.gameInterface = gameInterface;
		loadBees(list);
	}

	private void loadBees(ArrayList<Bee> savedList) {
		if(savedList!=null ) bees=savedList;
		else bees=new ArrayList<>();
		if(isGameOver()) gameInterface.gameOver();
	}
	private void loadBees() {
		bees=new ArrayList<>();
			for (int i = 0; i < MAX_COUNT_DRONE; i++) {
				bees.add(new DroneBee(getRandomCellIndex()));
			}
			Log.d(TAG, "DroneBees spawned");

			for (int i = 0; i < MAX_COUNT_WORKER; i++) {
				bees.add(new WorkerBee(getRandomCellIndex()));
			}
			Log.d(TAG, "WorkerBees spawned");

			for (int i = 0; i < MAX_COUNT_QUEEN; i++) {
				bees.add(new QueenBee(getRandomCellIndex()));
			}
			Log.d(TAG, "QueenBees spawned");
	}
	boolean checkCellAvailable(int i){
		for(Bee bee: bees){
			if(bee.getIndex()==i) return false;
		}
		return true;
	}
	int getRandomCellIndex(){
		int cellIndex= new Random().nextInt(CELL_COUNT);
		while(!checkCellAvailable(cellIndex)) cellIndex= new Random().nextInt(CELL_COUNT);
		return cellIndex;
	}

	public boolean isGameOver() {
		int beesAlive=0;
		for(Bee bee1:bees){
			if(bee1.getHp()>0) {
				beesAlive++;
			}else {
				if(bee1.getName().equals(NAME_QUEEN)) return true;
			}
		}
		return beesAlive <= 0;
	}
	Bee getRandomBeeForHit(){
		int random=new Random().nextInt(bees.size());
		Bee bee = bees.get(random);
		if(bee.getHp()>0) return bee;
		else return getRandomBeeForHit();
	}

	public void hitRandomBee() {
		Bee bee=getRandomBeeForHit();
		Log.d(TAG, "hitRandomBee: "+bee.getName()+" hp="+bee.getHp());
		assertNotNull(bee);
		bee.wasHit();
		Log.d(TAG, "hitRandomBee: after hit hp="+bee.getHp());
		if(bee.getHp()==0){
			if(isGameOver()) {
				gameInterface.gameOver();
			}
		}
		gameInterface.beeIsHit(bee);
	}

	ArrayList<Bee> getBees() {
		return bees;
	}

	public void start() {
		gameInterface.spawnBees(getBees());
		if(isGameOver()) gameInterface.gameOver();
	}
}
