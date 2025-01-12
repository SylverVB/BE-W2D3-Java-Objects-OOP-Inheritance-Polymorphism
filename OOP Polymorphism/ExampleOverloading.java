// Exercise

// Overload the DisplayOverloading class's disp method so that it can also accept two ints (int1 and int2) and prints out (int1 " + " int2).

class DisplayOverloading {
    public void disp(char c) {
        System.out.println(c);
    }
    public void disp(char c, int num) {
        System.out.println(c + " " + num);
    }
    public void disp(int int1, int int2) {
        System.out.println(int1 + " " + int2);
    }
}

public class ExampleOverloading {
    public static void main(String args[]) {
        DisplayOverloading obj = new DisplayOverloading();
        obj.disp('a');
        obj.disp('a',10);
        obj.disp(5, 7);
    }
}