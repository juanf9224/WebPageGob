/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.dao;

import gob.gobernacionsd.entities.Department;
import gob.gobernacionsd.entities.UserInfo;
import java.util.List;

/**
 *
 * @author juanf_000
 */
public interface UserDAO extends GenericDAO<UserInfo> {

    public List<Department> findDepartment();
}

