package com.example.esportslist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch

class eAthleteDatabaseHelper(
    private val context: Context,
    private val cursorFactory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, DATABASE_NAME, cursorFactory, DATABASE_VERISON) {

    override fun onCreate(database: SQLiteDatabase) {
        val createTable =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_HANDLE TEXT, " +
                    "$COLUMN_NAME TEXT, $COLUMN_GAME TEXT. $COLUMN_TEAM TEXT, $COLUMN_NATIONALITY TEXT)"

        database.execSQL(createTable)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(database)
    }

    companion object {
        val DATABASE_NAME = "eAthlete.db"
        val DATABASE_VERISON = 1
        val TABLE_NAME = "eAthletes"

        val COLUMN_ID = "ID"
        val COLUMN_HANDLE = "handle"
        val COLUMN_NAME = "name"
        val COLUMN_GAME = "game"
        val COLUMN_TEAM = "team"
        val COLUMN_NATIONALITY = "nationality"
    }

}