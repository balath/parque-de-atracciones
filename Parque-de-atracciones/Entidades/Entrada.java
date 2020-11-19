package Entidades;

/**
 * Clase que engloba los distintos tipos de entradas al parque y se ocupa principalmente de la
 * creación de instancias de entradas con los datos suministrados por parámetros, ademas de 
 * proporcionar getters publicos a sus atributos.
 */

public class Entrada
{        
    /**
     * Entidades.Temporada aplicable a la entrada.
     */
    private Temporada temporada;
    /**
     * Tipo de entrada creada.
     */
    private TipoEntrada tipo;
    /**
     * Precio final de la entrada después de aplicarle los descuentos oportunos.
     */
    private double precio;    
    private boolean vip;
    
    /**
     * Constructor de la clase Entidades.Entrada. Utiliza los parámetros para inicializar los atributos de una
     * instancia de Entidades.Entrada, utilizando métodos privados para el cálculo del precio y la temporada.
     * @param tipo: Tipo de entrada a crear.
     * @param descuentos: Entidades.Descuentos que se han de aplicar en el cálculo del precio.
     * @param vip: Adquisición o no del pase VIP
     */
    public Entrada(Temporada temporada,TipoEntrada tipo,boolean vip, double precio) {
        this.temporada = temporada;
        this.tipo = tipo;
        this.vip = vip;
        this.precio = precio;
    }

    /**
     * Setter para el atributo de la temporada.
     * @param clave de la temporada a asignar.
     */
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }
    
    /**
     * Setter para el valor final del precio de la entrada.
     * @param double con el precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Setter para el atributo del pase VIP
     * @param <ul>
     *    <li>true: entrada con pase Vip</li>
     *    <li>false: entrada sin pase Vip</li>
     *    </ul>
     */
    public void setVip(boolean vip) {
        this.vip = vip;
    }
    
    /**
     * Getter para el atributo de la temporada
     * @return Entidades.Temporada con la clave de temporada correspondiente
     */
    public Temporada getTemporada() {
        return this.temporada;
    }
    
    /**
     * Getter para el atributo del tipo de entrada
     * @return Entidades.TipoEntrada con la clave de del tipo que corresponda
     */
    public TipoEntrada getTipoEntrada() {
        return this.tipo;
    }
   
    /**
     * Getter para el valor final del precio de la entrada.
     * @return double con el precio final.
     */
    public double getPrecio() {
        return this.precio;
    }
    
    /**
     * Getter para el atributo del pase VIP
     * @return <ul>
     *    <li>true: entrada con pase Vip</li>
     *    <li>false: entrada sin pase Vip</li>
     *    </ul>
     */
    public boolean isVip() {
        return this.vip;
    }    
}
