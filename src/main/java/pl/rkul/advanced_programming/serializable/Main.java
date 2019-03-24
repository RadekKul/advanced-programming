package pl.rkul.advanced_programming.serializable;

import java.io.*;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        // Serializacja jest to zapisywanie danych w postaci binarnej. Warunek konieczny to implementacja Serializable.
        // Rzeczy ktore nie moga byc serializable musimy je sami zapisac zeby nie dostac nulli, zer itp.
        // readObject i writeObject to pisanie procesu dzialania Serializable w taki sposob w jaki chcemy to uczynic
        // przy tym procesie musimy byc bardzo dokladni  - tak jak zapisalismy, tak samo musimy odczytac, takie same typy zmienny i taka sama kolejnosc
        // przy deserializacji nie jest wywolywany konstruktor klasy tworzonej, ale klas po ktorych dziedziczy juz jest chyba ze one tez sa serializable

        // Wykonamy zapis do pliku w postaci binarnej a nie tekstowej jak kiedys

        Person maybeMe = new Person("Radek", "K");
        Person fullPerson = new Person("M", "P", Optional.of("maniek"), new Address("warsaw", "newStreet", 12)); // tam jest Optional wiec musimy
        // napisac w Optional.of


        // przyklad z kotem

        Cat kotek = new Cat("Filemon",5,3,true);

        String myPath = "D:" +
                File.separator + "programowanie" +
                File.separator + "person.ser";    // zazwyczaj dla takich plikow dodajemy rozszerzenie .ser

        String myPath2 = "D:" +
                File.separator + "programowanie" +
                File.separator + "person2.ser";    // zazwyczaj dla takich plikow dodajemy rozszerzenie .ser



        try (ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(myPath));    // jezeli zrobimy try with resources to nie bedziemy musieli potem zamykac,
             ObjectOutputStream serializer2 = new ObjectOutputStream(new FileOutputStream(myPath2))){; // jezeli tego tak nie wywolamy to trzeba napisac w finally serializer.close()

            serializer.writeObject(maybeMe);        // przekazujemy do zapisu caly Object Person, musi miec ta klasa Serializable interfejs bo inaczej wywali nam wyjatek

            serializer2.writeObject(fullPerson);    // tu w jednym pliku zapisujemy dwa obiekty i tez to bedzie dzialac jezeli w readerze je odczytac w dobrej kolejnosci
            serializer2.writeObject(kotek);

        } catch (IOException e) {
            e.printStackTrace();
        }

       try (ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(myPath2))) {

           System.out.println("Before deserialization");
           Person maybeMy = (Person) deserializer.readObject();   // podczas odczytu widzimy ze nawet nie uzywa konstruktora mimo ze tworzymy nowy Person tak naprawdę
           Cat myCat = (Cat) deserializer.readObject();
           // na szczescie statyczne pola sie nie serializuja, przeszkadzalo by to np w nadawaniu ip czy ilosci, zeby nie zwiekszala nam liczby obiektów przy odczycie, bo bylo by to bez sensu
           System.out.println(maybeMy);
           System.out.println(myCat);

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }



    }
}
