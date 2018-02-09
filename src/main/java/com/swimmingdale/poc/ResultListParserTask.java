package com.swimmingdale.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.swimmingdale.poc.util.Util;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak
 */
public class ResultListParserTask
    implements Runnable
{
    private final String pageUrl;

    private final ConcurrentHashMap<String, List<String>> results;



    public ResultListParserTask(final String pPageUrl, final ConcurrentHashMap<String, List<String>> pResults)
    {
        pageUrl = pPageUrl;
        results = pResults;
    }



    private void put(final String key, final String value, Map<String, List<String>> aggregator)
    {
        if (aggregator.containsKey(key)) {
            aggregator.get(key).add(value);
        }
        else {
            final ArrayList<String> urls = new ArrayList<>();
            urls.add(value);
            aggregator.put(key, urls);
        }
    }



    @Override
    public void run()
    {
        Util.log("Thread name: " + Thread.currentThread().getName());
        try {
            for (final String url : new ResultListParser(pageUrl).getAllListringsInSearchResult()) {
                final String phoneNum = new ListringParser(url).extractPhoneNumber();
                put(Util.removeSymbolsFromPhoneNum(phoneNum), url, results);
            }
        }
        catch (IOException pE) {
            System.err.append("Page cannot be parsed: " + pageUrl);
        }
    }
}
