package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
 * Use the [Agregar_Lugares.newInstance] factory method to
 * create an instance of this fragment.
 */
class Agregar_Lugares : Fragment() {
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
        val root = inflater.inflate(R.layout.fragment_agregar__lugares, container, false)

        val txtNombreLugar = root.findViewById<TextView>(R.id.txtNombreLugar)
        val txtDetalleLugar = root.findViewById<TextView>(R.id.txtDetalleLugar)
        val btnGuardar = root.findViewById<Button>(R.id.btnGuardar)
        val btnCancelar = root.findViewById<Button>(R.id.btnCancelar)

        fun tbLugarTuristico(
            UUID_LugarTuristico: String?,
            NombreLugarTuristico: String?
        ) {


            btnGuardar.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {

                    val objConexion = ClaseConexion().cadenaConexion()
                    val addAgregarLugares =
                        objConexion?.prepareStatement("insert into tbLugarTuristico(uuid_LugarTuristico,Nombre_LugarTuristico, Detalles_Lugar_Turistico)values(?, ?, ?)")!!
                    addAgregarLugares.setString(1, UUID.randomUUID().toString())
                    addAgregarLugares.setString(2, txtNombreLugar.text.toString())
                    addAgregarLugares.setString(3, txtDetalleLugar.text.toString())
                    addAgregarLugares.executeUpdate()


                }
                // Inflate the layout for this fragment

            }


        }
        return root
    }
}


