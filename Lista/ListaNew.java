package com.company;

@SuppressWarnings("NonAsciiCharacters")
public class ListaNew {
    static class ObiektListy {
        Osoba element;
        ObiektListy następny = null;
        ObiektListy poprzedni = null;
    }

    ObiektListy pierwszy, ostatni;
    int N = 0;
    boolean jestPusta(){
        return N == 0;
    }

    void dodajDoListy(Osoba el){
        ObiektListy staryOstatni = ostatni;
        ostatni = new ObiektListy();
        ostatni.element = el;
        ostatni.poprzedni = staryOstatni;
        ostatni.następny = null;
        if(jestPusta()) pierwszy = ostatni;
        else staryOstatni.następny = ostatni;
        N++;
        wynurzanie();
    }

    void drukuj(){
        ObiektListy bieżący = pierwszy;
        while (bieżący.następny != null){
            System.out.println(bieżący.element);
            bieżący = bieżący.następny;
        }
        System.out.println(bieżący.element);
    }

    void drukujWstecz(){
        ObiektListy bieżący = ostatni;
        while (bieżący.poprzedni != null){
            System.out.println(bieżący.element);
            bieżący = bieżący.poprzedni;
        }
        System.out.println(bieżący.element);
    }

    Osoba pobierzPierwszego(){
        if(jestPusta()) return null;
        ObiektListy bieżący = pierwszy;
        if(bieżący.następny != null) {
            bieżący.następny.poprzedni = null;
            pierwszy = bieżący.następny;
        }
        N--;
        if(jestPusta()) ostatni = null;
        return bieżący.element;
    }

    ObiektListy pobierzItego(int ity){
        if(jestPusta()) return null;
        int j = 0;
        ObiektListy pierwszyL = pierwszy;
        if(ity == 0) return pierwszyL;
        while(ity != j){
            pierwszyL = pierwszyL.następny;
            j++;
        }
        return pierwszyL;
    }

    void zamień(int i, int j){
        if(i > N || j > N) return;
        ObiektListy ityObiekt = pobierzItego(i);
        ObiektListy jtyObiekt = pobierzItego(j);
        Osoba temp = ityObiekt.element;
        ityObiekt.element = jtyObiekt.element;
        jtyObiekt.element = temp;
    }

    public boolean czyJestKopcem() {
        int dlugosc = N-1;
        boolean jestKopcem = true;
        for(int k = 1; k < N; k++){
            if(2 * k >= dlugosc) return true;
            jestKopcem = pobierzItego(k).element.compareTo(pobierzItego(2*k).element) >= 0;
            if(2*k+1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && pobierzItego(k).element.compareTo(pobierzItego(2*k+1).element) >= 0;
            if(!jestKopcem) return false;
        }
        return true;
    }

    void wynurzanie(){
        int k = N - 1;
        ObiektListy pierwszyD = pobierzItego(k/2);
        ObiektListy drugiD = pobierzItego(k);
        boolean warunek = pierwszyD.element.compareTo(drugiD.element) < 0;
        while (k > 1 && warunek){
            zamień(k/2, k);
            k = k / 2;
            if(k == 1) break;
            pierwszyD = pobierzItego(k/2);
            drugiD = pobierzItego(k);
            warunek = pierwszyD.element.compareTo(drugiD.element) < 0;
        }
    }

    public static void main(String[] args) {
        //Tworzenie obiektów
        ListaNew obiekt = new ListaNew();
        obiekt.pierwszy = new ObiektListy();
        //Dopisywanie do listy
        obiekt.dodajDoListy(new Osoba("Pudzianowski", "Mariusz", "1978-07-14"));
        obiekt.dodajDoListy(new Osoba("Lewandowski", "Robert", "1993-06-18"));
        obiekt.dodajDoListy(new Osoba("Lewandowska", "Anna", "1995-07-22"));
        obiekt.dodajDoListy(new Osoba("Podsiadło", "Dawid", "1994-04-08"));
        obiekt.dodajDoListy(new Osoba("Paweł", "Jan", "2005-04-02"));
        obiekt.dodajDoListy(new Osoba("Małysz", "Adam", "1982-09-26"));
        //Modyfikacja listy
        System.out.println("Drukuj:");
        obiekt.drukuj();
        System.out.println("\nCzy jest kopcem = " + obiekt.czyJestKopcem());
//        System.out.println("\nDrukuj wstecz:");
//        obiekt.drukujWstecz();

//        System.out.println("\nPobrany:");
//        Osoba pobrany = obiekt.pobierzPierwszego();
//        System.out.println(pobrany);

//        System.out.println("\nPo pobraniu:");
//        obiekt.drukuj();
//        int ity = 1;

//        System.out.println("\nPobrany "+ ity + " element:");
//        ObiektListy pobranyIty = obiekt.pobierzItego(ity);
//        System.out.println(pobranyIty.element);
//        int jty = 3;

//        System.out.println("\nPo zamianie " + ity + " i " + jty + " elementu:");
//        obiekt.zamień(ity, jty);
//        obiekt.drukuj();
//        ObiektListy pobranyJty = obiekt.pobierzItego(jty);

//        System.out.println("\nPorównanie osoby " + ity + " i " + jty + " = " + pobranyIty.element.compareTo(pobranyJty.element));
    }
}
