public class MathLab {
    /**
     * The Math class contains utility methods that can be used for calculations like exponents, rounding, random number generation, etc. The point of this challenge, however, is not strictly to use the Math class - there are many "Utility Classes" which follow a curious pattern - where we usually instantiate an object before using it, classes such as Math, Arrays, System are used without instantiating the object - we would skip directly to using a method such as Math.pow() immediately. How does this work?
     *
     * The Math class contains static methods, which means that it does not need to be instantiated for a developer to use the contained methods. They are global to the entire program. This is very important to understand, as new coders often misuse the static keyword. So, static methods and variables of Math are associated with the Math class rather than a Math object.
     *
     * This is also an exercise in reading the official Oracle documentation rather than relying on a tutorial for beginners. In the future, when you will be navigating complex topics, you will need to rely on official documentation to learn how to use new tools, which is oftentimes rather dry.
     * Enjoy: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
     *
     * @return return a^b using the pow() method of the Math class.
     */
    public double mathPow(double a, double b) {
        return Math.pow(a, b);
    }

    public static void main(String[] args) {
        // Create an instance of the Lab class
        MathLab lab = new MathLab();

        // Test the mathPow method with some example inputs
        double base = 2;
        double exponent = 3;

        double result = lab.mathPow(base, exponent);

        // Print the result
        System.out.println(base + " raised to the power of " + exponent + " is " + result);

        // Additional test cases
        System.out.println("5^2 = " + lab.mathPow(5, 2));
        System.out.println("3^4 = " + lab.mathPow(3, 4));
        System.out.println("10^0 = " + lab.mathPow(10, 0));
        System.out.println("2^(-3) = " + lab.mathPow(2, -3));
    }
}