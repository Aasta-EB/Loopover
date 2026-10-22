/**
 * An immutable tuple representing a successor node in the state space:
 * the action applied, the resulting state, and the cost of that transition.
 */
public final class Sucesor {

    private final int accion;
    private final Estado estado;
    private final float costo;

    public Sucesor(int accion, Estado estado, float costo) {
        this.accion = accion;
        this.estado = estado;
        this.costo = costo;
    }

    public int accion() {
        return accion;
    }

    public Estado estado() {
        return estado;
    }

    public float costo() {
        return costo;
    }

    @Override
    public String toString() {
        return "(" + Estado.accionComoTexto(accion) + "," + estado + "," + costo + ')';
    }
}
