package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.dataclassRoles
import expo.turismo.takatuli.Modelo.tbAgregarLugaresTuristicos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PaquetesDestinos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val root = inflater.inflate(R.layout.fragment_paquetes_destinos, container, false)
        val txtNombrePaquetes= root.findViewById<EditText>(R.id.txtNombrePaquete)
        val spLugarPaquete= root.findViewById<Spinner>(R.id.spLugarPaquete)
            val txtDescripcionPaquete = root.findViewById<EditText>(R.id.txtDescripcionPaquete)
        val  txtPrecioTransporte = root.findViewById<EditText>(R.id.txtPrecioTransporte)
       val txtPrecioentrada = root.findViewById<EditText>(R.id.txtPreciodeEntrada)
        val btnAgregarPaquete = root.findViewById<Button>(R.id.btnAgregarPaquete)
        val btnCancelarPaquete = root.findViewById<Button>(R.id.btnCancelarPaquete)



        fun obtenerLugares(): List<tbAgregarLugaresTuristicos> {


            val objConexion = ClaseConexion().cadenaConexion()


            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from tbLugarTuristico")!!
            val ListadoLugaresTuristicos = mutableListOf<tbAgregarLugaresTuristicos>()

            while (resultSet.next()) {
                val uuid = resultSet.getString("UUID_LugarTuristico")
                val Nombre_LugarTuris = resultSet.getString("Nombre_LugarTuristico")
                val NombreLugar = tbAgregarLugaresTuristicos(uuid, Nombre_LugarTuris)
                ListadoLugaresTuristicos.add(NombreLugar)
            }
            return ListadoLugaresTuristicos

        }




        CoroutineScope(Dispatchers.IO).launch {
            //1. Obtener los datos
            val verListaLugares = obtenerLugares()
            val nombreLugarTuristico = verListaLugares.map { it.Nombre_Rol  }

            withContext(Dispatchers.Main) {
                //2. Crear y modificar el adaptador
                val Adaptador = ArrayAdapter(
                    this@PaquetesDestinos,
                    android.R.layout.simple_spinner_dropdown_item,
                    nombreLugarTuristico
                )
                spLugarPaquete.adapter = Adaptador
            }
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaquetesDestinos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaquetesDestinos().apply {
                arguments = Bundle().apply {

                }
            }
    }
}