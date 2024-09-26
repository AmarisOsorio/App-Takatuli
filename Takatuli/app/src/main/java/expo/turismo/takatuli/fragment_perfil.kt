package expo.turismo.takatuli


import RecyclerViewHelper.AdaptadorUsuario
import android.content.Intent

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
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tb_Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.ResultSet


class fragment_perfil : Fragment() {


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

        val fotoPerfil = fragment_fotoperfil.variablesGlobales.uuid;

        //variable root
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)

        //mandó a traer el valor que es global
        val nombreGlobal = RegistrarUsuarios.variablesGlobales.nombreUser
        val correoGlobal = RegistrarUsuarios.variablesGlobales.correo
        val telefonoGlobal = RegistrarUsuarios.variablesGlobales.telefono
        val edadGlobal = RegistrarUsuarios.variablesGlobales.edad


        //Mando a traer el textview que está en la pantalla
        val nombreUser = root.findViewById<TextView>(R.id.txvNombre)
        //asignamos el valor global al textview
        nombreUser.text = nombreGlobal

        val correoUser = root.findViewById<TextView>(R.id.txvCorreo)
        correoUser.text = correoGlobal

        val telefonoUser = root.findViewById<TextView>(R.id.txvTelefono)
        telefonoUser.text = telefonoGlobal

        val edadUser = root.findViewById<TextView>(R.id.txvEdad)
        edadUser.text = edadGlobal.toString()








        //Boton para navegar entre fragments

        /*val campoCorreo = root.findViewById<TextView>(R.id.textView5)
        val campoUsuario = root.findViewById<TextView>(R.id.txvNombre)
        val campoTelefono = root.findViewById<TextView>(R.id.textView7)
        val campoEdad = root.findViewById<TextView>(R.id.txvEdad)


        fun obtenerDatos(): ArrayList<String> {
            lateinit var datos : ArrayList<String>
            val objConexion = ClaseConexion().cadenaConexion()
            val getData = objConexion?.prepareStatement("select * from tbUsuario where correo_usuario = ?")!!
            getData.setString(1, RegistrarUsuarios.correo)
            val rs = getData.executeQuery()
            while(rs.next()){

                datos.add(rs.getString("nombre_usuario"))
                datos.add(rs.getString("correo_usuario"))
                datos.add(rs.getInt("edad_usuario").toString())
                datos.add(rs.getString("telefono_usuario"))
            }
            return datos;
        }

        CoroutineScope(Dispatchers.IO).launch {
            var datos = obtenerDatos()
            withContext(Dispatchers.Main){
                campoCorreo.setText(datos.get(1))
                campoUsuario.setText(datos.get(0))
                campoTelefono.setText(datos.get(3))
                campoEdad.setText(datos.get(2))
            }
        }*/




        val imgCamara = root.findViewById<ImageView>(R.id.imgCamera)
        //val imgFotoMostrar = root.findViewById<ImageView>(R.id.imgFotoperfilMostrar)

        imgCamara.setOnClickListener(){
            val intent = Intent(requireContext(),Navigation::class.java)
            intent.putExtra("ir_a_fotoPerfil",true)
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