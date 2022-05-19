package com.company;

public class Krawedz implements Comparable<Krawedz>{
    private int v; //pierwszy wierzcholek
    private int w; //drugi wierzcholek
    private double waga; //waga krawedzi
    public Krawedz(int v, int w, double waga){
        this.v = v;
        this.w = w;
        this.waga = waga;
    }
    public double waga() { return waga; }
    public int pierwszy() { return v; }
    public int drugi(int nrWierzcholka){
        if(nrWierzcholka == v) return w;
        else if(nrWierzcholka == w) return v;
        else return v;
    }
    public int compareTo(Krawedz innaKrawedz){
        if(this.waga() < innaKrawedz.waga()) return -1;
        else if(this.waga > innaKrawedz.waga()) return 1;
        else return 0;
    }
    public String toString() { return String.valueOf(v) + " - " + String.valueOf(w) + " - " + String.format(" %4.3f", waga); }
}
