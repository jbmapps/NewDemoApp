package com.tiranga.acharyaattendence.utils

import com.preference.PowerPreference

const val LOGGED_IN = "LOGGED_IN"

fun writeString(key: String, value: String)  {
    PowerPreference.getDefaultFile().putString(key, value)
}

fun readString(key: String, value: String = ""): String {
    return PowerPreference.getDefaultFile().getString(key, value)
}

fun readBool(key: String, value: Boolean = false): Boolean {
    return PowerPreference.getDefaultFile().getBoolean(key, value)
}

fun writeBool(key: String, value: Boolean) {
    PowerPreference.getDefaultFile().putBoolean(key, value)
}

fun readInt(key: String, value: Int = 0): Int {
    return PowerPreference.getDefaultFile().getInt(key, value)
}

fun writeInt(key: String, value: Int) {
    PowerPreference.getDefaultFile().putInt(key, value)
}