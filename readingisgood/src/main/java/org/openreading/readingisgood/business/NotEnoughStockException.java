package org.openreading.readingisgood.business;

/** @author Muhammet Sakarya created at 8/15/2021 */
public class NotEnoughStockException extends RuntimeException {
  public NotEnoughStockException(String was_not_enough) {}
}
