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

        var btn1A = root.findViewById<Button>(R.id.btn1A)
        var btn2A = root.findViewById<Button>(R.id.btn2A)
        var btn3A = root.findViewById<Button>(R.id.btn3A)

        btn1A.setOnClickListener(){
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Explora")
            builder.setMessage("Conoce mucho más de este lugar...")
            builder.setPositiveButton("Aceptar"){
                dialog,which -> val pasarDetalle = Intent(requireContext(), activity_detalle::class.java)
                startActivity(pasarDetalle)
            }
            builder.setNegativeButton("Cancelar"){
                dialog, which ->
            }
            builder.show()

        }

        btn2A.setOnClickListener(){
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Explora")
            builder.setMessage("Conoce mucho más de este lugar...")
            builder.setPositiveButton("Aceptar"){
                    dialog,which -> val pasarDetalle = Intent(requireContext(), activity_detalle::class.java)
                startActivity(pasarDetalle)
            }
            builder.setNegativeButton("Cancelar"){
                    dialog, which ->
            }
            builder.show()

        }

        btn3A.setOnClickListener(){
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Explora")
            builder.setMessage("Conoce mucho más de este lugar...")
            builder.setPositiveButton("Aceptar"){
                    dialog,which -> val pasarDetalle = Intent(requireContext(), activity_detalle::class.java)
                startActivity(pasarDetalle)
            }
            builder.setNegativeButton("Cancelar"){
                    dialog, which ->
            }
            builder.show()

        }
        return root
    }
}