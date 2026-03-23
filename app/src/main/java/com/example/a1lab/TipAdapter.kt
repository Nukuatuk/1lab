package com.example.a1lab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TipAdapter(private val tips: List<Tip>) :
    RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

    class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardTip)
        val dayText: TextView = itemView.findViewById(R.id.tvDay)
        val titleText: TextView = itemView.findViewById(R.id.tvTitle)
        val descriptionText: TextView = itemView.findViewById(R.id.tvDescription)
        val imageView: ImageView = itemView.findViewById(R.id.ivTip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tips[position]
        val context = holder.itemView.context

        holder.dayText.text = context.getString(R.string.day_number, tip.day)
        holder.titleText.setText(tip.titleResId)
        holder.descriptionText.setText(tip.shortDescriptionResId)
        holder.imageView.setImageResource(tip.imageResId)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("day", tip.day)
            intent.putExtra("titleResId", tip.titleResId)
            intent.putExtra("fullDescriptionResId", tip.fullDescriptionResId)
            intent.putExtra("imageResId", tip.imageResId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = tips.size
}