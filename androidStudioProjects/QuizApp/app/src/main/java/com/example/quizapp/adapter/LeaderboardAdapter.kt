package com.example.quizapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.util.ScoreEntry

class LeaderboardAdapter(private val entries: List<ScoreEntry>) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRank: TextView = view.findViewById(R.id.tvRank)
        val tvDifficulty: TextView = view.findViewById(R.id.tvDifficulty)
        val tvScorePercent: TextView = view.findViewById(R.id.tvScorePercent)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.tvRank.text = "#${position + 1}"
        holder.tvDifficulty.text = entry.difficulty
        holder.tvScorePercent.text = "${entry.percentage}%"
        holder.tvDate.text = entry.date

        val color = when {
            entry.percentage >= 80 -> Color.parseColor("#4CAF50")
            entry.percentage >= 50 -> Color.parseColor("#FF9800")
            else -> Color.parseColor("#F44336")
        }
        holder.tvScorePercent.setTextColor(color)
    }

    override fun getItemCount() = entries.size
}
