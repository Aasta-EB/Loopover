
/**
 * A table of visited states that stores the best value (lowest cost) obtained
 * for each state encountered. It prevents the re-expansion of nodes with an equal or higher cost.
 *
 * Uses `Long2IntOpenHashMap` from fastutil: a primitive hash map that avoids
 * boxing `Long`/`Integer` objects, making it significantly more efficient than
 * `HashMap<Long, Integer>` when dealing with millions of states.
 *
 * TODO Task 2: Implement all methods.
 */
public final class Visitados {

    // TODO: declare the map field
    // private final Long2IntOpenHashMap mapa;

    public Visitados() {
        // TODO: this.mapa = new Long2IntOpenHashMap()
        throw new UnsupportedOperationException("TODO: Tarea 2 - Visitados()");
    }

    /**
     * Attempts to register a state with a given value.
     * Returns true and updates the map if the state is new OR if the value < previous value.
     * Returns false if the map already contains the state with an equal or better value.
     *
     * Pattern:
     *   int previous = map.getOrDefault(state, Integer.MAX_VALUE);
     *   if (Math.abs(value) < previous) { map.put(state, Math.abs(value)); return true; }
     *   return false;
     */
    public boolean registrarSiMejor(long estado, int valor) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }

    public int tamano() {
        // TODO: mapa.size()
        throw new UnsupportedOperationException("TODO: Tarea 2");
    }
}
