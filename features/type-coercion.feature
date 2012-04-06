Feature: coercing parameters

  Scenario: type is Date

    Given a directory 'features'
    When I run the feature file loader
    Then it should find the features 'annotation-scanning.feature, test-feature2'
