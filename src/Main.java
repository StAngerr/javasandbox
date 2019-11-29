import collections.MyList;

import java.util.ArrayList;

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
    public static void main(String[] args) {
        User user1 = new User("Rick", 22);
        User user2 = new User("Tom", 14);
        User user3 = new User("Jack", 1);
        User user4 = new User("test", 22);
        User user5 = new User("Fuck ", 21321);
        User user6 = new User("Rick ", 22);
        User user7 = new User("Irynka Brusket", 22);

        ArrayList<User> other = new ArrayList<>();
        other.add(user1);
        other.add(user4);
        other.add(user5);
         other.add(user7);

        MyList test1 = new MyList();
        test1.add(user1);
        test1.add(user2);
        test1.add(user3);
        test1.add(user4);
        test1.add(user5);
        test1.add(user7);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        test1.add(user6);
        checkPerformance();
        // int[] ar = new int[Integer.MAX_VALUE - 5];
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
}
