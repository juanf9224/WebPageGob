/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Administrator
 */
@Named
public class MapModelBean implements Serializable{
    private MapModel simpleModel;
  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(18.487391, -69.829329);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Gobernacion provincial de Santo domingo"));
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
