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


    companion object variablesGlobales{
        lateinit var correo : String
        lateinit var nombreUser : String
        lateinit var telefono : String
        lateinit var contrasena : String
        var edad = 0
        lateinit var fotoPerfil : String
    }

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
        val UUID = fragment_fotoperfil.variablesGlobales.uuid;

        //variable root
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)

        //mandó a traer el valor que es global
        /*val nombreGlobal = nombreUser
        val correoGlobal = correo
        val telefonoGlobal = telefono
        val edadGlobal = edad
        val fotoGlobal = fotoPerfil*/


        //Mando a traer el textview que está en la pantalla
        var nombreUserT = root.findViewById<TextView>(R.id.txvNombre)
        //asignamos el valor global al textview
        nombreUserT.text = nombreUser.toString()

        val correoUser = root.findViewById<TextView>(R.id.txvCorreo)
        correoUser.text = correo

        val telefonoUser = root.findViewById<TextView>(R.id.txvTelefono)
        telefonoUser.text = telefono

        val edadUser = root.findViewById<TextView>(R.id.txvEdad)
        edadUser.text = edad.toString()








        //Boton para navegar entre fragments

                val campoCorreo = root.findViewById<TextView>(R.id.textView5)
                val campoUsuario = root.findViewById<TextView>(R.id.txvNombre)
                val campoTelefono = root.findViewById<TextView>(R.id.textView7)
                val campoEdad = root.findViewById<TextView>(R.id.txvEdad)
                val campoFotoPerfil = root.findViewById<ImageView>(R.id.imgFotoperfilMostrar)
                val campoContra = root.findViewById<TextView>(R.id.txtContra)


               /* fun obtenerDatos(): ArrayList<String> {
                    lateinit var datos : ArrayList<String>
                    val objConexion = ClaseConexion().cadenaConexion()
                    val getData = objConexion?.prepareStatement("select * from tbUsuario where nombre_usuario = ?")!!
                    getData.setString(1, Login.nombreusuario)
                    val rs = getData.executeQuery()
                    while(rs.next()){

                    RegistrarUsuarios.nombreUser =  rs.getString("nombre_usuario")
                        RegistrarUsuarios.correo = rs.getString("correo_usuario")
                        RegistrarUsuarios.edad = rs.getInt("edad_usuario")
                        RegistrarUsuarios.telefono = rs.getString("telefono_usuario")
                        RegistrarUsuarios.contrasena = rs.getString("Password_Usuario")
                        RegistrarUsuarios.fotoPerfil = rs.getString("Fotos_usuario")
                    }
                    return datos;
                }*/


        fun obtenerDatos():ArrayList<String>  {
            try {
                GlobalScope.launch(Dispatchers.IO){
                    lateinit var datos : ArrayList<String>
                    val objConexion = ClaseConexion().cadenaConexion()
                    val getData = objConexion?.prepareStatement("select * from tbUsuario where nombre_usuario = ?")!!
                    getData.setString(1, Login.nombreusuario)
                    val rs = getData.executeQuery()
                    while(rs.next()){

                        nombreUser =  rs.getString("nombre_usuario")
                        correo = rs.getString("correo_usuario")
                        edad = rs.getInt("edad_usuario")
                        telefono = rs.getString("telefono_usuario")
                        contrasena = rs.getString("Password_Usuario")
                        fotoPerfil = rs.getString("Fotos_usuario")

                        arrayOf(nombreUser,correo, edad, telefono, contrasena, fotoPerfil)

                    }

                }
            }catch (e:SQLException){
                println("Este es el Error: $e")
            }

            //Cambiar a datos
            return obtenerDatos()
        }

                CoroutineScope(Dispatchers.IO).launch {
                    var datos = obtenerDatos()
                    withContext(Dispatchers.Main){
                        campoCorreo.setText(datos.get(1))
                        campoUsuario.setText(datos.get(0))
                        campoTelefono.setText(datos.get(3))
                        campoEdad.setText(datos.get(2))
                        campoContra.setText(datos.get(4))
                        Glide.with(requireContext()).load(fotoPerfil).into(campoFotoPerfil)

                    }
                }




        val imgCamara = root.findViewById<ImageView>(R.id.imgCamera)
        //val imgFotoMostrar = root.findViewById<ImageView>(R.id.imgFotoperfilMostrar)

        imgCamara.setOnClickListener(){
            val intent = Intent(requireContext(),foto_perfil::class.java)
            startActivity(intent)
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