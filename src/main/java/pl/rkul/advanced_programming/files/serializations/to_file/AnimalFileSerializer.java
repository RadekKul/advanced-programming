package pl.rkul.advanced_programming.files.serializations.to_file;

import pl.rkul.advanced_programming.files.Animal;
import pl.rkul.advanced_programming.files.serializations.AnimalSerializer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public class AnimalFileSerializer implements AnimalSerializer, AutoCloseable {  // implementujemy interfejs serializer do tworzenia i closeable zeby robic cos przy zamknieciu.

    private final String filePath;
    private final PrintWriter printWriter;

    private static final String rowFormat = "%20s %3d %10d %5b";  // stwarzamy sobie formatke dla jednego wiersza

    public AnimalFileSerializer(String filePath) throws FileNotFoundException {
        // validujemy odrazu
        this.filePath = Objects.requireNonNull(filePath, "[file path can't be null !]");
        printWriter = new PrintWriter(filePath);    // przypisujemy do printWritera sciezke gdzie bedzie mial zapisywac
    }

    @Override
    public void writeAnimals(List<Animal> animals) {

        // utowrzyc rekord
        // wyslac do pliku

        animals.forEach(animal -> {
           printWriter.format(rowFormat,animal.getName(),animal.getNumberOfLegs(),animal.getHairs(),animal.isAlive())
           .println();
        });

        printWriter.flush();    // tzw spłuczka, zamykanie
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
