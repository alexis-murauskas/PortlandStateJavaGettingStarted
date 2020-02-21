package edu.pdx.cs410J.alm9;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
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

    /**
     * Given an available XML file, this method will parse the file and return an airline object.
     * If there is no XML file, the parser exits and returns null. If the text contents are mal-
     * formatted, an error will be thrown.
     * @return Parsed airline object
     * @throws ParserException if XML file contents are malformatted
     */
    @Override
    public T parse() throws ParserException {
        Airline<Flight> airline;
        try {
            Document document = createDocument();
            airline = processAirlineName(document);
            Collection<InputModel> flights = processFlights(document);

            for (var flight : flights)
                airline.addFlight(flight);

        } catch (FileNotFoundException e) {
            return null;
        }
        catch (Exception e) {
            throw new ParserException(e.getMessage());
        }

        return (T) airline;
    }

    /**
     * Based on the file name given at instantiation, this method will create an XML document
     * and build the contents using the SAX API. If the file is not present, an exception
     * will be thrown.
     * @return A new document object
     * @throws IOException if the contents of the file are malformatted
     * @throws SAXException if there is an error accessing the DOM elements
     * @throws ParserConfigurationException if the parser was incorrectly configured
     */
    private Document createDocument() throws IOException, SAXException, ParserConfigurationException {
        File in = new File(fileName);

        if (!in.isFile()) {
            throw new FileNotFoundException();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(in);
        document.getDocumentElement().normalize();

        return document;
    }

    /**
     * All airlines must have an associated name. Here the document checks if a name is present.
     * If it is a multi-word name, then quotes are added to correct the formatting. A new airline
     * object is returned
     * @param document an airline document
     * @return a new airline object with the name indicated in the document
     */
    private Airline processAirlineName(Document document) {
        NodeList airlineElement = document.getElementsByTagName("name");
        String name = airlineElement.item(0).getTextContent();

        if (name.contains(" "))
            name = "'" + name + "'";

        return new Airline<Flight>(name);
    }

    /**
     * This is the entry point for flight processing. Based on an airline document, this method
     * attempts to parse flight objects. Each flight has a number, departure location and date,
     * and arrival location and date. This is the wrapper that iterates through flight elements
     * and sends them for processing.
     * @param document an airline document
     * @return a collection of flight objects
     * @throws ParseException if an error occurs while parsing the XML
     */
    private Collection<InputModel> processFlights(Document document) throws ParseException {
        ArrayList<InputModel> flights = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName("flight");

        for (int i = 0; i < nodes.getLength(); ++i) {
            var rv = processFlight(nodes.item(i));
            flights.add(rv);
        }

        return flights;
    }

    /**
     * Each flight must be processed for each sub-element it contains. The date and time elements
     * are the only ones with attributes.
     * @param node a flight node
     * @return model representing a flight
     * @throws ParseException if file contents are malformatted
     */
    private InputModel processFlight(Node node) throws ParseException {
        Element flight = (Element) node;
        ArrayList<String> in = new ArrayList<>();

        in.add("placeholder");
        in.add(flight.getElementsByTagName("number").item(0).getTextContent());

        in.add(flight.getElementsByTagName("src").item(0).getTextContent());
        String departString = processDateTime(
                flight.getElementsByTagName("date").item(0),
                flight.getElementsByTagName("time").item(0)
                );

        String[] depart = departString.split(";");
        in.add(depart[0]);
        in.add(depart[1]);
        in.add(depart[2]);

        in.add(flight.getElementsByTagName("dest").item(0).getTextContent());
        String arriveString =  processDateTime(
                flight.getElementsByTagName("date").item(1),
                flight.getElementsByTagName("time").item(1)
        );

        String[] arrive = arriveString.split(";");
        in.add(arrive[0]);
        in.add(arrive[1]);
        in.add(arrive[2]);

        String[] array = new String[in.size()];
        in.toArray(array);

        return AirlineCommand.parse(array);
    }

    /**
     * Because flight arrivals and departures have complex formatting they must be processed
     * separately.
     * @param dateNode a date element
     * @param timeNode a time element
     * @return string of date/time pairing separated by semi-colons
     */
    private String processDateTime(Node dateNode, Node timeNode) {
        String rv = "";

        Element date = (Element) dateNode;
        Element time = (Element) timeNode;

        rv += date.getAttribute("month") + "/";
        rv += date.getAttribute("day") + "/";
        rv += date.getAttribute("year") + ";";

        // Parse time
        int rawHour = Integer.parseInt(time.getAttribute("hour"));
        int converted = rawHour % 12;

        rv += converted + ":";
        String minute = time.getAttribute("minute");

        if (minute.length() < 2)
            rv += "0" + minute + ";";
        else
            rv += minute + ";";

        // Parse period
        if (rawHour == converted)
            rv += "am";
        else
            rv += "pm";

        return rv;
    }
}
