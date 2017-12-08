/*
 * Joshua Haupt
 * CS 490 Project 4 2D Cellular Automata for Game of Life
 * 0 is ~ and 1 is ^ [this can be changed in print()]
 */
package ca2d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

/**
 *
 * @author jhaupt
 */
public class CA2D {

    // Global class variables
    public int CA_M = 50;
    public int CA_N = 50;
    public int CA_GENERATIONS = 100;
    public int CA_GENERATION = 0;

    public int[][] CA_CELLS;

    public void printDog() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("");
        System.out.println("");
        System.out.println("            /~~~~~~~~\\                           _");
        System.out.println("    ##\\__/ @)      ~~~~~~~~\\                     \\ \\ ) )");
        System.out.println("    |              /~~\\~~~~~                ((    |  \\");
        System.out.println("     \\    /           |                          /   |");
        System.out.println("      (~~~   /         \\____________/~~~~~~~~~~~~   /");
        System.out.println("       ~~~~|~                                     /");
        System.out.println("           :                                      |");
        System.out.println("            \\                                     |");
        System.out.println("            |                               /      \\");
        System.out.println("             \\  \\_         :         \\     /~~~\\    |");
        System.out.println("             /   :~~~~~|   :~~~~~~~~~~|   :     :   :");
        System.out.println("            /    :    /    :         /    :    /    :");
        System.out.println("        (~~~     )(~~~     )     (~~~     )(~~~     )");
        System.out.println("         ~~~~~~~~  ~~~~~~~~       ~~~~~~~~  ~~~~~~~~");
        System.out.println("          STOMP     STOMP          STOMP     STOMP");
        System.out.println("");
        System.out.println("");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    public void print() {
        System.out.println("CA_GENERATION: " + CA_GENERATION + " CA_M: " + CA_M + " CA_N: " + CA_N + " CA_GENERATIONS: " + CA_GENERATIONS);
        System.out.println(Arrays.deepToString(CA_CELLS));

        for (int i = 0; i < CA_M; i++) {
            for (int j = 0; j < CA_N; j++) {
                // replace 0 with ~
                if (CA_CELLS[i][j] == 0) {
                    System.out.print("~");
                } // repalce 1 with ^
                else {
                    System.out.print("^");
                }
            }
            System.out.println();
        }

    }

    public void initialize() {
        CA_CELLS = new int[CA_M][CA_N];

        // initialize all cells to 0
        for (int i = 0; i < CA_M; i++) {
            for (int j = 0; j < CA_N; j++) {
                CA_CELLS[i][j] = (Math.random() < 0.5) ? 0 : 1;
            }

        }
    }

    public void genNextState() {
        int[][] nextGen = new int[CA_M][CA_N];

        // loop through every cell
        for (int i = 1; i < CA_M - 1; i++) {
            for (int j = 1; j < CA_N - 1; j++) {

                // find number of neighbor cells that are alive
                int aliveNeighbors = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        aliveNeighbors -= CA_CELLS[i][j];

                        // Game of Life rules
                        // Any live cell with fewer than two live neighbors dies
                        if ((CA_CELLS[i][j] == 1) && (aliveNeighbors < 2)) {
                            nextGen[i][j] = 0;
                        } // Any live cell with two or three live neighbors lives on
                        else if ((CA_CELLS[i][j] == 1) && ((aliveNeighbors == 2 || aliveNeighbors == 3))) {
                            nextGen[i][j] = 1;
                        } // Any live cell with more than three neighbors dies
                        else if ((CA_CELLS[i][j] == 1) && (aliveNeighbors > 3)) {
                            nextGen[i][j] = 0;
                        } // Any dead cell with exactly three live neighbors becomes a live cell
                        else if ((CA_CELLS[i][j] == 0) && (aliveNeighbors == 3)) {
                            nextGen[i][j] = 1;
                        } // ELSE State stays the same
                        else {
                            nextGen[i][j] = CA_CELLS[i][j];
                            //System.out.println("DBG: reached else in genNextState()");
                        }

                    }
                }
            }

        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        
        // laziest way to write to file
        // http://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/     
        PrintStream file = new PrintStream(new File("CA2D.txt"));
        System.setOut(file);

        // Run 1
        // 100 generations
        // default initialization
        System.out.println("Run 1");

        CA2D run1 = new CA2D();
        run1.CA_GENERATIONS = 100;

        run1.initialize();

        for (int i = 0; i < run1.CA_GENERATIONS + 1; i++) {
            run1.print();
            run1.genNextState();
            run1.CA_GENERATION++;
        }

        run1.printDog();

        // Run 2
        // 100 generations
        // default initialization
        System.out.println("Run 2");

        CA2D run2 = new CA2D();
        run2.CA_GENERATIONS = 100;

        run2.initialize();

        for (int i = 0; i < run2.CA_GENERATIONS + 1; i++) {
            run2.print();
            run2.genNextState();
            run2.CA_GENERATION++;
        }

        run2.printDog();

        // Run 3
        // 100 generations
        // default initialization
        System.out.println("Run 3");

        CA2D run3 = new CA2D();
        run3.CA_GENERATIONS = 100;

        run3.initialize();

        for (int i = 0; i < run3.CA_GENERATIONS + 1; i++) {
            run3.print();
            run3.genNextState();
            run3.CA_GENERATION++;
        }

        run3.printDog();

        // Run 4
        // 100 generations
        // default initialization
        System.out.println("Run 4");

        CA2D run4 = new CA2D();
        run4.CA_GENERATIONS = 100;

        run4.initialize();

        for (int i = 0; i < run4.CA_GENERATIONS + 1; i++) {
            run4.print();
            run4.genNextState();
            run4.CA_GENERATION++;
        }

        run4.printDog();

    }

}
