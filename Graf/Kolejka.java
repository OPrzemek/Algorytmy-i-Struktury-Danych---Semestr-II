package com.company;

import java.util.Iterator;

public class Kolejka <Element> implements Iterable<Element>{
    private Węzeł pierwszy;
    private Węzeł ostatni;
    private int N;

    private class Węzeł{
        Element element;
        Węzeł następny;
    }
    public boolean jestPusta() {return pierwszy == null;}
    public int size() {return N;}

    public void dodajDoKolejki(Element el) {
        Węzeł staryOstatni = ostatni;
        ostatni = new Węzeł();
        ostatni.element = el;
        ostatni.następny = null;
        if (jestPusta()) pierwszy=ostatni;
        else
            staryOstatni.następny=ostatni;
        N++;
    }
    public Element pobierzZKolejki() {
        Element el=pierwszy.element;
        pierwszy=pierwszy.następny;
        if (jestPusta()) ostatni = null;
        N--;
        return el;
    }
    public Iterator<Element> iterator(){return new LIterator();}
    public class LIterator implements Iterator<Element>
    {
        private Węzeł bieżący = pierwszy;
        public boolean hasNext() {return bieżący != null;}
        public Element next() {
            Element el = bieżący.element;
            bieżący=bieżący.następny;
            return el;
        }}}
