package org.example.owl;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.io.BufferedReader;

public class OntologyFromCSV {
    public static void main(String[] args) {
        // Create an empty ontology model
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        // Define the namespace for your ontology
        String namespace = "http://example.org/vet_clinics#";

        // Create classes
        OntClass veterinaryClinicClass = model.createClass(namespace + "VeterinaryClinic");

        // Read CSV data using BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/darko/Developer/Finki/WBS/wbs_project/WBS_Vets/veterinary_ambulances.csv"))) {
            String line;
            // Skip header if present
            br.readLine(); // Uncomment this line if your CSV has a header

            while ((line = br.readLine()) != null) {
                // Split the line into values (assuming the CSV is semicolon-separated)
                String[] values = line.split(";"); // Split using semicolon

                // Assuming the columns based on your CSV structure
                String id = values[2]; // Clinic ID
                String name = values[6]; // Clinic Name
                String address = values[7]; // Address
                String latitude = values[9]; // Latitude
                String longitude = values[10]; // Longitude

                // Create individuals for each clinic
                Individual clinic = model.createIndividual(namespace + id, veterinaryClinicClass);
                clinic.addLabel(name, null);
                clinic.addProperty(model.createDatatypeProperty(namespace + "address"), address);
                clinic.addProperty(model.createDatatypeProperty(namespace + "latitude"), String.valueOf(latitude));
                clinic.addProperty(model.createDatatypeProperty(namespace + "longitude"), String.valueOf(longitude));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Save the ontology to a file
        try (OutputStream out = new FileOutputStream("veterinary_clinics.owl")) {
            model.write(out, "RDF/XML-ABBREV");
            System.out.println("Ontology saved to veterinary_clinics.owl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
