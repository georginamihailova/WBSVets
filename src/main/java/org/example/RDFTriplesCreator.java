package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RDFTriplesCreator {

    public void createTriples(List<String[]> csvData, String outputFilePath) throws Exception {
        // Create an empty RDF model
        Model model = ModelFactory.createDefaultModel();

        // Iterate over CSV rows
        for (String[] line : csvData) {
            // Sanitize IRI values by replacing spaces
            String id = line[0];
            String location = line[5];
            String date = line[3];
            String type = line[4];
            String nameOfPlace = line[6];
            String legalEntity = line[8];
            String address = line[9];
            String longitude = line[11];
            String latitude = line[10];


            // Create RDF resources and properties
            Resource vetResource = model.createResource(VetVocabulary.NS + id);
            vetResource.addProperty(VetVocabulary.HAS_LOCATION, location)
                    .addProperty(VetVocabulary.HAS_DATE, date)
                    .addProperty(VetVocabulary.HAS_NAME, nameOfPlace)
                    .addProperty(VetVocabulary.HAS_LEGAL_ENTITY, legalEntity)
                    .addProperty(VetVocabulary.HAS_ADDRESS, address)
                    .addProperty(VetVocabulary.HAS_LONGITUDE, longitude)
                    .addProperty(VetVocabulary.HAS_LATITUDE, latitude)
                    .addProperty(VetVocabulary.HAS_TYPE, type);

        }

        // Write RDF model to the specified output file
        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
            model.write(outputStream, "RDF/XML");

        }

        System.out.println("RDF data written to " + outputFilePath);
    }
}
