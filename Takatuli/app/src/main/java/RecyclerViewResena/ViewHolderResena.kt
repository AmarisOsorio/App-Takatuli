package RecyclerViewResena

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolderResena(view: View):RecyclerView.ViewHolder(view) {
    val txtNombreCard = view.findViewById<TextView>(R.id.txtNombreCard)
    val imgEdiatrR = view.findViewById<ImageView>(R.id.imgEditarR)
    val imgEliminarR = view.findViewById<ImageView>(R.id.imgEliminarR)
}