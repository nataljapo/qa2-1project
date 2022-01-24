Feature: Weather forecast

  Scenario: Weather for specific city
    Given city name is "Cairns"
    And country is "AU"
    When we are requesting weather data

    Then coordinates are:
      | lon | 145.77 |
      | lat | -16.92 |

    And weather is:
      | id  | main   | description      | icon |
      | 802 | Clouds | scattered clouds | 03n  |

    And base is "stations"

    And main info is:
      | temp     | 300.15 |
      | pressure | 1007   |
      | humidity | 74     |
      | temp_min | 300.15 |
      | temp_max | 300.15 |

    And visibility is 10000

    And wind is:
      | speed | 3.6 |
      | deg   | 160 |

    And clouds are:
      | all | 40 |

    And dt is 1485790200
    And sys is:
      | type    | 1          |
      | id      | 8166       |
      | message | 0.2064     |
      | country | AU         |
      | sunrise | 1485720272 |
      | sunset  | 1485766550 |

    And id is 2172797
    And name is "Cairns"
    And cod is 200

