package task2;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File animalTxt;
        Animal animalObject_01;
        Animal animalObject_02;
        ObjectOutputStream oos;
        ObjectInputStream ois;

        animalTxt = new File("animal.txt");
        animalObject_01 = new Animal("Cat", "World", "mammals", new DataAnimal("Moysha", 9, 15.0));

        oos = new ObjectOutputStream(new FileOutputStream(animalTxt));
        ois = new ObjectInputStream(new FileInputStream(animalTxt));

        try {
            System.out.println("Сериализуем объект Animal (animalObject_01)  в байт-код");
            oos.writeObject(animalObject_01); // Сериализуем объект Animal  в байт-код

            System.out.println("Десериализуем объект Animal с байт-кода в ( animalObject_02)");
            animalObject_02 = (Animal) ois.readObject(); // Десериализуем объект Animal с байт-кода

            System.out.println(animalObject_02);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Изменим объект Animal ( animalObject_02)");
        animalObject_02 = new Animal("Собака", "Улица", "Млекопитающие", new DataAnimal("Шарик", 15, 16.0));
        System.out.println(animalObject_02);


        try {
            System.out.println("Сериализуем объект Animal (animalObject_02)  в байт-код");
            oos.writeObject(animalObject_02); // Сериализуем объект Animal  в байт-код

            System.out.println("Десериализуем объект Animal с байт-кода в ( animalObject_01)");
            animalObject_01 = (Animal) ois.readObject(); // Десериализуем объект Animal с байт-кода

            System.out.println(animalObject_01);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
