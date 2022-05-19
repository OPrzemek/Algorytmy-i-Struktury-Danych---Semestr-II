package com.company;

@SuppressWarnings({"SpellCheckingInspection","NonAsciiCharacters"})
public class GrafW { //Graf ważony
    final int V;
    private int E;
    public Worek<Krawedz>[] sasiedzi;
    public GrafW(int V){
        this.V = V;
        this.E = 0;
        sasiedzi = (Worek<Krawedz>[]) new Worek[V];
        for(int v = 0; v < V; v++) sasiedzi[v] = new Worek<Krawedz>();
    }
    public void dodajKrawedz(Krawedz krawedz){
        int v = krawedz.pierwszy(), w = krawedz.drugi(v);
        sasiedzi[v].dodaj(krawedz);
        sasiedzi[w].dodaj(krawedz);
        E++;
    }
    public Iterable<Krawedz> sasiednie(int v){
        return sasiedzi[v];
    }

    public static void main(String[] args) {
        GrafW ważonyGraf=new GrafW(8);
        ważonyGraf.dodajKrawedz(new Krawedz(6,0,0.58));
        ważonyGraf.dodajKrawedz(new Krawedz(0,2,0.26));
        ważonyGraf.dodajKrawedz(new Krawedz(0,4,0.38));
        ważonyGraf.dodajKrawedz(new Krawedz(0,7,0.16));
        ważonyGraf.dodajKrawedz(new Krawedz(1,3,0.29));
        ważonyGraf.dodajKrawedz(new Krawedz(1,2,0.36));
        ważonyGraf.dodajKrawedz(new Krawedz(1,7,0.19));
        ważonyGraf.dodajKrawedz(new Krawedz(1,5,0.32));
        ważonyGraf.dodajKrawedz(new Krawedz(6,2,0.41));
        ważonyGraf.dodajKrawedz(new Krawedz(2,7,0.34));
        ważonyGraf.dodajKrawedz(new Krawedz(2,3,0.17));
        ważonyGraf.dodajKrawedz(new Krawedz(3,6,0.52));
        ważonyGraf.dodajKrawedz(new Krawedz(6,4,0.93));
        ważonyGraf.dodajKrawedz(new Krawedz(4,7,0.37));
        ważonyGraf.dodajKrawedz(new Krawedz(4,5,0.35));
        ważonyGraf.dodajKrawedz(new Krawedz(5,7,0.28));

        Worek<Krawedz> worek;
        for(int i = 0; i < 8; i++){
            System.out.println("Sasiedzi " + i + ":");
            worek = ważonyGraf.sasiedzi[i];
            for(Krawedz j : worek) System.out.println(j);
        }

    }
}
