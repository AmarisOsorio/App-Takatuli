package expo.turismo.takatuli.Modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {
    fun cadenaConexion(): Connection?{

        try {

            val url = "jdbc:oracle:thin:@192.168.68.102:1521:xe"
            val usuario = "AlejandroPTC"
            val contrasena = "pituco01"




            val connection = DriverManager.getConnection(url,usuario,contrasena)
            return connection

        }

        catch (e: Exception){
            println("Este es el error: $e")
            return null
        }
    }
}