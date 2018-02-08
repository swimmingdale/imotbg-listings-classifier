package com.gfk.poc;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
 */
public class ListringParser
{

    private final Document document;

    private final String url;



    public ListringParser(final String pUrl)
        throws IOException
    {
        url = pUrl;
        document = Jsoup.connect(pUrl).get();
    }



    public String extractPhoneNumber()
    {
        Elements phoneNumberSiblingElement = document.select("img[src*=\"../images/picturess/phone-ico.gif\"]");
        Elements phoneNumberSiblings = phoneNumberSiblingElement.first().siblingElements();
        Element phoneNumber = null;
        for (Element sibling : phoneNumberSiblings) {
            if (sibling.is("span")) {
                phoneNumber = sibling;
                break;
            }
        }
        return phoneNumber.ownText();
    }



    private void log(String str)
    {
        System.out.println(str);
    }
}
