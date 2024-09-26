package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class fragment_mapa : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_mapa, container, false)

        val btnFragment_mapa = root.findViewById<Button>(R.id.btnFragment_mapa)
        btnFragment_mapa.setOnClickListener(){
            val intent = Intent(requireContext(), activity_maps::class.java)
            startActivity(intent)
        }

        return root
    }
}