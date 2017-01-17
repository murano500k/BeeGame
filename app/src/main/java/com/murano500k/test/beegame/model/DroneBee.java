package com.murano500k.test.beegame.model;

import android.os.Parcel;

import static com.murano500k.test.beegame.GameLogic.DAMAGE_PER_HIT_DRONE;
import static com.murano500k.test.beegame.GameLogic.MAX_COUNT_DRONE;
import static com.murano500k.test.beegame.GameLogic.MAX_HP_DRONE;
import static com.murano500k.test.beegame.GameLogic.NAME_DRONE;

/**
 * Created by artem on 1/16/17.
 */

public class DroneBee extends Bee {


	public DroneBee(int index) {
		super(NAME_DRONE, MAX_HP_DRONE, MAX_COUNT_DRONE, DAMAGE_PER_HIT_DRONE, index);
	}




	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(getIndex());
		parcel.writeInt(getHp());
		parcel.writeString(getName());
	}
}
