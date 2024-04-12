package com.etrg.syed.assignment.movierental.utilities;

public class Constants {

  // Pricing Strategies
  public static final double REGULAR_BASE_PRICE = 2.0;
  public static final int REGULAR_BASE_DAYS = 2;
  public static final double REGULAR_ADDITIONAL_PRICE = 1.5;
  public static final double NEW_RELEASE_PRICE_PER_DAY = 3.0;
  public static final double CHILDRENS_BASE_PRICE = 1.5;
  public static final int CHILDRENS_BASE_DAYS = 3;
  public static final double CHILDRENS_ADDITIONAL_PRICE = 1.5;

  // Frequent Renter Points
  public static final int FREQUENT_RENTER_POINTS_BASE = 1;
  public static final int BONUS_FREQUENT_RENTER_POINTS =
      2; // new released movie rented more than 2 day

  // formats
  public static final String LINE_BREAK = "\n";
  public static final String TAB = "\t";

  private Constants() {}
}
