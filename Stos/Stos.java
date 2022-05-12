package com.company;

import java.util.Random;

@SuppressWarnings("NonAsciiCharacters")
public class Stos <Element>{
    public ObiektStosu pierwszy = null;
    private int N;

    public class ObiektStosu{
        Element element;
        ObiektStosu elPoniżej = null;
    }

    public boolean czyPusty() { return N==0; }
    public int rozmiar() { return N; }

    public void połóżNaStosie(Element el){
        ObiektStosu sPierwszy = pierwszy;
        pierwszy = new ObiektStosu();
        pierwszy.element = el;
        pierwszy.elPoniżej = sPierwszy;
        N++;
    }
    public Element zdejmijZeStosu() {
        Element pobrany = pierwszy.element;
        pierwszy = pierwszy.elPoniżej;
        N--;
        return pobrany;
    }

    public static void main(String[] args) {
        Stos<Integer> stos = new Stos<>();

        //String ONP = "1 3 + 5 6 2 / * - 2 7 * +"; //Wynik to: 3
        String ONP = "-4 8 4 / 3 * + 12 6 3 / + - 5 4 * +"; //Wynik to: 16
        String[] podzielonyONP = ONP.split(" ");
        int k = 0;
        while (k < podzielonyONP.length){
            String el = podzielonyONP[k];
            if(el.matches("[0-9]*") || el.matches("-[0-9]+")) stos.połóżNaStosie(Integer.valueOf(el));
            else{
                int a = stos.zdejmijZeStosu();
                int b = stos.zdejmijZeStosu();
                switch (el) {
                    case "+" -> stos.połóżNaStosie(b + a);
                    case "-" -> stos.połóżNaStosie(b - a);
                    case "/" -> stos.połóżNaStosie(b / a);
                    case "*" -> stos.połóżNaStosie(b * a);
                }
            }
            k++;
        }
        System.out.println("Wynik to: " + stos.pierwszy.element);
//        Pierwsza część zajęć
//        Random random = new Random();
//        for(int i = 0; i < 20; i++){
//            Integer liczba = random.nextInt(100);
//            System.out.println("Położono na stosie: " + liczba);
//            stos.połóżNaStosie(liczba);
//        }
//        stos.zdejmijZeStosu();
//        System.out.println("Pierwszy element stosu po zdjęciu to: " + stos.pierwszy.element);
    }
}
