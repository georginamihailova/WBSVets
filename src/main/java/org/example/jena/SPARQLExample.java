package org.example.jena;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.example.VetVocabulary; // Import your vocabulary class

public class SPARQLExample {


    public static void queryVetClinics(Model model, String location, String nameOfPlace) {
        // Build the SPARQL query dynamically
        StringBuilder queryStringBuilder = getStringBuilder(location, nameOfPlace);

        // Create the query
        Query query = QueryFactory.create(queryStringBuilder.toString());
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                String name = soln.getLiteral("name") != null ? soln.getLiteral("name").getString() : "No Name";
                String loc = soln.getLiteral("location") != null ? soln.getLiteral("location").getString() : "No Location";
                System.out.println("Name: " + name + ", Location: " + loc);
            }
        } catch (QueryExecException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getStringBuilder(String location, String nameOfPlace) {
        StringBuilder queryStringBuilder = new StringBuilder("SELECT ?name ?location WHERE { ");
        queryStringBuilder.append("?s <http://example.org/vet/hasNameOfPlace> ?name . ");
        queryStringBuilder.append("?s <http://example.org/vet/hasLocation> ?location . ");

        // Add filters based on the parameters
        if (location != null && !location.isEmpty()) {
            queryStringBuilder.append("FILTER (str(?location) = \"" + location + "\") . ");
        }
        if (nameOfPlace != null && !nameOfPlace.isEmpty()) {
            queryStringBuilder.append("FILTER (str(?name) = \"" + nameOfPlace + "\") . ");
        }

        queryStringBuilder.append("}");
        return queryStringBuilder;
    }

}
