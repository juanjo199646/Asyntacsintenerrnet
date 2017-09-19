package com.i053113.asyntacsintenerrnet;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// hacer referencia de las variables de encima

        progressBar = (ProgressBar) findViewById(R.id.id_pb_1);
        button =  (Button) findViewById(R.id.id_btn_1);
        textView = (TextView)findViewById(R.id.id_tv_1);

    }
    // SIEMPRE SE VA A USAR ESTE METODO PARA CUALQUIER APLICASION

    //validar toda la conexion
    public Boolean isOnline (){
        //obgeto conectivy para manejar la conectavidades
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //obtener el estado de conexion
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //Validar  el estado de la red
        if(networkInfo != null){
            return  true;

        }else {
            return false;
        }
    }


    // programar el  bototn
    // crear un metodo para cargar datos y se lo asigamos al boton
    // el loadData es del boton en Onclic
    public void  loadData (View view) {
        if (isOnline()) {
            Toast.makeText(this, "Cargar datos", Toast.LENGTH_SHORT).show();
            // declarando un obgeto de la calse que acaba de crear
                MyTask task = new MyTask();
            // para que funsione el obgeto
            // tambien para poner la url d otra pagina en este caso post
            task.execute("https://jsonplaceholder.typicode.com/posts");
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    // se encaargara de procsar todos los datos
    public void  proccessData(String s){
        // para que aparseca uno abajo de otro como  lista solo se añade  + /n despues de +S
            textView.setText("Item: " + s);
        // cambiano  por un entero
            textView.setTextSize(Integer.parseInt(s));
    }




    public  class MyTask extends AsyncTask <String, String ,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // mostrar el progressbar en la aplicasion
            progressBar.setVisibility(View.VISIBLE);

        }
/// aqui esta la logia de la tarea
        @Override
        protected String doInBackground(String... params) {
            //ciclo que vaya desde uno a 50
          /*  for (int i = 1 ; i < 50 ; i++){
                try {
                    // dar un tiempo de espera par que vaya mostrado paulatinmwnte
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //pasando de tipo a entero a string la i
                publishProgress(String.valueOf(i));
            }
            return null;*/
          // crando una variable contend
          String contend = null;
            // a la variable le asignamos  la clase hhttpmanager
            try {
                contend= HttpManager.getData(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  contend;


        }

// este es el metodo que va a tener la informacion y toca llamar al metodo
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            proccessData(values[0]);


        }
// el que ejecuta
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // para que lo vuela a desaparecer
            progressBar.setVisibility(View.GONE);
            proccessData(s);
        }



    }


}
