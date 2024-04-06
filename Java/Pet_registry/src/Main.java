import Exceptions.AnimalNotFound;
import Exceptions.InvalidType;
import Exceptions.InvalidBirthdateFormat;
import Exceptions.InvalidIDF;
import Model.PackModel;
import Model.PetModel;
import Registry.PackRegistry;
import Registry.PetRegistry;
import Service.AnimalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int FINISH_COMMAND = 6;

    public static void main(String[] args) {
        PetRegistry petRegistry = new PetRegistry();
        PackRegistry packRegistry = new PackRegistry();
        AnimalService animalSvc = new AnimalService(petRegistry, packRegistry);

        Scanner sc = new Scanner(System.in);

        int command = 0;
        while (command != FINISH_COMMAND) {
            try {
                command = askCommand(sc);

                switch (command) {
                    case 1 -> {
                        for (PetModel pet : animalSvc.listPets()) {
                            System.out.println(pet);
                        }
                    }
                    case 2 -> {
                        for (PackModel packAnimal : animalSvc.listPackAnimals()) {
                            System.out.println(packAnimal);
                        }
                    }
                    case 3 -> {
                        try {
                            addAnimal(sc, animalSvc);
                        } catch (InvalidBirthdateFormat e) {
                            System.out.println("Invalid birth date format");
                        } catch (InvalidType e) {
                            System.out.println("Invalid animal type");
                        } catch (InvalidPropertiesFormatException e) {
                            System.out.println("Invalid property format: " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        try {
                            addCommand(sc, animalSvc);
                        } catch (InvalidIDF e) {
                            System.out.println("Enter a valid animal id");
                        } catch (AnimalNotFound e) {
                            System.out.println("Animal not found");
                        } catch (InvalidPropertiesFormatException e) {
                            System.out.println("Invalid property format: " + e.getMessage());
                        }
                    }
                    case 5 -> System.out.println("animals number: " + animalSvc.animalsCount());
                    case FINISH_COMMAND -> {
                    }
                    default -> System.out.println("Command not found");
                }
            } catch (InvalidIDF ignore) {
                System.out.println("Enter a valid command id");
            }

            System.out.println("----------------------------------------");
        }
    }

    private static int askCommand(Scanner sc) throws InvalidIDF {
        System.out.println("""
                Enter the number of the command:
                1. List pets
                2. List pack animals
                3. Add a new animal
                4. Teach new commands
                5. Show counter of animals
                6. Exit""");

        System.out.print("Enter the command: ");
        try {
            int command = sc.nextInt();
            sc.nextLine();
            return command;
        } catch (Exception e) {
            sc.nextLine();
            throw new InvalidIDF();
        }
    }

    private static void addAnimal(Scanner sc, AnimalService svc) throws InvalidBirthdateFormat, InvalidType, InvalidPropertiesFormatException {
        System.out.print("Enter the animal name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            throw new InvalidPropertiesFormatException("animal name can not be empty");
        }

        System.out.print("Enter the animal type (dog, cat, hamster, donkey, horse or camel): ");
        String type = sc.nextLine();
        if (type.isEmpty()) {
            throw new InvalidPropertiesFormatException("animal type can not be empty");
        }

        System.out.print("Enter the animal birth date (yyyy-mm-dd): ");
        String birthDateStr = sc.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
        } catch (ParseException e) {
            throw new InvalidBirthdateFormat();
        }

        svc.createAnimal(name, type, date);
    }

    private static void addCommand(Scanner sc, AnimalService svc) throws InvalidIDF, AnimalNotFound, InvalidPropertiesFormatException {
        List<PetModel> pets = svc.listPets();
        List<PackModel> packAnimals = svc.listPackAnimals();

        if (pets.isEmpty() && packAnimals.isEmpty()) {
            throw new AnimalNotFound();
        }

        int idx = 1;
        for (PetModel pet : pets) {
            System.out.printf("%d: %s%n", idx++, pet);
        }

        for (PackModel packAnimal : packAnimals) {
            System.out.printf("%d: %s%n", idx++, packAnimal);
        }

        System.out.print("Enter the animal's id: ");
        int id;
        try {
            id = sc.nextInt() - 1;
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            throw new InvalidIDF();
        }

        if (id < 0 || id >= pets.size() + packAnimals.size()) {
            throw new AnimalNotFound();
        }

        System.out.print("Enter command: ");
        String command = sc.nextLine().trim();
        if (command.isEmpty()) {
            throw new InvalidPropertiesFormatException("command can not be empty");
        }

        if (id < pets.size()) {
            svc.addCommandToPet(id, command);
        } else {
            svc.addCommandToPackAnimal(id - pets.size(), command);
        }
    }
}