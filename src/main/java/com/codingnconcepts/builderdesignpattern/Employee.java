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
