
/**
 * Search frontier, implemented as a minimax heap using
 * Guava's MinMaxPriorityQueue.
 *
 * The minimax queue allows for O(1) access to the minimum and maximum (peekFirst/peekLast)
 * and supports insertion and extraction in O(log n) time (add/pollFirst/pollLast).
 *
 * TODO Task 2: implement all methods marked with TODO.
 * TODO Task 3: implement removeWorstNonRootLeaf() for SMA*.
 */
public final class Frontera {

    private final int maxNodos;
    // TODO: declare the heap field
    // private final MinMaxPriorityQueue<Nodo> heap;

    /**
     * Initialize the MinMaxPriorityQueue, setting a limit on the initial allocation.
     * Pattern: MinMaxPriorityQueue
     *             .expectedSize(Math.min(maxNodos, 65536))
     *             .create()
     *
     * Note: expectedSize(n) is NOT just a hint; it immediately allocates an Object[n] array.
     * The search uses 10,000,000 as a limit when the -c flag is not provided, so passing
     * maxNodos directly would allocate ~40 MB before a single node is expanded. This
     * limit does not cap the total number of elements: the heap grows only when necessary.
     */
    public Frontera(int maxNodos) {
        if (maxNodos < 1) throw new IllegalArgumentException("maxNodos debe ser >= 1: " + maxNodos);
        this.maxNodos = maxNodos;
        // TODO: initialize heap
        throw new UnsupportedOperationException("TODO: Tarea 2 - Frontera(int)");
    }

    /** Number of nodes currently in the frontier. */
    public int tamano() {
        // TODO: return monticulo.size()
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public boolean vacia() {
        // TODO: return monticulo.isEmpty()
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public boolean contiene(Nodo nodo) {
        //Comparison is based on identity (n == node) while traversing the queue: we need
        // to know whether that specific reference is present, not whether there is
        // another node with the same state.
        // TODO: return ...
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public void insertar(Nodo nodo) {
        // TODO: monticulo.add(nodo)
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /**Extracts and returns the node with the lowest value (the most promising one). */
    public Nodo extraerMejor() {
        // TODO: check that it is not empty, return heap.pollFirst()
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    /**
     * For SMA*: Iterate through the queue to find the node with the highest value that is not the root
     * and has no living children (i.e., it is a true leaf). Remove it
     * from the queue and return it, or return null if only the root and internal
     * nodes remain.
     *
     * Filtering by living children ensures that removing a node does not destroy a
     * branch: a node with descendants in the tree remains an internal node,
     * even if it has the highest value; removing it would leave subtrees orphaned.
     *
     * TODO: Implement in Task 3.
     */
    public Nodo eliminarPeorHojaNoRaiz() {
        throw new UnsupportedOperationException("TODO: Tarea 3");
    }
}

