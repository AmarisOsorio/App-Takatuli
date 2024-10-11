package expo.turismo.takatuli


import RecyclerViewHelper.AdaptadorUsuario
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import android.widget.TextView

import androidx.navigation.Navigation


import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tb_Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.ResultSet
import java.sql.SQLException
import java.util.UUID


class fragment_perfil : Fragment() {

    var UUID = ""
    var nombreUser = " "
    var correo = " "
    var contrasena = " "
    var telefono = " "
    var edad = 0
    var fotoPerfil = " "


    /*lateinit var imageView: ImageView
    lateinit var miPath: Path

    val codigoGaleria = 102
    val codigoTomarFoto = 103
    val Camera_request_code = 0
    val Storage_request_code = 1
    val uuid = UUID.randomUUID().toString()*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }
    /*private var _binding:FragmentPerfilBinding? = null
    private val binding get() = _binding!!*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //obtenerDatos()
        var UUID = fragment_fotoperfil.variablesGlobales.uuid;

        //variable root
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)

        //mandó a traer el valor que es global
        /*val nombreGlobal = nombreUser
        val correoGlobal = correo
        val telefonoGlobal = telefono
        val edadGlobal = edad
        val fotoGlobal = fotoPerfil*/




        //Boton para navegar entre fragments

        val campoCorreo = root.findViewById<TextView>(R.id.textView5)
        val campoUsuario = root.findViewById<TextView>(R.id.txvNombre)
        val campoTelefono = root.findViewById<TextView>(R.id.textView7)
        val campoEdad = root.findViewById<TextView>(R.id.txvEdad)
        val campoFotoPerfil = root.findViewById<ImageView>(R.id.imgFotoperfilMostrar)
        val campoContra = root.findViewById<TextView>(R.id.txtContra)

        val urlF = foto_perfil.variableGlobalFoto.fileUri



        suspend fun obtenerDatos(): List<tb_Usuario> {
            return withContext(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()
                val getData =
                    objConexion?.prepareStatement("SELECT * FROM tbUsuario WHERE nombre_usuario = ?")
                        ?: return@withContext emptyList()
                getData.setString(1, Login.nombreusuario)
                val rs = getData.executeQuery()
                val listaDatosUsuario = mutableListOf<tb_Usuario>()

                while (rs.next()) {
                    val UUID = rs.getString("UUID_usuario")
                    val nombreUser = rs.getString("nombre_usuario")
                    val correo = rs.getString("correo_usuario")
                    val edad = rs.getInt("edad_usuario")
                    val telefono = rs.getString("telefono_usuario")
                    val contrasena = rs.getString("Password_Usuario")
                    val fotoPerfil = rs.getString("Fotos_usuario")

                    val valoresJuntos =
                        tb_Usuario(UUID, nombreUser, contrasena,edad, telefono, correo,  fotoPerfil)
                    listaDatosUsuario.add(valoresJuntos)
                }
                listaDatosUsuario
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val datosUsuario = obtenerDatos()
            val usuario = datosUsuario[0]
            campoUsuario.text = usuario.Nombre_Usuario
            campoCorreo.setText(usuario.Correo_Usuario)
            campoEdad.text = usuario.Edad_Usuario.toString()
            campoTelefono.setText(usuario.Telefono_Usuario)
            campoContra.setText(usuario.Password_Usuario)
            Glide.with(requireContext()).load(usuario.foto).into(campoFotoPerfil)





        }


        val imgCamara = root.findViewById<ImageView>(R.id.imgCamera)
        //val imgFotoMostrar = root.findViewById<ImageView>(R.id.imgFotoperfilMostrar)

        imgCamara.setOnClickListener() {
            val intent = Intent(requireContext(), foto_perfil::class.java)
            startActivity(intent)
        }


        fun actualizarFoto(){

            val objConexion = ClaseConexion().cadenaConexion()

            val NuevaFoto = urlF

            val  UpdateIMG = objConexion?.prepareStatement("update tbUsuario set Fotos_Usuario = ? where UUID_Usuario = ?")!!
            UpdateIMG.setString(1, UUID)
            UpdateIMG.setString(2, NuevaFoto.toString())
            UpdateIMG.executeUpdate()

            val commit = objConexion?.prepareStatement("commit")!!
            commit.executeUpdate()
        }

        GlobalScope.launch(Dispatchers.IO) {
            val nuevaFoto = actualizarFoto()
            nuevaFoto.toString()

        }














        // Inflate the layout for this fragment
        /* return inflater.inflate(R.layout.fragment_perfil, container, false)
    val root : View = binding.root*/

        /*imageView = root.findViewById(R.id.imgPerfil)
    val btnGaleria = root.findViewById<Button>(R.id.btnGaleria)
    val btnFoto = root.findViewById<Button>(R.id.btnTomarFoto)*/


        // btnGaleria.setOnClickListener {
        //checkStoragePermission()
        //val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        //intent.type = "image/*"
        //startActivityForResult(intent,codigoGaleria)
        //}


        /*private fun checkStoragePermission(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            pedirPermisoStorage()
        } else{

        }
    }*/

        /*private fun pedirPermisoStorage(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this@fragment_perfil , android.Manifest.permission.READ_EXTERNAL_STORAGE)){

        }else {
            ActivityCompat.requestPermissions(requireContext(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),Storage_request_code)
        }
    }*/


        return root

    }


}