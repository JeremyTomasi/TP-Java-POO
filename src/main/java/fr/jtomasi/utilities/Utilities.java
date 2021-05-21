package fr.jtomasi.utilities;

import com.github.javafaker.Faker;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;

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

    public static LocalDate generateDate(String date){
        LocalDate dateGenerated = null;
        try {
            if(Pattern.matches("\\d+/\\d+/\\d+",date)){
                String[] infosDate = date.split("/");
                int jour = Integer.parseInt(infosDate[0]);
                int mois = Integer.parseInt(infosDate[1]);
                int annee = Integer.parseInt(infosDate[2]);

                dateGenerated = LocalDate.of(annee,mois,jour);
            } else if(Pattern.matches("\\d+-\\d+-\\d+",date)){
                String[] infosDate = date.split("-");
                int jour = Integer.parseInt(infosDate[0]);
                int mois = Integer.parseInt(infosDate[1]);
                int annee = Integer.parseInt(infosDate[2]);

                dateGenerated = LocalDate.of(annee,mois,jour);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        } finally {
            return dateGenerated;
        }
    }

    public static String displayDate(LocalDate date){
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }
}
