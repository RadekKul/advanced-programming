package pl.rkul.advanced_programming.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Optional;

public class Cat implements Serializable {

    private static final long serialVersionUID = -3127809224643015237L;
    private String name;
    private int age;
    private long weight;
    private boolean isFunny;

    public Cat(String name, int age, long weight, boolean isFunny) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.isFunny = isFunny;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", isFunny=" + isFunny +
                '}';
    }

    // zapisywanie i odczytywanie informacji serializable, za pomoca tych dowch metod, zastosowano w mainie z tworzeniem serializatora i deserializatora

    private void writeObject(ObjectOutputStream writer) throws IOException {    // dwie metody wlaczone w Settingsach ktore mozemy nadpisac zeby czytalo i nadpisywalo to jak my chcemy

       writer.writeObject(name);
       writer.write(age);
       writer.writeLong(weight);
       writer.writeBoolean(isFunny);
    }

    private void readObject(ObjectInputStream reader) throws IOException, ClassNotFoundException {

        name = (String) reader.readObject();
        age = reader.read();
        weight = reader.readLong();
        isFunny = reader.readBoolean();
    }
}
