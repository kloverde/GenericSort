/*
 * Copyright (c) 2015 Kurtis LoVerde
 * All rights reserved
 */

package org.loverde.sorting;

import java.util.Date;

import org.loverde.sorting.exception.ExceptionMessages;


public class Statistics {

	private Date startDate,
	             endDate;

	private long iterationsPerformed,
	             swapsPerformed;


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate( final Date startDate ) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate( final Date endDate ) {
		this.endDate = endDate;
	}

	public long getElapsedTimeMillis() {
		if( getStartDate() == null ) throw new IllegalStateException( ExceptionMessages.NULL_START_DATE );
		if( getEndDate() == null) throw new IllegalStateException( ExceptionMessages.NULL_END_DATE );

		return endDate.getTime() - startDate.getTime();
	}

	public long getSwapsPerformed() {
		return swapsPerformed;
	}

	public void setSwapsPerformed( final long swapsPerformed ) {
		this.swapsPerformed = swapsPerformed;
	}

  public long getIterationsPerformed() {
      return iterationsPerformed;
   }

   public void setIterationsPerformed( final long iterationsPerformed ) {
      this.iterationsPerformed = iterationsPerformed;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder( 256 );

      sb.append( "Iterations: " );
      sb.append( getIterationsPerformed() );
      sb.append( "\nSwaps: " );
      sb.append( getSwapsPerformed() );
      sb.append( "\nElapsed time (seconds): " );
      sb.append( getElapsedTimeMillis() / 1000.0d );

      return sb.toString();
   }
}
