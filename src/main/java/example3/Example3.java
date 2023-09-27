package example3;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;

/**
 * Serialization and Deserialization with Inheritance
 */
public class Example3 {
   public static void main(String[] args) throws Exception{
      FileOutputStream fileOutputStream = new FileOutputStream("example3");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      Person person = new Person("Name1", "Surname1", new Address("Country1", "Street1"));
      objectOutputStream.writeObject(person);

      FileInputStream fileInputStream = new FileInputStream("example3");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Object personDeserialized = objectInputStream.readObject();

      System.out.println(personDeserialized);
   }
}

@AllArgsConstructor
@ToString
class Person implements Serializable {
   String name;
   String surname;

   Address address;
}

@AllArgsConstructor
@ToString
class Address implements Serializable {
   String country;
   String street;
}