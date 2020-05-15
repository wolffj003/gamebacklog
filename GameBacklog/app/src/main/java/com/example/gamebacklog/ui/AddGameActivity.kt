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

        if (
            tiletGameDateDayText.isBlank() or
            (tiletGameDateDayText.toInt() > 31)
        ) {
            toastError(getString(R.string.enterDay))
            return
        }

        if (
            tiletGameDateMonthText.isBlank() or
            (tiletGameDateDayText.toInt() > 12)
        ) {
            toastError(getString(R.string.enterMonth))
            return
        }

        if (tiletGameDateYearText.isBlank()) {
            toastError(getString(R.string.enterYear))
            return
        }

        if (!validDate()) {  // TODO date checking (Zie code beneden)
            toastError(getString(R.string.enterValidDate))
        }

        val gameReleaseDateString =
            "$tiletGameDateDayText-$tiletGameDateMonthText-$tiletGameDateYearText"

        val game = Game(
            tiletGameTitleText,
            tiletGamePlatformText,
            gameReleaseDateString
        )

        val resultIntent = Intent()

        resultIntent.putExtra(EXTRA_GAME, game)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun toastError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun validDate(date: Date) : Boolean {  // Implement
        return true
    }

    private fun strToDate(date: String) : Date? {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        var d: Date? = null
        try {
            d = formatter.parse(date)
        } catch (e: ParseException) {  // Parseexception gebeurt miss al als date invalid is (dan bedoel ik invalid als schrikkeljaar of dag die niet bestaat.)
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return d
    }
}
