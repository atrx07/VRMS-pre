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

## Run pages for presentation

Customer login:

```bash
java -cp out vrms.Main
```

Main vehicle catalog:

```bash
java -cp out vrms.CatalogPage
```

My Vehicles:

```bash
java -cp out vrms.MyVehiclesPage
```

List Vehicle:

```bash
java -cp out vrms.ListVehiclePage
```

Rent Vehicle:

```bash
java -cp out vrms.RentVehiclePage
```

My Rentals:

```bash
java -cp out vrms.MyRentalsPage
```

Admin vehicle catalog:

```bash
java -cp out vrms.AdminDashboardPage
```

Pending approvals:

```bash
java -cp out vrms.AdminApprovalPage
```

Customer registration and admin login are accessible from the login UI.

Java 17 or newer is recommended.
