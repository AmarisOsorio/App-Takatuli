package expo.turismo.takatuli.RecyclerViewHost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.R


class AdaptadorHost(var Datos: List<tbHospedaje>): RecyclerView.Adapter<ViewHolderHost>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHost {
        val vistaH = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_hospedaje,parent,false)
        return ViewHolderHost(vistaH)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolderHost, position: Int) {
        val itemhost = Datos[position]
        holder.txtNombreHost.text= itemhost.Nombre_Hospedaje
    }
}