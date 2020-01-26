import Functions.MyChangeFunc;
import Functions.MyFunction;
import collections.MyList;

class User1 {
    public String name;
    public int age;
    public User1(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class TestFunctions {
    public static void testFunc() {
        User1 u1 = new User1("test", 12);
        User1 u2 = new User1("dou", 1);
        User1[] ar = {u1, u2};
        MyFunction<String> printText = (text) -> "!" + text + "!";
        MyFunction<Integer> pow = (num) -> num * num;
        MyChangeFunc<User1, String> userToString = (user) -> {
            return "Name: " + user.name + " \n " + user.age;
        };
        for (int i = 0; i < ar.length; i++) {
            System.out.println(userToString.apply(ar[i]));
        }

        System.out.println(printText.func(" Hello world ").equals( "! Hello world !"));
        System.out.println(pow.func(2) == 4);
        System.out.println(pow.func(9) == 81);
    }
}
