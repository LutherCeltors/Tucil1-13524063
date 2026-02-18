package com.queens.entities;

public class Coordinate {
    private String type;
    private int xInd;
    private int yInd;

    /**
     * @implNote Coordinate memiliki 2 tipe, yaitu sebagai koordinat queen ("Queen"), ataupun sebagai koordinat dari setiap kotak dalam suatu daerah ("AreaElmt").
     * @param type "Queen" or "AreaElmt" or "Blocked"
     * @param xInd
     * @param yInd
     */
    public Coordinate (String type, int xInd, int yInd){
        this.type = type;
        this.xInd = xInd;
        this.yInd = yInd;
    }

    /**
     * @implNote Hal yang bisa diubah adalah elemen x dan y pada kordinat.
     * @param newXInd
     * @param newYInd
     */
    public void setCoordinate (int newXInd, int newYInd){
        this.xInd = newXInd;
        this.yInd = newYInd;
    }
    
    /**
     * 
     * @param newType "Queen" or "AreaElmt" or "Blocked"
     */
    public void setType (String newType){
        this.type = newType;
    }

    public int[] getCoordinate (){
        int[] coordinate = {this.xInd, this.yInd};
        return coordinate;
    }

    public String getType(){
        return this.type;
    }
}
