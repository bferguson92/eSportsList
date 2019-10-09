package com.example.esportslist.view

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esportslist.R
import com.example.esportslist.adapter.eAthleteAdapter
import com.example.esportslist.database.eAthleteDatabaseHelper
import com.example.esportslist.model.eAthlete
import com.example.esportslist.util.ErrorLogger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), eAthleteAdapter.eAthleteDelegate {

    private lateinit var dbHelper: eAthleteDatabaseHelper
    private val eAthleteList = mutableListOf<eAthlete>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd.setOnClickListener {
            startActivity(
                Intent(this, NewEAthlete::class.java)
            )
        }

        dbHelper = eAthleteDatabaseHelper(this, null)
        populateList()

        rvDisplayEAthletes.adapter = eAthleteAdapter(eAthleteList, this)
        rvDisplayEAthletes.layoutManager = LinearLayoutManager(this)


    }

    private fun populateList(){
        val dbCursor = dbHelper.getEAthletes()
        dbCursor.moveToFirst()

        try {
            dbCursor.let { myCursor->
                addToList(dbCursor)
                while (dbCursor.moveToNext()){
                    addToList(dbCursor)
                }

                myCursor.close()
            }
        } catch (throwable: Throwable){
            ErrorLogger.LogError(throwable)
        }
    }

    private fun addToList(dbCursor: Cursor){
        val id = dbCursor.getInt(dbCursor.getColumnIndex(eAthleteDatabaseHelper.COLUMN_ID))
        val name = dbCursor.getString(dbCursor.getColumnIndex(eAthleteDatabaseHelper.COLUMN_NAME))
        val game = dbCursor.getString(dbCursor.getColumnIndex(eAthleteDatabaseHelper.COLUMN_GAME))
        val handle = dbCursor.getString(dbCursor.getColumnIndex(eAthleteDatabaseHelper.COLUMN_HANDLE))
        val team = dbCursor.getString(dbCursor.getColumnIndex(eAthleteDatabaseHelper.COLUMN_TEAM))
        val nation = dbCursor.getString(dbCursor.getColumnIndex(eAthleteDatabaseHelper.COLUMN_NATIONALITY))

        val athlete = eAthlete(id, name, handle, game, team, nation)

        eAthleteList.add(athlete)
    }

    override fun getMoreInfo(eathlete: eAthlete) {
        val intent = Intent(this, eAthleteInfo::class.java)
        intent.putExtra("eAthlete", eathlete)
        startActivity(intent)
    }

    override fun addToFavorites(holder: eAthleteAdapter.CustomViewHolder) {
        holder.apply {
            image.setImageResource(R.drawable.ic_favorite_checked)
        }
    }
}
