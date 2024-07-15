package expo.turismo.takatuli

<<<<<<< HEAD
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
=======
import android.content.Intent
>>>>>>> a86306b049036ba3215683cb52b860ec8d11afd6
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
<<<<<<< HEAD
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import expo.turismo.takatuli.databinding.FragmentPerfilBinding
import java.nio.file.Path
import java.util.UUID
=======
import androidx.navigation.fragment.findNavController
>>>>>>> a86306b049036ba3215683cb52b860ec8d11afd6

class fragment_perfil : Fragment() {
<<<<<<< HEAD
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var imageView: ImageView
    lateinit var miPath: Path

    val codigoGaleria = 102
    val codigoTomarFoto = 103
    val Camera_request_code = 0
    val Storage_request_code = 1
    val uuid = UUID.randomUUID().toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }
    private var _binding:FragmentPerfilBinding? = null
    private val binding get() = _binding!!
=======
>>>>>>> a86306b049036ba3215683cb52b860ec8d11afd6

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        return inflater.inflate(R.layout.fragment_perfil, container, false)
        val root : View = binding.root

        imageView = root.findViewById(R.id.imgPerfil)
        val btnGaleria = root.findViewById<Button>(R.id.btnGaleria)
        val btnFoto = root.findViewById<Button>(R.id.btnTomarFoto)


        btnGaleria.setOnClickListener {
            checkStoragePermission()
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.type = "image/*"
            startActivityForResult(intent,codigoGaleria)
        }



        return root
    }


    private fun checkStoragePermission(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            pedirPermisoStorage()
        } else{

        }
    }

    private fun pedirPermisoStorage(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this@fragment_perfil , android.Manifest.permission.READ_EXTERNAL_STORAGE)){

        }else {
            ActivityCompat.requestPermissions(requireContext(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),Storage_request_code)
        }
    }









    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_perfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_perfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
=======
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)
        val btnSubidos = root.findViewById<Button>(R.id.btnSubidos)

        btnSubidos.setOnClickListener {
            findNavController().navigate(R.id.idAction)
        }

        return root

    }


>>>>>>> a86306b049036ba3215683cb52b860ec8d11afd6
}