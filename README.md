# Tucil1-13524063 Queens "Queens Solver"

```markdown
A Java-based application to solve the classic **N-Queens problem**, featuring both brute-force and optimized algorithms.  
This project includes a **JavaFX GUI** for visualization and JUnit tests for validation.
```

---

## Project Structure

```markdown
│ pom.xml
│ README.md
│
├───src
│ ├───main
│ │ ├───java
│ │ │ └───com/queens
│ │ │ ├───app
│ │ │ │ Main.java # CLI entry point
│ │ │ │
│ │ │ ├───entities # Core domain classes
│ │ │ │ Area.java
│ │ │ │ Board.java
│ │ │ │ Coordinate.java
│ │ │ │
│ │ │ ├───solver # Algorithms
│ │ │ │ Bruteforce.java
│ │ │ │ Optimized.java
│ │ │ │
│ │ │ └───ui # JavaFX UI components
│ │ │
│ │ └───resources
│ │ │ board.txt
│ │ │ board1.txt
│ │ └───result/result.txt
│ │
│ └───test
│ ├───java/com/queens/entities
│ │ BoardTest.java
│ └───resources
│ board-test.txt
```

---

## Dependencies

- **JavaFX 21.0.2**
  - `javafx-controls`
  - `javafx-fxml`
- **JUnit Jupiter 5.9.3** (for testing)

---

## How to Run

### 1. Run CLI Version

```bash
mvn clean compile exec:java
```

## Features

- **Brute-force solver**: explores all possible queen placements.
- **Optimized solver**: optimized algorithm.
- **File I/O**: load board configurations from `resources/board.txt` and save results to `resources/result/result.txt`.

## Requirements

- Java 21+
- Maven 3.9+
- Compatible OS: Windows, macOS, Linux

---

## Notes

- Use the main project directory where pom.xml has been located for command-line execution in `./`.
- Results are written to `src/main/resources/result/result.txt`.

---
