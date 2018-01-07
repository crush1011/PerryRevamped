package org.usfirst.frc.team1011.robot;

import java.util.HashMap;

import org.usfirst.frc.team1011.robot.Controls.ControlAxis;
import org.usfirst.frc.team1011.robot.Controls.ControlButtons;
import org.usfirst.frc.team1011.robot.Subsystems.Climber;
import org.usfirst.frc.team1011.robot.Subsystems.Drive;
import org.usfirst.frc.team1011.robot.Subsystems.GearCollector;
import org.usfirst.frc.team1011.robot.Subsystems.GearCollector.GCAState;
import org.usfirst.frc.team1011.robot.Subsystems.LEDs;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jerry.code.SysObj;
import jerry.code.Systems;
import rc.CastroOrnelas.FRC.Loop;
import rc.CastroOrnelas.FRC.RNavX;
import rc.CastrooOrnelas.FRC.TrajectoryInterpreter;

public class Robot extends IterativeRobot {

	Controls controls;
	
	GearCollector gearCollector;
	Climber climber;
	LEDs leds;
	Drive drive;
	Compressor compressor;
	RNavX navX;
	Systems robotSystems;
	
	Loop periodic;
	
	TrajectoryThang tt;
	
	public void robotInit() {
		//Config.init();
		robotSystems = Systems.getInstance();
		robotSystems.initiateSubsystems();
		controls = robotSystems.controls;
		climber = robotSystems.climber;
		leds = robotSystems.leds;
		drive = robotSystems.drive;
		gearCollector = robotSystems.gearCollector;
		compressor = robotSystems.compressor;
		navX = (RNavX) robotSystems.getHashMap().get(SysObj.Sensors.NAVX);
		
		periodic = new Loop(this::periodic , 5);
		periodic.start();
		
		tt = new TrajectoryThang();
	}
	
	@Override
	public void autonomousInit(){
		TrajectoryInterpreter ti = new TrajectoryInterpreter(drive.getRDriveTrain(), tt.trajectories,new HashMap<String, Runnable>());
		ti.run();
	}
	
	@Override
	public void teleopPeriodic(){
		//gearCollector.writeToSmartDashboard();
		//climber.writeToSmartDashboard();
		//drive.writeToSmartDashboard();
		
		controls.update();
		if(controls.operatorAxis.get(ControlAxis.RIGHT_TRIGGER)>0.5){
			gearCollector.setState(GCAState.SCORING);
		}else if(controls.operatorAxis.get(ControlAxis.LEFT_TRIGGER)>0.5){
			gearCollector.setState(GCAState.PICKUP);
		}else if(controls.operatorButtons.get(ControlButtons.A)){
			gearCollector.setState(GCAState.BEGINNING);
		}
		
		if(controls.operatorButtons.get(ControlButtons.X)){
			climber.setWinch(true);
		}else{
			climber.setWinch(false);
		}
		
		if(controls.operatorButtons.get(ControlButtons.LEFT_BUMPER)){
			leds.flashLEDS();
		}
		//slow for little children
		double constant = 0.75;
		double y = -controls.driverAxis.get(ControlAxis.LEFT_Y);
		double x = controls.driverAxis.get(ControlAxis.RIGHT_X);
		y = Math.abs(y)<0.2?0:y;
		x = Math.abs(x)<0.2?0:x;
		//.out.println(y);
		drive.arcadeDrive(constant * y,constant * -x);
		
		
	}
	
	public void disabledPeriodic(){
		
	}
	
	private void periodic(){
		if(SmartDashboard.getBoolean("DB/Button 3", false)){
			compressor.stop();
		}else{
			compressor.start();
		}
		SmartDashboard.putNumber("GyroValue", navX.getAngle());
		gearCollector.writeToSmartDashboard();
		
		//drive.getRDriveTrain().testPID(0);
	}
	
}