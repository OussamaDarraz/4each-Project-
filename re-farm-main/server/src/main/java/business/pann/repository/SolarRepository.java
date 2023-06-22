package business.pann.repository;

import business.pann.domain.PositionChangedValues;
import business.pann.domain.PositionValues;
import business.pann.domain.SolarPanelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.LoadProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolarRepository {
    static Logger logger = LoggerFactory.getLogger(SolarRepository.class.getName());
    private final static LoadProperties props = new LoadProperties("queries.properties");

    private final Connection connection;

    public SolarRepository(Connection connection) {
        this.connection = connection;
    }

    public List<SolarPanelValue> FindObjectById(int id) {
        List<SolarPanelValue> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectAllPanel"));
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SolarPanelValue value = new SolarPanelValue(
                        rs.getInt("id_sol_panel"),
                        rs.getString("panel_name"),
                        rs.getInt("panel_energy"),
                        rs.getFloat("panel_surface"),
                        rs.getString("obj_label"),
                        rs.getString("panel_type")
                );
                result.add(value);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    public List<String> findAllObjectByLabel() {
        List<String> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(props.getProp("selectPanelPlaced"));
            while (rs.next()) {
                result.add(rs.getString("obj_label"));
            }
            logger.info("result is " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Double> GetEnergyValue() {
        List<Double> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(props.getProp("selectAllEnergie"));
            while (rs.next()) {
                result.add(rs.getDouble("panel_energy"));
            }
            logger.info("result is " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<SolarPanelValue> FindObjectByType(String type) {
        List<SolarPanelValue> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectWtypePanel"));
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SolarPanelValue value = new SolarPanelValue(
                        rs.getInt("id_sol_panel"),
                        rs.getString("panel_name"),
                        rs.getInt("panel_energy"),
                        rs.getFloat("panel_surface"),
                        rs.getString("obj_label"),
                        rs.getString("panel_type")
                );
                result.add(value);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    public List<Double> TotalEnergyByType(String type) {
        List<Double> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectEnergieWtypePanel"));
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(rs.getDouble("panel_energy"));
            }
            logger.info("result is " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }


    public List<Integer> FindIdByType(String type) {
        List<Integer> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectidbytype"));
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("id_sol_panel"));
            }
            logger.info("result is " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    public List<PositionValues> FindAllPlaces() {
        List<PositionValues> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectallpositioninfo"));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PositionValues value = new PositionValues(
                        rs.getInt("x"),
                        rs.getInt("y"),
                        rs.getInt("sunshine_lvl")
                );
                result.add(value);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    public void InsertIntoObject(String obj_label) {

        try {
            PreparedStatement statement = connection.prepareStatement(props.getProp("inserttothemap"));
            statement.setString(1,obj_label);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateaPanel ( String panel_type )
    {
        try {
            PreparedStatement statement = connection.prepareStatement(props.getProp("configidpanel"));
            statement.setString(1,panel_type);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void ConfigthePlace ( int sunshine_lvl)
    {
        try {
            PreparedStatement statement = connection.prepareStatement(props.getProp("configidpanelplace"));
            statement.setInt(1,sunshine_lvl);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<PositionChangedValues> FindAllPlacesWenergie() {
        List<PositionChangedValues> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectpositionwithenergie"));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PositionChangedValues value = new PositionChangedValues(
                        rs.getInt("x"),
                        rs.getInt("y"),
                        rs.getDouble("sunshine_lvl"),
                        rs.getDouble("energy_generated")
                );
                result.add(value);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    public List<Double> FindAllEnergy() {
        List<Double> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectallEnergie"));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(rs.getDouble("energy_generated"));
            }
            logger.info("result is " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    public List<PositionChangedValues> findTotalByEnergie(double energy) {
        List<PositionChangedValues> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectmapzones"));
            preparedStatement.setDouble(1, energy);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PositionChangedValues value = new PositionChangedValues(
                        rs.getInt("x"),
                        rs.getInt("y"),
                        rs.getDouble("sunshine_lvl"),
                        rs.getDouble("energy_generated")
                );
                result.add(value);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }


    public void Createapanel() {
        try {
            PreparedStatement statement = connection.prepareStatement(props.getProp("createPanels"));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int Findeapanel() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectPanels"));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("obj_id");
        } catch (SQLException e) {
            throw new RuntimeException(e); }


    }

    public void PaneltotheMap ( int id , int x ,int y )
    {
        try {
            PreparedStatement statement = connection.prepareStatement(props.getProp("insertPanels"));
            statement.setInt(1,id);
            statement.setInt(2, x );
            statement.setInt(3, y );
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int Totalpan() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("totalPanel"));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("count");
        } catch (SQLException e) {
            throw new RuntimeException(e); }


    }


}
