package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StagingArea {
    private final Repository repo;

    public StagingArea(Repository repo) {
        this.repo = repo;
    }

    public void addFile(String fileName) throws IOException {
        Path sourcePath = Paths.get(System.getProperty("user.dir")).resolve(fileName);
        if (!Files.exists(sourcePath)) {
            throw new IOException("File not found: " + fileName);
        }

        Path repoDir = repo.getRepoDir().resolve(".repo");
        if (!Files.exists(repoDir)) {
            Files.createDirectories(repoDir);
        }

        Path destPath = repoDir.resolve(sourcePath.getFileName());
        Files.copy(sourcePath, destPath);
        System.out.println("File staged: " + fileName);
    }
}
