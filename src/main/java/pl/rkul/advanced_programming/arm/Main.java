package pl.rkul.advanced_programming.arm;

public class Main {

    public static void main(String[] args) {
        mustBeClose();
    }

    private static void mustBeClose(){

        try (MyOwnClosable myOwnClosable = new MyOwnClosable()){    // wywolanie metody ktora sama wykona metode close() tam gdzie sie konczy "zywot" obiektu czyli w naszym przypadku
            // na koncu try, bo tylko w tej petli bedzie zyl i rzeczywiscie wywoluje nam to co w MyOwnClosable w close(), MUSIMY IMPLEMENTOWAC AUTOCLOSABLE
            // instrukcja ta musi zostac wpisana jako parametr try() a nie jego cialo!!!
        }
    }
}