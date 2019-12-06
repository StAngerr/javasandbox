import collections.MyList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

class User {
    String name;
    int age;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
public class Main {
    private static int tests = 0;
    private static List<String> failedMessages = new ArrayList<>();
    public static void main(String[] args) {
        testSizeMethod();
        testIsEmptyMethod();
        testContainsMethod();
        testIteratorMethod();
        testToArray();
        testAddMethod();
        testRemoveMethod();
        testIndexOfMethod();
        testAddAllMethod();
        testClearMethod();
        testHashCode();
        testRetainAllMethod();
        testRemoveAll();
        testContainsAll();
        testCloneMethod();
        printTestResults();
    }


     public static void checkPerformance() {
        long start = System.currentTimeMillis();
        MyList<Object> test = new MyList<>();
        // Integer.MAX_VALUE - 10
        for (int i = 0; i < 100000; i++) {
            test.add(new Object());
        }
        System.out.println("Performance result: " + (System.currentTimeMillis() - start));
     }


    private static <T> void check(MyList list, Function<MyList, T> func, T comparingVal, String message) {
        if (!func.apply(list).equals(comparingVal)) {
            failedMessages.add(message);
        }
        tests++;
    }

    private static void printTestResults() {
        if (failedMessages.size() > 0) {
            System.out.println("Failed tests:");
            failedMessages.forEach(m -> System.out.println(" - " + m));
        }
        System.out.println(tests + " tests passed. Failed " + failedMessages.size() + ".");
    }

    private static void testCloneMethod() {
        MyList original = new MyList();
        MyList clone = original.clone();
        if (original.equals(clone)) {
            failedMessages.add("Clone should return new instance of collection");
        }
        tests++;

    }

    private static void testContainsAll() {
        User one = new User("contains", 1);
        User two = new User("contains", 2);
        MyList basic = new MyList();
        basic.add(new User("some", 11));
        basic.add(one);
        basic.add(two);
        MyList containsCol = new MyList();
        containsCol.add(one);
        containsCol.add(two);
        check(basic, (list) -> list.containsAll(containsCol), true, "Should return true for containing items.");
        containsCol.add(new User("test user", 33));
        check(basic, (list) -> list.containsAll(containsCol), false, "Should return false if not contains some.");
    }


    private static void testRemoveAll() {
        User one = new User("remove", 123);
        User two = new User("remove2", 123);
        MyList basic = new MyList();
        basic.add(new User("sadas", 11));
        basic.add(one);
        basic.add(two);
        MyList removeCol = new MyList();
        removeCol.add(one);
        removeCol.add(two);
        check(basic, (list) -> list.removeAll(removeCol), true, "Should successfully remove all items.");
        check(basic, MyList::size, 1, "Size should change after removing");
    }

    private static void testHashCode() {
        MyList basic = new MyList();
        basic.add(new User("sad", 2));
        basic.add(new User("sad", 2));
        check(basic, MyList::hashCode, 6, "Hash code should be equal to sum for every item's hashcode  + collection length");
    }

    public static void testRetainAllMethod() {
        User retain1 = new User("some", 88);
        User retain2 = new User("else", 12);
        MyList basic = new MyList();
        MyList retainCollection = new MyList();
        basic.add(new User("temove", 22));
        basic.add(retain1);
        basic.add(retain2);
        basic.add(new User("",12));
        retainCollection.add(retain1);
        retainCollection.add(retain2);
        check(basic, (list) -> list.retainAll(retainCollection), true, "Should retain without errors");
        check(basic, MyList::size, 2, "List size should change after removing items.");
    }


    private static void testClearMethod() {
        MyList basic = new MyList();
        basic.add(new User("sad", 2));
        basic.add(new User("sad", 2));
        basic.clear();
        check(basic, MyList::size, 0, "Collection size should be equal to zero after clearing.");
    }

    private static void testAddAllMethod() {
        MyList basic = new MyList();
        MyList toAdd = new MyList();
        toAdd.add(new User("no nae", 2));
        toAdd.add(new User("no nae", 2));
        toAdd.add(new User("no nae", 2));
        check(basic, (list) -> list.addAll(toAdd), true, "Should add collection without errors.");
        check(basic, MyList::size, 3, "List size should increase after adding collection of items.");
        basic.addAll(new MyList());
        check(basic, MyList::size, 3, "Size should not be changed if adding empty list");
    }

    private static void testIndexOfMethod() {
        MyList test1 = new MyList();
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        test1.add(user1);
        test1.add(user2);
        test1.add(user3);
        check(test1, (list) -> list.indexOf(user1), 0, "1Should return proper index.");
        check(test1, (list) -> list.indexOf(user3), 2, "2Should return proper index.");
        check(test1, (list) -> list.indexOf(new User("sadas", 1111)), -1, "Should negative one if no such user in collection.");
    }

