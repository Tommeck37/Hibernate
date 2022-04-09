package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.Student;
import pl.coderslab.app.entity.Person;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }
    @ModelAttribute("skills")
    public List<String> programmingLanguages() {
        return Arrays.asList("Java", "Python", "C#", "Ruby", "Perl");
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("Jazda", "Oglądanie", "Pływanie", "Jazda Konno", "Łowienie");
    }
    @ResponseBody
    @RequestMapping(value = "/studentForm", method = RequestMethod.POST)
    public String showPersonForm(Student student){ //tu dostajemy obiekt zbindowany (wypelniony danymi)

        return student.getFirstName() + " " + student.getLastName() + " "+
               student.getCountry() + " "+ student.getGender() + " " +
                student.getHobbies();
    }
    @RequestMapping(value = "/studentForm", method = RequestMethod.GET)
    public String showPersonForm(Model model){

        Student student = new Student();
        model.addAttribute("student", student);

        return "student";
    }

}
