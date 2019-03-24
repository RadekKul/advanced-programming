package pl.rkul.advanced_programming.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Optional;

public class Person implements Serializable {

    private static final long serialVersionUID = 9123297892293469266L;// unikalny kod nadany dla classy - wlaczony w settingsach
    private String name;
    private String surname;
    private transient Optional<String> nick;    // transient to slowo kluczowe
    private transient Optional<Integer> littleInt;    // transient to slowo kluczowe
    private Address address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        System.out.println("Konstruktor z name i surname");
    }

    public Person(String name, String surname, Optional<String> nick, Address address) {
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.address = address;
        System.out.println("konstruktor ze wszystkim");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nick=" + nick +
                ", address=" + address +
                '}';
    }

    private void writeObject(ObjectOutputStream writer) throws IOException {    // dwie metody wlaczone w Settingsach ktore mozemy nadpisac zeby czytalo i nadpisywalo to jak my chcemy
        writer.defaultWriteObject();

        //tak by sie zapisywalo inta opakowanego w optionala, tylko to zamiast tego w ifie.
//        writer.write(littleInt.get());

        // tak zapisujemy Stringa opakowanego w Optionalu, a w readzie trzeba tez dorzucic troche.

        String toWrite = null;
        if(null != nick && nick.isPresent()){
            toWrite = nick.get();
        }
        writer.writeObject(toWrite);
    }

    private void readObject(ObjectInputStream reader) throws IOException, ClassNotFoundException {
        reader.defaultReadObject();
        String nick = (String) reader.readObject();
        this.nick = Optional.ofNullable(nick);
    }
}
