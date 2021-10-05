package com.example.homework.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homework.R
import com.example.homework.modelFromAcademy.Actor


class ActorListAdapter : RecyclerView.Adapter<ActorViewHolder>() {
    private var movieActors: List<Actor> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(movieActors[position])
    }

    override fun getItemCount(): Int {
        return movieActors.size
    }
    fun bindActors(actors: List<Actor>) {
        movieActors = actors
    }

}
class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor)
    private val actorName: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        Glide.with(context)
            .load(actor.imageUrl)
            .into(avatar)
        actorName.text = actor.name
    }
}
private val RecyclerView.ViewHolder.context
    get() = this.itemView.context