package expo.turismo.takatuli.RecyclerViewMostrar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbDestinos
import expo.turismo.takatuli.R


class Adaptador(var Datos: List<tbDestinos>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card,parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.textView.text = item.UUID_Hospedaje
    }
}