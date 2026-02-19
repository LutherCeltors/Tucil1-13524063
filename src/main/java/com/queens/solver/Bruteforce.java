package com.queens.solver;
import java.util.ArrayList;
import com.queens.entities.*;

public class Bruteforce {
    private ArrayList<ArrayList<Coordinate>> combination;
    private int iteration;
    private boolean solutionsFound;

    public Bruteforce(){
        // this.combination = new ArrayList<>();
        this.iteration = 0;
        this.solutionsFound = false;
    }

    /**
     * @implNote Iterasi ini terus dilakukan dengan batasan iterasi yang spesifik, sehingga tidak melakukan iterasi yang mungkin dapat terlalu lama.
     * @param board
     * @param index
     * @param pointCombination
     * @param maxIter
     */
    public void bruteforceQueenPos (Board board, int index, ArrayList<Coordinate> pointCombination, int maxIter){
        if (this.solutionsFound){
            return;
        }
        
        if (this.iteration >= maxIter){
            return;
        }

        if (index == board.getAreas().size()){
            // this.combination.add(pointCombination);
            this.iteration++;
            for (Coordinate cord : pointCombination){
                board.addQueen(cord.getCoordinate()[0], cord.getCoordinate()[1]);
            }

            board.resetAreas();
            board.generateArea();
            boolean isEverOverwriteQueen = board.setEliminationFromQueens();

            if (!isEverOverwriteQueen){
                this.solutionsFound = true;
                System.out.print("Areas: ");System.out.println(board.getAreas().size());
                System.out.print("Queens: ");System.out.println(board.getQueens().size());
            }
            return;
        }else{
            for (int ar = 0; ar < board.getAreas().get(index).getCoordinates().size(); ar++){
                pointCombination.add(board.getAreas().get(index).getCoordinates().get(ar));
                bruteforceQueenPos (board, index + 1, pointCombination, maxIter);
                pointCombination.remove(pointCombination.size());
            }
        }

        return;
    }

    public int getIter(){
        return this.iteration;
    }

    public ArrayList<ArrayList<Coordinate>> getCombination(){
        return this.combination;
    }

    public boolean getSolutionsFound(){
        return this.solutionsFound;
    }
}
