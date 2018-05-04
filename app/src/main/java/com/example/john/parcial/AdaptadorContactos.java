package com.example.john.parcial;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//ALIMENTA MI ARCHIVO item_list_contactos.xml

public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ViewHolderContactos> {

    ArrayList<Contacto> listaContactos;
    Context ctx;

    public AdaptadorContactos(ArrayList<Contacto> listaContactos, Context ctx) {
        this.listaContactos = listaContactos;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolderContactos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contactos, null, false);
        return new ViewHolderContactos(view, ctx, listaContactos);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContactos holder, int position) {
        holder.etiNombre.setText(listaContactos.get(position).getNombre());
        holder.etiApellido.setText(listaContactos.get(position).getApellido());
        holder.foto.setImageResource(listaContactos.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }


    public class ViewHolderContactos extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView etiNombre, etiApellido;
        ImageView foto;
        ArrayList<Contacto> contactos;
        Context ctx;

        public ViewHolderContactos(View itemView, Context ctx, ArrayList<Contacto> contactos) {
            super(itemView);
            this.contactos = contactos;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            etiNombre = itemView.findViewById(R.id.idNombre);
            etiApellido = itemView.findViewById(R.id.idApellido);
            foto = itemView.findViewById(R.id.idImagen);
        }

        @Override
        public void onClick(View v) {
            int posicion = getAdapterPosition();
            Contacto contacto = this.contactos.get(posicion);
            Intent intent = new Intent(this.ctx, ContactDtails.class);
            intent.putExtra("img", contacto.getFoto());
            intent.putExtra("Name", contacto.getNombre());
            intent.putExtra("LastName", contacto.getApellido());
            intent.putExtra("Email", contacto.getCorreo());
            intent.putExtra("Address", contacto.getDireccion());
            intent.putExtra("Phone", contacto.getTelefono());
            this.ctx.startActivity(intent);
        }
    }

    public void setFilter(ArrayList<Contacto> newList) {

        listaContactos = new ArrayList<>();
        listaContactos.addAll(newList);
        notifyDataSetChanged();

    }

}
