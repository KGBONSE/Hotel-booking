Feature: hotel reservations
  As a customer
  I want to make bookings on the hotel reservations system
  So I can reserve a room for the duration of my stay


  Scenario: Should make a successful reservation
    Given I am on the hotel booking page
    When I submit my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      | John      | Roberts  | 20     | true      | 2021-12-02 | 2022-01-03 |
    Then my booking is registered


  Scenario: No firstname provided
    Given I am on the hotel booking page

    And I have submitted my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      |           | Bull     | 10     | true      | 2021-12-02 | 2022-01-03 |
    When I submit my details
    Then i get an error message "please provide a valid firstname"


  Scenario: No lastname provided
    Given I am on the hotel booking page

    And I have submitted my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      |           | Bull     | 10     | true      | 2021-12-02 | 2022-01-03 |
    When I submit my details
    Then i get an error message "please provide a valid lastname"


  Scenario: No amount provided
    Given I am on the hotel booking page

    And I have submitted my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      |           | Bull     | 10     | true      | 2021-12-02 | 2022-01-03 |
    When I submit my details
    Then i get an error message "please provide a valid amount"


  Scenario: No checkin date provided
    Given I am on the hotel booking page

    And I have submitted my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      |           | Bull     | 10     | true      | 2021-12-02 | 2022-01-03 |
    When I submit my details
    Then i get an error message "please provide a valid checkin date"


  Scenario: No checkout date provided
    Given I am on the hotel booking page
    And I have submitted my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      |           | Bull     | 10     | true      | 2021-12-02 | 2022-01-03 |
    When I submit my details
    Then i get an error message "please provide a valid checkout date"


  Scenario: Should fail to submit reservation with an invalid amount
    Given I am on the hotel booking page
    When I submit my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      | Justice   | League   | aa     | true      | 2021-12-02 | 2022-01-03 |
    Then my booking is not saved
