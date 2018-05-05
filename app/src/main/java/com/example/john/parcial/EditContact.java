package com.example.john.parcial;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditContact extends AppCompatActivity {

    private EditText Name, LastName, Mail, Address, Phone;
    private ImageView img;
    String nombre,apellido,correo,direccion,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Name = findViewById(R.id.nameEdit);
        LastName = findViewById(R.id.lasnameEdit);
        Mail = findViewById(R.id.mailEdit);
        Address = findViewById(R.id.addressEdit);
        Phone = findViewById(R.id.phoneEdit);
        img = findViewById(R.id.imgEdit);

        //Obtener datos para editar
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = (String) bundle.get("nombre");
        Name.setText(nombre);
        apellido = (String) bundle.get("apellido");
        LastName.setText(apellido);
        correo = (String) bundle.get("correo");
        Mail.setText(correo);
        direccion = (String) bundle.get("direccion");
        Address.setText(direccion);
        telefono = (String) bundle.get("telefono");
        Phone.setText(telefono);
    }

    //ENVIAR DATOS DEL CONTACTO EDITADO
    public void sendEdit(View view){
        Intent intent = new Intent(EditContact.this,MainActivity.class);
        intent.putExtra("editNombre",nombre);
        intent.putExtra("editApellido",apellido);
        intent.putExtra("editCorreo",direccion);
        intent.putExtra("editDireccion",correo);
        intent.putExtra("editTelefono",telefono);
        startActivity(intent);
    }

    //CARGAR FOTO
    public void Edit_photo(View view) {
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
