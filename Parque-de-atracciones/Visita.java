import Entidades.*;

import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Clase que crea una visita asociando una entrada a un visitante en una fecha concreta, 
 * gestionando todo el proceso de venta de entradas. 
 */
public class Visita
{
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Formato de fecha para imprimir entradas.
    private static int contadorNumRef = 1; //Inicialización de la variable de clase que funciona como contador de número de referencia.
    
    private Visitante visitante;
    private Entrada entrada;
    private LocalDate fecha;
    private int numRef;
    
    /**
     * Constructor de la clase Visita. Llama a los métodos privados correspondientes para ir leyendo los datos
     * introducidos por el usuario y crear una instancia de la clase en base a ellos. 
     */
    public Visita(LocalDate fecha) {      
       this.fecha = fecha;
       switch (leerGrupoEdad()) {
           case ADULTO: visitante = new Adulto(new HashSet<Descuentos>());
                        break;
           case NIÑO:   visitante = new Niño(new HashSet<Descuentos>());
                        System.out.println("ATENCIÓN:   Recuerde que para el acceso al parque de niños, es imprescindible ");
                        System.out.println("            que vayan acompañados de al menos un adulto o senior.");
                        System.out.println();
                        break;
           case SENIOR: visitante = new Senior(new HashSet<Descuentos>());
                        break;
       }
       visitante.setDescuentos(leerDescuentos()); 
       entrada = leerTipoEntrada(fecha);
       entrada.setVip(leerVip());
       entrada.setPrecio(calcularPrecio());     
       numRef = contadorNumRef;
       contadorNumRef++;
       RegistroVisitas.addVisita(this);
       printEntrada();
    }    
    
  
    /**
     * Constructor para entradas en grupo, cuyo suplemento Vip se aplica de forma uniforme en su caso.
     * @param grupo de edad del visitante.
     * @param vip define la selección o no de este suplemento.
     */
    public Visita(GrupoEdad grupo, boolean vip, Entrada entrada, LocalDate fecha) {
       this.fecha = fecha;
       switch (grupo) {
           case ADULTO: visitante = new Adulto(new HashSet<Descuentos>());
                        break;
           case NIÑO:   visitante = new Niño(new HashSet<Descuentos>());
                        break;
           case SENIOR: visitante = new Senior(new HashSet<Descuentos>());
                        break;
       }
       visitante.setDescuentos(leerDescuentos()); 
       this.entrada = entrada;
       this.entrada.setVip(vip);
       this.entrada.setPrecio(calcularPrecio());
       numRef = contadorNumRef;
       contadorNumRef++;
       RegistroVisitas.addVisita(this);
       printEntrada();
    }

    
    /**
     * Constructor de la clase Visita, que se utiliza como auxiliar para la clase DatosTest. 
     * @param visitante Objeto de la clase Entidades.Visitante con sus atributos ya definidos.
     * @param entrada Objeto de la clase Entidades.Entrada con sus atributos ya definidos.
     * @param fecha Fecha de la visita, que será la actual para la venta en taquilla, u otra para a anticipada.
     */
    public Visita(Visitante visitante, Entrada entrada, LocalDate fecha) {      
       this.visitante = visitante;
       this.entrada = entrada;
       this.entrada.setTemporada(calcularTemporada(fecha)); 
       this.entrada.setPrecio(calcularPrecio());
       this.fecha = fecha;
       numRef = contadorNumRef;
       contadorNumRef++;
       RegistroVisitas.addVisita(this);
    }
    
    /**
     * Método que inicia la venta de dos entradas de adultos, con la misma selección del suplemento vip.
    * @param fecha Fecha de la visita, que será la actual para la venta en taquilla, u otra para a anticipada.
     */
    public static void compraDosAdultos(LocalDate fecha) {
        Entrada entrada = leerTipoEntrada(fecha);
        boolean Vip = leerVip();
        System.out.println("Entidades.Entrada número 1: ");
        System.out.println();
        new Visita(GrupoEdad.ADULTO,Vip,entrada,fecha);
        System.out.println("Entidades.Entrada número 2: ");
        System.out.println();
        new Visita(GrupoEdad.ADULTO,Vip,entrada,fecha);
    }    

