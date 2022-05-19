package com.company;

import java.util.Iterator;

@SuppressWarnings({"SpellCheckingInspection"})
public class Graf {
    private final int V;
    private int E;
    public Worek<Integer>[] sasiedzi;

    private int s = 0;
    boolean[] oznakowane;
    int[] krawedzDo;

    public Graf(int V){
        this.V = V;
        this.E = 0;
        sasiedzi = (Worek<Integer>[]) new Worek[V];
        for(int v = 0; v < V; v++) sasiedzi[v] = new Worek<Integer>();
        oznakowane = new boolean[V];
        krawedzDo = new int[V];
    }

    public void dodajKrawedz(int v, int w){
        sasiedzi[v].dodaj(w);
        sasiedzi[w].dodaj(v);
        E++;
    }
    //Depth-First Search
    private void dfs(Graf g, int v){
        oznakowane[v] = true;
        for(int w : g.sasiedzi[v]){
            if(!oznakowane[w]){
                //System.out.println("badanie dfs " + w);
                krawedzDo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean jestSciezkaDo(int v){ return oznakowane[v]; }

    public Iterable<Integer> sciezkaDo(int v){
        if(!jestSciezkaDo(v)) return null;
        Worek<Integer> sciezka = new Worek<>();
        for(int x = v; x != s; x = krawedzDo[x]) sciezka.dodaj(x);
        sciezka.dodaj(s);
        return sciezka;
    }

    //Breadth-First Search
    private void bfs(Graf g, int v){
        Kolejka<Integer> kolejka = new Kolejka<Integer>();
        oznakowane[v] = true;
        kolejka.dodajDoKolejki(v);
        while(!kolejka.jestPusta()){
            int vv = kolejka.pobierzZKolejki();
            for(int w : g.sasiedzi[vv]){
                if(!oznakowane[w]){
                    //System.out.println("badanie bfs " + w);
                    krawedzDo[w] = vv;
                    oznakowane[w] = true;
                    kolejka.dodajDoKolejki(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graf graf = new Graf(10);
        //CZESC TRZECIA CWICZEN
        graf.dodajKrawedz(0,1);
        graf.dodajKrawedz(0,3);
        graf.dodajKrawedz(0,4);
        graf.dodajKrawedz(3,4);
        graf.dodajKrawedz(3,2);
        graf.dodajKrawedz(2,5);
        graf.dodajKrawedz(4,8);
        graf.dodajKrawedz(8,9);
        graf.dodajKrawedz(4,7);
        graf.dodajKrawedz(7,6);

        Iterator<Integer> it;
        for(int i = 0; i < graf.sasiedzi.length; i++){
            it = graf.sasiedzi[i].iterator();
            System.out.print("Nastepniki wierzchołka " + i + ":  ");
            while(it.hasNext()) System.out.print(it.next() + "  ");
            System.out.println();
        }
        System.out.println();

        graf.bfs(graf,0);

        Iterable<Integer> wynik = graf.sciezkaDo(5);
        System.out.println("Czy istnieje droga do wierzcholka 5?\nOdpowiedz: " + graf.jestSciezkaDo(5));
        if(graf.jestSciezkaDo(5)){
            System.out.println("\nSprawdzenie:");
            for(Integer i : wynik) System.out.print(" -> " + i);
        }
        System.out.println("\n");
        wynik = graf.sciezkaDo(6);
        System.out.println("Czy istnieje droga do wierzcholka 6?\nOdpowiedz: " + graf.jestSciezkaDo(6));
        if(graf.jestSciezkaDo(6)){
            System.out.println("\nSprawdzenie:");
            for(Integer i : wynik) System.out.print(" -> " + i);
        }



        //CZESC DRUGA CWICZEN
//        graf.dodajKrawedz(0,1);
//        graf.dodajKrawedz(1,2);
//        graf.dodajKrawedz(2,5);
//        graf.dodajKrawedz(0,3);
//        graf.dodajKrawedz(0,4);
//        graf.dodajKrawedz(4,8);
//        graf.dodajKrawedz(8,9);
//        graf.dodajKrawedz(4,7);
//        graf.dodajKrawedz(7,6);
//
//        Iterator<Integer> it;
//        for(int i = 0; i < graf.sasiedzi.length; i++){
//            it = graf.sasiedzi[i].iterator();
//            System.out.print("Nastepniki wierzchołka " + i + ":  ");
//            while(it.hasNext()) System.out.print(it.next() + "  ");
//            System.out.println();
//        }
//        System.out.println();
//
//        graf.dfs(graf,0);
//
//        System.out.println("Czy istnieje droga do wierzcholka 5?\nOdpowiedz: " + graf.jestSciezkaDo(5));
//        if(graf.jestSciezkaDo(5)){
//            System.out.println("\nSprawdzenie:");
//            for(Integer i : graf.sciezkaDo(5)) System.out.print(" -> " + i);
//        }
//        System.out.println("\n");
//
//        System.out.println("Sciezka do 6:");
//        Iterable<Integer> wynik =graf.sciezkaDo(6);
//        for(Integer i : wynik) System.out.print(" -> " + i);

        //CZESC PIERWSZA CWICZEN
//        graf.dodajKrawedz(0,5);
//        graf.dodajKrawedz(2,4);
//        graf.dodajKrawedz(2,3);
//        graf.dodajKrawedz(1,2);
//        graf.dodajKrawedz(0,1);
//        graf.dodajKrawedz(3,4);
//        graf.dodajKrawedz(3,5);
//        graf.dodajKrawedz(0,2);

//        int s = 0;
//        System.out.print("Sasiedzi liczby " + s + ": ");
//        for(Integer sasiad: graf.sasiedzi[s]){
//            System.out.print(sasiad + " ");
//        }

//        Iterator<Integer> it;
//        for(int i = 0; i < graf.sasiedzi.length; i++){
//            it = graf.sasiedzi[i].iterator();
//            System.out.print("Nastepniki wierzchołka " + i + ":  ");
//            while(it.hasNext()) System.out.print(it.next() + "  ");
//            System.out.println();
//        }
//        System.out.println();
//
//        //Depth-First Search
//        graf.dfs(graf, 0);
//        for(int i = 0; i < graf.V; i++)
//            System.out.println(i + ") " + graf.oznakowane[i] + "  " + graf.krawedzDo[i]);
//        System.out.println();
//
//        int sciezkaDo = 5;
//        for(int k = sciezkaDo; k != graf.s; k = graf.krawedzDo[k])
//            System.out.println(" -> " + k);
//        System.out.println(" -> " + graf.s);
//        System.out.println();
//
//        //Breadth-First Search
//        graf.bfs(graf, 0);
//        for(int i = 0; i < graf.V; i++)
//            System.out.println(i + ") " + graf.oznakowane[i] + "  " + graf.krawedzDo[i]);
//        System.out.println();
//
//        for(int k = sciezkaDo; k != graf.s; k = graf.krawedzDo[k])
//            System.out.println(" -> " + k);
//        System.out.println(" -> " + graf.s);
    }
}
