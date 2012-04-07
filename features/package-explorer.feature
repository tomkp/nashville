Feature: exploring packages for classes

  Scenario: package / sub packages contains classes

    Given a package 'com.tomkp.testdata.classes'
    When I run the package explorer
    Then it should find the classes 'Test1, Test2, Test3'