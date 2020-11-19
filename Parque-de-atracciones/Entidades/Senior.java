package Entidades;

import java.util.HashSet;
/**
 * Subclase de Entidades.Visitante que especifica un visitante Entidades.Senior
 */
public class Senior extends Visitante
{
    /**
     * Constructor de subclase Entidades.Senior
     */
    public Senior(HashSet<Descuentos> descuentos) {
        super(GrupoEdad.SENIOR, descuentos);
    }
}
