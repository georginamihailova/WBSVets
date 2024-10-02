package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static List<String[]> loadCSV(String csvFilePath) throws IOException {
        List<String[]> csvData = new ArrayList<>();

        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                String[] row = new String[csvRecord.size()];
                for (int i = 0; i < csvRecord.size(); i++) {
                    row[i] = csvRecord.get(i);
                }
                csvData.add(row);
            }
        }

        return csvData;
    }
}
