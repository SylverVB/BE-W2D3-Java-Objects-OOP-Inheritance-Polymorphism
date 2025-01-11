// Object class

// Object is a special class in Java which is the root class from which all other classes inherit, either directly or indirectly.

// Any class that doesn't have an extends clause implicitly inherits Object. If a subclass has an extends clause that specifies a superclass other than Object, the class still inherits Object.

// Consider this example:

// public class Manager extends SalariedEmployee...
// public class SalariedEmployee extends Employee...
// public class Employee extends Person....
// public class Person...

// The Manager class inherits the Object class indirectly because it inherits SalariedEmployee, which inherits Employee, which inherits Person, which inherits Object.

// Creating a class that doesn't inherit Object is not possible.

// Also, note that primitives (such as int variables) are not objects; therefore they do not inherit the Object class.

// Since all objects inherit from the Object class, they have at least the methods defined in the Object class:

//   • Object clone() - Returns a copy of this object.
//   • boolean equals(Object o) - Indicates whether this object is equal to the o object.
//   • void finalize() - Called by the garbage collector when the object is destroyed.
//   • Class<?> getClass() Returns a Class object that represents this object's runtime class
//   • int hashCode() - Returns this object's hash code.
//   • void notify() - Is used with threaded applications to wake up a thread that's waiting on this object.
//   • void notifyAll() - Is used with threaded applications to wake up all threads that are waiting on this object.
//   • String toString() - Returns a String representation of this object.
//   • void wait() - Causes this object's thread to wait until another thread calls notify or notifyAll.
//   • void wait(long timeout) - Is a variation of the basic wait method.
//   • void wait(long timeout, int nanos) - Another variation of the wait method.


// Object class methods

// The toString() method is automatically called if you print an Object. Usually, this is overridden to provide human-readable output. Otherwise, you will print out fully.qualified.ClassName@memoryAddress

// The equals(Object o) method compares two Objects. The == operator also compares objects, but only the memory address (i.e. will return true if and only if the variables refer to the exact same object in memory). By default, and unless you explicitly override it, the equals method simply calls the == operator.

// The hashCode() method returns a hash code - a number that puts instances of a class into a finite number of categories. There are a few rules that the method follows:

//   • You are expected to override hashCode() if you override equals()
//   • The result of hashCode() should not change in a program
//   • if .equals() returns true, the hash codes should be equal
//   • if .equals() returns false, the hash codes do not have to be distinct. However, doing so will help the performance of hash tables.

// Finally, the .finalize() method is called by the garbage collector when it determines there are no more references to the object. It can be overriden to perform cleanup activities before garbage collection, although it has been deprecated in newer versions of Java.


// Today we will introduce two methods that closely belong together: equals() and hashCode(). We'll focus on their relationship with each other, how to correctly override them, and why we should override both or neither.

// equals()

// The Object class defines both the equals() and hashCode() methods, which means that these two methods are implicitly defined in every Java class, including the ones we create:

class Money {
    int amount;
    String currencyCode;
}

Money income = new Money(55, "USD");
Money expenses = new Money(55, "USD");
boolean balanced = income.equals(expenses)

// We would expect income.equals(expenses) to return true, but with the Money class in its current form, it won't.

// The default implementation of equals() in the Object class says that equality is the same as object identity, and income and expenses are two distinct instances.

//   • Overriding equals() Let's override the equals() method so that it doesn't consider only object identity, but also the value of the two relevant properties:

@Override
public boolean equals(Object o) {
    if (o == this)
        return true;
    if (!(o instanceof Money))
        return false;
    Money other = (Money)o;
    boolean currencyCodeEquals = (this.currencyCode == null && other.currencyCode == null)
      || (this.currencyCode != null && this.currencyCode.equals(other.currencyCode));
    return this.amount == other.amount && currencyCodeEquals;
}

//   • equals() conditions Java defines the conditions that our implementation of the equals() method must fulfill. Most of the criteria are common sense. The equals() method must be:
//       • reflexive: an object must equal itself
//       • symmetric: x.equals(y) must return the same result as y.equals(x)
//       • transitive: if x.equals(y) and y.equals(z), then also x.equals(z)
//       • consistent: the value of equals() should change only if a property that is contained in equals() changes (no randomness allowed)

//   • Violating equals() Symmetry With Inheritance
//       • If the criteria for equals() is such common sense, then how can we violate it at all? Well, violations happen most often if we extend a class that has overridden equals(). Let's consider a Voucher class that extends our Money class:

    class WrongVoucher extends Money {

      private String store;

      @Override
      public boolean equals(Object o) {
          if (o == this)
              return true;
          if (!(o instanceof WrongVoucher))
              return false;
          WrongVoucher other = (WrongVoucher)o;
          boolean currencyCodeEquals = (this.currencyCode == null && other.currencyCode == null)
            || (this.currencyCode != null && this.currencyCode.equals(other.currencyCode));
          boolean storeEquals = (this.store == null && other.store == null)
            || (this.store != null && this.store.equals(other.store));
          return this.amount == other.amount && currencyCodeEquals && storeEquals;
          }

      // other methods
      }

