# Sportradar Coding academy

Dear sportradar professional,

you can find an entity-relationship diagramm and the SQL file in the `./resources` folder.
The database is a small file based one (H2) and is also located
there.

The graphical user interfaces main site is the `calendar.html` in the 
project root. There is some additional `JavaScript` to connect to the backend.

The backend is made in `Java SE` and i have used `Maven` as a dependency management
tool.

You can find three important dependencies in the Maven pom.xml file:

* Jersey (Standard REST server implementation from Java EE) with the right
* JSON Mapper (with Jackson) and the
* H2 database driver

In the `com.sportradar.intern.rest` package are the REST endpoints. <br>
* `http://localhost:8080/api/calendar`
* `http://localhost:8080/api/calendar/football`
* `http://localhost:8080/api/calendar/hockey`

Under `com.sportradar.intern.persistence.db` you can find the database specific path. <br>
There is an additional package `com.sportradar.intern.persistence.officialAPI` as consideration
to connect the backend with a webservice.


#### Summary

In short, for me it was a complete new thing to create 
a simple Java program without big frameworks like Spring or the JavaEE
to connect to the "outside world". 

I had a lot of fun and know that there is a lot more to learn.

Best regards, Christopher