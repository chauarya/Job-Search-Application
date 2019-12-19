package ca.sheridancollege.Database;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.Bean.DummyJob;
import ca.sheridancollege.Bean.Job;
import ca.sheridancollege.Bean.User;
 



@Repository
public class DatabaseAccess {

  
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void addJob(Job num) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query="INSERT INTO job (jobid,company,position,ptype,status) VALUES "+
		"(:jobid, :company, :position, :ptype, :status)";
		parameters.addValue("jobid", num.getJobid());
		parameters.addValue("company", num.getCompany());
		parameters.addValue("position", num.getPosition());
		parameters.addValue("ptype", num.getPtype());
		parameters.addValue("status", num.getStatus());
		 
		jdbc.update(query, parameters);
		}
	
 
	public ArrayList<Job> getJobs() {
		
		String query = "SELECT * FROM job";

		ArrayList<Job> jo = (ArrayList<Job>)jdbc.query(query,
		new BeanPropertyRowMapper<Job>(Job.class));
 
		return jo;	
	}
	public Job getJobsById(int id) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "SELECT * FROM job WHERE Id=:id";

		parameters.addValue("id", id);

		ArrayList<Job> j =

		(ArrayList<Job>)jdbc.query(query, parameters,

		new BeanPropertyRowMapper<Job>(Job.class));

		if (j.size()>0)

		return j.get(0);
		else
		return null;

		}
	
	public void updateJob(Job j) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query="UPDATE job SET jobid=:jobid, company=:company, position=:position,"
				+ "ptype=:ptype, status=:status WHERE Id=:id";
		parameters.addValue("jobid", j.getJobid());
	parameters.addValue("company", j.getCompany());
	parameters.addValue("position", j.getPosition());
	parameters.addValue("ptype", j.getPtype());
	parameters.addValue("status", j.getStatus());
	parameters.addValue("id", j.getId());
		jdbc.update(query, parameters);
		}
	
	public void deleteJob(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query="DELETE from job where id=:id";
		 
		parameters.addValue("id",id);

		jdbc.update(query, parameters);
		}

	
	public ArrayList<Job> searchByfield(String by,String searchf){
		
		ArrayList<Job> nn= new ArrayList<Job>();
		
		if(by.equalsIgnoreCase("jobid")) {
			
			String query = "SELECT * FROM job WHERE jobid="+searchf+";";

			List<Map<String, Object>> rows = jdbc.queryForList(query,

					new HashMap<String,Object>());

					for (Map<String, Object> row : rows) {

					Job d = new Job();

					d.setJobid((String)(row.get("jobid")));
					d.setCompany((String)(row.get("company")));
					d.setPosition((String)(row.get("position")));
					d.setPtype((String)(row.get("ptype")));
					d.setStatus((String)(row.get("status")));
			nn.add(d);
		}
					}
		
		else if(by.equalsIgnoreCase("company")) {
			
 			String query = "SELECT * FROM job WHERE company='"+searchf+"'";

			List<Map<String, Object>> rows = jdbc.queryForList(query,

					new HashMap<String,Object>());

					for (Map<String, Object> row : rows) {

					Job d = new Job();

					d.setJobid((String)(row.get("jobid")));
					d.setCompany((String)(row.get("company")));
					d.setPosition((String)(row.get("position")));
					d.setPtype((String)(row.get("ptype")));
					d.setStatus((String)(row.get("status")));
			nn.add(d);
		}
					}
		else if(by.equalsIgnoreCase("position")) {
			
			String query = "SELECT * FROM job WHERE position='"+searchf+"'";

			List<Map<String, Object>> rows = jdbc.queryForList(query,

					new HashMap<String,Object>());

					for (Map<String, Object> row : rows) {

					Job d = new Job();

					d.setJobid((String)(row.get("jobid")));
					d.setCompany((String)(row.get("company")));
					d.setPosition((String)(row.get("position")));
					d.setPtype((String)(row.get("ptype")));
					d.setStatus((String)(row.get("status")));
			nn.add(d);
		}
					}
		else if(by.equalsIgnoreCase("ptype")) {
			
			String query = "SELECT * FROM job WHERE ptype='"+searchf+"'";

			List<Map<String, Object>> rows = jdbc.queryForList(query,

					new HashMap<String,Object>());

					for (Map<String, Object> row : rows) {

					Job d = new Job();

					d.setJobid((String)(row.get("jobid")));
					d.setCompany((String)(row.get("company")));
					d.setPosition((String)(row.get("position")));
					d.setPtype((String)(row.get("ptype")));
					d.setStatus((String)(row.get("status")));
			nn.add(d);
		}
					}
		else if(by.equalsIgnoreCase("status")) {
			
			String query = "SELECT * FROM job WHERE status='"+searchf+"'";

			List<Map<String, Object>> rows = jdbc.queryForList(query,

					new HashMap<String,Object>());

					for (Map<String, Object> row : rows) {

					Job d = new Job();

					d.setJobid((String)(row.get("jobid")));
					d.setCompany((String)(row.get("company")));
					d.setPosition((String)(row.get("position")));
					d.setPtype((String)(row.get("ptype")));
					d.setStatus((String)(row.get("status")));
			nn.add(d);
		}
					}
		
		
		return nn;
		
		
	}
	
	
	
	

	public User findUserAccount(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where userName=:userName";
		parameters.addValue("userName", userName);
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size()>0)
		{
			System.out.println(users.toString());
			return users.get(0);
		}
		else
			return null;
	}
	
	public List<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "and userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void addUser(String userName, String password) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "insert into SEC_User "

		+ "(userName, encryptedPassword, ENABLED)"

		+ " values (:userName, :encryptedPassword, 1)";

		parameters.addValue("userName", userName);

		parameters.addValue("encryptedPassword",

		passwordEncoder.encode(password));

		jdbc.update(query, parameters);


		}
	
	public void addRole(long userId, long roleId) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "insert into user_role (userId, roleId)"

		+ "values (:userId, :roleId);";

		parameters.addValue("userId", userId);

		parameters.addValue("roleId", roleId);

		jdbc.update(query, parameters);

		}
	
	  
    public void generateJobs() {

    	for(int i = 1; i <=2; i++) {

addDummyJobs(new DummyJob("65403"+i,"RBC BANK","Software Developer","Full Time","OPEN"),
		new DummyJob("65401"+i,"IBM","Data Analysis","Full Time","OPEN"),
		new DummyJob("65405"+i,"Metro Logistic","Web Developer","Full Time","OPEN"),
		new DummyJob("65407"+i,"City Of Brampton","Web Designer","Part Time","OPEN"),
		new DummyJob("65409"+i,"CIBC","Full Stack Developer","Full Time","OPEN"));

    	}

    	}


