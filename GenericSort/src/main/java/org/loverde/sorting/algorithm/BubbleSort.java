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


public class BubbleSort<T> implements SortingAlgorithm<T> {

   private Comparator<T> comparator;


   public BubbleSort() {}

   /**
    * @param comparator See the javadoc for {@link #setComparator(Comparator)}
    */
   public BubbleSort( final Comparator<T> comparator ) {
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

      boolean swapped = true;

      checkForIllegalState( data );

      stats = new Statistics();
      stats.setStartDate( new Date() );
      stats.setEndDate( stats.getStartDate() );
      stats.setIterationsPerformed( 0 );

      if( data.size() < 2 ) {
         return stats;
      }

      while( swapped ) {
         swapped = false;
         iterations++;

         for( int i = 0; i < data.size() - 1; i++ ) {
            final int nextIdx = i + 1;

            if( nextIdx < data.size() ) {
               final T currElem = data.get( i );
               final T nextElem = data.get( nextIdx );

               if( comparator.compare(currElem, nextElem) > 0 ) {
                  data.set( nextIdx, currElem );
                  data.set( i, nextElem );
                  swaps++;
                  swapped = true;
               }
            }
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
