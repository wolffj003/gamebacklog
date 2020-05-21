package com.example.gamebacklog.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_main.*

const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mainActivityViewModel: MainActivityViewModel

    var sortedBacklog = mutableListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tbMain)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fabAddGame.setOnClickListener { startAddActivity() }

        viewManager = LinearLayoutManager(this)
        viewAdapter = GameAdapter(sortedBacklog)

        recyclerView = findViewById<RecyclerView>(R.id.rvBacklog).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        createItemTouchHelper().attachToRecyclerView(rvBacklog)
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.backlog.observe(this, Observer { backlog ->
            var sortedBacklog = backlog.sortedWith(compareBy { it.releaseDate })
            sortedBacklog = sortedBacklog.reversed()
            this@MainActivity.sortedBacklog.clear()
            this@MainActivity.sortedBacklog.addAll(sortedBacklog)

            viewAdapter.notifyDataSetChanged()
        })
    }


    private fun startAddActivity() {  // Load add activity n stuff
        val intent = Intent(this, AddGameActivity::class.java)
        startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    val game = data!!.getParcelableExtra<Game>(EXTRA_GAME)
                    game?.let { mainActivityViewModel.insertGame(game) }
                }
            }
        }
    }


    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = sortedBacklog[position]

                mainActivityViewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
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

    private fun deleteBacklog() {
        mainActivityViewModel.deleteBacklog()
    }
}
