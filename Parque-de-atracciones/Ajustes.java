import Entidades.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * Clase orientada a responder la pregunta 2) de la práctica. Mediante la creación de una instancia 
 * de esta clase al inicio del programa se estableceran en distintas estructuras HashMap las condiciones
 * específicas respecto a precios, descuentos, temporadas y atracciones. El apartado de número visitas
 * lo controla la clase Registro.
 */
public class Ajustes
{
    /**
     * Constante de clase de tipo entero correspondiente al día del año 2019 que se 
     * corresponde con el domingo de pascua, utilizado para el cálculo de las temporadas.
     */
    private static final int PASCUA = 111;
    
    private static double precioGeneral;
    private static double porcentajeMinimo;
    private static HashMap<Descuentos,Double> descuentos;
    private static HashMap<TipoEntrada,Double> tipoEntrada;
    private static HashMap<GrupoEdad,Double> grupoEdad;
    private static HashMap<Temporada,HashSet<Integer>> temporada;
    private static HashMap<Temporada,Double> precioTemporada;
    private static HashSet<Integer> festivos;
    private static double precioVip;
    private static HashMap<Atraccion.Tipo,Atraccion> atracciones;
    
    private static ArrayList<Atraccion> atraccionesDelParque;
    
    /**
     * Constructor para objetos de la clase Registros.Ajustes, que crea las variables de clase, para después iniciarlas según
     * las indicaciones dadas en la pregunta 2 de la práctica.
     */
    public Ajustes() {
        
        precioGeneral = 0.0;
        porcentajeMinimo = 0.0;
        descuentos = new HashMap<>();
        tipoEntrada = new HashMap<>();
        grupoEdad = new HashMap<>();
        temporada = new HashMap<>();
        precioTemporada = new HashMap<>();
        festivos = new HashSet<>();
        precioVip = 0;
        atracciones = new HashMap<>();
        atraccionesDelParque = new ArrayList<>();
        //Aplicación de las restricciones, creación de las atracciones y contratación del personal
        ajustesPractica();
        iniciarAtraccionesDelParque();
        iniciarPersonal();
    }

    /**
     * Método que aplica las condiciones específicas descritas en la práctica.
     */
    public void ajustesPractica() {

        setPrecioGeneral(60.0);
        
        setPorcentajeMinimo(10.0);
        
        setDescuentos(Descuentos.ESTUDIANTE,10.0);
        setDescuentos(Descuentos.JOVEN,10.0);
        setDescuentos(Descuentos.DESEMPLEADO,10.0);
        setDescuentos(Descuentos.DIVERSIDAD,20.0);
        
        setTipoEntrada(TipoEntrada.GENERAL,0.0);
        setTipoEntrada(TipoEntrada.LABORABLE,10.0);
        setTipoEntrada(TipoEntrada.TARDE,25.0);
        setTipoEntrada(TipoEntrada.FAMILIAR,20.0);
        setTipoEntrada(TipoEntrada.OFERTA,0.0);
        
        setGrupoEdad(GrupoEdad.ADULTO,0.0);
        setGrupoEdad(GrupoEdad.NIÑO,50.0);
        setGrupoEdad(GrupoEdad.SENIOR,35.0);
        
        //Declaración de los conjuntos que albergarán los días que corresponden a cada temporada.
        HashSet<Integer> media = new HashSet<>(), alta = new HashSet<>(), baja = new HashSet<>();
        for (int i=1;i<=365;i++) {
            if ((i>=9&&i<=31)||(i>=60&&i<=90)||(i>=121&&i<=212)||(i>=244&&i<=304)) { 
                media.add(i);   //Añade los días del año que pertenecen a temporada media: 
            }                   //9 al 31 de enero, marzo, de mayo a julio y de septiembre a octubre. 
            else {
                if ((i>=1&&i<=8)||(i>=91&&i<=120)||(i>=PASCUA-8&&i<=PASCUA)||(i>=213&&i<=243)||(i>=335&&i<=365)) { 
                    alta.add(i);    //Añade los días del año que pertenecen a temporada alta: 1 al 8 de enero, abril, agosto y diciembre. 
                }                   //Aunque semana santa está incluida en abril en 2019, se deja incluida la condición.
                else {
                    baja.add(i);    //Añade los días del año que pertenecen a temporada baja: febrero y noviembre.
                }
            }
        }
        setTemporada(Temporada.MEDIA,media);    //Se asignan los conjuntos anteriores a las claves correspondientes del Mapa de temporadas.
        setTemporada(Temporada.ALTA,alta);
        setTemporada(Temporada.BAJA,baja);
        
        setPrecioTemporada(Temporada.MEDIA,0.0);
        setPrecioTemporada(Temporada.ALTA,-15.0);
        setPrecioTemporada(Temporada.BAJA,15.0);
        
        //Se añaden festivos: año nuevo, reyes, día de andalucía, jueves y viernes santo, día del trabajo, fiesta nacional,
        //día de todos los santos, día de la inmaculada, constitución y navidad.
        setFestivos(1); setFestivos(6); setFestivos(59);setFestivos(PASCUA-2);setFestivos(PASCUA-3);setFestivos(121);
        setFestivos(285); setFestivos(305); setFestivos(340);setFestivos(342);setFestivos(359);
        
        setVip(50.0);
        
        HashSet<GrupoEdad> a = new HashSet<>(), b = new HashSet<>(), c = new HashSet<>(), d = new HashSet<>(), e = new HashSet<>();
        a.add(GrupoEdad.ADULTO);    //Declaración y creación de los grupos de claves que se asignarán como público de cada tipo de atracción.
        a.add(GrupoEdad.NIÑO);
        b.add(GrupoEdad.ADULTO);
        c.add(GrupoEdad.NIÑO);
        d.add(GrupoEdad.ADULTO);
        d.add(GrupoEdad.NIÑO);
        e.add(GrupoEdad.ADULTO);        
        setAtracciones(Atraccion.Tipo.A,a,1.20,0.0,true,6);
        setAtracciones(Atraccion.Tipo.B,b,1.20,1.90,false,5);
        setAtracciones(Atraccion.Tipo.C,c,0.00,1.20,false,3);
        setAtracciones(Atraccion.Tipo.D,d,0.00,0.00,true,5);
        setAtracciones(Atraccion.Tipo.E,e,0.00,0.00,true,7);
    }
    
