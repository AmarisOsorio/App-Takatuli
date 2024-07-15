package RestaurantesHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolderRestaurantes(view: View): RecyclerView.ViewHolder(view) {
  val txtViewRestaurante : TextView = view.findViewById(R.id.txtNombreHCard)
  val imgeditRestaurante: ImageView = view.findViewById(R.id.btnEditH)
  val imgdeleteRestaurante: ImageView = view.findViewById(R.id.btnDeleteH)

}