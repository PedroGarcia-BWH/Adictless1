package com.example.adictless1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_progress.view.*
import kotlinx.android.synthetic.main.mensajecardview.view.*

class MensajeAdapter(val mensajes:List<Mensaje>):RecyclerView.Adapter<MensajeAdapter.MensajeHolder>() {

    class MensajeHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(mensaje: Mensaje){
            view.nickname.text = mensaje.Nickname
            view.cuerpoMensaje.text = mensaje.CuerpoMensaje
            view.horaMensaje.text = mensaje.hora
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MensajeHolder(layoutInflater.inflate(R.layout.mensajecardview, parent, false))
    }

    override fun onBindViewHolder(holder: MensajeHolder, position: Int) {
        holder.render(mensajes[position])
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }
}