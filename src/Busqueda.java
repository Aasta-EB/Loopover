import java.util.function.ToIntFunction;

/**
 * State-space search with configurable strategies and pruning based on
 * visited states.
 *
 * The visited-states map stores the best value encountered for each bitboard.
 * When a node is extracted from the frontier, it is pruned if its state is
 * already recorded with an equal or better value; otherwise, it is recorded,
 * and all its successors are expanded.
 *
 * TODO Task 2: Implement `buscar()` (search) and `asignarValor()` (assign value) for
 *              uninformed strategies (DEPTH-FIRST, BREADTH-FIRST, UNIFORM-COST).
 * TODO Task 3: Complete `asignarValor()` for GREEDY, A*, and BOUNDED-A*,
 *              and implement `hacerSitio()` (make space) for SMA*.
 */
public final class Busqueda {

    public enum Estrategia {
        PROFUNDIDAD,
        ANCHURA,
        COSTO_UNIFORME,
        VORAZ,
        A_ESTRELLA,
        A_ESTRELLA_ACOTADA
    }

    private static final int MAX_NODOS_POR_DEFECTO = 10_000_000;

    private final Estado estadoInicial;
    private final Estrategia estrategia;
    private final int profundidadMaxima;
    private final int maxNodosArbol;
    private final ToIntFunction<Estado> heuristica; // This variable is changed due to LongToIntFunction making the constructors of this class undefined
    private final long maxVisitados;

    private int nodosEnArbol;
    private int nodosExpandidos;
    private int estadosVisitados;
    private long tiempoMs;
    private boolean limiteVisitadosAlcanzado;

    public Busqueda(Estado estadoInicial, Estrategia estrategia, int profundidadMaxima) 
    {
        this(estadoInicial, estrategia, profundidadMaxima, MAX_NODOS_POR_DEFECTO, Heuristicas::heuristicaManhattanToroidal, 0L);
    }

    public Busqueda(Estado estadoInicial, Estrategia estrategia, int profundidadMaxima, int maxNodosArbol) {
        this(estadoInicial, estrategia, profundidadMaxima, maxNodosArbol, Heuristicas::heuristicaManhattanToroidal);
    }

    public Busqueda(Estado estadoInicial, Estrategia estrategia, int profundidadMaxima, int maxNodosArbol, ToIntFunction<Estado> heuristica) {
        this(estadoInicial, estrategia, profundidadMaxima, maxNodosArbol, heuristica, 0L);
    }

    public Busqueda(Estado estadoInicial, Estrategia estrategia, int profundidadMaxima,
                    int maxNodosArbol, ToIntFunction<Estado> heuristica, long maxVisitados) {
        if (estadoInicial == null) throw new IllegalArgumentException("El estado inicial no puede ser nulo");
        if (estrategia == null)    throw new IllegalArgumentException("La estrategia no puede ser nula");
        if (profundidadMaxima < 0) throw new IllegalArgumentException("La profundidad maxima debe ser >= 0: " + profundidadMaxima);
        if (estrategia == Estrategia.A_ESTRELLA_ACOTADA && maxNodosArbol < 1)
            throw new IllegalArgumentException("A* acotada requiere maximo de nodos del arbol >= 1");
        if (maxVisitados < 0) throw new IllegalArgumentException("El limite de visitados debe ser >= 0");
        this.estadoInicial    = estadoInicial;
        this.estrategia       = estrategia;
        this.profundidadMaxima = profundidadMaxima;
        this.maxNodosArbol    = maxNodosArbol;
        this.heuristica       = heuristica;
        this.maxVisitados     = maxVisitados;
    }
    /**
     * Executes the search and returns the solution node, or null if none was found.
     */
    public Nodo buscar() {
        // TODO: implement the search loop

        Nodo newNode = new Nodo(estadoInicial);
        asignarValor(newNode);

        Visitados visitedStates = new Visitados();
        Frontera frontier = new Frontera(maxNodosArbol); 
        frontier.insertar(newNode);

        long time = System.currentTimeMillis();

        while(!frontier.vacia())
        {
            Nodo actual = frontier.extraerMejor();

            if(maxVisitados > 0 && visitedStates.tamano() >= maxVisitados)
            {
                limiteVisitadosAlcanzado = true; 
                tiempoMs = System.currentTimeMillis() - time; 
                return null; 
            }

            if(!visitedStates.registrarSiMejor(actual.estado().bitboard(), actual.valor()))
            {
                continue; 
            }

            estadosVisitados = visitedStates.tamano();
            nodosExpandidos++;

            if(actual.estado().esResuelto())
            {
                tiempoMs = System.currentTimeMillis() - time; 
                return actual;
            }

            if(actual.profundidad() >= profundidadMaxima)
            {
                continue;
            }

            for(int i = 0; i < Estado.NUM_ACCIONES; i++)
            {
                Nodo child = new Nodo(actual, i);
                child.fijarHeuristica(heuristica.applyAsInt(child.estado()));
                asignarValor(child);
                frontier.insertar(child);
            }
        }
        
        tiempoMs = System.currentTimeMillis() - time; 
        return null; 
    }

    public int nodosExpandidos() { return nodosExpandidos; }
    public int estadosVisitados() { return estadosVisitados; }
    public long tiempoMs() { return tiempoMs; }
    public boolean limiteVisitadosAlcanzado() { return limiteVisitadosAlcanzado; }

    /**
     * Assigns the node's sorting value according to the strategy.
     */
    private void asignarValor(Nodo nodo) {
        switch (estrategia) {
            case PROFUNDIDAD:
                nodo.fijarValor(-nodo.profundidad());
                break;
            
            case ANCHURA:
                nodo.fijarValor(nodo.profundidad());
                break; 
            case COSTO_UNIFORME:
                nodo.fijarValor(nodo.costoAcumulado());
                break;
                
            case VORAZ, A_ESTRELLA, A_ESTRELLA_ACOTADA: throw new UnsupportedOperationException("TODO: Tarea 3 - estrategia informada");
            default:
                nodo.fijarValor(0);
                break;
        }
    }

    /**
     *For SMA*: frees memory by evicting a discardable page and returns true
     * if successful, or false if none were available.
     *
     * TODO: implement in Task 3.
     */
    private boolean hacerSitio(Frontera frontera) {
        throw new UnsupportedOperationException("TODO: Tarea 3 - hacerSitio()");
    }
}
