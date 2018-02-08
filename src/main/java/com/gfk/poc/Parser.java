package com.gfk.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak, GfK
 */
public class Parser
{
    private String url;



    public Parser(final String pUrl)
    {
        url = pUrl;
    }



    public List<Map.Entry<String, List<String>>> getPhoneToListingsMapping()
        throws IOException
    {
        final HashMap<String, List<String>> aggregator = new HashMap<>();
        crawlPage(url, aggregator);
        return sortByPhoneNumber(aggregator);
    }



    void put(final String key, final String value, Map<String, List<String>> aggregator)
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



    private List<Map.Entry<String, List<String>>> sortByPhoneNumber(final Map<String, List<String>> pAggregaor)
    {
        final List<Map.Entry<String, List<String>>> allPairs = new ArrayList<>(pAggregaor.entrySet());
        Collections.sort(allPairs, (pLeft, pRight) -> {
            return Integer.compare(pRight.getValue().size(), pLeft.getValue().size());
        });
        return allPairs;
    }



    private void crawlPage(final String pPageUrl, Map<String, List<String>> aggregator)
        throws IOException
    {
        if (pPageUrl == null) {
            return;
        }
        for (final String url : new ResultListParser(pPageUrl).getAllListringsInSearchResult()) {
            final String phoneNum = new ListringParser(url).extractPhoneNumber();
            put(phoneNum, url, aggregator);
        }

        crawlPage(new ResultListParser(pPageUrl).getNextPageUrl(), aggregator);
    }
}
