/**
 * Clase principal del proyecto para la práctica de la asignatura Programación Orientada a Objetos, para el curso 2018/2019
 *
 * @author Sergio Flor
 * @version 1.0
 */
public class Parque {    
    public static void main(String[] args)    {
        //Cargando ajustes iniciales, creando atracciones, simulando visitas y montar en atracciones.
        RegistroPersonal regPer = new RegistroPersonal();
        Ajustes ajustes = new Ajustes();
        AtraccionesFuncionando atrFun = new AtraccionesFuncionando();
        RegistroVisitas regVis = new RegistroVisitas();
        System.out.print("Introduzca el valor máximo para ĺa generación de visitas aleatorias diarias:");
        DatosTest.simular(Menu.leerOp(2000));
        //Iniciando aplicación.
        Menu.principal();
    }   
}

