package com.eng.gp.project.util.date;

import java.util.Collection;

/**
 * This enumerates the various things we know how to measure and maps them
 * to the applicable collection of Measurement that describe the situations where
 * it can be used. This allows us to easily find out how to measure a property in
 * any supported measurement system. 
 */
public enum MeasurementOf {
        AREA,

        PER_AREA,

        GAS_VOLUME,

        BILLING_GAS_VOLUME,

        LIQUID_VOLUME,

        PRESSURE,

        DEGREE_DAYS,

        TEMPERATURE;

        public Collection<Measurement> getMeasurements(){
            return Measurement.measures(this);
        }
}
