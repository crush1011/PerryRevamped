package org.usfirst.frc.team1011.robot;

import java.util.HashMap;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Config {
	static String autoSelected;
	static SendableChooser chooser;
	static Joystick driverJoystick, operatorJoystick;

	static Encoder armEncoder;
	static Encoder scalingEncoder;
	static AHRS navx;
	private static PowerDistributionPanel pdp;
	static UsbCamera usbCamera;
	public static Compressor compressor;

	public static void init() {
		driverJoystick = new Joystick(0);
		operatorJoystick = new Joystick(1);
		/*
		 * compressor = new Compressor(0);
		 * compressor.setClosedLoopControl(true); compressor.enabled();
		 */

		// solenoid init
		HashMap<String, Solenoid> solenoids = new HashMap<>();
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);

		solenoids.put("GearCollectorAngleSolenoid", new Solenoid(7));
		solenoids.put("GearCollectorClampSolenoid", new Solenoid(3)); // 0 p3

		// motor controller inits
		HashMap<String, SpeedController> motorControllers = new HashMap<>();
		CANTalon l1 = new CANTalon(1); // 1 p3
		motorControllers.put("l1", l1);
		CANTalon l2 = new CANTalon(3);// 3 p4
		motorControllers.put("l2", l2);
		CANTalon l3 = new CANTalon(5);// 5 p5
		motorControllers.put("l3", l3);
		CANTalon r1 = new CANTalon(2);// 2 p0
		motorControllers.put("r1", r1);
		CANTalon r2 = new CANTalon(4);// 4 p1
		motorControllers.put("r2", r2);
		CANTalon r3 = new CANTalon(6);// 6 p2
		motorControllers.put("r3", r3);
		CANTalon gearArmVictor = new CANTalon(0);
		Spark winch1 = new Spark(6);
		motorControllers.put("Winch1", winch1);
		Spark winch2 = new Spark(7);
		motorControllers.put("Winch2", winch2);

		// sensor inits
		HashMap<String, Object> sensors = new HashMap();

		sensors.put("DriverJoystick", driverJoystick);
		sensors.put("OperatorJoystick", operatorJoystick);
		operatorJoystick.setRumble(RumbleType.kLeftRumble, 0);
		operatorJoystick.setRumble(RumbleType.kRightRumble, 0);

		navx = new AHRS(SPI.Port.kMXP);
		sensors.put("navX", navx);
		pdp = new PowerDistributionPanel(0);
		sensors.put("pdp", pdp);

		sensors.put("ScalingEncoder", scalingEncoder);

		armEncoder = new Encoder(4, 5);

		sensors.put("GearArmEncoder", armEncoder);
		Encoder encoder1 = new Encoder(2, 3);
		sensors.put("LeftEncoder", encoder1);
		Encoder encoder2 = new Encoder(1, 0);
		sensors.put("RightEncoder", encoder2);

		CameraServer cameraServer = CameraServer.getInstance();
		usbCamera = cameraServer.startAutomaticCapture();
		usbCamera.setBrightness(10);
		usbCamera.setResolution(320, 240);
		usbCamera.setFPS(15);
		usbCamera.setExposureManual(1);
		CameraServer.getInstance().startAutomaticCapture().setFPS(10);
		// usbCamera.setFPS(20);

	}
}
