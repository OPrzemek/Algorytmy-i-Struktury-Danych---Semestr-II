package com.company;

import java.util.ArrayList;

public class MinSTree {
    public Krawedz[] krawedzDo;
    public double[] odlegloscDo;
    public boolean[] oznaczone;
    public double minWag;
    public int pozycjaMin;
    boolean czyPusta;

    GrafW graf;

    public MinSTree(int v){
        graf = new GrafW(v);
        krawedzDo = new Krawedz[v];
        odlegloscDo = new double[v];
        oznaczone = new boolean[v];
        for(int k = 0; k < v; k++)
            odlegloscDo[k] = Double.POSITIVE_INFINITY;
        pozycjaMin = -1;
    }

    public void badajMinimum(){
        pozycjaMin = -1;
        int i;
        for(i = 0; i < graf.V; i++)
        {
            if(pq.get(i) != null)
            {
                pozycjaMin=i;
                break;
            }
        }
        czyPusta = pozycjaMin == -1;
        if(czyPusta) return;
        minWag = pq.get(i);
        for(int ii = i+1; ii < graf.V; ii++)
        {
            if(pq.get(ii) != null && minWag > pq.get(ii))
            {
                minWag = pq.get(ii);
                pozycjaMin = ii;
            }
        }
        pq.set(pozycjaMin, null);
    }
    ArrayList<Double> pq = new ArrayList<Double>();

    public static void main(String[] args) {
        int ileWierz = 8;
        MinSTree obiektST = new MinSTree(ileWierz);
        obiektST.graf.dodajKrawedz(new Krawedz(6,0,0.58));
        obiektST.graf.dodajKrawedz(new Krawedz(0,2,0.26));
        obiektST.graf.dodajKrawedz(new Krawedz(0,4,0.38));
        obiektST.graf.dodajKrawedz(new Krawedz(0,7,0.16));
        obiektST.graf.dodajKrawedz(new Krawedz(1,3,0.29));
        obiektST.graf.dodajKrawedz(new Krawedz(1,2,0.36));
        obiektST.graf.dodajKrawedz(new Krawedz(1,7,0.19));
        obiektST.graf.dodajKrawedz(new Krawedz(1,5,0.32));
        obiektST.graf.dodajKrawedz(new Krawedz(6,2,0.41));
        obiektST.graf.dodajKrawedz(new Krawedz(2,7,0.34));
        obiektST.graf.dodajKrawedz(new Krawedz(2,3,0.17));
        obiektST.graf.dodajKrawedz(new Krawedz(3,6,0.52));
        obiektST.graf.dodajKrawedz(new Krawedz(6,4,0.93));
        obiektST.graf.dodajKrawedz(new Krawedz(4,7,0.37));
        obiektST.graf.dodajKrawedz(new Krawedz(4,5,0.35));
        obiektST.graf.dodajKrawedz(new Krawedz(5,7,0.28));

        for(int i = 0; i < ileWierz; i++)
            obiektST.pq.add(null);
        int v = 0;
        obiektST.odlegloscDo[0] = 0.0;
        obiektST.pq.add(0, 0.0);
        double poprz;
        while(true) {
            obiektST.badajMinimum();
            if(obiektST.pozycjaMin == -1) break;
            v = obiektST.pozycjaMin;
            System.out.println("\nbadanie " + v + "\n");
            obiektST.oznaczone[v] = true;
            for(Krawedz e: obiektST.graf.sasiedzi[v]){
                int w = e.drugi(v);
                System.out.println("krawedz " + v + " -> " + w + " " + e.waga());
                if(obiektST.oznaczone[w]) { System.out.println(w + "już onaczone"); continue; }
                if(e.waga() < obiektST.odlegloscDo[w]){
                    obiektST.krawedzDo[w] = e;
                    poprz = obiektST.odlegloscDo[w];
                    obiektST.odlegloscDo[w] = e.waga();
                    obiektST.pq.set(w, obiektST.odlegloscDo[w]);
                    System.out.println(w + " modyfikacja wagi "+ e.waga()+ " jest mniejsze od " + poprz);
                }
                else System.out.println(v + " " + w + " " + e.waga() + " nie jest mniejsze od " + obiektST.odlegloscDo[w]);
            }
        }
        System.out.println(" \nRozwiazanie: ");
        for(int i = 1; i < ileWierz; i++)
            System.out.println(obiektST.krawedzDo[i]);
        obiektST.badajMinimum();
    }
}