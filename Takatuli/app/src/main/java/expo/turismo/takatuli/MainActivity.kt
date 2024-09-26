package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.maps.SupportMapFragment
import expo.turismo.takatuli.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_perfil, R.id.fragment_ajustes,R.id.fragment_chat,R.id.fragment_inicio,R.id.frameLayout5
            )
        )
        val btnIrrestaurante = findViewById<ImageView>(R.id.imgCirResta)
        btnIrrestaurante.setOnClickListener{
            val actTuristico = Intent(this, activity_rcvRestaurante::class.java)
             startActivity(actTuristico)
        }
        val btnIrHospedaje = findViewById<ImageView>(R.id.imgCirHost)
        btnIrHospedaje.setOnClickListener{
            val actHospe= Intent(this, rcvhospedajes::class.java)
            startActivity(actHospe)
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()

        if (intent.getBooleanExtra("ir_a_fotoPerfil", false)) {
            navController.navigate(R.id.fragmente_fotoperfil)
        }

        if (intent.getBooleanExtra("ir_a_mapa", false)) {
            navController.navigate(R.id.fragmentMap)
        }

    }
}