Feature: scanning for annotations

  Scenario: package contains annotated fixtures

    Given a package 'com.tomkp.testdata.annotations'
    When I scan for 'com.tomkp.nashville.annotations.Fixture' annotations
    Then it should find the annotated classes 'TestFixture1, TestFixture2'
