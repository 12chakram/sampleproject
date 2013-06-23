package com.eng.gp.project.util.date;

import java.util.HashMap;
import java.util.Map;
/**
 * A helper class for converting cardinal directions in to degrees.
 * @author ksarzynski
 *
 */
@SuppressWarnings("serial")
public class CardinalDirection
{
    private static final Map<String, Double> CARDINAL_TO_DEGREES = new HashMap<String, Double>() {{
        put("N", new Double(0));
        put("NNE", new Double(22.5));
        put("NE", new Double(45));
        put("ENE", new Double(67.5));
        put("E", new Double(90));
        put("ESE", new Double(112.5));
        put("SE", new Double(135));
        put("SSE", new Double(157.5));
        put("S", new Double(180));
        put("SSW", new Double(202.5));
        put("SW", new Double(225));
        put("WSW", new Double(247.5));
        put("W", new Double(270));
        put("WNW", new Double(292.5));
        put("NW", new Double(315));
        put("NNW", new Double(337.5));
    }};

    /**
     * Convert a cardinal direction in to degrees (0-360).
     * @param cardinalDirection The direction to be converted
     * @return a Double that represents the direction in degrees
     */
    public static Double getDegrees(String cardinalDirection)
    {
        return CARDINAL_TO_DEGREES.get(cardinalDirection);
    }
}
