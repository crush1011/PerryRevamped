/*
 * Class: Systems
 * Author: Jeremiah Hanson
 * ----------------------------------------------------------------------------
 * Purpose: The purpose of this class is to create a hashmap that stores all of
 * objects that represent the various systems and sysObjects on the robot, such
 * as solenoids and speed-controllers 
 * 
 * Responsibilities: Responsible for maintaining the systems so that no class 
 * should have to create their own system objects but instead ask this class 
 * for those objects.
 */

package jerry.code;

import java.util.HashMap;

import org.usfirst.frc.team1011.robot.Controls;
import org.usfirst.frc.team1011.robot.Subsystems.Climber;
import org.usfirst.frc.team1011.robot.Subsystems.Drive;
import org.usfirst.frc.team1011.robot.Subsystems.GearCollector;
import org.usfirst.frc.team1011.robot.Subsystems.LEDs;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import rc.CastroOrnelas.FRC.RNavX;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import vison.VisionLoop;

public class Systems {
	
	private static Systems systems;
	HashMap<SysObj, Object> sysObjects;
	
	public Controls controls;
	public Drive drive;
	public Compressor compressor;
	public GearCollector gearCollector;
	public Climber climber;
	public LEDs leds;
	
	public static final double COLLECTOR_P = 0.015;
	public static final double COLLECTOR_I = 0;
	public static final double COLLECTOR_D = 0;
	
	//3.9 is wheel width
	public static final double TICK_TO_IN = ((256) / (3.9 * Math.PI));
	// Private constructor that is used once to create all the subsystem objects
	private Systems(){

		sysObjects = new HashMap();
		
		//constants
		sysObjects.put(SysObj.Constants.COLLECTOR_PICKUP, new Double(-10));
		sysObjects.put(SysObj.Constants.COLLECTOR_SCORING, new Double(3));
		sysObjects.put(SysObj.Constants.COLLECTOR_CARRYING, new Double(90));
		sysObjects.put(SysObj.Constants.COLLECTOR_POTTO90, new Double(75*(66/18)));
		sysObjects.put(SysObj.Constants.COLLECTOR_GIVEFACTOR, new Double(0.03));
		
		//driveTrain Constants
		sysObjects.put(SysObj.Constants.DRIVETRAIN_TICKTOIN, new Double(-20));
		
		
		// Solenoids
		sysObjects.put(SysObj.Solenoid.COLLECTOR_ANGLE, new Solenoid(7));
		sysObjects.put(SysObj.Solenoid.COLLECTOR_CLAMP, new Solenoid(3));
		sysObjects.put(SysObj.Solenoid.LEDS, new Solenoid(4));
		
		// Left Motor Controllers
		sysObjects.put(SysObj.MotorController.LEFT_1, new CANTalon(2));
		sysObjects.put(SysObj.MotorController.LEFT_2, new CANTalon(4));
		sysObjects.put(SysObj.MotorController.LEFT_3, new CANTalon(6));
		
		// Right Motor Controllers
		sysObjects.put(SysObj.MotorController.RIGHT_1, new CANTalon(1));
		sysObjects.put(SysObj.MotorController.RIGHT_2, new CANTalon(3));
		sysObjects.put(SysObj.MotorController.RIGHT_3, new CANTalon(5));
		
		// Arm Motor Controllers
		sysObjects.put(SysObj.MotorController.GEAR_ARM, new CANTalon(0));
		sysObjects.put(SysObj.MotorController.WINCH_1, new Spark(6));
		sysObjects.put(SysObj.MotorController.WINCH_2, new Spark(5));
		
		// Sensors
		sysObjects.put(SysObj.Sensors.DRIVER_STICK, new Joystick(0));
		sysObjects.put(SysObj.Sensors.OPERATOR_STICK, new Joystick(1));
		((Joystick)sysObjects.get(SysObj.Sensors.OPERATOR_STICK)).setRumble(RumbleType.kLeftRumble, 0);
		((Joystick)sysObjects.get(SysObj.Sensors.OPERATOR_STICK)).setRumble(RumbleType.kRightRumble, 0);
		sysObjects.put(SysObj.Sensors.NAVX, new RNavX(new AHRS(SPI.Port.kMXP)));
		sysObjects.put(SysObj.Sensors.PDP, new PowerDistributionPanel(0));
		
		// Encoders
		sysObjects.put(SysObj.Sensors.SCALING_ENCODER, new Encoder(8,9));
		sysObjects.put(SysObj.Sensors.ARM_ENCODER, new Encoder(4,5));
		sysObjects.put(SysObj.Sensors.LEFT_ENCODER, new Encoder(2,3));
		sysObjects.put(SysObj.Sensors.RIGHT_ENCODER, new Encoder(1,0));
		
		// Camera
		UsbCamera usbCamera;
		CameraServer cameraServer = CameraServer.getInstance();
		usbCamera = cameraServer.startAutomaticCapture();
		usbCamera.setBrightness(10);
		usbCamera.setResolution(320, 240);
		usbCamera.setFPS(15);
		usbCamera.setExposureManual(1);
		CameraServer.getInstance().startAutomaticCapture().setFPS(10);
		
		CvSink cvSink;
		cvSink = cameraServer.getVideo();
		sysObjects.put(SysObj.Sensors.VISION, new VisionLoop(cvSink));
		
		
		

	}
	
	public void initiateSubsystems(){
		
		controls = new Controls((Joystick)sysObjects.get(SysObj.Sensors.DRIVER_STICK),
				(Joystick)sysObjects.get(SysObj.Sensors.OPERATOR_STICK));
				
		climber = Climber.getInstance();
		drive = Drive.getInstance();
		gearCollector = GearCollector.getInstance();
		leds = LEDs.getInstance();
		compressor = new Compressor(0);
	}
	
	
	// This returns the instance of Systems, or creates the instnace if there is
	// none.
	public static Systems getInstance(){
		
		if (systems == null) {
			systems = new Systems();

		}
		return systems;
		
	}
	
	// returns the hashmap containing all the sysObjects
	public HashMap<SysObj, Object> getHashMap() {
		return sysObjects;
	}
	

}
