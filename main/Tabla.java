import java.util.*;

public class Tabla {
    private ArrayList<Registro> registros;

    // Constructor que acepta un registro inicial
    public Tabla(Registro registro) {
        registros = new ArrayList<>();
        registros.add(registro);
    }

    // Método para agregar un registro, verificando que los tipos sean consistentes
    public void addRegistro(Registro registro) {
        if (registros.isEmpty()) {
            // Si la tabla está vacía, simplemente agregamos el registro
            registros.add(registro);
        } else {
            // Verificamos que el tipo de datos del nuevo registro coincida con el primero
            ArrayList<Class<?>> tiposExistentes = registros.get(0).obtenerTipoDatos();
            ArrayList<Class<?>> tiposNuevos = registro.obtenerTipoDatos();

            if (tiposExistentes.equals(tiposNuevos)) {
                // Si los tipos coinciden, agregamos el registro
                registros.add(registro);
            } else {
                // Si no coinciden, lanzamos una excepción
                throw new IllegalArgumentException("Los tipos de datos del nuevo registro no coinciden con los tipos existentes.");
            }
        }
    }

    // Método para obtener todos los registros de la tabla
    public ArrayList<Registro> obtenerRegistros() {
        return registros;
    }

}
