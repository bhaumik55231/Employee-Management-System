package serviceLayer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.*;
public class cronJob implements Job{
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		System.out.println("Today's date" +new Date());
	}
}
