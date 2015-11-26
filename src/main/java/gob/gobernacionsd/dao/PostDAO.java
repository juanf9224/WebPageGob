/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.dao;

import gob.gobernacionsd.entities.LoginInfo;
import gob.gobernacionsd.entities.Post;
import java.util.List;

/**
 *
 * @author juanf_000
 */
public interface PostDAO extends GenericDAO<Post>{
    public long findUser(String username);
}
