/*
 Copyright (c) Ron Coleman

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
 * This test case tests fleeing under more controlled conditions.
 * @author Ron Coleman
 */
public class Flee01 {
    
    /**
     * Constructor
     */
    public Flee01() {
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
         
         Vector2D Acceleration = Behavior.Flee(TargetPos);
         
         System.out.println(Acceleration);
     }
}
