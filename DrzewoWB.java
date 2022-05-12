package com.company;

import java.awt.*;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
public class DrzewoWB<K1,K2> {
    private Wierzchołek korzeń;
    class Wierzchołek {
        K1 klucz;
        K2 wartość;
        Wierzchołek lewyPotomek;
        Wierzchołek prawyPotomek;
        int rozmiarP;
        public Wierzchołek(K1 a, K2 b, int N){
            this.klucz = a;
            this.wartość = b;
            this.rozmiarP = N;
        }
        public String toString(){
            return String.format("%-11s %-11s %02d", klucz, wartość, rozmiarP);
        }
    }

    Comparator <K1> compS = new Comparator<K1>() {
        final Collator c = Collator.getInstance(new Locale("pl", "PL"));
        public int compare(K1 s1, K1 s2){
            if (s1.getClass().getName().endsWith("Integer")) return (int)Math.signum((Integer)s1 - (Integer)s2 );
            return c.compare(s1, s2);
        }
    };

    public int rozmiar(Wierzchołek w){
        if(w == null) return 0;
        else return w.rozmiarP;
    }

    public void dopisz(K1 klucz, K2 wartość){
        korzeń = dopisz(korzeń, klucz, wartość);
    }

    private Wierzchołek dopisz(Wierzchołek w, K1 klucz, K2 wartość){
        if(w == null) return new Wierzchołek(klucz, wartość, 1);
        int porównanie = compS.compare(klucz, w.klucz);
        if (porównanie < 0) w.lewyPotomek = dopisz(w.lewyPotomek, klucz, wartość);
        else if (porównanie > 0) w.prawyPotomek = dopisz(w.prawyPotomek, klucz, wartość);
        else { w.wartość = wartość; }
        w.rozmiarP = rozmiar(w.prawyPotomek) + rozmiar(w.lewyPotomek) + 1;
        return w;
    }

    public Wierzchołek miejsceDla(K1 klucz, K2 wartość){ //Pozwala na wprowadzenie do drzewaWB w odpowiednie miejsce nowego wierzchołka
        String kierunek = "";
        Wierzchołek w = korzeń, wP = null;
        while (w != null){
            wP = w; //zapamiętanie odwiedzonego wierzchołka
            int porównanie = compS.compare(klucz, w.klucz);
            if (porównanie < 0) { kierunek = "lewy"; w = w.lewyPotomek; }         //klucz mniejszy - kierunek w lewo
            else if (porównanie > 0) { kierunek = "prawy"; w = w.prawyPotomek; }   //klucz większy - kierunek w prawo
            else { kierunek = "trafiony"; break; }                              //klucz trafiony
        }
        if (korzeń == null) korzeń = new Wierzchołek(klucz, wartość, 1);     //początek tworzenia drzewa
        else {
            if (kierunek.equals("lewy")) wP.lewyPotomek = new Wierzchołek(klucz, wartość, 1);   //kierunek w lewo
            if (kierunek.equals("prawy")) wP.prawyPotomek = new Wierzchołek(klucz, wartość, 1); //kierunek w prawo
            if (kierunek.equals("trafiony")) wP.wartość = wartość;                                 //trafienie - korekta wartości klucza
        }
        return wP;
    }

    public Wierzchołek znajdz(Wierzchołek w, K1 klucz){ //Wyszukuje wierzchołek, na zwrocie odpowiedniego adresu zwraca referencję danego wierzchołka
        if(w == null) return null;
        int porównanie = compS.compare(klucz, w.klucz);
        if(porównanie < 0) return znajdz(w.lewyPotomek, klucz);
        if(porównanie > 0) return znajdz(w.prawyPotomek, klucz);
        else return w;
    }

    public int zwrocPoziom(K1 klucz){
        Wierzchołek w = korzeń;
        int poziom = 0;
        while(w != null){ poziom++;
            int porównanie = compS.compare(klucz, w.klucz);
            if(porównanie < 0) w = w.lewyPotomek;
            else if(porównanie > 0) w = w.prawyPotomek;
            else return poziom;
        }
        return -1;
    }


    public static void main(String[] args) {
        DrzewoWB<String, String> drzewo = new DrzewoWB<>();
        //Tworzenie drzewa
        /*
        drzewo.dopisz("Zawada", "2011");
        drzewo.dopisz("Środa", "2020-12-12");
        drzewo.dopisz("Gródek", "1990");
        drzewo.dopisz("Alabama", "2020-11-20");
        drzewo.dopisz("Aleksandria", "2018-01-23");
        drzewo.dopisz("Żabińska", "2011");
        drzewo.dopisz("Słupsk", "2010-10-12");
        drzewo.dopisz("Gdańsk", "2010");
        drzewo.dopisz("Kraków", "1999");
        */
        drzewo.dopisz("Alabama", "2020-11-20");
        drzewo.dopisz("Aleksandria", "2018-01-23");
        drzewo.dopisz("Gdańsk", "2010");
        drzewo.dopisz("Gródek", "1990");
        drzewo.dopisz("Kraków", "1999");
        drzewo.dopisz("Słupsk", "2010-10-12");
        drzewo.dopisz("Środa", "2020-12-12");
        drzewo.dopisz("Zawada", "2011");
        drzewo.dopisz("Żabińska", "2011");


        String tekst = "Struktura danych (ang. data structure) – " +
                "sposób przechowywania danych w pamięci komputera. " +
                "Na strukturach danych operują algorytmy.";

//        System.out.println(drzewo.korzeń.lewyPotomek);
//        Wierzchołek najmniejszy = ;
//        while(najmniejszy.lewyPotomek != null){
//
//        }
        DrzewoWB<String, String>.Wierzchołek szukany = drzewo.znajdz(drzewo.korzeń, "Gródek");
        System.out.println("Szukany: " + szukany);
        System.out.println("Lewy potomek: " + szukany.lewyPotomek);
        System.out.println("Prawy potomek: " + szukany.prawyPotomek);
        String klucz = "Żabińska";
        System.out.println("Poziom klucza " + klucz + ": " + drzewo.zwrocPoziom(klucz));
    }
}
