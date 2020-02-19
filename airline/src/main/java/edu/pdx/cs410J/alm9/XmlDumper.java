package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlDumper<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {
    private String fileName;

    public XmlDumper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void dump(T airline) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Root
            Element root = document.createElement("airline");
            document.appendChild(root);

            // Airline name
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(airline.getName()));
            root.appendChild(name);

            // Create XML document
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        }
        catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
