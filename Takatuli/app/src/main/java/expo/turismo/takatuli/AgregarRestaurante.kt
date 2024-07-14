package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import expo.turismo.takatuli.Modelo.ClaseConexion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarRestaurante.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarRestaurante : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_agregar_restaurante, container, false)
        val txtNombreRestaurante = root.findViewById<EditText>(R.id.txtNombreRestaurante)
        val txtMenuEscrito= root.findViewById<EditText>(R.id.txtMenuEscrito)
        val btnAgregarRestaurante = root.findViewById<Button>(R.id.btnAgregarRestaurante)
        val btnCancelarRestaurante = root.findViewById<Button>(R.id.btnCancelarRestaurante)

        btnAgregarRestaurante.setOnClickListener{
        CoroutineScope(Dispatchers.IO).launch {
            val objConexion = ClaseConexion().cadenaConexion()

             val RestauranteAgregado = objConexion?.prepareStatement("insert into tbRestaurantes (UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante)Values (?, ?, ?)")!!
           RestauranteAgregado.setString(1, UUID.randomUUID().toString())
            RestauranteAgregado.setString(2, txtNombreRestaurante.text.toString())
            RestauranteAgregado.setString(3,txtMenuEscrito.text.toString())
            RestauranteAgregado.executeUpdate()
        }

        }
        return root




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AgregarRestaurante.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                AgregarRestaurante().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}