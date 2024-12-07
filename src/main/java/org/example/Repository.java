package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Repository {
    private final Path repoDir;
    private String currentBranch;
    private final Map<String, Branch> branches = new HashMap<>();
    private final Map<String, String> tags = new HashMap<>();

    public Repository(String repoPath) {
        this.repoDir = Path.of(repoPath);
        this.currentBranch = "main";
        branches.put(currentBranch, new Branch(currentBranch)); // Initialize the main branch
    }

    public Path getRepoDir() {
        return repoDir;
    }

    public void init() throws IOException {
        Path dotRepo = repoDir.resolve(".repo");
        if (!Files.exists(dotRepo)) {
            Files.createDirectories(dotRepo);
            System.out.println("Repository initialized at: " + repoDir);
        } else {
            System.out.println("Repository already exists.");
        }
    }

    public void addFile(String filePath) throws IOException {
        Path file = repoDir.resolve(filePath);
        if (Files.exists(file)) {
            System.out.println("File staged: " + filePath);
        } else {
            throw new IOException("File does not exist: " + filePath);
        }
    }

    public void commit(String message) {
        List<Commit> currentCommits = getCurrentBranchCommits();
        Commit lastCommit = currentCommits.isEmpty() ? null : currentCommits.get(currentCommits.size() - 1);
        Commit newCommit = new Commit(message, lastCommit);
        currentCommits.add(newCommit);
        System.out.println("Commit created: " + newCommit);
    }

    public void viewHistory() {
        List<Commit> commits = getCurrentBranchCommits();
        if (commits.isEmpty()) {
            System.out.println("No commit history available.");
        } else {
            System.out.println("Commit history for branch: " + currentBranch);
            for (int i = commits.size() - 1; i >= 0; i--) {
                System.out.println(commits.get(i));
            }
        }
    }

    public void echoToFile(String filePath, String content) throws IOException {
        Path file = repoDir.resolve(filePath);
        Files.createDirectories(file.getParent()); // Ensure parent directories exist
        Files.writeString(file, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Content written to: " + filePath);
    }


    public void createBranch(String branchName) {
        if (branches.containsKey(branchName)) {
            System.out.println("Branch already exists: " + branchName);
        } else {
            Branch newBranch = new Branch(branchName);
            List<Commit> currentCommits = getCurrentBranchCommits();
            if (!currentCommits.isEmpty()) {
                newBranch.addCommit(currentCommits.get(currentCommits.size() - 1));
            }
            branches.put(branchName, newBranch);
            System.out.println("Branch created: " + branchName);
        }
    }


    public void switchBranch(String branchName) throws IOException {
        if (!branches.containsKey(branchName)) {
            throw new IOException("Branch does not exist: " + branchName);
        }
        currentBranch = branchName;
        System.out.println("Switched to branch: " + branchName);
    }

    public void merge(String branchName) {
        if (!branches.containsKey(branchName)) {
            System.out.println("Branch not found: " + branchName);
            return;
        }

        Branch currentBranchObj = branches.get(currentBranch);
        Branch branchToMerge = branches.get(branchName);

        if (currentBranchObj == null || branchToMerge == null) {
            System.out.println("One of the branches is invalid.");
            return;
        }

        System.out.println("Merging branch " + branchName + " into " + currentBranch);

        if (hasConflicts(currentBranchObj, branchToMerge)) {
            System.out.println("Conflicts detected. Merge cannot proceed.");
            return;
        }

        for (Map.Entry<String, String> entry : branchToMerge.getFiles().entrySet()) {
            String fileName = entry.getKey();
            String content = entry.getValue();
            currentBranchObj.setFileContent(fileName, content);
        }
        System.out.println("Merge completed successfully.");
    }



    private boolean hasConflicts(Branch currentBranchObj, Branch branchToMerge) {
        Map<String, String> currentBranchFiles = currentBranchObj.getFiles();
        Map<String, String> branchToMergeFiles = branchToMerge.getFiles();
        boolean hasConflicts = false;

        for (Map.Entry<String, String> entry : branchToMergeFiles.entrySet()) {
            String fileName = entry.getKey();
            String contentToMerge = entry.getValue();

            if (currentBranchFiles.containsKey(fileName)) {
                String currentContent = currentBranchFiles.get(fileName);

                if (!currentContent.equals(contentToMerge)) {
                    hasConflicts = true;
                    System.out.println("Conflict detected in file: " + fileName);
                }
            }
        }

        return hasConflicts;
    }

    public void addTag(String commitHash, String tagName) {
        if (findCommitByHash(commitHash) != null) {
            tags.put(tagName, commitHash);
            System.out.println("Tag '" + tagName + "' added to commit: " + commitHash);
        } else {
            System.out.println("Commit not found: " + commitHash);
        }
    }

    public void viewTags() {
        if (tags.isEmpty()) {
            System.out.println("No tags available.");
        } else {
            System.out.println("Tags:");
            for (Map.Entry<String, String> entry : tags.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    // Clone repository
    public void cloneRepository(String sourceUrl, String destinationPath) throws IOException {
        Path destinationDir = Path.of(destinationPath);
        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }
        System.out.println("Simulated cloning from: " + sourceUrl + " to " + destinationPath);
        // In a real scenario, add logic to download and extract repository files
    }

    private List<Commit> getCurrentBranchCommits() {
        Branch currentBranchObj = branches.get(currentBranch);
        if (currentBranchObj != null) {
            return currentBranchObj.getCommits();
        }
        return new ArrayList<>();
    }

    private Commit findCommitByHash(String hash) {
        for (Branch branch : branches.values()) {
            for (Commit commit : branch.getCommits()) {
                if (commit.getHash().equals(hash)) {
                    return commit;
                }
            }
        }
        return null;
    }

}