    /**
     * Método que inicia la compra de un grupo de entrada familiar compuesto, según las especificaciones, por dos adultos y dos niños,
     * con la misma selección del suplemento vip.
     * @param fecha Fecha de la visita, que será la actual para la venta en taquilla, u otra para a anticipada.
     */    
    public static void compraFamiliar(LocalDate fecha) {
        boolean Vip = leerVip();
        System.out.println("Entidades.Entrada del adulto 1: ");
        System.out.println();
        Visita Adulto1 = new Visita(GrupoEdad.ADULTO,Vip,new Familiar(calcularTemporada(fecha)),fecha);
        System.out.println("Entidades.Entrada del adulto 2: ");
        System.out.println();
        Visita Adulto2 = new Visita(GrupoEdad.ADULTO,Vip,new Familiar(calcularTemporada(fecha)),fecha);
        System.out.println("Entidades.Entrada del niño 1: ");
        System.out.println();
        Visita Niño1 = new Visita(GrupoEdad.NIÑO,Vip,new Familiar(calcularTemporada(fecha)),fecha);
        System.out.println("Entidades.Entrada del niño 2: ");
        System.out.println();
        Visita Niño2 = new Visita(GrupoEdad.NIÑO,Vip,new Familiar(calcularTemporada(fecha)),fecha);       
    }

    /**
     * Método que inicia la venta de entradas en grupo, con la misma selección del suplemento vip.
     */   
    public static void compraGrupo(int num, LocalDate fecha) {
        Entrada entrada = leerTipoEntrada(fecha);
        boolean Vip = leerVip();
        for (int i=1;i<=num;i++) {
            System.out.println("Entidades.Entrada número "+i+": ");
            new Visita(leerGrupoEdad(),Vip,entrada,fecha);
        }
    }
    
    /**
     * Getter que devuelve la entrada de la visita
     * @return entrada
     */
    public Entrada getEntrada() {
        return this.entrada;
    }
    
    /**
     * Getter que devuelve el visitante asociado a la visita
     * @return visitante
     */
    public Visitante getVisitante() {
        return this.visitante;
    }
    
    /**
     * Getter que devuelve la fecha de la visita
     * @return fecha en formato LocalDate
     */
    public LocalDate getFecha() {
        return this.fecha;
    }
    
    /**
     * Getter que devuelve el número de referencia de la visita
     * @return número en formato entero
     */
    public int getNumRef() {
        return this.numRef;
    }

    /**
     * Método que lee el grupo de edad seleccionado.
     * @return Entidades.Visitante del grupo correspondiente con el atributo descuentos vacío.
     */
    private static GrupoEdad leerGrupoEdad() {        
        Visitante visitante = null;        
        System.out.println();
        System.out.println("Seleccione el grupo de edad del visitante:");
        System.out.println("    1 -> Entidades.Adulto");
        System.out.println("    2 -> Entidades.Niño");
        System.out.println("    3 -> Entidades.Senior");
        System.out.print("                                    -> ");
        switch (Menu.leerOp(3)) { 
            case 1: return GrupoEdad.ADULTO;
            case 2: return GrupoEdad.NIÑO;
            case 3: return GrupoEdad.SENIOR;
        }
        return null;
    }
    
