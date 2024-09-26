package RecyclerViewResena

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbResena
import expo.turismo.takatuli.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdaptadorResena(var Datos:List<tbResena>):RecyclerView.Adapter<ViewHolderResena>() {

 fun eliminarResena(Reseña_Viaje:String, position: Int){
     val listaResenas = Datos.toMutableList()
     listaResenas.removeAt(position)

     GlobalScope.launch(Dispatchers.IO) {
         val objConexion = ClaseConexion().cadenaConexion()
         val deleteResena = objConexion?.prepareStatement("delete tbReseñas where Reseña_Viaje = ?")!!
         deleteResena.setString(1,Reseña_Viaje)
         deleteResena.executeUpdate()
         val commit = objConexion.prepareStatement("commit")
         commit.executeUpdate()
     }
  Datos = listaResenas.toList()
     notifyItemRemoved(position)
     notifyDataSetChanged()
 }

    fun editarResena(Reseña_Viaje: String, UUID_Reseña:String){
        GlobalScope.launch(Dispatchers.IO) {
            val objConexion = ClaseConexion().cadenaConexion()
            val updateResena = objConexion?.prepareStatement("update tbReseñas set Reseña_Viaje = ? where UUID_Reseña = ?   ")!!
            updateResena.setString(1,Reseña_Viaje)
            updateResena.setString(2, UUID_Reseña)
            updateResena.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderResena {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_resena, parent, false)
        return ViewHolderResena(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolderResena, position: Int) {
        val item = Datos[position]
        holder.txtNombreCard.text = item.Reseña_Viaje

        holder.imgEliminarR.setOnClickListener {
            val contexto = holder.txtNombreCard.context
            val builder = AlertDialog.Builder(contexto)
            builder.setTitle("Eliminar")
            builder.setMessage("Estas seguro que deseas eliminar tu reseña?")

            builder.setPositiveButton("Si") { dialog, wich ->

                eliminarResena(item.Reseña_Viaje, position)

            }
            builder.setNegativeButton("No") { dialog, wich ->

                dialog.dismiss()
            }
            builder.show()
        }
     holder.imgEdiatrR.setOnClickListener{
         val context = holder.txtNombreCard.context
         val builder = AlertDialog.Builder(context)
         builder.setTitle("Actualizar")
         builder.setMessage("Desea actualizar su reseña?")

         val cuadrotexto = EditText(context)
         cuadrotexto.setHint(item.Reseña_Viaje)
         builder.setView(cuadrotexto)

         builder.setPositiveButton("Actualizar"){dialog,wich ->
      editarResena(cuadrotexto.text.toString(), item.UUID_Reseña)
         }
       builder.setNegativeButton("Cancelar"){dialog,wich ->
       dialog.dismiss()
       }
       builder.show()
     }
    }
}

       