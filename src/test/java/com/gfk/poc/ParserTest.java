package com.gfk.poc;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final String TEST_RES_PAGE_URL_3_PAGES_LEFT =
        "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=3h0j45&f1=1";



    @Test
    public void getPhoneToListringUrlMapping()
        throws IOException
    {
        final List<Map.Entry<String, List<String>>> phoneToListingsMapping = new Parser(TEST_RES_PAGE_URL)
            .getPhoneToListingsMapping();
        Assertions.assertThat(phoneToListingsMapping).isNotEmpty();
    }



    @Test
    public void getPhoneToListingsMapping_3pagesLeft()
        throws IOException
    {
        final List<Map.Entry<String, List<String>>> phoneToListingsMapping = new Parser(TEST_RES_PAGE_URL_3_PAGES_LEFT)
            .getPhoneToListingsMapping();
        print(phoneToListingsMapping);
    }



    private void print(List<Map.Entry<String, List<String>>> pEntries)
    {
        System.out.println(pEntries.stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue().stream().collect(Collectors.joining(", ")))
            .collect(Collectors.joining(",\n ")));
    }
}
