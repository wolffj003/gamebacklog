package com.example.gamebacklog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.item_game.view.*
import kotlin.collections.ArrayList

class GameAdapter(private val gameBacklog: ArrayList<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolderCard>() {

    class ViewHolderCard(cardViewText: View) : RecyclerView.ViewHolder(cardViewText) {
        fun bind(game: Game) {
            itemView.gameTitle.text = game.title
            itemView.gamePlatform.text = game.platform
            itemView.gameReleaseDate.text = game.releaseDate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCard {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return ViewHolderCard(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolderCard, position: Int) {
        holder.bind(gameBacklog[position])
    }

    override fun getItemCount(): Int {
        return gameBacklog.size
    }
}
