# Model Generator (JPA Entity Generator)

An open-source tool for generating Java source code for entity classes with JPA2 annotations from database metadata.
What sets this tool apart from the others is the ability to programmatically customize the generation process and
output results, giving you an extreme flexibility and ability to re-generate your model any time.

## Supported databases

- Oracle

## Use cases

- Non-standard naming conventions
- Custom relationships between entity
- Fine-grained configuration of JPA annotations
- Additional annotations e.g. Jackson or JAXB
- Entity customization that survives re-generations
- Custom solutions based on database metamodel
- Custom solutions based on JPA and Java model

## Alternative tools

### Eclipse Dali (Dali Java Persistence Tools) (GUI)
http://www.eclipse.org/webtools/dali/
http://wiki.eclipse.org/Dali
Eclipse plugin for entity generation.

### IntelliJ IDEA Persistence Tool (GUI)
http://www.jetbrains.com/idea/
http://www.jetbrains.com/idea/webhelp/generating-persistence-mappings.html
Tool for JPA and Hibernate entity generation. As of version 13, there are only a few customization options.

### Telosys Tools (GUI)
https://sites.google.com/site/telosystools/home
https://github.com/telosys-tools-demo/
Eclipse plugin for code generation via templates. Can generate JPA entities and CRUD applications.

### Hibernate Tools (command-line and GUI)
http://hibernate.org/tools/
https://github.com/hibernate/hibernate-tools
Set of tools for generating source artifacts for Hibernate, including JPA entities. Used to be
part of Hibernate 3, now part of JBoss Tools used in JBoss Developer studio.

### MinuteProject (command-line and GUI)
http://minuteproject.wikispaces.com/
Application generator which is also able to reverse-engineer database schemas.

### OpenJPA Reverse Mapping Tool (command-line)
http://openjpa.apache.org/tools.html
Another JPA entity generator similar to Hibernate Tools.

Anything else worth mentioning (reliable, proven)? Let me know!
