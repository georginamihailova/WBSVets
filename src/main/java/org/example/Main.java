package org.example;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//         Path to your CSV file and RDF output file
        String csvFilePath = "C:\\Users\\ninam\\Downloads\\wbs_project\\wbs_project\\WBS_Vets\\veterinary_ambulances.csv";
        String outputFilePath = "C:\\Users\\ninam\\OneDrive\\Desktop\\WBS_Schools\\veterinary_ambulances_output.rdf";


//            String csvFilePath = "/Users/darko/Developer/Finki/WBS/wbs_project/WBS_Vets/veterinary_ambulances.csv";
//            String outputFilePath = "/Users/darko/Developer/Finki/WBS/wbs_project/WBS_Vets/veterinary_ambulances_output.rdf";
        try {
            // Use CSVLoader to load the CSV file
            List<String[]> csvLines = CSVLoader.loadCSV(csvFilePath);  // Now using List<String[]>

            // Remove the header if needed (assuming the first line is the header)
            csvLines.remove(0);

            // Create an instance of the RDFTripleCreator class
            RDFTriplesCreator tripleCreator = new RDFTriplesCreator();

            // Generate RDF triples from the CSV data and save them to the specified output file
            tripleCreator.createTriples(csvLines, outputFilePath);

            System.out.println("RDF triples have been successfully created and saved to: " + outputFilePath);


        } catch (Exception e) {
            // Handle any errors that occur during processing
            System.err.println("Error processing CSV file: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
