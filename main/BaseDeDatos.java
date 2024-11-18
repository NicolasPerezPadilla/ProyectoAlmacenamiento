import java.util.*;

public class BaseDeDatos {
    private ArrayList<Tabla> tablas;

    public BaseDeDatos() {
        tablas = new ArrayList<>();
    }

    public static void main(String[] args) {
        BaseDeDatos bd = new BaseDeDatos();

        bd.addRegistro("Augusto", 45);
        bd.addRegistro("Augusto");
        bd.addRegistro("Pedro", 80);
        bd.addRegistro("Pedro", 80, 10.7);
        bd.addRegistro("Pedro");

        bd.listarTabla("001");
        bd.editarRegistro("001002", "Hola");
        bd.listarTablas();  

    }

    //Metodo para añadir registros con tipo String
    public void addRegistro(String string) {
        Registro registro = new Registro();
        registro.addDato(string);
        addRegistroAux(registro);
    }

    //Metodo para añadir registros con tipo String e Integer
    public void addRegistro(String string, Integer integer) {
        Registro registro = new Registro();
        registro.addDato(string);
        registro.addDato(integer);
        addRegistroAux(registro);
    }

    //Metodo para añadir registros con tipo String, Integer y Double
    public void addRegistro(String string, Integer integer, Double double1) {
        Registro registro = new Registro();
        registro.addDato(string);
        registro.addDato(integer);
        registro.addDato(double1);
        addRegistroAux(registro);
    }

    //Metodo para editar un registro en especifico
    public void editarRegistro(String identificador, Object nuevoDato) {
        int tablaIndex = Integer.parseInt(identificador.substring(0, 3)) - 1;  // Restamos 1 para índice basado en 0
        int registroIndex = Integer.parseInt(identificador.substring(3, 6)) - 1;  // Restamos 1 para índice basado en 0

        if (tablaIndex >= 0 && tablaIndex < tablas.size()) {
            Tabla tabla = tablas.get(tablaIndex);
            ArrayList<Registro> registros = tabla.obtenerRegistros();

            if (registroIndex >= 0 && registroIndex < registros.size()) {
                Registro registro = registros.get(registroIndex);
                ArrayList<Object> datos = registro.obtenerDatos();

                boolean encontrado = false;  

                for (int i = 0; i < datos.size(); i++) {
                    Object campoActual = datos.get(i);

                    // Se verifica si es compatible el nuevo dato
                    if (campoActual.getClass().isAssignableFrom(nuevoDato.getClass())) {
                        registro.editarDato(i, nuevoDato);
                        System.out.println("Dato editado con éxito.");
                        encontrado = true; 
                        break;  
                    }
                }

                if (!encontrado) {
                    System.out.println("No se encontró un campo con el tipo de dato compatible.");
                }

            } else {
                 System.out.println("No se encontró el registro con el índice especificado.");
            }
        } else {
             System.out.println("No se encontró la tabla con el índice especificado.");
        }
    }

    private boolean esTipoValido(Registro registro, Object nuevoDato) {
        Class<?> tipoNuevoDato = nuevoDato.getClass();
        ArrayList<Class<?>> tiposExistentes = registro.obtenerTipoDatos();

        for (Class<?> class1 : tiposExistentes) {
            if(tipoNuevoDato==class1){
                return true;
            }
        }
        return false;
    }

    public void eliminarRegistro(String identificador) {
        // Desglosamos el identificador en tabla y registro
        int tablaIndex = Integer.parseInt(identificador.substring(0, 3)) - 1;  // Restamos 1 para convertirlo en índice basado en 0
        int registroIndex = Integer.parseInt(identificador.substring(3, 6)) - 1;  // Restamos 1 para índice basado en 0

        // Verificamos que la tabla y el registro existen
        if (tablaIndex >= 0 && tablaIndex < tablas.size()) {
            Tabla tabla = tablas.get(tablaIndex);
            ArrayList<Registro> registros = tabla.obtenerRegistros();

            if (registroIndex >= 0 && registroIndex < registros.size()) {
                // Eliminamos el registro de la lista de registros
                registros.remove(registroIndex);
                System.out.println("Registro eliminado con éxito.");
            } else {
                System.out.println("Error: No se encontró el registro con el índice especificado.");
            }
        } else {
            System.out.println("Error: No se encontró la tabla con el índice especificado.");
        }
    }

