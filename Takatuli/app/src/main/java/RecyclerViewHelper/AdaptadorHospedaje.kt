package RecyclerViewHelper

import RestaurantesHelper.ViewHolderRestaurantes
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdaptadorHospedaje(var DatosH: List<tbHospedaje>) : RecyclerView.Adapter<ViewHolderH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderH {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolderH(vista)
    }

    fun actualizarListaDespuesDeActualizarDatos(UUID_Hospedaje: String,nuevoNombre:String){
        val index = DatosH.indexOfFirst { it.UUID_Hospedaje == UUID_Hospedaje }
        DatosH[index].Nombre_Hospedaje = nuevoNombre
        notifyItemChanged(index)
    }


    fun actualizarTickets(Nombre_Hospedaje: String,UUID_Hospedaje: String){
        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()
            val updateProducto = objConexion?.prepareStatement("update tbHospedaje set Nombre_Hospedaje = ? where UUID_Hospedaje = ?")!!
            updateProducto.setString(1,Nombre_Hospedaje)
            updateProducto.setString(2,UUID_Hospedaje)
            updateProducto.executeUpdate()

            val commit = objConexion?.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                actualizarListaDespuesDeActualizarDatos(UUID_Hospedaje,Nombre_Hospedaje)
            }
        }


    }

    fun eliminarRegistro(Nombre_Hospedaje: String,posicion: Int){

        val listaDatos =DatosH.toMutableList()
        listaDatos.removeAt(posicion)

        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()
            val delTickets = objConexion?.prepareStatement("delete tbHospedaje where Nombre_Hospedaje = ?")!!
            delTickets.setString(1,Nombre_Hospedaje)
            delTickets.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }

        DatosH = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    override fun getItemCount() = DatosH.size

    override fun onBindViewHolder(holder: ViewHolderH, position: Int) {
        val hospedajeC = DatosH[position]
        holder.txtNombreHCard.text = hospedajeC.Nombre_Hospedaje

        val item = DatosH[position]


        holder.btnDeleteH.setOnClickListener {
            val context =  holder.itemView.context

            val builder = AlertDialog.Builder(context)

            builder.setTitle("¿Estas seguro?")

            builder.setMessage("¿Desea eliminar el registro?")


            builder.setNegativeButton("No"){dialog,which ->

            }

            builder.setPositiveButton("Si"){dialog,which ->
                eliminarRegistro(item.Nombre_Hospedaje, position)
            }



            val alertDialog = builder.create()

            alertDialog.show()
        }



        holder.btnEditH.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Editar nombre")

            val nuevoTitulo = EditText(context)
            nuevoTitulo.setHint(item.Nombre_Hospedaje)
            builder.setView(nuevoTitulo)

            builder.setPositiveButton("Actualizar"){dialog,wich ->
                actualizarTickets(nuevoTitulo.text.toString(),item.UUID_Hospedaje)
            }

            builder.setNegativeButton("Cancelar"){dialog,wich ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }



    }



}