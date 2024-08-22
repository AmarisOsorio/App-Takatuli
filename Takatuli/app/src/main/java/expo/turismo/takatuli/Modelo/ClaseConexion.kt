package expo.turismo.takatuli.Modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {
    fun cadenaConexion(): Connection?{

        try {

<<<<<<< HEAD
            val url = "jdbc:oracle:thin:@172.19.176.1:xe"
            val usuario = "TakatuliPTC"
            val contrasena = "Takatuli2A"
=======

            val url = "jdbc:oracle:thin:@192.168.0.5:1521:xe"
            val usuario = "tbRolUs"
            val contrasena = "1234"
>>>>>>> master

            val connection = DriverManager.getConnection(url,usuario,contrasena)
            return connection

        }

        catch (e: Exception){
            println("Este es el error: $e")
            return null
        }
    }
}