package com.swimmingdale.poc;

import java.io.IOException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak
 */
public class ResultListParserTest
{
    private static final String TEST_RES_PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=3gzze3&f1=1";

    private static final String TEST_RES_NO_NEXT_PAGE_URL =
        "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=3gzze3&f1=27";

    private static final String TEST_RES_NEXT_PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=3gzze3&f1=2";



    @Test
    public void getAllListringsInSearchResult()
        throws IOException
    {
        List<String> listringUrls = new ResultListParser(TEST_RES_PAGE_URL).getAllListringsInSearchResult();
        Assertions.assertThat(listringUrls).hasSize(40);
    }



    @Test
    public void getNextPageUrl()
        throws IOException
    {
        Assertions.assertThat(new ResultListParser(TEST_RES_PAGE_URL).getNextPageUrl()).isEqualTo(
            TEST_RES_NEXT_PAGE_URL);
    }



    @Test
    public void getNextPageUrl_noElemsOnPage_null()
        throws IOException
    {
        Assertions.assertThat(new ResultListParser(TEST_RES_NO_NEXT_PAGE_URL).getNextPageUrl()).isNull();
    }
}
