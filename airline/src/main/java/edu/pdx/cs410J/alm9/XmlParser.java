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
