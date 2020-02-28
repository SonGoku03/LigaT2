package com.emontec.ligat.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.emontec.ligat.Adaptadores.ListAdapter_Goleo;
import com.emontec.ligat.MainActivity;
import com.emontec.ligat.Modelo.M_goleador;
import com.emontec.ligat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Goleador extends Fragment {

    View view;
    Switch aSwitch;
    LinearLayout LYEquipos;
    LinearLayout LYGrupos;
    private RecyclerView mRecyclerViewEquipos;
    private RecyclerView.Adapter mAdapterEquipos;
    private RecyclerView.LayoutManager mLayoutManagerca;
    private static final String TAG = MainActivity.class.getName();
    TextView des;
    LottieAnimationView fubol;
    CardView cardView;
    ///////////////// Declaracion de variables para el recicler view

    private ArrayList<M_goleador> myDatasetCa;

    private OnFragmentInteractionListener mListener;

    public Goleador() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_goleo, container, false);
        cardView= (CardView) view.findViewById(R.id.card_view);
        fubol = (LottieAnimationView) view.findViewById(R.id.futbol);
        fubol.setAnimation("futbol.json");
        fubol.playAnimation();
        mRecyclerViewEquipos = (RecyclerView) view.findViewById(R.id.my_recycler_view_goleo);
        mRecyclerViewEquipos.setHasFixedSize(true);
        mLayoutManagerca = new LinearLayoutManager(getContext());
        mRecyclerViewEquipos.setLayoutManager(mLayoutManagerca);
        loadPhotosFromWeb();

        // Inflate the layout for this fragment
        return  view;
    }



    private void refreshDataset () {
        if (mRecyclerViewEquipos == null)
            return;
        if (mAdapterEquipos == null) {
            mAdapterEquipos = new ListAdapter_Goleo(getContext(), myDatasetCa);
            mRecyclerViewEquipos.setAdapter(mAdapterEquipos);
        } else {
            mAdapterEquipos.notifyDataSetChanged();
        }
    }

    private void loadPhotosFromWeb () {

      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */

        //Hacemos uso de Volley para consumir el End-point
        myDatasetCa = new ArrayList<M_goleador>();


        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/ws_tabla_goleo.php";
        final String urlIMG = "http://emontec.com/liga/imagenes/logo_equipos/";

        //Instanciamos un objeto RequestQueue el cual sae encarga de gestionar la cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(getContext());

        //Armamos una peticion de tipo JSONArray por que es un JsonArray lo que obtendremos como resultado
        JsonArrayRequest aRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse");
                        //Obtenemos un JSONArray como respuesta
                        if (response != null && response.length() > 0) {
                            //Iteramos son el JSONArray
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject p = (JSONObject) response.get(i);
                                    if (p != null) {
                                        //Armamos un objeto Photo con el Title y la URL de cada JSONObject
                                        M_goleador photo = new M_goleador();

                                        // if (p.has("posicion"))
                                        //photo.setPosicion(p.getString("posicion"));


                                        if (p.has("id_equipo"))
                                            photo.setId_equipo(p.getString("id_equipo"));

                                        if (p.has("id_jugador"))
                                            photo.setId_jugador(p.getString("id_jugador"));


                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));

                                        if (p.has("goles"))
                                            photo.setGoles(p.getString("goles"));

                                        if (p.has("equipo"))
                                            photo.setEquipo(p.getString("equipo"));

                                        if (p.has("logo"))
                                            photo.setLogo_visita((urlIMG+(p.getString("logo"))));


                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetCa.add(photo);
                                        //   progreso.hide();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetCa.size() > 0)
                                        refreshDataset();
                                }

                                mRecyclerViewEquipos.setVisibility(View.VISIBLE);

                            }
                            cardView.setVisibility(View.VISIBLE);
                            fubol.setVisibility(View.INVISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        });

        //Agregamos la petición de tipo JSON a la cola
        queue.add(aRequest);

    }

    // TODO: Rename method, update argument and hook method into UI event


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
