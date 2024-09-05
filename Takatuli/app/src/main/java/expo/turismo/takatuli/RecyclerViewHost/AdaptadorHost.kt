package expo.turismo.takatuli.RecyclerViewHost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.R
import expo.turismo.takatuli.RecyclerViewMostrar.ViewHolder

class AdaptadorHost(var Datos: List<tbHospedaje>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_hospedaje,parent,false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        //holder.txtNombreLugarTuristico.text = item.Nombre_Hospedaje
    }
}