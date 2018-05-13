package com.books;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class RetriveBooks {
    private static final String APPLICATION_NAME = "23ME-test/1.0";
    final static Logger logger = Logger.getLogger(RetriveBooks.class);
    java.util.List<Volume> filteredVolumeList = null;
    ArrayList<String> virtualShelf = new ArrayList<String>();

    private  Volumes queryGoogleBooks(JsonFactory jsonFactory, String query,
                                         int startIndex,
                                         int maxResults) throws Exception {
        // Set up Books client.
        final Books books = new Books.Builder(GoogleNetHttpTransport.
                newTrustedTransport(), jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
        List volumesList = books.volumes().list(query);
        volumesList.setStartIndex((long) startIndex);
        volumesList.setMaxResults((long) maxResults);
        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            logger.error("No  matches found for query");
            return null;
        }
        return volumes;

    }

    private void filterBooks(Volumes volumes, String bookNameFilter) {
        for (Volume volume : volumes.getItems()) {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            for(String book: bookNameFilter.split(",")) {
                book = book.trim().toLowerCase();
                String title = volumeInfo.getTitle().toLowerCase();
                if(book.equals(title)) {
                    filteredVolumeList.add(volume);
                    break;
                }
            }
        }
    }

    private void doCreateVirtualShelf() throws IOException{
        for(Volume iterVol: filteredVolumeList) {
            virtualShelf.add(iterVol.toPrettyString());
        }
    }

    public ArrayList<String> doExtractBooks(Map<String, String> argMap) throws IOException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        filteredVolumeList = new ArrayList<Volume>();
        int maxResults = 40;
        int startIndex = 0;
        Volumes volumes = null;
        try {
            try {
               // do {
                    try {
                        volumes = queryGoogleBooks(jsonFactory,
                                argMap.get("query"),
                                startIndex,
                                maxResults);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                        throw new IOException(e.getMessage());
                    }
                    if (volumes != null) {
                        filterBooks(volumes, argMap.get("book"));
                        startIndex += maxResults;
                    }
                //} while ((volumes != null));
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new IOException(e.getMessage());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        String sortFilter = argMap.get("sort");
        if(sortFilter.equals("avg_rating"))
            Collections.sort(filteredVolumeList,
                    CustomComparator.AvgRatingComparator);
        else if(sortFilter.equals("rating_count"))
            Collections.sort(filteredVolumeList,
                    CustomComparator.RatingComparator);
        else if(sortFilter.equals("page_count"))
            Collections.sort(filteredVolumeList,
                    CustomComparator.PageCountComparator);
        else if(sortFilter.equals("retail_price"))
            Collections.sort(filteredVolumeList,
                    CustomComparator.PriceComparator);
        else if(sortFilter.equals("published_date"))
            Collections.sort(filteredVolumeList,
                    CustomComparator.publishedDateComparator);

        doCreateVirtualShelf();
        return virtualShelf;
    }
}
