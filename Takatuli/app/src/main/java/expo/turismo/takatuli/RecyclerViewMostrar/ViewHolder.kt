package expo.turismo.takatuli.RecyclerViewMostrar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtNombreLugarTuristico: TextView = view.findViewById(R.id.txtNombreHost)
    var ImgLugarTuristico: ImageView = view.findViewById(R.id.imgLugarTuristico)
}