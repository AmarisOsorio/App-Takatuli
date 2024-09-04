package expo.turismo.takatuli.RecyclerViewMostrar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.txtNombreLugarM)
}