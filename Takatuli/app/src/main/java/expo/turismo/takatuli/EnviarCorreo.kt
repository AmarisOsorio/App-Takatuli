package expo.turismo.takatuli

import android.media.MediaCas
import android.se.omapi.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.PasswordAuthentication
import java.security.Security
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

suspend fun enviarCorreo(correo: String, asunto: String, mensaje: String) = withContext(
    Dispatchers.IO) {
    val props = Properties().apply {
        put("mail.smtp.host", "smtp.gmail.com")
        put("mail.smtp.socketFactory.port", "465")
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        put("mail.smtp.auth", "true")
        put("mail.smtp.port", "465")
}

    val session = javax.mail.Session.getInstance(props, object : javax.mail.Authenticator() {
        override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
            return javax.mail.PasswordAuthentication("noseenose64@gmail.com", "csqh ilpe ivyj mrbi")
        }
    })

    try {
        val message = MimeMessage(session).apply {
            //Con que correo enviaré el mensaje
            setFrom(InternetAddress("noseenose64@gmail.com"))
            addRecipient(Message.RecipientType.TO, InternetAddress(correo))
            subject = asunto
            setText(mensaje)
        }
        Transport.send(message)
        println("Correo enviado satisfactoriamente")
    } catch (e: MessagingException) {
        e.printStackTrace()
        println("CORREO NO ENVIADO EXE")
    }
}


