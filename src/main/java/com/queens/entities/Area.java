package com.queens.entities;
import java.util.ArrayList;

public class Area {
    private char symbol;
    private ArrayList<Coordinate> coordinates;

    public Area (char symbol) {
        this.symbol = symbol;
        this.coordinates = new ArrayList<>();
    }

    /**
     * @implNote Simbol pada Area ditentukan pada saar konstruksi objek dan tidak dapat dibuah pada saat runtime, hanya dapat diambil saja.
     * @return atribut symbol dari objek Area.
     */
    public char getSymbol(){
        return this.symbol;
    }

    public ArrayList<Coordinate> getCoordinates(){
        return this.coordinates;
    }

    public void addArea (int xIdx, int yIdx){
        Coordinate newCord = new Coordinate("AreaElmt", xIdx, yIdx);
        this.coordinates.add(newCord);
    }
}
