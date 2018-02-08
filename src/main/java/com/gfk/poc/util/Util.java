package com.gfk.poc.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
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
}
