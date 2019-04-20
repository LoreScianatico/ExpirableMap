# ExpirableMap

ExpirableMap is a simple Map implementation with time-to-live elements. 

You can handle it as a regular Map, but in addition to it, the implementation itself will take cake of removing from the map outdated elements.
You can tune the Map for increasing or decreasing the frequency of removal of outdated objects, an undelying thread will take care of inspecting old 
elements and remove them from Map.

[![Build Status](https://travis-ci.org/LoreScianatico/ExpirableMap.svg?branch=master)](https://travis-ci.org/LoreScianatico/ExpirableMap)