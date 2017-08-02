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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory for generating steering behaviors.
 * @author Ron Coleman
 */
public class SteeringFactory {
    private static Constructor<SteeringBehavior> m_SteeringConstructor = null;
    
    /**
     * Gets an instance of steering behavior.
     * @param pVehicle For this vehicle
     * @return Steering behavior
     */
    public static SteeringBehavior GetInstance(Vehicle pVehicle) {
        if(m_SteeringConstructor != null) {
            SteeringBehavior pSteeringBehavior = null;
            
            try {
                pSteeringBehavior = m_SteeringConstructor.newInstance(pVehicle);
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
        String className = Prm.SteeringBehaviorClass;
        
        // Reflectively create a steering behavior
        // See http://bit.ly/2eWDKYT
        Class<SteeringBehavior> clazz;
        try {
            clazz = (Class<SteeringBehavior>) Class.forName(className);
            
            m_SteeringConstructor = clazz.getConstructor(Vehicle.class);
            
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
