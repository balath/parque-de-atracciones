import java.util.Scanner;
import java.time.LocalDate;
/**
 * Clase que se encarga de propocionar el conjunto de menús y submenús principales.
 */
public class Menu
{

    /**
     * Constructor de clase.
     */
    public Menu() {
    }
    
    /**
     * Menú principal.
     */
    public static void principal() {
        boolean fin = false;
        do {
            System.out.println();
            System.out.println("---------------------------------Menú Principal---------------------------------");
            System.out.println();
            System.out.println("        1-> Venta de entradas");
            System.out.println("        2-> Gestión de atracciones");
            System.out.println("        3-> Registro de visitas");
            System.out.println("        4-> Resumen anual de personal");
            System.out.println("        5-> Salir");
            System.out.print("                                                     Introduzca una opción: ");
            int op = leerOp(5);
            System.out.println();            
            switch (op) {
                case 1: ventaEntradas();
                        break;
                case 2: gestionAtracciones();
                        break;
                case 3: registroVisitas();
                        break;
                case 4: resumenPersonal();
                        break;
                case 5: fin = true;
                        break;
            }
        } while (!fin);
    }
    
    /**
     * Submenú venta de entradas, que llama al constructor de la clase Visita, donde están delegadas la introducción de las 
     * diferentes opciones aplicables a las entradas.
     */
    private static void ventaEntradas() {
        boolean fin = false;
        do {
            System.out.println();
            System.out.println("-------------------------------Venta de Entradas--------------------------------");
            System.out.println();
            System.out.println("        1-> Venta en taquilla");
            System.out.println("        2-> Venta anticipada/online");
            System.out.println("        3-> Volver al menú principal");
            System.out.print("                                                     Introduzca una opción: ");
            int op = leerOp(3);
            System.out.println();
            switch (op) {
                case 1: tipoCompra(LocalDate.now());
                        break;
                case 2: tipoCompra(leerFecha(1));
                        break;
                case 3: fin = true;
                        break;
            }
        } while (!fin);
    }
    
    /**
     * Submenú del de venta de entradas, que llama al constructor correspondiente de la clase Visita, según la elección de compra.
     */
    private static void tipoCompra(LocalDate fecha) {
        String modo = fecha.equals(LocalDate.now())? "en taquilla" : " anticipada";
        System.out.println();
        System.out.println("-------------------------------Venta "+modo+"--------------------------------");
        System.out.println();
        System.out.println("        1-> Compra de entradas individuales");
        System.out.println("        2-> Compra de dos entradas de adultos");            
        System.out.println("        3-> Compra de entradas tipo familiar (2 adultos + 2 niños)");
        System.out.println("        4-> Compra de entradas en grupo");
        System.out.println("        5-> Volver al submenú de venta de entradas");
        System.out.print("                                                     Introduzca una opción: ");
        int op = leerOp(5);
        System.out.println();
        switch (op) {
            case 1: new Visita(fecha);
                    break;
            case 2: Visita.compraDosAdultos(fecha);
                    break;
            case 3: Visita.compraFamiliar(fecha);
                    break;
            case 4: System.out.print("                                                     Núm. de entradas(máx.30):");
                    int num = leerOp(30); 
                    System.out.println();
                    Visita.compraGrupo(num,fecha);
                    break;
            case 5: break;       
        }
    }
    
    /**
     * Submenú gestión de atracciones, ofrece opciones para los listados de atracciones activas e inactivas y la gestión 
     * de su estado para el año natural.
     */
    private static void gestionAtracciones() {
        boolean fin = false;
        do {
            System.out.println();
            System.out.println("-----------------------------Gestión de atracciones-----------------------------");
            System.out.println();
            System.out.println("        1-> Listado de atracciones activas");
            System.out.println("        2-> Listado de atracciones inactivas");
            System.out.println("        3-> Inactivar atracción para el año natural");
            System.out.println("        4-> Activar atracción para el año natural");
            System.out.println("        5-> Inactivar todas las atracciones");
            System.out.println("        6-> Activar todas las atracciones");
            System.out.println("        7-> Volver al menú principal");
            System.out.print("                                                     Introduzca una opción: ");
            int op = leerOp(7);
            System.out.println();
            switch (op) {
                case 1: AtraccionesFuncionando.printAtraccionesActivas();
                        break;
                case 2: AtraccionesFuncionando.printAtraccionesInactivas();
                        break;
                case 3: AtraccionesFuncionando.inactivarAtraccion();
                        break;
                case 4: AtraccionesFuncionando.activarAtraccion();
                        break;
                case 5: AtraccionesFuncionando.inactivarTodas();
                        break;
                case 6: AtraccionesFuncionando.activarTodas();
                        break;
                case 7: fin = true;
                        break;
            }
        } while (!fin);
    }

    /**
     * Submenú registro de visitas, ofrece estadisticas por días, semanas, meses y año completo.
     */
    private static void registroVisitas() {
        boolean fin = false;
        do {
            System.out.println();
            System.out.println("------------------------------Registro de visitas-------------------------------");
            System.out.println();
            System.out.println("        1-> Diario");
            System.out.println("        2-> Semanal");
            System.out.println("        3-> Mensual");
            System.out.println("        4-> Anual");
            System.out.println("        5-> Volver al menú principal");
            System.out.print("                                                     Introduzca una opción: ");
            int op = leerOp(5);
            System.out.println();
            switch (op) {
                case 1: RegistroVisitas.datosDiarios(leerFecha(op));
                        break;
                case 2: RegistroVisitas.datosSemanales(leerFecha(op));
                        break;
                case 3: RegistroVisitas.datosMensuales(leerFecha(op));
                        break;
                case 4: RegistroVisitas.datosAnuales();
                        break;
                case 5: fin = true;
                        break;
            }
        } while (!fin);
    }

    /**
     * Método que llama al registro de personal, e imprime el resumen anual de gastos de personal.
     */
    private static void resumenPersonal() {
        RegistroPersonal.resumenAnualPersonal();
    }
    
    /**
     * Método que lee la fecha introducida por el ususario y la devuelve en formato LocalDate
     * @param op    Para valores 1 o 2 lee un mes y un día del mes, según la longitud del mes elegido previamente
     *              Para valor 3 lee sólo el mes.
     * @return fecha en formato LocalDate
     */
    private static LocalDate leerFecha(int op) {
        int d, m;
        System.out.print("Introduzca mes (1..12): ");
        m = leerOp(12);
        LocalDate fecha = LocalDate.of(2019,m,1);
        if (op==3) {
            return fecha;
        }
        System.out.print("Introduzca día del mes (1.."+fecha.lengthOfMonth()+"): ");
        d = leerOp(fecha.lengthOfMonth());
        fecha = fecha.withDayOfMonth(d);
        return fecha;
    }
    
    /**
     * Método que lee una entrada de teclado, y comprueba que es un número dentro del ámbito de las opciones posibles según parámetro.
     * este método es utilizado por otras clases cuando necesitan leer de teclado una opción.
     * @param Número de opciones posibles.
     * @return Número de opción seleccionada válido.
     */    
    public static int leerOp(int ops) {
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        int op = 1;
        do {
            try {
                op = Integer.parseInt(input.nextLine());
                if (op>=1&&op<=ops) {
                    ok = true;
                }
                else {
                    System.out.print("Introduzca una opción válida (1.."+ops+") -> ");
                }
            }
            catch (NumberFormatException e) {
                System.out.print("Introduzca una número válido (1.."+ops+") -> ");
            }
        } while (!ok);
        return op;
    }
}
