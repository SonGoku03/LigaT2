package com.emontec.ligat.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emontec.ligat.Modelo.Equipos;
import com.emontec.ligat.Modelo.Jornadas;
import com.emontec.ligat.R;
import com.emontec.ligat.Tu_liga;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapter_Jornadas extends RecyclerView.Adapter<ListAdapter_Jornadas.ViewHolder>  {
    private Context mContextCol;
    private ArrayList<Jornadas> mDatasetcol;
    String codigo;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView jornada,Nombre_local, gol_local,gol_visita, nombre_visita,imagen,horario,campo;
        ImageView logo_local,logo_visita;

        Context context;
        private String codigo;
        RelativeLayout cardw;

        public Transition transition;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            jornada = (TextView) v.findViewById(R.id.Jonadas);
            Nombre_local = (TextView) v.findViewById(R.id.Nombre_local);
            logo_local = (ImageView) v.findViewById(R.id.logo_local);
            gol_local = (TextView) v.findViewById(R.id.gol_local);
            gol_visita = (TextView) v.findViewById(R.id.gol_visita);
            logo_visita = (ImageView) v.findViewById(R.id.logo_visita);
            nombre_visita = (TextView) v.findViewById(R.id.Nombre_visita);
            horario = (TextView) v.findViewById(R.id.horario);
            campo = (TextView) v.findViewById(R.id.campo);


        }
        void setOnClickListener()    {
//          cardw.setOnClickListener(this);
    }



      @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rlContainer:

                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    Intent bundle = new Intent(context.getApplicationContext(), Tu_liga.class);
                    //  bundle.putExtra("codigo",codigo);
                    /*
                    bundle.putExtra("category_name", (String) NombreCate.getText());
                    bundle.putExtra("virtuemart_category_id", (String) idcategory.getText());

                    SharedPreferences misPreferencias = context.getSharedPreferences("Familia",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =  misPreferencias.edit();
                    String familia = idcategory.getText().toString();
                    editor.putString("familia",familia);
                    editor.commit();
                    */

                    context.startActivity(bundle);
                    break;
            }
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter_Jornadas(Context contextcol, ArrayList<Jornadas> myDatasetco) {
        mDatasetcol = myDatasetco;
        mContextCol= contextcol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_jornadas, parent, false);


        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(mContextCol).load(mDatasetcol.get(position).getLogo_local()).into(holder.logo_local);
        Glide.with(mContextCol).load(mDatasetcol.get(position).getLogo_visita()).into(holder.logo_visita);
        holder.jornada.setText(mDatasetcol.get(position).getJornada());
        holder.Nombre_local.setText(mDatasetcol.get(position).getNombre_local());
        holder.gol_local.setText(mDatasetcol.get(position).getGol_local());
        holder.gol_visita.setText(mDatasetcol.get(position).getGol_visita());
        holder.nombre_visita.setText(mDatasetcol.get(position).getNombre_visita());
        holder.horario.setText(mDatasetcol.get(position).getHorario());
        holder.campo.setText(mDatasetcol.get(position).getCampo());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetcol.size();
    }
}
