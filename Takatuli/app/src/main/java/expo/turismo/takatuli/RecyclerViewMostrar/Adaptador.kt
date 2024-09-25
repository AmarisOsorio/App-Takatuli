package expo.turismo.takatuli.RecyclerViewMostrar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import expo.turismo.takatuli.Modelo.tbLugarTuristico
import expo.turismo.takatuli.R


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
    }
}