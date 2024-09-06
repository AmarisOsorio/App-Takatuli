package expo.turismo.takatuli.RecyclerViewResta

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolderResta(view: View) : RecyclerView.ViewHolder(view) {
    val txtNombreResturante : TextView = view.findViewById(R.id.txtNombreRestaM)
}