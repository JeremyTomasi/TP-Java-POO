package fr.jtomasi.utilities;

public class Utilities {
    public static String ucfirst(String str){
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