    /**
     * Método que crea las atracciones disponibles del parque especificadas en la práctica.
     */
    private void iniciarAtraccionesDelParque() {

        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.A,"El Camarón"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.A,"La Gamba"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.A,"El Langostino"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.A,"La Galera"));

        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.B,"La Sardina"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.B,"La Caballa"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.B,"La Melva"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.B,"El Atún"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.B,"El Alistao"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.B,"La Palometa"));
        
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.C,"La Dorada"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.C,"El Robalo"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.C,"El Sargo"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.C,"La Corvina"));
                
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.D,"El Pulpo"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.D,"El Choco"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.D,"El Calamar"));
        
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"El Pargo"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"El Borriquete"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"El Salmonete"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"La Vieja"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"El Bodión"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"La Araña"));
        atraccionesDelParque.add(new Atraccion(Atraccion.Tipo.E,"La Breca"));        
    }
    
    /**
     * Método que contrata el personal del parque según los porcentajes especificados en la práctica.
     */
    private void iniciarPersonal() {
        int trabAtracciones = 0;
        for(Atraccion atracción: atraccionesDelParque) {
            trabAtracciones += (1 + atracción.getNumAyudantes());   //Se calculan total de responsables y ayudantes del parque            
        }
        int atenciónCliente = trabAtracciones * 30 / 100;           //Cálculo del número de trabajadores de atención al cliente y contratación.
        for (int i=1;i<=(atenciónCliente);i++) {
            RegistroPersonal.addTrabajador(new AtencionCliente());
        }
        int relacionesPub = trabAtracciones * 10 / 100;           //Cálculo del número de trabajadores de relaciones públicas y contratación.
        for (int i=1;i<=(relacionesPub);i++) {
            RegistroPersonal.addTrabajador(new RelacionesPub());
        }
    }
    
    /**
     * Setter para el precio de entrada general en temporada media.
     * @param precio a fijar. 
     */
    private void setPrecioGeneral(Double precio) {
        precioGeneral = precio;        
    }
    
    /**
     * Setter para el porcentaje mínimo a pagar por una entrada, aplicado sobre la entrada general de la temporada corriente.
     * @param porcentaje a aplicar. 
     */
    private void setPorcentajeMinimo(Double porcentaje) {
        porcentajeMinimo = porcentaje;        
    }
    
    /**
     * Setter para el mapa de los descuentos aplicados a los visitantes.
     * @param clave corespondiente al descuento. 
     * @param porcentaje de descuento aplicable.
     */
    private void setDescuentos(Descuentos clave, Double porcentaje) {
        descuentos.put(clave,porcentaje);        
    }
    
    /**
     * Setter para el mapa de los diferentes tipos de entradas.
     * @param clave corespondiente a la entrada.
     * @param porcentaje de descuento aplicable.
     */
    private void setTipoEntrada(TipoEntrada clave, Double porcentaje) {
        tipoEntrada.put(clave,porcentaje);        
    }
    
    /**
     * Setter para el mapa de los descuentos aplicados a distintos grupos de edad.
     * @param clave corespondiente al grupo.
     * @param porcentaje de descuento aplicado.
     */
    private void setGrupoEdad(GrupoEdad clave, Double porcentaje) {
        grupoEdad.put(clave,porcentaje);        
    }
    
    /**
     * Setter para el mapa de los días del año que pertenecen a cada temporada
     * @param clave corespondiente a la temporada.
     * @param periodo conjunto de días del año que pertenecen a la temporada clave.
     */
    private void setTemporada(Temporada clave, HashSet<Integer> periodo) {
        temporada.put(clave,periodo);        
    }
    
    /**
     * Setter para el mapa de los descuentos aplicados a las temporadas
     * @param clave corespondiente a la temporada.
     * @param porcentaje de descuento aplicado.
     */
    private void setPrecioTemporada(Temporada clave, double porcentaje) {
        precioTemporada.put(clave,porcentaje);        
    }
    
    /**
     * Setter para añadir días festivos.
     * @param int que representa el día del año a introducir.
     */
    private void setFestivos(int festivo) {
        festivos.add(festivo);        
    }
    
    /**
     * Setter para el valor del precio del pase VIP.
     * @param previo a asignar al pase.
     */
    private void setVip(Double precio) {
        precioVip = precio;        
    }
    
    /**
     * Setter para el mapa de los distintos tipos de atracciones.
     * @param clave corespondiente al tipo de atracción.
     * @param porcentaje de descuento aplicado.
     */
    private void setAtracciones(Atraccion.Tipo clave, HashSet<GrupoEdad> publico,double min,double max,boolean vip,int ayudantes) {
        atracciones.put(clave,new Atraccion(clave,publico,min,max,vip,ayudantes));        
    }    
  
    /**
     * Getter para el precio de entrada general en temporada media.
     * @return precio fijado para esta entrada. 
     */
    public static Double getPrecioGeneral() {
        return precioGeneral;
    }
    
    /**
     * Getter para el porcentaje mínimo a pagar por una entrada, aplicado sobre la entrada general de la temporada corriente.
     * @return porcentaje a aplicar. 
     */
    public static double getPorcentajeMinimo() {
        return porcentajeMinimo;        
    }
    
    /**
     * Getter para el mapa de los descuentos aplicados a los visitantes.
     * @return HashMap con las claves de los descuentos y sus respectivos valores.
     */
    public static HashMap<Descuentos,Double> getDescuentos() {
        return descuentos;        
    }
    
    /**
     * Getter para el mapa de los diferentes tipos de entradas.
     * @return HashMap con las claves de los tipos de entradas y sus respectivos valores.
     */
    public static HashMap<TipoEntrada,Double> getTipoEntrada() {
        return tipoEntrada;        
    }
    
    /**
     * Getter para el mapa de los descuentos aplicados a distintos grupos de edad.
     * @return HashMap con las claves de los grupos de edad y sus respectivos valores.
     */
    public static HashMap<GrupoEdad,Double> getGrupoEdad() {
        return grupoEdad;        
    }
    
    /**
     * Getter para el mapa de los días del año que pertenecen a cada temporada.
     * @return HashSet con las claves de las temporadas y los periodos correspondientes.
     */
    public static HashMap<Temporada,HashSet<Integer>> getTemporada() {
        return temporada;        
    }
    
    /**
     * getter para el mapa de los descuentos aplicados a las temporadas.
     * @return HashMap con las claves y descuentos/incrementos correspondientes.
     */
    public static HashMap<Temporada,Double> getPrecioTemporada() {
        return precioTemporada;        
    }
    
    /**
     * Getter para el conjunto de días festivos.
     * @return HashSet con los días declarados festivo.
     */
    public static HashSet<Integer> getFestivos() {
        return festivos;        
    }
    
    /**
     * Getter para el valor del precio del pase VIP.
     * @return Double con el precio asignado.
     */
    public static Double getVip() {
        return precioVip;        
    }
    
    /**
     * Getter para el mapa de los diferentes tipos de atracciones.
     * @return HashMap con las claves de los tipos de atracción y un objeto atracción con los ajustes de referencia.
     */
    public static HashMap<Atraccion.Tipo,Atraccion> getAtracciones() {
        return atracciones;
    }
    
    /**
     * Getter para la lista de atracciones disponibles del parque, esten funcionando o no este año.
     * @return ArrayList con las atracciones.
     */
    public static ArrayList<Atraccion> getAtraccionesDelParque() {
        return atraccionesDelParque;
    }
}
