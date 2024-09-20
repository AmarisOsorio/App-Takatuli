package expo.turismo.takatuli

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbLugarTuristico
import expo.turismo.takatuli.Modelo.tbRestaurante2
import expo.turismo.takatuli.RecyclerViewMostrar.Adaptador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class activity_rcvRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rcv_restaurante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rcvRestaurantes = findViewById<RecyclerView>(R.id.rcvRestaurantes)
        rcvRestaurantes.layoutManager = LinearLayoutManager(this)

        fun obtenerRestaurante(): List<tbRestaurante2> {
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("Select * from tbRestaurante2")!!

            val listaRestaurante = mutableListOf<tbRestaurante2>()

            while (resulset.next()) {
                val UUID_Restaurante = resulset.getString("UUID_Restaurante")
                val Nombre_Restaurante = resulset.getString("Nombre_Restaurante")
                val Menu_Restaurante = resulset.getString("Menu_Restaurante")
                val Foto_Menu = resulset.getString("Foto_Menu")
                val Fotos_Restaurante = resulset.getString("Fotos_Restaurante")

                val valoresJuntos2 = tbLugarTuristico(
                    UUID_Restaurante,
                    Nombre_Restaurante,
                    Menu_Restaurante,
                    Foto_Menu,
                    Fotos_Restaurante
                )

                //listaRestaurante.add(valoresJuntos2)
            }

            return listaRestaurante
        }

        GlobalScope.launch(Dispatchers.IO) {
            val TakatuliBD2 = obtenerRestaurante()
            withContext(Dispatchers.Main) {
             //   val adapter = expo.turismo.takatuli.RecyclerViewResta.Adaptador(TakatuliBD2)
              //  rcvRestaurantes.adapter = adapter
            }
        }
    }
}