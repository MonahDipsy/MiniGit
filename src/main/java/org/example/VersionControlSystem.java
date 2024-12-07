package org.example;

import java.io.IOException;
import java.util.Scanner;

public class VersionControlSystem {
    private static Repository repository;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path for the repository: ");
        String repoPath = scanner.nextLine();

        try {
            repository = new Repository(repoPath);

            while (true) {
                System.out.println("\nChoose an operation: init, add, commit, create-branch, switch-branch, merge, view-tags, add-tag, clone, exit");
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "init":
                        repository.init();
                        break;

                    case "add":
                        System.out.print("Enter file name to stage: ");
                        String fileName = scanner.nextLine();
                        repository.addFile(fileName);
                        break;

                    case "commit":
                        System.out.print("Enter commit message: ");
                        String message = scanner.nextLine();
                        repository.commit(message);
                        break;

                    case "create-branch":
                        System.out.print("Enter branch name: ");
                        String branchName = scanner.nextLine();
                        repository.createBranch(branchName);
                        break;

                    case "switch-branch":
                        System.out.print("Enter branch name: ");
                        String switchBranchName = scanner.nextLine();
                        try {
                            repository.switchBranch(switchBranchName);
                        } catch (IOException e) {
                            System.out.println("Error switching branch: " + e.getMessage());
                        }
                        break;

                    case "merge":
                        System.out.print("Enter branch to merge: ");
                        String branchToMerge = scanner.nextLine();
                        repository.merge(branchToMerge);
                        break;

                    case "view-tags":
                        repository.viewTags();
                        break;

                    case "add-tag":
                        System.out.print("Enter commit hash: ");
                        String commitHash = scanner.nextLine();
                        System.out.print("Enter tag name: ");
                        String tagName = scanner.nextLine();
                        repository.addTag(commitHash, tagName);
                        break;

                    case "clone":
                        System.out.print("Enter source repository path or URL: ");
                        String sourcePath = scanner.nextLine();
                        System.out.print("Enter destination path: ");
                        String destPath = scanner.nextLine();
                        try {
                            repository.cloneRepository(sourcePath, destPath);
                        } catch (IOException e) {
                            System.out.println("Error cloning repository: " + e.getMessage());
                        }
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid command. Try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error initializing repository: " + e.getMessage());
        }
    }
}
