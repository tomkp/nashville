Feature: coercing parameters

  Scenario: type is Date
    Given a parameter '20/03/2001'
    When I run the coercer

  Scenario: type is a Class
    Given a parameter 'java.lang.String'
    When I run the coercer

  Scenario: type is a Boolean
    Given a parameter 'java.lang.Boolean'
    When I run the coercer
