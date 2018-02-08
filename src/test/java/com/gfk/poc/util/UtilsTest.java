package com.gfk.poc.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
 */
public class UtilsTest
{

    @Test
    public void removeSymbolsFromPhoneNum_oneSlash_removed()
    {
        final String clearedPhoneNum = Util.removeSymbolsFromPhoneNum("012/1232");
        Assertions.assertThat(clearedPhoneNum).isEqualTo("0121232");
    }



    @Test
    public void removeSymbolsFromPhoneNum_dashes_removed()
    {
        final String clearedPhoneNum = Util.removeSymbolsFromPhoneNum("012-12-32");
        Assertions.assertThat(clearedPhoneNum).isEqualTo("0121232");
    }


    @Test
    public void removeSymbolsFromPhoneNum_spaces_removed()
    {
        final String clearedPhoneNum = Util.removeSymbolsFromPhoneNum("012 12 32");
        Assertions.assertThat(clearedPhoneNum).isEqualTo("0121232");
    }
}
