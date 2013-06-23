package com.eng.gp.project.util.date;

import java.util.Collection;

public enum MeasurementSystem {
    METRIC,

    US_STANDARD,

    NONE;

    public Collection<Measurement> getMeasurements(){
        return Measurement.measures(this);
    }

    public static MeasurementSystem getSystemByDataUnit(String unitId) {
    	//BUGBUG: this assumes that all systems have mutually exclusive unit names.
    	//currently we dont have a way to tell which system a unit comes from
    	Measurement testMeasure = METRIC.findByUnitId(unitId);
    	if (testMeasure != null) {
    		return METRIC;
    	}
    	
    	testMeasure = US_STANDARD.findByUnitId(unitId);
    	if (testMeasure != null) {
    		return US_STANDARD;
    	}
    	
    	return null;
    }
    
    public Measurement findByUnitId(String id) {
        for(Measurement measure : getMeasurements()){
    		if (measure.unit.equals(id)) {
    			return measure;
    		}
    	}
    	return null;
    }
    
    public Measurement findByMeasurementId(MeasurementOf id) {
        return Measurement.measure(this, id);
    }

    public static Measurement getConversion(MeasurementSystem from, MeasurementSystem to, String unitType) {
    	Measurement fromUnit = from.findByUnitId(unitType);
    	if (fromUnit == null) {
    		return null;
    	}
    	
    	return to.findByMeasurementId(fromUnit.measurementOf);
    }
}
