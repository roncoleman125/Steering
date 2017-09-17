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
import buckland.ch3.common.D2.Vector2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory for creating vehicles.
 * @author Ron Coleman
 */
public class VehicleFactory {
    protected static Constructor<Vehicle> PreyConstructor = null;
    protected static Constructor<Vehicle> PredatorConstructor = null;

    /**
     * Reflectively creates a prey at spawn position based on params.ini setting.
     * See http://bit.ly/2eWDKYT≥
     * @param SpawnPos Spawn position
     * @return Instance of a prey
     */
    public static Vehicle GetPreyAt(Vector2D SpawnPos) {
        if(PreyConstructor != null)
            try {
                return (Vehicle) PreyConstructor.newInstance(SpawnPos);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String className = Prm.PreyClass;
        
        // Reflectively create a prey
        // See http://bit.ly/2eWDKYT
        Class<Vehicle> clazz;
        try {
            clazz = (Class<Vehicle>) Class.forName(className);
            
            PreyConstructor = clazz.getConstructor(Vector2D.class);
            
            return GetPreyAt(SpawnPos);
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
    
    /**
     * Reflectively creates a predator at spawn position based on params.ini setting.
     * See http://bit.ly/2eWDKYT≥
     * @param SpawnPos Spawn position
     * @return Instance of a predator
     */    
   public static Vehicle GetPredatorAt(Vector2D SpawnPos) {
        if(PredatorConstructor != null)
            try {
                return (Vehicle) PredatorConstructor.newInstance(SpawnPos);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String className = Prm.PredatorClass;

        Class<Vehicle> clazz;
        try {
            clazz = (Class<Vehicle>) Class.forName(className);
            
            PredatorConstructor = clazz.getConstructor(Vector2D.class);
            
            return GetPredatorAt(SpawnPos);
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
