#!/bin/bash

#
# Must run from ${project} directory
#

# Punten
# Input: src/main/resources/Puntsymbolen_v1.1.0.sld
# Output: /tmp/Puntsymbolen_v1.1.0.json
mvn clean test -Dtest=SymbolApplicationTests#genPointSLD

cat /tmp/Puntsymbolen_v1.1.0.json | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' -e 's/"xlink:/"xlink_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/Puntsymbolen_v1.1.0.json


# Lijnen
# Input: src/main/resources/Lijnsymbolen_v1.1.0.sld
# Output: /tmp/Lijnsymbolen_v1.1.0.json
mvn test -Dtest=SymbolApplicationTests#genLineSLD


cat /tmp/Lijnsymbolen_v1.1.0.json | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/Lijnsymbolen_v1.1.0.json

# Vlakken
# Input: src/main/resources/Vlaksymbolen_v1.1.0.sld
# Output: /tmp/Vlaksymbolen_v1.1.0.json
mvn test -Dtest=SymbolApplicationTests#genVlakSLD


cat /tmp/Vlaksymbolen_v1.1.0.json | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/Vlaksymbolen_v1.1.0.json
