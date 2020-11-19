import Entidades.Atraccion;
import Entidades.Ayudante;
import Entidades.Responsable;
import Entidades.Trabajador;
import java.util.ArrayList;

/**
 * Clase que gestiona las atracciones creadas en el parque, activándolas, inactivándolas y reflejando estas acciones en los costes
 * de personal. Esta clase está orientada a responder a la segunda parte de la pregunta 4) de la práctica.
 *
 */
public class AtraccionesFuncionando {
    /**
     * Lista de atracciones del parque, las cuales podrán estar activas o no.
     */
    private static ArrayList<Atraccion> atraccionesActivas;
    private static ArrayList<Atraccion> atraccionesInactivas;
    
    /**
     * Constructor de la clase AtraccionesFuncionando, que crea su campo por copia de la lista de las Atracciones disponibles en el parque.
     */
    public AtraccionesFuncionando(){
        atraccionesActivas = new ArrayList<>(Ajustes.getAtraccionesDelParque());
        atraccionesInactivas = new ArrayList<>();
    }
        
    /**
     * Método que devuelve la lista de atracciones activas.
     * @return ArrayList de atracciones activas
     */
    public static ArrayList<Atraccion> getAtraccionesActivas() {
        return atraccionesActivas;
    } 
        
    /**
     * Método que llama al método imprime por pantalla el listado de atracciones para el listado de las activas.
     */
    public static void printAtraccionesActivas() {
        printLista(atraccionesActivas);
    }    
        
    /**
     * Método que llama al método imprime por pantalla el listado de atracciones para el listado de las inactivas.
     */
    public static void printAtraccionesInactivas() {
        printLista(atraccionesInactivas);
    }    
      
    /**
     * Método mediante el que se selecciona una atraccion inactiva para activar, para lo que se localiza, y se cambia el estado de
     * la misma, con la contratación de los empleados que le correspondan y la actualización del resto de personal.
     */
    public static void activarAtraccion() {
        if (atraccionesInactivas.isEmpty()) {
            System.out.println("-> No hay atracciones inactivas para activar");
            return;
        }        
        int i = 0, op;  //Variable usada como índice para el listado, opción para elegir e índice para modificar las listas de atracciones. 
        System.out.println("Atracciones inactivas: ");
        for (Atraccion atraccion : atraccionesInactivas) {
            System.out.println("   "+(++i)+"-> "+atraccion.getNombre()+"(Tipo "+atraccion.getTipo()+")");
        }
        System.out.print("                          -> ");
        op = Menu.leerOp(i)-1;
        System.out.println();
        atraccionesInactivas.get(op).setEstado(true);
        atraccionesActivas.add(atraccionesInactivas.remove(op));
        RegistroPersonal.actualizarPersonal();
    }
        
    /**
    * Método mediante el que se selecciona una atraccion activa para desactivar, para lo que se localiza, se despide 
    * a los trabajadores correspondientes, se cambia el estado de la atraccion y se pasa a la lista de atracciones inactivas 
    * y se actualiza el resto de personal.
    */
    public static void inactivarAtraccion() {
        if (atraccionesActivas.isEmpty()) {
            System.out.println("-> No hay atracciones activas para inactivar");
            return;
        }        
        int i = 0, op;  //Variable usada como índice para el listado, opción para elegir e índice para modificar las listas de atracciones. 
        System.out.println("Atracciones activas: ");
        for (Atraccion atraccion : atraccionesActivas) {
            System.out.println("   "+(++i)+"-> "+atraccion.getNombre()+"(Tipo "+atraccion.getTipo()+")");
        }
        System.out.print("                          -> ");
        op = Menu.leerOp(i)-1;
        System.out.println();
        Responsable r = atraccionesActivas.get(op).getResponsable();
        for (Ayudante ayudante: r.getAyudantes()) {
            RegistroPersonal.despedir(Trabajador.Perfil.AYUDANTE,ayudante.getNumTrab());
        }
        RegistroPersonal.despedir(Trabajador.Perfil.RESPONSABLE,r.getNumTrab());
        atraccionesActivas.get(op).setEstado(false);
        atraccionesInactivas.add(atraccionesActivas.remove(op));
        RegistroPersonal.actualizarPersonal();
    }
    
    /**
     * Método que inactiva todas las atracciones.
     */
    public static void inactivarTodas() {
        while (!atraccionesActivas.isEmpty()) {
            Responsable r = atraccionesActivas.get(0).getResponsable();    
            for (Ayudante ayudante: r.getAyudantes()) {
                RegistroPersonal.despedir(Trabajador.Perfil.AYUDANTE,ayudante.getNumTrab());
            }
            RegistroPersonal.despedir(Trabajador.Perfil.RESPONSABLE,r.getNumTrab());
            atraccionesActivas.get(0).setEstado(false);
            atraccionesInactivas.add(atraccionesActivas.remove(0));
        }
        RegistroPersonal.actualizarPersonal();
    }
            
    /**
     * Método que activa todas las atracciones.
     */
    public static void activarTodas() {
        while (!atraccionesInactivas.isEmpty()) {
            atraccionesInactivas.get(0).setEstado(true);
            atraccionesActivas.add(atraccionesInactivas.remove(0));
        }
        RegistroPersonal.actualizarPersonal();
    }
      
    /**
     * Método que imprime por pantalla el listado de atracciones pasado por parámetro, con información sobre nombre, tipo de atraccion,
     * público al que va dirigida, si posee o no restricciones de altura, y, en caso de que esté activa, da información de los trabajadores
     * asignados a ella, y del total de visitas anuales, y promedios mensuales, semanales y diarios.
     */
    private static void printLista(ArrayList<Atraccion> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay atracciones en este estado");
            return;
        }
        int i = 0;
        for (Atraccion atraccion:lista) {
            String alt = (atraccion.getMaxAlt()!=0.0||atraccion.getMinAlt()!=0.0)?"Sí":"No";
            System.out.println(" "+(++i)+" -> Atracción "+atraccion.getNombre()+" (Tipo "+atraccion.getTipo()+") ");
            System.out.println("       Dirigida a : "+atraccion.getPublico().toString()+" -> Restricciones de altura: "+alt);            
            if (atraccion.getEstado()) {
                System.out.println("       Personal : Un Entidades.Responsable de atracción y "+atraccion.getNumAyudantes()+" ayudantes");
                System.out.println("       Visitas anuales y promedios:");
                int vis = atraccion.getVisitantesMontados();
                System.out.println("                ->Anuales:   "+vis+"   ->Mensuales: "+vis/12);
                System.out.println("                ->Semanales: "+vis/52+"     ->Diarias:   "+vis/365);
            }
            System.out.println();
        }
    }    
}
