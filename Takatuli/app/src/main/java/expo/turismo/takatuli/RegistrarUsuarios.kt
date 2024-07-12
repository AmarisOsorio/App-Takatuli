package expo.turismo.takatuli

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.dataclassRoles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class RegistrarUsuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasena)
        val txtEdad = findViewById<EditText>(R.id.txtEdad)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefono)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val spRol = findViewById<Spinner>(R.id.spRol)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)


        fun obtenerRoles(): List<dataclassRoles> {


            val objConexion = ClaseConexion().cadenaConexion()


            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * from tbRol")!!
            val listadoRoles = mutableListOf<dataclassRoles>()

            while (resultSet.next()) {
                val uuid = resultSet.getString("UUID_Rol ")
                val nombreROL = resultSet.getString("Nombre_Rol ")
                val ROLCompleto = dataclassRoles(uuid, nombreROL)
                listadoRoles.add(ROLCompleto)
            }
            return listadoRoles

        }


        btnRegistrar.setOnClickListener {

            GlobalScope.launch (Dispatchers.IO) {

                val objConexion = ClaseConexion().cadenaConexion()

                val roles = obtenerRoles()

                val crearUsuario = objConexion?.prepareStatement("Insert into tbUsuario (UUID_Usuario, Nombre_Usuario , Password_Usuario , Edad_Usuario, Telefono_Usuario ,Correo_Usuario , DUI_Usuario , UUID_Rol ) values (?,?,?,?,?,?,?,?)")!!
                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2, txtNombre.text.toString())
                crearUsuario.setString(3, txtContrasena.text.toString())
                crearUsuario.setInt(4, txtEdad.text.toString().toInt())
                crearUsuario.setString(5, txtTelefono.text.toString())
                crearUsuario.setString(6,txtCorreo.text.toString())
                crearUsuario.setString(7, roles[spRol.selectedItemPosition].Nombre_Rol)

            }



        }





        CoroutineScope(Dispatchers.IO).launch {
            //1. Obtener los datos
            val verListaRol = obtenerRoles()
            val nombreRol = verListaRol.map { it.Nombre_Rol  }

            withContext(Dispatchers.Main) {
                //2. Crear y modificar el adaptador
                val Adaptador = ArrayAdapter(
                    this@RegistrarUsuarios,
                    android.R.layout.simple_spinner_dropdown_item,
                    nombreRol
                )
                spRol.adapter = Adaptador
            }
        }

    }
    }
