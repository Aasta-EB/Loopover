import java.util.List;

/**
 * The state of the 4x4 Loopover puzzle represented as a bitboard in an unsigned
 * long, along with the actions that transform it into another state.
 *
 * Each cell occupies 4 bits: cell i (row i/4, column i%4) occupies
 * bits [i*4, i*4+3], with cell (0,0) located at the lowest bits. The
 * solved state is 0xFEDCBA98_76543210L.
 *
 * An action is a shift operation encoded in 5 bits: bits 0-1 represent the row,
 * bits 2-3 the column, and bit 4 is 1 if the sign is '+'. With '+', the row
 * shifts right and the column shifts down; with '-', they shift left and up.
 *
 * TODO Task 1: implement all methods marked with TODO.
 */
public final class Estado {

    public static final int LADO = 4;
    public static final int NUM_CASILLAS = LADO * LADO;
    public static final int BITS_POR_CASILLA = 4;
    public static final long MASCARA_FICHA = 15L;

    public static final int NUM_ACCIONES = 32;

    // The solved state: tile i in slot i → nibble i = i
    private static final long BITBOARD_RESUELTO = 0xFEDCBA98_76543210L;

    // Action coding bits
    private static final int MASCARA_FILA_ACCION    = 0b00011;
    private static final int MASCARA_COLUMNA_ACCION = 0b01100;
    private static final int BIT_SIGNO_ACCION       = 0b10000;
    private static final int MASCARA_CODIGO_ACCION  = 0b11111;

    // Masks for operating on board rows and columns
    private static final int  BITS_POR_FILA_TABLERO  = LADO * BITS_POR_CASILLA; // 16
    private static final long MASCARA_FILA_TABLERO   = 0xFFFFL;
    // Selecciona el nibble de columna 0 en cada una de las 4 filas
    private static final long MASCARA_COLUMNA_TABLERO = 0x000F000F000F000FL;

    /**
     * Table of 32 action codes. The first 16 entries have a '+' sign,
     * the next 16 have a '-' sign. It is already initialized; do not modify.
     *
     * Format of each code (5 bits):
     *   bit  4  = sign (1 = '+')
     *   bits 3-2 = column (0..3)
     *   bits 1-0 = row    (0..3)
     */
    public static final int[] ACCIONES = new int[NUM_ACCIONES];

    static {
        for (int f = 0; f < LADO; f++) {
            for (int c = 0; c < LADO; c++) {
                int codigoBase = f | (c << 2);
                int posicion   = f * LADO + c;
                ACCIONES[posicion]               = codigoBase | BIT_SIGNO_ACCION; // signo '+'
                ACCIONES[NUM_CASILLAS + posicion] = codigoBase;                   // signo '-'
            }
        }
    }

    private final long bitboard;

    // -------------------------------------------------------------------------
    // Constructors — TODO Task 1
    // -------------------------------------------------------------------------

    /** Construct the state directly from the (already validated) bitboard. */
    public Estado(long bitboard) {
        // TODO: store bitboard
        throw new UnsupportedOperationException("TODO: Tarea 1 - Constructor Estado(long)");
    }

    /**
     * Constructs the state from a string of 32 decimal digits where each
     * pair represents the tile for a specific position (position 0 = pair 0–1, etc.).
     * Example: "00010203040506070809101112131415" is the solved state.
     */
    public Estado(String representacion) {
        // TODO: validate length, parse 16 pairs of digits, call buildBitboard
        throw new UnsupportedOperationException("TODO: Tarea 1 - Constructor Estado(String)");
    }

    /**
     * Constructs the state from an array of 16 tiles, where `tiles[i]` is the
     * tile at position `i`.
     */
    public Estado(int[] fichas) {
        // TODO: validate length, call buildBitboard
        throw new UnsupportedOperationException("TODO: Tarea 1 - Constructor Estado(int[])");
    }

    // -------------------------------------------------------------------------
    // Inquiries — TODO Task 1
    // -------------------------------------------------------------------------

    /** Returns the internal bitboard (required for the visited table). */
    public long bitboard() {
        // TODO: return bitboard
        throw new UnsupportedOperationException("TODO: Tarea 1 - bitboard()");
    }

