package org.example;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.example.deces.beans.NbreDeDeces;
import org.example.deces.functions.parser.NbreDeDecesMapper;

public class Main {
    public static void main(String[] args) {

        System.out.println("Fichier des personnes atteintes ou décédées du Covid");

        Config config = ConfigFactory.load("application.conf");
        String masterUrl = config.getString("app.master");
        String appName = config.getString("app.name");

        SparkSession sparkSession = SparkSession
                .builder()
                .master(masterUrl)
                .appName(appName)
                .getOrCreate();

        String inputPathStr = config.getString("app.path.input");

        Dataset<Row> inputDS = sparkSession.read().text(inputPathStr);
        inputDS.printSchema();
        inputDS.show(5, false);

        Dataset<NbreDeDeces> cleanDS = new NbreDeDecesMapper().apply(inputDS);
        cleanDS.printSchema();
        cleanDS.show(10, false);

        Dataset<Row> cleanDS2 = cleanDS.groupBy("semaine").count();
        cleanDS2.show();



    }
}