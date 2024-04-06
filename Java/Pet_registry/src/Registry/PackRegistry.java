package Registry;

import Exceptions.AnimalNotFound;
import Model.PackModel;

import java.util.ArrayList;
import java.util.List;

public class PackRegistry {
    private final List<PackModel> packAnimals;

    public PackRegistry() {
        this.packAnimals = new ArrayList<>();
    }

    public void create(PackModel packAnimal) {
        packAnimals.add(packAnimal);
    }

    public List<PackModel> list() {
        return packAnimals;
    }

    public void addCommand(int petIndex, String command) throws AnimalNotFound {
        if (petIndex < 0 || petIndex >= packAnimals.size()) {
            throw new AnimalNotFound();
        }
        packAnimals.get(petIndex).addCommand(command);
    }
}
