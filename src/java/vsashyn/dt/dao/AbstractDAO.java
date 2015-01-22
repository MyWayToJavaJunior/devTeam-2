/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.util.List;

/**
 *
 * @author vsa
 */
public  abstract class AbstractDAO <K, Entity> {
    public abstract List<Entity> findAll();
    public abstract Entity findEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(Entity entity);
    public abstract Entity update(Entity entity);
}
