package com.jonathan.domainmodel;

import java.util.ArrayList;

/*
 *  The Journey class will make the project a bit easier initially a Vehicle shall have a journey object attached to it with the nodes that the journey will traverse 
 *  
 *  This is a will make it a bit easier without having to solve some variant of the traveling sales man problem.
 */

public class Journey {
	
	public Journey(ArrayList<Junction> route) {
		this._route = route;
		
	}
	
	
	long  _lt; // seconds from start of simulation this is when the vehicle will queue at the ingress node
	ArrayList<Junction> _route;
}
