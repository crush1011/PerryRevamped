package org.usfirst.frc.team1011.robot.Subsystems;

import java.util.function.Function;

import org.usfirst.frc.team1011.robot.Robot;

import com.ctre.CANTalon;
/*
 * Class: GearCollector
 * Author: Ethan Ngo and Ruben Castro
 * ----------------------------------------------------------------------------
 * Purpose: This class is for a Gear Collector Subsystem. It will easily switch between 
 * different angles and positions.
 */

//imports
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jerry.code.SysObj;
import jerry.code.Systems;
import rc.CastroOrnelas.FRC.Loop;
import rc.CastroOrnelas.FRC.REncoder;
import rc.CastroOrnelas.FRC.Resources;

import static rc.CastroOrnelas.FRC.Resources.*;
import rc.CastroOrnelas.FRC.SubSystem;
import rc.CastrooOrnelas.datatypes.RPID;

public class GearCollector implements SubSystem {
	// instantiation of variables
	private RPID anglePID;
	
	private double motorOutput;

	private SpeedController angleMotor;
	private REncoder encoder;

	private boolean invertedMotor;
	private double angle;
	public double POTSTART, POTTO90, k;
	public boolean PIDEnabled;
	public boolean PotEnabled;
	public double manualMotorOutput = 0;
	private boolean beingUsed = false;
	public double displacement = 0;

	// collector head variables
	private Solenoid angleSolenoid, clampSolenoid;
	private boolean angleON = false, clampON = false;

	private Loop collectorLoop;

	private static GearCollector instance;

	// create Gear Collector instance
	public static void instantiate() {

		// Potentiometer p = (Potentiometer)
		// resources.getSensor("GearCollectorPotentiometer");
		Encoder e = (Encoder) Systems.getInstance().getHashMap().get(SysObj.Sensors.ARM_ENCODER);
		SpeedController sc = (SpeedController) Systems.getInstance().getHashMap().get(SysObj.MotorController.GEAR_ARM);
		GearCollector gc;
		Solenoid angleSol = (Solenoid) Systems.getInstance().getHashMap().get(SysObj.Solenoid.COLLECTOR_ANGLE);
		Solenoid clampSol = (Solenoid) Systems.getInstance().getHashMap().get(SysObj.Solenoid.COLLECTOR_CLAMP);

		if (e != null) {
			gc = new GearCollector(sc, true, e, e.getDistance(),
					(Double) Systems.getInstance().getHashMap().get(SysObj.Constants.COLLECTOR_POTTO90),
					(Double) Systems.getInstance().getHashMap().get(SysObj.Constants.COLLECTOR_GIVEFACTOR), angleSol,
					clampSol);

			gc.setAngle(90);
			gc.startLoops();
			instance = gc;
		}
	}

