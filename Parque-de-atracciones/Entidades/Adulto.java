package Entidades;

import java.util.HashSet;
/**
 * Subclase de Entidades.Visitante que especifica un visitante Entidades.Adulto
 */
public class Adulto extends Visitante
{
    /**
     * Constructor de subclase Entidades.Adulto
     */
    public Adulto(HashSet<Descuentos> descuentos) {
        super(GrupoEdad.ADULTO, descuentos);
    }
}
