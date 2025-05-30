package org.dflib.avro.types;

import org.dflib.avro.Avro;
import org.apache.avro.Schema;


public class AvroTypeExtensions {

    public static final SingleSchemaLogicalType UNMAPPED_TYPE = new SingleSchemaLogicalType("dflib-unmapped", Schema.Type.STRING);

    static {
        Avro.registerCustomType(new BigDecimalConversion());
        Avro.registerCustomType(new BigIntegerConversion());
        Avro.registerCustomType(new ByteArrayConversion());
        Avro.registerCustomType(new DurationConversion());
        Avro.registerCustomType(new LocalDateConversion());
        Avro.registerCustomType(new LocalDateTimeConversion());
        Avro.registerCustomType(new LocalTimeConversion());
        Avro.registerCustomType(new PeriodConversion());
        Avro.registerCustomType(new YearMonthConversion());
        Avro.registerCustomType(new YearConversion());

        Avro.registerCustomType(new UnmappedConversion(UNMAPPED_TYPE));
    }

    /**
     * Invoked from loaders and savers to ensure that DFLib Avro types extensions are loaded in the environment.
     * This method can be safely called multiple times.
     */
    public static void initIfNeeded() {
        // Noop with a side effect... By calling this method the caller could trigger class loading,
        // which in turn would call "init()"
    }
}
