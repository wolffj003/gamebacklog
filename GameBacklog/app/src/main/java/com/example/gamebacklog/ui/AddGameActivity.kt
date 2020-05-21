package com.example.gamebacklog.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_add_game.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val EXTRA_GAME = "EXTRA_GAME"

class AddGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(tbAddGame)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        fabSaveGame.setOnClickListener{ onSaveGameClick() }
    }

    private fun onSaveGameClick() {
        val tiletGameTitleText = tiletGameTitle.text.toString()
        val tiletGamePlatformText = tiletGamePlatform.text.toString()
        val tiletGameDateDayText = tiletGameDateDay.text.toString()
        val tiletGameDateMonthText = tiletGameDateMonth.text.toString()
        val tiletGameDateYearText = tiletGameDateYear.text.toString()

        if (tiletGameTitleText.isBlank()) {
            toastError(getString(R.string.enterTitle))
            return
        }

        if (tiletGamePlatformText.isBlank()) {
            toastError(getString(R.string.enterPlatform))
            return
        }

        if (tiletGameDateDayText.isBlank()) {
            toastError(getString(R.string.enterDay))
            return
        }

        if (tiletGameDateMonthText.isBlank()) {
            toastError(getString(R.string.enterMonth))
            return
        }

        if (tiletGameDateYearText.isBlank()) {
            toastError(getString(R.string.enterYear))
            return
        }

        val date = strToDate("$tiletGameDateDayText-$tiletGameDateMonthText-$tiletGameDateYearText")
        date?.let {

            val game = Game(
                tiletGameTitleText,
                tiletGamePlatformText,
                date
            )
            val resultIntent = Intent()

            resultIntent.putExtra(EXTRA_GAME, game)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
            return
        }
        toastError(getString(R.string.enterValidDate))
        return
    }

    private fun toastError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun strToDate(dateStr: String) : Date? {
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        formatter.isLenient = false
        var date: Date? = null
        try {
            date = formatter.parse(dateStr)
        } catch (e: ParseException) {
            return date
        }

        return date
    }
}
