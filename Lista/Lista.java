package com.company;

import javax.lang.model.element.Element;

@SuppressWarnings("NonAsciiCharacters")
public class Lista {
    static class ObiektListy {
        Osoba element;
        ObiektListy następny = null;
    }

    ObiektListy pierwszy;
    void drukuj(){
        ObiektListy bieżący = pierwszy;
        while (bieżący.następny != null){
            System.out.println(bieżący.element);
            bieżący = bieżący.następny;
        }
        System.out.println(bieżący.element);
    }

    void zamień(int indeks, String nazwisko){
        ObiektListy zmieniany = pierwszy;
        for(int i = 0; i < 1000; i++){
            if(i == indeks) break;
            zmieniany = zmieniany.następny;
        }
        zmieniany.element.nazwisko = nazwisko;
    }


    public static void main(String[] args) {
        String[][] osoby = {{"Nowak", "Wojciech", "2000-02-29"},
                {"Nowakowski", "Piotr", "2000-03-29"},
                {"Adamski", "Adam", "2000-10-09"},
                {"Cegielski", "Zbigniew", "1999-11-09"}};
        Lista obiekt = new Lista();
        obiekt.pierwszy = new ObiektListy();
        obiekt.pierwszy.element = new Osoba(osoby[0][0], osoby[0][1], osoby[0][2]);
        ObiektListy kolejny = obiekt.pierwszy;
        for(int i = 1; i < osoby.length; i++){
            kolejny.następny = new ObiektListy();
            kolejny.następny.element = new Osoba(osoby[i][0], osoby[i][1], osoby[i][2]);
            kolejny = kolejny.następny;
        }
        System.out.println("Przed zmiana nazwiska: ");
        obiekt.drukuj();
        System.out.println("Po zmianie nazwiska: ");
        obiekt.zamień(2, "Stefański");
        obiekt.pierwszy.następny.element.imię = "Onufry";
        obiekt.pierwszy.następny.następny.element.dataUr = "2005-12-01";
        obiekt.drukuj();
    }
}
