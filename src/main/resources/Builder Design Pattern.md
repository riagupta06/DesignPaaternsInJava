# Builder Design Pattern in Java

_Builder Design Pattern_ is one of the commonly used design patterns. It falls under the category of _Creational Design Patterns_.

## Overview

### Analogy

As the name suggests,Builder Design Patternis used to build objects.

Mainly it is used to create complex object by _using step by step approach_ and final step will return the _object in complete state_. Same construction process is used to create different representations of a complex object. This helps to make the _object construction process generic_.

Class does not directly create an object itself directly but delegates object creation to its Builder.

## **Need**  **for Builder Design Pattern**

Let&#39;s consider a case where we have entity that contains a lot of attributes

1. In case of too many parameters of a single object, some may be optional and some required. In that case we would require to create multiple constructors (constructor overloading).
2. Or we would be forced to send NULL for optional, not required parameters.
3. Also, in case of same types of parameters, we would need to maintain order of parameters.

    We can solve this problem by having a constructor for required attributes and setters for optional attributes. In that case, the problem would be that we would have an intermediate state of object in between during the process of building the final object i.e. till the last required setter call. This implies we would have an _inconsistent_ _state_ of theobject until the expected object is fully created. Ideally the object shouldn&#39;t be available till it is in complete state.

1. So we would need a way to create objects in complete state i.e. without the setters. This would ensure the immutability of the final object that is built. _Immutability_ is the key of builder design pattern.

## How to implement Builder Design Pattern

1. Create an entity class and add all the attributes of the class.
2. Add a static nested Builder class.
3. Copy all the attributes from outer class to builder class.
4. Add methods in Builder class for each attribute to set on builder object and return the same builder object i.e. each of these methods are enriching the builder object with newer attribute. These method names need not follow setter methods rules, names could be verbose.
5. We could add little optimization here by adding the constructor in builder class for required attributes and individual methods for optional attributes. (do this optimization if you feel required attributes list would never change, otherwise not a necessary step).
6. Final step in object creation would be to add a build method in builder class that would actually call the private constructor of outer class to create an object. For this, we would need to add a _private constructor in the outer entity class_ that accepts Builder object to construct the final entity object. Private constructor would ensure that object is created through this builder only.
7. Make sure _not to add any setter methods_ in outer class. It will defeat the purpose of creating an immutable object.
8. Finally we would create a client class that uses the static inner Builder class to first create its object and calls different methods to enrich the builder and in the end calls build() which actually returns the required entity object. We notice that the object creation is chained method call where each call is returning the builder object itself though we get final object on call of build().

### Code Example

- Let&#39;s create an entity class called **Employee** which would help us in creating desired objects with all required attributes and combination of optional attributes ensuring immutability.
```java
package com.codingnconcepts.builderdesignpattern;

public class Employee {

    private int employeeId; //required
    private String name;  //required
    private String phone;  //optional
    private String alternativePhone;  //optional
    private String address;  //optional

    //Note - only 1 private constructor to ensure all object creation goes through this
    private Employee(EmployeeBuilder employeeBuilder) {
        this.employeeId = employeeBuilder.employeeId;
        this.name = employeeBuilder.name;
        this.phone = employeeBuilder.phone;
        this.alternativePhone = employeeBuilder.alternativePhone;
        this.address = employeeBuilder.address;
    }

    //Note - No setters (provides immutability)

    // You can add getters if needed

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", alternativePhone='" + alternativePhone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class EmployeeBuilder {

        private int employeeId;
        private String name;
        private String phone;
        private String alternativePhone;
        private String address;

        public EmployeeBuilder(int employeeId, String name) {
            this.employeeId = employeeId;
            this.name = name;
        }
        public EmployeeBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public EmployeeBuilder withAlternativePhone(String alternativePhone) {
            this.alternativePhone = alternativePhone;
            return this;
        }
        public EmployeeBuilder withAddress(String address) {
            this.address = address;
            return this;
        }
        public Employee build() {
            Employee employee = new Employee(this);
            return employee;
        }
    }

}
```

- Let&#39;s create a client (Main Class) to test object creation through Builder Design Pattern
```java
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
```
```
OUTPUT:
Employee{employeeId=112, name=&#39;Ria Gupta&#39;, phone=&#39;9876543210&#39;, alternativePhone=&#39;9988776655&#39;, address=&#39;413-3rd Avenue, Shantinagar, Banglore&#39;}

Employee{employeeId=113, name=&#39;Rishabh Goyal&#39;, phone=&#39;1234567890&#39;, alternativePhone=&#39;null&#39;, address=&#39;null&#39;}

Employee{employeeId=114, name=&#39;Abhishek Bansal&#39;, phone=&#39;null&#39;, alternativePhone=&#39;null&#39;, address=&#39;null&#39;}

```

## **Advantages**  **of Builder Design Pattern**

1. Reduction in multiple constructors. Only one constructor that accepts Builder object is required.
2. There is no need to pass null for optional parameters to the constructor.
3. Object is instantiated fully i.e. in a complete state. We always get an immutable object since we didn&#39;t provide any setter methods and hence state of object once created cannot be changed.
4. Lesser error prone as user would know what they are passing in each method call. This also gives design flexibility and more readable of client code.

## **Disadvantages**  **of Builder Design Pattern**

1. The number of lines of code gets at least double using builder pattern as we copy all the fields of entity class to builder class. Code becomes more verbose.
2. It requires the creation of a separate builder for each type of object.

## Conclusion

In this article, we saw that Builder Design Pattern is a rescue where our use-case is such that we have a lot of parameters for a particular object type or we have requirement of adding new parameters may be in future. This should be used when we want to build immutable objects using theme construction process.

In addition to the benefits laid above, opt for this pattern makes adding parameters easier and code becomes less error-prone and easy to read.