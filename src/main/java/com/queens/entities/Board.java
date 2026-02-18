package com.queens.entities;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

public class Board {
    private int order;
    private ArrayList<ArrayList<Character>> boardElement;
    private ArrayList<Area> areas;
    private ArrayList<Coordinate> queens;

    /**
     * @implNote Constructor board bertugas untuk menginisiasi element array dengan 'n'sebagai singkatan dari null, sesuai dengan orde parameter, orde 8 adalah default
     * @param order sebagai ordo dari matriks papan yang akan dibuat, diasumsikan papan selalu persegi.
     */
    public Board(int order){
        this.order = order;
        this.boardElement = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.queens = new ArrayList<>();
    }
    
    public int getOrder (){
        return this.order;
    }

    public ArrayList<Coordinate> getQueens() {
        return this.queens;
    }

    public ArrayList<Area> getAreas (){
        return this.areas;
    }

    public char getElementFromBoard(int xInd, int yInd){
        return this.boardElement.get(xInd).get(yInd);
    }

    public static Board setBoardFromTxt (String filePath){
        Path path = Paths.get(filePath);

        try (BufferedReader buffer = Files.newBufferedReader(path)){
            String line;

            if ((line = buffer.readLine()) != null){
                Board board = new Board(line.length());
                ArrayList<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()){
                    row.add(c);
                }
                board.boardElement.add(row);


                while ((line = buffer.readLine()) != null){
                    if (line.length() != board.getOrder()){
                        System.out.println("Gagal memuat board: Ordo dari board pada file tidak konsisten");
                        return null;
                    }
                    row = new ArrayList<>();
                    for (char c : line.toCharArray()){
                        row.add(c);
                    }
                    board.boardElement.add(row);
                }

                if (board.boardElement.size() != board.getOrder()){
                    System.out.println("Gagal memuat board: Ordo dari board pada file tidak konsisten");
                    return null;
                }

                return board;
            }else{
                System.out.println("Gagal memuat board: Pastikan baris pertama pada file input tidak kosong.");
                return null;
            }  
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**s
     * @implNote Menambahkan objek area pada atribut areas dengan keterurutan kecil ke besar berdasarkan jumlah koordinat pada objek Area.
     * @param area
     */
    public void addNewAreaSorted (Area newArea){
        for (int aIdx = 0; aIdx < this.areas.size(); aIdx++){
            if (this.areas.get(aIdx).getCoordinates().size() > newArea.getCoordinates().size()){
                this.areas.add(aIdx, newArea);
                return;
            }
        }
        this.areas.add(newArea);
    }


    //OPERASI PADA AREA, Pada algoritma ini boardElement hanya digunakan untuk generate Area, tetapi tidak digunakan untuk operasi queen dan blok pada board.
    /**
     * @implNote Mengidentifikasi area apa saja yang ada pada board (board tidak kosong), lalu menyimpannya pada atribut board. Area direpresentasikan dengan huruf kapital.
     */
    public void generateArea (){
        for (int row = 0; row < this.order; row++){
            for (int col = 0; col < this.order; col++){
                if (this.boardElement.get(row).get(col) != 'b' ){
                    boolean found = false;
                    for (Area tArea : this.areas){
                        if (this.boardElement.get(row).get(col) == tArea.getSymbol()){
                            tArea.addArea(row, col);
                            found = true;
                        }
                    }

                    if (!found){
                        Area newArea = new Area (this.boardElement.get(row).get(col));
                        newArea.addArea(row, col);
                        addNewAreaSorted(newArea);
                    }
                }
            }
        }
    }

    public void setAreaCoordinateType(String newType, int xInd, int yInd){
        boolean found = false;
        for (int arr = 0; arr < this.areas.size() && !(found); arr++){
            for (int cord = 0; cord < this.areas.get(arr).getCoordinates().size() && !(found); cord++){
                if (this.areas.get(arr).getCoordinates().get(cord).getCoordinate()[0] == xInd && this.areas.get(arr).getCoordinates().get(cord).getCoordinate()[1] == yInd){
                        this.areas.get(arr).getCoordinates().get(cord).setType(newType);
                        found = true;
                }
            }
        }
        // if (!(found)){
        //     System.out.println("Gagal mengubah tipe koordinat: koordinat tidak ditemukan");
        // }
    }

    /**
     * @implNote Mengeliminasi baris dan kolom pada board sesuai dengan posisi queen yang ada pada board tersebut, mengembalikan nilai dari baris dan kolom tersebut dengan 'b' (blocked).
     * @param index
     * @return 1 jika mengeliminasi suatu area yang memiliki properti "Queen", 0 jika tidak.
     */
    public boolean eliminateRownCol (int xInd, int yInd){
        boolean everEliminateQueen = false;
        for (int arr = 0; arr < this.areas.size(); arr++){
            for (int cord = 0; cord < this.areas.get(arr).getCoordinates().size(); cord++){
                if (this.areas.get(arr).getCoordinates().get(cord).getCoordinate()[0] == xInd){
                    if (this.areas.get(arr).getCoordinates().get(cord).getCoordinate()[1] != yInd){
                        if (this.areas.get(arr).getCoordinates().get(cord).getType().equals("Queen")){
                            everEliminateQueen = true;
                        }
                        this.areas.get(arr).getCoordinates().get(cord).setType("blocked");
                    }
                }else{
                    if (this.areas.get(arr).getCoordinates().get(cord).getCoordinate()[1] == yInd){
                        if (this.areas.get(arr).getCoordinates().get(cord).getType().equals("Queen")){
                            everEliminateQueen = true;
                        }
                        this.areas.get(arr).getCoordinates().get(cord).setType("blocked");
                    }
                }
            }
        }
        int[] diagonalElt1 = {xInd - 1, yInd + 1};
        int[] diagonalElt2 = {xInd + 1, yInd + 1};
        int[] diagonalElt3 = {xInd - 1, yInd - 1}; 
        int[] diagonalElt4 = {xInd + 1, yInd - 1};

        if (diagonalElt1[0] >= 0 && diagonalElt1[1] < this.order){
            setAreaCoordinateType("blocked", diagonalElt1[0], diagonalElt1[1]);
        }

        if (diagonalElt2[0] < this.order && diagonalElt2[1] < this.order){
            setAreaCoordinateType("blocked", diagonalElt2[0], diagonalElt2[1]);
        }


        if (diagonalElt3[0] >= 0 && diagonalElt3[1] >= 0){
            setAreaCoordinateType("blocked", diagonalElt3[0], diagonalElt3[1]);
        }


        if (diagonalElt4[0] >= 0 && diagonalElt4[1] < this.order){
            setAreaCoordinateType("blocked", diagonalElt4[0], diagonalElt4[1]);
        }
        return everEliminateQueen;
        
    }


    /**
     * @implNote Penambahan queen dilakukan hanya pada atribut queens saja, perlu dilakuakn evaluasi kembail untuk melihat apakah state-nya valid atau tidak.
     * @param xInd
     * @param yInd
     */
    public void addQueen (int xInd, int yInd){
        this.queens.add(new Coordinate("Queen", xInd, yInd));
    }

    public boolean setEliminationFromQueens(){
        boolean isEverEliminateQueen = false;
        for (Coordinate q : this.queens){
            this.setAreaCoordinateType("Queen", q.getCoordinate()[0], q.getCoordinate()[1]);
            boolean el =this.eliminateRownCol(q.getCoordinate()[0], q.getCoordinate()[1]);
            if (el){
                isEverEliminateQueen = true;
            }
        }
        return isEverEliminateQueen;
    }

    public int totalQueenInBoardAreas(){
        int total = 0;
        for (int arr = 0; arr < this.areas.size(); arr++){
            for (int cord = 0; cord < this.areas.get(arr).getCoordinates().size(); cord++){
                if (this.areas.get(arr).getCoordinates().get(cord).getType().equals("Queen")){
                        total++;
                }
            }
        }
        return total;
    }

    public void popLastQueens(){
        this.queens.remove(this.queens.size() - 1);
    }

    public void resetQueens(){
        this.queens.clear();
    }

    public void resetAreas(){
        this.areas.clear();
    }

    public char[][] displayBoard(){
        char[][] display = new char[this.order][this.order];
        // System.out.printf("Areas from display: %d\n", this.areas.size());
        // System.out.printf("Queens from display: %d\n", this.queens.size());

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                display[i][j] = '.';
            }
        }

        for (Area area : this.areas) {
            char symbol = area.getSymbol();
            for (Coordinate cord : area.getCoordinates()) {
                int[] pos = cord.getCoordinate();
                int x = pos[0];
                int y = pos[1];

                if (x >= 0 && x < order && y >= 0 && y < order) {
                    display[x][y] = symbol;
                }
            }
        }

        for (Coordinate queen : this.queens) {
            int[] pos = queen.getCoordinate();
            int x = pos[0];
            int y = pos[1];

            if (x >= 0 && x < order && y >= 0 && y < order) {
                display[x][y] = '#';
            }
        }

        System.out.println("Board:");
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                System.out.print(display[i][j] + " ");
            }
            System.out.println();
        }
        return display;
    }

    public void saveBoardState(char[][] display, String filePath){
        Path path = Paths.get(filePath);
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (char[] line : display){
                lines.add(new String(line));
            } 

            Files.write(path, lines);
            return;
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }
}
