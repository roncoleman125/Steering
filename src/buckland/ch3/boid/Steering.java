/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buckland.ch3.boid;

import buckland.ch3.GameWorld;
import static buckland.ch3.ParamLoader.Prm;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roncoleman
 */
public class Steering {
    private static Constructor<SteeringBehavior> m_SteeringConstructor = null;
    
    public static SteeringBehavior GetInstance(Vehicle pVehicle) {
        if(m_SteeringConstructor != null) {
            SteeringBehavior pSteeringBehavior = null;
            try {
                pSteeringBehavior = m_SteeringConstructor.newInstance(pVehicle);
            } catch (InstantiationException ex) {
                Logger.getLogger(Steering.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Steering.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Steering.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Steering.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return pSteeringBehavior;
        }
        
                //set up the steering behavior class
        String className = Prm.SteeringBehaviorClass;
        
        // Reflectively create a prey
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
