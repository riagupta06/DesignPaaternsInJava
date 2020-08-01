package com.codingnconcepts.builderdesignpattern;

public class Client {

    public static void main(String[] args) {

        //Object with all the attributes
        Employee employee1 = new Employee.EmployeeBuilder(112, "Ria Gupta")
                .withPhone("9876543210")
                .withAlternativePhone("9988776655")
                .withAddress("413-3rd Avenue, Shantinagar, Bangalore")
                .build();

        System.out.println(employee1);

        //Object with a combination of required and some optional attributes
        Employee employee2 = new Employee.EmployeeBuilder(113, "Rishabh Goyal")
                .withPhone("1234567890")
                // no alternative phone
                // no address
                .build();

        System.out.println(employee2);

        //Object with only required attributes
        Employee employee3 = new Employee.EmployeeBuilder(114, "Abhishek Bansal")
                //no phone
                //no alternative phone no
                // address
                .build();

        System.out.println(employee3);


    }

}
