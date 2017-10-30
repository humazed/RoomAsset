package com.huma.room_for_asset

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * User: YourPc
 * Date: 10/20/2017
 */
inline val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)
