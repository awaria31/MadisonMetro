import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Iterator;

public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   *
   * @return true if the test passes, false otherwise.
   */
  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    //creating two bus routes with different arrival times
    BusRoute route1 = BusRoute.dummyRoute("Route1", BusRoute.BusDirection.OUTGOING,
            new int[]{1, 2}, new String[]{"09:00", "10:00"}, BusRoute.Day.weekdays());

    BusRoute route2 = BusRoute.dummyRoute("Route2", BusRoute.BusDirection.INCOMING,
            new int[]{1, 2}, new String[]{"11:00", "12:00"}, BusRoute.Day.weekdays());

    //creating buses for two routes, bus2 arrives later than bus1
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    //comparing both buses
    int result1 = bus1.compareTo(bus2);
     if (result1 >= 0){
       // bus1 arrives earlier than bus2
       return false;
     }

     int result2 = bus2.compareTo(bus1);
     if (result2 <= 0){
       //bus2 arrives later than bus1
       return false;
     }
     return true;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    //creating two bus routes with the same arrival times, different routes
    BusRoute route1 = BusRoute.dummyRoute("Route1", BusRoute.BusDirection.OUTGOING,
            new int[]{1, 2}, new String[]{"09:00", "10:00"}, BusRoute.Day.weekdays());
    BusRoute route2 = BusRoute.dummyRoute("Route2", BusRoute.BusDirection.OUTGOING,
            new int[]{1, 2}, new String[]{"09:00", "10:00"}, BusRoute.Day.weekdays());
    //creating buses for two routes
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    //comparing buses
    int result1 = bus1.compareTo(bus2);
    if (result1 >= 0){
      //bus 1 route is less than bus 2 route
      return false;
    }
    int result2 = bus2.compareTo(bus1);
    if (result2 <= 0){
      //bus 2 route is greater than bus 1 route
      return false;
    }
    return true;
  }

  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    //creating 2 bus routes with the same arrival times, same route names, different direction
    BusRoute route1 = BusRoute.dummyRoute("Route1", BusRoute.BusDirection.OUTGOING,
            new int[]{1, 2}, new String[]{"09:00", "10:00"}, BusRoute.Day.weekdays());
    BusRoute route2 = BusRoute.dummyRoute("Route1", BusRoute.BusDirection.INCOMING,
            new int[]{1, 2}, new String[]{"09:00", "10:00"}, BusRoute.Day.weekdays());
    //creating buses for the two routes
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    int result1 = bus1.compareTo(bus2);
    if (result1 >= 0){
      // bus1's direction is less than bus2's direction
      return false;
    }

    int result2 = bus2.compareTo(bus1);
    if (result2 <= 0){
      // bus2's direction is grater than bus1's direction
      return false;
    }
    return true ;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    //creating an array of stop IDs and stop times
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    //creating two bus routes with the same parameters
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);

    //creating buses for two routes and same stop
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    boolean compare1 = bus1.compareTo(bus2) == 0;
    boolean compare2 = bus2.compareTo(bus1) == 0;
    //comparing both buses to see if they are the same
    return compare1 && compare2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    //creating an empty BusStopTree with user stop ID of 1
    BusStopTree empty = new BusStopTree(1);
    //calling isValidBST method with the root of the empty tree
    //returns true because an empty tree is considered a valid BST
    return BusStopTree.isValidBST(empty.getRoot());
  }

  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    //creating buses with routes and specific parameters
    Bus busA = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2, 3}, new String[]{"10:00", "11:00"}));
    Bus busB = new Bus(BusStop.getStop(2), BusRoute.dummyRoute("RouteB",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"}));
    Bus busC = new Bus(BusStop.getStop(3), BusRoute.dummyRoute("RouteC",
            BusRoute.BusDirection.OUTGOING, new int[]{4}, new String[]{"12:00"}));
    Bus busD = new Bus(BusStop.getStop(4), BusRoute.dummyRoute("RouteD",
            BusRoute.BusDirection.OUTGOING, new int[]{}, new String[]{}));

    //creating nodes and forming a tree with an invalid order
    Node<Bus> rootNode = new Node<>(busA);
    rootNode.setLeft(new Node<>(busB));
    rootNode.setRight(new Node<>(busC));
    rootNode.getLeft().setRight(new Node<>(busD));
    //testing if the tree is not a valid BST
    return !BusStopTree.isValidBST(rootNode);
  }

  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    //creating buses with routes and specific parameters
    Bus busA = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2, 3}, new String[]{"10:00", "11:00"}));
    Bus busB = new Bus(BusStop.getStop(2), BusRoute.dummyRoute("RouteB",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"}));
    Bus busC = new Bus(BusStop.getStop(3), BusRoute.dummyRoute("RouteC",
            BusRoute.BusDirection.OUTGOING, new int[]{4}, new String[]{"12:00"}));
    Bus busD = new Bus(BusStop.getStop(4), BusRoute.dummyRoute("RouteD",
            BusRoute.BusDirection.OUTGOING, new int[]{}, new String[]{}));

    //creating nodes and forming a tree with a valid order
    Node<Bus> rootNode = new Node<>(busA);
    rootNode.setLeft(new Node<>(busB));
    rootNode.setRight(new Node<>(busC));
    rootNode.getLeft().setRight(new Node<>(busD));

    //testing if the tree is a valid BST
    return BusStopTree.isValidBST(rootNode);
  }

  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    //creating an empty BST with a user stop
    BusStopTree BST = new BusStopTree(BusStop.getStop(1).getStopId());

    //creating a bus
    Bus busA = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2}, new String[]{"10:00"}));

    //adding the bus to the BST
    boolean result = BST.addBus(busA);

    //checking if the bus was added properly
    return result && BST.getRoot() != null && BST.getRoot().getValue().equals(busA);
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    //creating a BST with a user stop
    BusStopTree BST = new BusStopTree(BusStop.getStop(1).getStopId());
    //creating a bus
    Bus busA = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2}, new String[]{"10:00"}));
    //adding first bus to the BST
    boolean result = BST.addBus(busA);
    //checking if the first bus was added properly
    if (!result || BST.getRoot() == null || BST.size() != 1) {
      return false;
    }
    //creating second bus
    Bus busB = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteB",
            BusRoute.BusDirection.OUTGOING, new int[]{3}, new String[]{"11:00"}));
    //adding the second bus to the BST
    result = BST.addBus(busB);
    //checking if the second bus was added properly
    return result && BST.getRoot() != null && BST.size() == 2;
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    //creating a BST with a user stop
    BusStopTree BST = new BusStopTree(BusStop.getStop(1).getStopId());
    //creating a bus
    Bus busA = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2}, new String[]{"10:00"}));
    //adding first bus to the BST
    boolean result = BST.addBus(busA);
    //checking if first bus was added properly
    if (!result || BST.getRoot() == null || BST.size() != 1) {
      return false;
    }
    //adding the duplicate bus to the BST
    result = BST.addBus(busA);
    //testing if duplicate bus was not added, and size should stay the same
    return !result && BST.getRoot() != null && BST.size() == 1;
  }

  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    //creating a BST with a user stop
    BusRoute r8 =  BusRoute.dummyRoute("8",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"08:00"});

    BusRoute r5 =  BusRoute.dummyRoute("5",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"05:00"});

    BusRoute r9 =  BusRoute.dummyRoute("9",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"});

    //adding the buses to the BST
    Bus b8 = new Bus(BusStop.getStop(1), r8);
    Bus b5 = new Bus(BusStop.getStop(1), r5);
    Bus b9 = new Bus(BusStop.getStop(1), r9);

    BusStopTree bst = new BusStopTree(1);

    //returns false if all buses are not found in BST
    bst.addBus(b8);
    if (!bst.contains(b8)) {
      return false;
    }
    bst.addBus(b5);
    if (!bst.contains(b5)) {
      return false;
    }
    if (bst.contains(b9)) {
      return false;
    }
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    //creating bus stops and a BST with a user stop
    BusRoute r8 =  BusRoute.dummyRoute("8",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"08:00"});

    BusRoute r5 =  BusRoute.dummyRoute("5",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"05:00"});

    BusRoute r9 =  BusRoute.dummyRoute("9",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"});
    //creating a bus route and buses
    Bus b8 = new Bus(BusStop.getStop(1), r8);
    Bus b5 = new Bus(BusStop.getStop(1), r5);
    Bus b9 = new Bus(BusStop.getStop(1), r9);

    BusStopTree bst = new BusStopTree(1);
    //adding buses to the BST

    bst.addBus(b8);
    bst.addBus(b5);
    bst.addBus(b9);
    //getting final node using getFirstNodeAfter method
    if (!bst.getFirstNodeAfter(LocalTime.parse("08:00"), bst.getRoot()).equals(bst.getRoot())) {
      return false;
    }

    if (!bst.getFirstNodeAfter(LocalTime.parse("07:00"), bst.getRoot()).equals(bst.getRoot())) {
      return false;
    }

    if (!bst.getFirstNodeAfter(LocalTime.parse("06:00"), bst.getRoot()).equals(bst.getRoot())) {
      return false;
    }
    //returns true if the result node is not null and its value is bus2

    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    BusRoute r8 =  BusRoute.dummyRoute("8",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"08:00"});

    BusRoute r5 =  BusRoute.dummyRoute("5",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"05:00"});

    BusRoute r9 =  BusRoute.dummyRoute("9",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"});
    BusRoute r6 =  BusRoute.dummyRoute("6",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"06:00"});
    BusRoute r11 =  BusRoute.dummyRoute("11",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"11:00"});

    //creating a bus route and buses
    Bus b8 = new Bus(BusStop.getStop(1), r8);
    Bus b5 = new Bus(BusStop.getStop(1), r5);
    Bus b9 = new Bus(BusStop.getStop(1), r9);
    Bus b6 = new Bus(BusStop.getStop(1), r6);
    Bus b11 = new Bus(BusStop.getStop(1), r11);

    BusStopTree bst = new BusStopTree(1);
    //adding buses to the BST

    bst.addBus(b8);
    bst.addBus(b5);
    bst.addBus(b9);
    bst.addBus(b6);
    bst.addBus(b11);

    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("05:00"), bst.getRoot())).getValue().equals(b5)) {
      return false;
    }

    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("04:00"), bst.getRoot())).getValue().equals(b5)) {
      return false;
    }

    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("06:00"), bst.getRoot())).getValue().equals(b6)) {
      return false;
    }

    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("03:00"), bst.getRoot())).getValue().equals(b5)) {
      return false;
    }

    return true;

  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    BusRoute r8 =  BusRoute.dummyRoute("8",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"08:00"});

    BusRoute r5 =  BusRoute.dummyRoute("5",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"05:00"});

    BusRoute r9 =  BusRoute.dummyRoute("9",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"});
    BusRoute r6 =  BusRoute.dummyRoute("6",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"06:00"});
    BusRoute r11 =  BusRoute.dummyRoute("11",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"11:00"});

    //creating a bus route and buses
    Bus b8 = new Bus(BusStop.getStop(1), r8);
    Bus b5 = new Bus(BusStop.getStop(1), r5);
    Bus b9 = new Bus(BusStop.getStop(1), r9);
    Bus b6 = new Bus(BusStop.getStop(1), r6);
    Bus b11 = new Bus(BusStop.getStop(1), r11);

    BusStopTree bst = new BusStopTree(1);
    //adding buses to the BST

    bst.addBus(b8);
    bst.addBus(b5);
    bst.addBus(b9);
    bst.addBus(b6);
    bst.addBus(b11);

    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("09:00"), bst.getRoot())).getValue().equals(b9)) {
      return false;
    }

    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("10:00"), bst.getRoot())).getValue().equals(b11)) {
      return false;
    }


    //getting final node using getFirstNodeAfter method
    if (! (bst.getFirstNodeAfter(LocalTime.parse("11:00"), bst.getRoot())).getValue().equals(b11)) {
      return false;
    }

    return true;

  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {

    BusRoute r8 =  BusRoute.dummyRoute("8",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"08:00"});

    BusRoute r5 =  BusRoute.dummyRoute("5",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"05:00"});

    BusRoute r9 =  BusRoute.dummyRoute("9",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"});
    //creating a bus route and buses
    Bus b8 = new Bus(BusStop.getStop(1), r8);
    Bus b5 = new Bus(BusStop.getStop(1), r5);
    Bus b9 = new Bus(BusStop.getStop(1), r9);

    BusStopTree bst = new BusStopTree(1);
    //adding buses to the BST

    bst.addBus(b8);
    bst.addBus(b5);
    bst.addBus(b9);

    // none go to stop 5
    BusForwardIterator forw = new BusForwardIterator(bst.getRoot(), bst.getFirstBusHelper(bst.getRoot()));

    BusStop s = BusStop.getStop(2);

    BusFilteredIterator fil = new BusFilteredIterator(forw, s);

    while (fil.hasNext()) {
      return false;
    }

    return true;

    /*
    //creating an empty BST with a user stop
    BusStopTree BST = new BusStopTree(1);

    //creating buses with routes and add to BST
    Bus bus1 = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2}, new String[]{"10:00"}));
    Bus bus2 = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteB",
            BusRoute.BusDirection.INCOMING, new int[]{3}, new String[]{"11:00"}));
    BST.addBus(bus1);
    BST.addBus(bus2);

    //creating BusFilteredIterator for buses going to a non-existent destination
    Iterator<Bus> filterIterator = BST.getNextBusesTo(LocalTime.now(), new BusStop(999, "Non-existent", 0, 0));

    //returns true if the iterator has no next value, no buses go to the destination
    return !filterIterator.hasNext();

     */

  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {
    BusRoute r8 =  BusRoute.dummyRoute("8",
            BusRoute.BusDirection.OUTGOING, new int[]{1, 3}, new String[]{"08:00", "12:00"});

    BusRoute r5 =  BusRoute.dummyRoute("5",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"05:00"});

    BusRoute r9 =  BusRoute.dummyRoute("9",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"});
    //creating a bus route and buses
    Bus b8 = new Bus(BusStop.getStop(1), r8);
    Bus b5 = new Bus(BusStop.getStop(1), r5);
    Bus b9 = new Bus(BusStop.getStop(1), r9);

    BusStopTree bst = new BusStopTree(1);

    // only b8 goes to stop 3
    BusForwardIterator forw = new BusForwardIterator(bst.getRoot(), bst.getFirstBusHelper(bst.getRoot()));

    BusStop s = BusStop.getStop(2);

    BusFilteredIterator fil = new BusFilteredIterator(forw, s);

    String ret = "";
    while (fil.hasNext()) {
      ret += fil.next()+"\n";
    }

    if (!ret.equals("[8 08:00]\n")) {
      return false;
    }

    return true;



    /*
    BusStopTree BST = new BusStopTree(1);

    BusStop stop1 = BusStop.getStop(1);
    BusStop stop2 = BusStop.getStop(2);

    Bus bus1 = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2, 3}, new String[]{"10:00", "11:00"}));
    Bus bus2 = new Bus(BusStop.getStop(2), BusRoute.dummyRoute("RouteB",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"}));
    Bus bus3 = new Bus(BusStop.getStop(3), BusRoute.dummyRoute("RouteC",
            BusRoute.BusDirection.OUTGOING, new int[]{4}, new String[]{"12:00"}));
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);

    Iterator<Bus> filterIterator = BST.getNextBusesTo(LocalTime.now(), stop1);


      return filterIterator.hasNext();

     */
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    BusStopTree BST = new BusStopTree(1);

    BusStop stop1 = BusStop.getStop(1);

    Bus bus1 = new Bus(BusStop.getStop(1), BusRoute.dummyRoute("RouteA",
            BusRoute.BusDirection.OUTGOING, new int[]{2, 3}, new String[]{"10:00", "11:00"}));
    Bus bus2 = new Bus(BusStop.getStop(2), BusRoute.dummyRoute("RouteB",
            BusRoute.BusDirection.OUTGOING, new int[]{1}, new String[]{"09:00"}));
    Bus bus3 = new Bus(BusStop.getStop(3), BusRoute.dummyRoute("RouteC",
            BusRoute.BusDirection.OUTGOING, new int[]{4}, new String[]{"12:00"}));
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);

    Iterator<Bus> filterIterator = BST.getNextBusesTo(LocalTime.now(), stop1);


      return filterIterator.hasNext();
  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus" + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }
}
