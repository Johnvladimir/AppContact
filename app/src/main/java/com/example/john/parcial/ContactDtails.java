package com.example.john.parcial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class ContactDtails extends AppCompatActivity {

    ImageView imageView;
    TextView tv_name, tv_lastname, tv_email, tv_address, tv_phone;
    Button btn_call, btn_share;
    String getnombre, getapellido, getcorreo, getdireccion, gettelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details_layout);

        imageView = findViewById(R.id.ImagenIdDetalle);
        tv_name = findViewById(R.id.NombreIdDetalle);
        tv_lastname = findViewById(R.id.ApellidoIdDetalle);
        tv_email = findViewById(R.id.MailIdDetalle);
        tv_address = findViewById(R.id.DireccionIdDetalle);
        tv_phone = findViewById(R.id.TelIdDetalle);
        btn_call = findViewById(R.id.btn_CallIdDetalle);
        btn_share = findViewById(R.id.btn_ShareIdDetalle);

        //OBTENER INFORMACION PARA COMPARTIR
        getnombre = getIntent().getStringExtra("Name");
        getapellido = getIntent().getStringExtra("LastName");
        getcorreo = getIntent().getStringExtra("Email");
        getdireccion = getIntent().getStringExtra("Address");
        gettelefono = getIntent().getStringExtra("Phone");

        //DATOS A SET EN LAYOUT
        imageView.setImageResource(getIntent().getIntExtra("img", 00));
        tv_name.setText(getIntent().getStringExtra("Name"));
        tv_lastname.setText(getIntent().getStringExtra("LastName"));
        tv_email.setText(getIntent().getStringExtra("Email"));
        tv_address.setText(getIntent().getStringExtra("Address"));
        tv_phone.setText(getIntent().getStringExtra("Phone"));

    }

    //COMPARTIR INFORMACION
    public void ShareInfo(View view) {
        imageView.buildDrawingCache();
        Bitmap bits_imagen = imageView.getDrawingCache();
        try {
            File archivo_imagen = new File(imageView.getContext().getCacheDir(), bits_imagen + ".png");
            FileOutputStream fout;
            fout = new FileOutputStream(archivo_imagen);
            fout.flush();
            fout.close();
            archivo_imagen.setReadable(true, false);
            final Intent compartir = new Intent(Intent.ACTION_SEND);
            compartir.setType("text/plain");
            compartir.putExtra(Intent.EXTRA_TEXT, "Name: " + getnombre + "\nLastName: " + getapellido + "\nE-Mail: " + getcorreo
                    + "\nAddress: " + getdireccion + "\nPhone: " + gettelefono);
            compartir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            compartir.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(archivo_imagen));
            compartir.setType("imgage/png");

            startActivity(compartir.createChooser(compartir, "Share whith"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CallInfo(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + gettelefono));
        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        view.getContext().startActivity(intent);
    }

    public void EditContact(View view) {
        Intent intent = new Intent(ContactDtails.this, EditContact.class);
        intent.putExtra("nombre", getnombre);
        intent.putExtra("apellido", getapellido);
        intent.putExtra("correo", getcorreo);
        intent.putExtra("direccion", getdireccion);
        intent.putExtra("telefono", gettelefono);
        startActivity(intent);
    }
}