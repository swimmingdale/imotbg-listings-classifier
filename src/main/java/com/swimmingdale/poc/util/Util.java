package com.swimmingdale.poc.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak
 */
public class Util
{
    public static final List<String> UNNECESSARY_SYMBOLS = Arrays.asList("/", "-", StringUtils.SPACE);



    private Util()
    {
    }



    public static String removeSymbolsFromPhoneNum(final String pPhoneNum)
    {
        String cleared = pPhoneNum;
        for (String forbiddenSymbol : UNNECESSARY_SYMBOLS) {
            cleared = StringUtils.remove(cleared, forbiddenSymbol);
        }
        return cleared;
    }



    public static void log(String str)
    {
        System.out.println(str);
    }



    public static void log(String pattern, Object... argument)
    {
        System.out.println(String.format(pattern, argument));
    }


    public static void print(List<Map.Entry<String, List<String>>> pEntries)
    {
        System.out.println(pEntries.stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue().stream().collect(Collectors.joining(", ")))
            .collect(Collectors.joining(",\n ")));
    }
}
