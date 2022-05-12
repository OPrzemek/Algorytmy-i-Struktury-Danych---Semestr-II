package com.company;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
public class DrzewoCC<Klucz, Wartość> { //Drzewo Czerwono czarne
    //implementacja drzew czarno-czerwonych
    Comparator<Klucz> compS = new Comparator<Klucz>() {
        final Collator c = Collator.getInstance(new Locale("pl", "PL"));

        public int compare(Klucz s1, Klucz s2) {
            if (s1.getClass().getName().endsWith("Integer")) {
                return (int) Math.signum((Integer) s1 - (Integer) s2);
            }
            return c.compare(s1,s2);
        }};
    static final boolean CZERWONY = true;
    static final boolean CZARNY = false;
    private Wierzchołek korzeń;
    class Wierzchołek{
        Klucz klucz;
        Wartość wartość;
        Wierzchołek lewyPotomek, prawyPotomek;
        int N = 0;
        boolean kolor;
        public Wierzchołek(Klucz a, Wartość b, int N, boolean kolor){
            this.klucz = a;
            this.wartość = b;
            this.N = N;
            this.kolor = kolor;
        }
        public String toString(){
            return String.format("%12s  %12s wielkość %5s", klucz.toString(), wartość.toString(), String.valueOf(N));
        }
    }

    public int rozmiar(){ return rozmiar(korzeń); }

    private int rozmiar(Wierzchołek w){
        if(w == null) return 0;
        else return w.N;
    }

    public Wierzchołek rotacjaLewa(Wierzchołek h){
        //obracamy wokół wierzchołka h
        Wierzchołek x = h.prawyPotomek;
        h.prawyPotomek = x.lewyPotomek;
        x.lewyPotomek = h;
        x.kolor = h.kolor;
        h.kolor = CZERWONY;
        x.N = h.N;
        h.N = 1 + rozmiar(h.lewyPotomek) + rozmiar(h.prawyPotomek);
        return x;
    }

    public Wierzchołek rotacjaPrawa(Wierzchołek h){
        //obracamy wokół wierzchołka h
        Wierzchołek x = h.lewyPotomek;
        h.lewyPotomek = x.prawyPotomek;
        x.prawyPotomek = h;
        x.kolor = h.kolor;
        h.kolor = CZERWONY;
        x.N = h.N;
        h.N = 1 + rozmiar(h.lewyPotomek) + rozmiar(h.prawyPotomek);
        return x;
    }

    void zmienKolory(Wierzchołek h){
        h.kolor = CZERWONY;
        h.lewyPotomek.kolor = CZARNY;
        h.prawyPotomek.kolor = CZARNY;
    }

    public void dopisz(Klucz klucz, Wartość wartość){
        korzeń = dopisz(korzeń, klucz, wartość);
        korzeń.kolor = CZARNY;
    }

    private Wierzchołek dopisz(Wierzchołek w, Klucz klucz, Wartość wartość){
        if(w == null) return new Wierzchołek(klucz, wartość, 1, CZERWONY);
        int porównanie = compS.compare(klucz, w.klucz);
        if (porównanie < 0) w.lewyPotomek = dopisz(w.lewyPotomek, klucz, wartość);
        else if (porównanie > 0) w.prawyPotomek = dopisz(w.prawyPotomek, klucz, wartość);
        else w.wartość = wartość;
        if(jestCzerwony(w.prawyPotomek) && !jestCzerwony(w.lewyPotomek)) w = rotacjaLewa(w);
        if(jestCzerwony(w.lewyPotomek) && jestCzerwony(w.lewyPotomek.lewyPotomek)) w = rotacjaPrawa(w);
        if(jestCzerwony(w.prawyPotomek) && jestCzerwony(w.lewyPotomek)) zmienKolory(w);
        w.N = rozmiar(w.prawyPotomek) + rozmiar(w.lewyPotomek) + 1;
        return w;
    }

    boolean jestCzerwony(Wierzchołek x){
        if(x == null) return false;
        return x.kolor == CZERWONY;
    }

    ArrayList<Wierzchołek> kolejka = new ArrayList<>();
    public void przesukajZakres(Wierzchołek w, Klucz dolny, Klucz górny){
        if(w == null) return;
        int dZDanym = compS.compare(dolny, w.klucz);
        int gZDanym = compS.compare(górny, w.klucz);
        if(dZDanym < 0) przesukajZakres(w.lewyPotomek, dolny, górny);
        if(dZDanym <= 0 && gZDanym >= 0) kolejka.add(w);
        if(gZDanym > 0) przesukajZakres(w.prawyPotomek, dolny, górny);
    }

    public int wysokoscDrzewa(){
        if(kolejka.isEmpty()) return -1;
        int wysokosc = 0;
        int poziomKlucza = 0;
        for(Wierzchołek w : kolejka){
            poziomKlucza = zwrocPoziom(w.klucz);
            if(wysokosc < poziomKlucza)
                wysokosc = poziomKlucza;
        }
        return wysokosc;
    }

    public int zwrocPoziom(Klucz klucz){
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
        DrzewoCC<String, String> drzewo = new DrzewoCC<>();
        //Tworzenie drzewa
        drzewo.dopisz("Alabama", "2020-11-20");
        drzewo.dopisz("Aleksandria", "2018-01-23");
        drzewo.dopisz("Gdańsk", "2010");
        drzewo.dopisz("Gródek", "1990");
        drzewo.dopisz("Kraków", "1999");
        drzewo.dopisz("Słupsk", "2010-10-12");
        drzewo.dopisz("Środa", "2020-12-12");
        drzewo.dopisz("Zawada", "2011");
        drzewo.dopisz("Żabińska", "2011");
        System.out.println("Korzen to: " + drzewo.korzeń);
        drzewo.przesukajZakres(drzewo.korzeń, "Alabama", "Żabińska");
        System.out.println("Wysokosc drzewa to: " + drzewo.wysokoscDrzewa());
    }
}