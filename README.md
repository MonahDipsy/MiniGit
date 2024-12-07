# MiniGit Project
## Overview
MiniGit is a simplified distributed source control system inspired by Git. This project supports essential version control operations including repository initialization, commit management, branching, merging, and more. The project is designed to mimic the functionalities of Git in a simplified format, supporting file staging, committing, viewing commit history, and basic branch operations.

## Features
## Core Features
Repository Initialization: Create and initialize a new repository or open an existing one.
Staging and Committing: Stage files and commit changes with messages.
Branch Management: Create new branches, switch between branches, and view the current branch.
Merging: Merge branches while detecting potential conflicts (with no resolution functionality).
Viewing Commit History: View the history of commits for the current branch.
Tag Management: View existing tags and create new tags for commits.
Operations Supported
init: Initialize a new repository.
commit: Commit staged changes with a provided message.
add: Stage a specific file for the next commit.
create-branch: Create a new branch.
switch-branch: Switch to a different branch.
merge: Merge another branch into the current branch.
view-tags: Display all existing tags in the repository.
add-tag: Add a new tag to a specific commit.
clone: Clone an existing repository (local disk only).
view-history: View the commit history of the current branch.
exit: Exit the program.
Installation and Setup
Prerequisites
Java Development Kit (JDK): Ensure you have JDK 23 or a compatible version installed.
IDE: Recommended IDE is IntelliJ IDEA or any other Java-compatible IDE for development.
Steps to Run the Project
Clone or Download the Project: Obtain the project files to your local machine.
Compile the Project:
Navigate to the project directory and compile the code using Maven or your chosen build tool.
Use mvn clean compile if using Maven.
Run the Main Class:
Execute the program using the Main class from your IDE or command line.
Example command:
bash
Copy code
java -cp target/classes org.example.Main
Usage
Running the Program
Initialize a Repository: Start by running init to create a new repository or open an existing one.
Perform Operations:
Use commands such as add, commit, create-branch, switch-branch, merge, and others to interact with the repository.
View Commit History: Use view-history to display the sequence of commits in the current branch.
Example Workflow
java
Copy code
Choose an operation: init
Enter the path for the repository: init
Repository initialized at: C:\path\to\repo

Choose an operation: create-branch
Enter branch name: main
Branch created: main

Choose an operation: switch-branch
Enter branch name: main
Switched to branch: main

Choose an operation: echo
Enter file path to write to (relative to repo): cars.txt
Enter content to write to the file: home
Content written to: C:\path\to\repo\cars.txt

Choose an operation: add
Enter file path: cars.txt
File staged: C:\path\to\repo\cars.txt

Choose an operation: commit
Enter commit message: add home
Commit created: Commit{hash='xxxxxx', message='add home'}

Choose an operation: view-history
Commit history for branch: main
Commit{hash='xxxxxx', message='add home'}
Branching and Merging
Branch Creation: Use create-branch to make a new branch.
Switching Branches: Use switch-branch to change the current branch.
Merging: The merge command combines changes from the specified branch into the current branch. It will detect potential conflicts but does not handle conflict resolution.
Known Limitations
Conflict Resolution: The program detects conflicts during a merge but cannot resolve them. The current implementation only reports a conflict without automatic resolution.
File Path Handling: Ensure that file paths are correctly formatted to avoid errors. Paths with unexpected characters (e.g., C:\path\to\file.txt) may lead to InvalidPathException.
No GUI: The project operates solely as a terminal-based application; no graphical interface is provided.
Potential Enhancements
Conflict Resolution: Implement a logic to handle file conflicts during merges.
GUI Support: Develop a graphical user interface for a more intuitive user experience.
Tag Viewing: Enhance the view-tags feature to display tags with associated commit messages.
Troubleshooting
File Path Errors: If you encounter InvalidPathException, ensure that the paths entered are properly formatted and relative to the repository directory.
Unknown Operations: Verify that the input command matches the available options and is correctly spelled.
License
This project is licensed under the MIT License. See the LICENSE file for details.

### Acknowledgements
Inspired by Git and version control systems.
Built using Java and supported with standard libraries
