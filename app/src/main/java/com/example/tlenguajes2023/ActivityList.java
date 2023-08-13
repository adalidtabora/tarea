package com.example.tlenguajes2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tlenguajes2023.configuracion.ConfigDB;
import com.example.tlenguajes2023.configuracion.CustomAdapter;
import com.example.tlenguajes2023.configuracion.SQLiteConnection;
import com.example.tlenguajes2023.configuracion.personas;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity
{
    SQLiteConnection conexion;
    ListView list;

    ArrayList<personas> listpersonas;
    ArrayList<personas> arreglopersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLiteConnection(this, ConfigDB.namebd, null, 1);
        list = (ListView) findViewById(R.id.Lista);

        ObtenerTabla();

        CustomAdapter apd = new CustomAdapter(listpersonas, ActivityList.this, R.layout.row_personas);
        list.setAdapter(apd);
    }

    private void ObtenerTabla()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        personas person = null;
        listpersonas = new ArrayList<personas>();

        // Cursor de Base de Datos
        Cursor cursor = db.rawQuery(ConfigDB.SelectTBPersonas,null);

        // recorremos el cursor
        while(cursor.moveToNext())
        {
            person = new personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setDescripcion(cursor.getString(2));
            person.setFoto(cursor.getString(3));
            listpersonas.add(person);
        }

        cursor.close();

        fillData();
    }

    private void fillData() {
        arreglopersonas = listpersonas;
    }

    public void ActivityNuevaPersona(View view){
        Intent nuevo = new Intent(this, ActivityIngresar.class);
        startActivity(nuevo);
    }

}