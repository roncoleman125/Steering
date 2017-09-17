/*
LICENSE
=======
Excluding parts with their own licences, all source code are distributed
with MIT License:

Copyright (c) <2012> <Petr Bilek, http://www.sallyx.org/game-ai/>

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/
package buckland.ch3.boid;

import buckland.ch3.GameWorld;
import static buckland.ch3.ParamLoader.Prm;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory for generating steering behaviors.
 * @author Ron Coleman
 */
public class SteeringFactory {
    private static Constructor<SteeringBehavior> m_PreySteeringConstructor = null;
    private static Constructor<SteeringBehavior> m_PredatorSteeringConstructor = null;
    /**
     * Gets an instance of steering behavior.
     * @param pVehicle For this vehicle
     * @return Steering behavior
     */
    public static SteeringBehavior GetInstance(Vehicle pVehicle) {
        if(m_PreySteeringConstructor != null && pVehicle instanceof Prey) {
            SteeringBehavior pSteeringBehavior = null;
            
            try {
                pSteeringBehavior = m_PreySteeringConstructor.newInstance(pVehicle);
            } catch (InstantiationException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return pSteeringBehavior;
        }
        else if(m_PredatorSteeringConstructor != null && pVehicle instanceof Predator) {
            SteeringBehavior pSteeringBehavior = null;
            
            try {
                pSteeringBehavior = m_PredatorSteeringConstructor.newInstance(pVehicle);
            } catch (InstantiationException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(SteeringFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return pSteeringBehavior;            
        }            
        
        // Get the steering class configuration
        String className;
        if(pVehicle instanceof Prey)
            className = Prm.PreySteering;
        
        else if(pVehicle instanceof Predator)
            className = Prm.PredatorSteering;
        
        else
            return null;
        
        // Reflectively create a steering behavior
        // See http://bit.ly/2eWDKYT
        Class<SteeringBehavior> clazz;
        try {
            clazz = (Class<SteeringBehavior>) Class.forName(className);
            if(pVehicle instanceof Prey)
                m_PreySteeringConstructor = clazz.getConstructor(Vehicle.class);
            
            else if(pVehicle instanceof Predator)
                m_PredatorSteeringConstructor = clazz.getConstructor(Vehicle.class);
            
            return GetInstance(pVehicle);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
