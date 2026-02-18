package com.queens.solver;

import java.util.ArrayList;

import com.queens.entities.Board;
import com.queens.entities.Coordinate;

public class Optimized {
    private boolean solutionsFound;
    private int iteration;

    public Optimized(){
        this.solutionsFound = false;
        this.iteration = 0;
    }

    public void optimizedQueenPos (Board board, int index, ArrayList<Coordinate> pointCombination){
        if (this.solutionsFound){
            return;
        }

        if (index == board.getAreas().size()){
            if (pointCombination.size() == board.totalQueenInBoardAreas()){
                this.solutionsFound = true;
                System.out.print("Areas: ");System.out.println(board.getAreas().size());
                System.out.print("Queens: ");System.out.println(board.getQueens().size());
            }
            return;

        }else{
            for (int ar = 0; ar < board.getAreas().get(index).getCoordinates().size(); ar++){
                pointCombination.add(board.getAreas().get(index).getCoordinates().get(ar));
                board.addQueen(pointCombination.getLast().getCoordinate()[0],pointCombination.getLast().getCoordinate()[1]);
                boolean isEverOverwriteQueen = board.setEliminationFromQueens();
                if (isEverOverwriteQueen){
                    board.popLastQueens();
                    board.resetAreas();
                    board.generateArea();
                    board.setEliminationFromQueens();
                    pointCombination.remove(pointCombination.size()-1);
                    continue;
                }else{
                    optimizedQueenPos(board, index + 1, pointCombination);
                    if (this.solutionsFound){
                        return;
                    }
                    board.popLastQueens();
                    board.resetAreas();
                    board.generateArea();
                    board.setEliminationFromQueens();
                    pointCombination.remove(pointCombination.size() - 1);
                }
                this.iteration++;
            }
        }
        return;
    }

    public boolean getSolutionsFound(){
        return this.solutionsFound;
    }

    public int getIter(){
        return this.iteration;
    }

}
