package expo.turismo.takatuli

import android.content.pm.PackageManager
import android.graphics.Path
import android.os.Bundle
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
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, codigoGaleria)
        }

        btnTomarFoto.setOnClickListener {
            checkCameraPermission()
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>, grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Camera_request_code -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //El permiso está aceptado, entonces Abrimos la camara:
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, codigoTomarFoto)
                } else {
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una alerta/Toast.
                    Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                }
                return
            }
            Storage_request_code -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //El permiso está aceptado, entonces Abrimos la galeria
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(intent, codigoGaleria)
                } else {
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una alerta/Toast.
                    Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
                // Este else lo dejamos por si sale un permiso que no teníamos controlado.
            }
        }




        return inflater.inflate(R.layout.fragment_fotoperfil, container, false)
    }



}