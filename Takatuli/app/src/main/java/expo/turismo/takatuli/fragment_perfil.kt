package expo.turismo.takatuli

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import expo.turismo.takatuli.databinding.FragmentPerfilBinding
import java.nio.file.Path
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_perfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_perfil : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
}