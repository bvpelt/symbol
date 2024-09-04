#!/bin/bash

#
# Must run from ${project} directory
#

# Punten
# Input: src/main/resources/Puntsymbolen_v1.0.1.sld
# Output: /tmp/Puntsymbolen_v1.0.1.json
mvn clean test -Dtest=SymbolApplicationTests#genPointSLD

file=Puntsymbolen_v1.0.1.json
cat /tmp/${file} | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' -e 's/"xlink:/"xlink_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/${file}


# Lijnen
# Input: src/main/resources/Lijnsymbolen_v1.0.1.sld
# Output: /tmp/Lijnsymbolen_v1.0.1.json
mvn test -Dtest=SymbolApplicationTests#genLineSLD

file=Lijnsymbolen_v1.0.1.json
cat /tmp/${file} | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/${file}

# Vlakken
# Input: src/main/resources/Vlaksymbolen_v1.1.0.sld
# Output: /tmp/Vlaksymbolen_v1.1.0.json
mvn test -Dtest=SymbolApplicationTests#genVlakSLD

file=Vlaksymbolen_v1.1.0.json
cat /tmp/${file} | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/${file}

# Normwaarden
# Input: src/main/resources/Normwaarden_v1.0.1.sld
# Output: /tmp/Normwaarden_v1.0.1.sld
mvn test -Dtest=SymbolApplicationTests#genNormwaardeSLD

file=Normwaarden_v1.0.1.json
cat /tmp/${file} | sed -e 's/"sld:/"sld_/g'  -e 's/"xmlns:/"xmlns_/g'  -e 's/"se:/"se_/g' -e 's/"ogc:/"ogc_/g' -e 's/"xsi:/"xsi_/g' > /tmp/a.a
cp /tmp/a.a src/main/resources/${file}