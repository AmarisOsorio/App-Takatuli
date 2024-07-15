package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fragment_seleccion : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_seleccion, container, false)
        val btnLugarTuristicoSL = root.findViewById<Button>(R.id.btnLugarTuristicoSL)
        val btnHospedajeSL = root.findViewById<Button>(R.id.btnHospedajeSL)
        val btnRestaurantesSL = root.findViewById<Button>(R.id.btnRestaurantesSL)


        btnHospedajeSL.setOnClickListener(){
            findNavController().navigate(R.id.idActionH)
        }
        btnRestaurantesSL.setOnClickListener(){
            findNavController().navigate(R.id.idActionR)
        }
        return root

    }


}