    /**
     * Método que lee los descuentos que se vayan a aplicar a un visitante.
     * @return HashSet con los descuentos seleccionados.
     */
    private HashSet<Descuentos> leerDescuentos() {        
        HashSet<Descuentos> descuentos = new HashSet<>();        
        boolean fin = false;                
        do {
            System.out.println("Desea: ");
            System.out.println("    1 -> Continuar con la compra de la entrada ");
            System.out.println("    2 -> Aplicar algún descuento");
            System.out.print("                                    -> ");
            switch (Menu.leerOp(2)) {   
                case 1: fin = true;
                        break;
                case 2: break;
            }
            if (!fin) {
                System.out.println("Seleccione un descuento a aplicar a la entrada:");
                System.out.println("    1 -> Carné de estudiante");
                System.out.println("    2 -> Carné joven");
                System.out.println("    3 -> Tarjeta de desempleado");
                System.out.println("    4 -> Diversidad funcional");
                System.out.print("                                    -> ");
                switch (Menu.leerOp(4)) {  //El parámetro corresponde al número de opciones
                    case 1: descuentos.add(Descuentos.ESTUDIANTE);
                            break;
                    case 2: descuentos.add(Descuentos.JOVEN);
                            break;
                    case 3: descuentos.add(Descuentos.DESEMPLEADO);
                            break;            
                    case 4: descuentos.add(Descuentos.DIVERSIDAD);
                            break;            
                }
            }
        } while (!fin);
        return descuentos;
    }
    
    /**
     * Método que lee el tipo de entrada que se vaya a adquirir.
     * @return Entidades.Entrada del tipo seleccionado.
     */
    private static Entrada leerTipoEntrada(LocalDate fecha) {
        boolean fin = true;
        do {
            System.out.println("Seleccione el tipo de entrada:");
            System.out.println("    1 -> Entidades.Entrada general");
            System.out.println("    2 -> Entidades.Entrada de día laborable");
            System.out.println("    3 -> Entidades.Entrada de tarde");
            System.out.println("    4 -> Otras entradas y ofertas");
            System.out.print("                                    -> ");
            switch (Menu.leerOp(5)) { 
                case 1: return new General(calcularTemporada(fecha));                        
                case 2: if (fecha.getDayOfWeek().getValue()==6 || 
                            fecha.getDayOfWeek().getValue()==7 ||
                            Ajustes.getFestivos().contains(fecha.getDayOfYear())) {
                            System.out.println("Tipo de entrada no válida para la fecha introducida, seleccione otro tipo.");
                            System.out.println();
                            fin = false;
                            break;
                        }   
                        return new Laborable(calcularTemporada(fecha));
                case 3: System.out.println("Recuerde que esta entrada sólo es válida a partir de las 16:00");
                        System.out.println();
                        return new Tarde(calcularTemporada(fecha));
                case 4: System.out.println("Actualmente no hay en vigor ofertas especiales");
                        System.out.println();
                        fin = false;
                        break;                                
            }
        } while (!fin);
        return null;
    }
    
    /**
     * Método que lee la selección o no del pase Vip.
     * @return boolean con la selección escogida.
     */
    private static boolean leerVip() {
        System.out.println("Seleccione si desea adquirir el pase Vip para la/s entrada/s:");
        System.out.println("    1 -> Si, desea pase Vip");
        System.out.println("    2 -> No, no desea pase Vip");
        System.out.print("                               -> ");
        switch (Menu.leerOp(2)) { 
            case 1: return true;                        
            case 2: return false;                         
        }
        return false;
    }
    
