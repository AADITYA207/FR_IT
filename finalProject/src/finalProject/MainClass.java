package finalProject;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

public class MainClass  {
	private static String path = "C:\\Users\\Aaditya Gupta\\FR_Training\\javaprojs\\finalProject\\src\\finalProject\\";
    public static void main(String[] args) {
        displayWelcomeScreen();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listFiles();
                    break;
                case 2:
                    displayUserInterfaceDetails();
                    break;
                case 3:
                    System.out.println("Closing the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private static void displayWelcomeScreen() {
        System.out.println("Welcome to My File Manager");
        System.out.println("Developed by: Your Name");
        System.out.println();
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Sort the files in ascending order");
        System.out.println("2. User options");
        System.out.println("3. Close Application");
        
        System.out.print("Enter your choice: ");
    }

    private static void listFiles() {
        File directory = new File("./src/finalProject"); // Current directory
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("The directory is empty.");
            return;
        }
        Arrays.sort(files);
        System.out.println("Files in ascending order:");
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName()+" with size: "+ file.length() + ", last modified on: "+file.lastModified());
            }
        }
    }

    private static void displayUserInterfaceDetails() {
        System.out.println("User Interface Details:");
        System.out.println("1. Add a file to the existing directory list");
        System.out.println("2. Delete a user specified file from the existing directory list");
        System.out.println("3. Search a user specified file from the main directory");
        System.out.println("4. Navigate back to the main context");
        System.out.println("5. Read File");
        System.out.println("6. Count files and folders");
        System.out.println("7. Sort files with last modified");

        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addFile();
                break;
            case 2:
                deleteFile();
                break;
            case 3:
                searchFile();
                break;
            case 5:
            	System.out.println("Input file name");
                String file = scanner.nextLine();
                readFileContent(file);
                break;
            case 6:
            	
//                String file = scanner.nextLine();
            	countFilesFolders();
                break;
            case 7:
            	
//              String file = scanner.nextLine();
                File directory = new File("./src/finalProject"); // Current directory
                File[] files = directory.listFiles();
                sortByLastModified(files);
              break;
            case 4:
                System.out.println("Returning to the main context.");
                break;
            
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name to add: ");
        String fileName = scanner.nextLine();
        File file = new File("./src/",fileName);
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("File added successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void deleteFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("File not found.");
        }
    }
    private static void searchFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to search: ");
        String fileName = scanner.nextLine();
        
        File file = new File(fileName);
        if(file.exists()) {
        	System.out.println("File found: " + file.getAbsolutePath());
        }else {
        	System.out.println("Not found");
        }
    }
    
    private static void readFileContent(String fileName) {
        String file = path+ fileName;
        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

               String line;
               // Read each line from the file and print it
               while ((line = bufferedReader.readLine()) != null) {
                   System.out.println(line);
               }
           } catch (IOException e) {
               System.out.println("An error occurred while reading the file: " + e.getMessage());
//               e.printStackTrace();
           }
    }
    private static void countFilesFolders() {
        File directory = new File("./src/finalProject"); // Current directory
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("The directory is empty.");
            return;
        }
        System.out.println("Number of files: "+files.length);
        
//        File file = new File("/path/to/directory");
        String[] directories = directory.list(new FilenameFilter() {
          @Override
          public boolean accept(File current, String name) {
            return new File(current, name).isDirectory();
          }
        });
        System.out.println("Number of directories: "+directories.length );
    }
    private static void sortByLastModified(File[] files) {
        Arrays.sort(files, new ComparatorClass());

        // Print the sorted files
        for (File file : files) {
            System.out.println(file.getName() + " - Last Modified: " + file.lastModified());
        }
    }




//	@Override
//	public int compare(File o1, File o2) {
//		return (int)(o1.lastModified()-o2.lastModified());
//		// TODO Auto-generated method stub
//	}


}