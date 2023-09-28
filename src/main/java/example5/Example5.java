package example5;

import java.io.*;

/**
 * Quiz yourself: Deserializing objects with readObject
 * <a href="https://blogs.oracle.com/javamagazine/post/java-quiz-serialize-deserialize-readobject">Quiz yourself: Deserializing objects with readObject</a>
 */
public class Example5 {
   public static void main(String[] args) throws IOException, ClassNotFoundException {
      String filename = "example5.txt";
      FileOutputStream fileOutputStream = new FileOutputStream(filename);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      Person person = new Person("Name1");
      objectOutputStream.writeObject(person);
      objectOutputStream.flush();
      objectOutputStream.close();

      try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
         variantA(inputStream);
         variantB(inputStream);
         variantC(inputStream);
         variantD(inputStream);
      } catch (Exception e) {
         throw e;
      }
   }

   private static void variantA(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
      var person = (Person) null;
      if (inputStream.readObject() instanceof Person p) {
         person = p;
      }
   }

   private static void variantB(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
      var person = inputStream.readObject();
   }

   private static void variantC(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
      Person person = null;
      if (inputStream.readObject() instanceof Person) {
         person = inputStream.readObject();
      }
   }

   private static void variantD(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
      var person = null;
      Object o = inputStream.readObject();
      if (o instanceof Person) {
         person = (Person) o;
      }
   }
}

record Person(String name) implements Serializable {
}
