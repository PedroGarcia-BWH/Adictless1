package com.example.adictless1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*public abstract class AdaptarMensaje extends RecyclerView.Adapter<Mensaje>{

    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context c;

    public AdaptarMensaje(Context c) {
        this.c = c;
    }

    public void addMensaje(MensajeRecibir m){
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.mensajecardview,parent,false);
        HolderMensaje h = new HolderMensaje(v);
        return h;
    }


    //@Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(listMensaje.get(position).getNombre());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());

        if(listMensaje.get(position).getFotoPerfil().isEmpty()){
            holder.getFotoMensajePerfil().setImageResource(R.mipmap.ic_launcher);
        }else{
            //Glide.with(c).load(listMensaje.get(position).getFotoPerfil()).into(holder.getFotoMensajePerfil());
        }
        Long codigoHora = listMensaje.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");//a pm o am
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}*/
