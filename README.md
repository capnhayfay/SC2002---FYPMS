# FYPMS

NTU AY2022/23 Semester 2 SC2002 Group Project - Final Year Project Management System (FYPMS).

Final Year Project Management System (FYPMS) is a Java console application that utilizes object-oriented concepts to efficiently manage final year project settings. The program is designed with a focus on reusability, extensibility, and maintainability, allowing for easy upgrades and future development. It provides flexibility to accommodate different user types and their requirements.

## Links

- [GitHub Repository](https://github.com/capnhayfay/SC2002---FYPMS)
- [Report](https://github.com/capnhayfay/SC2002---FYPMS/report/A25-grp2-report.pdf)
- [Presentation Video](https://youtu.be/8FikWzfHlLA)

## Team Members

We are group 2 from tutorial group A25. There are 5 members in our group:

| Name              | Github Account                                  |
|-------------------|-------------------------------------------------|
| Bryan Lim Kai Wen | [zonpig](https://github.com/zonpig)             |
| Guo Zhiqi         | [Zq199](https://github.com/Zq199)               |
| Lam Wei Lin, Zoey | [Zomankey](https://github.com/Zomankey)         |
| Ng Zu Wei, Jovin  | [Jovin2525](https://github.com/Jovin2525)       | 
| Singh Dhruv       | [capnhayfay](https://github.com/capnhayfay)     | 

## Features

- [x] Student
  - [x] View my profile
  - [x] Change my password
  - [x] View project list
  - [x] View my project
  - [x] View my supervisor
  - [x] Register for a project
  - [x] Deregister for a project
  - [x] Change title for a project
  - [x] View history and status of my requests
- [x] Supervisor
  - [x] View my profile
  - [x] Change my password
  - [x] Create a project
  - [x] View all my projects
  - [x] Modify title of projects
  - [x] View all pending student requests
  - [x] Approve/Reject student requests
  - [x] Submit request for transferring
  - [x] View all incoming/outgoing requests' history and status
- [x] Supervisor
  - [x] View My Profile
  - [x] Change My Password
  - [x] View All Projects
  - [x] View Pending Requests
  - [x] View All Requests' History and Status
  - [x] Accept or Reject Requests
  - [x] Generate Project Details

## Build

Download the project from GitHub.

```bash
git clone https://github.com/capnhayfay/SC2002---FYPMS
```

Use JetBrains IntelliJ IDEA to build the project.

The project is built with Java 19.

## Run

The built jar file is located at `out/artifacts/FYPMS_jar/FYPMS.jar`.

There is a shell script `run.sh` and a Windows command script `run.cmd` to run the program.

Or you could run the jar file with the following command:

```bash
java -jar ./out/artifacts/FYPMS_jar/FYPMS.jar
```

Also, you can also use JetBrains IntelliJ IDEA to run the project.

The main class is `src/main/Main.java`.

## Test

The java class `src/test/HardReload.java` is used to hard reload the database.

## UML Class Diagram

[![UML Class Diagram](UMLClassDiagram/main.svg)](UMLClassDiagram/main.svg)

