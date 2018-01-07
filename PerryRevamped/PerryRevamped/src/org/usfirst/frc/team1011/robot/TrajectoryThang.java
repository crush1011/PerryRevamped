package org.usfirst.frc.team1011.robot;

import java.util.ArrayList;

import AutonomousTrajectories.momentsClass;
import RealBot.Moment;
import RealBot.Trajectory;

public class TrajectoryThang {

	ArrayList<Trajectory> trajectories;
	
	public TrajectoryThang(){
		trajectories = new ArrayList<>();
		Trajectory t = new Trajectory(){

			public ArrayList<Moment> moments;
			@Override
			public ArrayList<Moment> getMoments() {
				// TODO Auto-generated method stub
				moments = new ArrayList<>();
				
				double angle=0;
				for(double[] dArr : momentsClass.objects){
					angle=dArr[3];
					moments.add(new Moment("", dArr[0], 0,0,dArr[3],dArr[1],dArr[2]));
				}
				moments.add(new Moment("",0,0,0,angle,0,0));
				
				return moments;
			}
			
		};
		trajectories.add(t);
	}
}
