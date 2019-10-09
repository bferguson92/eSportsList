package com.example.esportslist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.esportslist.R
import com.example.esportslist.database.eAthleteDatabaseHelper
import com.example.esportslist.model.eAthlete
import kotlinx.android.synthetic.main.activity_edit_eathlete.*
import kotlinx.android.synthetic.main.activity_new_eathlete.*
import kotlinx.android.synthetic.main.activity_new_eathlete.etHandle

class EditEAthlete : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_eathlete)

        val dbhelper = eAthleteDatabaseHelper(this, null)

        btnUpdate.setOnClickListener {
            val eathlete = intent.getParcelableExtra<eAthlete>("eathlete")

            val passEAthlte = eAthlete(
                eathlete.id,
                update_name.text.toString(),
                update_handle.text.toString(),
                update_game.text.toString(),
                update_team.text.toString(),
                update_nation.text.toString()
            )

            dbhelper.updateInfo(passEAthlte)
            startActivity(Intent(this, MainActivity::class.java))
        }

        populateDisplay()
    }

    private fun populateDisplay() {
        val eathlete = intent.getParcelableExtra<eAthlete>("eathlete")
        Log.d("TAG_EATHLETE", "${eathlete.handle} | ${eathlete.game}")

        update_handle.setText(eathlete.handle)
        update_name.setText(eathlete.name)
        update_game.setText(eathlete.game)
        update_nation.setText(eathlete.nationality)
        update_team.setText(eathlete.team)
    }

}
