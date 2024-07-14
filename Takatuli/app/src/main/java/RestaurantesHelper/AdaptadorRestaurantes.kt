package RestaurantesHelper

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.SubirRestaurante
import expo.turismo.takatuli.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdaptadorRestaurantes (private var Datos: List<SubirRestaurante>) : RecyclerView.Adapter<ViewHolderRestaurantes>() {


    

    fun ActualizarListaDespuesDeActualizarDatos(UUID_Restaurante: String, Menu_Restaurante :  String, NuevoNombreRestaurante: String){
        val index = Datos.indexOfFirst { it.UUID_Restaurante == UUID_Restaurante }
        Datos[index].Nombre_Restaurante = NuevoNombreRestaurante
        Datos[index].Menu_Restaurante = Menu_Restaurante
        notifyItemChanged(index)
    }

    fun eliminarRegistro(NombreRestaurante: String, posicion: Int){

        //Quitar el elemento de la lista
        val listaDatos= Datos.toMutableList()
        listaDatos.removeAt(posicion)

        //Quitar de la base de datos
        GlobalScope.launch(Dispatchers.IO){
            //1-Crear un objeto de clase conexion
            val objConexion = ClaseConexion().cadenaConexion()
            val deleteRestaurante= objConexion?.prepareStatement("Delete tbRestaurantes where Nombre_Restaurante = ?")!!
            deleteRestaurante.setString(1,NombreRestaurante)
            deleteRestaurante.executeUpdate()


            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    fun actualizarproductos(Nombre_Restaurante: String, Menu_Restaurante: String, UUID_Restaurante: String
    ){
        //1-Creo un acorrutina
        GlobalScope.launch(Dispatchers.IO){

            val objConexion = ClaseConexion().cadenaConexion()

            val updateRestaurante= objConexion?.prepareStatement("update tbRestaurantes set Nombre_Restaurante = ?, Menu_Restaurante = ? where UUID_Restaurante = ?")!!
            updateRestaurante.setString(1,Nombre_Restaurante)
            updateRestaurante.setString(2,Menu_Restaurante)
            updateRestaurante.setString(3,UUID_Restaurante)
            updateRestaurante.executeUpdate()


            val commit = objConexion?.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                ActualizarListaDespuesDeActualizarDatos(UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante  )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRestaurantes {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_restaurante, parent, false)
        return ViewHolderRestaurantes(vista)    }


    override fun getItemCount() = Datos.size
    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolderRestaurantes, position: Int) {
        val producto = Datos[position]
        holder.txtViewRestaurante.text = producto.Nombre_Restaurante
        val item = Datos[position]
        holder.imgdeleteRestaurante.setOnClickListener {

            //Creamos un alerta
            //Creamos el contexto
            val context = holder.itemView.context

            //Creo la alerta
            val builder = AlertDialog.Builder(context)

            //A mi alerta le pongo un titulo
            builder.setTitle("¿Estas seguro?")

            //Ponerle un mensaje
            builder.setMessage("¿Desea eliminar el registro?")

            //Paso final, agregamos los botones
            builder.setPositiveButton("SI") { dialog, wich ->
                eliminarRegistro(item.Nombre_Restaurante, position)
            }

            builder.setNegativeButton("No"){dialog, wich ->
            }
            //Creamos la alerta
            val alertDialog = builder.create()
            //Mostramos la alerta
            alertDialog.show()
        }

        holder.imgeditRestaurante.setOnClickListener {
            val context= holder.itemView.context

            //Creo la alerta
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Editar Nombre")

            //Agregamos un cuadro de texto
            //pueda escribir el nuevo nombre
            val cuadritoNuevoNombre= EditText(context)
            cuadritoNuevoNombre.setHint(item.Nombre_Restaurante)
            builder.setView(cuadritoNuevoNombre)
            val cuadritoNuevoMenu= EditText(context)
            cuadritoNuevoMenu.setHint(item.Menu_Restaurante)
            builder.setView(cuadritoNuevoMenu)

            builder.setPositiveButton("Actualizar"){ dialog, wich->
                actualizarproductos(cuadritoNuevoNombre.text.toString(), cuadritoNuevoMenu.text.toString(), item.UUID_Restaurante)
            }

            builder.setNegativeButton("Cancelar"){dialog, wich ->
                dialog.dismiss()
            }

            val dialog= builder.create()
            dialog.show()
        }


    }
}