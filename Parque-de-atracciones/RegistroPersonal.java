import Entidades.AtencionCliente;
import Entidades.RelacionesPub;
import Entidades.Trabajador;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.text.DecimalFormat;
/**
 * Clase orientada a responder a la primera parte de la pregunta 4), mediante la creación y gestión de un registro del personal 
 * que trabaja en el parque.
 */
public class RegistroPersonal
{
    private static HashMap<Trabajador.Perfil,HashSet<Trabajador>> registro;

    /**
     * Constructor de clase, que inicia el registro estático.
     */
    public RegistroPersonal() {
        registro = new HashMap<>();
        registro.put(Trabajador.Perfil.RESPONSABLE,new HashSet<>());
        registro.put(Trabajador.Perfil.AYUDANTE,new HashSet<>());
        registro.put(Trabajador.Perfil.ATENCION,new HashSet<>());
        registro.put(Trabajador.Perfil.RRPP,new HashSet<>());
    }
    
    /**
     * Método que se encarga de actualizar el registro de personal con las nuevas contrataciones.
     */
    public static void addTrabajador(Trabajador trabajador) {
        registro.get(trabajador.getPerfil()).add(trabajador);
    }
    
    /**
     * Getter que devuelve el registro de personal.
     * @return Mapa del registro de personal.
     */
    public static HashMap<Trabajador.Perfil,HashSet<Trabajador>> getRegistro() {
        return registro;
    }
    
    /**
     * Método que despide a un trabajador.
     * @return true si se ha realizado la operación.
     */
    public static boolean despedir(Trabajador.Perfil perfil, int numTrab) {
        Iterator it = registro.get(perfil).iterator();
        while (it.hasNext()) {
            Trabajador trabajador= (Trabajador) it.next();
            if (trabajador.getNumTrab() == numTrab) {
                it.remove();
                return true;
            }
        }        
        return false;
    }
    
    /**
     * Método que actualiza el personal contratado del parque según los porcentajes especificados en la práctica y las atracciones activas.
     */
    public static void actualizarPersonal() {
        if (AtraccionesFuncionando.getAtraccionesActivas().isEmpty()) {
            registro.get(Trabajador.Perfil.ATENCION).clear();
            registro.get(Trabajador.Perfil.RRPP).clear();
            return;
        }
        registro.get(Trabajador.Perfil.ATENCION).clear();
        registro.get(Trabajador.Perfil.RRPP).clear();
        int trabAtracciones = registro.get(Trabajador.Perfil.RESPONSABLE).size() + registro.get(Trabajador.Perfil.AYUDANTE).size();
        int atenciónCliente = trabAtracciones * 30 / 100;           //Cálculo del número de trabajadores de atención al cliente y contratación.
        for (int i=0;i<(atenciónCliente);i++) {
            RegistroPersonal.addTrabajador(new AtencionCliente());
        }
        int relacionesPub = trabAtracciones * 10 / 100;           //Cálculo del número de trabajadores de relaciones públicas y contratación.
        for (int i=0;i<(relacionesPub);i++) {
            RegistroPersonal.addTrabajador(new RelacionesPub());
        }
    }
    
    /**
     * Método que genera el resumen anual de gastos
     */
    public static void resumenAnualPersonal() {
        double totResp = 0.0, totAyu = 0.0, totAte = 0.0,totRRPP = 0.0;
        for (Trabajador resp:registro.get(Trabajador.Perfil.RESPONSABLE)) {
            totResp += resp.getSueldo();
        }
        for (Trabajador ayu:registro.get(Trabajador.Perfil.AYUDANTE)) {
            totAyu += ayu.getSueldo();
        }
        for (Trabajador ate:registro.get(Trabajador.Perfil.ATENCION)) {
            totAte += ate.getSueldo();
        }
        for (Trabajador rrpp:registro.get(Trabajador.Perfil.RRPP)) {
            totRRPP += rrpp.getSueldo();
        }
        double tot = totResp + totAyu + totAte + totRRPP;
        int plantResp = registro.get(Trabajador.Perfil.RESPONSABLE).size();
        int plantAyu = registro.get(Trabajador.Perfil.AYUDANTE).size();
        int plantAte = registro.get(Trabajador.Perfil.ATENCION).size();
        int plantRRPP = registro.get(Trabajador.Perfil.RRPP).size();
        int plantilla =  plantResp + plantAyu + plantAte + plantRRPP;                                
        System.out.println();
        System.out.println("Resumen anual de gastos en sueldos del Personal:");
        System.out.println();
        System.out.println("Responsables de Atracción("+plantResp+"):");
        printResumen(totResp);
        System.out.println("Ayudantes de Atracción("+plantAyu+"):");
        printResumen(totAyu);
        System.out.println("Atención al cliente("+plantAte+"):");    
        printResumen(totAte);
        System.out.println("Relaciones Públicas("+plantRRPP+"):");
        printResumen(totRRPP);
        System.out.println("Total de todos los perfiles:("+plantilla+"):");
        printResumen(tot);
        System.out.println("Promedio por trabajador:");
        printResumen(plantilla==0?0:tot/plantilla);
    }
    
    /**
     * Método auxiliar para la impresión por pantalla de los datos del resumen anual.
     * @param valor de los sueldos totales al mes para mostrar.
     */
    private static void printResumen(double sueldosMes) {
        DecimalFormat dec = new DecimalFormat("###,###,##0.00 €");
        System.out.println("                           ->    Anual: "+dec.format(sueldosMes*12));
        System.out.println("                           ->  Mensual: "+dec.format(sueldosMes));
        System.out.println("                           ->  Semanal: "+dec.format(sueldosMes*12/52));
        System.out.println("                           ->   Diario: "+dec.format(sueldosMes*12/365));
        System.out.println();
        
    }
}

        
