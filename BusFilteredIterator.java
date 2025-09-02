
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BusFilteredIterator implements Iterator<Bus> {

    /**
     * The iterator we are filtering.
     */
    private Iterator<Bus> baseIterator;

    /**
     * The destination BusStop we are filtering by.
     */
    private BusStop destination;

    /**
     * The next Bus to be returned, or null if there aren't any more.
     */
    private Bus next;

    /**
     * Constructs a new BusFilteredIterator that filters the given iterator to
     * return only Bus-es that stop at the given destination.
     *
     * @param iterator   the iterator we are filtering.
     * @param destination the desired destination.
     */
    public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
        this.baseIterator = iterator;
        this.destination = destination;
        //initializes the next option for first bus that goes to destination.
        advanceToNext();
    }

    /**
     * Private helper method that advances this iterator.
     * It will iterate over `this.iterator` until it reaches a Bus that stops at destination.
     * Then, it will store that Bus in `next`.
     */
    private void advanceToNext() {
        while (baseIterator.hasNext()) {
            Bus bus = baseIterator.next();
            if (bus.goesTo(destination)) {
                //find a bus that goes to given destination
                next = bus;
                return;
            }
        }
        //no buses left going to given destination
        next = null;
    }

    /**
     * Returns true if there is another Bus (that goes to the destination) in this iterator,
     * or false otherwise. This method should not change any of the fields of the iterator.
     *
     * @return true if a call to next() will return another Bus; false otherwise.
     */
    @Override
    public boolean hasNext() {
        //check if next is not null to see if there is another bus
        return next != null;
    }

    /**
     * Returns the `next` bus and advances the iterator until the next bus it will return.
     *
     * @return Buses from the iterator baseIterator that go to the destination stop.
     * @throws NoSuchElementException if called when there is no next Bus.
     */
    @Override
    public Bus next() {
        if (!hasNext()) {
            //throw exception if there is no next bus
            throw new NoSuchElementException();
        }
        //storing the next available bus
        Bus current = next;
        //advancing to the next bus that goes to the destination
        advanceToNext();
        //return current bus
        return current;
    }
}