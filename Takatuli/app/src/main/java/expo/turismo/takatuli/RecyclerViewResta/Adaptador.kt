package expo.turismo.takatuli.RecyclerViewResta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbRestaurante2
import expo.turismo.takatuli.R
import expo.turismo.takatuli.RecyclerViewMostrar.ViewHolder

class Adaptador(var Datos: List<tbRestaurante2>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vistaR = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_resta,parent,false)
        return ViewHolder(vistaR)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.txtNombreResturante.text = item.Nombre_Restaurante
    }
}