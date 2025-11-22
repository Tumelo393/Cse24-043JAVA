# Banking System (OOAD Java Assignment)

Java 17, JavaFX UI, SQLite backend.
This repository contains:
- UML diagrams in `uml/` (PlantUML `.puml` files for Visual Paradigm import)
- A Maven Java project under `src/` with packages:
  - `app`, `model`, `dao`, `controller`, `view`
- SQLite DB file `banking.db` will be created automatically in the working directory.
- Database initializer inserts a sample dataset of 10 records on first run.

## Run in Codespaces or locally

Requirements:
- Java 17
- Maven

To run:
```bash
mvn clean javafx:run
```

The app will create `banking.db` and populate sample data on first start.

UML PlantUML files are in the `uml/` folder (open with Visual Paradigm PlantUML plugin or any PlantUML tool).
