package com.example.gamebacklog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.AddGameActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_main.*

const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var gameBacklog = arrayListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tbMain)

        initViews()
    }

    private fun initViews() {  // Implement
        fabAddGame.setOnClickListener { startAddActivity() }  // Activity maken en launchen

        viewManager = LinearLayoutManager(this)
        viewAdapter = GameAdapter(gameBacklog)

        recyclerView = findViewById<RecyclerView>(R.id.rvBacklog).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun startAddActivity() {  // Load add activity n stuff
        val intent = Intent(this, AddGameActivity::class.java)
        startActivityForResult(intent,  ADD_GAME_REQUEST_CODE)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        deleteBacklog()

        return when (item.itemId) {
            R.id.menuDelete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteBacklog() {  // Implement

    }
}
