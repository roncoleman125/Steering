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
package buckland.ch3;

import static buckland.ch3.ParamLoader.Prm;
import buckland.ch3.common.D2.Vector2D;
import static buckland.ch3.common.misc.utils.RandFloat;
import static buckland.ch3.common.misc.utils.TwoPi;

/**
 * This class implement basic prey as vehicle found in Buckland (2005)
 * @author Ron Coleman
 */
public class Prey extends Vehicle {
    
    public Prey(Vector2D spawnPos) {
        super(GameWorld.GetInstance(),
              spawnPos,                    
              RandFloat() * TwoPi, //start rotation
              new Vector2D(0, 0), //velocity
              Prm.VehicleMass, //mass
              Prm.MaxSteeringForce, //max force
              Prm.MaxSpeed, //max velocity
              Prm.MaxTurnRatePerSecond, //max turn rate
              Prm.VehicleScale);
        
        init();
    }
    
    protected final void init() {
        this.Steering().FlockingOn();
        
        int scale = Prm.PreyScale;
        
        this.SetScale(new Vector2D(scale, scale));
    }
}