    //Metodo para borrar una tabla en especifico
    public void borrarTabla(String identificador) {
        int tablaIndex = Integer.parseInt(identificador) - 1; // Restamos 1 para convertirlo en índice basado en 0

        if (tablaIndex >= 0 && tablaIndex < tablas.size()) {
            tablas.remove(tablaIndex);
            System.out.println("Tabla eliminada con éxito.");
        } else {
            System.out.println("Error: No se encontró la tabla con el índice especificado.");
        }
    }

    //Metodo para listar una tabla en especifico
    public void listarTabla(String identificador) {
        int tablaIndex = Integer.parseInt(identificador) - 1;

        // Verificamos si la tabla existe
        if (tablaIndex >= 0 && tablaIndex < tablas.size()) {
            Tabla tabla = tablas.get(tablaIndex);
            ArrayList<Registro> registros = tabla.obtenerRegistros();

            System.out.println("Listado de registros en la tabla " + identificador + ":");
            if (registros.isEmpty()) {
                System.out.println("No hay registros en esta tabla.");
            } else {
                for (int i = 0; i < registros.size(); i++) {
                    Registro registro = registros.get(i);
                    System.out.print("Registro " + (i + 1) + ": ");
                    ArrayList<Object> datos = registro.obtenerDatos();
                    for (Object dato : datos) {
                        System.out.print(dato + " ");
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("Error: No se encontró la tabla con el índice especificado.");
        }
    }

    //Metodo para listar todas las tablas
    public void listarTablas() {
        for (int i = 0; i < tablas.size(); i++) {
            Tabla tabla = tablas.get(i);
            ArrayList<Registro> registros = tabla.obtenerRegistros();

            System.out.println("Tabla " + String.format("%03d", i + 1) + ":");
            if (!registros.isEmpty()) {
                Registro primerRegistro = registros.get(0);
                ArrayList<Class<?>> tipoDatos = primerRegistro.obtenerTipoDatos();
                System.out.println("  Estructura de la tabla (tipos de datos):");

                for (Class<?> tipo : tipoDatos) {
                    System.out.print("    " + tipo.getSimpleName() + " ");
                }
                System.out.println();

                System.out.println("  Registros:");
                for (int j = 0; j < registros.size(); j++) {
                    Registro registro = registros.get(j);
                    ArrayList<Object> datos = registro.obtenerDatos();
                    System.out.print("    Registro " + (j + 1) + ": ");
                    for (Object dato : datos) {
                        System.out.print(dato + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("  Esta tabla está vacía.");
            }
            System.out.println();
        }
    }


    // Método auxiliar para agregar un registro a la base de datos
    public void addRegistroAux(Registro registro) {
        if (seEncuentraTabla(registro)) {
            for (Tabla tabla : tablas) {
                ArrayList<Registro> registrosAux = tabla.obtenerRegistros();
                if (registro.obtenerTipoDatos().equals(registrosAux.get(0).obtenerTipoDatos())) {
                    tabla.addRegistro(registro);
                    return;
                }
            }
        } else {
            Tabla tabla = new Tabla(registro);
            tablas.add(tabla);
        }
    }

    // Método para verificar si ya existe una tabla con el mismo tipo de datos
    public boolean seEncuentraTabla(Registro registro) {
        for (Tabla tabla1 : tablas) {
            ArrayList<Registro> registrosAux2 = tabla1.obtenerRegistros();
            if (!registrosAux2.isEmpty() && registro.obtenerTipoDatos().equals(registrosAux2.get(0).obtenerTipoDatos())) {
                return true;
            }
        }
        return false;
    }
}
