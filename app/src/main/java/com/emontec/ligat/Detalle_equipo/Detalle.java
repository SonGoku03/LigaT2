package com.emontec.ligat.Detalle_equipo;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.emontec.ligat.R;

public class Detalle extends AppCompatActivity {

    TextView equipo, puntos,jugados, ganados,perdidos, empatados, gol_fa, gol_con,diferencia,posicion;
    CollapsingToolbarLayout appbar;
    ImageView logo_equipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

    appbar = (CollapsingToolbarLayout) findViewById(R.id.colapso_bar);

        logo_equipo = (ImageView) findViewById(R.id.imagen_detalle);
        equipo = (TextView) findViewById(R.id.nombre_equipo_deta);
        puntos = (TextView) findViewById(R.id.puntos);
        jugados = (TextView) findViewById(R.id.jugados);
        ganados = (TextView) findViewById(R.id.ganados);
        empatados = (TextView) findViewById(R.id.empatados);
        perdidos = (TextView) findViewById(R.id.perdidos);
        gol_fa = (TextView) findViewById(R.id.gol_favor);
        gol_con = (TextView) findViewById(R.id.gol_contra);
        diferencia = (TextView) findViewById(R.id.diferencia);
        posicion= (TextView) findViewById(R.id.posicion);

        String equi = getIntent().getStringExtra("nombre");
        String pun = getIntent().getStringExtra("puntos");
        String jug = getIntent().getStringExtra("par_ju");
        String gan = getIntent().getStringExtra("par_ga");
        String emp = getIntent().getStringExtra("par_em");
        String per = getIntent().getStringExtra("par_pe");
        String gol_favo = getIntent().getStringExtra("gol_fa");
        String gol_contr = getIntent().getStringExtra("gol_contra");
        String dif = getIntent().getStringExtra("diferencia");
        String pos = getIntent().getStringExtra("posicion");
        Bitmap bitmap = getIntent().getParcelableExtra("bitMap");



        appbar.setTitle(equi);

       equipo.setText(equi);
       jugados.setText(jug);
       ganados.setText(gan);
       empatados.setText(emp);
       perdidos.setText(per);
       gol_fa.setText(gol_favo);
       gol_con.setText(gol_contr);
       puntos.setText(pun);
       diferencia.setText(dif);
       posicion.setText(pos);
       logo_equipo.setImageBitmap(bitmap);



    }
}
