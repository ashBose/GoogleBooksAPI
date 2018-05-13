package com.books;

import com.google.api.services.books.model.Volume;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

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

    /*
    public static final Comparator<Volume> publishedDateComparator =
            new Comparator<Volume>(){

        public int compare(Volume o1, Volume o2){
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Volume.VolumeInfo volumeInfo1 = o1.getVolumeInfo();
            Volume.VolumeInfo volumeInfo2 = o2.getVolumeInfo();
            Date date1 = dt.parse(volumeInfo1.getPublishedDate());
            Date date2 = dt.parse(volumeInfo2.getPublishedDate());
            if (date1.compareTo(date2) > 0)
                return 1;
            else
                return -1;

        }

    };*/


}
