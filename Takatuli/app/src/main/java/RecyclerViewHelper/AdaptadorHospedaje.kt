package RecyclerViewHelper

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.R

class AdaptadorHospedaje(var Datos: List<tbHospedaje>) : RecyclerView.Adapter<ViewHolderH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderH {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolderH(vista)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolderH, position: Int) {
        TODO("Not yet implemented")
    }

}