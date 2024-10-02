package org.example.interactiveMap;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.example.RDFExporter;

public class jsonExporter {
    public static void main(String[] args) {
        // Create an RDF model
        Model model = ModelFactory.createDefaultModel();

        // Load your RDF data into the model
        // Assuming you have a method to load RDF data
//        loadRDFData(model, "/Users/darko/Developer/Finki/WBS/wbs_project/WBS_Vets/veterinary_ambulances_output.rdf");
                loadRDFData(model, "C:\\Users\\ninam\\Downloads\\wbs_project\\wbs_project\\WBS_Vets\\veterinary_ambulances_output.rdf");


        // Export the RDF data to JSON-LD
//        RDFExporter.exportToJsonLD(model, "/Users/darko/Developer/Finki/WBS/wbs_project/WBS_Vets/veterinary_ambulances_output.jsonld");
                RDFExporter.exportToJsonLD(model, "C:\\Users\\ninam\\Downloads\\wbs_project\\wbs_project\\WBS_Vets\\veterinary_ambulances_output.jsonld");


    }

    private static void loadRDFData(Model model, String inputFilePath) {
        // Implement your logic to read RDF data into the model
        try {
            model.read(inputFilePath);
            System.out.println("RDF data loaded into model.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
