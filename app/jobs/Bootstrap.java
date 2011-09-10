package jobs;

import java.security.SecureRandom;

import play.jobs.*;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {

	@Override
	public void doJob() throws Exception {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("init_fixtures.yml");
		
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		
	}
}