package fr.jtomasi.utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Utilities {
    public static String ucfirst(String str){
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String generateNumTel(){
        Faker faker = new Faker(new Locale("fr-FR"));
        String numTel = faker.phoneNumber().cellPhone();
        if(numTel.startsWith("+33")){
            numTel = numTel.substring(numTel.indexOf(" ") + 1);
            numTel = "0"+numTel;
        }
        numTel = numTel.replaceAll(" ","");
        return numTel;
    }
}
