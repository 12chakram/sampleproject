package com.eng.gp.project.util.date;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ConversionUtil {

    // Setup map of units to conversion info
    private static final ConversionRule celsiusConversionRule = new ConversionRule() {
        public String getSourceUnit() {return "DEGREES_CELSIUS";}
        public String getDestinationUnit() {return "DEGREES_FAHRENHEIT";}
        public double convertFrom(double baseVal) {return (baseVal - 32) * (5.0/9.0);}
        public double convertTo(double initialVal) {return (9.0/5.0 * initialVal) + 32.0;}
    };
    private static final Map<String, UnitInfo> unitInfos;
    static {
        Map<String, UnitInfo> tmpUnitInfos = new HashMap<String, UnitInfo>();
        tmpUnitInfos.put("GAL", new UnitInfo(MeasurementOf.LIQUID_VOLUME, getBaseConversionRule("GAL")));
        tmpUnitInfos.put("CUFT", new UnitInfo(MeasurementOf.GAS_VOLUME, getBaseConversionRule("CUFT")));
        tmpUnitInfos.put("CCFT", new UnitInfo(MeasurementOf.BILLING_GAS_VOLUME, getBaseConversionRule("CCFT")));
        tmpUnitInfos.put("POUND_PER_SQ_INCH", new UnitInfo(MeasurementOf.PRESSURE, getBaseConversionRule("POUND_PER_SQ_INCH")));
        tmpUnitInfos.put("SQFT", new UnitInfo(MeasurementOf.AREA, getBaseConversionRule("SQFT")));
        tmpUnitInfos.put("PER_SQFT", new UnitInfo(MeasurementOf.PER_AREA, getBaseConversionRule("PER_SQFT")));
        tmpUnitInfos.put("DEGREE_DAYS_FAHRENHEIT", new UnitInfo(MeasurementOf.DEGREE_DAYS, getBaseConversionRule("DEGREE_DAYS_FAHRENHEIT")));
        tmpUnitInfos.put("DEGREES_FAHRENHEIT", new UnitInfo(MeasurementOf.TEMPERATURE, getBaseConversionRule("DEGREES_FAHRENHEIT")));
        tmpUnitInfos.put("LITER", new UnitInfo(MeasurementOf.LIQUID_VOLUME, getFromFactorConversionRule("LITER", "GAL", 3.78541178)));
        tmpUnitInfos.put("M3", new UnitInfo(MeasurementOf.GAS_VOLUME, getFromFactorConversionRule("M3", "CUFT", 0.0283168466)));
        tmpUnitInfos.put("M3_BILL", new UnitInfo(MeasurementOf.BILLING_GAS_VOLUME, getFromFactorConversionRule("M3_BILL", "CCFT", 2.83168466)));
        tmpUnitInfos.put("KPA", new UnitInfo(MeasurementOf.PRESSURE, getFromFactorConversionRule("KPA", "POUND_PER_SQ_INCH", 6.89475729)));
        tmpUnitInfos.put("SQM", new UnitInfo(MeasurementOf.AREA, getFromFactorConversionRule("SQM", "SQFT", 0.09290304)));
        tmpUnitInfos.put("PER_SQM", new UnitInfo(MeasurementOf.PER_AREA, getFromFactorConversionRule("PER_SQM", "PER_SQFT", 10.7639104167097)));
        tmpUnitInfos.put("DEGREE_DAYS_CELSIUS", new UnitInfo(MeasurementOf.DEGREE_DAYS, getFromFactorConversionRule("DEGREE_DAYS_CELSIUS", "DEGREE_DAYS_FAHRENHEIT", (5.0/9.0))));
        tmpUnitInfos.put("DEGREES_CELSIUS", new UnitInfo(MeasurementOf.TEMPERATURE, celsiusConversionRule));
        unitInfos = Collections.unmodifiableMap(tmpUnitInfos);
    }

    public interface ConversionRule {
        public String getSourceUnit();
        public String getDestinationUnit();
        public double convertFrom(double baseVal);
        public double convertTo(double initialVal);
    }

    private static class UnitInfo {
        public final MeasurementOf measureOf;
        public final ConversionRule baseConversionRule;

        private UnitInfo(MeasurementOf measureOf, ConversionRule baseConversionRule) {
            this.measureOf = measureOf;
            this.baseConversionRule = baseConversionRule;
        }
    }

    private static ConversionRule getBaseConversionRule(final String baseUnit) {
        return new ConversionRule() {
            public String getSourceUnit() {return baseUnit;}
            public String getDestinationUnit() {return baseUnit;}
            public double convertFrom(double baseVal) {return baseVal;}
            public double convertTo(double initialVal) {return initialVal;}
        };
    }

    private static ConversionRule getFromFactorConversionRule(final String sourceUnit, final String destinationUnit, final double factor) {
        return new ConversionRule() {
            public String getSourceUnit() {return sourceUnit;}
            public String getDestinationUnit() {return destinationUnit;}
            public double convertFrom(double baseVal) {return baseVal * factor;}
            public double convertTo(double initialVal) {return initialVal / factor;}
        };
    }

    private static ConversionRule combineBaseConversionRules (final ConversionRule sourceBaseRule, final ConversionRule localeBaseRule) {
        if (!localeBaseRule.getDestinationUnit().equals(sourceBaseRule.getDestinationUnit())) {
            throw new IllegalArgumentException("Conversion rules are being combined that don't have the same destination.");
        }
        // Special case where A -> B X B -> B returns A -> B
        if (localeBaseRule.getSourceUnit().equals(localeBaseRule.getDestinationUnit())) {
            return sourceBaseRule;
        }
        final String destinationUnit = localeBaseRule.getSourceUnit();
        final String sourceUnit = sourceBaseRule.getSourceUnit();
        return new ConversionRule() {
            public String getSourceUnit() {return sourceUnit;}
            public String getDestinationUnit() {return destinationUnit;}
            public double convertTo(double initialVal) {return localeBaseRule.convertFrom(sourceBaseRule.convertTo(initialVal));}
            public double convertFrom(double initialVal) {return sourceBaseRule.convertFrom(localeBaseRule.convertTo(initialVal));}
        };
    }

    public static class Converter {
        private final Map<String, ConversionRule> conversions;

        private Converter(MeasurementSystem measurementSystem) {
            Map<String, ConversionRule> tmpConversions =  new HashMap<String, ConversionRule>();
            if (measurementSystem != null) {
                for (Measurement measurement : measurementSystem.getMeasurements()) {
                    UnitInfo localeUnitInfo = unitInfos.get(measurement.getUnit());
                    if (localeUnitInfo == null) {
                        continue;
                    }

                    for (Map.Entry<String, UnitInfo> entry : unitInfos.entrySet()) {
                        String sourceUnit = entry.getKey();
                        UnitInfo sourceUnitInfo = entry.getValue();
                        if (sourceUnitInfo.measureOf.equals(localeUnitInfo.measureOf)) {
                            tmpConversions.put(sourceUnit, combineBaseConversionRules(sourceUnitInfo.baseConversionRule, localeUnitInfo.baseConversionRule));
                        }
                    }
                }
            }
            this.conversions = Collections.unmodifiableMap(tmpConversions);
        }

        public double convertTo(String initialUnit, double initialVal) {
            if (conversions.containsKey(initialUnit)) {
                return conversions.get(initialUnit).convertTo(initialVal);
            }
            else {
                return initialVal;
            }
        }

        public String convertGraphUnit(String initialUnit) {
            if (conversions.containsKey(initialUnit)) {
                return conversions.get(initialUnit).getDestinationUnit();
            }
            else {
                return initialUnit;
            }
        }

        public ConversionRule getConversionRuleForUnit(String unit) {
            return conversions.get(unit);

        }
    }

    public static Converter getConverter (MeasurementSystem measurementSystem) {
        return new Converter(measurementSystem);
    }

}
