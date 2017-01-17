package com.murano500k.test.beegame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.murano500k.test.beegame.model.Bee;

import java.util.ArrayList;

import static com.murano500k.test.beegame.GameLogic.CELL_COUNT;
import static com.murano500k.test.beegame.GameLogic.COLUMN_COUNT;
import static com.murano500k.test.beegame.GameLogic.KEY_BEES_LIST;

public class MainActivity extends Activity  implements GameInterface{
	private static final String TAG = "MainActivity";
	GridLayout gridLayout;
	Button buttonHit;
	GameLogic gameLogic;
	SparseArray<TextView> mapViewId;
	private int orangeBeeColor, deadBeeColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridLayout=(GridLayout) findViewById(R.id.table);
		buttonHit=(Button) findViewById(R.id.buttonHit);
		orangeBeeColor =getColor(android.R.color.holo_orange_light);
		deadBeeColor =getColor(R.color.cardview_dark_background);
		gridLayout = (GridLayout) findViewById(R.id.table);
		if(savedInstanceState!=null
				&& savedInstanceState.containsKey(KEY_BEES_LIST)) {
			ArrayList<Bee> bees = savedInstanceState.getParcelableArrayList(KEY_BEES_LIST);
			if (bees != null && bees.size() > 0) {
				initGame(bees);
				return;
			}
		}
		initGame(null);
	}
		private void initGame(ArrayList<Bee> list){
			gridLayout.removeAllViews();
			buttonHit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d(TAG, "onClick: ");
					gameLogic.hitRandomBee();
				}
			});
			if(list!=null) gameLogic=new GameLogic(this,list);
			else gameLogic=new GameLogic(this);
			gameLogic.start();
		}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if(gameLogic.getBees()!=null)outState.putParcelableArrayList(KEY_BEES_LIST,
				gameLogic.getBees());
		super.onSaveInstanceState(outState);
	}

	@Override
	public void spawnBees(ArrayList<Bee> bees) {
		Log.d(TAG, "spawnBees");
		int total = CELL_COUNT;
		int column = COLUMN_COUNT;
		int row = total / column;
		gridLayout.setColumnCount(column);
		gridLayout.setRowCount(row + 1);
		Log.d(TAG, "spawnBees: total cells"+total);
		Log.d(TAG, "spawnBees: rows "+row);
		Log.d(TAG, "spawnBees: columns"+column);


		mapViewId=new SparseArray<>();
		for (int i = 0, c = 0, r = 0; i < total; i++, c++) {
			if (c == column) {
				c = 0;
				r++;
			}
			TextView view=(TextView) getLayoutInflater().inflate(
					R.layout.bee_view,
					gridLayout,
					false);
			for(Bee bee: bees) {
				if(bee.getIndex()==i) {
					Log.w(TAG, "spawnBee at "+i+" cell");
					view=drawBeeView(bee, view);
					mapViewId.put(i,view);
					break;
				}
			}
			GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
			GridLayout.Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, 1);
			GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
					rowSpan, colspan);
			gridLayout.addView(view, i, gridParam);
		}
	}


	private TextView drawBeeView(Bee bee,TextView view) {
		if(bee.getHp()>0){
			view.setBackgroundColor(orangeBeeColor);
			view.setText(
					bee.getName()+"\n"+	bee.getHp());
		}
		else {
			view.setBackgroundColor(deadBeeColor);
			view.setText(
					bee.getName()+"\nDEAD"
			);
		}
		return view;
	}
	@Override
	public void beeIsHit(Bee bee) {
		Log.d(TAG, "beeIsHit: "+bee);
		drawBeeView(bee, mapViewId.get(bee.getIndex()));
	}


	@Override
	public void gameOver() {
		Log.d(TAG, "gameOver: ");
		Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
		buttonHit.setText("Restart");
		buttonHit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				initGame(null);
			}
		});
	}
}
