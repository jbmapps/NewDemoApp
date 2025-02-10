package com.tiranga.acharyaattendence.utils

import com.preference.PowerPreference

const val LOGGED_IN = "LOGGED_IN"

fun readString(key: String, value: String = ""): String {
    return PowerPreference.getDefaultFile().getString(key, value)
}

fun readBool(key: String, value: Boolean = false): Boolean {
    return PowerPreference.getDefaultFile().getBoolean(key, value)
}

fun readInt(key: String, value: Int = 0): Int {
    return PowerPreference.getDefaultFile().getInt(key, value)
}