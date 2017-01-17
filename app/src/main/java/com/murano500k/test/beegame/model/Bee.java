package com.murano500k.test.beegame.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by artem on 1/16/17.
 */

public class Bee implements Parcelable {
	private String name;
	private int maxHp, maxCount,oneHitDamage, hp, index;

	protected Bee(Parcel in) {
		this.name = in.readString();
		this.maxHp=in.readInt();
		this.maxCount=in.readInt();
		this.oneHitDamage=in.readInt();
		this.hp=in.readInt();
		this.index=in.readInt();
	}

	@Override
	public int describeContents() {
		return 123;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(maxHp);
		dest.writeInt(maxCount);
		dest.writeInt(oneHitDamage);
		dest.writeInt(maxHp);
		dest.writeInt(hp);
		dest.writeInt(index);
	}

	public static final Parcelable.Creator<Bee> CREATOR = new Parcelable.Creator<Bee>() {
		@Override
		public Bee createFromParcel(Parcel in) {
			return new Bee(in);
		}

		@Override
		public Bee[] newArray(int size) {
			return new Bee[size];
		}
	};
	public Bee(String name, int maxHp, int maxCount, int oneHitDamage, int index) {
		this.name = name;
		this.maxHp=maxHp;
		this.maxCount=maxCount;
		this.oneHitDamage=oneHitDamage;
		this.hp=maxHp;
		this.index=index;
	}


	public int getIndex() {
		return index;
	}

	public int getHp() {
		Log.d(TAG, "getHp: "+hp);
		return hp;
	}


	public String getName() {
		Log.d(TAG, "getName: "+name);

		return name;
	}


	public void wasHit() {
		hp-=oneHitDamage;
		if(hp<0)hp=0;
	}
}
