package expo.turismo.takatuli

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class foto_perfil : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var galeria: Button
    lateinit var subirImagen: Button
    companion object variableGlobalFoto{
        var fileUri: Uri? = null
    }
    //var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_foto_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //madar a llamar a todos los elementos
        imageView = findViewById(R.id.imgfotoPerfil)
        galeria = findViewById<Button>(R.id.btngaleria)
        subirImagen = findViewById<Button>(R.id.btnsubirimg)
        val imgAtrasFoto = findViewById<ImageView>(R.id.imgatras)

        imgAtrasFoto.setOnClickListener(){
            val intent = Intent(this, fragment_perfil::class.java)
            startActivity(intent)
        }

        galeria.setOnClickListener(){
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent,"Escoge una imagen"),0)
        }
        subirImagen.setOnClickListener(){
            if(fileUri!=null){
                subirImagen()
            }else{
                Toast.makeText(this,"Seleccione una imagen", Toast.LENGTH_LONG).show()
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.data != null){
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,fileUri)
                imageView.setImageBitmap(bitmap)
            }catch (e: Exception){
                Log.e("Exception", "Este es el error: " +e)
            }
        }
    }
    fun subirImagen(){
        if (fileUri!= null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Upload Image...")
            progressDialog.setMessage("Procesando...")
            progressDialog.show()

            val ref: StorageReference = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString())
            ref.putFile(fileUri!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "El archivo se subio correctamente", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Ha ocurrido un error en la descarga...",Toast.LENGTH_LONG).show()
            }
        }
    }

}