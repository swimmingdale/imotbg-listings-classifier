package com.gfk.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ResultListParser
{

    private Document document;



    public ResultListParser(final String pTestResPageUrl)
        throws IOException
    {
        document = Jsoup.connect(pTestResPageUrl).get();
    }



    public List<String> getAllListringsInSearchResult()
    {
        final List<String> allResults = new ArrayList<>();
        log(document.title());
        Elements newsHeadlines = document.select(".photoLink");
        for (Element headline : newsHeadlines) {
            final String href = headline.absUrl("href");
            log("%s", href);
            allResults.add(href);
        }
        return allResults;
    }



    private void log(String str)
    {
        System.out.println(str);
    }



    private void log(String pattern, Object... argument)
    {
        System.out.println(String.format(pattern, argument));
    }
}
