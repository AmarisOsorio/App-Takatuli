package expo.turismo.takatuli

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Path
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast


class fragment_fotoperfil : Fragment() {

    lateinit var imageView: ImageView
    lateinit var miPath: String

    val codigoGaleria = 102
    val codigoTomarFoto = 103
    val Camera_request_code = 0
    val Storage_request_code = 1

    //val uuid = UUID.randomUUID().toString()

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
        val btnGaleria = root.findViewById<Button>(R.id.btnGaleria)
        val btnTomarFoto = root.findViewById<Button>(R.id.btnTomarFoto)

        btnGaleria.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, codigoGaleria)
        }



     return root
    }



}