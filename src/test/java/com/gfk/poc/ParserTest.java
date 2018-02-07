package com.gfk.poc;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
 */
public class ParserTest
{
    private static final String TEST_RES_PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=3gzze3&f1=1";



    @Test
    public void getPhoneToListringUrlMapping()
    {
        final Map<String, List<String>> phoneToListringUrlsMapping = new Parser(TEST_RES_PAGE_URL)
            .getPhoneToListringUrlsMapping();
        Assertions.assertThat(phoneToListringUrlsMapping).isNotEmpty();
    }
}
