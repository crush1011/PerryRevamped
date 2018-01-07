package org.usfirst.frc.team1011.robot.Subsystems;

import org.usfirst.frc.team1011.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jerry.code.SysObj;
import jerry.code.Systems;
import rc.CastroOrnelas.FRC.SubSystem;

public class Climber implements SubSystem {

	SpeedController winch1, winch2;
	boolean scaling = false;
	private static Climber instance;

	public Climber(SpeedController winch1, SpeedController winch2) {
		this.winch1 = winch1;
		this.winch2 = winch2;
	}

	public static void instantiate() {
		Climber climber = new Climber(
				(SpeedController) Systems.getInstance().getHashMap().get(SysObj.MotorController.WINCH_1),
				(SpeedController) Systems.getInstance().getHashMap().get(SysObj.MotorController.WINCH_2));
		instance = climber;
	}

	public void setWinch(boolean b) {
		if (b) {
			winch1.set(1);
			winch2.set(1);
			scaling = true;

		} else {
			winch1.set(0);
			winch2.set(0);
			scaling = false;
		}
	}

	public static Climber getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instantiate();
			return instance;
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
		SmartDashboard.putString("DB/String 0", "Scaling: " + scaling);
	}

	@Override
	public void startLoops() {
		// TODO Auto-generated method stub

	}

}
