Feature: loading features from the file system

  Scenario: directory contains feature files

    Given a directory 'test-features'
    When I run the feature file loader
    Then it should find the features 'test-feature1, test-feature2'