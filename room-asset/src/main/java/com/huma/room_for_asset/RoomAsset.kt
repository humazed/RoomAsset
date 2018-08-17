package com.huma.room_for_asset

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

/**
 * User: YourPc
 * Date: 7/20/2017
 */
class RoomAsset {

    companion object {
        private val TAG = RoomAsset::class.java.simpleName

        /**
         * Creates a RoomDatabase.Builder for a  persistent database. Once a database is built, you
         * should keep a reference to it and re-use it.
         *
         * @param context          The context for the database. This is usually the Application context.
         * @param klass            The abstract class which is annotated with @[Database] and extends
         *                         [RoomDatabase].
         * @param name             The name of the database file.
         * @param storageDirectory To store the database file upon creation; caller must ensure that
         *                         the specified absolute path is available and can be written to;
         *                         not needed if the database is the default location :assets/database.
         * @param factory          to use for creating cursor objects, or null for the default.
         * @return A [RoomDatabase.Builder<T>] which you can use to create the database.
         */
        @JvmStatic
        @JvmOverloads
        fun <T : RoomDatabase> databaseBuilder(
                context: Context,
                klass: Class<T>,
                name: String,
                storageDirectory: String? = null,
                factory: SQLiteDatabase.CursorFactory? = null)
                : RoomDatabase.Builder<T> {

            openDb(context, name, storageDirectory, factory)

            return Room.databaseBuilder(context, klass, name)
                    .addMigrations(object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            Log.w(TAG, "migrate from version 1 to 2 ")
                        }
                    })
        }

        /**
         * Open the database and copy it to data folder using [SQLiteAssetHelper]
         */
        private fun openDb(context: Context, name: String, storageDirectory: String?, factory: SQLiteDatabase.CursorFactory?) {
            val instantiated = "instantiated"
            val sharedPref = context.defaultSharedPreferences

            if (!sharedPref.getBoolean(instantiated, false)) {
                SQLiteAssetHelper(context, name, storageDirectory, factory, 1).writableDatabase.close()
                sharedPref.edit().putBoolean(instantiated, true).apply()
                Log.w(TAG, "RoomAsset is ready ")
            }
        }
    }
}