//    public void generateJobs2() {
//    	for(int i = 1; i <=30; i++) {
//addDummyJobs(new DummyJob("65401"+i,"RBC BANK","Software Developer","Full Time","OPEN"));}}
//    public void generateJobs3() {
//    	for(int i = 1; i <=30; i++) {
//addDummyJobs(new DummyJob("65401"+i,"RBC BANK","Software Developer","Full Time","OPEN"));}}
//    public void generateJobs4() {
//    	for(int i = 1; i <=30; i++) {
//addDummyJobs(new DummyJob("65401"+i,"RBC BANK","Software Developer","Full Time","OPEN"));}}
//    public void generateJobs5() {
//    	for(int i = 1; i <=30; i++) {
//addDummyJobs(new DummyJob("65401"+i,"RBC BANK","Software Developer","Full Time","OPEN"));}} 
    
    
    
    public void addDummyJobs(DummyJob j1,DummyJob j2,DummyJob j3,DummyJob j4,DummyJob j5) {

    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	
 
	String query="INSERT INTO job (jobid,company,position,ptype,status) VALUES "+
			"(:jobid, :company, :position, :ptype, :status)";
    		
    			parameters.addValue("jobid", j1.getJobid());
    			parameters.addValue("company", j1.getCompany());
    			parameters.addValue("position", j1.getPosition());
    			parameters.addValue("ptype",j1.getPtype());
    			parameters.addValue("status",j1.getStatus());
    	     jdbc.update(query, parameters);
    	     

 			parameters.addValue("jobid", j2.getJobid());
 			parameters.addValue("company", j2.getCompany());
 			parameters.addValue("position", j2.getPosition());
 			parameters.addValue("ptype",j2.getPtype());
 			parameters.addValue("status",j2.getStatus());
 	     jdbc.update(query, parameters);
 	     

			parameters.addValue("jobid", j3.getJobid());
			parameters.addValue("company", j3.getCompany());
			parameters.addValue("position", j3.getPosition());
			parameters.addValue("ptype",j3.getPtype());
			parameters.addValue("status",j3.getStatus());
	     jdbc.update(query, parameters);
	     

			parameters.addValue("jobid", j4.getJobid());
			parameters.addValue("company", j4.getCompany());
			parameters.addValue("position", j4.getPosition());
			parameters.addValue("ptype",j4.getPtype());
			parameters.addValue("status",j4.getStatus());
	     jdbc.update(query, parameters);
	     

			parameters.addValue("jobid", j5.getJobid());
			parameters.addValue("company", j5.getCompany());
			parameters.addValue("position", j5.getPosition());
			parameters.addValue("ptype",j5.getPtype());
			parameters.addValue("status",j5.getStatus());
	     jdbc.update(query, parameters);
    
    }
}
