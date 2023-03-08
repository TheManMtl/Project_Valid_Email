/*
 * Li Qian Student --> ID:
 *
 * Ehsan KH. Motlagh --> student ID: 2340457
 *
 * */
public class Validator {

    public static void main(String[] args) {
/*
        char[] chars = {'h', 'D', '3', '!', '*'}; // T,T,T,F,F
        for (char c : chars)
            System.out.println(isAlphaNum(c) + " " + c);

        System.out.println();

        System.out.println(isSpecialChar('_', true)); // true
        System.out.println(isSpecialChar('-', true)); // true
        System.out.println(isSpecialChar('-', false)); // true
        System.out.println(isSpecialChar('_', false)); // false
        System.out.println(isSpecialChar('@', false)); // false
        System.out.println(isSpecialChar('!', false)); // false

        System.out.println();

        System.out.println(isPrefixChar('-')); // true
        System.out.println(isPrefixChar('F')); // true
        System.out.println(isPrefixChar('8')); // true
        System.out.println(isPrefixChar('&')); // false
        System.out.println(isPrefixChar('-')); // false

        System.out.println();
        System.out.println(isDomainChar('-')); // true
        System.out.println(isDomainChar('s')); // true
        System.out.println(isDomainChar('9')); // true
        System.out.println(isDomainChar('_')); // false*/

        //System.out.println(singleAtSign(".et.@han"));
        /*System.out.println(fetchBeforeAt("ethan.m@gmail.com"));
        System.out.println(fetchAfterAt("ethan.m@gmail.com"));
        String[] str = {"", "e_9999.9909009i", "7777777", "_lklkl",
                "e.motlag", "e.motla_g", "e.motlag_", "e.motla..g", "g21$_k"};
        for (String s : str)
            System.out.println(isPrefix(s) + " " + s);*/

        String[] strD = {"et_fdr.jhon", "ehsan_.mot", "ee.t1", ".domain", "ehs.com", "ac-133.yul", "and..com"};
        for (String s : strD)
            System.out.println(isDomain(s));
        // System.out.println(isDomain("et_fdr.jhon"));
    }

    // returns true if char is alphanumeric(english letters and 0-9)
    public static boolean isAlphaNum(char c) {
        return isAlphabetic(c) || (c >= '0' && c <= '9');
    }

    //return true if
    // - or .
    //OR _ is true
    public static boolean isSpecialChar(char c, boolean underscore) {

        if (c != '.' && c != '-' && c != '_') return false;

        if (c == '-' || c == '.') return true;

        return underscore;
    }

    //validate 1st part of email
    //return true if char is alpha_num or _.-
    public static boolean isPrefixChar(char c) {
        if (isAlphaNum(c)) {
            return true;
        }
        return isSpecialChar(c, true);
    }

    //validate 2nd part of email
    //return true if char is alpha_num or .-
    public static boolean isDomainChar(char c) {
        if (isAlphaNum(c)) {
            return true;
        }
        return isSpecialChar(c, false);
    }

    //return true if there is only one @ in the string
    //It will return false as soon as number of @ is more than 1
    public static boolean singleAtSign(String str) {

        return onlyOneChar('@', str);
    }

    //return string BEFORE @
    public static String fetchBeforeAt(String str) {
        return str.split("@")[0];
    }

    //return string AFTER @
    public static String fetchAfterAt(String str) {
        return str.split("@")[1];
    }

    /*
        true if
          * 1 char is in string Not empty
          * first char is alphanumeric
          * only alphanumeric and underscore,period,dash is allowed
          * underscore,period,dash must be followed by 1 alphanumeric char
     */
    public static boolean isPrefix(String str) {
        int i = 0;
        //return false if string is empty
        if (str.length() == 0)
            return false;
        //current character in string
        char ch = str.charAt(i);

        // if first character is not alpha_num char return false
        if (!isAlphaNum(ch))
            return false;

        i++;
        while (i < str.length()) {

            ch = str.charAt(i);

            //if _.- is the last character return false
            if (isSpecialChar(ch, true) && (i + 1) == str.length()) return false;

            //if _.- is not followed by alpha_num return false
            if (isSpecialChar(ch, true) && !isAlphaNum(str.charAt(i + 1))) return false;

            //if char is not special char(Including underscore) nor alpha_num return false
            if (!isPrefixChar(ch)) return false;

            i++;
        }
        //if the code reaches here
        //means all the conditions are met
        return true;
    }

    /*
        true if
          * there is only 1 period  Done
          * Before Period
                ** contains at least 1 Char Done
                ** only alpha_num and period and dash Done
                ** period and dash must be followed by Done
          * After period
                ** contains at least two char
                ** contains only english alphabet
          *
     */

    public static boolean isDomain(String str) {
        int i = 0;
        char ch;
        String firstPart = splitByChar(0, '.', str);
        String secondPart = splitByChar(1, '.', str);

        //DELETE BEFORE SUBMIT
        System.out.println(firstPart + " -- " + secondPart);

        //only 1 period allowed
        if (!onlyOneChar('.', str)) return false;

        // validation first portion

        // false if first portion is empty
        if (firstPart.length() == 0) return false;
        boolean isSpecial = false;
        while (i < firstPart.length()) {

            ch = firstPart.charAt(i);

            //false if .- is the last character
            if (isSpecialChar(ch, false) && (i + 1) == str.length()) return false;

            //false if .- is not followed by alpha_num
            if (isSpecialChar(ch, false) && !isAlphaNum(str.charAt(i + 1))) return false;

            //false if char is not special char(Excluding underscore) nor alpha_num
            if (!isDomainChar(ch)) return false;

            i++;
        }

        //validation second portion

        //false if less than 2 char
        if (secondPart.length() < 3)
            return false;
        i = 0;
        ch = secondPart.charAt(i);
        while (i < secondPart.length()) {
            //false if contains non-alphabet
            if (!isAlphabetic(ch)) return false;
            i++;
        }
        return true;
    }



    //returns true if char is english alphabet
    private static boolean isAlphabetic(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    // searches string for the given character
    // true if the occurrence is only one
    private static boolean onlyOneChar(char ch, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (count > 1) return false;
            if (str.charAt(i) == ch)
                count++;
        }
        return count == 1;
    }

    /*
     * portion: only 0 and 1 allowed and indicates the return portion
     * ch: split by char for which str contains only one ch
     * str: to split
     */
    private static String splitByChar(int portion, char ch, String str) {
        String reg = "\\".concat(Character.toString(ch));
        String[] portions = str.split(reg);
        return portions[portion];
    }
}
