package com.emontec.ligat.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emontec.ligat.Modelo.Jornadas;
import com.emontec.ligat.R;
import com.emontec.ligat.Tu_liga;
import com.emontec.ligat.fragmentos.Fr_Jornadas;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapter_Jornadas_Select extends RecyclerView.Adapter<ListAdapter_Jornadas_Select.ViewHolder>  {
    private Context mContextCol;
    private ArrayList<Jornadas> mDatasetcol;
    String codigo;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView jornada;
        ImageView logo_local,logo_visita;

        Context context;
        private String codigo;
        RelativeLayout cardw;


        public Transition transition;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            jornada = (TextView) v.findViewById(R.id.jonada_id);
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

                    //Toast.makeText(context, ""+ jornada.getText().toString(), Toast.LENGTH_LONG).show();
                    Fr_Jornadas nuevosFragment= new Fr_Jornadas();
                    FragmentManager fragmentManager1 =((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction().addToBackStack(null);
                    fragmentTransaction.replace(R.id.tab_content,nuevosFragment,null);
                    fragmentTransaction.commit();

                    SharedPreferences misPreferencias = context.getSharedPreferences("Jornada",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =  misPreferencias.edit();
                    String id_jornada = jornada.getText().toString();
                    editor.putString("id_jornada",id_jornada);
                    editor.commit();
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


                    break;
            }
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter_Jornadas_Select(Context contextcol, ArrayList<Jornadas> myDatasetco) {
        mDatasetcol = myDatasetco;
        mContextCol= contextcol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_jornadas_select, parent, false);


        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.jornada.setText(mDatasetcol.get(position).getJornada());
        holder.setOnClickListener();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetcol.size();
    }
}
