package expo.turismo.takatuli.RecyclerViewResta

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import expo.turismo.takatuli.Modelo.tbRestaurante2
import expo.turismo.takatuli.R
import expo.turismo.takatuli.activity_detallesRestaurante


class AdaptadorResta(var Datos: List<tbRestaurante2>) : RecyclerView.Adapter<ViewHolderResta>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderResta {
        val vistaR = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_card_resta, parent, false)
        return ViewHolderResta(vistaR)
    }

    override fun getItemCount(): Int = Datos.size

    override fun onBindViewHolder(holder: ViewHolderResta, position: Int) {
        val itemResta = Datos[position]
        holder.txtNombreResturante.text = itemResta.Nombre_Restaurante


        //Luego de cargar el nombre, cargamos la imagen
        //Glide: libreria para cargar URL en imageView
        Glide.with(holder.ImgRestaurante)
            .load(itemResta.Fotos_Restaurante)
            .into(holder.ImgRestaurante)

        //Todo: Clic a la card completa
        //Vamos a ir a otra pantalla donde me mostrará todos los datos
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            //Cambiar de pantalla a la pantalla de detalle
            val pantallaDetalleResta = Intent(context, activity_detallesRestaurante::class.java)
            //enviar a la otra pantalla todos mis valores
            pantallaDetalleResta.putExtra("Nombre_Restaurante", itemResta.Nombre_Restaurante)
            pantallaDetalleResta.putExtra("Menu_Restaurante", itemResta.Menu_Restaurante)
            pantallaDetalleResta.putExtra("Foto_Menu", itemResta.Foto_Menu)
            pantallaDetalleResta.putExtra("Foto_Resturantes", itemResta.Fotos_Restaurante)


            context.startActivity(pantallaDetalleResta)
        }
    }
}