// In Java, several classes automatically override the equals() method to ensure that the contents of objects are compared rather than their memory addresses (which is what == does). Here are some common classes that override equals() by default:

// 1. String
// 	•	The String class overrides equals() to compare the actual sequence of characters in the string, rather than the memory references.
// 	•	Example:

String str1 = new String("hello");
String str2 = new String("hello");
System.out.println(str1.equals(str2)); // true



// 2. Integer, Double, Float, Character, Boolean, and other wrapper classes
// 	•	These classes, which are wrappers for primitive types, override equals() to compare the actual value wrapped inside the object.
// 	•	Example:

Integer num1 = 10;
Integer num2 = 10;
System.out.println(num1.equals(num2)); // true



// 3. List, Set, Map, and other collections classes (e.g., ArrayList, HashSet, HashMap)
// 	•	These classes override equals() to compare the contents of the collection, not the references.
// 	•	Example:

List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
System.out.println(list1.equals(list2)); // true



// 4. Date (from java.util.Date)
// 	•	The Date class overrides equals() to compare the actual time represented by two Date objects, not their memory locations.
// 	•	Example:

Date date1 = new Date(1000000000L);
Date date2 = new Date(1000000000L);
System.out.println(date1.equals(date2)); // true



// 5. UUID
// 	•	The UUID class, which represents a universally unique identifier, overrides equals() to compare the actual value of the UUID.
// 	•	Example:

UUID uuid1 = UUID.randomUUID();
UUID uuid2 = UUID.fromString(uuid1.toString());
System.out.println(uuid1.equals(uuid2)); // true



// 6. BigInteger and BigDecimal
// 	•	These classes override equals() to compare their numerical value.
// 	•	Example:

BigInteger big1 = new BigInteger("12345");
BigInteger big2 = new BigInteger("12345");
System.out.println(big1.equals(big2)); // true



// 7. File (from java.io.File)
// 	•	The File class overrides equals() to compare the file paths, rather than the references.
// 	•	Example:

File file1 = new File("/path/to/file.txt");
File file2 = new File("/path/to/file.txt");
System.out.println(file1.equals(file2)); // true



// 8. LocalDate, LocalDateTime, Instant, etc. (from java.time)
// 	•	Classes in the java.time package (introduced in Java 8) override equals() to compare their time values.
// 	•	Example:

LocalDate date1 = LocalDate.of(2022, 1, 1);
LocalDate date2 = LocalDate.of(2022, 1, 1);
System.out.println(date1.equals(date2)); // true



// 9. Path (from java.nio.file.Path)
// 	•	The Path class overrides equals() to compare file paths.
// 	•	Example:

Path path1 = Paths.get("/path/to/file.txt");
Path path2 = Paths.get("/path/to/file.txt");
System.out.println(path1.equals(path2)); // true



// 10. Enum
// 	•	All enum types (such as Day, Month, etc.) in Java automatically override equals() to compare the instance (since there is only one instance per enum constant).
	// •	Example:

Day day1 = Day.MONDAY;
Day day2 = Day.MONDAY;
System.out.println(day1.equals(day2)); // true



// Why Do These Classes Override equals()?

// The main reason these classes override equals() is to ensure logical equality rather than reference equality:
// 	•	Reference equality means comparing if two references point to the exact same memory location (which is the behavior of == for objects).
// 	•	Logical equality means comparing if the values or the contents of the objects are the same, which is what equals() is typically used for.

// What Happens If a Class Doesn’t Override equals()?

// If a class doesn’t override the equals() method, the default implementation from the Object class is used, which compares memory references:
// 	•	The equals() method in Object simply calls == to check if the two object references point to the same memory location.

// For example:

MyClass obj1 = new MyClass();
MyClass obj2 = new MyClass();
System.out.println(obj1.equals(obj2)); // false, because it compares references by default

// Thus, overriding equals() is essential if you want to compare the actual contents of objects of that class. If a class doesn’t override equals(), it will default to comparing references, which is not usually what you want for value-based objects.

// Conclusion

// In Java, classes that contain meaningful data (like String, Integer, List, etc.) usually override the equals() method to compare their contents. For custom classes that hold meaningful data, it’s good practice to override equals() (and usually hashCode() as well) to provide logical equality checking.