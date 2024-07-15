package expo.turismo.takatuli

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.DataclassUsuarios
import expo.turismo.takatuli.Modelo.dataclassRoles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecuperacionDecontrasenia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperacion_decontrasenia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val NuevaContra = findViewById<EditText>(R.id.txtNuevaContra)
        val btnActuContra = findViewById<Button>(R.id.btnActuContra)
        val spUsuarios = findViewById<Spinner>(R.id.spUsuarios)



      fun obtenerUsuarios(): List<DataclassUsuarios> {

          val objConexion = ClaseConexion().cadenaConexion()

          val statement = objConexion?.createStatement()
          val resultSet = statement?.executeQuery("select * from tbUsuario")!!
          val listadoUsuarios = mutableListOf<DataclassUsuarios>()

          while (resultSet.next()) {
              val uuidUsua = resultSet.getString("UUID_Usuario")
              val nombreUsua = resultSet.getString("Nombre_Usuario")
              val UsuariosCompleto = DataclassUsuarios(uuidUsua, nombreUsua)
              listadoUsuarios.add(UsuariosCompleto)
          }
          return listadoUsuarios
      }


        btnActuContra.setOnClickListener {


            GlobalScope.launch (Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()

                val usuarios = obtenerUsuarios()

                val UpdateContra = objConexion?.prepareStatement("update tbUsuario set Password_Usuario = ? where UUID_Usuario = ?")!!
                UpdateContra.setString(1, NuevaContra.text.toString())
                UpdateContra.setString(2, usuarios[spUsuarios.selectedItemPosition].Nombre_Usuario)
                UpdateContra.executeUpdate()

                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecuperacionDecontrasenia, "Contraseña Actualizada", Toast.LENGTH_SHORT).show()

                }


            }

        }


        CoroutineScope(Dispatchers.IO).launch {
            //1. Obtener los datos
            val verListaUsua = obtenerUsuarios()
            val nombreRol = verListaUsua.map { it.Nombre_Usuario  }

            withContext(Dispatchers.Main) {
                //2. Crear y modificar el adaptador
                val Adaptador = ArrayAdapter(
                    this@RecuperacionDecontrasenia,
                    android.R.layout.simple_spinner_dropdown_item,
                    nombreRol
                )
                spUsuarios.adapter = Adaptador
            }
        }
    }
}