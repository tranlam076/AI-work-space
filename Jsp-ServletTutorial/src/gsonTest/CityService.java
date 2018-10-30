package gsonTest;

import java.util.List;

public class CityService implements ICityService {
    
    ICityDao cityDao;
    
    public CityService() {
        
        cityDao = new CityDao();
    }

    @Override
    public List<City> getCities() {

        return cityDao.findAll();
    }
}