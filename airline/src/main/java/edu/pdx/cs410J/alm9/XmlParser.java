package edu.pdx.cs410J.alm9;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlParser<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineParser<T> {
    private String fileName;

    public XmlParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T parse() throws ParserException {
        Airline<Flight> airline;
        try {
            Document document = createDocument();
            airline = processAirlineName(document);
            Collection<InputModel> flights = processFlights(document);

            for (var flight : flights)
                airline.addFlight(flight);
        } catch (Exception e) {
            throw new ParserException(e.toString());
        }

        return (T) airline;
    }

    private Document createDocument() throws IOException, SAXException, ParserConfigurationException {
        File in = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(in);
        document.getDocumentElement().normalize();

        return document;
    }

    private Airline processAirlineName(Document document) {
        NodeList airlineElement = document.getElementsByTagName("name");
        String name = airlineElement.item(0).getTextContent();

        if (name.contains(" "))
            name = "'" + name + "'";

        return new Airline<Flight>(name);
    }

    private Collection<InputModel> processFlights(Document document) throws ParseException {
        ArrayList<InputModel> flights = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName("flight");

        for (int i = 0; i < nodes.getLength(); ++i) {
            var rv = processFlight(nodes.item(i));
            flights.add(rv);
        }

        return flights;
    }

    private InputModel processFlight(Node node) throws ParseException {
        Element flight = (Element) node;
        ArrayList<String> in = new ArrayList<>();

        in.add("placeholder");
        in.add(flight.getElementsByTagName("number").item(0).getTextContent());

        in.add(flight.getElementsByTagName("src").item(0).getTextContent());
        String departString = processDateTime(flight.getElementsByTagName("depart"));
        String[] depart = departString.split(";");
        in.add(depart[0]);
        in.add(depart[1]);
        in.add(depart[2]);

        in.add(flight.getElementsByTagName("dest").item(0).getTextContent());
        String arriveString =  processDateTime(flight.getElementsByTagName("arrive"));
        String[] arrive = arriveString.split(";");
        in.add(arrive[0]);
        in.add(arrive[1]);
        in.add(arrive[2]);

        String[] array = new String[in.size()];
        in.toArray(array);

        return AirlineCommand.parse(array);
    }

    private String processDateTime(NodeList list) {
        String rv = "2/2/2020;4:00;pm";

        return rv;
    }
}
