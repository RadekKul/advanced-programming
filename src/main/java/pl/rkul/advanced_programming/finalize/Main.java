package pl.rkul.advanced_programming.finalize;

import java.lang.ref.WeakReference;

public class Main {

    // ulubiona klasa
    // nadpisac metode finalize
    // utworzyc 100 obiektow i zobaczyc czy zostana uprzatniete

    public static void main(String[] args) throws Throwable {


        for (int i=0; i<1500000;i++){   // jezeli nadpisalismy finalize() - czyli dzialanie garbageColectora -  to zauwazymy ze rzeczywiscie dziala ten GarbageColector
            // bo w pustej petli zobaczymy ze wyswietla to co nadpisalismy w Cat
            Cat cat = new Cat("Mruczek","Czarny");
        }

        Cat cat1 = new Cat("ha","bia");

        // ta week referencja bedzie tylko wtedy kiedy na cat1 cos jeszcze wskazuje, w momencie kiedy zmieni sie cat1 ona taz sie zmienia na Nulla,
        //chyba ze cos innego zacznie wskazywac na cat1, wtedy ta weak referencja pozostaje cat1, wiec ginie ona kiedy NIC juz nie wskazuje na cat1
        WeakReference maybeCat = new WeakReference(cat1);

    }
}
