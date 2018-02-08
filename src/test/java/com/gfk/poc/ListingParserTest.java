package com.gfk.poc;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
 */
public class ListingParserTest
{
    private static final String PHONE_NUMBER_EXPEXTED = "0889017187";

    private static final String LISTING_URL =
        "https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1a151799831464339&slink=3gzze3&f1=1";



    @Test
    public void fetchPhoneNumberForListing()
        throws IOException
    {
        Assertions.assertThat(new ListringParser(LISTING_URL).extractPhoneNumber()).isEqualTo(PHONE_NUMBER_EXPEXTED);
    }



}
