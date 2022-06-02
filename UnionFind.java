package com.company;

@SuppressWarnings({"SpellCheckingInspection"})
public class UnionFind {
    static int wielkosc = 10;
    static int skladowe = 10;
    static int[] reprezentanci = new int[wielkosc];
    static int[] reprezentanciA = new int[wielkosc];
    static int[] wielkosci = new int[wielkosc];

    static int wyszukaj(int x){
        return reprezentanci[x];
    }
    static boolean polacz(int x, int y){
        int sX = wyszukaj(x);
        int sY = wyszukaj(y);
        if(sX == sY) return false;
        else{
            for(int i = 0; i < wielkosc; i++){
                if(reprezentanci[i] == sX) reprezentanci[i] = sY;
            }
            skladowe--;
            return true;
        }
    }
    public static boolean polaczA(int x, int y){
        int sX = wyszukaj(x);
        int sY = wyszukaj(y);
        if(sX == sY) return false;
        if(wielkosci[sX] < wielkosci[sY]){
            reprezentanciA[sX] = sY;
            wielkosci[sY] += wielkosci[sX];
        }
        else{
            reprezentanciA[sY] = sX;
            wielkosci[sX] += wielkosci[sY];
        }
        skladowe--;
        return true;
    }

    public static void main(String[] args) {
        polaczA(4,3);
        polaczA(3,8);
        polaczA(6,5);
        polaczA(2,1);
        polaczA(5,0);
        polaczA(7,2);
        polaczA(5,9);
        int rep1 = wyszukaj(0);
        int rep2 = wyszukaj(6);
        System.out.println("skladowych " + skladowe);
        if(rep1 == rep2) System.out.println("Istnieje sciezka.");
        else System.out.println("Nie istnieje sciezka.");
    }
}