# VRMS - Vehicle Rental Management System

UI-only presentation repository for the Vehicle Rental Management System micro project.

## Scope

This repository mirrors the current pages and visual design of the main `VRMS-project` repository, but uses static sample data instead of the storage and business-logic layer.

It is intended for the early design / presentation stage, so every screen can be opened directly without creating accounts, vehicle records, rentals, or payment data first.

Included pages:

- Customer Login
- Customer Registration
- Admin Login
- Vehicle Catalog
- My Vehicles
- List Vehicle
- Rent Vehicle
- Payment
- My Rentals
- Profile
- Admin Vehicle Catalog
- Pending Vehicle Approvals
- Admin Earnings

The buttons are present for presentation and basic screen-to-screen navigation. Actions that would normally change persistent data display preview messages only.

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
java -cp out vrms.PaymentPage
java -cp out vrms.MyRentalsPage
java -cp out vrms.ProfilePage
java -cp out vrms.AdminDashboardPage
java -cp out vrms.AdminApprovalPage
java -cp out vrms.AdminEarningsPage
```

`vrms.Main` opens the customer login page:

```bash
java -cp out vrms.Main
```

Java 17 or newer is recommended.
