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
import expo.turismo.takatuli.Modelo.dataclassRoles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.util.UUID

class RegistrarUsuarios : AppCompatActivity() {


    /*companion object variablesGlobales{
        var correo = " "
         var nombreUser = " "
        var telefono = " "
       var contrasena = " "
        var edad = 0
       var fotoPerfil = " "
    }*/





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombreR)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaR)
        val txtEdad = findViewById<EditText>(R.id.txtEdadR)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefonoR)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreoR)
        val txtDui = findViewById<EditText>(R.id.txtDuiR)
        //val spRol = findViewById<Spinner>(R.id.spRolR)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)


        fun obtenerRoles(): List<dataclassRoles> {


            val objConexion = ClaseConexion().cadenaConexion()


            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from tbRol")!!
            val listadoRoles = mutableListOf<dataclassRoles>()

            while (resultSet.next()) {
                val uuid = resultSet.getString("UUID_Rol")
                val nombreROL = resultSet.getString("Nombre_Rol")
                val ROLCompleto = dataclassRoles(uuid, nombreROL)
                listadoRoles.add(ROLCompleto)
            }
            return listadoRoles

        }


        fun hashSHA256(contraseniaEscrita: String): String{
            val bytes = MessageDigest.getInstance("SHA-256").digest(contraseniaEscrita.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }


        btnRegistrar.setOnClickListener {

            GlobalScope.launch (Dispatchers.IO) {

                val objConexion = ClaseConexion().cadenaConexion()

                val contrasenaEncriptada = hashSHA256(txtContrasena.text.toString())

                val roles = obtenerRoles()

                val crearUsuario =
                    objConexion?.prepareStatement("Insert into tbUsuario(UUID_Usuario, Nombre_Usuario, Password_Usuario, Edad_Usuario, Telefono_Usuario,Fotos_usuario,Correo_Usuario, DUI_Usuario, UUID_Rol) values (?,?,?,?,?,?,?,?,?)")!!
                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2, txtNombre.text.toString())
                crearUsuario.setString(3, contrasenaEncriptada)
                crearUsuario.setInt(4, txtEdad.text.toString().toInt())
                crearUsuario.setString(5, txtTelefono.text.toString())
                crearUsuario.setString(6, "https://cdn-icons-png.freepik.com/512/8742/8742495.png")
                crearUsuario.setString(7, txtCorreo.text.toString())
                crearUsuario.setString(8, txtDui.text.toString())
                crearUsuario.setInt(9, 2)


                /*correo = txtCorreo.text.toString()
                nombreUser = txtNombre.text.toString()
                edad = txtEdad.text.toString().toInt()
                contrasena = txtContrasena.text.toString()
                telefono = txtTelefono.text.toString()*/

                crearUsuario.executeQuery()

                withContext(Dispatchers.Main) {

                    Toast.makeText(this@RegistrarUsuarios, "Usuario Creado", Toast.LENGTH_SHORT).show()
                    txtNombre.setText("")
                    txtContrasena.setText("")
                    txtEdad.setText("")
                    txtTelefono.setText("")
                    txtCorreo.setText("")
                    txtDui.setText("")
                }

            }


        }


        /*CoroutineScope(Dispatchers.IO).launch {
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
        }*/

    }
}
