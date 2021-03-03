Search Breweries

1. Search by full brewery name - automated
2. Search by partially matched brewery name - automated
3. Search by query which returns no results - automated
4. Verify json schema - automated
5. Search query with underscores - automated. Test is ignored, because it is failing when search query contains more than one underscore
6. Search by other parameters, e.g. city, stree etc. Requrements should be clarified by which attributes search mechanism should be implemented
7. Spell check test - verify search mechanism when query contains some spelling error
8. Autosuggestions - verify auto suggested results if such mechanism should be implemented. Looks like it is more related to breweries/autocomplete endpoint
9. Verify url encoding for spaces with %20 - implicitly covered as RestAssured doing it automatically if urlEncodingEnabled = true (it is true by default) 
10. Verify search mechanism when search query contains special characters/digits/etc Requirements should be clarified here

List Breweries

1. Verify filtering by city
2. Verify filtering by name
3. Verify filtering by state
4. Verify filtering by postal code. Verify for all valid postal code formats - 4 digits; 9 digits; if there are requirements for formst, e.g. poste code should starts with some number 
5. Verify filtering by type. One of possible types is enough, as all types in the same class of equivalence. Verify not existing type, that it retun empty results instead of system crush for example
6. Verify results per given page. Important to check boundary values
7. Verify number of results returned when using per_page parameter
8. Verify sorting for one of the String filter (city, name, state, type). Verify sorting for poste code as it has digits.
9. Verify complex query with filtering, page, per_page, sort parameters
10. Verify ordering of how parameters are applied. Looks like that all results are firstly sorted and only then filtered
11. Additionally to that negative cases can be verified with not existing values; values special characters. Sapces in uri encoding, schema verification

Estimated effort for task is about 2-3 story points based on teams base estimation and scope (only coded implementation or also requirements clarification). Code review, CI flow are also included here.

Make sure that you have Maven installed

To run tests:
mvn clean test

To generate and open Allure report:
mvn allure:serve