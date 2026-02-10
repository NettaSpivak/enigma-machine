package ui;

import dto.MessageDto;
import engine.enigmaManager.EnigmaManager;
import ui.menu.actions.SetCodeManual;
import ui.menu.actions.ShowHistoryAndStatistics;
import ui.menu.actions.ShowMachineData;
import ui.menu.menu.Menu;
import ui.menu.menu.MenuChoices;
import ui.menu.menu.MenuImpl;
import ui.menu.menu.MenuState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnigmaManager enigmaManager = new EnigmaManager();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new MenuImpl();
        MenuState menuState = MenuState.XML_NOT_LOADED;

        System.out.println("Welcome to the Enigma Machine Simulator!");
        boolean exit = false;

        while (!exit){
            menu.displayMenu(menuState);
            String choice = scanner.nextLine().trim();
            System.out.print("\n");
            try {
              MenuChoices menuChoice = convertChoiceToEnum(choice, menuState);
              switch (menuChoice) {
                  case LOAD_XML: {
                      System.out.print("Enter XML file path: ");
                      String path = scanner.nextLine();
                      enigmaManager.loadXml(path);

                      menuState = MenuState.XML_LOADED;
                      System.out.println("XML loaded successfully.\n");
                      break;
                  }
                  case SHOW_MACHINE_DATA: {
                      System.out.println(ShowMachineData.showMachineData(enigmaManager.getMachineData()));
                      break;
                  }
                  case SET_CODE_MANUAL: {
                      System.out.print("Enter rotors: ");
                      String rotors = scanner.nextLine();
                      System.out.print("Enter rotors positions: ");
                      String rotorsPositions = scanner.nextLine();
                      System.out.print("Enter reflector: ");
                      String reflector = scanner.nextLine();
                      System.out.print("Enter plugboard pairs (empty line = no plugboard):");
                      String plugboard = scanner.nextLine();
                      enigmaManager.setCodeManual(SetCodeManual.setCodeMenual(rotors, rotorsPositions, reflector, plugboard));
                      System.out.println("Manual code set successfully.\n");
                      menuState = MenuState.CODE_SET;
                      break;
                  }
                  case SET_CODE_AUTO: {
                      enigmaManager.setCodeAutomatic();
                      System.out.println("Automatic code set successfully.\n");
                      menuState = MenuState.CODE_SET;
                      break;
                  }
                  case PROCESS_MESSAGE: {
                      System.out.print("Enter message to process: ");
                      String message = scanner.nextLine();
                      MessageDto processedMessageDto = enigmaManager.processMessage(new MessageDto(message));
                      System.out.println("Processed Message: " + processedMessageDto.getMessage() + "\n");
                      break;
                  }
                  case RESET_CODE: {
                      enigmaManager.resetCode();
                      System.out.println("Code reset successfully.\n");
                      break;
                  }
                  case SHOW_HISTORY_AND_STATISTICS: {
                      System.out.println(ShowHistoryAndStatistics.showHistoryAndStatistics(enigmaManager.historyAndStatistics()));
                      break;
                  }
                  case LOAD_SAVED_MACHINE: {
                      System.out.print("Enter full file path to load machine state from an external .bin file: ");
                      String path = scanner.nextLine();
                      enigmaManager.loadSnapshot(path);

                      if (enigmaManager.haveCode()) {
                          menuState = MenuState.CODE_SET;
                      } else {
                          menuState = MenuState.XML_LOADED;
                      }
                      System.out.println("Saved machine was loaded successfully.\n");
                      break;
                  }
                  case SAVE_MACHINE: {
                      System.out.print("Enter full file path to save machine state to an external file: ");
                      String path = scanner.nextLine();
                      enigmaManager.saveSnapshot(path);

                      System.out.println("Machine saved successfully.\n");
                      break;
                  }
                  case EXIT: {
                      exit = true;
                      System.out.println("Goodbye!");
                      break;
                  }
              }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }
    }

    private static MenuChoices convertChoiceToEnum(String choice, MenuState menuState) {
        if (choice.equals("1")) {
            return MenuChoices.LOAD_XML;
        }
        return switch (menuState) {

            case XML_NOT_LOADED -> switch (choice) {
                case "2" -> MenuChoices.LOAD_SAVED_MACHINE;
                case "3" -> MenuChoices.EXIT;
                default -> throw new IllegalArgumentException("Invalid choice. Please try again.\n");
            };

            case XML_LOADED -> switch (choice) {
                case "2" -> MenuChoices.SHOW_MACHINE_DATA;
                case "3" -> MenuChoices.SET_CODE_MANUAL;
                case "4" -> MenuChoices.SET_CODE_AUTO;
                case "5" -> MenuChoices.SHOW_HISTORY_AND_STATISTICS;
                case "6" -> MenuChoices.LOAD_SAVED_MACHINE;
                case "7" -> MenuChoices.SAVE_MACHINE;
                case "8" -> MenuChoices.EXIT;
                default -> throw new IllegalArgumentException("Invalid choice. Please try again.\n");
            };

            case CODE_SET -> switch (choice) {
                case "2" -> MenuChoices.SHOW_MACHINE_DATA;
                case "3" -> MenuChoices.SET_CODE_MANUAL;
                case "4" -> MenuChoices.SET_CODE_AUTO;
                case "5" -> MenuChoices.PROCESS_MESSAGE;
                case "6" -> MenuChoices.RESET_CODE;
                case "7" -> MenuChoices.SHOW_HISTORY_AND_STATISTICS;
                case "8" -> MenuChoices.LOAD_SAVED_MACHINE;
                case "9" -> MenuChoices.SAVE_MACHINE;
                case "10" -> MenuChoices.EXIT;
                default -> throw new IllegalArgumentException("Invalid choice. Please try again.\n");
            };
        };
    }
}