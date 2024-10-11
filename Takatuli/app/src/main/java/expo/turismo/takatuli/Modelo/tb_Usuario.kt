package expo.turismo.takatuli.Modelo

data class tb_Usuario(
    val UUID_Usuario: String,
    val Nombre_Usuario: String,
    val Password_Usuario:String,
    val Edad_Usuario: Int,
    val Telefono_Usuario: String,
    val Correo_Usuario: String,
    val foto: String
)
