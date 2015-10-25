/*
 * Copyright (c) 2015 Kurtis LoVerde
 * All rights reserved
 */

package org.loverde.sorting.algorithm;

import java.util.Comparator;
import java.util.List;

import org.loverde.sorting.Statistics;


public interface SortingAlgorithm <T> {

   /**
    * The {@code Comparator} is a user-supplied instruction to the sorting algorithm
    * for how to sort two items of the same type.  Null values in the data set are
    * not regarded as errors by the {@code SortingAlgorithm} implementations.  In
    * other words, the implementations will pass nulls to your {@code Comparator} if
    * they are present in your data set; it is up to you to specify how to handle
    * them.  You can throw an exception or make null entries appear first or last in
    * the sorted data.
    *
    * @param comparator
    */
   public void setComparator( Comparator<T> comparator );

   public Statistics sortList( List<T> data );
}
