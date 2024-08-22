# Symbol

Symboltables

Bron https://github.com/Geonovum/TPOD/blob/master/Presentatiemodel/Symbolenbibliotheek%20STOP-TPOD%20v1.1.0.zip

## Usage
Create a postgres database with name usage and user testuser with pwd 12345
or adjust datasource definition in [application.yaml](./src/main/resources/application.yaml)

### Convert xml to json
To convert the sld from the zip file to json execute [convert.bash](./scripts/convert.bash) from the project directory.
This will generate json files and load the json files into the database.

When the convert.bash script is restarted, all saved database content will be deleted and loaded again.

## References
- [jaxb tutorial](https://www.baeldung.com/jaxb)
- [example binding.xjb](https://github.com/orbisgis/ogc-custom-jaxb/blob/master/ogc-custom-model/src/main/resources/binding.xjb)
- [example xml to json](https://www.javatpoint.com/convert-xml-to-json-in-java)
- [online json to java](https://codebeautify.org/json-to-java-converter#)


## Maven package
- https://repository.sonatype.org/
- https://mvnrepository.com/search
