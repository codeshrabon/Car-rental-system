import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String carBrand;
    private String carModel;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String carBrand, String carModel, double basePricePerDay) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.basePricePerDay = basePricePerDay;
        // above there is a new car so its available so its true
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getCarBrand() {
        return carBrand;

    }

    public String getCarModel() {
        return carModel;
    }

    // this rentalDays means how many days will he take is
    // so basePerDay will count it and retun its result
    public double caculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // when it rented then this method will invoke that the car is not availavle
    // or you can say isCarAvailabe = false;
    public void rent() {
        isAvailable = false;
    }
    // if its retun then isCarAvailabe will be true

    public void returnCar() {
        isAvailable = true;
    }

    // public double caculatePrice(int rentalDays) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'caculatePrice'");
    // }

}

class Customer {
    private String customerId;
    private String customerName;

    public Customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

}

class Rental {
    // here from Car type we create an object Car = car
    // creating object of class type
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        // if car shows available then it straingt go for rent by car object
        if (car.isAvailable()) {
            car.rent();

            // so when i rent that car then i will enter all into Array list rental info
            rentals.add((new Rental(car, customer, days)));

        } else {
            System.out.println("Car is not available");
        }
    }

    // now time for return car and which car is he returning
    public void returnCar(Car car) {
        // this whos that returning car is now available
        // in returnCar method will show true that is available
        // maybe some info is not inserted so this posistion is not quite right
        car.returnCar();

        // make a vartibale
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;

            }

        }
        if (rentalToRemove != null) {
            // remove() method is buildin version to remove the specific info
            rentals.remove(rentalToRemove);
            // System.out.println("Car return successfully ");
        } else {
            System.out.println("Car is not returned");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        // using while loop to specify my choice and this code will run untill i didnt
        // do exit
        while (true) {
            System.out.println("==== Car Rental System ====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter you choice");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("\n == Rent a Car ==\n");
                System.out.print("Enter your name : ");
                String customerName = sc.nextLine();

                System.out.println("\n Available Cars: ");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getCarBrand() + " - " + car.getCarModel());

                    }
                }
                System.out.println("\n Enter the car ID you want to rent: ");
                String carId = sc.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentalDays = sc.nextInt();
                sc.nextLine();

                // creating Customer type object

                // now what i did was giving customer ID(CUS) string and customers.size() it
                // means size of customer identity with +1
                // address of customer will appera in cutomers.size() +1;
                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car serlectCar = null;
                for (Car car : cars) {
                    // input carId will be checked with getCarId and entered carId
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        serlectCar = car;
                        break;

                    }
                }
                if (serlectCar != null) {
                    double totalPrice = serlectCar.caculatePrice(rentalDays);
                    System.out.println("\n == Rental Information =\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getCustomerName());
                    System.out.println("Cae: " + serlectCar.getCarBrand() + " " + serlectCar.getCarModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $ ", totalPrice);

                    System.out.println("\n Confirm rental (Y/N)");
                    String confirm = sc.nextLine();
                    // ewaulIgnoreCare is a string which check the statement capital/small matter na

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(serlectCar, newCustomer, rentalDays);
                        System.out.println("\n Car rented successfullty. ");

                    } else {
                        System.out.println("\n Rental canceled. ");
                    }

                } else {
                    System.out.println("\n Invalid car selection or car not available fr rent. ");
                }

            } else if (choice == 2) {
                System.out.println("\n == Return a Car == \n");
                System.out.println("Enter the car ID you want to return: ");
                String carId = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;

                    }
                }
                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }

                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by: " + customer.getCustomerName());

                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }

                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }

            } else if (choice == 3) {
                break;

            } else {
                System.out.println("Invalid choice. Pleace enter a valid option.");
            }

        }
        System.out.println("\n Thank you for using the Car Rental System!.");
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}
