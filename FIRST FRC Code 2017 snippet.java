/**
 * This code is the code made for the 2017 FRIST FRC. This team is 6423. 
 * Authors: Rohan Rajagopalan, Eddie Yan, Chukwudumebi Joshua Obi
 * Please open this in eclipse and make sure you have java installed. 
 */



package org.usfirst.frc.team6423.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	//gyro and Kp will help us with drving the robot straight
//	private static final double kAngleSetpoint = 0.0;
//  private static final double kP = 0.005;
//	private static final int kGyroPort = 0;
	// private static final double kVoltsPerDegreePerSecond = 0.0128;
	// private AnalogGyro gyro = new AnalogGyro(kGyroPort);
	
	RobotDrive myDrive;
	Spark frontLeft, frontRight, rearLeft, rearRight;
	static TalonSRX ropeClimber;
	Joystick leftStick, rightStick; 
	JoystickButton ropeUp;
	JoystickButton accelerate;
	static Timer timer;
	// new thing VV
	static boolean fast = false;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		//SmartDashboard.putData("Auto choices", chooser);
		//Gyro sensitivity value
		//gyro.setSensitivity(kVoltsPerDegreePerSecond);
		
		// Driving Objects
		frontLeft = new Spark(1);
		frontRight = new Spark(3);
		rearLeft = new Spark(0);
		rearRight = new Spark(2);
		
		frontLeft.setSpeed(0.7);
		frontLeft.enableDeadbandElimination(true);
		frontRight.setSpeed(0.7);
		frontRight.enableDeadbandElimination(true);
		rearLeft.setSpeed(0.7);
		rearLeft.enableDeadbandElimination(true);
		rearRight.setSpeed(0.7);
		rearRight.enableDeadbandElimination(true);
		
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		
		ropeUp = new JoystickButton(rightStick, 1);
		accelerate = new JoystickButton(leftStick,0);
		
		ropeClimber = new TalonSRX(4);
		ropeClimber.setSpeed(0);
		ropeClimber.enableDeadbandElimination(true);
		
		timer = new Timer();
		
		myDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		myDrive.setSensitivity(0.7);
		//In this is particular form we have selected the camera the webdashbord has named called camo0. We have set its port to 0 (Java does this automatically but we're going to do it manuallly) 
		
		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().startAutomaticCapture(); 
	
		myDrive.setMaxOutput(.4);
		myDrive.setExpiration(0.1);
		
		// Autonomous code. Decide whether or not to delete method below
		/*Scheduler.getInstance().run();
		
		switch (autoSelected) 
		{
		case customAuto:
			// Put custom auto code here... i.e. for left
			
			break;
		case defaultAuto:
			// for right
			break;
		default:
			// Put default auto code here...i.e. for middle
			break;
		}
		
		**/
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */

	@Override
	
	public void autonomousInit() 
	{
	
		myDrive.setSafetyEnabled(true);
		 	
	
		
		    timer.reset();

		    // Start timer
		    timer.start();
				
		
		//autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		//System.out.println("Auto selected: " + autoSelected);
		
		//while(isAutonomous() && isEnabled()){
			
		//}
		
	}
	
	
	//This function is called periodically during autonomous
	 
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		//double turningValue = (kAngleSetpoint - gyro.getAngle()) * kP;

		while (isAutonomous() && isEnabled())
		{
			// > 0 turns right... < 0 turns left
			//middle 
			/*
			if(timer.get() < 2.0)
			{
				myDrive.drive(-.5, 0.0);
			}
			else 
			{
				myDrive.drive(0.0, 0.0);
				timer.stop();
			}	
			*/
			/*
			// right side... drives straight
			if(timer.get() < 5.0)
			{
				myDrive.drive(-.5, 0.0);  
			}
			else
			{
				timer.stop();
				myDrive.drive(0.0, 0.0);
			}
			*/
			// left side code... turns around
			
			if (timer.get() < 6.0)
			{
				myDrive.drive(-.5, 0.0);
			}
			else if (timer.get() >= 6.0 && timer.get() < 9.8)
			{
				myDrive.drive(-.5, -1.0);
			}
			else if (timer.get() >= 9.8 && timer.get() < 10.8)
			{
				myDrive.drive(-.5, 0.0);
			}
			else
			{
				timer.stop();
				myDrive.drive(0.0, 0.0);
			}
			
			
			
		}
		/*switch (autoSelected) 
		{
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
		*/
		
	}
	
	@Override
	public void teleopInit() {
		
		Scheduler.getInstance().run();
		//frontLeft.set(.5);
		//frontLeft.enableDeadbandElimination(true);
		//frontRight.set(.5);
		//frontRight.enableDeadbandElimination(true);
		//rearLeft.set(-.5);
		//rearLeft.enableDeadbandElimination(true);
		//rearRight.set(-.5);
		//rearRight.enableDeadbandElimination(true);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		while (isOperatorControl() && isEnabled())
		{
			myDrive.setSafetyEnabled(false);
			myDrive.tankDrive(leftStick, rightStick);
			
			buttonSlowDown(accelerate);
			Timer.delay(.005);
			ropeClimb(ropeUp);
			
		}
	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
		
	}
	
	public static void ropeClimb(JoystickButton button1)
	{
		while(button1.get())
		{
			ropeClimber.set(-1);
		}
		ropeClimber.set(0);
	}
	
	public void buttonSlowDown (JoystickButton button2)
	{
		while( button2.get() ) {  
			
			frontLeft.setSpeed(0.4);
			frontLeft.enableDeadbandElimination(true);
			frontRight.setSpeed(0.4);
			frontRight.enableDeadbandElimination(true);
			rearLeft.setSpeed(0.4);
			rearLeft.enableDeadbandElimination(true);
			rearRight.setSpeed(0.4);
			rearRight.enableDeadbandElimination(true);
		}
	}    
//This is the method we will implement during teleoperiodic, it will log values to smartdashbaord.

    }
