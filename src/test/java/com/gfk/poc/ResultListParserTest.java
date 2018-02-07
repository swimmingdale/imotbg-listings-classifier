package com.gfk.poc;

import java.io.IOException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
 */
public class ResultListParserTest
{
    private static final String TEST_RES_PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=3gzze3&f1=1";

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
        Assertions.assertThat(new ListringParser(TEST_RES_PAGE_URL).getNextPageUrl()).isEqualTo(TEST_RES_NEXT_PAGE_URL);
    }
}