    /**
     * Método que calcula el precio de la entrada según el tipo de visitante, la temporada,
     * el tipo de entrada, los descuentos de los que disfruta y las restricciones aplicables.
     * @return precio final de la entrada
     */
    private double calcularPrecio() {
        double totalDesc = 0.0;
        //Se acumulan los descuentos por grupo de edad.
        if (!(this.visitante instanceof Adulto)) {
            switch (this.visitante.getGrupoEdad()) {
                case NIÑO:  totalDesc += Ajustes.getGrupoEdad().get(GrupoEdad.NIÑO);
                            break;
                case SENIOR:totalDesc += Ajustes.getGrupoEdad().get(GrupoEdad.SENIOR);
                            break;
            }
        }
        //Entidades.Descuentos que acredita el visitante.
        for (Descuentos desc:this.visitante.getDescuentos()) {
            totalDesc += Ajustes.getDescuentos().get(desc);
        }
        //Descuento o incrementos por temporada.
        switch (this.entrada.getTemporada()) {
            case ALTA:  totalDesc += Ajustes.getPrecioTemporada().get(Temporada.ALTA);
                        break;
            case BAJA:  totalDesc += Ajustes.getPrecioTemporada().get(Temporada.BAJA);
                        break;
            default:    break;
        }
        //Descuento por tipo de entrada.
        if (!(this.entrada instanceof General)) {
            switch (this.entrada.getTipoEntrada()) {
                case LABORABLE: totalDesc += Ajustes.getTipoEntrada().get(TipoEntrada.LABORABLE);
                                break;
                case TARDE:     totalDesc += Ajustes.getTipoEntrada().get(TipoEntrada.TARDE);
                                break;
                case FAMILIAR:  totalDesc += Ajustes.getTipoEntrada().get(TipoEntrada.FAMILIAR);
                                break;
                case OFERTA:    totalDesc += Ajustes.getTipoEntrada().get(TipoEntrada.OFERTA);
                                break;
                                
            }
        }
        //Se calcula el precio con descuentos
        double precioConDescuentos = Ajustes.getPrecioGeneral() - (Ajustes.getPrecioGeneral() * totalDesc / 100);  
        //Se calcula el precio mínimo, en función de la entrada general de la temporada.
        double mínimo = (Ajustes.getPrecioGeneral() - 
                        (Ajustes.getPrecioGeneral() * Ajustes.getPrecioTemporada().get(this.entrada.getTemporada()) / 100) ) *  
                        Ajustes.getPorcentajeMinimo() / 100;
        //Se comprueba si se ha sobrepasado el precio mínimo y se añade el suplemento Vip
        precioConDescuentos = (precioConDescuentos < mínimo)? mínimo : precioConDescuentos;
        if (this.entrada.isVip()) {
            precioConDescuentos += Ajustes.getVip();
        }
        return precioConDescuentos;
    }
    
    /**
     * Método que calcula la temporada de acuerdo a la fecha introducida por parámetro  
     * @param fecha para calcular en formato LocalDate
     * @return Clave de la temporada
     */
    public static Temporada calcularTemporada(LocalDate fecha) {
        int hoy = fecha.getDayOfYear();
        if (Ajustes.getTemporada().get(Temporada.MEDIA).contains(hoy)) {
            return Temporada.MEDIA;
        }
        else {
            if (Ajustes.getTemporada().get(Temporada.ALTA).contains(hoy)) {
                return Temporada.ALTA;
            }
            else {
                return Temporada.BAJA;
            }
        }
    }
    
    /**
     * Método que imprime la entrada correspondiente a la visita por pantalla
     */
    private void printEntrada() {
        System.out.println("*********************************************");
        System.out.println("ENTRADA "+entrada.getTipoEntrada()+"    -"+fecha.format(formato)+"-");
        System.out.println(visitante.getGrupoEdad()+visitante.getDescuentos().toString());
        System.out.print("Ref."+numRef+"   P.V.P.: "+entrada.getPrecio());
        if (entrada.isVip()) {
            System.out.print("--->> Suplemento VIP");
        }
        System.out.println();
        System.out.println("*********************************************");
        System.out.println();
    }
    
    /**
     * Método que simula el torno de entrada al parque, donde tras introducir la entrada se comprueba si los datos 
     * de fecha y tipo de entrada son los adecuados a la fecha y hora actuales.
     * @param visita Entidades.Entrada impresa del visitante.
     * @return true si la entrada es válida para entrar en este momento al parque.
     */
    public static boolean comprobarEntrada(Visita visita) {
        boolean horaCorrecta = true;
        if  (visita.getEntrada().getTipoEntrada()==TipoEntrada.TARDE) {
            horaCorrecta = LocalDateTime.now().getHour()>=16?true:false;
        }        
        return (visita.getFecha()==LocalDate.now()) && horaCorrecta;
    } 
}
