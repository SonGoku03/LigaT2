package com.emontec.ligat.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emontec.ligat.Modelo.Equipos;
import com.emontec.ligat.R;
import com.emontec.ligat.Tu_liga;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapter_Equipos extends RecyclerView.Adapter<ListAdapter_Equipos.ViewHolder>  {
    private Context mContextCol;
    private ArrayList<Equipos> mDatasetcol;
    String codigo;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView Nombre,posicion,puntos,diferencia;

        Context context;
        private String codigo;
        RelativeLayout cardw;

        public Transition transition;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            Nombre = (TextView) v.findViewById(R.id.tvNombre);
            posicion = (TextView) v.findViewById(R.id.tvPosicion);
            puntos = (TextView) v.findViewById(R.id.tvPuntos);
            diferencia = (TextView) v.findViewById(R.id.tvDiferencia);


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
    public ListAdapter_Equipos(Context contextcol, ArrayList<Equipos> myDatasetco) {
        mDatasetcol = myDatasetco;
        mContextCol= contextcol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_equipos, parent, false);


        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.Nombre.setText(mDatasetcol.get(position).getNombre());
        holder.puntos.setText(mDatasetcol.get(position).getPuntos());
        int count =1;
        int pos= count+position;
        String posicion = Integer.toString(pos);
        holder.posicion.setText(posicion);
        holder.diferencia.setText(mDatasetcol.get(position).getDiferencia());
        holder.setOnClickListener();


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetcol.size();
    }
}
