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

        ArrayList<User> arTest = new ArrayList<>();

       MyList<User> test = new MyList<User>();
        test.add(null);
        test.add(user1);
        test.add(user2);
        test.add(user3);
        test.add(user4);
        test.add(user5);

        System.out.println(test);
        MyList<User> remove = new MyList<>();
        remove.add(user3);
        remove.add(user4);
        test.removeAll(remove);
        System.out.println(test);

                /// System.out.println(test);

        // System.out.println(test1.getIf((Object item) -> ((User) item).name == "sdsds Brusket").get());

//        System.out.println(test1.size());
//        System.out.println(test1.removeAll(other));
//        System.out.println(test1.size());
        // System.out.println(user1.hashCode());
        // System.out.println(user6.hashCode());
//        int[] ar = new int[10];
//        int index = 0;
//        ar[index++] = 10;
//        System.out.println(ar[0]);
//        System.out.println(index);
//        ar[index++] = 40;
//        System.out.println(ar[1]);
//        System.out.println(index);


     }
}
