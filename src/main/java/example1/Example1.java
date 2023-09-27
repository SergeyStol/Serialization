package example1;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;

/**
 * @author Sergey Stol
 * 2023-09-27
 */
public class Example1 {
   public static void main(String[] args) throws IOException, ClassNotFoundException {
      FileOutputStream fileOutputStream = new FileOutputStream("example1.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(new Person("Name1", "Surname1"));
      objectOutputStream.flush();
      objectOutputStream.close();

      FileInputStream fileInputStream = new FileInputStream("example1.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Person person = (Person) objectInputStream.readObject();

      System.out.println(person);
   }
}

@ToString
@AllArgsConstructor
class Person implements Serializable {
   String name;
   String surname;
}
