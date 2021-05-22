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
        // on définit le français en tant que locale
        Faker faker = new Faker(new Locale("fr-FR"));
        // On génère un numéro de téléphone français
        String numTel = faker.phoneNumber().cellPhone();
        // Si le numéro de téléphone commence par l'indicatif +33
        if(numTel.startsWith("+33")){
            numTel = numTel.substring(numTel.indexOf(" ") + 1);
            numTel = "0"+numTel;
        }
        // On supprime tous les espaces dans le numéro
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
            //si la date est au format "dd/mm/yyyy"
            if(Pattern.matches("\\d+/\\d+/\\d+",date)){
                // On sépare la chaîne pour obtenir le jour, le mois et l'année
                String[] infosDate = date.split("/");
                int jour = Integer.parseInt(infosDate[0]);
                int mois = Integer.parseInt(infosDate[1]);
                int annee = Integer.parseInt(infosDate[2]);

                // On génère un LocalDate avec le jour, le mois et l'année
                dateGenerated = LocalDate.of(annee,mois,jour);
            }
            //si la date est au format "dd-mm-yyyy"
            else if(Pattern.matches("\\d+-\\d+-\\d+",date)){
                // On sépare la chaîne pour obtenir le jour, le mois et l'année
                String[] infosDate = date.split("-");
                int jour = Integer.parseInt(infosDate[0]);
                int mois = Integer.parseInt(infosDate[1]);
                int annee = Integer.parseInt(infosDate[2]);

                // On génère un LocalDate avec le jour, le mois et l'année
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
