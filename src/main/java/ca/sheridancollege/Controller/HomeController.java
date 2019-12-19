package ca.sheridancollege.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.Bean.Job;
import ca.sheridancollege.Database.DatabaseAccess;
@Controller
public class HomeController {

	@Autowired
	private DatabaseAccess da;

	@GetMapping("/ap")
	public String goRun() {
	
		return"addjob.html";
	}
	

	@GetMapping("/")
	public String runHome() {
	
		return"HomePage.html";
	}
	
	@GetMapping("/register")

	public String goRegistration () {

	return "register.html";

	}
	
	
	@GetMapping("/showjobs")
	public String jobresult(Model model) {
		
		 model.addAttribute("conNum", da.getJobs());

		return "result.html";
	}
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "th_login.html";
	}
	
	 @GetMapping("/access-denied")
	    public String accessDenied() {
	        return "th_access-denied.html";
	    }
	

	@GetMapping("/addjob.kar")
	public String addKar(@RequestParam String jobid,
			             @RequestParam String company,
			             @RequestParam String position,
			             @RequestParam String ptype,
			             @RequestParam String status,Model model) {
		
		Job ca= new Job();
		ca.setJobid(jobid);
		ca.setCompany(company);
		ca.setPosition(position);
		ca.setPtype(ptype);
		ca.setStatus(status);
		
	da.addJob(ca);
		 model.addAttribute("conNum", da.getJobs());
		 
		return"result.html";
	}

 
	
	@GetMapping("/editLink/{id}")
	public String editLink(Model model, @PathVariable int id){
		
	Job s = da.getJobsById(id);

	model.addAttribute("jb", s);

	return "modify.html";

	}

	@GetMapping("/modify")
	public String modifyStudent(@RequestParam String jobid,
            @RequestParam String company,
            @RequestParam String position,
            @RequestParam String ptype,
            @RequestParam String status,@RequestParam int id,Model model) {

		Job ca= new Job();
		ca.setJobid(jobid);
		ca.setCompany(company);
		ca.setPosition(position);
		ca.setPtype(ptype);
		ca.setStatus(status);
	ca.setId(id);	
		 da.updateJob(ca);
		model.addAttribute("conNum",da.getJobs());
		
		return"result.html";

	}



	@GetMapping("/deleteLink/{id}")
	public String deleteLink(Model model, @PathVariable int id){
		
da.deleteJob(id);

	model.addAttribute("conNum", da.getJobs());
	
	return"result.html";

	}
	
	@GetMapping("/search")
public String getElement(Model model,@RequestParam String by,@RequestParam String searchf) {
	 
		model.addAttribute("conNum",da.searchByfield(by, searchf));
		
		return "result.html";
	}
	
	
	@PostMapping("/register")

	public String doRegistration(@RequestParam String username,

	@RequestParam String password,@RequestParam int roleId) {

		da.addUser(username, password);

		long userId = da.findUserAccount(username).getUserId();
	if(roleId==1) {
		da.addRole(userId, 1);
		}
	if(roleId==2){
		da.addRole(userId, 2);
	}
	
	return "redirect:/";

	}
	
	@GetMapping("/Dummy")
	public String DummyB(Model model) {
	da.generateJobs();
	model.addAttribute("conNum", da.getJobs());
	return "result.html";

	}
}
