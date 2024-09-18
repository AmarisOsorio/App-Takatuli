package expo.turismo.takatuli.RecyclerViewHost

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolderHost(view: View) : RecyclerView.ViewHolder(view) {
    var txtNombreHost: TextView = view.findViewById(R.id.txtNombreHost)
}