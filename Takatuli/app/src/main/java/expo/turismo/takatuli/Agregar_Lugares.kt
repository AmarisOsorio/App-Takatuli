package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.android.gms.ads.mediation.Adapter
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbAgregarLugaresTuristicos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


        fun obtenerLugares(): List<tbAgregarLugaresTuristicos> {
            val objConexion = ClaseConexion().cadenaConexion()

            //Creo un Statement que me ejecutará el select
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from tbTipoLugarTuristirco")!!

            val ListadoLugares = mutableListOf<tbAgregarLugaresTuristicos>()

            while (resultSet.next()) {
                val uuid = resultSet.getString("UUUID_TipoLugarTuristico")
                val nombreLugar = resultSet.getString("NombreTipo")
                val detalleLugar = resultSet.getString("DetalleTipo")
                val listadoLugaresCompleto = tbAgregarLugaresTuristicos(uuid, nombreLugar, detalleLugar)
                ListadoLugares.add(listadoLugaresCompleto)
               println("asdfasdf $ListadoLugares")
            }
            return ListadoLugares
        }


        CoroutineScope(Dispatchers.IO).launch {
            //1- Obtengo los datos
            val listadoDeLugares = obtenerLugares()
            val nombreLugares = listadoDeLugares.map { it.NombreLugarTuristico }

            withContext(Dispatchers.Main) {
                //2- Crear y modificar el adaptador
                val miAdaptador = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    nombreLugares

                )
               val spTipoLugar = root.findViewById<Spinner>(R.id.spTipoLugar)
                 spTipoLugar.adapter = miAdaptador


            }

        }

        fun tbLugarTuristico(
            UUID_LugarTuristico: String?,
            NombreLugarTuristico: String?

        ) {


            btnGuardar.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {

                    val objConexion = ClaseConexion().cadenaConexion()
                    val addAgregarLugares =
                        objConexion?.prepareStatement("insert into tbLugarTuristico(UUID_LugarTuristico,Nombre_LugarTuristico, Detalles_Lugar_Turistico)values(?, ?, ?)")!!
                    addAgregarLugares.setString(1, UUID.randomUUID().toString())
                    addAgregarLugares.setString(2, txtNombreLugar.text.toString())
                    addAgregarLugares.setString(3, txtDetalleLugar.text.toString())
                    addAgregarLugares.executeUpdate()


                }
                // Inflate the layout for this fragment

            }


            val listadoLugares = arrayOf("Montaña","Playa","Bañario","Lago","Bosques")
            val miAdaptadordeLinea = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,listadoLugares)
            val spTipoLugar = root.findViewById<Spinner>(R.id.spTipoLugar)
            spTipoLugar.adapter = miAdaptadordeLinea
        }

        return root

    }

}


