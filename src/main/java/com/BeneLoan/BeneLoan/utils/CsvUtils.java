package com.BeneLoan.BeneLoan.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvUtils {

    public static CSVParser parse(InputStream inputStream, String[] headers) throws Exception
    {
        var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        var format = CSVFormat.DEFAULT
                .builder()
                .setHeader(headers)
                .setSkipHeaderRecord(true) //first line is header
                .setTrim(true)
                .build();
        return new CSVParser(reader, format);
    }

}
