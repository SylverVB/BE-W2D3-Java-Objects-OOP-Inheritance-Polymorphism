// Upcasting and Downcasting:
// 	•	The train method demonstrates upcasting because it accepts an Athlete but can handle Snowboarder and BasketballPlayer.
// 	•	The train2 method demonstrates downcasting after type-checking with instanceof.

// Output

// When you run the Main class, you should see:

// Casting Objects

// Upcasting:
// I am a snowboarder. I go fast, but when I fall the ground is soft!
// I am a basketball player. Sometimes I take 3 pointers, sometimes I windmill jam.

// Downcasting:
// I'm a snowboarder! I do backflips and my board is blue
// I'm a basketball player and I can jump 4 feet high

public class Main {
    public static void train(Athlete athlete) {
        athlete.print();
    }

    public static void train2(Athlete athlete) {
        if (athlete instanceof Snowboarder) {
            // Downcasting
            Snowboarder snowboarder = (Snowboarder) athlete;
            System.out.println("I'm a snowboarder! I do backflips and my board is " + snowboarder.boardColor);
        } else if (athlete instanceof BasketballPlayer) {
            // Downcasting
            BasketballPlayer ballPlayer = (BasketballPlayer) athlete;
            System.out.println("I'm a basketball player and I can jump " + ballPlayer.jumpHeight + " feet high");
        }
    }

    public static void main(String[] args) {
        System.out.println("Casting Objects\n");

        // Create objects to cast
        Snowboarder snowboarder = new Snowboarder();
        BasketballPlayer ballPlayer = new BasketballPlayer();

        // Upcasting
        System.out.println("Upcasting:");
        train(snowboarder); 
        train(ballPlayer);

        System.out.println("\nDowncasting:");
        // Downcasting
        train2(snowboarder);
        train2(ballPlayer);
    }
}