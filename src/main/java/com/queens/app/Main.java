package com.queens.app;
import com.queens.entities.*;
import com.queens.solver.Bruteforce;
import com.queens.solver.Optimized;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Scanner keyScanner = new Scanner(System.in);

        System.out.println("Masukkan Path file dari board yang ada di data :");
        System.out.print("src/main/resources/");
        String path = keyScanner.nextLine();

        Board board = Board.setBoardFromTxt("src/main/resources/" + path);
        board.generateArea();
        board.displayBoard();

        Bruteforce bf = new Bruteforce();
        Optimized op = new Optimized();
        ArrayList<Coordinate> pointCombination = new ArrayList<>();

        System.out.println("Pilih metode pencarian solusi: ");
        System.out.println("1. Bruteforce");
        System.out.println("2. Optimized");
        System.out.print("Masukan: ");int method = keyScanner.nextInt();

        if (method == 1){
            System.out.print("Masukkan jumlah iterasi pencarian solusi: ");
            int maxIter = keyScanner.nextInt();
            Instant startTime = Instant.now();
            bf.bruteforceQueenPos(board, 0, pointCombination, maxIter);
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            if (bf.getSolutionsFound()){
                System.out.println("Solusi ditemukan: ");
                System.out.print("Jumlah iterasi: "); System.out.println(bf.getIter());
                System.out.printf("Durasi: %d detik %d milidetik\n", duration.toSeconds(), duration.toMillisPart());
                char[][] display = board.displayBoard();
                System.out.print("Simpan hasil (Ya/Tidak): "); 
                String ans = keyScanner.nextLine(); //untuk skip "\n dari nextInt
                ans = keyScanner.nextLine();

                if (ans.equals("Ya")){
                    board.saveBoardState(display, "src/main/resources/result/result.txt");
                }
            }else{
                System.out.println("Solusi tidak ditemukan");
            }
        }else if (method == 2){
            Instant startTime = Instant.now();
            op.optimizedQueenPos(board, 0, pointCombination);
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            if (op.getSolutionsFound()){
                System.out.println("Solusi ditemukan ");
                System.out.print("Jumlah iterasi: "); System.out.println(op.getIter());
                System.out.printf("Durasi: %d detik %d milidetik\n", duration.toSeconds(), duration.toMillisPart());
                char[][] display = board.displayBoard();
                System.out.print("Simpan hasil (Ya/Tidak): "); 
                String ans = keyScanner.nextLine(); // untuk skip "\n dari nextInt"
                ans = keyScanner.nextLine();

                if (ans.equals("Ya")){
                    board.saveBoardState(display, "src/main/resources/result.txt");
                }
            }else{
                System.out.println("Solusi tidak ditemukan");
            }

        }else{
            System.out.println("Pilihan metode tidak valid");
        }

        keyScanner.close();
    }
}