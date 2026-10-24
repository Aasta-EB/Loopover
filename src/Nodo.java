import java.util.LinkedList;

import it.unimi.dsi.fastutil.objects.ObjectList;

/**
 * A node in the search tree for the state space of the Loopover puzzle.
 *
 * Each node contains a unique, incrementally assigned identifier (given upon creation),
 * the state reached, a reference to the parent node, the index of the action
 * applied to the parent to generate this node, the depth (number of actions from
 * the root), the cumulative path cost, and the value assigned by the search strategy.
 *
 * An action for a given node is identified by its index (0–31) within `Estado.ACCIONES`,
 * rather than by its 5-bit code: index 0 corresponds to "00+", 1 to "01+", 4 to "10+",
 * 16 to "00-", and 31 to "33-". The code for the i-th action can be retrieved
 * via `Estado.ACCIONES[i]`.
 *
 * The root node is created from a state that has neither a parent nor an action.
 * A child node is created from its parent, the index of an action, and the cost
 * associated with that action.
 *
 * TODO Task 2: Implement all methods marked with "TODO".
 */
public final class Nodo implements Comparable<Nodo> {

    private static final int SIN_ACCION = -1;

    //The counter and the identifier are of type `long`: an `int` would overflow at $2^{31}$
    // nodes, and negative IDs would disrupt the tie-breaking logic in `compareTo`, which assumes
    // that a node created later has a larger ID.
    private static long siguienteId = 0L;

    private final long id;
    private final long bitboard;      // The state is stored as a bitboard, not as an object.
    private final Nodo padre;
    private final int indiceAccion;   // index 0..31 of the action in Estado.ACCIONES
    private final int profundidad;
    private final int costoAcumulado;
    private int heuristica;
    private int valor;
    // Para SMA* (Tarea 3):
    private int hijosPendientes;  // mascara de 32 bits, uno por accion; bit i a 1 =
                                  //   hijo pendiente de generar o de reconstruir
    private int hijosVivos;       // cuantos de esos 32 hijos estan ahora en el arbol
    private boolean expandido;

    /**
     * Root node constructor: a node with no parent and no action.
     * - id = nextId++
     * - depth = 0, accumulatedCost = 0
     * - pendingChildren = all 32 bits set to 1, activeChildren = 0, expanded = false
     */
    public Nodo(Estado estado) {
        if (estado == null) throw new IllegalArgumentException("El estado no puede ser nulo");
        // TODO: initialize all fields

        bitboard = estado.bitboard(); 
        padre = null; 
        indiceAccion = -1;
        id = siguienteId++;
        profundidad = 0;
        costoAcumulado = 0; 
        hijosPendientes = ; // Dont understand what value to put here
        hijosVivos = 0; 
        expandido = false; 

        // throw new UnsupportedOperationException("TODO: Tarea 2 - Nodo(Estado)");
    }

    /**
     * Child node constructor with an action cost of 1 (default cost).
     * Delegates to Node(parent, actionIndex, 1).
     */
    public Nodo(Nodo padre, int indiceAccion) {
        // TODO: this(padre, indiceAccion, 1)
        this(padre, indiceAccion, 1);

        // throw new UnsupportedOperationException("TODO: Tarea 2 - Nodo(Nodo,int)");
    }

    /**
     * Child node constructor with an explicit action cost.
     * - validate the index (0..31)
     * - state = parent.state.apply(State.ACTIONS[actionIndex])
     * - depth = parent.depth + 1
     * - accumulatedCost = parent.accumulatedCost + actionCost
     * - pendingChildren = all 32 bits set to 1, activeChildren = 0, expanded = false
     */
    public Nodo(Nodo padre, int indiceAccion, int costoAccion) {
        if (padre == null) throw new IllegalArgumentException("El padre no puede ser nulo");
        // TODO: initialize all fields
        this.padre = padre; 
        this.indiceAccion = indiceAccion; 
        id = siguienteId++;
        
        estado = padre.estado().aplicar(Estado.ACCIONES[indiceAccion]);
        profundidad = padre.profundidad + 1;
        costoAcumulado = padre.costoAcumulado + costoAccion;
        hijosPendientes = ; 
        hijosVivos = 0; 
        expandido = false; 

        // throw new UnsupportedOperationException("TODO: Tarea 2 - Nodo(Nodo,int,int)");
    }

