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
import com.emontec.ligat.Modelo.M_goleador;
import com.emontec.ligat.R;
import com.emontec.ligat.Tu_liga;
import com.emontec.ligat.fragmentos.Goleador;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapter_Goleo extends RecyclerView.Adapter<ListAdapter_Goleo.ViewHolder>  {
    private Context mContextCol;
    private ArrayList<M_goleador> mDatasetcol;
    String codigo;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView Nombre,id_goleador,id_equipo,Goles,posicion;
        ImageView logo;
        Context context;
        private String codigo;
        RelativeLayout cardw;

        public Transition transition;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            Nombre = (TextView) v.findViewById(R.id.nombre_goleador);
            Goles = (TextView) v.findViewById(R.id.goles);
            id_goleador = (TextView) v.findViewById(R.id.id_goleador);
            id_equipo = (TextView) v.findViewById(R.id.id_equipo);
            posicion = (TextView) v.findViewById(R.id.posiicion_goleo);
            logo=(ImageView) v.findViewById(R.id.logo_goleador);

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
    public ListAdapter_Goleo(Context contextcol, ArrayList<M_goleador> myDatasetco) {
        mDatasetcol = myDatasetco;
        mContextCol= contextcol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_goleador, parent, false);


        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(mContextCol).load(mDatasetcol.get(position).getLogo_visita()).into(holder.logo);
        holder.Nombre.setText(mDatasetcol.get(position).getNombre());
        holder.id_equipo.setText(mDatasetcol.get(position).getId_equipo());
        int count =1;
        int pos= count+position;
        String posicion = Integer.toString(pos);
        holder.posicion.setText(posicion);
        holder.id_goleador.setText(mDatasetcol.get(position).getId_jugador());
        holder.Goles.setText(mDatasetcol.get(position).getGoles());

        holder.setOnClickListener();


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetcol.size();
    }
}
