package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;

public class RDFExporter {

    public static void exportToJsonLD(Model model, String outputFilePath) {
        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
            RDFDataMgr.write(outputStream, model, org.apache.jena.riot.RDFLanguages.JSONLD);
            System.out.println("Exported RDF to JSON-LD format at: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
