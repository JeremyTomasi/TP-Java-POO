package fr.jtomasi.utilities;

import com.github.javafaker.Faker;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utilities {
    /**
     * Met en majuscule le premier caractère de la chaîne
     * @param str La chaîne
     * @return Une nouvelle chaîne de caractères avec le premier caractère en majuscule
     */
    public static String ucfirst(String str){
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Génère un faux numéro de téléphone
     * @return Le faux numéro de téléphone
     */
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

    /**
     * Parse la date en chaîne de caractères en LocalDate
     * @param date La date à parser
     * @return LocalDate
     */
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

    /**
     * Retourne la date sous forme de phrase
     * @param date La date à afficher
     * @return La date sous forme de phrases
     */
    public static String displayDate(LocalDate date){
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }
}
