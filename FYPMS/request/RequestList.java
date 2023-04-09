package FYPMS.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of requests
 */
public class RequestList implements Iterable<Request> {

    private final List<Request> requests;

    /**
     * Creates a new, empty RequestList
     */
    public RequestList() {
        requests = new ArrayList<>();
    }

    /**
     * Adds a request to the RequestList
     * @param request The request to add
     */
    public void add(Request request) {
        requests.add(request);
    }

    /**
     * Prints the details of each request in the RequestList
     */
    public void printDetails() {
        for (Request request : requests) {
            request.printDetails();
        }
    }

    /**
     * Returns an iterator over the requests in the RequestList
     */
    @Override
    public Iterator<Request> iterator() {
        return requests.iterator();
    }

    /**
     * Returns whether there is another request to iterate over
     * @param iterator The iterator being used
     * @return true if there is another request to iterate over, false otherwise
     */
    public boolean hasNext(Iterator<Request> iterator) {
        return iterator.hasNext();
    }

    /**
     * Returns the next request to iterate over
     * @param iterator The iterator being used
     * @return the next request to iterate over
     */
    public Request next(Iterator<Request> iterator) {
        return iterator.next();
    }
}
