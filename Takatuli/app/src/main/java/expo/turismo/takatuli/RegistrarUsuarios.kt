package expo.turismo.takatuli

import android.os.Bundle
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
        val imgVerContraR = findViewById<ImageView>(R.id.imgVerContraR)


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
            var hayErrores = false
            val Usuario = txtNombre.text.toString()
            val correo = txtCorreo.text.toString()
            val edad = txtEdad.text.toString()
            val telefono = txtTelefono.text.toString()
            val contrasena = txtContrasena.text.toString()
            val DUI = txtDui.text.toString()

            //TODO://///////////////  Validaciones //////////////

            //TODO: 1- Validar que los campos no estén vacíos
            if (Usuario.isEmpty()) {
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

            if (edad.isEmpty()) {
                txtEdad.error = "La edad es obligatoria"
                hayErrores = true
            } else {
                txtEdad.error = null
            }

            if (telefono.isEmpty()) {
                txtTelefono.error = "El teléfono es obligatorio"
                hayErrores = true
            } else {
                txtTelefono.error = null
            }

            if (DUI.isEmpty()) {
                txtDui.error = "El DUI es obligatorio"
                hayErrores = true
            } else {
                txtDui.error = null
            }

            if (contrasena.isEmpty()) {
                txtContrasena.error = "La contraseña es obligatoria"
                hayErrores = true
            } else {
                txtContrasena.error = null
            }

            // Valida que la contraseña tenga más de 7 dígitos
            if (contrasena.length <= 7) {
                txtContrasena.error = "La contraseña debe tener más de 7 caracteres"
                hayErrores = true
            } else {
                txtContrasena.error = null
            }

            ///////////////////Validación DUI ///////////////////
            if (!DUI.matches(Regex("[0-9]{8}-[0-9]"))) {
                txtDui.error = "El DUI no tiene un formato válido. Ej: 12345678-9"
                hayErrores = true
            } else {
                txtDui.error = null
            }

            //Validar que la edad solo contenga números
            if (!edad.matches(Regex("[0-9]+"))) {
                txtEdad.error = "La edad debe contener solo números"
                hayErrores = true
            } else if (edad.toInt() <= 0 || edad.toInt() > 110) {
                txtEdad.error = "Ingrese una edad valida"
                hayErrores = true
            } else {
                txtEdad.error = null
            }

            //Validar el corrreo
            if (!correo.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+[.][a-z]+"))) {
                txtCorreo.error = "El correo no tiene un formato válido"
                hayErrores = true
            } else {
                txtCorreo.error = null
            }

            GlobalScope.launch(Dispatchers.IO) {

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


        }

        //TODO:///////////////////////////// boton ver contraseña //////////////////////////////////////////

        /*imgVerContraR.setOnClickListener {
            if (txtContrasena.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                txtContrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtContrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }*/

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
