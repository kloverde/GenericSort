/*
 * Copyright (c) 2015 Kurtis LoVerde
 * All rights reserved
 */

package org.loverde.sorting.algorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.loverde.sorting.Statistics;
import org.loverde.sorting.exception.ExceptionMessages;


public class SelectionSort<T> implements SortingAlgorithm<T> {

   private Comparator<T> comparator;


   public SelectionSort() {}

   /**
    * @param comparator See the javadoc for {@link #setComparator(Comparator)}
    */
   public SelectionSort( final Comparator<T> comparator ) {
      setComparator( comparator );
   }

   @Override
   public void setComparator( final Comparator<T> comparator ) {
      this.comparator = comparator;
   }

   @Override
   public Statistics sortList( final List<T> data ) {
      final Statistics stats;

      long iterations = 0,
           swaps      = 0;

      checkForIllegalState( data );

      stats = new Statistics();
      stats.setStartDate( new Date() );
      stats.setEndDate( stats.getStartDate() );

      if( data.size() < 2 ) {
         return stats;
      }

      final int dataSize = data.size();

      int minIdx;

      for( int sortedStartIdx = 0; sortedStartIdx < dataSize - 1; sortedStartIdx++ ) {
          minIdx = sortedStartIdx;

          for( int unsortedStartIdx = sortedStartIdx + 1; unsortedStartIdx < dataSize; unsortedStartIdx++ ) {
             iterations++;

             if( comparator.compare(data.get(unsortedStartIdx), data.get(minIdx)) < 0 ) {
                  minIdx = unsortedStartIdx;
              }
          }

          if( minIdx != sortedStartIdx ) {
             final T start = data.get( sortedStartIdx );
             final T min = data.get( minIdx );

             data.set( sortedStartIdx, min );
             data.set( minIdx, start );

             swaps++;
          }
      }

      stats.setEndDate( new Date() );
      stats.setIterationsPerformed( iterations );
      stats.setSwapsPerformed( swaps );

      return stats;
   }

   private void checkForIllegalState( final Collection<T> data ) {
      if( comparator == null ) throw new IllegalStateException( ExceptionMessages.NULL_COMPARATOR );
      if( data == null ) throw new IllegalArgumentException( ExceptionMessages.NULL_DATA_SET );
   }
}
