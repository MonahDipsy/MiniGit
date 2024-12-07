package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the path for the repository: ");
            String repoPath = scanner.nextLine();
            Path repoDir = Paths.get(repoPath);
            if (!Files.exists(repoDir)) {
                System.out.println("Creating the repository directory...");
                Files.createDirectories(repoDir);
            }

            Repository repo = new Repository(repoPath);
            repo.init();
            System.out.println("Repository initialized.");
            StagingArea staging = new StagingArea(repo);
            String operation;
            do {
                System.out.print("Choose an operation: init, add, commit, echo, create-branch, switch-branch, merge, view-tags, add-tag, clone, view-history, exit: ");
                operation = scanner.nextLine();

                switch (operation) {
                    case "init":
                        repo.init();
                        break;
                    case "commit":
                        System.out.print("Enter commit message: ");
                        String message = scanner.nextLine();
                        repo.commit(message);
                        break;
                    case "add":
                        System.out.print("Enter file path: ");
                        String filePath = scanner.nextLine();
                        repo.addFile(filePath);
                        break;
                    case "create-branch":
                        System.out.print("Enter branch name: ");
                        String branchName = scanner.nextLine();
                        repo.createBranch(branchName);
                        break;
                    case "switch-branch":
                        System.out.print("Enter branch name: ");
                        branchName = scanner.nextLine();
                        repo.switchBranch(branchName);
                        break;
                    case "merge":
                        System.out.print("Enter branch to merge: ");
                        branchName = scanner.nextLine();
                        repo.merge(branchName);
                        break;
                    case "view-tags":
                        repo.viewTags();
                        break;
                    case "add-tag":
                        System.out.print("Enter commit hash: ");
                        String commitHash = scanner.nextLine();
                        System.out.print("Enter tag name: ");
                        String tagName = scanner.nextLine();
                        repo.addTag(commitHash, tagName);
                        break;
                    case "echo":
                        System.out.print("Enter file path to write to (relative to repo): ");
                        String echoFilePath = scanner.nextLine().trim();
                        System.out.print("Enter content to write to the file: ");
                        String content = scanner.nextLine();
                        try {
                            repo.echoToFile(echoFilePath, content);
                        } catch (IOException e) {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }
                        break;
                    case "clone":
                        System.out.print("Enter source URL: ");
                        String url = scanner.nextLine();
                        System.out.print("Enter destination path: ");
                        String destPath = scanner.nextLine();
                        repo.cloneRepository(url, destPath);
                        break;
                    case "view-history":
                        repo.viewHistory();
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Unknown operation: " + operation);
                        break;
                }
            } while (!operation.equals("exit"));
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
