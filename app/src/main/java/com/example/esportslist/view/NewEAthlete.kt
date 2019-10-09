package com.example.esportslist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.esportslist.R
import com.example.esportslist.adapter.eAthleteAdapter
import com.example.esportslist.database.eAthleteDatabaseHelper
import com.example.esportslist.model.eAthlete
import com.example.esportslist.util.ErrorLogger
import kotlinx.android.synthetic.main.activity_new_eathlete.*

class NewEAthlete : AppCompatActivity() {
    lateinit var dbHealper: eAthleteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_eathlete)

        dbHealper = eAthleteDatabaseHelper(this, null)

        btnCreate.setOnClickListener {
            val name = etName.text.toString()
            val game = etGame.text.toString()
            val handle = etHandle.text.toString()
            val team = etTeam.text.toString()
            val nation = etNation.text.toString()

            val athlete = eAthlete(name, game, handle, team, nation)

            try {
                dbHealper.insert(athlete)
            } catch (throwable: Throwable){
                ErrorLogger.LogError(throwable)
            }

            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}
