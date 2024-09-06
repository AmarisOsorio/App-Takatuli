package expo.turismo.takatuli.RecyclerViewResta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbRestaurante2
import expo.turismo.takatuli.R



class AdaptadorResta(var Datos: List<tbRestaurante2>): RecyclerView.Adapter<ViewHolderResta>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderResta {
        val vistaR = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card_resta,parent, false)
        return ViewHolderResta(vistaR)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolderResta, position: Int) {
        val itemResta = Datos[position]
        holder.txtNombreResturante.text = itemResta.Nombre_Restaurante
    }
}