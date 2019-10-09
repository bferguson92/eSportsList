package com.example.esportslist.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.esportslist.model.eAthlete

class eAthleteDatabaseHelper(
    private val context: Context,
    private val cursorFactory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, DATABASE_NAME, cursorFactory, DATABASE_VERISON) {

    override fun onCreate(database: SQLiteDatabase) {
        val createTable =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_HANDLE TEXT, " +
                    "$COLUMN_NAME TEXT, $COLUMN_GAME TEXT, $COLUMN_TEAM TEXT, $COLUMN_NATIONALITY TEXT)"

        database.execSQL(createTable)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(database)
    }

    fun insert(name: String, game: String, handle: String, team: String, nation: String){
        val values = ContentValues()

        values.apply {
            put(COLUMN_NAME, name)
            put(COLUMN_GAME, game)
            put(COLUMN_HANDLE, handle)
            put(COLUMN_TEAM, team)
            put(COLUMN_NATIONALITY, nation)
        }

        val database = this.writableDatabase
        database.insert(TABLE_NAME, null, values)
        database.close()
    }

    fun getEAthletes(): Cursor {
        val database = this.readableDatabase
        return database.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun removeEAthlete(eathlete: eAthlete){
        val database = this.writableDatabase
        val id: Array<out String> = arrayOf(eathlete.id.toString())

        database.delete(TABLE_NAME, "$COLUMN_ID=?", id)
    }

    fun updateInfo(eathlete: eAthlete){
        val database = this.writableDatabase
        val values = ContentValues()
        val id: Array<out String> = arrayOf(eathlete.id.toString())
        Log.d("TAG_EATHLETE", "${eathlete.handle} | ${eathlete.game}")

        values.apply {
            put(COLUMN_NAME, eathlete.name)
            put(COLUMN_GAME, eathlete.game)
            put(COLUMN_HANDLE, eathlete.handle)
            put(COLUMN_TEAM, eathlete.team)
            put(COLUMN_NATIONALITY, eathlete.nationality)
        }

        Log.d("TAG_EATHLETE", "$values")

        database.update(TABLE_NAME, values, "$COLUMN_ID=?", id)
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