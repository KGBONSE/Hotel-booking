Feature: hotel reservations
  As a customer
  I want to make bookings on the hotel reservations system
  So I can reserve a room for the duration of my stay

  Background:
    Given I am on the hotel booking page


  Scenario: Should make a successful reservation
    When I submit my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      | John      | Roberts  | 20     | true      | 2021-12-02 | 2022-01-03 |
    Then my booking is registered

  @smoke
  Scenario: Should delete a submitted booking
    And I have submitted my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      | Zoe       | Bull     | 10     | true      | 2021-12-02 | 2022-01-03 |
    When I delete my booking
    Then my booking is removed from the list



  Scenario: Should fail to submit reservation with an invalid amount
    When I submit my details
      | firstName | lastName | amount | isDeposit | checkIn    | checkOut   |
      | Justice   | League   | aa     | true      | 2021-12-02 | 2022-01-03 |
    Then my booking is not saved
