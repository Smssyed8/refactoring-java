package com.etrg.syed.assignment.movierental.strategy;

import com.etrg.syed.assignment.movierental.model.MovieTypeEnum;
import com.etrg.syed.assignment.movierental.strategy.impl.ChildrensPricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.impl.NewReleasePricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.impl.PricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.impl.RegularPricingStrategy;

/**
 * Factory Pattern is used, Encapsulates the logic of instantiating and returning specific pricing
 * strategy implementations based on movie type.
 */
public class PricingStrategyFactory {
  private PricingStrategyFactory() {}

  public static PricingStrategy getStrategy(MovieTypeEnum type) {
    return switch (type) {
      case REGULAR -> new RegularPricingStrategy();
      case NEW_RELEASE -> new NewReleasePricingStrategy();
      case CHILDREN -> new ChildrensPricingStrategy();
    };
  }
}
