package com.valkyrie.nabeshimamac.quitaclient

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by NabeshimaMAC on 2016/12/24.
 */

data class User(val id: String,
           val name: String,
           val profileImageURI: String) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = source.run {
                User(readString(), readString(), readString())
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(id)
            writeString(name)
            writeString(profileImageURI)
        }
    }

    public inline fun <T, R> T.run(block: T.() -> R): R = block()
}