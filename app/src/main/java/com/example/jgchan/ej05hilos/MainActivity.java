package com.example.jgchan.ej05hilos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Dibujar(this));
    }


    public class Dibujar extends View implements Runnable{
    Drawable imagenActual;
    Drawable [] imagenes;

        public Dibujar(Context context) {
            super(context);
            int[] nombres={
                    R.drawable.man01,
                    R.drawable.man02,
                    R.drawable.man03,
                    R.drawable.man04,
                    R.drawable.man05,
                    R.drawable.man06,
                    R.drawable.man07,
                    R.drawable.man08
            };

            imagenes = new Drawable[8];

            for(int i =0; i<imagenes.length;i++){
               //imagenes[i] = Drawable.createFromPath("../drawable"+nombres[i]);
               imagenes[i] = context.getResources().getDrawable(nombres[i]);
            }

            imagenActual=imagenes[0];

            Thread hilo = new Thread(this);
            hilo.start();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int ancho,alto; // figura
            ancho = imagenActual.getIntrinsicWidth();
            alto = imagenActual.getIntrinsicHeight();

            imagenActual.setBounds(0,0, ancho,alto);
            imagenActual.draw(canvas);

        }

        @Override
        public void run() {
            int img=0;

            while(true){
                imagenActual = imagenes[img];
                img++;
                if(img>7) img=0;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate(); //Para Redibujar si se usan hilos
            }

        }
    }

}
