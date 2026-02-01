package pl.jakub.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestUserProvider {

  private final String name;
  private final String emailBase;
  private final String password;
  private final Title title;
  private final LocalDate dateOfBirth;
  private final String firstName;
  private final String lastName;
  private final String company;
  private final String address;
  private final String addressSecond;
  private final Country country;
  private final String state;
  private final String city;
  private final String zipCode;
  private final String mobileNumber;

  public TestUserProvider(@Value("${test.user.name}") String name,
      @Value("${test.user.email}") String emailBase,
      @Value("${test.user.password}") String password, @Value("${test.user.title}") Title title,
      @Value("${test.user.dateOfBirth}") String dateOfBirth,
      @Value("${test.user.firstName}") String firstName,
      @Value("${test.user.lastName}") String lastName,
      @Value("${test.user.company}") String company, @Value("${test.user.address}") String address,
      @Value("${test.user.addressSecond:}") String addressSecond,
      @Value("${test.user.country}") Country country, @Value("${test.user.state}") String state,
      @Value("${test.user.city}") String city, @Value("${test.user.zipCode}") String zipCode,
      @Value("${test.user.mobileNumber}") String mobileNumber) {
    this.name = name;
    this.emailBase = emailBase;
    this.password = password;
    this.title = title;
    this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.addressSecond = addressSecond;
    this.country = country;
    this.state = state;
    this.city = city;
    this.zipCode = zipCode;
    this.mobileNumber = mobileNumber;
  }

  public UserCredentials defaultUser() {
    return new UserCredentials(name, emailBase, password, title, dateOfBirth, firstName, lastName,
        company, address, addressSecond, country, state, city, zipCode, mobileNumber);
  }
}
