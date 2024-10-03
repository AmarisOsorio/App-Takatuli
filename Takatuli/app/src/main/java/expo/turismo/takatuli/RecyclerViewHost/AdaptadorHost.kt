package expo.turismo.takatuli.RecyclerViewHost

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.R
import expo.turismo.takatuli.activity_detallesTuristicos
import expo.turismo.takatuli.ui.activity_detallesHospedaje
import kotlinx.coroutines.withContext


class AdaptadorHost(var Datos: List<tbHospedaje>): RecyclerView.Adapter<ViewHolderHost>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHost {
        val vistaH = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_hospedaje,parent,false)
        return ViewHolderHost(vistaH)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolderHost, position: Int) {
        val itemhost = Datos[position]
        holder.txtNombreHost.text= itemhost.Nombre_Hospedaje

        //Luego de cargar el nombre, cargamos la imagen
        //Glide: libreria para cargar URL en imageView
        Glide.with(holder.ImgHospedaje)
            .load(itemhost.Fotos_Hospedaje)
            .into(holder.ImgHospedaje)

        //Todo: Clic a la card completa
        //Vamos a ir a otra pantalla donde me mostrará todos los datos
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            //Cambiar de pantalla a la pantalla de detalle
            val pantallaDetalleHost = Intent(context, activity_detallesHospedaje::class.java)
            //enviar a la otra pantalla todos mis valores
            pantallaDetalleHost.putExtra("Nombre_Hospedaje", itemhost.Nombre_Hospedaje)
            pantallaDetalleHost.putExtra("Precio_Hospedaje", itemhost.Precio_Hospedaje)
            pantallaDetalleHost.putExtra("Detalles_Hospedaje", itemhost.Detalles_Hospedaje)
            pantallaDetalleHost.putExtra("Fotos_Hospedaje", itemhost.Fotos_Hospedaje)
            context.startActivity(pantallaDetalleHost)
        }
    }
}