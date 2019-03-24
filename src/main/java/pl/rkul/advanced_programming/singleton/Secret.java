package pl.rkul.advanced_programming.singleton;

public class Secret {   // singleton to nazwa dla klasy ktorej obiekt jest tworzony tylko raz i to nie przez konstruktor, konstruktor jest pusty, a stworzenie singletona
    // wolamy poprzez getInstance, ktora tworzy nam singleton, tylko raz, jezeli ktos wywola drugi raz to zwroci mu zawsze pierwsza i jedyna instancje

    private static Secret INSTANCE;     // instancja musi byc static, bo jest jednak dla calej klasy
    private Secret(){
        //empty
    }

    public static Secret getInstance(){     // wchodza tu dwa watki na raz, sprawdzaja ze nie ma jeszcze metody, obydwa przechodza z nullem, wtedy synchronized wpuszcza tylko jeden z watkow
        if(null==INSTANCE){                 // dalej i to on stworzy na wlasna reke pierwsza i zarazem ostatnio instancje czyli singleton. Kolejny ktory wejdzie, nie przejdzie juz bo instance nie bedzie nullem
            synchronized (Secret.class){    //secret.class - jest to jeden obiekt dla tej calej klasy, dlatego na nim synchronizujemy
                if (null==INSTANCE){
                    INSTANCE = new Secret();
                }
            }
        }
        return INSTANCE;
    }


}
