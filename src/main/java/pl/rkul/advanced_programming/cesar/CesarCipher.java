package pl.rkul.advanced_programming.cesar;

import java.util.Arrays;

public class CesarCipher {

    //2 metody: encrypt i decrypt
    // argumentem jest String  i int(przesuniecie)
    // spacje nie są szyfrowane (szyfrujemy tylko znaki alfabetu)

    public static void main(String[] args) {

        //97 - 122 a-z
        //65-90 A-Z

        System.out.println(cipher("zuza", 2));
    }

    //TODO zapytaj Mariusza o to jak najszybciej posortowac GUI jezeli zrobiles tam tabele javową i dolaczyles baze danych i chcesz sortowac po dwoch kolumnach

    //TODO zrobic w druga strone

    public static String cipher(String word, int rightOffset) {

        StringBuilder result = new StringBuilder();
        final int normalizationConst = 1;

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);  // przypisujemy do zmiennej current wartosc char danej literki ktora przemieniamy
            int numberOfLetters = 'z' - 'a' + normalizationConst; // ustalamy ile jest liter i tyle bedzie maksymalnie trwalo nasze "kolo" przy szyfrowaniu, dodajemy +1 zeby dostac dobra ilosc znakow


            if ((current >= 'a' && current <= 'z')) {
                result.append(
                        (char) ((current + rightOffset - 'a')%numberOfLetters + 'a')); // jezeli do char dodamy int to samo sie zmienia na inta i wartosc danego znaku, wazne!!!
            } else if((current >= 'A' && current <= 'Z')){
                result.append(
                (char) ((current + rightOffset - 'A')%numberOfLetters + 'A'));
            } else {
                result.append(current);
            }
        }
        return result.toString();
    }
}
