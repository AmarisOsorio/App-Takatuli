package expo.turismo.takatuli

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import expo.turismo.takatuli.Modelo.ClaseConexion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID


class fragment_fotoperfil : Fragment() {

    lateinit var imageView: ImageView
    lateinit var galeria: Button
    lateinit var subirImagen:Button
    var fileUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //variable root
        val root = inflater.inflate(R.layout.fragment_fotoperfil, container, false)

        //madar a llamar a todos los elementos
        imageView = root.findViewById(R.id.imgFotoPerfil)
        galeria = root.findViewById<Button>(R.id.btnGaleria)
        subirImagen = root.findViewById<Button>(R.id.btnSubirImg)
        val imgAtrasFoto = root.findViewById<ImageView>(R.id.imgAtrasFotoPerfil)


        imgAtrasFoto.setOnClickListener(){
            findNavController().navigate(R.id.idActionAFP)
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
                Toast.makeText(context?.applicationContext,"Seleccione una imagen", Toast.LENGTH_LONG).show()
            }

        }


        return root
    }

    //Esta función ayuda a seleccionar una imagen y la muestra en la image view
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.data != null){
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver,fileUri)
                imageView.setImageBitmap(bitmap)
            }catch (e: Exception){
                Log.e("Exception", "Este es el error: " +e)
            }
        }
    }

    //Funcion para que se pueda subir una imagen desde la galeria
    fun subirImagen(){
        if (fileUri!= null){
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setTitle("Upload Image...")
            progressDialog.setMessage("Procesando...")
            progressDialog.show()

            val ref: StorageReference = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString())
            ref.putFile(fileUri!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(context?.applicationContext, "El archivo se subio correctamente", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(context?.applicationContext, "Ha ocurrido un error en la descarga...",Toast.LENGTH_LONG).show()
            }
        }
    }

    //Función para guardar la url de foto en oracle
    /* private fun guardarUsuarioConFoto(correo: String, clave: String, imageUri: String) {
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()
                val statement =
                    objConexion?.prepareStatement("INSERT INTO tbMisUsuarios (UUID, correo, contrasena, FotoURI) VALUES (?, ?, ?, ?)")!!
                statement.setString(1, uuid)
                statement.setString(2, correo)
                statement.setString(3, clave)
                statement.setString(4, imageUri)
                statement.executeUpdate()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Datos guardados", Toast.LENGTH_SHORT).show()
                    txtCorreo.text.clear()
                    txtClave.text.clear()
                    imageView.setImageResource(0)
                    imageView.tag = null
                }
            }
        } catch (e: SQLException) {
            println("Error al guardar usuario: $e")
        }
    }*/
}