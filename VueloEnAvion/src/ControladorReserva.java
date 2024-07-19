package vueloenavion;

import java.util.Date;
import java.util.List;

public class ControladorReserva {

    public boolean realizarReserva(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, List<String> asientosSeleccionados) {
        // Aquí puedes agregar la lógica para realizar la reserva, como validar la disponibilidad del vuelo,
        // guardar los datos en una base de datos, etc. Por ahora, simplemente simulamos la reserva.

        // Simulación de una reserva exitosa
        System.out.println("Reserva realizada:");
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);
        System.out.println("Fecha: " + fecha);
        System.out.println("Pasajeros: " + cantidadPasajeros);
        for (int i = 0; i < cantidadPasajeros; i++) {
            System.out.println("Pasajero " + (i + 1) + ": " + nombres.get(i) + " " + apellidos.get(i) + " - Asiento: " + asientosSeleccionados.get(i));
        }
        return true;
    }
}
