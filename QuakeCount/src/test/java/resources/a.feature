Feature: The Dangerous Earthquakes in Turkey

  Scenario : The Last 100 Earthquake in Turkey
    Given : go to AFAD web site
    And : navigate to the last earthquakes data
    When List to dates,deepnesses,locates and magnitudes of the last 100 earthquakes
    And test that at least one of the earthquakes is greater than 4.0



