package com.emontec.ligat.fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.emontec.ligat.Adaptadores.ListAdapter_Equipos;
import com.emontec.ligat.MainActivity;
import com.emontec.ligat.Modelo.Equipos;
import com.emontec.ligat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Posiciones extends Fragment {

    View view;
    Switch aSwitch;
    LinearLayout LYEquipos;
    LinearLayout LYGrupos;
    private RecyclerView mRecyclerViewEquipos;
    private RecyclerView.Adapter mAdapterEquipos;
    private RecyclerView.LayoutManager mLayoutManagerca;
    private static final String TAG = MainActivity.class.getName();
    TextView des;
///////////////// Declaracion de variables para el recicler view
    private RecyclerView mRecyclerViewGrupo1;
    private RecyclerView.Adapter mAdapterGrupo1;
    private RecyclerView.LayoutManager mLayoutManagerGrupo1;

    private RecyclerView mRecyclerViewGrupo2;
    private RecyclerView.Adapter mAdapterGrupo2;
    private RecyclerView.LayoutManager mLayoutManagerGrupo2;

    private RecyclerView mRecyclerViewGrupo3;
    private RecyclerView.Adapter mAdapterGrupo3;
    private RecyclerView.LayoutManager mLayoutManagerGrupo3;

    private RecyclerView mRecyclerViewGrupo4;
    private RecyclerView.Adapter mAdapterGrupo4;
    private RecyclerView.LayoutManager mLayoutManagerGrupo4;

    ProgressDialog progreso;
    //El dataset de tipo Photo
    private ArrayList<Equipos> myDatasetCa;
    private ArrayList<Equipos> myDatasetGrupo1;
    private ArrayList<Equipos> myDatasetGrupo2;
    private ArrayList<Equipos> myDatasetGrupo3;
    private ArrayList<Equipos> myDatasetGrupo4;

    RecyclerView recyclerEquipos;

    private OnFragmentInteractionListener mListener;

    public Posiciones() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_two, container, false);

        LYEquipos = (LinearLayout) view.findViewById(R.id.layout_general);
        LYGrupos = (LinearLayout) view.findViewById(R.id.layout_grupos);

        aSwitch = (Switch)view.findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b== true){
                  //  Toast.makeText(getActivity(), "prendido" , Toast.LENGTH_LONG).show();
                    //mRecyclerViewEquipos.setVisibility(View.INVISIBLE);
                    LYEquipos.setVisibility(View.GONE);
                    LYGrupos.setVisibility(View.VISIBLE);
                    /*
                    Posiciones_grupo nuevoFragmento = new Posiciones_grupo();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedor, nuevoFragmento);
                    transaction.addToBackStack(null);
                    transaction.commit();*/


                }else{
                  //  Toast.makeText(getActivity(), "apagado" , Toast.LENGTH_LONG).show();
                    //mRecyclerViewEquipos.setVisibility(View.VISIBLE);
                    LYGrupos.setVisibility(View.GONE);
                    LYEquipos.setVisibility(View.VISIBLE);
                }
            }
        });

        mRecyclerViewEquipos = (RecyclerView) view.findViewById(R.id.my_recycler_view_fa);
        mRecyclerViewEquipos.setHasFixedSize(true);
        mLayoutManagerca = new LinearLayoutManager(getContext());
        mRecyclerViewEquipos.setLayoutManager(mLayoutManagerca);
        loadPhotosFromWeb();

        mRecyclerViewGrupo1 = (RecyclerView) view.findViewById(R.id.my_recycler_view_grupo1);
        mRecyclerViewGrupo1.setHasFixedSize(true);
        mLayoutManagerGrupo1 = new LinearLayoutManager(getContext());
        mRecyclerViewGrupo1.setLayoutManager(mLayoutManagerGrupo1);
        Grupo1();

        mRecyclerViewGrupo2 = (RecyclerView) view.findViewById(R.id.my_recycler_view_grupo2);
        mRecyclerViewGrupo2.setHasFixedSize(true);
        mLayoutManagerGrupo2 = new LinearLayoutManager(getContext());
        mRecyclerViewGrupo2.setLayoutManager(mLayoutManagerGrupo2);
        Grupo2();

        mRecyclerViewGrupo3 = (RecyclerView) view.findViewById(R.id.my_recycler_view_grupo3);
        mRecyclerViewGrupo3.setHasFixedSize(true);
        mLayoutManagerGrupo3 = new LinearLayoutManager(getContext());
        mRecyclerViewGrupo3.setLayoutManager(mLayoutManagerGrupo3);
        Grupo3();

        mRecyclerViewGrupo4 = (RecyclerView) view.findViewById(R.id.my_recycler_view_grupo4);
        mRecyclerViewGrupo4.setHasFixedSize(true);
        mLayoutManagerGrupo4 = new LinearLayoutManager(getContext());
        mRecyclerViewGrupo4.setLayoutManager(mLayoutManagerGrupo4);
        Grupo4();

        // Inflate the layout for this fragment
        return  view;
    }



    private void refreshDataset () {
        if (mRecyclerViewEquipos == null)
            return;
        if (mAdapterEquipos == null) {
            mAdapterEquipos = new ListAdapter_Equipos(getContext(), myDatasetCa);
            mRecyclerViewEquipos.setAdapter(mAdapterEquipos);
        } else {
            mAdapterEquipos.notifyDataSetChanged();
        }
    }

    private void refreshgrupo1 () {
        if (mRecyclerViewGrupo1 == null)
            return;
        if (mAdapterGrupo1 == null) {
            mAdapterGrupo1 = new ListAdapter_Equipos(getContext(), myDatasetGrupo1);
            mRecyclerViewGrupo1.setAdapter(mAdapterGrupo1);
        } else {
            mAdapterGrupo1.notifyDataSetChanged();
        }
    }

    private void refreshgrupo2 () {
        if (mRecyclerViewGrupo2 == null)
            return;
        if (mAdapterGrupo2 == null) {
            mAdapterGrupo2 = new ListAdapter_Equipos(getContext(), myDatasetGrupo2);
            mRecyclerViewGrupo2.setAdapter(mAdapterGrupo2);
        } else {
            mAdapterGrupo2.notifyDataSetChanged();
        }
    }

    private void refreshgrupo3 () {
        if (mRecyclerViewGrupo3 == null)
            return;
        if (mAdapterGrupo3 == null) {
            mAdapterGrupo3 = new ListAdapter_Equipos(getContext(), myDatasetGrupo3);
            mRecyclerViewGrupo3.setAdapter(mAdapterGrupo3);
        } else {
            mAdapterGrupo3.notifyDataSetChanged();
        }
    }

    private void refreshgrupo4 () {
        if (mRecyclerViewGrupo4 == null)
            return;
        if (mAdapterGrupo4 == null) {
            mAdapterGrupo4 = new ListAdapter_Equipos(getContext(), myDatasetGrupo4);
            mRecyclerViewGrupo4.setAdapter(mAdapterGrupo4);
        } else {
            mAdapterGrupo4.notifyDataSetChanged();
        }
    }


    private void loadPhotosFromWeb () {

      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */

        //Hacemos uso de Volley para consumir el End-point
        myDatasetCa = new ArrayList<Equipos>();


        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/ws_tabla_general.php";
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
                                        Equipos photo = new Equipos();

                                       // if (p.has("posicion"))
                                            //photo.setPosicion(p.getString("posicion"));

                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));

                                        if (p.has("puntos"))
                                            photo.setPuntos(p.getString("puntos"));

                                        if (p.has("part_jugados"))
                                            photo.setPar_ju(p.getString("part_jugados"));

                                        if (p.has("part_ganados"))
                                            photo.setPar_ga(p.getString("part_ganados"));

                                        if (p.has("part_empatados"))
                                            photo.setPar_em(p.getString("part_empatados"));

                                        if (p.has("part_perdidos"))
                                            photo.setPar_pe(p.getString("part_perdidos"));

                                        if (p.has("goles_favor"))
                                            photo.setGol_fa(p.getString("goles_favor"));

                                        if (p.has("goles_contra"))
                                            photo.setGol_contra(p.getString("goles_contra"));

                                        if (p.has("diferencia"))
                                            photo.setDiferencia(p.getString("diferencia"));

                                        if (p.has("url_logo"))
                                            photo.setLogo_visita((urlIMG+(p.getString("url_logo"))));


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



    private void Grupo1 () {
        myDatasetGrupo1 = new ArrayList<Equipos>();
        String grupo="1";
        String url = "http://emontec.com/liga/ws_tabla_grupo1.php?Grupo="+grupo;
        final String urlIMG = "http://emontec.com/liga/imagenes/logo_equipos/";
        RequestQueue queue = Volley.newRequestQueue(getContext());
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
                                        Equipos photo = new Equipos();
                                        // if (p.has("posicion"))
                                        //photo.setPosicion(p.getString("posicion"));
                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));
                                        if (p.has("puntos"))
                                            photo.setPuntos(p.getString("puntos"));
                                        if (p.has("part_jugados"))
                                            photo.setPar_ju(p.getString("part_jugados"));

                                        if (p.has("part_ganados"))
                                            photo.setPar_ga(p.getString("part_ganados"));

                                        if (p.has("part_empatados"))
                                            photo.setPar_em(p.getString("part_empatados"));

                                        if (p.has("part_perdidos"))
                                            photo.setPar_pe(p.getString("part_perdidos"));

                                        if (p.has("goles_favor"))
                                            photo.setGol_fa(p.getString("goles_favor"));

                                        if (p.has("goles_contra"))
                                            photo.setGol_contra(p.getString("goles_contra"));


                                        if (p.has("diferencia"))
                                            photo.setDiferencia(p.getString("diferencia"));
                                        if (p.has("url_logo"))
                                            photo.setLogo_visita((urlIMG+(p.getString("url_logo"))));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetGrupo1.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetGrupo1.size() > 0)
                                        refreshgrupo1();
                                }
                                mRecyclerViewGrupo1.setVisibility(View.VISIBLE);
                            }
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

    private void Grupo2 () {
        myDatasetGrupo2 = new ArrayList<Equipos>();
        String grupo="2";
        String url = "http://emontec.com/liga/ws_tabla_grupo1.php?Grupo="+grupo;
        final String urlIMG = "http://emontec.com/liga/imagenes/logo_equipos/";
        RequestQueue queue = Volley.newRequestQueue(getContext());
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
                                        Equipos photo = new Equipos();
                                        // if (p.has("posicion"))
                                        //photo.setPosicion(p.getString("posicion"));
                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));
                                        if (p.has("puntos"))
                                            photo.setPuntos(p.getString("puntos"));
                                        if (p.has("part_jugados"))
                                            photo.setPar_ju(p.getString("part_jugados"));

                                        if (p.has("part_ganados"))
                                            photo.setPar_ga(p.getString("part_ganados"));

                                        if (p.has("part_empatados"))
                                            photo.setPar_em(p.getString("part_empatados"));

                                        if (p.has("part_perdidos"))
                                            photo.setPar_pe(p.getString("part_perdidos"));

                                        if (p.has("goles_favor"))
                                            photo.setGol_fa(p.getString("goles_favor"));

                                        if (p.has("goles_contra"))
                                            photo.setGol_contra(p.getString("goles_contra"));


                                        if (p.has("diferencia"))
                                            photo.setDiferencia(p.getString("diferencia"));
                                        if (p.has("url_logo"))
                                            photo.setLogo_visita((urlIMG+(p.getString("url_logo"))));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetGrupo2.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetGrupo2.size() > 0)
                                        refreshgrupo2();
                                }
                                mRecyclerViewGrupo2.setVisibility(View.VISIBLE);
                            }
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



    private void Grupo3 () {
        myDatasetGrupo3 = new ArrayList<Equipos>();
        String grupo="3";
        String url = "http://emontec.com/liga/ws_tabla_grupo1.php?Grupo="+grupo;
        final String urlIMG = "http://emontec.com/liga/imagenes/logo_equipos/";
        RequestQueue queue = Volley.newRequestQueue(getContext());
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
                                        Equipos photo = new Equipos();
                                        // if (p.has("posicion"))
                                        //photo.setPosicion(p.getString("posicion"));
                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));
                                        if (p.has("puntos"))
                                            photo.setPuntos(p.getString("puntos"));
                                        if (p.has("part_jugados"))
                                            photo.setPar_ju(p.getString("part_jugados"));

                                        if (p.has("part_ganados"))
                                            photo.setPar_ga(p.getString("part_ganados"));

                                        if (p.has("part_empatados"))
                                            photo.setPar_em(p.getString("part_empatados"));

                                        if (p.has("part_perdidos"))
                                            photo.setPar_pe(p.getString("part_perdidos"));

                                        if (p.has("goles_favor"))
                                            photo.setGol_fa(p.getString("goles_favor"));

                                        if (p.has("goles_contra"))
                                            photo.setGol_contra(p.getString("goles_contra"));


                                        if (p.has("diferencia"))
                                            photo.setDiferencia(p.getString("diferencia"));
                                        if (p.has("url_logo"))
                                            photo.setLogo_visita((urlIMG+(p.getString("url_logo"))));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetGrupo3.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetGrupo3.size() > 0)
                                        refreshgrupo3();
                                }
                                mRecyclerViewGrupo3.setVisibility(View.VISIBLE);
                            }
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


    private void Grupo4 () {
        myDatasetGrupo4 = new ArrayList<Equipos>();
        String grupo="4";
        String url = "http://emontec.com/liga/ws_tabla_grupo1.php?Grupo="+grupo;
        final String urlIMG = "http://emontec.com/liga/imagenes/logo_equipos/";
        RequestQueue queue = Volley.newRequestQueue(getContext());
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
                                        Equipos photo = new Equipos();
                                        // if (p.has("posicion"))
                                        //photo.setPosicion(p.getString("posicion"));
                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));
                                        if (p.has("puntos"))
                                            photo.setPuntos(p.getString("puntos"));
                                        if (p.has("part_jugados"))
                                            photo.setPar_ju(p.getString("part_jugados"));

                                        if (p.has("part_ganados"))
                                            photo.setPar_ga(p.getString("part_ganados"));

                                        if (p.has("part_empatados"))
                                            photo.setPar_em(p.getString("part_empatados"));

                                        if (p.has("part_perdidos"))
                                            photo.setPar_pe(p.getString("part_perdidos"));

                                        if (p.has("goles_favor"))
                                            photo.setGol_fa(p.getString("goles_favor"));

                                        if (p.has("goles_contra"))
                                            photo.setGol_contra(p.getString("goles_contra"));


                                        if (p.has("diferencia"))
                                            photo.setDiferencia(p.getString("diferencia"));
                                        if (p.has("url_logo"))
                                            photo.setLogo_visita((urlIMG+(p.getString("url_logo"))));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetGrupo4.add(photo);
                                        //   progreso.hide();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //   progreso.hide();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetGrupo4.size() > 0)
                                        refreshgrupo4();
                                }
                                mRecyclerViewGrupo4.setVisibility(View.VISIBLE);
                            }
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
