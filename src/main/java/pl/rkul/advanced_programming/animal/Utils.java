package pl.rkul.advanced_programming.animal;

public class Utils {


    private Utils(){

        throw new UnsupportedOperationException("Not yet implemented");
        // musimy tutaj to zrobic zeby sie zabezpieczyc przed uzyciem tego konstruktora, bo bedzie to klasa pomocna, majaca same statyczne metody pomocnicze
    }
    public static long convertSecToMiliSec(long timeIntervalInSec){
        return timeIntervalInSec*1000;
    }
}
