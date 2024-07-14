package expo.turismo.takatuli

import RestaurantesHelper.AdaptadorRestaurantes
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.SubirRestaurante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [subidosdestinosdueno.newInstance] factory method to
 * create an instance of this fragment.
 */
class subidosdestinosdueno : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_subidosdestinosdueno, container, false)
        val rcvRestaurantes = root.findViewById<RecyclerView>(R.id.rcvRestaurantes)

        //Asignar un layout al ReciclerView
        rcvRestaurantes.layoutManager = LinearLayoutManager(context)


        //Funcion para obtener datos
        fun obtenerDatos(): List<SubirRestaurante>{
            val objConexion = ClaseConexion ().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("select * from tbRestaurante")!!
            val Restaurante = mutableListOf<SubirRestaurante>()
            while (resulset.next()){
                val UUID_Restaurante= resulset.getString("UUID_Restaurante")
                val NombreRestaurante= resulset.getString("Nombre_Restaurante")
                val Menu_Restaurantee = resulset.getString("Menu_Restaurante")

                val Restaurantes= SubirRestaurante(UUID_Restaurante, NombreRestaurante , Menu_Restaurantee )
                Restaurante.add(Restaurantes)
            }
            return Restaurante
        }

        //asignar un adaptador
        CoroutineScope(Dispatchers.IO).launch {
            val RestaurantesDB = obtenerDatos()
            withContext(Dispatchers.Main){
                val miAdapter = AdaptadorRestaurantes(RestaurantesDB)
                rcvRestaurantes.adapter = miAdapter
            }
        }

        return  root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment subidosdestinosdueno.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            subidosdestinosdueno().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}