package expo.turismo.takatuli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecuperacionDeContrasena : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)


        btnEnviar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                enviarCorreo("${txtCorreo.text}", "Recuperación de contraseña", "Hola")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recuperacion_de_contrasena, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecuperacionDeContrasena().apply {
                arguments = Bundle().apply {

                }
            }
    }
}