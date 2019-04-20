# ExpirableMap

ExpirableMap is a simple Map implementation with time-to-live elements. 

You can handle it as a regular Map, but in addition to it, the implementation itself will take cake of removing from the map outdated elements.
You can tune the Map for increasing or decreasing the frequency of removal of outdated objects, an undelying thread will take care of inspecting old 
elements and remove them from Map.

[![Build Status](https://travis-ci.org/LoreScianatico/ExpirableMap.svg?branch=master)](https://travis-ci.org/LoreScianatico/ExpirableMap)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=alert_status)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=alert_status)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=coverage)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=bugs)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=bugs)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=code_smells)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=code_smells)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=duplicated_lines_density)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=duplicated_lines_density)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=sqale_rating)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=sqale_rating)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=reliability_rating)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=reliability_rating)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=security_rating)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=security_rating)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=sqale_index)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=sqale_index)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=vulnerabilities)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=vulnerabilities)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=ncloc)](https://sonarcloud.io/api/project_badges/measure?project=com.lorescianatico%3AExpirableMap&metric=ncloc)