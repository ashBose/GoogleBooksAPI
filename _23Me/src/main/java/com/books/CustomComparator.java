package com.books;

import com.google.api.services.books.model.Volume;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CustomComparator {

    private static final int LESS = -1;
    private static final int EQUAL = 0;
    private static final int GREATER = 1;
    private static final int NONNULL = 2;

    private static int nullSafeCompare(Object first, Object second) {
        if(first == null && second == null)
            return EQUAL;
        else if(first == null && second != null)
            return GREATER;
        else if(first != null && second == null)
            return LESS;
        return NONNULL;
    }

    private static Date convertToDate(String dateString)
    {
        if(dateString == null)
            return null;
        List<String> formatStrings = Arrays.asList("M/y", "yyyy", "yyyy-MM-dd");
        for (String formatString : formatStrings)
        {
            try
            {
                return new SimpleDateFormat(formatString).parse(dateString);
            }
            catch (ParseException e) {}
        }
        return null;
    }

    // price, avg rating, rating count, published date, or page count
    public static final Comparator<Volume> PageCountComparator =
            new Comparator<Volume>(){

        public int compare(Volume o1, Volume o2) {
            Volume.VolumeInfo volumeInfo1 = o1.getVolumeInfo();
            Volume.VolumeInfo volumeInfo2 = o2.getVolumeInfo();
            int val = nullSafeCompare(volumeInfo1.getPageCount(),
                    volumeInfo2.getPageCount());
            if( val != NONNULL)
                return val;
            return Integer.compare(volumeInfo1.getPageCount(),
                    volumeInfo2.getPageCount());
        }

    };

    public static final Comparator<Volume> RatingComparator =
            new Comparator<Volume>(){

        public int compare(Volume o1, Volume o2) {
            Volume.VolumeInfo volumeInfo1 = o1.getVolumeInfo();
            Volume.VolumeInfo volumeInfo2 = o2.getVolumeInfo();
            int val = nullSafeCompare(volumeInfo1.getRatingsCount(),
                    volumeInfo2.getRatingsCount());
            if( val != NONNULL)
                return val;
            return Double.compare(volumeInfo1.getRatingsCount(),
                    volumeInfo2.getRatingsCount());
        }

    };

    public static final Comparator<Volume> AvgRatingComparator =
            new Comparator<Volume>(){

        public int compare(Volume o1, Volume o2) {
            Volume.VolumeInfo volumeInfo1 = o1.getVolumeInfo();
            Volume.VolumeInfo volumeInfo2 = o2.getVolumeInfo();
            int val = nullSafeCompare(volumeInfo1.getAverageRating(),
                    volumeInfo2.getAverageRating());
            if( val != NONNULL)
                return val;
            return Double.compare(volumeInfo1.getAverageRating(),
                    volumeInfo2.getAverageRating());
        }
    };

    public static final Comparator<Volume> PriceComparator =
            new Comparator<Volume>(){

                public int compare(Volume o1, Volume o2) {
                    Volume.SaleInfo saleInfo1 = o1.getSaleInfo();
                    Volume.SaleInfo saleInfo2 = o2.getSaleInfo();
                    int val = nullSafeCompare(saleInfo1.getRetailPrice(),
                            saleInfo2.getRetailPrice());
                    if( val != NONNULL)
                        return val;
                    return Double.compare(saleInfo1.getRetailPrice().getAmount(),
                            saleInfo2.getRetailPrice().getAmount());
                }
            };


    public static final Comparator<Volume> publishedDateComparator =
            new Comparator<Volume>(){

        public int compare(Volume o1, Volume o2){
            Volume.VolumeInfo volumeInfo1 = o1.getVolumeInfo();
            Volume.VolumeInfo volumeInfo2 = o2.getVolumeInfo();
            Date date1 = convertToDate(volumeInfo1.getPublishedDate());
            Date date2 = convertToDate(volumeInfo2.getPublishedDate());
            int val = nullSafeCompare(date1, date2);
            if( val != NONNULL)
                return val;
            return Long.compare(date1.getTime(), date2.getTime());
        }

    };


}
