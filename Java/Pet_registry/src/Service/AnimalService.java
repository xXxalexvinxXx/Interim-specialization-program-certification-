package Service;

import Exceptions.AnimalNotFound;
import Exceptions.InvalidType;
import Model.PackModel;
import Model.PetModel;
import Registry.PackRegistry;
import Registry.PetRegistry;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class AnimalService {
    private final PetRegistry petRegistry;
    private final PackRegistry packRegistry;
    private final Counter counter;

    public AnimalService(PetRegistry petRegistry, PackRegistry packRegistry) {
        this.petRegistry = petRegistry;
        this.packRegistry = packRegistry;
        this.counter = new Counter();
    }

    public List<PetModel> listPets() {
        return petRegistry.list();
    }

    public List<PackModel> listPackAnimals() {
        return packRegistry.list();
    }

    public Integer animalsCount() {
        return counter.getCount();
    }

    public void createAnimal(String name, String animalType, Date birthDate) throws InvalidType {
        switch (animalType) {
            case "dog", "cat", "hamster" -> {
                PetModel pet = new PetModel(name, animalType, birthDate, new HashSet<>());
                petRegistry.create(pet);
                counter.increment();
            }
            case "donkey", "horse", "camel" -> {
                PackModel packAnimal = new PackModel(name, animalType, birthDate, new HashSet<>());
                packRegistry.create(packAnimal);
                counter.increment();
            }
            default -> throw new InvalidType();
        }
    }

    public void addCommandToPet(int petIndex, String command) throws AnimalNotFound {
        petRegistry.addCommand(petIndex, command);
    }

    public void addCommandToPackAnimal(int petIndex, String command) throws AnimalNotFound {
        packRegistry.addCommand(petIndex, command);
    }
}
