import java.io.Serializable

data class Reserva(
    val nombre: String,
    val domicilio: String,
    val producto: String,
    val talla: String,
    val telefono: String,
    val fecha: String
) : Serializable
