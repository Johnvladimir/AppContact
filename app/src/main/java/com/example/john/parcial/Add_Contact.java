package com.example.john.parcial;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Add_Contact extends AppCompatActivity {

    private EditText Name, LastName, Mail, Address, Phone;
    private ImageView img;
    private Button btn_Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);

        Name = findViewById(R.id.nameAdd);
        LastName = findViewById(R.id.lasnameAdd);
        Mail = findViewById(R.id.mailAdd);
        Address = findViewById(R.id.addressAdd);
        Phone = findViewById(R.id.phoneAdd);
        img = findViewById(R.id.imgAdd);
        btn_Add = findViewById(R.id.buttonAdd);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = Name.getText().toString();
                String apellido = LastName.getText().toString();
                String correo = Mail.getText().toString();
                String direccion = Address.getText().toString();
                String telefono = Phone.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("KEY1", nombre);
                intent.putExtra("KEY2", apellido);
                intent.putExtra("KEY3", correo);
                intent.putExtra("KEY4", direccion);
                intent.putExtra("KEY5", telefono);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    //CARGAR FOTO
    public void Add_photo(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione Aplicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            img.setImageURI(path);
        }
    }
}
