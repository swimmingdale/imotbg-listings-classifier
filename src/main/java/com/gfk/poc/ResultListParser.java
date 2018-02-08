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

    private final String url;



    public ResultListParser(final String pUrl)
        throws IOException
    {
        url = pUrl;
        document = Jsoup.connect(pUrl).get();
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



    public String getNextPageUrl()
    {
        if (getAllListringsInSearchResult().size() == 0) {
            return null;
        }

        final String idKey = "&f1=";
        final int indexOfPageId = url.lastIndexOf(idKey);
        final String pageIdSubstr = url.substring(indexOfPageId);
        final String pageNumberStr = pageIdSubstr.substring(pageIdSubstr.lastIndexOf("=") + 1);
        int pageNum = Integer.valueOf(pageNumberStr).intValue();
        pageNum++;
        return url.replace(pageIdSubstr, idKey + Integer.valueOf(pageNum).toString());
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
