/*
 * Li Qian Student --> ID:
 *
 * Ehsan KH. Motlagh --> student ID: 2340457
 *
 * */
public class Validator {

    public static void main(String[] args) {

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
        System.out.println(isDomainChar('_')); // false

        System.out.println(singleAtSign("ethan@"));
        System.out.println(fetchBeforeAt("ethan.m@gmail.com"));
        System.out.println(fetchAfterAt("ethan.m@gmail.com"));
        String[] str = {"", "e_9999.9909009i", "7777777", "_lklkl",
                "e.motlag", "e.motla_g", "e.motlag_", "e.motla..g", "g21$_k"};
        for (String s : str)
            System.out.println(isPrefix(s) + " " + s);
    }

    // returns true if char is alphanumeric(english letters and 0-9)
    public static boolean isAlphaNum(char c) {
        return isAlphabetic(c) || (c >= '0' && c <= '9');
    }

    //return true if
    // - or .
    //OR _ is true
    public static boolean isSpecialChar(char c, boolean underscore) {
        if (c == '-' || c == '.') {
            return true;
        }
        return underscore && c == '_';
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
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (count > 1) return false;
            if (str.charAt(i) == '@')
                count++;
        }
        return count == 1;
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
          1 char is in string
          only alphanumeric and underscore,period,dash is allowed
          underscore,period,dash must be followed by 1 alphanumeric char
          first char is alphanumeric
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

        if (!containsChar(str))
            return false;
        i++;
        while (i < str.length()) {

            ch = str.charAt(i);

            //if _.- is the last character return false
            if (isSpecialChar(ch, true) && (i + 1) == str.length()) return false;

            //if _.- is not followed by alpha_num return false
            if (isSpecialChar(ch, true) && !isAlphaNum(str.charAt(i + 1))) return false;

            //if char is not special char nor alpha_num return false
            if (!isPrefixChar(ch)) return false;

            i++;
        }
        //if the code reaches here
        //means all the conditions are met
        return true;
    }

    //returns true if char is english alphabet
    private static boolean isAlphabetic(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    //return true if there is at least 1 char in string
    private static boolean containsChar(String str) {
        for (int i = 0; i < str.length(); i++)
            if (isAlphabetic(str.charAt(i)))
                return true;
        return false;
    }
}
