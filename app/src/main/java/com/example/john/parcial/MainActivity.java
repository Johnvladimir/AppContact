package com.example.john.parcial;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<Contacto> listaContacto;
    RecyclerView recyclerContacto;
    AdaptadorContactos adapter;
    private static final int Code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContacto = new ArrayList<>();
        recyclerContacto = findViewById(R.id.recyclerId);
        recyclerContacto.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            cargarContactos();
        } else {
            listaContacto = savedInstanceState.getParcelableArrayList("key");
        }

        adapter = new AdaptadorContactos(listaContacto, this);
        recyclerContacto.setAdapter(adapter);
    }

    //CARGAR CONTACTOS DEL CELULAR
    private void cargarContactos() {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String LastName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE));
                int hasNumner = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasNumner > 0) {
                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (cursor2.moveToNext()) {
                        String phoneNumner = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append(name).append(".");
                        builder2.append(phoneNumner).append(".");
                        builder3.append(LastName).append(".");
                    }
                    cursor2.close();
                }
            }
        }
        cursor.close();

        String names = builder.toString();
        String phones = builder2.toString();
        String lastnames = builder3.toString();

        String[] allnames = names.split("\\.");
        String[] allphones = phones.split("\\.");
        String[] alllastnames = lastnames.split("\\.");

        for (int i = 0; i < allnames.length; i++) {
            listaContacto.add(new Contacto(allnames[i], alllastnames[i], " ", " ", allphones[i],
                    R.drawable.perfil));
        }
    }

    //PETICION
    //AGREGAR UN CONTACTO
    public void agregarContacto(View view) {
        Intent intent = new Intent(this, Add_Contact.class);
        startActivityForResult(intent, Code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String nombre = data.getStringExtra("KEY1");
            String apellido = data.getStringExtra("KEY2");
            String correo = data.getStringExtra("KEY3");
            String direccion = data.getStringExtra("KEY4");
            String telefono = data.getStringExtra("KEY5");
            listaContacto.add(new Contacto(nombre, apellido, correo, direccion, telefono, R.drawable.perfil));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", listaContacto);
        super.onSaveInstanceState(outState);
    }

    //METODO LLENAR CONTACTOS DE MODO QUEMADO
    private void llenarPersonajes() {
        listaContacto.add(new Contacto("John", "Linares", "00005016@uca.edu.sv", "San Salvador", "75809621",
                R.drawable.foto1));

        listaContacto.add(new Contacto("Milton", "Alejandro", "00012316@uca.edu.sv", "San Salvador", "76809621",
                R.drawable.perfil));

        listaContacto.add(new Contacto("Margarita", "Aviles", "00057312@uca.edu.sv", "La Libertad", "76569689",
                R.drawable.foto2));
    }

    //BARRA BUSCADOR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Contacto> newList = new ArrayList<>();
        for (Contacto contacto : listaContacto) {
            String name = contacto.getNombre().toLowerCase();
            if (name.contains(newText)) {
                newList.add(contacto);
            }
        }

        adapter.setFilter(newList);
        return true;
    }
}