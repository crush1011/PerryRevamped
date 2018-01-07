package org.usfirst.frc.team1011.robot.Subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import jerry.code.SysObj;
import jerry.code.Systems;
import rc.CastroOrnelas.FRC.SubSystem;

public class LEDs implements SubSystem{

	Solenoid ledsSolenoid;
	int count=0;
	boolean threadStarted;
	
	boolean flashing;
	
	private static LEDs instance;
	
	public static void instantiate(){
		LEDs leds = new LEDs((Solenoid) Systems.getInstance().getHashMap().get(SysObj.Solenoid.LEDS));
		instance=leds;
	}

	public static LEDs getInstance(){
		if(instance!=null){
			return instance;
		}else{
			instantiate();
			return instance;
		}
	}
	
	public LEDs(Solenoid solenoid){
		ledsSolenoid = solenoid;
		ledsSolenoid.set(false);
	}
	
	public void flashLEDS(){
		
		if(count==8){
			count=7;
		}else{
			count=8;
		}
		if(!threadStarted){
			threadStarted=true;
			Runnable run = new Runnable(){
				@Override
				public void run() {

					System.out.println("FLASHING");
					while(count>0){
						count--;
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(count%2==0){
							ledsSolenoid.set(false);
						}else{
							ledsSolenoid.set(true);
						}
						
					}
					ledsSolenoid.set(false);
					threadStarted=false;
				}
				
			};
			new Thread(run).start();
		}
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startLoops() {
		// TODO Auto-generated method stub
		
	}
}
