package com.swimmingdale.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * TODO Purpose of this type, in one sentence, ending with a dot.
 * <p/>
 * Further, arbitrarily elaborate description. HTML allowed.
 *
 * @author Ahmed Sadak
 */
public class Parser
{
    private String url;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private List<Future<?>> futures = new ArrayList<>();



    public Parser(final String pUrl)
    {
        url = pUrl;
    }



    public List<Map.Entry<String, List<String>>> getPhoneToListingsMapping()
        throws IOException
    {
        final ConcurrentHashMap<String, List<String>> aggregator = new ConcurrentHashMap<>();
        crawlPage(url, aggregator);
        while (!areAllCompleted()) {
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException pE) {
                throw new Error(pE);
            }
        }
        return sortByPhoneNumber(aggregator);
    }



    private boolean areAllCompleted()
    {
        for (Future<?> future : futures) {
            if (!future.isDone()) {
                return false;
            }
        }
        return true;
    }



    private void crawlPage(final String pPageUrl, ConcurrentHashMap<String, List<String>> aggregator)
        throws IOException
    {
        if (pPageUrl == null) {
            return;
        }
        final ResultListParserTask resultListParserTask = new ResultListParserTask(pPageUrl, aggregator);
        final Future<?> future = executorService.submit(resultListParserTask);
        futures.add(future);
        crawlPage(new ResultListParser(pPageUrl).getNextPageUrl(), aggregator);
    }



    private List<Map.Entry<String, List<String>>> sortByPhoneNumber(final Map<String, List<String>> pAggregaor)
    {
        final List<Map.Entry<String, List<String>>> allPairs = new ArrayList<>(pAggregaor.entrySet());
        Collections.sort(allPairs, (pLeft, pRight) -> {
            return Integer.compare(pRight.getValue().size(), pLeft.getValue().size());
        });
        return allPairs;
    }
}
