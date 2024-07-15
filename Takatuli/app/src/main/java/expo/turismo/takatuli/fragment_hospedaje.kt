package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.SubirRestaurante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class fragment_hospedaje : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_hospedaje, container, false)
        val btnAgregarHospedaje = root.findViewById<Button>(R.id.btnAgregarHospedaje)
        val txtNombreH = root.findViewById<EditText>(R.id.txtNombreH)
        val txtPrecioH = root.findViewById<EditText>(R.id.txtPrecioH)
        val txtDetallesH = root.findViewById<EditText>(R.id.txtDetallesH)


        btnAgregarHospedaje.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val claseConexion = ClaseConexion().cadenaConexion()

                val addHospedaje =
                    claseConexion?.prepareStatement("insert into tbHospedaje(UUID_Hospedaje,Nombre_Hospedaje,Precio_Hospedaje,Detalles_Hospedaje) values(?, ?, ?, ?)")!!
                addHospedaje.setString(1, UUID.randomUUID().toString())
                addHospedaje.setString(2, txtNombreH.text.toString())
                addHospedaje.setInt(3, txtPrecioH.text.toString().toInt())
                addHospedaje.setString(4, txtDetallesH.text.toString())
                addHospedaje.executeUpdate()

            }


        }






        return root

        }
}