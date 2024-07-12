package RecyclerViewHelper

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.R

class AdaptadorHospedaje(var Datos: List<tbHospedaje>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderH {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolderH(vista)
    }

    override fun getItemCount() = Datos.size
    
    override fun onBindViewHolder(holder: ViewHolderH, position: Int) {
        val hospedaje = Datos[position]
        holder.txtNombreHCard.text = hospedaje.Nombre_Hospedaje

        val item = Datos[position]
    }
}