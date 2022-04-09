package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.Person;
import pl.coderslab.app.entity.PersonDao;
import pl.coderslab.app.entity.PersonDetails;
import pl.coderslab.app.entity.PersonDetailsDao;

@Controller
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonDetailsDao personDetailsDao;


    @RequestMapping("/person-details")
    @ResponseBody
    public String personHello(){
        Person person = new Person();
        person.setEmail("tome@onet.pl");
        person.setLogin("Tomasz");
        PersonDetails personDetails = new PersonDetails();
        personDetails.setCity("Kraków");
        personDetails.setStreet("Mickiewicza");
        personDetails.setFirstName("Tomek");
        personDetails.setLastName("Czornak");
        personDetails.setStreetNumber(11);
        personDao.savePerson(person);
        personDetailsDao.savePersonDetails(personDetails);
        Person person1 = personDao.findById(1);
        PersonDetails personDetails1 = personDetailsDao.findById(1);
        person1.setPersonDetails(personDetails1);


        return "check the database";

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showPersonForm(Model model){

        Person person = new Person();
        model.addAttribute("person", person);

        return "PersonForm";
    }
    //zadanie 1
    @RequestMapping(value = "/zad1", method = RequestMethod.POST)
    public String showPersonFormZad1(@RequestParam String email, @RequestParam String password, @RequestParam String login){

        Person person = new Person();
        person.setEmail(email);
        person.setLogin(login);
        person.setPassword(password);

        personDao.savePerson(person);

        return "ThankYou";
    }

    //zadanie 2
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String showPersonForm(Person person){ //tu dostajemy obiekt zbindowany (wypelniony danymi)

        personDao.savePerson(person);

        return "ThankYou";
    }

}
