package pl.rkul.advanced_programming.files.serializations.to_file;

import pl.rkul.advanced_programming.files.Animal;
import pl.rkul.advanced_programming.files.serializations.AnimalDeserializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalFileDeserializer implements AnimalDeserializer, AutoCloseable {

    private String filepath;
    private BufferedReader bufferedReader;

    public AnimalFileDeserializer(String filepath) throws FileNotFoundException {
        this.filepath = Objects.requireNonNull(filepath, "[file path can't be null !]");
        this.bufferedReader = new BufferedReader(new FileReader(filepath)); // do buffered readera musimy dac cos co jest strumieniem, a filereader jest readerem.
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }

    private Optional<Animal> fromString(String string){ // jezeli z tamtej metody poleci wyjatek to lapiemy go tutaj i przpyisujemy pusta wartosc, wiec wszystko jest ogarniete
        try{
            return Optional.of(convertStringToAnimal(string));
        }catch (RuntimeException ex){
            return Optional.empty();
        }
    }

    private Animal convertStringToAnimal(String string) {    // mozna by to zrobic za pomoc metody split() ktora stworzyla by tablice springow i z niej dane bysmy konwertowali
        Scanner scanner = new Scanner(string);  // tutaj w skanerze dajemy cala linijke do przeczytania czyli string, bo to zostanie nam przekazane do metody
        String name = scanner.next();
        int numbersOfLegs = scanner.nextInt();
        long hairs = scanner.nextLong();
        boolean isAlive = scanner.nextBoolean();

        return new Animal(name,numbersOfLegs,hairs,isAlive);
    }

    @Override
    public List<Animal> readAnimals() {
        // opisy tu zeby widac co dostajemy
        // przechodzi nam po kazdej linijce
        // konwertujemy na optionala, pustego lub pelnego ze stringow
        // filtrujemy zeby wywalic puste optionale
        // pobieramy wszystkie zwierzeta i mapujemy je do kolekcji

       return bufferedReader.lines()
                .map(this::fromString)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    /*@Override
    public List<Animal> readAnimals() {
        return bufferedReader.lines()
                .filter(s -> s.length() > 0)
                .map(this::convertStringToAnimal)
                .collect(Collectors.toList());
    }*/
}
