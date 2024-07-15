package expo.turismo.takatuli.Modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {
    fun cadenaConexion(): Connection?{

        try {

<<<<<<< HEAD
            val url = "jdbc:oracle:thin:@172.27.48.1:1521:xe"

            val usuario = "TakatuliPTC"
            val contrasena = "Takatuli2A"
=======
            val url = "jdbc:oracle:thin:@192.168.1.27:1521:xe"
            val usuario = "Amaris_Developer"
            val contrasena = "Chalateca_1006"
>>>>>>> Rafael

            val connection = DriverManager.getConnection(url,usuario,contrasena)
            return connection

        }

        catch (e: Exception){
            println("Este es el error: $e")
            return null
        }
    }
}