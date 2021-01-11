# NERHEAD-archetypes



# Eclipse

       * New - Project - Maven Project - ... 

       - "Select an Archetype"  - Catalog ( Configure ) Click
       
       - Add Remote Catalog
       
       - Remote Archetype Catalog - Catalog File
       
            https://nerdhead.github.io/NERHEAD-archetypes/archetype-catalog.xml
            
# How to Create archetypes

https://maven.apache.org/archetype/maven-archetype-plugin/advanced-usage.html

* Create archetype project from original project

      - mvn archetype:create-from-project

* Create archetype jar+pom files

      - cd "project path"\target\generated-sources\archetype
      - mvn install
            
* setting remote Catalog

      - find catalog: C:\Users\"USER_NAME"\.m2\repository\archetype-catalog.xml
      
      - find archetype: C:\Users\"USER_NAME"\.m2\repository\"GROUP ID"\"PROJECT NAME"
      
      - edit catalog :
      
            - add <repository>https://"GIT_NICK_NAME".github.io/"REPOSITORY_NAME"</repository>
            
      - create branch name : gh-pages
      
      - add catalog, archetype
      
