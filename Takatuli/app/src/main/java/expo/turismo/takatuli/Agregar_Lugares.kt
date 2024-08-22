package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbAgregarLugaresTuristicos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class Agregar_Lugares : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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
        val spTipoLugar = root.findViewById<Spinner>(R.id.spTipoLugar)
        val imgAtrasLugarTuristico = root.findViewById<ImageView>(R.id.idActionATL)

        fun obtenerLugares(): List<tbAgregarLugaresTuristicos> {
            val objConexion = ClaseConexion().cadenaConexion()

            //Creo un Statement que me ejecutará el select
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from tbTipoLugarTuristirco")!!

            val ListadoLugares = mutableListOf<tbAgregarLugaresTuristicos>()

            while (resultSet.next()) {
                val uuid = resultSet.getString("UUUID_TipoLugarTuristico")
                val nombreLugar = resultSet.getString("NombreTipo")
               // val detalleLugar = resultSet.getString("DetalleTipo")
                val listadoLugaresCompleto = tbAgregarLugaresTuristicos(uuid, nombreLugar, "")
                ListadoLugares.add(listadoLugaresCompleto)
                println("asdfasdf $ListadoLugares")
            }
            return ListadoLugares
        }

        btnGuardar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val objConexion = ClaseConexion().cadenaConexion()
                val tipoLugarTuristico = obtenerLugares()
                val addAgregarLugares =
                    objConexion?.prepareStatement("insert into tbLugarTuristico(UUID_LugarTuristico,Nombre_LugarTuristico, Detalles_Lugar_Turistico, UUUID_TipoLugarTuristico )values(?, ?, ?, ?)")!!
                addAgregarLugares.setString(1, UUID.randomUUID().toString())
                addAgregarLugares.setString(2, txtNombreLugar.text.toString())
                addAgregarLugares.setString(3, txtDetalleLugar.text.toString())
                addAgregarLugares.setString(4, tipoLugarTuristico[spTipoLugar.selectedItemPosition].UUID_LugarTuristico)
                addAgregarLugares.executeUpdate()


            }
            // Inflate the layout for this fragment

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
                 spTipoLugar.adapter = miAdaptador
            }

        }

        imgAtrasLugarTuristico.setOnClickListener(){
            findNavController().navigate(R.id.idActionATL)
        }

        return root

    }

}


