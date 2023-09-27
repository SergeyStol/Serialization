package example2;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Stol
 * 2023-09-27
 */
public class Example2 {
   public static void main(String[] args) throws Exception {
      FileOutputStream fileOutputStream = new FileOutputStream("example2.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      ArrayList<Person> persons = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
         persons.add(Person.of("Name" + i, "Surname" + i));
      }
      objectOutputStream.writeObject(persons);
      objectOutputStream.flush();
      objectOutputStream.close();

      FileInputStream fileInputStream = new FileInputStream("example2.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      List<Person> persons2 = (List<Person>) objectInputStream.readObject();

      persons2.forEach(System.out::println);
   }
}

@AllArgsConstructor(staticName = "of")
@ToString
class Person implements Serializable {
   String name;
   String surname;
}
