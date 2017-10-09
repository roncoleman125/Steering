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
package buckland.ch3.boid;

import buckland.ch3.GameWorld;
import static buckland.ch3.ParamLoader.Prm;
import buckland.ch3.common.D2.Vector2D;
import static buckland.ch3.common.misc.utils.RandFloat;
import static buckland.ch3.common.misc.utils.TwoPi;

/**
 * This class implements a basic predator as vehicle found in Buckland (2005)
 * @author Ron Coleman
 */
public class Predator extends Vehicle {
    /**
     * Constructor
     * @param spawnPos Agent spawn position.
     */
    public Predator(Vector2D spawnPos) {
        super(GameWorld.GetInstance(),
              spawnPos,                    
              RandFloat() * TwoPi,      // Start rotation
              new Vector2D(0, 0),       // Velocity
              Prm.VehicleMass,          // Mass
              Prm.MaxSteeringForce,     // Max force
              Prm.MaxSpeed,             // Max velocity
              Prm.MaxTurnRatePerSecond, // Max turn rate
              Prm.VehicleScale          // Vehicle size
        );
        
        Init();
    }
    
    /**
     * Initializes the agent.
     */    
    protected final void Init() {
        this.Steering().FlockingOff();
   
        int scale = Prm.PredatorScale;
        
        this.SetScale(new Vector2D(scale, scale));
            
        this.Steering().WanderOn();
            
        this.SetMaxSpeed(70);   
    }
        
}
