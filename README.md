# Symbol

Symboltables

Bron https://github.com/Geonovum/TPOD/blob/master/Presentatiemodel/Symbolenbibliotheek%20STOP-TPOD%20v1.1.0.zip

## Usage
Create a postgres database with name usage and user testuser with pwd 12345
or adjust datasource definition in [application.yaml](./src/main/resources/application.yaml)

start the app
```bash
$ mvn clean spring-boot:run
```

### Convert xml to json
To convert the sld from the zip file to json execute [convert.bash](./scripts/convert.bash) from the project directory.
This will generate json files and load the json files into the database.

When the convert.bash script is restarted, all saved database content will be deleted and loaded again.

## Queries

```sql
-- get lines
select * from line where substring(name,1,2) <> 'lt' and substring(name,1,2) <> 'lm' and substring(name,1,2) <> 'ls';

-- lookup symbol by name
select * from symbol where name like 'vag%' order by name  limit 10;
```

## Swagger
See 
- http://localhost:8080/swagger-ui/index.html
- https://www.baeldung.com/java-swagger-set-list-response 

## Cors
See
- https://www.baeldung.com/spring-cors

## Actuator
See 
- http://localhost:8081/actuator
- http://localhost:8081/actuator/app-health
- http://localhost:8081/actuator/app-info

## References
- [spring doc](https://springdoc.org/)
- [jaxb tutorial](https://www.baeldung.com/jaxb)
- [example binding.xjb](https://github.com/orbisgis/ogc-custom-jaxb/blob/master/ogc-custom-model/src/main/resources/binding.xjb)
- [example xml to json](https://www.javatpoint.com/convert-xml-to-json-in-java)
- [online json to java](https://codebeautify.org/json-to-java-converter#)
- [web api](https://developer.mozilla.org/en-US/docs/Web/API)


## Maven package
- https://repository.sonatype.org/
- https://mvnrepository.com/search
