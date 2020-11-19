import Entidades.GrupoEdad;

import java.util.ArrayList;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.text.DecimalFormat;
/**
 * Clase que se encarga de registrar las visitas al parque y generar las estadísticas recogidas en la pregunta 3) de la práctica.
 */
public class RegistroVisitas
{
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    /**
     * Registro principal de visitas al parque por días.
     */
    private static ArrayList<HashSet<Visita>> registro;
    
    /**
     * Constructor del Registro. Crea la lista de registro y los conjuntos para cada día del año
     */
    public RegistroVisitas()
    {
        registro = new ArrayList<>();
        for (int i=0;i<365;i++) {
            registro.add(new HashSet<>());
        }
    }
    
    /**
     * Método que extrae el conjunto de visitas situado en la posición correspondiente al día del año de la visita que se pretende añadir, 
     * añade la nueva visita, y devuelve el conjunto actualizado a la lista en la posición que estaba.
     * @param visita a añadir
     */
    public static void addVisita(Visita visita) {
        int pos = visita.getFecha().getDayOfYear() - 1;
        HashSet<Visita> registroDia = registro.get(pos);  
        registroDia.add(visita);
        registro.set(pos,registroDia);
    }
           
    /**
     * Método que devuelve el conjunto de visitas para un día determinado
     * @param fecha en formato LocalDate (yyyy-mm-dd)
     */
    public static HashSet<Visita> getRegistroDia(LocalDate fecha) {
        return registro.get(fecha.getDayOfYear()-1);
    }
    
    /**
     * Método que llama al método que calcula e imprime las estadísticas, con parámetros para un día concreto.
     * @param fecha en formato LocalDate(yyyy-mm-dd)de la que se quiere obtener la información.
     */
    public static void datosDiarios(LocalDate fecha) {
        calculaEstadis(fecha.getDayOfYear(),fecha.getDayOfYear(),"el día "+fecha.format(formato)+"(Entidades.Temporada "+ Visita.calcularTemporada(fecha)+")");
    }
    
    /**
     * Método que llama al método que calcula e imprime las estadísticas, con parámetros para una semana concreta.
     * @param fecha en formato LocalDate(yyyy-mm-dd)que pertenece a la semana de la que se quiere obtener la información.
     */
    public static void datosSemanales(LocalDate fecha) {        
        if (fecha.getDayOfYear() < 7) {
            calculaEstadis(1,6,"la primera semana del año (6 días)"); //Contempla el caso de la primera semana del año.
        }
        else {
            if (fecha.getDayOfYear() > 363) {
                calculaEstadis(364,365,"la última semana del año (2 días)"); //Caso de la útima semana del año
            }
            else{
                while (fecha.getDayOfWeek().getValue() != 1) {
                    fecha = fecha.minusDays(1);
                }
                int pos = fecha.getDayOfYear();
                calculaEstadis(pos,pos+6,"la semana del día "+fecha.format(formato));
            }
        }
    }
    
     /**
     * Método que llama al método que calcula e imprime las estadísticas, con parámetros para un mes concreto.
     * @param fecha en formato LocalDate(yyyy-mm-dd)que pertenece al mes del que se quiere obtener la información.
     */
    public static void datosMensuales(LocalDate fecha) { 
        int inicioMes = LocalDate.of(fecha.getYear(),fecha.getMonth(),1).getDayOfYear();
        int finMes = inicioMes + fecha.lengthOfMonth() - 1;
        calculaEstadis(inicioMes,finMes,fecha.getMonth()+" de "+fecha.getYear());
    }
    
    /**
     * Método que llama al método que calcula e imprime las estadísticas, con parámetros para el año 2019.     
     */
    public static void datosAnuales() {        
        calculaEstadis(1,365,"el año 2019");  
    }
    
    /**
     * Método que calcula las estadisticas para el periodo indicado por parámetros.
     * @param inicio Primer día del periodo.
     * @param inicio Último día del periodo.
     * @param periodo Cadena referente al periodo para mostrar pantalla.
     */
    private static void calculaEstadis(int inicio,int fin,String periodo) {
        DecimalFormat dec = new DecimalFormat("###,###,##0.00 €");
        DecimalFormat noDec = new DecimalFormat("###,###,###");
        int totalVisitas = 0;
        double totalEntrada = 0.0;
        int adultos = 0, niños = 0, seniors = 0;
        int pasesVip = 0;
        for (int i = inicio-1;i < fin;i++) {
            for (Visita visita:registro.get(i)) {
                totalVisitas++;
                totalEntrada += visita.getEntrada().getPrecio();
                if (visita.getVisitante().getGrupoEdad()==GrupoEdad.ADULTO) {
                    adultos++;
                }
                else {
                    if (visita.getVisitante().getGrupoEdad()== GrupoEdad.NIÑO) {
                        niños++;
                    }
                    else{
                        seniors++;
                    }
                }
                if (visita.getEntrada().isVip()) {
                    pasesVip++;
                }
            }
        }
        System.out.println("Estadísticas para "+periodo+":");
        System.out.println(" -Visitas Totales:    "+noDec.format(totalVisitas));
        System.out.println("    · Adultos:        "+noDec.format(adultos));
        System.out.println("    · Niños:          "+noDec.format(niños));
        System.out.println("    · Seniors:        "+noDec.format(seniors));
        System.out.println(" -Precio medio de la entrada:                      "+dec.format(totalVisitas==0?0:totalEntrada / totalVisitas));
        System.out.println(" -Porcentaje de addquisición del suplemento Vip:      "+noDec.format(totalVisitas==0?0:pasesVip*100/totalVisitas)+" %");
        System.out.println(" -Recaudación total:                       "+dec.format(totalEntrada));
        System.out.println();        
    }
}
