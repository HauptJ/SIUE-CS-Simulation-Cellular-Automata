/*
 * Joshua Haupt
 * CS 490 Project 4 Cellular Automata
 * 0 is ~ and 1 is ^
 */
package ca1d;

import java.util.Arrays;

/**
 *
 * @author jhaupt
 */
public class CA1D {

    /**
     * @param args the command line arguments
     */
    
    // Global class variables
    
    public int CA_INIT = 0;
    public int CA_RULE = 90;
    public int CA_WIDTH = 100;
    public int CA_GENERATIONS = 100;
    public int CA_GENERATION = 0;
    public int[] CA_CELLS;
    
    
    public void printDog(){
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
    }
    
    
    public void print(){
        System.out.println("CA_GENERATION: " + CA_GENERATION + " CA_INIT: " + CA_INIT + " CA_RULE: " + CA_RULE + " CA_WIDTH: " + CA_WIDTH + " CA_GENERATIONS: " + CA_GENERATIONS);
        //System.out.println(Arrays.toString(CA_CELLS));
        String genStrOrig = Arrays.toString(CA_CELLS);
        String genStrRepl0 = genStrOrig.replaceAll("0", "~");
        String genStrRepl1 = genStrRepl0.replaceAll("1", "^");
        System.out.println(genStrRepl1);
    }
    
    
    public void initialize(){
        CA_CELLS = new int[CA_WIDTH];
        
        // initialize all cells to 0
        for(int i = 0; i < CA_CELLS.length; i++){
            
            CA_CELLS[i] = 0;
        }
        
        // set cell 33 and 66 to 1 if specified
        if(CA_INIT == 1){
            CA_CELLS[33] = 1;
            CA_CELLS[66] = 1;
        }
        // set middle cell to 1 by default
        else{
            CA_CELLS[CA_CELLS.length/2] = 1;
        }
        
    }
    
    public void genNextState(){
        int[] nextGen = new int[CA_CELLS.length];
        
        int left = 0;
        int thing = 0;
        int right = 0;
        
        for(int i = 0; i < CA_CELLS.length; i++){
            
            thing = CA_CELLS[i];
            
            // use last element as left neighbor if at first element in array
            if(i == 0){
                left = CA_CELLS[CA_CELLS.length-1];
                right = CA_CELLS[i+1];
            }
            // normal neighbors
            else if(i > 0 && i < CA_CELLS.length-1){
                left = CA_CELLS[i-1];
                right = CA_CELLS[i+1];
            }
            // use first element as right neighbor if at last element in array
            else if(i == CA_CELLS.length){
                left = CA_CELLS[i-1];
                right = CA_CELLS[0];
            }
            nextGen[i] = isAlive(left, thing, right);
            
        }
        CA_CELLS = nextGen;
        
    }
    
    public int isAlive(int left, int thing, int right){
        int alive = 0;
        
        // CASE: 111
        if(left == 1 && thing == 1 && right == 1){
            alive = 0; // it dead
        }
        // CASE: 110
        else if(left == 1 && thing == 1 && right == 0){
            if(CA_RULE == 90){
                alive = 1; // its alive
            }
            else if(CA_RULE == 30) {
                alive = 0; // it dead
            }
            else {
                System.out.println("DBG: ERROR in isAlive CA_RULE does not exist for CASE 110");
                System.exit(2);
            }
        }
        // CASE: 101
        else if(left == 1 && thing == 0 && right == 1){
            alive = 0; // it dead
        }
        // CASE: 100
        else if(left == 1 && thing == 0 && right == 0){
            alive = 1; // its alive
        }
        // CASE: 011
        else if(left == 0 && thing == 1 && right == 1){
            alive = 1; // its alive
        }
        // CASE: 010
        else if(left == 0 && thing == 1 && right == 0){
            if(CA_RULE == 90){
                alive = 0; // it dead
            }
            else if(CA_RULE == 30) {
                alive = 1; // its alive
            }
            else {
                System.out.println("DBG: ERROR in isAlive CA_RULE does not exist for CASE 010");
                System.exit(3);
            }
        }
        // CASE: 001
        else if(left == 0 && thing == 0 && right == 1){
            alive = 1; // its alive
        }
        // CASE: 000
        else if(left == 0 && thing == 0 && right == 0){
            alive = 0; // it dead
        }
        // CASE: ERROR
        else {
            System.out.println("DBG: ERROR in isAlive CASE does not exist");
            System.exit(1);
        }
    
        return alive;
        
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Part 1
        // Rule 90
        // 30 generations
        // default initialization
        
        System.out.println("Part 1");
        
        CA1D part1 = new CA1D();
        part1.CA_GENERATIONS = 30;
        
        part1.initialize();
        
        for(int i = 0; i < part1.CA_GENERATIONS+1; i++){
            part1.print();
            part1.genNextState();
            part1.CA_GENERATION++;
        }
        
        part1.printDog();
        
        
        // Part 2
        // Rule 90
        // 100 generations
        // default initialization
        
        System.out.println("Part 2");
        
        CA1D part2 = new CA1D();
        part2.CA_GENERATIONS = 100;
        
        part2.initialize();
        
        for(int i = 0; i < part2.CA_GENERATIONS+1; i++){
            part2.print();
            part2.genNextState();
            part2.CA_GENERATION++;
        }
        
        part2.printDog();
        
        
        // Part 3
        // Rule 90
        // 50 generations
        // cells 33 and 66 initialized to 1
        
        System.out.println("Part 3");
        
        CA1D part3 = new CA1D();
        part3.CA_GENERATIONS = 50;
        part3.CA_INIT = 1;
        
        part3.initialize();
        
        for(int i = 0; i < part3.CA_GENERATIONS+1; i++){
            part3.print();
            part3.genNextState();
            part3.CA_GENERATION++;
        }
        
        part3.printDog();
        
        
        // Part 4
        // Rule 30
        // 50 generations
        // default initialization
        
        System.out.println("Part 4");
        
        CA1D part4 = new CA1D();
        part4.CA_GENERATIONS = 50;
        part4.CA_RULE = 30;
        
        part4.initialize();
        
        for(int i = 0; i < part4.CA_GENERATIONS+1; i++){
            part4.print();
            part4.genNextState();
            part4.CA_GENERATION++;
        }
        
        part4.printDog();
        
        
        // Part 5
        // Rule 30
        // 200 generations
        // default initialization
        
        System.out.println("Part 5");
        
        CA1D part5 = new CA1D();
        part5.CA_GENERATIONS = 200;
        part5.CA_RULE = 30;
        
        part5.initialize();
        
        for(int i = 0; i < part5.CA_GENERATIONS+1; i++){
            part5.print();
            part5.genNextState();
            part5.CA_GENERATION++;
        }
        
        part5.printDog();
        
    }
    
}
