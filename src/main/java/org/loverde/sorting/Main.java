/*
 * Copyright (c) 2015 Kurtis LoVerde
 * All rights reserved
 */

package org.loverde.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.loverde.sorting.algorithm.BubbleSort;
import org.loverde.sorting.algorithm.SelectionSort;
import org.loverde.sorting.algorithm.SortingAlgorithm;


public class Main {
   private static final Comparator<Integer> comparator;

   private static final String DIR_DATA   = "data/",
                               DIR_OUTPUT = "output/";

   private static final String FILE_HIGHLY_UNORDERED = DIR_DATA + "highly_unordered.txt";


   static {
      // This Comparator puts nulls last

      comparator = new Comparator<Integer>() {
         @Override
         public int compare( final Integer i1, final Integer i2 ) {
            if( i1 == null ) return 1;
            if( i2 == null ) return -1;

            return i1.compareTo( i2 );
         }
      };
   }


   public static void main( final String args[] ) throws IOException {
      final Main application = new Main();

      application.doSort( new BubbleSort<Integer>(comparator), FILE_HIGHLY_UNORDERED );
      application.doSort( new SelectionSort<Integer>(comparator), FILE_HIGHLY_UNORDERED );
   }

   private void doSort( final SortingAlgorithm<Integer> algorithm, final String filename ) throws IOException {
      final String prefix;
      final List<Integer> data = loadFile( filename );
      final Statistics stats = algorithm.sortList( data );
      final File dirOutput = new File( DIR_OUTPUT );
      BufferedWriter writer = null;

      if( BubbleSort.class.equals(algorithm.getClass()) ) {
         prefix = "Bubble Sort";
      } else if( SelectionSort.class.equals(algorithm.getClass()) ) {
         prefix = "Selection Sort";
      } else {
         prefix = "";
      }

      dirOutput.mkdirs();

      try {
         writer = new BufferedWriter( new FileWriter(DIR_OUTPUT + prefix + ".txt", false) );

         for( int i = 0; i < data.size(); i++ ) {
            writer.write( data.get(i) );
            writer.write( "\n" );
         }
      } finally {
         if( writer != null ) {
            writer.close();
         }
      }

      System.out.println( "\n" + prefix + "\n" + stats );
   }

   private List<Integer> loadFile( final String filename ) throws IOException {
      final List<Integer> data = new ArrayList<Integer>();
      final BufferedReader br = new BufferedReader( new FileReader(filename) );

      String s = br.readLine();

      while( s != null ) {
         int i = Integer.parseInt( s );
         s = br.readLine();
         data.add( i );
      }

      br.close();

      return data;
   }
}
