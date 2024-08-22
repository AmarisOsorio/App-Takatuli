package RecyclerViewHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolderH(view: View) : RecyclerView.ViewHolder(view) {
    val txtNombreHCard : TextView = itemView.findViewById(R.id.txtNombreHCard)
    val btnEditH : ImageView = itemView.findViewById(R.id.btnEditH)
    val btnDeleteH : ImageView = itemView.findViewById(R.id.btnDeleteH)

}