# VRMS - Vehicle Rental Management System

UI-only first submission for the Vehicle Rental Management System micro project.

## Scope of this submission

This repository contains the complete planned Swing interface without the storage/business implementation used in the full project.

Included pages:

- Customer Login
- Customer Registration
- Admin Login
- Vehicle Catalog
- My Vehicles
- List Vehicle
- Rent Vehicle
- My Rentals
- Admin Vehicle Catalog
- Pending Vehicle Approvals

The catalog and management pages use static sample data only so the complete UI can be presented without a database, local-file storage, authentication backend, rental processing, or approval logic.

Buttons are included for presentation. Backend actions are intentionally not required for this submission.

## Compile

From the repository root:

```bash
javac -d out src/vrms/*.java
```

## Run any page directly

```bash
java -cp out vrms.LoginPage
java -cp out vrms.RegisterPage
java -cp out vrms.AdminLoginPage
java -cp out vrms.CatalogPage
java -cp out vrms.MyVehiclesPage
java -cp out vrms.ListVehiclePage
java -cp out vrms.RentVehiclePage
java -cp out vrms.MyRentalsPage
java -cp out vrms.AdminDashboardPage
java -cp out vrms.AdminApprovalPage
```

`vrms.Main` also opens the customer login page:

```bash
java -cp out vrms.Main
```

Java 17 or newer is recommended.
