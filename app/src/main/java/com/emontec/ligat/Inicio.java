package com.emontec.ligat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.emontec.ligat.Modelo.ListaFamilias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerViewCa;
    private RecyclerView.Adapter mAdapterca;
    private RecyclerView.LayoutManager mLayoutManagerca;
    private static final String TAG = MainActivity.class.getName();
    TextView des;
    final Context context = this;

    ProgressDialog progreso;
    //El dataset de tipo Photo
    private ArrayList<ListaFamilias> myDatasetCa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
      //  drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        mRecyclerViewCa = (RecyclerView) findViewById(R.id.my_recycler_view_fa);

        // Si se sabe que la cantidad de items de la lista es siempre la misma y esta no cambiará
        // entonces podemos hacer uso de la sigiente propidad para mejorar el
        // Performance del RecyclerView
        mRecyclerViewCa.setHasFixedSize(true);

        // Instanciamos unlinear layout manager para setearlo en el RecyclerView
        mLayoutManagerca = new LinearLayoutManager(getApplication());
        mRecyclerViewCa.setLayoutManager(mLayoutManagerca);
        loadPhotosFromWeb();
    }


    private void refreshDataset () {
        if (mRecyclerViewCa == null)
            return;

        if (mAdapterca == null) {
            mAdapterca = new com.emontec.ligat.ListAdapterFamiliasAll(getApplication(), myDatasetCa);
            mRecyclerViewCa.setAdapter(mAdapterca);
        } else {
            mAdapterca.notifyDataSetChanged();
        }
    }


    private void loadPhotosFromWeb () {

      /*  progreso= new ProgressDialog(this);
        progreso.setMessage("cargando...");
        progreso.show();  */

        //Hacemos uso de Volley para consumir el End-point
        myDatasetCa = new ArrayList<ListaFamilias>();

       // Toast.makeText(getApplication(), " 1", Toast.LENGTH_SHORT).show();
        //Definimos un String con la URL del End-point
        String url = "http://emontec.com/liga/Cantidad.php";
        final String urlIMG = "http://bluelinemexico.com/";

      //  Toast.makeText(getApplication(), " 2", Toast.LENGTH_SHORT).show();
        //Instanciamos un objeto RequestQueue el cual sae encarga de gestionar la cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(getApplication());

        //Armamos una peticion de tipo JSONArray por que es un JsonArray lo que obtendremos como resultado
        JsonArrayRequest aRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse");
                   //     Toast.makeText(getApplication(), " 3", Toast.LENGTH_SHORT).show();
                        //Obtenemos un JSONArray como respuesta
                        if (response != null && response.length() > 0) {
                            //Iteramos son el JSONArray
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject p = (JSONObject) response.get(i);
                                    if (p != null) {
                                        //Armamos un objeto Photo con el Title y la URL de cada JSONObject
                                        ListaFamilias photo = new ListaFamilias();
                                        if (p.has("nombre"))
                                            photo.setNombre(p.getString("nombre"));

                                        if (p.has("jornada"))
                                            photo.setJornada(p.getString("jornada"));

                                        if (p.has("tipo"))
                                            photo.setTipo(p.getString("tipo"));

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

                                mRecyclerViewCa.setVisibility(View.VISIBLE);
                              //  animacion.setVisibility(View.INVISIBLE);
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse____");
            }
        });

        //Agregamos la petición de tipo JSON a la cola
        queue.add(aRequest);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
