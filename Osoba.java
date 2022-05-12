package com.company;

import java.text.Collator;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Locale;

@SuppressWarnings("NonAsciiCharacters")
public class Osoba implements Comparable<Osoba>{
    @Override
    public int compareTo(Osoba o) {
//        Collator c = Collator.getInstance(new Locale("pl","PL"));
//        int porównanieNazwisk = c.compare(this.nazwisko, o.nazwisko);
//        int porównanieImion = c.compare(this.imię, o.imię);
//        if(porównanieNazwisk == 0) return porównanieImion;
//        return porównanieNazwisk;
        double rW = this.wiek(this.dataUr) - o.wiek(o.dataUr);
        if(rW > 0) return 1;
        else if(rW < 0) return -1;
        else return 0;
    }

    String nazwisko = "Kowalski";
    String imię = "Jan";
    String dataUr = "1999-10-12";
    static LocalDate dzisiaj = LocalDate.now();
    static int bRok = dzisiaj.getYear();
    static int bMiesiąc = dzisiaj.getMonthValue();
    static ArrayList<Osoba> listaOsób = new ArrayList<>();

    @Override
    public String toString() {
        return nazwisko + " " + imię + " " + dataUr + " wiek " + String.format("%5.2f", wiek(dataUr));
    }

    double wiek(String dataUr) {
        String[] data = dataUr.split("-");
        LocalDate urodzenie = LocalDate.of(Integer.parseInt(data[0]),
                Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        Period per = Period.between(urodzenie, dzisiaj);
        return per.getYears() + per.getMonths()/12.0 + per.getDays()/365.0;
    }

    public Osoba(){}
    public Osoba (String nazwisko, String imie, String dataUr){
        this.nazwisko = nazwisko;
        this.imię = imie;
        this.dataUr = dataUr;
    }
}
