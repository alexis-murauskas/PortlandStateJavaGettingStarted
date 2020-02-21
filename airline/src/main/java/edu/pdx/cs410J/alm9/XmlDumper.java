package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class XmlDumper<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {
    private String fileName;

    public XmlDumper(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Takes an airline object, formats the contents, and dumps to an XML file. If the specified
     * file name does not already exist, it is created.
     * @param airline airline data to be dumped
     * @throws IOException if there is an error writing to the file
     */
    @Override
    public void dump(T airline) throws IOException {
        try {
            Document document = createDocument();

            Element root = document.createElement("airline");
            document.appendChild(root);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(airline.getName()));
            root.appendChild(name);

            Collection<Q> flights = airline.getFlights();
            for (Q flight : flights) {
                Element child = processFlight(document, flight);
                root.appendChild(child);
            }

            createXml(document);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Given a file name, a new document object is created. If the file did not exist before
     * it is created now.
     * @return a new document object
     * @throws ParserConfigurationException if there is an error creating the document
     */
    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        return documentBuilder.newDocument();
    }

    /**
     * Processes each flight associated with the airline. Elements must be created for the next level
     * of tags, and then the data formatted correctly.
     * @param document an airline document
     * @param flight a flight to be processed
     * @return the flight element to be attached to the airline element
     */
    private Element processFlight(Document document, Q flight) {
        Element child = document.createElement("flight");
        Element number = document.createElement("number");
        Element src = document.createElement("src");
        Element depart = document.createElement("depart");
        Element dest = document.createElement("dest");
        Element arrive = document.createElement("arrive");

        number.appendChild(document.createTextNode(String.valueOf(flight.getNumber())));
        child.appendChild(number);

        src.appendChild(document.createTextNode(flight.getSource()));
        child.appendChild(src);

        depart.appendChild(processDate(document, flight.getDeparture()));
        depart.appendChild(processTime(document, flight.getDeparture()));
        child.appendChild(depart);

        dest.appendChild(document.createTextNode(flight.getDestination()));
        child.appendChild(dest);

        arrive.appendChild(processDate(document, flight.getArrival()));
        arrive.appendChild(processTime(document, flight.getArrival()));
        child.appendChild(arrive);

        return child;
    }

    /**
     * Converts date instance to a calendar instance.
     * @param dateTime a date object
     * @return a new calendar object
     */
    private Calendar convertDateTime(Date dateTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);

        return calendar;
    }

    /**
     * Given a date and an airline document, create a new date node to attach to a flight
     * @param document an airline document
     * @param dateTime date associated with flight
     * @return the date element to be attached to the flight
     */
    private Element processDate(Document document, Date dateTime) {
        Element date = document.createElement("date");
        Calendar calendar = convertDateTime(dateTime);

        Attr day = document.createAttribute("day");
        Attr month = document.createAttribute("month");
        Attr year = document.createAttribute("year");

        day.setValue(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        month.setValue(String.valueOf(calendar.get(Calendar.MONTH)));
        year.setValue(String.valueOf(calendar.get(Calendar.YEAR)));

        date.setAttributeNode(day);
        date.setAttributeNode(month);
        date.setAttributeNode(year);

        return date;
    }

    /**
     * Similar to the processDate method, this method processes a given time.
     * @param document an airline document
     * @param dateTime date associated with flight
     * @return the time element to be attached to the flight
     */
    private Element processTime(Document document, Date dateTime) {
        Element time = document.createElement("time");
        Calendar calendar = convertDateTime(dateTime);

        Attr hour = document.createAttribute("hour");
        Attr minute = document.createAttribute("minute");

        hour.setValue(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        minute.setValue(String.valueOf(calendar.get(Calendar.MINUTE)));

        time.setAttributeNode(hour);
        time.setAttributeNode(minute);

        return time;
    }

    /**
     * Based on the given document, writes out an XML file to the name the dumper was
     * instantiated with and transforms the document with necessary keys.
     * @param document an XML document to be written
     * @throws TransformerException if there was an error in configuring the transformer
     */
    private void createXml(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, AirlineXmlHelper.SYSTEM_ID);

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(fileName));

        transformer.transform(domSource, streamResult);
    }
}
