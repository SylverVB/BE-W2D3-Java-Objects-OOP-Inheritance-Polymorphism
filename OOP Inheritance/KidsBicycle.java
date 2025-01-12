// Exercise:

// Create a subclass of Bicycle called KidsBicycle that:

//   • Adds a new int field called "NumberOfTrainingWheels"
//   • Adds two new methods:
//       • "AddTrainingWheels" sets the NumberOfTrainingWheels to the number 2.
//       • "RemoveTrainingWheels" sets the NumberOfTrainingWheels to the number 0.


public class KidsBicycle extends Bicycle {

    // The KidsBicycle subclass adds one field
    public int numberOfTrainingWheels;

    // The KidsBicycle subclass has one constructor
    public KidsBicycle(int startCadence, 
                       int startSpeed, 
                       int startGear, 
                       int startTrainingWheels) {
        super(startCadence, startSpeed, startGear);
        numberOfTrainingWheels = startTrainingWheels;
    }

    // Method to add training wheels
    public void addTrainingWheels() {
        numberOfTrainingWheels = 2;
    }

    // Method to remove training wheels
    public void removeTrainingWheels() {
        numberOfTrainingWheels = 0;
    }
}