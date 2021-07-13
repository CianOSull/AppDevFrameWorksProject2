# App Development Frameworks Project 2 <br />
 Second Project for Application Development Frameworks that is about combining the Spring framework with a html website. <br />
 
## Project specifiction: <br />
**Database Information** <br />
1. Create a Spring Starter Project with  <br />
o Web  <br />
o Thymeleaf  <br />
o Database  <br />
o JPA  <br />
o H2 <br />

2. Create a class for each of the entities you will need and use some or all of the following annotations: <br />
o @Entity <br />
o @ManyToOne, @OneToMany <br />
o @Id <br />
o @GeneratedValue <br />
o @Column(nullable=false, unique=true <br />
o @JoinColumn <br />
o @OnDelete(action = OnDeleteAction.CASCADE) <br />

3. Create a repository for each of the classes by creating an interface which extends the JpaRepository which is automatically a Spring bean. <br />
4. Create a service layer in case you need to add some business rules at a later stage. <br />
o Add a service interface for each class <br />
o Add a service implementation for each class <br />
5. Load data into the database by calling the appropriate method from your service layer. <br />
6. Use either a dedicated data loader class or add code to the main class, but in both cases you will implement CommandLineRunner, to give you access to the run() method which is run after the spring application context has been created. You will put your save commands to populate the database into this method. <br />

**The Controllers (MVC)** <br />
1. Create a homepage in HTML. Nothing fancy yet and no Thymeleaf. <br />
2. Create an index controller in a new @Controller class in a suitable package i.e. a get controller that returns the template's name (HTML page name) as a String <br />
3. Create a controller to show all the project/directors <br />
   The user might type http://localhost:8080/directors to see a webpage that lists the directors. <br />
   The controller should <br />
   o Have a Model parameter <br />
   o Use the project's service layer to access all the directors in alphabetical order <br />
   o Add this list to the model <br />
   o Return the view as a String <br />

   The view should use Thymeleaf to <br />
   o Iterate through the list of objects extracting <br />
   o Display the fields of the object on the page in some suitable format using th:text, for example <br />
4. Create a controller to show one movie based upon its id. <br />
   The user might type http://localhost:8080/movie/4 to see the movie with id # 4. <br />
   The controller should <br />
  o Have a @PathVariable parameter for the id <br />
  o Have a @Model parameter to send data to the view <br />
  o Use the project's service layer to access a particular project by id  <br />
  o Add this project to the model <br />
  o Return the view as a string <br />
  The View should use Thymeleaf to display the details about the project in a suitable format. <br />

  This is not a full list of controllers, but it gets you started.  <br />

**Fragments** <br />
I would advise you to use fragments because they really shorten the code in the views, maintain a consistent look and feel and allow you to easily edit the common elements e.g. the navigation menu. <br />

**Forms and form validation** <br />
There are a few forms needed in this project. <br />
1. Decide on what data you are trying to gather from the user. <br />
2. Create a class for that form. <br />
3. Create a Get controller which  <br />
   o receives a Model object as a parameter  <br />
   o adds a suitable object, for gathering form data, to the model e.g. NewDirectorForm <br />
   o returns the view (a HTML form to register a user) as a String  <br />
4. Create the HTML form  <br />
5. Create a POST controller which <br />
  o Receives the form's data (the bound object) as a parameter <br />
  o Use the service layer to save this user or deal with any arising issues e.g. duplicate user <br />
  o Return the home page as a String (you can change this "success" page later) <br />
6. Add annotations to the form's class where appropriate, these might be <br />
7. Test this by providing wrong data e.g. leave name fields empty. <br />

**Change Language** <br />
Create a new Configuration-annotated class that extends the WebMvcConfigurer containing the only boilerplate code you will need for this project. <br />
Create a file called, for example, messages_FR.properties which will hold the messages (text) for French. <br />

**So far?** <br />
If you have followed the instructions above you now have <br />
o an in-memory database and associated domain <br />
o Thymeleaf view to present single and multiple data to the user <br />
o Controllers that send data to views <br />
o Controllers that receive data from views and act upon the data received (save) <br />
o Internationalisation <br />
o Form validaton <br />

At this stage it should be easy to add other functionality and to provide two REST API endpoints that return JSON (no views), followed by a separate application to demonstrate how these endpoints might be used (no CSS required, just demonstrate how data from one application is retrieved and displayed be a second application). <br />

**Security**
Add the security to the project using the pom file and other classes, along with associated repository and service layer. Populate the new table(s). <br />
Create a security configuration file starting by adding no security checks for any user then add authentication for certain pages and authorisation for other pages.  <br />
Add authorisation/authentication to the consumer application. <br />

**Alphabetical Order** <br />
Return the list of directors in alphabetical order, first ordering by surname then by firstname. <br />