    public Estado estado() {
        // TODO: return estado
        return estado(); 
        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public Nodo padre() {
        // TODO: return padre
        
        return padre; 
        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /** Index 0..31 of the action applied to the parent in State.ACCIONES. */
    public int accion() {
        // TODO: return indiceAccion

        return indiceAccion; 

        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public int profundidad() {
        // TODO: return profundidad

        return profundidad; 

        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public int costoAcumulado() {
        // TODO: return costoAcumulado

        return costoAcumulado; 

        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public void fijarHeuristica(int heuristica) 
    { 
        throw new UnsupportedOperationException("TODO: Tarea 2"); 
    }
    public int valor() 
    { 
        return valor; 
        // throw new UnsupportedOperationException("TODO: Tarea 2"); 
    }
    public void fijarValor(int valor) 
    {
        throw new UnsupportedOperationException("TODO: Tarea 2"); 
    }

    // Gestion de hijos para SMA* — TODO: Tarea 3
    public void marcarHijoGenerado(int indiceAccion) { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public void marcarHijoPodado(int indiceAccion) { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public int mascaraHijosPendientes() { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public boolean tieneHijosPendientes() { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public boolean tieneHijosVivos() { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public void marcarExpandido(){ throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public boolean necesitaReexpansion() { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public boolean agotado()    { throw new UnsupportedOperationException("TODO: Tarea 3"); }
    public void respaldarMinimo(int valor) { throw new UnsupportedOperationException("TODO: Tarea 3"); }

    public boolean esRaiz() {
        // TODO: return padre == null
        return padre == null; 
        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /**
     * Ordered by ascending value; in the event of a tie, by ascending ID (FIFO).
     * Integer.compare(this.valor, otro.valor); if == 0 → Long.compare(id, otro.id)
     * (the ID is a `long` to prevent overflow during extensive searches)
     */
    @Override
    public int compareTo(Nodo otro) {
        // TODO
        if(Integer.compare(this.valor, otro.valor) == 0)
        {
            return Long.compare(id, otro.id);
        }
        else
        {
            throw new IllegalArgumentException("Something is wrong in compare to nodo.java"); 
        }
        
        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /**
     * Traverses the chain of parents from this node to the root and returns
     * the list in the order: root → this node.
     */
    public ObjectList<Nodo> camino() {
        //TODO: collect into a list, Collections.reverse

        ObjectList<Nodo> collections;
        
        for()
        {
            collections.add(this.padre);
        }

        return collections;

        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public String caminoComoTexto() {
        // TODO: construct text based on camino()
        String caminoAsText; 
        for(Nodo cam : camino())
        {
            cam.StringBuilder >> caminoAsText; 
        }
        return caminoAsText;
        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /**
     * Two nodes are considered equal if they match in both value and identifier 
     * the same condition under which `compareTo()` returns 0. The specification details this contract..
     */
    @Override
    public boolean equals(Object obj) {
        
        if(obj instanceof Nodo)
        {
            Nodo node = (Nodo) obj; 
            if(this.valor == node.valor && this.id == node.id)
            {
                return true; 
            }
            else 
            { 
                return false; 
            }
        }
        else 
        { 
            return false; 
        }
        

        // throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /** Consistent with equals(). */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /**
     * Textual representation of the node, in the format:
     * "[id,parent,action,state,depth,cost,heuristic,value]"; for the root node,
     * the parent and action are replaced by "root" and "NoAction".
     *
     * This is provided as a pre-implemented feature: the grading system compares the program's output
     * against that of the reference JAR file, so this format must remain unchanged (for
     * child nodes, the action text is displayed, not its index).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[").append(id);
        if (padre == null) {
            sb.append(",raiz,NoAccion");
        } else {
            sb.append(",").append(padre.id);
            sb.append(",").append(Estado.accionComoTexto(Estado.ACCIONES[indiceAccion]));
        }
        sb.append(",").append(new Estado(bitboard))
                .append(",").append(profundidad)
                .append(",").append(costoAcumulado)
                .append(",").append(heuristica)
                .append(",").append(valor)
                .append("]");
        return sb.toString();
    }

    /**
     * Verify that the action index is in the range 0..Estado.NUM_ACCIONES - 1.
     */
    private static void validarIndice(int indiceAccion) {
        // TODO
        if(!(indiceAccion >= 0 && indiceAccion <= Estado.NUM_ACCIONES -1))
        {
            throw new IllegalArgumentException("1, action index not in range");
        }
        // throw new UnsupportedOperationException("TODO: Tarea 2 - validarIndice");
    }
}
