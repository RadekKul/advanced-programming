package pl.rkul.advanced_programming.animal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {

        Random random = new Random();

        List<Wolf> wolvesList = Arrays.asList(
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(52),
                        Sex.FEMALE,"Zoe",
                        BigInteger.valueOf(1000),   // przypisujemy ile maja na start wody i jedzenia
                        BigInteger.valueOf(250 + random.nextInt(200)), // tworzymy randomowe zuzycie wody i jedzenia zeby dostawac rozne wyniki
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250+ random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(74),
                        Sex.MALE,"Bard",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(66),
                        Sex.MALE,"Zed",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(54),
                        Sex.FEMALE,"Jinx",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(58),
                        Sex.FEMALE,"Liss",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(76),
                        Sex.MALE,"Lee",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(86),
                        Sex.MALE,"Olaf",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(64),
                        Sex.MALE,"Jhin",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(88),
                        Sex.FEMALE,"Seju",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200))),
                Wolf.createWolfWithEverything(
                        BigDecimal.valueOf(45),
                        Sex.FEMALE,"Eve",
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)),
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(250 + random.nextInt(200)))
        );

        ZooWorker Jasio = new ZooWorker(wolvesList,4,"Jasio");
       // ZooWorker Stasio = new ZooWorker(wolvesList,1 + random.nextInt(10),"Stasio");
        StatsKeeper ZenekStatystk = new StatsKeeper(wolvesList,1);
        TimerCounter licznik = new TimerCounter(wolvesList,2);


        // 1 way to get threads started
       /* Thread thread1 = new Thread(Jasio,"Zoo Worker");    // tworzymy watki i do nich przypisujemy instancje klas i je nazywamy
        Thread thread2 = new Thread(ZenekStatystk, "Stats Keeper");
        Thread thread3 = new Thread(licznik,"Timer Counter");

        thread1.start();
        thread2.start();
        thread3.start();
*/

        // 2 way to get threads started (BETTER)
        ExecutorService workers = Executors.newFixedThreadPool(4);      // klaska pomagajaca nam wywolac pule robotnikow, w naszym przypadku 4

        workers.execute(Jasio);
        workers.execute(ZenekStatystk);
        workers.execute(licznik);

        //TODO naprawic program uzwajac lock, w odpowiedniach miejsach, dodac go do klas i konstruktorow i zrobic tak zeby dzialaly pokolei a nie samowolka



    }
}
