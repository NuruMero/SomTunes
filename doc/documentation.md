# Songs App Documentation

## Milestones

1. PostgreSQL container added to the docker-compose. (Functional)
2. Model classes created. Added autogenerated id field.
3. Necessary DTOs created.
4. Repository interfaces created extending JpaRepository.
5. Service interfaces and with getAll, getOne, postOne, putOne and deleteOne created.
6. ...

## TODO

Set up POST requests adjusted to 1:n classes.

Progress: Bands POST requests added.

## Imports and attributes

* PostgreSQL uses Integers for auto-increments. The IDs have been set to Integer for compatibility.
* Date is imported from java.sql.Date.
* Modelmapper config class created to aid in the entity to DTO conversion (and vice versa).
* Jakarta for entity persistence.

## Annotations and their usage

### Substituting @Autowired with @RequiredArgsConstructor
Modified a private field with @Autowired into a private + final field, with @RequiredArgsConstructor in the class annotations.

It not only keeps the original purpose but also makes sure to add the needed value into the field.

### @Table and @Column
Persistence annotations to make sure the system understands the classes and attributes are supposed to be part of the database.

### @Id and @GeneratedValue
Id is a persistence annotation that determines the primary key of a table. GeneratedValue makes sure that the attribute has a randomly generated value if the original value is null.

### @ManyToOne, @JoinColumn, @OneToMany and the Three Musketeers
Connects the entities' tables to each other in a 1:n relationship.

### @Service, @RestController, @Configuration
Makes sure that the classes affected by these annotations are undestood by the Spring App as the parts they're supposed to be.

### @RequestMapping
Used to set the root for the requests of an API.

### @GetMapping, @PutMapping, @PostMapping, @DeleteMapping
Establishes request methods and needed parts of the complete URL.

### @PathVariable and @RequestBody
Used for extra introduction of data or dynamic URLs.