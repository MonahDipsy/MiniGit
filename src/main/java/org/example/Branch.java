package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    private String name;
    private List<Commit> commits;
    private Map<String, String> files;

    public Branch(String name) {
        this.name = name;
        this.commits = new ArrayList<>();
        this.files = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void addCommit(Commit commit) {
        commits.add(commit);
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void addFile(String fileName, String content) {
        files.put(fileName, content);
    }

    public void setFileContent(String fileName, String content) {
        files.put(fileName, content);
    }

    public String getFileContent(String fileName) {
        return files.get(fileName);
    }
}
