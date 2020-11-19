import Entidades.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.HashSet;
/**
 * Clase que crea datos pseudoaleatorios de visitas al Parque para todo el año.
 */
public class DatosTest
{
    private static Random n = new Random();
    /**
     * Constructor de la clase DatosTest
     */
    public DatosTest() {
    }
    
    /**
     * Método público que inicia la simulación.
     */
    public static void simular(int max) {
        System.out.println();
        System.out.println("Procesando simulación de visitas al parque.....");
        crearVisitas(max);
        montarEnAtracciones();
    }
    
    /**
     * Método que crea visitas diarias pseudoaleatoriamente para cada día del año, con llamadas a métodos privados para las distintas opciones,
     * y cierta prevalencia de false en el pase Vip con respecto al true.
     * @param max Número máximo para el rango de números pseudoaleatorios
     */
    private static void crearVisitas(int max) {
        if (max==1) {
            return;
        }
        LocalDate fecha = LocalDate.of(2019,1,1);               
        for(int x=0;x<365;x++) {       
            for (int y=1;y<(n.nextInt(max)+50);y++) {
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),n.nextBoolean(),0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),true,0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),false,0.0),fecha);
                new Visita(new Visitante(grupo(),desc()),new Entrada(Temporada.MEDIA,tipo(),false,0.0),fecha);
            }
            fecha = fecha.plusDays(1);
        }        
    }
    
    /**
     * Método que simula visitantes montando en atracciones. Para ello, comprueba para cada día del año, en cada atracción y por cada visitante
     * si la atracción esta indicada para su grupo de edad, y si es así, se monta una o dos veces de forma pseudoaleatoria.
     */
    private static void montarEnAtracciones() {
        LocalDate fecha = LocalDate.of(2019,1,1);               
        for(int x=0;x<365;x++) {       
            for (Atraccion atraccion: AtraccionesFuncionando.getAtraccionesActivas()) {
                for (Visita visita: RegistroVisitas.getRegistroDia(fecha)) {
                    if (atraccion.getPublico().contains(visita.getVisitante().getGrupoEdad())) {
                        for (int y=1;y<=(n.nextInt(2));y++) {
                            atraccion.montar(visita.getVisitante());
                        }
                    }
                }
            }
            fecha = fecha.plusDays(1);
        }  
    }
    
    /**
     * Método que calcula una clave de Entidades.GrupoEdad pseudoaleatoria, con orden de probabilidad de visitas en adultos, niños, y, por último, seniors.
     * @return clave de grupo de edad pseudoaleatoria.
     */
    private static GrupoEdad grupo() {
        int g = n.nextInt(6); 
        switch (g) {
            case 1: return GrupoEdad.ADULTO;
            case 2: return GrupoEdad.NIÑO;
            case 3: return GrupoEdad.SENIOR;
            case 4: return GrupoEdad.ADULTO;
            case 5: return GrupoEdad.NIÑO;
            case 6: return GrupoEdad.ADULTO;
        }
        return GrupoEdad.ADULTO;
    }
    
    /**
     * Método que calcula un conjunto de descuentos pseudoaleatorios sobre unos conjuntos plantillas.
     * @return HashSet de descuentos pseudoaleatorios.
     */
    private static HashSet<Descuentos> desc() {
        HashSet<Descuentos> d1 = new HashSet<>(); d1.add(Descuentos.ESTUDIANTE); d1.add(Descuentos.JOVEN);
        HashSet<Descuentos> d2 = new HashSet<>(); d2.add(Descuentos.JOVEN); d2.add(Descuentos.DIVERSIDAD);
        HashSet<Descuentos> d3 = new HashSet<>(); d3.add(Descuentos.DIVERSIDAD);
        HashSet<Descuentos> d4 = new HashSet<>();
        HashSet<Descuentos> d5 = new HashSet<>(); d5.add(Descuentos.DESEMPLEADO);
        int d = n.nextInt(5); 
        switch (d) {
            case 1: return d1;
            case 2: return d2;
            case 3: return d3;
            case 4: return d4;
            case 5: return d5;
        }
        return d4;
    }
    
    /**
     * Método que calcula un tipo de entrada pseudoaleatoria.
     * @return clave de tipo de entrada pseudoaleatoria.
     */
    private static TipoEntrada tipo() {
        int t = n.nextInt(3); 
        switch (t) {
            case 1: return TipoEntrada.GENERAL;
            case 2: return TipoEntrada.TARDE;
            case 3: return TipoEntrada.LABORABLE;
        }
        return TipoEntrada.GENERAL;
    }
    
}
