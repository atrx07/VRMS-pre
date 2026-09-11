# VRMS - Vehicle Rental Management System

UI-only first submission for the Vehicle Rental Management System micro project.

## Scope of this submission

This repository contains only the first-stage Swing user interface:

- Customer login page
- Customer registration page
- Separate admin login page
- Navigation between the three screens
- Basic empty-field validation and password show/hide UI

No database, local-file storage, rental processing, vehicle catalog logic, or authentication backend is included in this submission.

## Run

From the repository root:

```bash
javac -d out src/vrms/*.java
java -cp out vrms.Main
```

Java 17 or newer is recommended.
