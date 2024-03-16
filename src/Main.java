import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол): ");
            String input = scanner.nextLine();

            String[] userData = input.split("\\s+");
            if (userData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных!");
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String birthDate = userData[3];
            String phoneNumberString = userData[4];
            char gender = userData[5].charAt(0);

            validatePhoneNumber(phoneNumberString);

            long phoneNumber = Long.parseLong(phoneNumberString);

            validateData(phoneNumber, gender);

            String fileName = lastName + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(lastName + " " + firstName + " " + middleName
                    + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
            fileWriter.close();

            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры!");
        }
    }

    private static void validateData(long phoneNumber, char gender) {
        if (phoneNumber < 0) {
            throw new IllegalArgumentException("Номер телефона должен быть положительным числом!");
        }

        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Неверный формат пола!");
        }
    }
}