package edu.pdx.cs410J.alm9;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class AirlineRestClient extends HttpRequestHelper {
    private static final String WEB_APP = "airline";
    private static final String SERVLET = "flights";

    private final String url;


    /**
     * Creates a client to the airline REST service running on the given host and port
     *
     * @param hostName The name of the host
     * @param port     The port
     */
    public AirlineRestClient(String hostName, int port) {
        this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
    }

    public String getAirline(String name) throws IOException {
        Response response = get(this.url, Map.of("airline", name));
        throwExceptionIfNotOkayHttpStatus(response);

        File file = new File("return.xml");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(response.getContent());

        return "return.xml";
    }

    public String getAirline(String name, String src, String dest) throws IOException {
        Response response = get(this.url, Map.of
                (
                        "airline", name,
                        "src", src,
                        "dest,", dest
                ));

        throwExceptionIfNotOkayHttpStatus(response);

        File file = new File("return.xml");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(response.getContent());
        return "return.xml";
    }

    public void postAirline(InputModel input) throws IOException {
        Response response = postToMyURL(Map.of
                (
                        "airline", input.airline,
                        "flightNumber", input.flightNumber,
                        "src", input.source,
                        "depart", input.departureTime,
                        "dest", input.destination,
                        "arrive", input.arrivalTime
                ));

        throwExceptionIfNotOkayHttpStatus(response);
    }


    @VisibleForTesting
    Response postToMyURL(Map<String, String> dictionaryEntries) throws IOException {
        return post(this.url, dictionaryEntries);
    }

    public void removeAllDictionaryEntries() throws IOException {
        Response response = delete(this.url, Map.of());
        throwExceptionIfNotOkayHttpStatus(response);
    }

    private Response throwExceptionIfNotOkayHttpStatus(Response response) {
        int code = response.getCode();
        if (code != HTTP_OK) {
            throw new AirlineRestException(code);
        }
        return response;
    }

    @VisibleForTesting
    class AirlineRestException extends RuntimeException {
        AirlineRestException(int httpStatusCode) {
            super("Got an HTTP Status Code of " + httpStatusCode);
        }
    }


    /**
     * Returns all dictionary entries from the server
     */
    public Map<String, String> getAllDictionaryEntries() throws IOException {
        Response response = get(this.url, Map.of());
        return Messages.parseDictionary(response.getContent());
    }

    /**
     * Returns the definition for the given word
     */
    public String getDefinition(String word) throws IOException {
        Response response = get(this.url, Map.of("word", word));
        throwExceptionIfNotOkayHttpStatus(response);
        String content = response.getContent();
        return Messages.parseDictionaryEntry(content).getValue();
    }

    public void addDictionaryEntry(String word, String definition) throws IOException {
        Response response = postToMyURL(Map.of("word", word, "definition", definition));
        throwExceptionIfNotOkayHttpStatus(response);
    }
}
