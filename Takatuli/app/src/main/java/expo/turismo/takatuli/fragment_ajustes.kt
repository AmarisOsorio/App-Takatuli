package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class fragment_ajustes : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_ajustes, container, false)




        //val imgCerrarSesion = root.findViewById<ImageView>(R.id.imgCerrarSesion)

        /*imgCerrarSesion.setOnClickListener{
            val cerrarsesion = Intent(requireContext(), Login::class.java)
            startActivity(cerrarsesion)
        }*/

        return  root

    }


}