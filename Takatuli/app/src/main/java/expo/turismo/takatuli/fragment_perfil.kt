package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView


import androidx.navigation.fragment.findNavController


class fragment_perfil : Fragment() {


    /*lateinit var imageView: ImageView
    lateinit var miPath: Path

    val codigoGaleria = 102
    val codigoTomarFoto = 103
    val Camera_request_code = 0
    val Storage_request_code = 1
    val uuid = UUID.randomUUID().toString()*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    /*private var _binding:FragmentPerfilBinding? = null
    private val binding get() = _binding!!*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fotoPerfil = fragment_fotoperfil.variablesGlobales.uuid;

        //variable root
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)

        //Boton para navegar entre fragments
        val imgCamara = root.findViewById<ImageView>(R.id.imgCamera)
        //val imgFotoMostrar = root.findViewById<ImageView>(R.id.imgFotoperfilMostrar)

        imgCamara.setOnClickListener(){
            findNavController().navigate(R.id.idAccion)
        }




















            // Inflate the layout for this fragment
            /* return inflater.inflate(R.layout.fragment_perfil, container, false)
        val root : View = binding.root*/

            /*imageView = root.findViewById(R.id.imgPerfil)
        val btnGaleria = root.findViewById<Button>(R.id.btnGaleria)
        val btnFoto = root.findViewById<Button>(R.id.btnTomarFoto)*/


            // btnGaleria.setOnClickListener {
            //checkStoragePermission()
            //val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            //intent.type = "image/*"
            //startActivityForResult(intent,codigoGaleria)
            //}


        /*private fun checkStoragePermission(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            pedirPermisoStorage()
        } else{

        }
    }*/

        /*private fun pedirPermisoStorage(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this@fragment_perfil , android.Manifest.permission.READ_EXTERNAL_STORAGE)){

        }else {
            ActivityCompat.requestPermissions(requireContext(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),Storage_request_code)
        }
    }*/


        return root

    }
}