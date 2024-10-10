package expo.turismo.takatuli

import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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


    companion object variablesGlobales{
        lateinit var correo : String
        lateinit var nombreUser : String
        lateinit var telefono : String
        lateinit var contrasena : String
        lateinit var DUI : String
        var edad = 0
    }





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
        val imgVerContraR = findViewById<ImageView>(R.id.imgVerContraR)


        // Variable para verificar si hay errores
        //La inicializamos en false
        var hayErrores = false


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


        fun hashSHA256(contraseniaEscrita: String): String {
            val bytes =
                MessageDigest.getInstance("SHA-256").digest(contraseniaEscrita.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }


        btnRegistrar.setOnClickListener {



            GlobalScope.launch(Dispatchers.IO) {

                val objConexion = ClaseConexion().cadenaConexion()

                val contrasenaEncriptada = hashSHA256(txtContrasena.text.toString())

                val roles = obtenerRoles()

                val crearUsuario =
                    objConexion?.prepareStatement("Insert into tbUsuario(UUID_Usuario, Nombre_Usuario, Password_Usuario, Edad_Usuario, Telefono_Usuario,Correo_Usuario, DUI_Usuario, UUID_Rol) values (?,?,?,?,?,?,?,?)")!!
                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2, txtNombre.text.toString())
                crearUsuario.setString(3, contrasenaEncriptada)
                crearUsuario.setInt(4, txtEdad.text.toString().toInt())
                crearUsuario.setString(5, txtTelefono.text.toString())
                crearUsuario.setString(6, txtCorreo.text.toString())
                crearUsuario.setString(7, txtDui.text.toString())
                crearUsuario.setInt(8, 2)


                correo = txtCorreo.text.toString()
                nombreUser = txtNombre.text.toString()
                edad = txtEdad.text.toString().toInt()
                contrasena = txtContrasena.text.toString()
                telefono = txtTelefono.text.toString()
                DUI = txtDui.text.toString()

                crearUsuario.executeQuery()

                withContext(Dispatchers.Main) {

                    Toast.makeText(this@RegistrarUsuarios, "Usuario Creado", Toast.LENGTH_SHORT)
                        .show()
                    txtNombre.setText("")
                    txtContrasena.setText("")
                    txtEdad.setText("")
                    txtTelefono.setText("")
                    txtCorreo.setText("")
                    txtDui.setText("")
                }

            }


           //TODO://///////////////  Validaciones //////////////

            //TODO: 1- Validar que los campos no estén vacíos
            if (nombreUser.isEmpty()) {
                txtNombre.error = "El nombre es obligatorio"
                hayErrores = true
            } else {
                txtNombre.error = null
            }
            if (correo.isEmpty()) {
                txtCorreo.error = "El correo es obligatorio"
                hayErrores = true
            } else {
                txtCorreo.error = null
            }

            /*if (edad.isEmpty()) {
                txtEdad.error = "La edad es obligatoria"
                hayErrores = true
            } else {
                txtEdad.error = null
            }*/

            if (telefono.isEmpty()) {
                txtTelefono.error = "El teléfono es obligatorio"
                hayErrores = true
            } else {
                txtTelefono.error = null
            }

            /*if (DUI.isEmpty()) {
                txtDUI.error = "El DUI es obligatorio"
                hayErrores = true
            } else {
                txtDUI.error = null
            }

            if (contrasenia.isEmpty()) {
                txtContrasenia.error = "La contraseña es obligatoria"
                hayErrores = true
            } else {
                txtContrasenia.error = null
            }*/

            ///////////////////Validación DUI ///////////////////
            if (!DUI.matches(Regex("[0-9]{8}-[0-9]"))) {
                txtDui.error = "El DUI no tiene un formato válido. Ej: 12345678-9"
                hayErrores = true
            } else {
                txtDui.error = null
            }





        }






//TODO:///////////////////////////// boton ver contraseña //////////////////////////////////////////

        imgVerContraR.setOnClickListener {
            if (txtContrasena.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                txtContrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtContrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
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
