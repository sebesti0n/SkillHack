package com.example.skillhack

import android.os.Parcel
import android.os.Parcelable

data class questions(
    val name: String ?= null,
    val fullQuestion: String ?= null,
    val lastDate: String ?= null,
    val rewardAmt: String ?= null,
    val shortDiscriptionQuestion: String ?= null,
    val skill: String ?= null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(fullQuestion)
        parcel.writeString(lastDate)
        parcel.writeString(rewardAmt)
        parcel.writeString(shortDiscriptionQuestion)
        parcel.writeString(skill)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<questions> {
        override fun createFromParcel(parcel: Parcel): questions {
            return questions(parcel)
        }

        override fun newArray(size: Int): Array<questions?> {
            return arrayOfNulls(size)
        }
    }
}
