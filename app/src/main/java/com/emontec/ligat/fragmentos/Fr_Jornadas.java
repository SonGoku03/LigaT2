package com.emontec.ligat.fragmentos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.emontec.ligat.Adaptadores.ListAdapter_Jornadas;
import com.emontec.ligat.MainActivity;
import com.emontec.ligat.Modelo.Jornadas;
import com.emontec.ligat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fr_Jornadas extends Fragment {

    View view;
    Switch aSwitch;
    LinearLayout LYEquipos;
    LinearLayout Partidos;
    private RecyclerView mRecyclerViewEquipos;
    private RecyclerView.Adapter mAdapterEquipos;
    private RecyclerView.LayoutManager mLayoutManagerca;

    private RecyclerView mRecyclerViewCampo2;
    private RecyclerView.Adapter mAdapterCampo2;
    private RecyclerView.LayoutManager mLayoutManagerCampo2;

    private RecyclerView mRecyclerViewCampo3;
    private RecyclerView.Adapter mAdapterCampo3;
    private RecyclerView.LayoutManager mLayoutManagerCampo3;

    private RecyclerView mRecyclerViewCampo4;
    private RecyclerView.Adapter mAdapterCampo4;
    private RecyclerView.LayoutManager mLayoutManagerCampo4;

    private static final String TAG = MainActivity.class.getName();
    TextView des;
    LottieAnimationView fubol;
    CardView cardView;
    ///////////////// Declaracion de variables para el recicler view

    private ArrayList<Jornadas> myDatasetCa;
    private ArrayList<Jornadas> myDatasetCampo2;
    private ArrayList<Jornadas> myDatasetCampo3;
    private ArrayList<Jornadas> myDatasetCampo4;

    private OnFragmentInteractionListener mListener;

    public Fr_Jornadas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_partidos, container, false);
        cardView= (CardView) view.findViewById(R.id.card_view);
        fubol = (LottieAnimationView) view.findViewById(R.id.futbol);
        fubol.setAnimation("futbol.json");
        fubol.playAnimation();

        ///// RECICLER CAMPO 1
        mRecyclerViewEquipos = (RecyclerView) view.findViewById(R.id.recycler_campo1);
        mRecyclerViewEquipos.setHasFixedSize(true);
        mLayoutManagerca = new LinearLayoutManager(getContext());
        mRecyclerViewEquipos.setLayoutManager(mLayoutManagerca);

        mRecyclerViewCampo2 = (RecyclerView) view.findViewById(R.id.recycler_campo2);
        mRecyclerViewCampo2.setHasFixedSize(true);
        mLayoutManagerCampo2 = new LinearLayoutManager(getContext());
        mRecyclerViewCampo2.setLayoutManager(mLayoutManagerCampo2);

        mRecyclerViewCampo3 = (RecyclerView) view.findViewById(R.id.recycler_campo3);
        mRecyclerViewCampo3.setHasFixedSize(true);
        mLayoutManagerCampo3 = new LinearLayoutManager(getContext());
        mRecyclerViewCampo3.setLayoutManager(mLayoutManagerCampo3);

        mRecyclerViewCampo4 = (RecyclerView) view.findViewById(R.id.recycler_campo4);
        mRecyclerViewCampo4.setHasFixedSize(true);
        mLayoutManagerCampo4 = new LinearLayoutManager(getContext());
        mRecyclerViewCampo4.setLayoutManager(mLayoutManagerCampo4);




        campo1();
        campo2();
        campo3();
        campo4();

        // Inflate the layout for this fragment
        return  view;
    }



    private void refreshDataset () {
        if (mRecyclerViewEquipos == null)
            return;
        if (mAdapterEquipos == null) {
            mAdapterEquipos = new ListAdapter_Jornadas(getContext(), myDatasetCa);
            mRecyclerViewEquipos.setAdapter(mAdapterEquipos);
        } else {
            mAdapterEquipos.notifyDataSetChanged();
        }
    }

    private void actualizar_lista_campo2 () {
        if (mRecyclerViewCampo2 == null)
            return;
        if (mAdapterCampo2 == null) {
            mAdapterCampo2 = new ListAdapter_Jornadas(getContext(), myDatasetCampo2);
            mRecyclerViewCampo2.setAdapter(mAdapterCampo2);
        } else {
            mAdapterCampo2.notifyDataSetChanged();
        }
    }

    private void actualizar_lista_campo3 () {
        if (mRecyclerViewCampo3 == null)
            return;
        if (mAdapterCampo3 == null) {
            mAdapterCampo3 = new ListAdapter_Jornadas(getContext(), myDatasetCampo3);
            mRecyclerViewCampo3.setAdapter(mAdapterCampo3);
        } else {
            mAdapterCampo3.notifyDataSetChanged();
        }
    }

    private void actualizar_lista_campo4 () {
        if (mRecyclerViewCampo4 == null)
            return;
        if (mAdapterCampo4 == null) {
            mAdapterCampo4 = new ListAdapter_Jornadas(getContext(), myDatasetCampo4);
            mRecyclerViewCampo4.setAdapter(mAdapterCampo4);
        } else {
            mAdapterCampo4.notifyDataSetChanged();
        }
    }

    private void campo1 () {
        SharedPreferences misPreferencias =getActivity().getSharedPreferences("Jornada",Context.MODE_PRIVATE);
        String id_jornada= (misPreferencias.getString("id_jornada",""));
        //Toast.makeText(getContext(), "Bienvenido  "+id_jornada, Toast.LENGTH_LONG).show();
      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */
        //Hacemos uso de Volley para consumir el End-point
        myDatasetCa = new ArrayList<Jornadas>();
        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/ws_tabla_resultados_jornada.php?Jornada="+id_jornada+"&Campo=1";
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
                                        Jornadas photo = new Jornadas();
                                        if (p.has("jornada"))
                                            photo.setJornada(p.getString("jornada"));
                                        if (p.has("logo_local"))
                                            photo.setLogo_local((urlIMG+(p.getString("logo_local"))));
                                        if (p.has("logo_visita"))
                                            photo.setLogo_visita((urlIMG+(p.getString("logo_visita"))));
                                        if (p.has("nombre_local"))
                                            photo.setNombre_local(p.getString("nombre_local"));
                                        if (p.has("gol_local"))
                                            photo.setGol_local(p.getString("gol_local"));
                                        if (p.has("gol_visitante"))
                                            photo.setGol_visita(p.getString("gol_visitante"));
                                        if (p.has("nombre_visitante"))
                                            photo.setNombre_visita(p.getString("nombre_visitante"));
                                        if (p.has("hora"))
                                            photo.setHorario(p.getString("hora"));
                                        if (p.has("estadio"))
                                            photo.setCampo(p.getString("estadio"));
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
                                    if (myDatasetCa.size() > 0){
                                        refreshDataset();
                                   //     Toast.makeText(getContext(), "hay datos  ", Toast.LENGTH_LONG).show();
                                    }else{
                                      //  Toast.makeText(getContext(), "sin datos  ", Toast.LENGTH_LONG).show();
                                    }
                                }
                                mRecyclerViewEquipos.setVisibility(View.VISIBLE);
                            }
                            cardView.setVisibility(View.VISIBLE);
                            fubol.setVisibility(View.INVISIBLE);
                         /*   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                            // set title
                            alertDialogBuilder.setTitle("Productos nuevos");

                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("No existen productos nuevos para este destino")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // if this button is clicked, close
                                            // current activity

                                        }
                                    });
                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show(); */
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


    private void campo2 () {
        SharedPreferences misPreferencias =getActivity().getSharedPreferences("Jornada",Context.MODE_PRIVATE);
        String id_jornada= (misPreferencias.getString("id_jornada",""));
        //Toast.makeText(getContext(), "Bienvenido  "+id_jornada, Toast.LENGTH_LONG).show();
      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */
        //Hacemos uso de Volley para consumir el End-point
        myDatasetCampo2 = new ArrayList<Jornadas>();
        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/ws_tabla_resultados_jornada.php?Jornada="+id_jornada+"&Campo=2";
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
                                        Jornadas photo = new Jornadas();
                                        if (p.has("jornada"))
                                            photo.setJornada(p.getString("jornada"));
                                        if (p.has("logo_local"))
                                            photo.setLogo_local((urlIMG+(p.getString("logo_local"))));
                                        if (p.has("logo_visita"))
                                            photo.setLogo_visita((urlIMG+(p.getString("logo_visita"))));
                                        if (p.has("nombre_local"))
                                            photo.setNombre_local(p.getString("nombre_local"));
                                        if (p.has("gol_local"))
                                            photo.setGol_local(p.getString("gol_local"));
                                        if (p.has("gol_visitante"))
                                            photo.setGol_visita(p.getString("gol_visitante"));
                                        if (p.has("nombre_visitante"))
                                            photo.setNombre_visita(p.getString("nombre_visitante"));
                                        if (p.has("hora"))
                                            photo.setHorario(p.getString("hora"));
                                        if (p.has("estadio"))
                                            photo.setCampo(p.getString("estadio"));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetCampo2.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetCampo2.size() > 0)
                                        actualizar_lista_campo2();
                                }
                                mRecyclerViewCampo2.setVisibility(View.VISIBLE);
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

    private void campo3 () {
        SharedPreferences misPreferencias =getActivity().getSharedPreferences("Jornada",Context.MODE_PRIVATE);
        String id_jornada= (misPreferencias.getString("id_jornada",""));
        //Toast.makeText(getContext(), "Bienvenido  "+id_jornada, Toast.LENGTH_LONG).show();
      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */
        //Hacemos uso de Volley para consumir el End-point
        myDatasetCampo3 = new ArrayList<Jornadas>();
        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/ws_tabla_resultados_jornada.php?Jornada="+id_jornada+"&Campo=3";
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
                                        Jornadas photo = new Jornadas();
                                        if (p.has("jornada"))
                                            photo.setJornada(p.getString("jornada"));
                                        if (p.has("logo_local"))
                                            photo.setLogo_local((urlIMG+(p.getString("logo_local"))));
                                        if (p.has("logo_visita"))
                                            photo.setLogo_visita((urlIMG+(p.getString("logo_visita"))));
                                        if (p.has("nombre_local"))
                                            photo.setNombre_local(p.getString("nombre_local"));
                                        if (p.has("gol_local"))
                                            photo.setGol_local(p.getString("gol_local"));
                                        if (p.has("gol_visitante"))
                                            photo.setGol_visita(p.getString("gol_visitante"));
                                        if (p.has("nombre_visitante"))
                                            photo.setNombre_visita(p.getString("nombre_visitante"));
                                        if (p.has("hora"))
                                            photo.setHorario(p.getString("hora"));
                                        if (p.has("estadio"))
                                            photo.setCampo(p.getString("estadio"));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetCampo3.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetCampo3.size() > 0)
                                        actualizar_lista_campo3();
                                }
                                mRecyclerViewCampo3.setVisibility(View.VISIBLE);
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

    private void campo4 () {
        SharedPreferences misPreferencias =getActivity().getSharedPreferences("Jornada",Context.MODE_PRIVATE);
        String id_jornada= (misPreferencias.getString("id_jornada",""));
        //Toast.makeText(getContext(), "Bienvenido  "+id_jornada, Toast.LENGTH_LONG).show();
      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */
        //Hacemos uso de Volley para consumir el End-point
        myDatasetCampo4 = new ArrayList<Jornadas>();
        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/ws_tabla_resultados_jornada.php?Jornada="+id_jornada+"&Campo=4";
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
                                        Jornadas photo = new Jornadas();
                                        if (p.has("jornada"))
                                            photo.setJornada(p.getString("jornada"));
                                        if (p.has("logo_local"))
                                            photo.setLogo_local((urlIMG+(p.getString("logo_local"))));
                                        if (p.has("logo_visita"))
                                            photo.setLogo_visita((urlIMG+(p.getString("logo_visita"))));
                                        if (p.has("nombre_local"))
                                            photo.setNombre_local(p.getString("nombre_local"));
                                        if (p.has("gol_local"))
                                            photo.setGol_local(p.getString("gol_local"));
                                        if (p.has("gol_visitante"))
                                            photo.setGol_visita(p.getString("gol_visitante"));
                                        if (p.has("nombre_visitante"))
                                            photo.setNombre_visita(p.getString("nombre_visitante"));
                                        if (p.has("hora"))
                                            photo.setHorario(p.getString("hora"));
                                        if (p.has("estadio"))
                                            photo.setCampo(p.getString("estadio"));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetCampo4.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetCampo4.size() > 0)
                                        actualizar_lista_campo4();
                                }
                                mRecyclerViewCampo4.setVisibility(View.VISIBLE);
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
