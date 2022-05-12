package com.company;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
public class DrzewoWBN<K1,K2> {
    private Wierzchołek korzeń;
    class Wierzchołek implements Comparable<Wierzchołek>{
        K1 klucz; K2 wartość;
        int ile = 0;
        Wierzchołek lewyPotomek, prawyPotomek;
        int rozmiarP = 0;
        public Wierzchołek(K1 a, K2 b, int N){
            this.klucz = a;
            this.wartość = b;
            this.ile = N;
            this.rozmiarP = N;
        }
        public String toString() { return String.format("klucz: %-2s wartość: %-2s rozmiarP: %02d  ile: %02d",
                klucz, wartość, rozmiarP, ile); }

        @Override
        public int compareTo(Wierzchołek w) {
            return compS.compare(this.klucz,  w.klucz);
        }
    }
    Collator c = Collator.getInstance(new Locale("pl", "PL"));

    Comparator<K1> compS = new Comparator<K1>() {
        @Override
        public int compare(K1 s1, K1 s2) {
            if(s1.getClass().getName().endsWith("Integer"))
                return (int)Math.signum((Integer)s1 - (Integer)s2);
            if(s1.getClass().getName().endsWith("Character"))
                return c.compare(String.valueOf(s1), String.valueOf(s2));
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
        else { w.wartość = wartość; w.ile++; }
        w.rozmiarP = rozmiar(w.prawyPotomek) + rozmiar(w.lewyPotomek) + 1;
        return w;
    }

    ArrayList<Wierzchołek> kolejka = new ArrayList<>();
    public void przesukajZakres(Wierzchołek w, K1 dolny, K1 górny){
        if(w == null) return;
        int dZDanym = compS.compare(dolny, w.klucz);
        int gZDanym = compS.compare(górny, w.klucz);
        if(dZDanym < 0) przesukajZakres(w.lewyPotomek, dolny, górny);
        if(dZDanym <= 0 && gZDanym >= 0) kolejka.add(w);
        if(gZDanym > 0) przesukajZakres(w.prawyPotomek, dolny, górny);
    }

    /**
     Wyszukuje wierzchołek, na zwrocie odpowiedniego adresu zwraca referencję danego wierzchołka.
     */
    public Wierzchołek znajdz(Wierzchołek w, K1 klucz){
        if(w == null) return null;
        int porównanie = compS.compare(klucz, w.klucz);
        if(porównanie < 0) return znajdz(w.lewyPotomek, klucz);
        if(porównanie > 0) return znajdz(w.prawyPotomek, klucz);
        else return w;
    }

    /**
     Zwraca referencję do wierzchołka zawierającego dany klucz. Jeżeli taki wierzchołek nie występuje zwracana jest wartość pusta.
     */
    public Wierzchołek czyWystępuje(K1 klucz){
        Wierzchołek w = korzeń;
        while(w != null){
            int porównanie = compS.compare(klucz, w.klucz);
            if(porównanie < 0) w = w.lewyPotomek;
            else if(porównanie > 0) w = w.prawyPotomek;
            else return w;
        }
        return null;
    }

    public static void main(String[] args) {
        String tekst = "Struktura danych (ang. data structure) – " +
                "sposób przechowywania danych w pamięci komputera. " +
                "Na strukturach danych operują algorytmy. " +
                "Podczas implementacji programu programista często staje przed wyborem między różnymi strukturami danych, " +
                "aby uzyskać pożądany efekt. Odpowiedni wybór może zmniejszyć złożoność obliczeniową, " +
                "ale z drugiej strony trudność implementacji danej struktury może stanowić istotną przeszkodę.";
        char x;
        DrzewoWBN<Character, Integer> drzS = new DrzewoWBN<>();
        for(int i = 0; i < tekst.length(); i++){
            x = tekst.charAt(i);
            drzS.dopisz(x, 1);

        }
        System.out.println("zrobione");
        DrzewoWBN<Character, Integer>.Wierzchołek adresZ = drzS.znajdz(drzS.korzeń, 'g');
        System.out.println(adresZ);
        DrzewoWBN<Character, Integer>.Wierzchołek adresY = drzS.znajdz(drzS.korzeń, 'p');
        System.out.println(adresY);
        drzS.przesukajZakres(drzS.korzeń, ' ', 'Ż');

//        DrzewoWBN.Wierzchołek[] lista = new DrzewoWBN.Wierzchołek[drzS.kolejka.size()];
        for(int i = 0; i < drzS.kolejka.size(); i++) {
            System.out.println((i + 1) + ") " + drzS.kolejka.get(i));
            //lista[i] = drzS.kolejka.get(i);
        }
        char jakis = 'A';
        boolean wystepuje = drzS.czyWystępuje(jakis) != null;
        System.out.println("Czy występuje '" + jakis + "': " + wystepuje);

    }
}