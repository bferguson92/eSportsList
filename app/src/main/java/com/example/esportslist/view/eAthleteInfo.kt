package com.example.esportslist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.esportslist.R
import com.example.esportslist.database.eAthleteDatabaseHelper
import com.example.esportslist.model.eAthlete
import kotlinx.android.synthetic.main.activity_e_athlete_info.*

class eAthleteInfo : AppCompatActivity() {

    private lateinit var dbHelper: eAthleteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_athlete_info)

        dbHelper = eAthleteDatabaseHelper(this, null)
        val eathlete = intent.getParcelableExtra<eAthlete>("eAthlete")


        btnEdit.setOnClickListener {
            val intent = Intent(this, EditEAthlete::class.java)
            intent.putExtra("eathlete", eathlete)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            if(eathlete != null) {
                dbHelper.removeEAthlete(eathlete)
            }

            startActivity(Intent(this, MainActivity::class.java))
        }

        setUpInfo()
    }

    private fun setUpInfo() {
        val eathlete = intent.getParcelableExtra<eAthlete>("eAthlete")

        tvHandle.text = eathlete.handle
        tvName.append(eathlete.name)
        tvGame.append(eathlete.game)
        tvNation.append(eathlete.nationality)
        tvTeam.append(eathlete.team)
    }

}
