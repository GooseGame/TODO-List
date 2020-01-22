import java.util.Scanner;

class Command {

    static void newItem(Scanner input) {
        System.out.println("Enter id of item");
        String td_num = input.nextLine();
        System.out.println("Add a description");
        String description = input.nextLine();
        if (Todo.makeNewItem(description, td_num)) {
            System.out.println("A new item has been added");
        }
        else System.out.println("An item with this id already exist");
    }

    private static void state(String td_num, Scanner input) {
        System.out.println("Enter state (unfinished, done or canceled)");
        String state = input.nextLine();
        switch (state) {
            case "unfinished": {
                Todo.setState(td_num, 0);
                break;
            }
            case "done": {
                Todo.setState(td_num, 1);
                break;
            }
            case "canceled": {
                Todo.setState(td_num, 2);
                break;
            }
            default: {
                System.out.println("can't resolve entered state");
                break;
            }
        }
    }

    static void updateState(Scanner input, String option) {
        if (option.matches("[0-9]+")) {
            state(option, input);
        }
        else if (option.equals("default")) {
            System.out.println("Enter id of item");
            String td_num = input.nextLine();
            state(td_num, input);
        }
        else System.out.println("Wrong option");
    }

    static void delete(Scanner input, String option) {
        if (option.matches("[0-9]+")) {
            if (Todo.delete(option)) {
                System.out.println("An item has been deleted");
            }
            else System.out.println("Undefined error");
        }
        else if (option.equals("default")) {
            System.out.println("Enter id of item");
            String td_num = input.nextLine();
            if (Todo.delete(td_num)) {
                System.out.println("An item has been deleted");
            }
            else System.out.println("Undefined error");
        }
        else System.out.println("Wrong option");
    }

    static void showAll(String option) {
        Todo.showItems(option);
    }

    static void clear(String option) {
        if (!Todo.clear(option)) {
            System.out.println("Can't clear items.");
        }
    }

    static void show(Scanner input, String option) {
        if (option.matches("[0-9]+")) {
            Todo.showItem(option);
        }
        else if (option.equals("default")) {
            System.out.println("Enter id");
            String td_num = input.nextLine();
            if (td_num.matches("[0-9]+")) {
                Todo.showItem(td_num);
            }
            else {
                System.out.println("Wrong id");
            }
        }
        else System.out.println("Wrong option");
    }

    static void updateText(Scanner input, String option, String id) {
        if (id.matches("[0-9]+")) {
            System.out.println("Enter text");
            String description = input.nextLine();
            Todo.updateDescription(option, description, id);
        }
        else if (id.equals("default")) {
            System.out.println("Enter id");
            String td_num = input.nextLine();
            System.out.println("Enter text");
            String description = input.nextLine();
            Todo.updateDescription(option, description, td_num);
        }
        else System.out.println("wrong id");
    }

    static void help() {
        System.out.println("Commands:");
        System.out.println("new - make new TODO item (You must put an unique id to item)");
        System.out.println("updateState - update the current item state. White id of item and then new state (unfinished, done or canceled)");
        System.out.println("delete - delete item (You must write id of item). Optional: id in command ine. Example: show 1");
        System.out.println("clear - delete all items For ordering enter unfinished, done or canceled after command. Example: 'clear done'");
        System.out.println("showAll - show all items. For ordering enter unfinished, done or canceled after command. Example: 'showAll done'");
        System.out.println("show - show current item (You must write id of item). Optional: id in command ine. Example: show 1");
        System.out.println("updateText - update a text of item (You must write id of item). Optional: rewrite, id in command line. Example: updateText rewrite 1");
        System.out.println("exit - shut down program");
    }
}
