package com.eng.gp.project.util.date;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * This POJO represents an abstract measurement. It contains info about the 
 * unit of the measurement, what it is a measurement of and what system of
 * measurement it belongs to.
 */
public class Measurement{

    private static final Map<MeasurementSystem, Map<MeasurementOf, Measurement>> MEASUREMENT_SYSTEM_TO_MEASUREMENT_OF = new EnumMap<MeasurementSystem, Map<MeasurementOf, Measurement>>(MeasurementSystem.class);
    private static final Map<MeasurementOf, Map<MeasurementSystem, Measurement>> MEASUREMENT_OF_TO_MEASUREMENT_SYSTEM = new EnumMap<MeasurementOf, Map<MeasurementSystem, Measurement>>(MeasurementOf.class);
    private static final Map<String, Measurement> UNIT_TO_MEASUREMENT = new HashMap<String, Measurement>();

    private static void add(Measurement measurement){
        MeasurementSystem ms = measurement.getMeasurementSystem();
        MeasurementOf mo = measurement.getMeasurementOf();
        String unit = measurement.getUnit();

        if(UNIT_TO_MEASUREMENT.containsKey(unit)){
            throw new IllegalArgumentException("Duplicate unit "+unit+" not allowed");
        }
        UNIT_TO_MEASUREMENT.put(unit, measurement);

        if(!MEASUREMENT_SYSTEM_TO_MEASUREMENT_OF.containsKey(ms)){
            MEASUREMENT_SYSTEM_TO_MEASUREMENT_OF.put(ms, new EnumMap<MeasurementOf, Measurement>(MeasurementOf.class));
        }
        if(!MEASUREMENT_OF_TO_MEASUREMENT_SYSTEM.containsKey(mo)){
            MEASUREMENT_OF_TO_MEASUREMENT_SYSTEM.put(mo, new EnumMap<MeasurementSystem, Measurement>(MeasurementSystem.class));
        }

        MEASUREMENT_SYSTEM_TO_MEASUREMENT_OF.get(ms).put(mo, measurement);
        MEASUREMENT_OF_TO_MEASUREMENT_SYSTEM.get(mo).put(ms, measurement);
    }

    static Measurement measure(String unit){
        return UNIT_TO_MEASUREMENT.get(unit);
    }

    static Measurement measure(MeasurementSystem ms, MeasurementOf mo){
        Map<MeasurementOf, Measurement> msMap = MEASUREMENT_SYSTEM_TO_MEASUREMENT_OF.get(ms);
        if (null != msMap) {
            return msMap.get(mo);
        } else {
            return null;
        }
    }

    static Collection<Measurement> measures(MeasurementSystem ms) {
        Map<MeasurementOf, Measurement> msMap = MEASUREMENT_SYSTEM_TO_MEASUREMENT_OF.get(ms);
        if (null != msMap) {
            return msMap.values();
        } else {
            return Collections.emptyList();
        }
    }

    static Collection<Measurement> measures(MeasurementOf mo){
        Map<MeasurementSystem, Measurement> moMap = MEASUREMENT_OF_TO_MEASUREMENT_SYSTEM.get(mo);
        if(null != moMap){
            return moMap.values();
        } else {
            return Collections.emptyList();
        }
    }

    static {
        // METRIC
        add(new Measurement("SQM", MeasurementOf.AREA, MeasurementSystem.METRIC));
        add(new Measurement("PER_SQM", MeasurementOf.PER_AREA, MeasurementSystem.METRIC));
        add(new Measurement("M3", MeasurementOf.GAS_VOLUME, MeasurementSystem.METRIC));
        add(new Measurement("M3_BILL", MeasurementOf.BILLING_GAS_VOLUME, MeasurementSystem.METRIC));
        add(new Measurement("LITER", MeasurementOf.LIQUID_VOLUME, MeasurementSystem.METRIC));
        add(new Measurement("KPA", MeasurementOf.PRESSURE, MeasurementSystem.METRIC));
        add(new Measurement("DEGREE_DAYS_CELSIUS", MeasurementOf.DEGREE_DAYS, MeasurementSystem.METRIC));
        add(new Measurement("DEGREES_CELSIUS", MeasurementOf.TEMPERATURE, MeasurementSystem.METRIC));

        // US
        add(new Measurement("SQFT", MeasurementOf.AREA, MeasurementSystem.US_STANDARD));
        add(new Measurement("PER_SQFT", MeasurementOf.PER_AREA, MeasurementSystem.US_STANDARD));
        add(new Measurement("CUFT", MeasurementOf.GAS_VOLUME, MeasurementSystem.US_STANDARD));
        add(new Measurement("CCFT", MeasurementOf.BILLING_GAS_VOLUME, MeasurementSystem.US_STANDARD));
        add(new Measurement("GAL", MeasurementOf.LIQUID_VOLUME, MeasurementSystem.US_STANDARD));
        add(new Measurement("POUND_PER_SQ_INCH", MeasurementOf.PRESSURE, MeasurementSystem.US_STANDARD));
        add(new Measurement("DEGREE_DAYS_FAHRENHEIT", MeasurementOf.DEGREE_DAYS, MeasurementSystem.US_STANDARD));
        add(new Measurement("DEGREES_FAHRENHEIT", MeasurementOf.TEMPERATURE, MeasurementSystem.US_STANDARD));
    }

    public final String unit;
    public final MeasurementOf measurementOf;
    public final MeasurementSystem measurementSystem;

    private Measurement(String unit, MeasurementOf measurementOf, MeasurementSystem measurementSystem){
        if(null == measurementOf){
            throw new IllegalArgumentException("measurementOf must not be null");
        }
        this.unit = unit;
        this.measurementOf = measurementOf;
        this.measurementSystem = measurementSystem;
    }

    public String getUnit(){
        return unit;
    }

    public MeasurementOf getMeasurementOf(){
        return measurementOf;
    }

    public MeasurementSystem getMeasurementSystem(){
        return measurementSystem;
    }

    public String toString(){
        return "Unit: " + unit + " MeasurementOf: " + measurementOf + " MeasurementSystem: " + measurementSystem;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((measurementOf == null) ? 0 : measurementOf.hashCode());
        result = prime
                * result
                + ((measurementSystem == null) ? 0 : measurementSystem
                        .hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Measurement other = (Measurement) obj;
        if (measurementOf == null) {
            if (other.measurementOf != null)
                return false;
        } else if (!measurementOf.equals(other.measurementOf))
            return false;
        if (measurementSystem == null) {
            if (other.measurementSystem != null)
                return false;
        } else if (!measurementSystem.equals(other.measurementSystem))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        return true;
    }
}
