package org.example.jena;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class ReasoningExample {
    public static void main(String[] args) {
        // Load your RDF model
        Model model = ModelFactory.createDefaultModel();
        model.read("/Users/darko/Developer/Finki/WBS/wbs_project/WBS_Vets/veterinary_ambulances_output.rdf");

        // Create a reasoner
        Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
        reasoner = reasoner.bindSchema(model); // Bind the schema (your ontology)

        // Perform reasoning
        InfModel infModel = ModelFactory.createInfModel(reasoner, model);

        // Now you can query the infModel with SPARQL
    }
}