	public static GearCollector getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instantiate();
			return instance;
		}
	}

	// possible collector states
	public enum GCAState {
		BEGINNING(90), SCORING(7), PICKUP(-10);

		private int angle;

		GCAState(int angle) {
			this.angle = angle;
		}

		public int getAngle() {
			return angle;
		}
	}

	// constructor for collector angles
	public GearCollector(SpeedController angleMotor, boolean invertedMotor, Encoder e, double POTSTART, double POTTO90,
			double giveConstant, Solenoid angleSolenoid, Solenoid clampSolenoid) {
		this.angleMotor = angleMotor;
		this.POTSTART = POTSTART;
		this.POTTO90 = POTTO90;
		this.k = giveConstant;
		this.invertedMotor = invertedMotor;
		Function<Double, Double> conversion = (val) -> {
			double current = 0;
			double potValue = val.doubleValue();

			current = potValue / POTTO90;
			current = current * 90;
			current = 90 + current + displacement * 5;

			return current;
		};

		this.encoder = new REncoder(e, conversion);
		anglePID = new RPID(Systems.COLLECTOR_P, Systems.COLLECTOR_I, Systems.COLLECTOR_D);
		anglePID.setOutputRange(-180, 180);
		anglePID.setAcceptableRange(2);
		angle = 90;
		PotEnabled = true;
		PIDEnabled = true;
		this.angleSolenoid = angleSolenoid;
		this.clampSolenoid = clampSolenoid;
	}

	@Override
	public void test() {

	}

	@Override
	public void log() {

	}

	@Override
	public void writeToSmartDashboard() {
		SmartDashboard.putString("DB/String 2", "GCA Angle: " + encoder.getValue());
		SmartDashboard.putString("DB/String 3","SET:" + anglePID.getSetPoint());
		SmartDashboard.putNumber("DB/Slider 3",motorOutput);
		SmartDashboard.putString("DB/String 4" , "CL:" + clampON + "AN:" +angleON);
	}

	public void setAngle(int angle) {
		if (!beingUsed) {
			anglePID.setSetPoint(angle);
			this.angle = angle;
		}
	}

	public void setMode(String mode) {
		switch (mode) {
		// PID with give
		case "PID":
			PIDEnabled = true;
			PotEnabled = true;
			// ((CANTalon) angleMotor).enableBrakeMode(false);
			break;
		// Give only
		case "Pot":
			PIDEnabled = false;
			PotEnabled = true;
			((CANTalon) angleMotor).enableBrakeMode(false);
			break;
		// Completely manual
		case "Manual":
			PIDEnabled = false;
			PotEnabled = false;
			((CANTalon) angleMotor).enableBrakeMode(true);
			break;

		}

	}

	public double getAngleSetpoint() {
		return angle;
	}

	public void setState(GCAState gcas) {
		anglePID.setSetPoint(gcas.getAngle());
		this.angle = gcas.getAngle();
		if (gcas == GCAState.BEGINNING) {
			toggleClampSolenoid(true);
			toggleAngleSolenoid(false);
		} else if (gcas == GCAState.PICKUP) {
			toggleClampSolenoid(false);
			toggleAngleSolenoid(true);
		} else if (gcas == GCAState.SCORING) {
			toggleClampSolenoid(true);
			toggleAngleSolenoid(false);
		}

	}

	public double getAngleRadians() {
		return (encoder.getValue() * Math.PI) / 180;
	}

	public boolean used() {
		return beingUsed;
	}
	/*
	 * amount of give that the collector outputs to keep the collector from
	 * falling due to gravity
	 */

	private double calculateGive(double angle) {
		return Math.cos(angle) * k;
	}

	// enable loops
	@Override
	public void startLoops() {
		if (collectorLoop != null) {
			collectorLoop.kill();
		}
		Runnable collectorLoopRunnable = () -> {
			double PIDOutput = anglePID.crunch(encoder.getValue());
			double motorOutput = PIDOutput;
			double give = calculateGive(getAngleRadians());
			if (encoder.getValue() > 80 && motorOutput > 0.2) {
				motorOutput = 0.1;
			}
			if (encoder.getValue() < -20 && motorOutput < -0.3) {
				motorOutput = -0.2;
			}
			
			if (PotEnabled && PIDEnabled) {

				angleMotor.set(
						invertedMotor ? -limit(give + motorOutput, -1.0, 1.0) : limit(give + motorOutput, -1.0, 1.0));
			} else if (PotEnabled) {

				angleMotor.set(invertedMotor ? -limit(give, -1.0, 1.0) : limit(give, -1.0, 1.0));
			} else {
				angleMotor.set(invertedMotor ? -limit(manualMotorOutput + 0.06, -1.0, 1.0)
						: limit(manualMotorOutput + 0.06, -1.0, 1.0));
			}
			this.motorOutput=motorOutput + give;
		};
		collectorLoop = new Loop(collectorLoopRunnable, 50);
		collectorLoop.start();
	}

	public void collectingPositionCylinders() {
		toggleAngleSolenoid(true);
		toggleClampSolenoid(false);
	}

	public void scoringPositionCylinders() {
		toggleAngleSolenoid(false);
		toggleClampSolenoid(true);
	}

	public void toggleAngleSolenoid() {
		toggleAngleSolenoid(!angleON);
		angleON = !angleON;
	}

	// SWITCH THIS
	public void toggleAngleSolenoid(boolean state) {
		angleSolenoid.set(state);
		angleON = state;
	}

	//remember clamp is backwards, so this might look weird
	public void toggleClampSolenoid() {
		toggleClampSolenoid(clampON);
		clampON = !clampON;
	}

	public void toggleClampSolenoid(boolean state) {
		clampSolenoid.set(!state);
		clampON = state;
	}

}