    private static void testRemoveMethod() {
        MyList test1 = new MyList();
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        test1.add(user1);
        test1.add(user2);
        test1.add(user3);
        int startSize = test1.size();
        check(test1, (list) -> list.remove(user1), true, "Should remove user from collection.");
        check(test1, MyList::size, startSize - 1, "After remove size should be decreased.");
        check(test1, (list) -> list.remove(new User("asdsadasdas", 232131)), false, "Should not remove item if it is not exist in collection.");
        check(test1,MyList::size, startSize - 1, "Size should be the same, if removing item not exist in collection.");
    }

    private static void testAddMethod() {
        MyList list1 = new MyList<>();
        User user1 = new User("asd", 22);
        check(list1, (list) -> list.add(user1), true, "Should return true for success operation");
        check(list1, MyList::size, 1, "List size should increase after adding an item");
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        list1.add(new User("New user", 65));
        check(list1, MyList::size, 13, "Should dynamically change list size when new items are added");
    }

    private static void testToArray() {
        MyList test1 = new MyList();
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        test1.add(user1);
        test1.add(user2);
        test1.add(user3);
        check(test1, (list) -> list.toArray().length, test1.size(), "Returning array size should be equal to collection size");
        check(new MyList(), (list) -> list.toArray().length, 0, "Returning array size should be 0 if empty collection");

    }

    private static void testForEachMethod(MyList collec) {
//        check(collec, (list) -> {
//            int sum = 0;
//            list.forEach((User item) -> {
//                sum += item.age;
//            })
//        }, 38, "forEach should collect sum of all age.");
    }

    private static void testIteratorMethod() {
        MyList test1 = new MyList();
        Iterator iterator = test1.iterator();
        if (!(iterator instanceof Iterator)) {
            failedMessages.add("Returning type should be instance of Iterator class");
        }
        tests++;
    }

    private static void testContainsMethod() {
        MyList col = new MyList();
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        col.add(user1);
        col.add(user2);
        col.add(user3);
        check(col, (list) -> list.contains(user1), true, "Should return TRUE for user that exist in collection.");
        check(col, (list) -> list.contains(new User("tese", 22)), false, "Should return FALSE for not existing user in collection.");
        check(col, (list) -> list.contains(null), false, "Should return FALSE for null if no null in collection.");
        col.add(null);
        check(col, (list) -> list.contains(null), true, "Should return TRUE for null if null exist in collection.");
    }

    private static void testIsEmptyMethod() {
        MyList test = new MyList();
        User user1 = new User("Rick", 3);
        test.add(user1);
        check(new MyList(), MyList::isEmpty, true, "Should return TRUE for empty list.");
        check(test, MyList::isEmpty, false, "Should return FALSE for list with one item.");
    }

    private static void testSizeMethod() {
        MyList test1 = new MyList();
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        test1.add(user1);
        test1.add(user2);
        test1.add(user3);
        // START testing
        check(new MyList(), MyList::size, 0, "Empty list size.");
        check(test1, MyList::size, 3, "MyList invalid initial size.");
        test1.remove(user2);
        check(test1, MyList::size, 2, "MyList invalid size after removing.");
        test1.add(new User("test user", 22));
        check(test1, MyList::size, 3, "MyList invalid size after adding an item.");
        test1.removeAll(test1);
        check(test1, MyList::size, 0, "MyList invalid size after removing all.");
        // END testing
    }


    private static MyList getData() {
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        User user4 = new User("test", 5);
        User user5 = new User("Fuck ", 22);
        User user6 = new User("Rick ", 4);
        User user7 = new User("Irynka Brusket", 1);

        MyList test1 = new MyList();
        test1.add(user1);
        test1.add(user2);
        test1.add(user3);
        test1.add(user4);
        test1.add(user5);
        test1.add(user6);
        test1.add(user7);
        return test1;
    }

    private static User[] getUsers() {
        User user1 = new User("Rick", 3);
        User user2 = new User("Tom", 2);
        User user3 = new User("Jack", 1);
        User user4 = new User("test", 5);
        User user5 = new User("Fuck ", 22);
        User user6 = new User("Rick ", 4);
        User user7 = new User("Irynka Brusket", 1);
        User[] users = new User[7];
        users[0] = user1;
        users[1] = user2;
        users[2] = user3;
        users[3] = user4;
        users[4] = user5;
        users[5] = user6;
        users[6] = user7;
        return users;
    }
}
