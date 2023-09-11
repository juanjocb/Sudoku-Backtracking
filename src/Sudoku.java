import java.util.Iterator;

public class Sudoku {

	private int[][] tablero;
	private static final int SIN_ASIGNAR = 0;

	// Constructor que inicializa una matriz de 9x9
	public Sudoku() {

		tablero = new int[9][9];
	}

	// Constructor que recibe una matriz
	public Sudoku(int sudoku[][]) {

		this.tablero = sudoku;
		resolverSudoku();

	}

	public boolean resolverSudoku() {

		// Ciclos que recorre la matriz
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9	; columna++) {

				// Verificamos si la fila y esa columna esta sin asignar
				if (tablero[fila][columna] == SIN_ASIGNAR) {

					// Recorremos los posibles numeros que pueden ir dentro de esa casilla
					for (int numero = 1; numero <= 9; numero++) {

						//Llamamos al metodo esValido y le mandamos la fila, la columna y el numero a asignar
						if (esValido(fila, columna, numero)) {

							//Le asignamos a esa posicion el numero
							tablero[fila][columna] = numero;
							//Hacemos una llamada recursiva para que siga solucionando el sudoku
							if (resolverSudoku()) {
								return true;
							//Si despues de la primera solucion no se puede, se devuelve y desasigna los campos para asignarle otro numero
							} else {
								tablero[fila][columna] = SIN_ASIGNAR;
							}

						}

					}
					return false;
				}

			}
		}
		mostrarSudoku();
		return false;

	}

	private boolean esValido(int fila, int columna, int numero) {

		return !(contieneEnFila(fila, numero) || contieneEnColumna(columna, numero)
				|| contieneEnCaja(fila, columna, numero));
	}

	//Verifica si la fila contiene el numero que tenemos para asignar
	private boolean contieneEnFila(int fila, int numero) {

		for (int i = 0; i < 9; i++) {

			if (tablero[fila][i] == numero) {
				return true;
			}

		}

		return false;
	}
	
	//Verifica si la columna contiene el numero que tenemos para asignar
	private boolean contieneEnColumna(int columna, int numero) {

		for (int i = 0; i < 9; i++) {

			if (tablero[i][columna] == numero) {
				return true;
			}

		}

		return false;
	}

	//Verifica si la caja  contiene el numero que tenemos para asignar
	private boolean contieneEnCaja(int fila, int columna, int numero) {

		int f = fila - fila % 3;
		int c = columna - columna % 3;

		for (int i = f; i < f + 3; i++) {

			for (int j = c; j < c + 3; j++) {

				if (tablero[i][j] == numero) {

					return true;
				}
			}

		}

		return false;
	}

	public void mostrarSudoku() {

		for (int i = 0; i < 9; i++) {

			if (i % 3 == 0 && i != 0) {
				System.out.println("---------------------------------------------\n");
			}
			for (int j = 0; j < 9; j++) {

				if (j % 3 == 0 && j != 0) {
					System.out.print(" | ");
				}
				System.out.print(" " + tablero[i][j] + " ");
			}

			System.out.println();

		}

		System.out.println("\n\n______________________________________\n\n");
	}

}
