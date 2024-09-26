package expo.turismo.takatuli.RecyclerViewMostrar

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import expo.turismo.takatuli.Modelo.tbLugarTuristico
import expo.turismo.takatuli.R
import expo.turismo.takatuli.activity_detallesRestaurante
import expo.turismo.takatuli.activity_detallesTuristicos


class Adaptador(var Datos: List<tbLugarTuristico>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card,parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.txtNombreLugarTuristico.text = item.Nombre_LugarTuristico

        //Luego de cargar el nombre, cargamos la imagen
        //Glide: libreria para cargar URL en imageView
        Glide.with(holder.ImgLugarTuristico)
            .load(item.Fotos_Lugar_Turistico)
            .into(holder.ImgLugarTuristico)

        //Todo: Clic a la card completa
        //Vamos a ir a otra pantalla donde me mostrará todos los datos
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            //Cambiar de pantalla a la pantalla de detalle
            val pantallaDetalleResta = Intent(context, activity_detallesTuristicos::class.java)
            //enviar a la otra pantalla todos mis valores
            pantallaDetalleResta.putExtra("Nombre_LugarTuristico", item.Nombre_LugarTuristico)
            println("esto es lo que le mando ${item.Nombre_LugarTuristico}")
            pantallaDetalleResta.putExtra("Detalles_Lugar_Turistico", item.Detalles_Lugar_Turistico)
            pantallaDetalleResta.putExtra("Fotos_Lugar_Turistico", item.Fotos_Lugar_Turistico)
            context.startActivity(pantallaDetalleResta)
        }
    }
}