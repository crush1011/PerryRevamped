package rc.CastroOrnelas.FRC;

public class Loop {

	private int hz;
	private long millisWaitTime;
	Runnable loopRun;
	private boolean kill = false;
	private boolean running = false;
	
	public Loop(Runnable r, int hz){
		millisWaitTime = (long) (1000/hz);
		
		loopRun = ()->{
			while(!kill){
				running=true;
				long iterationStart = System.currentTimeMillis();
				r.run();
				long delay = System.currentTimeMillis()-iterationStart;
				Resources.sleepTime(Math.max(0, millisWaitTime - delay));
			}
			running=false;
		};
	}
	
	public void kill(){
		kill=true;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void start(){
		if(!running){
			kill=false;
			new Thread(loopRun).start();
		}
	}
	
}
