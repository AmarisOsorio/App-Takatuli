package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController


class fragment_ajustes : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_ajustes, container, false)





        val cambioTema = root.findViewById<RadioButton>(R.id.rbmodoclaro)
        val cambioTema2 = root.findViewById<RadioButton>(R.id.rbmodoOscuro)
        val cambioTema3 = root.findViewById<RadioButton>(R.id.rbpredeterminadosistema)

        val themeRadioGroup = root.findViewById<RadioGroup>(R.id.themaradiogroup)

        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbmodoclaro -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                R.id.rbmodoOscuro -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                R.id.rbpredeterminadosistema -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }

            val button = root.findViewById<Button>(R.id.btnAcercade)
            button.setOnClickListener {
            val intent=Intent(requireContext(), Acerca_de::class.java)
                startActivity(intent)
            }
<<<<<<< HEAD
        val button2 = root.findViewById<Button>(R.id.btnPoliticas  )
=======
        val button2 = root.findViewById<Button>(R.id.btnPoliticas )
>>>>>>> master
            button2.setOnClickListener {
                val intent=Intent(requireContext(),Politicas_de_Privacidad::class.java)
                startActivity(intent)
            }

            val cerrarssesion = root.findViewById<Button>(R.id.btnCerrarsesion)
            cerrarssesion.setOnClickListener {
                val intent=Intent(requireContext(),Login::class.java)
                startActivity(intent)
            }
        }


        return root


    }

}