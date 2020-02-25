package com.emontec.ligat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.emontec.ligat.Modelo.ListaFamilias;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapterFamiliasAll extends RecyclerView.Adapter<ListAdapterFamiliasAll.ViewHolder>  {
    private Context mContextCol;
    private ArrayList<ListaFamilias> mDatasetcol;
    String codigo;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView NombreCate,url,jornada,tipo,MediaId,TituloImagen,CategoriaCole;

        Context context;
        private String codigo;
        RelativeLayout cardw;

        public Transition transition;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            NombreCate = (TextView) v.findViewById(R.id.tvTitle);
            jornada = (TextView) v.findViewById(R.id.tvJornada);
          //  tipo = (TextView) v.findViewById(R.id.tvTipo);
            cardw = (RelativeLayout) v.findViewById(R.id.rlContainer);


        }
        void setOnClickListener()    {
          cardw.setOnClickListener(this);
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
    public ListAdapterFamiliasAll(Context contextcol, ArrayList<ListaFamilias> myDatasetco) {
        mDatasetcol = myDatasetco;
        mContextCol= contextcol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_ligas, parent, false);


        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.NombreCate.setText(mDatasetcol.get(position).getNombre());
        holder.jornada.setText(mDatasetcol.get(position).getTipo());
        holder.setOnClickListener();


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetcol.size();
    }
}
