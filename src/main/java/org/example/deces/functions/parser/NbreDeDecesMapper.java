package org.example.deces.functions.parser;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.example.deces.beans.NbreDeDeces;

import javax.xml.crypto.Data;
import java.util.function.Function;

public class NbreDeDecesMapper implements Function<Dataset<Row>, Dataset<NbreDeDeces>> {

    private final RowToNbreDeDecesFunc parser = new RowToNbreDeDecesFunc();

    private final MapFunction<Row, NbreDeDeces> task = parser::apply;

    @Override
    public Dataset<NbreDeDeces> apply(Dataset<Row> inputDS) {
        return inputDS.map(task, Encoders.bean(NbreDeDeces.class));
    }
}
