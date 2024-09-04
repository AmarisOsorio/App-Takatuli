package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbDestinos
import expo.turismo.takatuli.RecyclerViewMostrar.Adaptador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


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

        val rcvDestinos = root.findViewById<RecyclerView>(R.id.rcvDestinos)
        rcvDestinos.layoutManager = LinearLayoutManager(context)

        fun obtenerPeliculas(): List<tbDestinos> {
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("Select * from tbDestinos")!!

            val listaDestinos = mutableListOf<tbDestinos>()

            while (resulset.next()) {
                val UUID_Destinos = resulset.getString("UUID_Destinos")
                val UUID_LugarTuristico = resulset.getString("UUID_LugarTuristico")
                val UUID_Hospedaje = resulset.getString("UUID_Hospedaje")
                val UUID_Restaurante = resulset.getString("UUID_Restaurante")
                val UUID_Ubicacion = resulset.getString("UUID_Ubicacion")

                val valoresJuntos = tbDestinos(
                    UUID_Destinos,
                    UUID_LugarTuristico,
                    UUID_Hospedaje,
                    UUID_Restaurante,
                    UUID_Ubicacion
                )

                listaDestinos.add(valoresJuntos)
            }
            return listaDestinos
        }
        
        coroutineScope(Dispatchers.IO).launch {
            val peliculasBD = obtenerPeliculas()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(peliculasBD)
                rcvDestinos.adapter=adapter
            }

        }








        return root


    }


}