package pl.rkul.advanced_programming.animal;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Wolf extends Animal {
    protected Wolf(BigDecimal weight, Sex sex) {
        super(weight, sex);
    }

    // metody fabryczne - zwracaja obiekt
    public static Wolf createWolfWithWeightAndSexAndName(BigDecimal weight, Sex sex, String name){
        Wolf result = new Wolf(weight,sex);
        result.setName(name);

        return result;
    }

    public static Wolf createWolfWithEverything(BigDecimal weight, Sex sex, String name, BigInteger waterQuantity, BigInteger waterUsage, BigInteger foodQuantity, BigInteger foodUsage){

        Wolf result = Wolf.createWolfWithWeightAndSexAndName(weight,sex,name);  // wykorzystujemy tą fabryke co zrobilismy wyzej
        result.setWaterQuantity(waterQuantity);
        result.setWaterUsagePerCycle(waterUsage);
        result.setFoodQuantity(foodQuantity);
        result.setFoodUsagePerCycle(foodUsage);

        return result;
    }
}
