public class Main {
    public static void main(String[] args) {

        // Create and use a Bicycle
        Bicycle bicycle = new Bicycle(10, 15, 3);
        System.out.println("Bicycle: ");
        System.out.println("Cadence: " + bicycle.cadence);
        System.out.println("Speed: " + bicycle.speed);
        System.out.println("Gear: " + bicycle.gear);
        bicycle.speedUp(5);
        bicycle.applyBrake(3);
        System.out.println("After speeding up and braking, Speed: " + bicycle.speed);
        System.out.println();

        // Create and use a MountainBike
        MountainBike mountainBike = new MountainBike(5, 12, 20, 4);
        System.out.println("MountainBike: ");
        System.out.println("Cadence: " + mountainBike.cadence);
        System.out.println("Speed: " + mountainBike.speed);
        System.out.println("Gear: " + mountainBike.gear);
        System.out.println("Seat Height: " + mountainBike.seatHeight);
        mountainBike.setHeight(7);
        System.out.println("After adjusting seat height, Seat Height: " + mountainBike.seatHeight);
        System.out.println();

        // Create and use a KidsBicycle
        KidsBicycle kidsBicycle = new KidsBicycle(8, 10, 2, 2);
        System.out.println("KidsBicycle: ");
        System.out.println("Cadence: " + kidsBicycle.cadence);
        System.out.println("Speed: " + kidsBicycle.speed);
        System.out.println("Gear: " + kidsBicycle.gear);
        System.out.println("Training Wheels: " + kidsBicycle.numberOfTrainingWheels);
        kidsBicycle.removeTrainingWheels();
        System.out.println("After removing training wheels, Training Wheels: " + kidsBicycle.numberOfTrainingWheels);
        kidsBicycle.addTrainingWheels();
        System.out.println("After adding training wheels, Training Wheels: " + kidsBicycle.numberOfTrainingWheels);
    }
}

// Output:

// Bicycle: 
// Cadence: 10
// Speed: 15
// Gear: 3
// After speeding up and braking, Speed: 17

// MountainBike: 
// Cadence: 12
// Speed: 20
// Gear: 4
// Seat Height: 5
// After adjusting seat height, Seat Height: 7

// KidsBicycle: 
// Cadence: 8
// Speed: 10
// Gear: 2
// Training Wheels: 2
// After removing training wheels, Training Wheels: 0
// After adding training wheels, Training Wheels: 2