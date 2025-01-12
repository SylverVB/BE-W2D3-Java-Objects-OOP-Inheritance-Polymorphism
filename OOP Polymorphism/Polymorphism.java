// Polymorphism

// By definition, polymorphism means "taking on many forms". In the realm of programming, it describes how objects can behave differently in different contexts. The most common examples of polymorphism are method overloading and overriding.

// Method Overloading

// Method overloading is when there exist two or more methods in a class with the same method name, but different method signatures by changing the parameter list.

// We can change the number of parameters, the types of the parameters, or the order in which the parameters are defined. Which version of the method is executed is determined by the arguments passed when the method is invoked. Note that varying the return type of the method alone is not permitted in some OOP languages.

// Because the argument list is known at compilation, the compiler knows which version of the method will be executed. Therefore, method overloading is a type of compile-time - or static - polymorphism.

// Method Overriding

// Method overriding is when a method in a child class has the same method signature as a method in the parent class, but with a different implementation. Thus, child classes can change the default behavior given by a parent's method. Overriding methods makes class hierarchies more flexible and dynamic.

// If we have a child object that overrides a parent class method, the child classes implementation of a method will run. This is referred to as virtual method invocation and is key to method overriding. The parent class (if it is abstract) does not even need to define any implementation for an overridden method, since the method to be executed will be determined at runtime depending on the object referred to in memory. This is the reason why method overriding is classified as runtime - or dynamic - polymorphism.

// One more item to note with method overriding is that static methods cannot be overridden. Instead, if a subclass implements the same static method as its parent, the method is hidden. Method hiding replaces the parent method in the calls defined in the child class.

// Covariant return types

// When overriding a method, we also have the option of changing the return type - provided that the overridden return type is a subtype of the original type. This is called covariant return types. We can also choose to change the access modifier of an overridden method - provided that the new modifier for the overriding method provides more, not less, access than the overridden method.

// Real World Application

// In object-oriented programming, Polymorphism provides the means to perform a single action in multiple different ways. Taking the real world example of animals, if we ask different animals to speak, they respond in their own way. Dog barks, duck quacks, cat says meow and so on. So the same action of speaking is performed in different ways by different animals.

// As another example, consider your Mobile phone. You can save your Contacts in it. Now suppose you want to save 2 numbers for one person. You can do it by saving the second number under the same name.

// Similarly, in an object-oriented language like Java, suppose you want to save two numbers for one person. You must have a function, which will take the two numbers and the person name as arguments to some function.

// Now it’s not necessary that every person will have 2 numbers. Many other contacts might have only a single number. In such a situation, instead of creating another method with different name to save one number for a contact, what you can do is that you can have the same name of the method i.e. createContact() but instead of taking 2 numbers as parameters, you can take only 1 number as parameter in it. This is an example of polymorphism. There is only one method (CreateContact) but it has two definitions.

// Implementation

// There are two types of polymorphism, Static and Dynamic.

//   • Static Polymorphism:
//      • Polymorphism that is resolved during compile time is known as static polymorphism.
//      • Method overloading can be considered as static polymorphism example.
//      • Method Overloading: This allows us to have more than one methods with same name in a class that differs in signature.
//    Example:

      class DisplayOverloading
      {
        public void disp(char c)
        {
          System.out.println(c);
        }
        public void disp(char c, int num)  
        {
          System.out.println(c + " "+num);
        }
      }
      public class ExampleOverloading
      {
        public static void main(String args[])
        {
          DisplayOverloading obj = new DisplayOverloading();
          obj.disp('a');
          obj.disp('a',10);
        }
      }

    // Dynamic Polymorphism
    //   • It is also known as Dynamic Method Dispatch.
    //   • Dynamic polymorphism is a process in which a call to an overridden method is resolved at runtime. It is otherwise known as runtime polymorphism
    //   Example:

    class Animal{
      public void animalSound(){
        System.out.println("Default Sound");
      }
    }
    public class Dog extends Animal{

      public void animalSound(){
          System.out.println("Woof");
      }

      public static void main(String args[]){
          Animal obj = new Dog();
          obj.animalSound();
      }
    }

// By definition, polymorphism means "taking on many forms". In the realm of programming, it describes how objects can behave differently in different contexts. The most common examples of polymorphism are method overloading and overriding.
//   • Method overloading is when there exist two or more methods in a class with the same method name, but different method signatures by changing the parameter list.
//   • Method overriding is when a method in a child class has the same method signature as a method in the parent class, but with a different implementation. Thus, child classes can change the default behavior given by a parent's method. Overriding methods makes class hierarchies more flexible and dynamic.