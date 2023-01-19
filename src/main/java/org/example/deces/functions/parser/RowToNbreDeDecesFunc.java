package org.example.deces.functions.parser;

import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.sql.Row;
import org.example.deces.beans.NbreDeDeces;

import java.io.Serializable;
import java.util.function.Function;

public class RowToNbreDeDecesFunc implements Function<Row, NbreDeDeces>, Serializable {

    @Override
    public NbreDeDeces apply(Row row){

        String line = row.getAs("value");

        String semaine = line.substring(80,81);
        int nb_cas_resid = Integer.parseInt(line.substring(81,89));
        int nb_cas_prsnl = Integer.parseInt(line.substring(89,94));

        return NbreDeDeces.builder()
                .semaine(semaine)
                .nb_cas_resid(nb_cas_resid)
                .nb_cas_prsnl(nb_cas_prsnl)
                .build();
    }
}
