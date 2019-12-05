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
    public String toString() {
        return name + " " + age;
    }
}
public class Main {
    private static int tests = 0;
    private static List<String> failedMessages = new ArrayList<>();
    public static void main(String[] args) {
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
        System.out.println(test1.toArray().length);
        // testSizeMethod(test1, user4);
        // testIsEmptyMethod(test1);
        // testContainsMethod(test1, user3, user4);
        // testIteratorMethod(test1);
        testToArray(test1);
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

    private static void testToArray(MyList test1) {
        check(test1, (list) -> list.toArray().length, test1.size(), "Returning array size should be equal to collection size");
        check(new MyList(), (list) -> list.toArray().length, 0, "Returning array size should be 0 if empty collection");


        printTestResults();
    }

    private static void testForEachMethod(MyList collec) {
//        check(collec, (list) -> {
//            int sum = 0;
//            list.forEach((User item) -> {
//                sum += item.age;
//            })
//        }, 38, "forEach should collect sum of all age.");
    }

    private static void testIteratorMethod(MyList test1) {
        Iterator iterator = test1.iterator();
        if (!(iterator instanceof Iterator)) {
            failedMessages.add("Returning type should be instance of Iterator class");
        }
        tests++;
        printTestResults();
    }

    private static void testContainsMethod(MyList col, User one, User two) {
        check(col, (list) -> list.contains(one), true, "Should return TRUE for user that exist in collection.");
        check(col, (list) -> list.contains(new User("tese", 22)), false, "Should return FALSE for not existing user in collection.");
        check(col, (list) -> list.contains(null), false, "Should return FALSE for null if no null in collection.");
        col.add(null);
        check(col, (list) -> list.contains(null), true, "Should return TRUE for null if null exist in collection.");
        printTestResults();
    }

    private static void testIsEmptyMethod(MyList test) {
        check(new MyList(), MyList::isEmpty, true, "Should return TRUE for empty list.");
        check(test, MyList::isEmpty, false, "Should return FALSE for list with one item.");
        printTestResults();
    }

    private static void testSizeMethod(MyList test1, User user) {
        // START testing
        check(new MyList(), MyList::size, 0, "Empty list size.");
        check(test1, MyList::size, 7, "MyList invalid initial size.");
        test1.remove(user);
        check(test1, MyList::size, 6, "MyList invalid size after removing.");
        test1.add(new User("test user", 22));
        check(test1, MyList::size, 7, "MyList invalid size after adding an item.");
        test1.removeAll(test1);
        check(test1, MyList::size, 0, "MyList invalid size after removing all.");
        printTestResults();
        // END testing
    }
}
