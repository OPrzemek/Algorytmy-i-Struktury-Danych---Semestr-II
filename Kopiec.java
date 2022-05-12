package com.company;

import java.util.ArrayList;

public class Kopiec <T extends Comparable<T>> {
    public boolean czyJestKopcem() {
        int dlugosc = lista.size();
        boolean jestKopcem = true;
        for(int k=1; k<lista.size();k++){
            if(2*k >= dlugosc) return true;
            jestKopcem = lista.get(k).compareTo(lista.get(2*k)) >= 0;
            if(2*k+1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && lista.get(k).compareTo(lista.get(2*k+1)) >= 0;
            if(!jestKopcem) return false;
        }
        return true;
    }

    private void zanurzanie(int k) {
        int N = lista.size() - 1;
        int j;
        while (2*k <= N) {
            j = 2*k;
            if(j < N && lista.get(j).compareTo(lista.get(j+1)) <= 0) j++;
            if(!(lista.get(k).compareTo(lista.get(j)) <= 0)) break;
            T pom = lista.get(k);
            lista.set(k, lista.get(j));
            lista.set(j, pom);
            k = j;
        }
    }

    private void wynurzanie(int k) {
        while (k > 1 && lista.get(k/2).compareTo(lista.get(k)) < 0) {
            T pom = lista.get(k);
            lista.set(k, lista.get(k/2));
            lista.set(k/2, pom);
            k = k/2;
        }
    }

    public void drukuj() {
        for (T t : lista) {
            System.out.print(t + ", ");
        }
    }

    public void dopisz(T dopisywany){
        lista.add(dopisywany);
        wynurzanie(lista.size() - 1);
    }

    ArrayList<T> lista = new ArrayList<>();
    public static void main(String[] args) {
//        Kopiec<String> kopiec = new Kopiec<>();
//        String ListaA[] = {"", "Zbigniew", "Adam", "Bronisław", "Daniel"};
//        for(String s:ListaA) kopiec.dopisz(s);
        Kopiec<Integer> kopiec = new Kopiec<>();
        Integer[] ListaA = {0,5,8,19,13,1,4};
        for(Integer s:ListaA) kopiec.dopisz(s);
        System.out.println("Czy jest kopcem: " + kopiec.czyJestKopcem());
        kopiec.drukuj();
    }
}
