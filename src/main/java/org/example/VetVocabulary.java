package org.example;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class VetVocabulary {
    public static final String NS = "http://example.org/vet/";

    public static final Property HAS_LOCATION = ModelFactory.createDefaultModel().createProperty(NS, "hasLocation");
    public static final Property HAS_DATE = ModelFactory.createDefaultModel().createProperty(NS, "hasDate");
    public static final Property HAS_NAME = ModelFactory.createDefaultModel().createProperty(NS, "hasNameOfPlace");
    public static final Property HAS_LEGAL_ENTITY = ModelFactory.createDefaultModel().createProperty(NS, "hasLegalEntity");
    public static final Property HAS_ADDRESS = ModelFactory.createDefaultModel().createProperty(NS, "hasAddress");
    public static final Property HAS_LONGITUDE = ModelFactory.createDefaultModel().createProperty(NS, "hasLongitude");
    public static final Property HAS_LATITUDE = ModelFactory.createDefaultModel().createProperty(NS, "hasLatitude");
    public static final Property HAS_TYPE = ModelFactory.createDefaultModel().createProperty(NS, "hasType");
}