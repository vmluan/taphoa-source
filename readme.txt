-add javax.faces-2.1.25 jar in the build path.
-modify pom.xml file, add the following lines of code:
<dependency>
	  <groupId>com.sun.faces</groupId>
	  <artifactId>jsf-api</artifactId>
	  <version>2.1.25</version>
	  <scope>compile</scope>
	</dependency>
	<dependency>
	  <groupId>com.sun.faces</groupId>
	  <artifactId>jsf-impl</artifactId>
	  <version>2.1.25</version>
	  <scope>compile</scope>
	</dependency>