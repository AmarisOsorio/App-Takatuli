package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbLugarTuristico
import expo.turismo.takatuli.RecyclerViewMostrar.Adaptador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class fragment_menuP : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_menu_p, container, false)

        val imgHost = root.findViewById<ImageView>(R.id.imgHost)
        val imgResta = root.findViewById<ImageView>(R.id.imgResta)
        val imgLugarTuristico = root.findViewById<ImageView>(R.id.imgLugaTuristico)
        val imgCirHost = root.findViewById<ImageView>(R.id.imgCirHost)
        val imgCirResta = root.findViewById<ImageView>(R.id.imgCirResta)
        val imgCirLugarTuristico  = root.findViewById<ImageView>(R.id.imgLugarTuristico)



        val rcvDestinos = root.findViewById<RecyclerView>(R.id.rcvDestinos)
        rcvDestinos.layoutManager = LinearLayoutManager(requireContext())

        fun obtenerDestinos(): List<tbLugarTuristico> {
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("Select * from tbLugarTuristico")!!

            val listaDestinos = mutableListOf<tbLugarTuristico>()

            while (resulset.next()) {
                val UUID_LugarTuristico = resulset.getString("UUID_LugarTuristico")
                val Nombre_LugarTuristico = resulset.getString("Nombre_LugarTuristico")
                val Detalles_Lugar_Turistico = resulset.getString("Detalles_Lugar_Turistico")
                val Fotos_Lugar_Turistico = resulset.getString("Fotos_Lugar_Turistico")
                val UUID_TipoLugarTuristico = resulset.getString("UUID_TipoLugarTuristico")

                val valoresJuntos = tbLugarTuristico(
                    UUID_LugarTuristico,
                    Nombre_LugarTuristico,
                    Detalles_Lugar_Turistico,
                    Fotos_Lugar_Turistico,
                    UUID_TipoLugarTuristico
                )

                listaDestinos.add(valoresJuntos)
            }

            return listaDestinos
        }

        GlobalScope.launch(Dispatchers.IO) {
             val TakatuliBD4 = obtenerDestinos()
            withContext(Dispatchers.Main) {
                val adapter = Adaptador(TakatuliBD4)
                rcvDestinos.adapter = adapter
            }
        }


        return root

    }

}