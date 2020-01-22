import java.util.Scanner;

class Iteration {
    static void newIteration() {
        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();
        String rule;
        String option = "default";
        String additionalOption = "default";
        if (userCommand.contains(" ")) {
            String[] commands = userCommand.split(" ");
            rule = commands[0];
            option = commands[1];
            if (commands.length == 3) {
                additionalOption = commands[2];
            }
        }

        else rule = userCommand;
        switch (rule) {
            case "exit": {
                System.out.println("shutting down..");
                System.exit(0);
            }
            case "new": {
                Command.newItem(input);
                break;
            }
            case "updateState": {
                Command.updateState(input, option);
                break;
            }
            case "delete": {
                Command.delete(input, option);
                break;
            }
            case "showAll": {
                Command.showAll(option);
                break;
            }
            case "clear": {
                Command.clear(option);
                break;
            }
            case "help": {
                Command.help();
                break;
            }
            case "show": {
               Command.show(input, option);
                break;
            }
            case "updateText": {
                Command.updateText(input, option, additionalOption);
                break;
            }
            default: {
                System.out.println("Wrong command. Enter 'help' for info.");
            }
        }
    }
}

