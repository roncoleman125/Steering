/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import buckland.ch3.GameWorld;
import buckland.ch3.boid.SteeringBehavior;
import buckland.ch3.boid.Vehicle;
import buckland.ch3.boid.VehicleFactory;
import buckland.ch3.common.D2.Vector2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roncoleman
 */
public class Seek01 {
    
    public Seek01() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void test() {
         // This statement is necessary to ensure the world initialized properly
         // for prey to access the world.
         GameWorld g_GameWorld = new GameWorld(500, 500);
         
         Vehicle Prey = VehicleFactory.GetPreyAt(new Vector2D(25, 10));
         
         Prey.SetVelocity(new Vector2D(50,100));
         
         Vector2D TargetPos = new Vector2D(100,75);
         
         SteeringBehavior Behavior = new SteeringBehavior(Prey);
         
         Vector2D Acceleration = Behavior.Seek(TargetPos);
         
         System.out.println(Acceleration);
     }
}
