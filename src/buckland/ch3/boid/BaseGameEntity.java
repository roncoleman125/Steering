/*
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

import java.io.InputStream;
import buckland.ch3.common.D2.Vector2D;
import buckland.ch3.common.Messaging.Telegram;
import static buckland.ch3.common.misc.utils.*;

public abstract class BaseGameEntity {

    public static int default_entity_type = -1;
    //each entity has a unique ID
    private int m_ID = 0;
    //every entity has a type associated with it (health, troll, ammo etc)
    int m_EntityType;
    //this is a generic flag. 
    boolean m_bTag;
    //this is the next valid ID. Each time a BaseGameEntity is instantiated
    //this value is updated
    static private int NextID = 0;

    //used by the constructor to give each entity a unique ID
    private int NextValidID() {
        return NextID++;
    }

    /**
     * Use with recreating GameWord
     */
    public static void resetValidID() {
        NextID = 0;
    }
    //its location in the environment
    protected Vector2D m_vPos;
    protected Vector2D m_vScale;
    //the length of this object's bounding radius
    protected double m_dBoundingRadius;

    protected BaseGameEntity() {
        m_ID = NextValidID();
        m_dBoundingRadius = 0.0;
        m_vPos = new Vector2D();
        m_vScale = new Vector2D(1.0, 1.0);
        m_EntityType = default_entity_type;
        m_bTag = false;
    }

    protected BaseGameEntity(int entity_type) {
        m_ID = NextValidID();
        m_dBoundingRadius = 0.0;
        m_vPos = new Vector2D();
        m_vScale = new Vector2D(1.0, 1.0);
        m_EntityType = entity_type;
        m_bTag = false;
    }

    protected BaseGameEntity(int entity_type, Vector2D pos, double r) {
        m_vPos = pos;
        m_dBoundingRadius = r;
        m_ID = NextValidID();
        m_vScale = new Vector2D(1.0, 1.0);
        m_EntityType = entity_type;
        m_bTag = false;

    }

    /**
     * this can be used to create an entity with a 'forced' ID. It can be used
     * when a previously created entity has been removed and deleted from the
     * game for some reason. For example, The Raven map editor uses this ctor 
     * in its undo/redo operations. 
     * USE WITH CAUTION!
     */
    protected BaseGameEntity(int entity_type, int ForcedID) {
        m_ID = ForcedID;
        m_dBoundingRadius = 0.0;
        m_vPos = new Vector2D();
        m_vScale = new Vector2D(1.0, 1.0);
        m_EntityType = entity_type;
        m_bTag = false;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public void Update(double time_elapsed) {
    }

    public void Render() {
    }

    public boolean HandleMessage(Telegram msg) {
        return false;
    }

    //entities should be able to read/write their data to a stream
    //virtual void Write(std::ostream&  os)const{}
    @Override
    public String toString() {
        return String.format("%f %f", this.m_vPos.x, this.m_vPos.y);
    }

    //virtual void Read (std::ifstream& is){}
    public void read(InputStream in) {
    }

    public Vector2D Pos() {
        return new Vector2D(m_vPos);
    }

    public void SetPos(Vector2D new_pos) {
        m_vPos = new Vector2D(new_pos);
    }

    public double BRadius() {
        return m_dBoundingRadius;
    }

    public void SetBRadius(double r) {
        m_dBoundingRadius = r;
    }

    public int ID() {
        return m_ID;
    }

    public boolean IsTagged() {
        return m_bTag;
    }

    public void Tag() {
        m_bTag = true;
    }

    public void UnTag() {
        m_bTag = false;
    }

    public Vector2D Scale() {
        return new Vector2D(m_vScale);
    }

    public void SetScale(Vector2D val) {
        m_dBoundingRadius *= MaxOf(val.x, val.y) / MaxOf(m_vScale.x, m_vScale.y);
        m_vScale = new Vector2D(val);
    }

    public void SetScale(double val) {
        m_dBoundingRadius *= (val / MaxOf(m_vScale.x, m_vScale.y));
        m_vScale = new Vector2D(val, val);
    }

    public int EntityType() {
        return m_EntityType;
    }

    public void SetEntityType(int new_type) {
        m_EntityType = new_type;
    }
}