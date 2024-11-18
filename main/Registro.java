import java.util.*;


public class Registro {
    private ArrayList<Object> datos;
    private ArrayList<Class<?>> tipoDatos;

    // Constructor inicializando las colecciones
    public Registro() {
        datos = new ArrayList<>();
        tipoDatos = new ArrayList<>();
    }

    // Método para agregar un dato al registro
    public void addDato(Object dato) {
        datos.add(dato);
        if (!fueLeido(dato.getClass())) {
            tipoDatos.add(dato.getClass());
        }
    }

    // Método para obtener los datos almacenados en el registro
    public ArrayList<Object> obtenerDatos() {
        return datos;
    }

    // Método para obtener los tipos de los datos almacenados
    public ArrayList<Class<?>> obtenerTipoDatos() {
        return tipoDatos;
    }

    // Método para comprobar si el tipo de dato ya ha sido leído
    public boolean fueLeido(Class<?> class1) {
        return tipoDatos.contains(class1);
    }

    // Método para obtener la cantidad de datos en el registro
    public int obtenerCantidadDatos() {
        return datos.size();
    }

    public void editarDato(int index, Object nuevoDato) {
        if (index >= 0 && index < datos.size()) {
            datos.set(index, nuevoDato);
        } 
    }

}
