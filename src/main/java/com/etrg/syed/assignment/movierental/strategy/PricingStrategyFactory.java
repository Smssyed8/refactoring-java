/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy;

import com.etrg.syed.assignment.movierental.model.MovieTypeEnum;
import com.etrg.syed.assignment.movierental.strategy.impl.ChildrensPricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.impl.NewReleasePricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.impl.RegularPricingStrategy;

public class PricingStrategyFactory {
    public static PricingStrategy getStrategy(MovieTypeEnum type) {
        return switch (type) {
            case REGULAR -> new RegularPricingStrategy();
            case NEW_RELEASE -> new NewReleasePricingStrategy();
            case CHILDRENS -> new ChildrensPricingStrategy();
            default -> throw new IllegalArgumentException("Unknown movie type: " + type);
        };
    }
}