    /**
     * Returns the tile (0..15) located at position i.
     * Position i = row i/4, column i%4.
     * Operation: (bitboard >>> (i * BITS_POR_CASILLA)) & MASCARA_FICHA
     */
    public int ficha(int casilla) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - ficha(int)");
    }

    /** Returns the tile at the position (row, column). */
    public int ficha(int fila, int columna) {
        // TODO: delegate to tile(row * SIDE + column)
        throw new UnsupportedOperationException("TODO: Tarea 1 - ficha(int,int)");
    }

    /** Returns true if the bitboard is equal to the solved state.*/
    public boolean esResuelto() {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - esResuelto()");
    }

    // -------------------------------------------------------------------------
    // Successors — TODO Task 1
    // -------------------------------------------------------------------------

    /**
     * Returns the list of 32 successors: one for each action in ACCIONES[].
     * Each successor contains: the applied action, the resulting state, and a cost of 1.0f.
     */
    public List<Sucesor> sucesores() {
        // TODO: iterate over ACTIONS[], create new Successor(action, apply(action), 1.0f)
        throw new UnsupportedOperationException("TODO: Tarea 1 - sucesores()");
    }

    // -------------------------------------------------------------------------
    // Apply actions — TODO Task 1
    // -------------------------------------------------------------------------

    /**
     * Applies the action and returns the new state without modifying the current one.
     * 1. Extract row and column from the code (bits 0-1 and 2-3).
     * 2. Extract the sign from bit 4.
     * 3. shiftRow(bitboard, row, positive)
     * 4. shiftColumn(previous_result, column, positive)
     */
    public Estado aplicar(int accion) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - aplicar(int)");
    }

    // -------------------------------------------------------------------------
    // Action Utilities — TODO Task 1
    // -------------------------------------------------------------------------

    /**
     * Converts the action code into readable text, for example "01+" or "33-".
     * Format: row_digit + column_digit + sign.
     */
    public static String accionComoTexto(int accion) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - accionComoTexto(int)");
    }

    /**
     * Parses an action in text format ("01+", "33-", etc.) and returns its code.
     * Validation: length = 3, first character = row digit (0-3),
     *             second = column digit (0-3), third = '+' or '-'.
     */
    public static int accionDesde(String representacion) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - accionDesde(String)");
    }

    // -------------------------------------------------------------------------
    // equals, hashCode, toString — TODO Task 1
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        // TODO: compare bitboard with instanceof State
        throw new UnsupportedOperationException("TODO: Tarea 1 - equals(Object)");
    }

    @Override
    public int hashCode() {
        // TODO: Long.hashCode(bitboard)
        throw new UnsupportedOperationException("TODO: Tarea 1 - hashCode()");
    }

    /**
     * 32-digit representation: each cell as two decimal digits,
     * starting with cell 0.
     * Example of the solved state: "00010203040506070809101112131415"
     */
    @Override
    public String toString() {
        // TODO: StringBuilder, para cada casilla i → ficha(i) con cero inicial si < 10
        throw new UnsupportedOperationException("TODO: Tarea 1 - toString()");
    }

    // =========================================================================
    // Private aid methods — TODO Task 1
    // =========================================================================

    /**
     * Constructs the bitboard from the array of tiles.
     * For each square i: result |= ((long) tiles[i]) << (i * BITS_PER_SQUARE)
     * Validate: no duplicates, each tile in [0, NUM_SQUARES).
     */
    private static long construirBitboard(int[] fichas) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - construirBitboard(int[])");
    }

    /**
     * Shifts the specified row of the bitboard one position to the right (positive=true)
     * or to the left (positive=false), with wrap-around.
     *
     * The row occupies 16 bits starting at bit (row * 16).
     * Row mask:    MASCARA_FILA_TABLERO << (row * 16)
     * Wrap-around nibble mask: MASCARA_FICHA << (row * 16)
     *
     * Right shift (positive):
     *   rotated = ((extracted << 4) & rowMask) | ((extracted >>> 12) & wrapMask)
     *
     * Left shift (negative):
     *   limitMask = wrapMask << 12
     *   rotated = ((extracted >>> 4) & rowMask) | ((extracted << 12) & limitMask)
     */
    private static long desplazarFila(long bitboard, int fila, boolean positivo) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - desplazarFila");
    }

    /**
     * Shifts the specified column of the bitboard one position down (positive=true)
     * or up (positive=false), with wrap-around.
     *
     * The column occupies 4 nibbles spaced 16 bits apart.
     * Column mask: MASCARA_COLUMNA_TABLERO << (column * BITS_POR_CASILLA)
     * Wrap-around nibble mask: MASCARA_FICHA << (column * BITS_POR_CASILLA)
     *
     * Shift down (positive):
     *   rotated = ((extracted << 16) & columnMask) | ((extracted >>> 48) & wrapMask)
     *
     * Shift up (negative):
     *   upperMask = 0xF000000000000000L >>> (12 - column * 4)
     *   rotated = ((extracted >>> 16) & columnMask) | ((extracted << 48) & upperMask)
     */
    private static long desplazarColumna(long bitboard, int columna, boolean positivo) {
        // TODO
        throw new UnsupportedOperationException("TODO: Tarea 1 - desplazarColumna");
    }

    private static void comprobarCasilla(int casilla) {
        if (casilla < 0 || casilla >= NUM_CASILLAS) {
            throw new IllegalArgumentException("Casilla fuera de rango: " + casilla);
        }
    }

    private static void validarAccion(int accion) {
        if (accion < 0 || accion > MASCARA_CODIGO_ACCION) {
            throw new IllegalArgumentException(
                    "La accion debe estar entre 0 y " + MASCARA_CODIGO_ACCION + ": " + accion);
        }
    }

    private static boolean esDigito(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }
}