//   • At first glance, the Voucher class and its override for equals() seem to be correct. And both equals() methods behave correctly as long as we compare Money to Money or Voucher to Voucher. But what happens, if we compare these two objects:

    Money cash = new Money(42, "USD");
    WrongVoucher voucher = new WrongVoucher(42, "USD", "Amazon");

    voucher.equals(cash) => false // As expected.
    cash.equals(voucher) => true // That's wrong.

//     This violates the symmetry criteria of the equals() contract.

//   • Fixing equals() Symmetry With Composition
//      • To avoid this pitfall, we should favor composition over inheritance.
//      • Instead of subclassing Money, let's create a Voucher class with a Money property:


      class Voucher {

          private Money value;
          private String store;

          Voucher(int amount, String currencyCode, String store) {
              this.value = new Money(amount, currencyCode);
              this.store = store;
          }

          @Override
          public boolean equals(Object o) {
              if (o == this)
                  return true;
              if (!(o instanceof Voucher))
                  return false;
              Voucher other = (Voucher) o;
              boolean valueEquals = (this.value == null && other.value == null)
              || (this.value != null && this.value.equals(other.value));
              boolean storeEquals = (this.store == null && other.store == null)
            || (this.store != null && this.store.equals(other.store));
          return valueEquals && storeEquals;
          }

          // other methods
      }

// Now equals will work symmetrically as required.

// hashCode()

//   • hashCode() returns an integer representing the current instance of the class. We should calculate this value consistent with the definition of equality for the class. Thus, if we override the equals() method, we also have to override hashCode().

//   • hashCode() conditions
//      • Java also defines a set of conditions for the hashCode() method. A thorough look at it shows how closely related hashCode() and equals() are.
//      • All three criteria for hashCode() mention the equals() method in some way:
//         • internal consistency: the value of hashCode() may only change if a property that is in equals() changes
//         • equals consistency: objects that are equal to each other must return the same hashCode
//         • collisions: unequal objects may have the same hashCode

//   • Violating the Consistency of hashCode() and equals()
//      • The second criteria of the hashCode conditions has an important consequence: If we override equals(), we must also override hashCode(). This is by far the most widespread violation regarding the equals() and hashCode() methods contracts.

//     Let's see such an example:

    class Team {

        String city;
        String department;

        @Override
        public final boolean equals(Object o) {
          // implementation
        }
    }

// The Team class overrides only equals(), but it still implicitly uses the default implementation of hashCode() as defined in the Object class. And this returns a different hashCode() for every instance of the class. This violates the second rule.

// Now, if we create two Team objects, both with city “New York” and department “marketing,” they will be equal, but they'll return different hashCodes.

// HashMap Key With an Inconsistent hashCode() But why is the violation in our Team class a problem? Well, the trouble starts when some hash-based collections are involved. Let's try to use our Team class as a key of a HashMap:

Map<Team,String> leaders = new HashMap<>();
leaders.put(new Team("New York", "development"), "Anne");
leaders.put(new Team("Boston", "development"), "Brian");
leaders.put(new Team("Boston", "marketing"), "Charlie");

Team myTeam = new Team("New York", "development");
String myTeamLeader = leaders.get(myTeam);

    // We would expect myTeamLeader to return “Anne,” but with the current code, it doesn't.

    // If we want to use instances of the Team class as HashMap keys, we have to override the hashCode() method so that it adheres to the contract; equal objects return the same hashCode.

    // Let's see an example implementation:

@Override
public final int hashCode() {
    int result = 17;
    if (city != null) {
        result = 31 * result + city.hashCode();
    }
    if (department != null) {
        result = 31 * result + department.hashCode();
    }
    return result;
}

    // After this change, leaders.get(myTeam) returns “Anne” as expected.

    // When Do We Override equals() and hashCode()? Generally, we want to override either both of them or neither of them. We just saw in Section 3 the undesired consequences if we ignore this rule.

    // Domain-Driven Design can help us decide circumstances when we should leave them be. For entity classes, for objects having an intrinsic identity, the default implementation often makes sense.

    // However, for value objects, we usually prefer equality based on their properties. Thus, we want to override equals() and hashCode().


// What is hashCode()?

// In Java, the `hashCode()` method is a method provided by the `Object` class, which returns an integer value that is used to uniquely identify an object in memory. It serves as a hash code, a numerical representation of an object's contents. It is primarily used by data structures like **hash-based collections** (e.g., `HashMap`, `HashSet`, `Hashtable`) to quickly locate or compare objects.

// Why Does `hashCode()` Need to Be Overridden?

// When you override the `equals()` method in a class to define logical equality between objects, you also need to override `hashCode()` to maintain the general contract between `equals()` and `hashCode()`.

// The `equals()` and `hashCode()` Contract

// Java's `Object` class provides a contract between `equals()` and `hashCode()`, which must be followed for consistency in hash-based collections. This contract ensures that two objects that are considered equal by the `equals()` method must return the same hash code. Here's the general contract:

