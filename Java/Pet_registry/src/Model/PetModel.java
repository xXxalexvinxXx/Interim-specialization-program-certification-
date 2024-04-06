package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class PetModel {
    private final Set<String> commands;
    private String name;
    private String animalKind;
    private Date birthdate;

    public PetModel(String name, String animalKind, Date birthdate, Set<String> commands) {
        this.name = name;
        this.animalKind = animalKind;
        this.birthdate = birthdate;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalKind() {
        return animalKind;
    }

    public void setAnimalKind(String animalKind) {
        this.animalKind = animalKind;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Set<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    @Override
    public String toString() {
        return "name: " + name + "; " +
                "animal type: " + animalKind + "; " +
                "birth date: " + new SimpleDateFormat("yyyy-MM-dd").format(birthdate) + "; " +
                "commands: " + commands;
    }
}

