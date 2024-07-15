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

import androidx.navigation.fragment.findNavController

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

        }


    }

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)
        val btnSubidos = root.findViewById<Button>(R.id.btnSubidos)

        //btnSubidos.setOnClickListener {
           // findNavController().navigate(R.id.idAction)
        //}

        return root

    }
}