// 1. Consistency with `equals()`:
//    • If two objects are considered equal (i.e., `obj1.equals(obj2)` returns `true`), then they must return the same `hashCode()`.
//    • If two objects are not equal (i.e., `obj1.equals(obj2)` returns `false`), then their hash codes can be different, but they are not required to be different.

// 2. Same object must have the same hash code:
//    • If an object is compared to itself using `equals()`, it must always return `true`, and the hash code must be the same every time the object is used.

// 3. Hash code consistency:
//    • The hash code of an object should remain constant during the lifetime of the object (i.e., it should not change if the object is not modified).

// Why Override `hashCode()` When Overriding `equals()`?

// If you override `equals()`, it is essential to also override `hashCode()` to prevent problems when objects are used in hash-based collections like `HashMap` or `HashSet`. If `equals()` is overridden but `hashCode()` is not, objects that are considered equal by `equals()` might not behave correctly in hash-based collections. Specifically, they may not be found or stored correctly.

// Here's a summary of the issues that arise if you override `equals()` without overriding `hashCode()`:
// • Hash-based collections (`HashSet`, `HashMap`) rely on `hashCode()` to store and retrieve objects. If two objects are equal according to `equals()` but have different hash codes, they might not be found or stored correctly in these collections.
// • Incorrect behavior in collections like `HashMap` or `HashSet`, where objects may be placed in the wrong bucket or fail to be retrieved correctly because their hash codes don't match.

// Example: `hashCode()` and `equals()` Contract in Practice

// Consider the following example where both `equals()` and `hashCode()` are overridden in a class:

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Overriding equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object
        if (obj == null || getClass() != obj.getClass()) return false; // null check and class type check
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name); // comparing fields
    }

    // Overriding hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name, age); // generate hash code based on name and age
    }

    public static void main(String[] args) {
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Alice", 30);
        
        System.out.println(person1.equals(person2));  // true, because they have the same name and age
        System.out.println(person1.hashCode() == person2.hashCode());  // true, because their hash codes are based on the same fields
    }
}

// In this example:
// - `equals()` checks if the `name` and `age` are the same between two `Person` objects.
// - `hashCode()` is overridden to return a hash code that is based on the `name` and `age` fields, ensuring that two `Person` objects with the same `name` and `age` will have the same hash code.

// Key Points About `hashCode()`:

// 1. Performance in Hash-Based Collections: 
//    • The primary reason for the `hashCode()` method is for efficient searching, inserting, and retrieving in hash-based collections like `HashMap`, `HashSet`, and `Hashtable`. If two objects have the same hash code (which occurs if their contents are the same), they are placed in the same "bucket" within the collection.
   
// 2. Equality vs. Identity:
//    • `equals()` compares whether two objects have the same logical value (content equality), while `hashCode()` provides a numeric value that supports efficient comparisons in hash-based data structures.

// 3. Default Implementation:
//    • If you do not override `hashCode()`, the default implementation in the `Object` class will be used, which is based on the memory address of the object (i.e., the object’s reference). This may lead to issues in collections where equality of object content is important.

// Default `hashCode()` Implementation in `Object`:
// • By default, the `hashCode()` method in the `Object` class uses the memory address of the object to generate a hash code. However, this default behavior does not satisfy the contract when `equals()` is overridden to compare object content.

// Conclusion:
// To ensure that objects are used correctly in hash-based collections like `HashMap`, `HashSet`, and others, it is important to override both `equals()` and `hashCode()` when defining equality based on the content of the object. If `equals()` compares two objects based on their field values, `hashCode()` must return the same hash code for objects with equal field values to ensure correct behavior in collections.


// Summary

// Object is a special class in Java which is the root class from which all other classes inherit, either directly or indirectly.
//  • Any class that doesn't have an extends clause implicitly inherits Object. If a subclass has an extends clause that specifies a superclass other than Object, the class still inherits Object.
//  • Creating a class that doesn't inherit Object is not possible.
//  • Also, note that primitives (such as int variables) are not objects; therefore they do not inherit the Object class.
//  • Since all objects inherit from the Object class, they have at least the methods defined in the Object class:
//      • Object clone() - Returns a copy of this object.
//      • boolean equals(Object o) - Indicates whether this object is equal to the o object.
//      • void finalize() - Called by the garbage collector when the object is destroyed.
//      • Class<?> getClass() Returns a Class object that represents this object's runtime class
//      • int hashCode() - Returns this object's hash code.
//      • void notify() - Is used with threaded applications to wake up a thread that's waiting on this object.
//      • void notifyAll() - Is used with threaded applications to wake up all threads that are waiting on this object.
//      • String toString() - Returns a String representation of this object.
//      • void wait() - Causes this object's thread to wait until another thread calls notify or notifyAll.
//      • void wait(long timeout) - Is a variation of the basic wait method.
//      • void wait(long timeout, int nanos) - Another variation of the wait method.