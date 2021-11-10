import java.util.*;

public class Queries {
  public static void UserMenu() {
    int command;
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to our School's database.");
    do {
      System.out.println("Press 0 to exit this menu.\nPress 1 to select what information you want to see.");
      System.out.println("Press 2 to insert information into the database.\nPress 3 to update information in the database.");
      System.out.print("Press 4 to delete information into the database.");
      command = scan.nextInt();
      //System.out.print("Press 5 for : ");
      switch (command) {
      case 0:
        return;
      case 1:
        SelectQuery();
        break;
      case 2:
        InsertQuery();
        break;
      case 3:
        UpdateQuery();
        break;
      case 4:
        DeleteQuery();
        break;
      default:
        System.out.println("Invalid Input, please try again.\n\n");
        break;
      }
    } while (command != 0);
    scan.close();
  }

  public static void SelectQuery() {
    // Support "FROM", Support "WHERE", Support all the JOINS.
  }

  public static void InsertQuery() {

  }

  public static void UpdateQuery() {

  }

  public static void DeleteQuery() {

  }
}
